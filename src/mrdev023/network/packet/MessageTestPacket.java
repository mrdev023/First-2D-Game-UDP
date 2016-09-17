package mrdev023.network.packet;

import java.net.*;

import mrdev023.network.common.*;
import mrdev023.network.packet.main.*;

public class MessageTestPacket implements IPacket{
	
	private String message;
	
	public MessageTestPacket(){}
	
	public MessageTestPacket(String message){
		this.message = message;
	}

	public void write(DataBuffer buff) throws Exception{
		buff.put(message);
	}
	
	public void read(DataBuffer buff) throws Exception{
		this.message = buff.getString();
	}
	
	public void manage(Client client,IPacket packet)throws Exception{
		
	}
	
	public void manage(Client client,IPacket packet,DatagramSocket server)throws Exception{
		
	}

	public String getMessage(){
		return this.message;
	}
	
}
