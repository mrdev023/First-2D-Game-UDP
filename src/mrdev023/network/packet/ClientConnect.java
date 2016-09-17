package mrdev023.network.packet;

import java.net.*;

import mrdev023.entities.*;
import mrdev023.gameengine.gamestate.*;
import mrdev023.math.*;
import mrdev023.network.common.*;
import mrdev023.network.packet.main.*;
import mrdev023.opengl.gui.*;

public class ClientConnect implements IPacket{

	public String pseudo;
	
	public ClientConnect(){}
	public ClientConnect(String pseudo){this.pseudo = pseudo;}
	
	public void write(DataBuffer buff) throws Exception {
		buff.put(pseudo);
	}
	
	public void read(DataBuffer buff) throws Exception {
		this.pseudo = buff.getString();
	}
	
	public void manage(Client client, IPacket packet) throws Exception {
		MainState.messages.add(new Message(pseudo + " has connected !"));
		Client.entities.add(new Player(new Vector2f(0,0),pseudo));
	}
	
	public void manage(Client client, IPacket packet, DatagramSocket server) throws Exception {
		
	}
		
	
	
}
