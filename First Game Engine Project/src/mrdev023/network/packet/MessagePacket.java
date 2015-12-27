package mrdev023.network.packet;

import java.net.*;

import mrdev023.gameengine.gamestate.*;
import mrdev023.network.common.*;
import mrdev023.network.packet.main.*;
import mrdev023.network.server.*;
import mrdev023.opengl.gui.*;

public class MessagePacket implements IPacket{
	
	public String message,pseudo;
	
	public MessagePacket(){}
	
	public MessagePacket(String message,String pseudo){this.message = message;this.pseudo = pseudo;}

	public void write(DataBuffer buff) throws Exception {
		buff.put(message);
		buff.put(pseudo);
	}

	public void read(DataBuffer buff) throws Exception {
		this.message = buff.getString();
		this.pseudo = buff.getString();
	}

	public void manage(Client client, IPacket packet) throws Exception {
		MainState.messages.add(new Message(pseudo + " : " + message));
	}

	public void manage(Client client, IPacket packet, DatagramSocket server) throws Exception {
		if(message.startsWith("/")){
			if(message.split(" ")[0].equals("/login") && message.split(" ").length == 2){
				if(message.split(" ")[1].equals(MainServer.password)){
					MainServer.sendToClient(client, new MessagePacket("login successful!", "Server"));
					client.setAdmin(true);
				}else{
					MainServer.sendToClient(client, new MessagePacket("password incorrect!", "Server"));
				}
			}else{
				if(client.isAdmin()){
					String[] cmd = message.split(" ");
					if(cmd[0].equals("/say") && cmd.length >= 2){
						MainServer.sendToClients(new MessagePacket(Utils.getStringByStringArray(cmd, 1, cmd.length - 1), "Server"));
					}else if(cmd[0].equals("/logout")){
						MainServer.sendToClient(client, new MessagePacket("logout successful!", "Server"));
						client.setAdmin(false);
					}else if(cmd[0].equals("/help")){
						MainServer.sendToClient(client, new MessagePacket("Help", "Server"));
						MainServer.sendToClient(client, new MessagePacket("/login [password]", "Server"));
						MainServer.sendToClient(client, new MessagePacket("/logout", "Server"));
						MainServer.sendToClient(client, new MessagePacket("/say", "Server"));
						MainServer.sendToClient(client, new MessagePacket("/help", "Server"));
					}else{
						MainServer.sendToClient(client, new MessagePacket("No command", "Server"));
					}
				}else{
					MainServer.sendToClient(client, new MessagePacket("You are not Admin!", "Server"));
				}
			}
				
		}
		else MainServer.sendToClients(packet);
	}

}
