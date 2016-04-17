package mrdev023.network.packet;

import java.net.*;

import mrdev023.gameengine.gamestate.*;
import mrdev023.math.*;
import mrdev023.network.common.*;
import mrdev023.network.packet.main.*;
import mrdev023.network.server.*;

public class UpdateYPacket implements IPacket{

	private String pseudo;
	private int x;
	
	public UpdateYPacket(){}
	
	public UpdateYPacket(String pseudo,double y){
		this.pseudo = pseudo;
		this.x = (int)y;
	}

	@Override
	public void write(DataBuffer buff) throws Exception {
		buff.put(pseudo);
		buff.put(x);
	}

	@Override
	public void read(DataBuffer buff) throws Exception {
		this.pseudo = buff.getString();
		this.x = buff.getInt();
	}

	@Override
	public void manage(Client client, IPacket packet) throws Exception {
		int i = Client.getIndexEntityByName(pseudo);
		if(i != -1)Client.entities.get(i).setPosition(new Vector2f(Client.entities.get(i).getPosition().getX(),x));
	}

	@Override
	public void manage(Client client, IPacket packet, DatagramSocket server) throws Exception {
		client.player.setPosition(new Vector2f(client.player.getPosition().getX(),x));
		MainServer.sendToClients(packet);
	}
	
}
