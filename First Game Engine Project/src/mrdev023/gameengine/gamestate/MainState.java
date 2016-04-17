package mrdev023.gameengine.gamestate;

import static org.lwjgl.glfw.GLFW.*;

import java.awt.*;
import java.util.*;

import javax.swing.*;

import mrdev023.entities.*;
import mrdev023.gameengine.gamestate.main.*;
import mrdev023.network.client.*;
import mrdev023.network.common.*;
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
		if(Input.isKey(GLFW_KEY_W)){
			try {
				MainClient.client.send(new UpdateYPacket(MainClient.pseudo,Client.getEntityByName(MainClient.pseudo).getPosition().getY()+1));
			} catch (HeadlessException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(Input.isKey(GLFW_KEY_S)){
			try {
				MainClient.client.send(new UpdateYPacket(MainClient.pseudo,Client.getEntityByName(MainClient.pseudo).getPosition().getY()-1));
			} catch (HeadlessException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(Input.isKey(GLFW_KEY_A)){
			try {
				MainClient.client.send(new UpdateXPacket(MainClient.pseudo,Client.getEntityByName(MainClient.pseudo).getPosition().getX()-1));
			} catch (HeadlessException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(Input.isKey(GLFW_KEY_D)){
			try {
				MainClient.client.send(new UpdateXPacket(MainClient.pseudo,Client.getEntityByName(MainClient.pseudo).getPosition().getX()+1));
			} catch (HeadlessException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void updateMouse() {
		
	}

	@Override
	public void render2D() {
		synchronized(Client.entities){
			try{
				for(Entity e : Client.entities){
					e.render();
				}
			}catch(ConcurrentModificationException e){
				e.printStackTrace();
			}

		}
	}

}
