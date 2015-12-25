package mrdev023.network.packet;

import java.net.*;

import mrdev023.network.common.*;
import mrdev023.network.packet.main.*;

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
		
	}
	
	public void manage(Client client, IPacket packet, DatagramSocket server) throws Exception {
		
	}
	
}
