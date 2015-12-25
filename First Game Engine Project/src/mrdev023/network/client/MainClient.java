package mrdev023.network.client;
import java.net.*;
import java.nio.*;

import javax.swing.*;

import mrdev023.gameengine.*;
import mrdev023.network.common.*;
import mrdev023.network.packet.*;
import mrdev023.network.packet.main.*;

public class MainClient {

	public static Client client;
	public static String pseudo = "";
	
	public static void startClient() {
		try {
			String input = JOptionPane.showInputDialog(null,"Entrer ip:port du serveur");
			String[] i = input.split(":");
			String pseudo = JOptionPane.showInputDialog(null,"Pseudo:");
			client = new Client(InetAddress.getByName(i[0]),Integer.parseInt(i[1]));
			client.send(new ChangePseudoPacket(pseudo));
			GameEngine.start("2D Game UDP", 800, 800);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void send(IPacket packet){
		try {
			client.send(packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
