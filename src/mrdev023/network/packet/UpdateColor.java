package mrdev023.network.packet;

import java.net.*;

import mrdev023.math.*;
import mrdev023.network.common.*;
import mrdev023.network.packet.main.*;
import mrdev023.network.server.*;

public class UpdateColor implements IPacket{

	private int r,g,b,a;
	private String pseudo;
	
	public UpdateColor(){}
	
	public UpdateColor(String pseudo,Color4f color){
		this.pseudo = pseudo;
		r = (int)(color.r * 100);
		g = (int)(color.g * 100);
		b = (int)(color.b * 100);
		a = (int)(color.a * 100);
	}

	@Override
	public void write(DataBuffer buff) throws Exception {
		buff.put(pseudo);
		buff.put(r);
		buff.put(g);
		buff.put(b);
		buff.put(a);
	}

	@Override
	public void read(DataBuffer buff) throws Exception {
		this.pseudo = buff.getString();
		r = buff.getInt();
		g = buff.getInt();
		b = buff.getInt();
		a = buff.getInt();
	}

	@Override
	public void manage(Client client, IPacket packet) throws Exception {
		int i = Client.getIndexEntityByName(pseudo);
		if(i != -1)Client.entities.get(i).setColor(new Color4f(r/100.0f,g/100.0f,b/100.0f,a/100.0f));
	}

	@Override
	public void manage(Client client, IPacket packet, DatagramSocket server) throws Exception {
		client.player.setColor(new Color4f(r/100.0f,g/100.0f,b/100.0f,1.0f));
		MainServer.sendToClients(packet);
	}
	
	
	
}
