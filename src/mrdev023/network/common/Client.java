package mrdev023.network.common;

import java.io.*;
import java.net.*;
import java.util.*;

import mrdev023.entities.*;
import mrdev023.network.packet.main.*;

public class Client extends Thread{

	private DatagramSocket client;
	private boolean isRunning = false;
	private boolean isConnect = false;
	private InetAddress address;
	private int port;
	private int TIME_OUT = 15000;
	private long current,elapsed,previous;
	private String pseudo = "Default";
	private String state = "";
	private boolean isAdmin = false;
	
	//Client
	public static ArrayList<Entity> entities = new ArrayList<Entity>();
	
	//Server
	public Player player;
	
	
	public Client(InetAddress address,int port) throws SocketException{
		client = new DatagramSocket();
		this.address = address;
		this.port = port;
		isRunning = true;
		current = System.currentTimeMillis();
		this.start();
	}
	
	public void update(){
		previous = current;
		current = System.currentTimeMillis();
		elapsed += current - previous;
	}
	
	public void setPseudo(String pseudo){
		this.pseudo = pseudo;
	}
	
	public void run(){
		while(isRunning){	
			up();
			byte[] data = new byte[DataBuffer.SIZE];
			DatagramPacket packet = new DatagramPacket(data, DataBuffer.SIZE);
			try {
				client.receive(packet);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if(packet.getAddress() != null){
				isConnect = true;
				if(is(packet.getAddress(),packet.getPort()) || true){
					DataBuffer dataBuff = new DataBuffer();
					dataBuff.setData(data);
					try {
						String name = Register.getClass(dataBuff.getInt());
						IPacket obj = (IPacket)Class.forName(name).newInstance();
						if(obj == null)continue;
						obj.read(dataBuff);
						obj.manage(this, obj);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		isConnect = false;
		destroy();
	}
	
	public void up(){
		
	}
	
	public void destroy(){
		isRunning = false;
		isConnect = false;
		client.close();
	}
	
	public boolean isTimeOut(){
		return elapsed > TIME_OUT;
	}
	
	public boolean is(InetAddress address,int port){
		return this.address.getHostAddress().equals(address.getHostAddress()) && this.port == port;
	}
	
	public void send(IPacket packet) throws Exception{
		DataBuffer data = new DataBuffer();
		data.put(Register.getClassID(packet.getClass()));
		packet.write(data);
		DatagramPacket sendPacket = new DatagramPacket(data.getData(),data.getData().length,address,port); 
		client.send(sendPacket);
		elapsed = 0;
	}
	
	public void render2D() {
		for(Entity e : entities)e.render();
	}

	public static Entity getEntityByName(String name){
		for(Entity e : entities){
			if(e.getName().equals(name))return e;
		}
		return null;
	}
	
	public static int getIndexEntityByName(String name){
		for(int i = 0;i < entities.size();i++){
			if(entities.get(i).getName().equals(name))return i;
		}
		return -1;
	}

	public DatagramSocket getClient() {
		return client;
	}

	public void setClient(DatagramSocket client) {
		this.client = client;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isConnect() {
		return isConnect;
	}

	public void setConnect(boolean isConnect) {
		this.isConnect = isConnect;
	}

	public InetAddress getAddress() {
		return address;
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getTIME_OUT() {
		return TIME_OUT;
	}

	public void setTIME_OUT(int tIME_OUT) {
		TIME_OUT = tIME_OUT;
	}

	public long getCurrent() {
		return current;
	}

	public void setCurrent(long current) {
		this.current = current;
	}

	public long getElapsed() {
		return elapsed;
	}

	public void setElapsed(long elapsed) {
		this.elapsed = elapsed;
	}

	public long getPrevious() {
		return previous;
	}

	public void setPrevious(long previous) {
		this.previous = previous;
	}

	public String getStatee() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPseudo() {
		return pseudo;
	}
	
	
	
}
