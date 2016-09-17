package mrdev023.network.packet.main;

import java.net.*;

import mrdev023.network.common.*;

public interface IPacket {

	public void write(DataBuffer buff) throws Exception;
	public void read(DataBuffer buff) throws Exception;
	public void manage(Client client,IPacket packet)throws Exception;
	public void manage(Client client,IPacket packet,DatagramSocket server)throws Exception;

	
}
