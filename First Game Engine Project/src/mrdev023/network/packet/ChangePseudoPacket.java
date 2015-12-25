package mrdev023.network.packet;

import java.net.*;

import mrdev023.*;
import mrdev023.network.client.*;
import mrdev023.network.common.*;
import mrdev023.network.packet.main.*;

public class ChangePseudoPacket implements IPacket{
	
	private String pseudo;
	
	public ChangePseudoPacket(){}
	public ChangePseudoPacket(String pseudo){
		this.pseudo = pseudo;
	}

	public void write(DataBuffer buff) throws Exception {
		buff.put(pseudo);
	}

	public void read(DataBuffer buff) throws Exception {
		this.pseudo = buff.getString();
	}

	public void manage(Client client, IPacket packet) throws Exception{
		MainClient.pseudo = pseudo;
	}
	
	public void manage(Client client, IPacket packet, DatagramSocket server) throws Exception {
		client.setPseudo(pseudo);
		client.send(packet);
	}

	
	
}
