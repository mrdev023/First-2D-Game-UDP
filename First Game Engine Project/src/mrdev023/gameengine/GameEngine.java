package mrdev023.gameengine;

import java.io.*;

import mrdev023.audio.*;
import mrdev023.gameengine.gamestate.main.*;
import mrdev023.network.client.*;
import mrdev023.network.common.*;
import mrdev023.network.packet.*;
import mrdev023.opengl.*;

public class GameEngine {
	
	private static Frame frame;
	
	private static long current,previous,elapsedInfo,elapsedTicks,elapsedPing;
	private static int countFPS = 0,countTICKS = 0,FPS,TICKS;
	private static boolean IsRunning = false;
	private static GameState state = GameState.MAIN;
	private static String title;
	private static long ping = 0; 

	public static void start(String title,int width,int height){
		try{
			GameEngine.title = title;
			frame = new Frame(title, width, height, false, false);
			init();
			loop();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void init(){
//		try{
//			File file = new File("/log/" + System.currentTimeMillis() + ".txt");
//			if(!file.exists()){
//				file.createNewFile();
//			}
//			PrintStream out = new PrintStream(file);
//			System.setOut(out);
//		}catch(Exception e){e.printStackTrace();}
		Input.init();
		current = System.nanoTime();
		state.init();
		Texture.init();
		Audio.create();
	}
	
	public static void loop(){
		IsRunning = true;
		while(IsRunning){
			previous = current;
			current = System.nanoTime();
			elapsedInfo += current - previous;
			elapsedTicks += current - previous;
			elapsedPing += current - previous;
			
			if(frame.isClosedRequested()){
				IsRunning = false;
				continue;
			}
			
			if(elapsedTicks >= 1000000000/60){
				frame.updateEvent();
				Input.update();
				state.updateKeyboard();
				state.updateMouse();
				state.update();
				MainClient.client.update();
				countTICKS++;
				elapsedTicks -= 1000000000/60;
			}else{
				DisplayManager.clear();
				DisplayManager.preRender2D();
				DisplayManager.render2D();
				DisplayManager.preRenderGUI();
				DisplayManager.renderGUI();
				frame.updateDisplay();
				countFPS++;
			}
			
			if(elapsedPing >= 1000000000){
				MainClient.send(new PingClientPacket(System.currentTimeMillis()));
				elapsedPing = 0;
			}
			
			if(elapsedInfo >= 1000000000){
				FPS = countFPS;
				countFPS = 0;
				TICKS = countTICKS;
				countTICKS = 0;
				frame.setTitle(title + " | FPS:" + FPS + " | TICKS:" + TICKS + " | PING:" + ping + "ms" + " | " + MainClient.pseudo + ((MainClient.client.isConnect())?" connected !":" disconnected !"));
				elapsedInfo -= 1000000000;
			}
		}
		destroy();
	}
	
	public static void destroy(){
		state.destroy();
		Audio.destroy();
		Input.destroy();
		frame.destroy();
		MainClient.send(new ClientDisconnect(MainClient.pseudo));
		MainClient.client.destroy();
	}

	public static Frame getFrame() {
		return frame;
	}

	public static boolean isRunning() {
		return IsRunning;
	}

	public static void setRunning(boolean isRunning) {
		IsRunning = isRunning;
	}

	public static GameState getGameState() {
		return state;
	}

	public static void setGameState(GameState state) {
		GameEngine.state.destroy();
		GameEngine.state = state;
		GameEngine.state.init();
	}
	
	public static void setPing(long ping){
		GameEngine.ping = ping;
	}
	
	
}
