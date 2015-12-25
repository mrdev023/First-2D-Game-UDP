package mrdev023.network.packet;

import java.net.*;

import mrdev023.gameengine.*;
import mrdev023.network.common.*;
import mrdev023.network.packet.main.*;

public class PingClientPacket implements IPacket{
	
	public long current;

	public PingClientPacket(){
		
	}
	
	public PingClientPacket(long current){
		this.current = current;
	}
	
	
	public void write(DataBuffer buff) throws Exception {
		buff.put(current);
	}

	public void read(DataBuffer buff) throws Exception {
		this.current = buff.getLong();
	}

	public void manage(Client client, IPacket packet) throws Exception {
		GameEngine.setPing((System.currentTimeMillis() - current));
	}

	public void manage(Client client, IPacket packet, DatagramSocket server) throws Exception {
		client.send(packet);
	}

}
