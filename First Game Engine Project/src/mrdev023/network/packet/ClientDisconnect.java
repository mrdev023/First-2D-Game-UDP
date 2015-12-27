package mrdev023.network.packet;

import java.net.*;

import mrdev023.gameengine.gamestate.*;
import mrdev023.network.common.*;
import mrdev023.network.packet.main.*;
import mrdev023.network.server.*;
import mrdev023.opengl.gui.*;

public class ClientDisconnect implements IPacket{

public String pseudo;
	
	public ClientDisconnect(){}
	public ClientDisconnect(String pseudo){this.pseudo = pseudo;}
	
	public void write(DataBuffer buff) throws Exception {
		buff.put(pseudo);
	}
	
	public void read(DataBuffer buff) throws Exception {
		this.pseudo = buff.getString();
	}
	
	public void manage(Client client, IPacket packet) throws Exception {
		MainState.messages.add(new Message(pseudo + " has disconnected !"));
	}
	
	public void manage(Client client, IPacket packet, DatagramSocket server) throws Exception {
		System.out.println(client.getPseudo() + " has diconnected " + client.getAddress().getHostName() + ":" + client.getPort());
		MainServer.sendToClients(new ClientDisconnect(client.getPseudo()));
		client.setRunning(false);
		MainServer.clients.remove(client);
	}
	
}
