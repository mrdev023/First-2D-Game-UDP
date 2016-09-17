package mrdev023.network.packet;

import java.net.*;

import mrdev023.math.*;
import mrdev023.network.client.*;
import mrdev023.network.common.*;
import mrdev023.network.packet.main.*;
import mrdev023.network.server.*;

public class UpdatePseudoPacket implements IPacket{
	
	public String pseudo,npseudo;
	
	public UpdatePseudoPacket(){}
	
	public UpdatePseudoPacket(String pseudo,String npseudo){
		this.pseudo = pseudo;
		this.npseudo = npseudo;
	}

	@Override
	public void write(DataBuffer buff) throws Exception {
		buff.put(pseudo);
		buff.put(npseudo);
	}

	@Override
	public void read(DataBuffer buff) throws Exception {
		pseudo = buff.getString();
		npseudo = buff.getString();
	}

	@Override
	public void manage(Client client, IPacket packet) throws Exception {
		int i = Client.getIndexEntityByName(pseudo);
		if(i != -1)Client.entities.get(i).setName(npseudo);
		if(MainClient.pseudo.equals(pseudo)){
			MainClient.pseudo = npseudo;
		}
	}

	@Override
	public void manage(Client client, IPacket packet, DatagramSocket server) throws Exception {
		client.setPseudo(npseudo);
		MainServer.sendToClients(packet);
	}

}
