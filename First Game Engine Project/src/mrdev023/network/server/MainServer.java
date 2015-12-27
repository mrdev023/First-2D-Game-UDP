package mrdev023.network.server;
import java.io.*;
import java.net.*;
import java.util.*;

import mrdev023.network.common.*;
import mrdev023.network.packet.*;
import mrdev023.network.packet.main.*;

public class MainServer extends Thread{
	
	public static DatagramSocket server;
	public static ArrayList<Client> clients = new ArrayList<Client>();
	public static boolean isRunning = false;
	public static final int PORT = 9999;
	public static final int SLOTS = 64;
	public static final String password = "florian";

	public void run(){
		while(isRunning){
			try{
				for(Client cl : clients){
					if(cl != null){
						cl.update();
						if(cl.isTimeOut()){
							System.out.println(cl.getPseudo() + " has diconnected by TIME OUT " + cl.getAddress().getHostName() + ":" + cl.getPort());
							sendToClients(new ClientDisconnect(cl.getPseudo()));
							cl.setRunning(false);
							clients.remove(cl);
							break;
						}
					}
				}
			}catch(Exception e){e.printStackTrace();}
		}
	}
	
	public static void main(String[] args) {
		try {
			server = new DatagramSocket(9999);
			System.out.println("Server binding " + InetAddress.getLocalHost().getHostAddress() + ":" + PORT);
			isRunning = true;
			Register.registerClass();
			(new Thread(new MainServer())).start();
			while(isRunning){
				byte[] data = new byte[DataBuffer.SIZE];
				DatagramPacket p = new DatagramPacket(data,DataBuffer.SIZE);
				try {
					server.receive(p);
					if(p.getAddress() != null){
						DataBuffer buffer = new DataBuffer();
						buffer.setData(data);
						IPacket packet = (IPacket)Class.forName(Register.getClass(buffer.getInt())).newInstance();
						packet.read(buffer);
						Client c = getClient(p.getAddress(), p.getPort());
						if(c == null){
							if(clients.size() >= SLOTS){
								send(new OutOfSocketPacket(), p.getAddress(), p.getPort());
							}else{
								c = new Client(p.getAddress(), p.getPort());
								packet.manage(c, packet,server);
								clients.add(c);
								System.out.println(c.getPseudo() + " has connected " + c.getAddress().getHostName() + ":" + c.getPort());
								sendToClients(new ClientConnect(c.getPseudo()));
							}
						}else{
							packet.manage(c, packet,server);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Client getClient(InetAddress address,int port){
		for(Client cl : clients){
			if(cl.getAddress().getHostAddress().equals(address.getHostAddress()) && port == cl.getPort())return cl;
		}	
		return null;
	}
	
	public static boolean isEquals(Client c,Client c2){
		if(c.getAddress().getHostAddress().equals(c2.getAddress().getHostAddress()) && c.getPort() == c2.getPort())return true;
		else return false;
	}
	
	public static void sendToClient(Client cl,IPacket packet){
		for(Client c : clients){
			if(isEquals(cl, c))send(packet, cl.getAddress(), c.getPort());
		}
	}
	
	public static void sendToClientsWithoutClient(Client[] cl,IPacket packet){
		for(Client c : clients){
			for(Client c2 : cl){
				if(!isEquals(c2, c)){
					sendToClient(c, packet);
				}
			}
		}
	}
	
	public static void sendToClients(IPacket packet){
		for(Client c : clients){
			sendToClient(c, packet);
		}
	}
	
	public static void send(IPacket packet,InetAddress ip,int port){
		try {
			DataBuffer buffer = new DataBuffer();
			buffer.put(Register.getClassID(packet.getClass()));
			packet.write(buffer);
			DatagramPacket p = new DatagramPacket(buffer.getData(), buffer.getData().length,ip,port);
			server.send(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
