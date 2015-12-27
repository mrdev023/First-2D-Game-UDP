package mrdev023.gameengine.gamestate;

import static org.lwjgl.glfw.GLFW.*;

import java.awt.*;
import java.util.*;

import javax.swing.*;

import mrdev023.gameengine.*;
import mrdev023.gameengine.gamestate.main.*;
import mrdev023.network.client.*;
import mrdev023.network.packet.*;
import mrdev023.opengl.*;
import mrdev023.opengl.gui.*;

public class MainState implements IGameState{

	public static ArrayList<Message> messages = new ArrayList<Message>();
	
	public void init() {
		
	}

	public void destroy() {
		
	}
	
	public void preRender2D() {
		
	}

	public void preRenderGUI() {
		
	}

	public void renderGUI() {
		for(int i = 0;i < messages.size();i++){
			messages.get(i).render(10, 10 + i * 20);
		}
	}

	public void render2D() {
		
	}

	public void update() {
		ArrayList<Message> rmList = new ArrayList<Message>();
		for(Message m : messages){
			if(m.isTimeOut()){
				rmList.add(m);
			}
		}
		for(Message m : rmList){
			messages.remove(m);
		}
		rmList.clear();
	}

	public void updateKeyboard() {
		if(Input.isKeyDown(GLFW_KEY_M)){
			try {
				MainClient.client.send(new MessagePacket(JOptionPane.showInputDialog(null, "Entrez votre message :"),MainClient.pseudo));
			} catch (HeadlessException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void updateMouse() {
		
	}

}
