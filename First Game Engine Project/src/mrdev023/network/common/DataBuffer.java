package mrdev023.network.common;

import java.nio.*;

public class DataBuffer {

	public static final int SIZE = 1024;
	
	public byte[] data = new byte[SIZE];
	public int p = 0;
	public int used = 0;
	
	public void setData(byte[] data){
		this.data = data;
	}
	
	public void put(byte a) throws Exception{
		if(p >= SIZE)throw new Exception("Overflow !");
		data[p++] = a;
		if(p>used)used = p;
	}
	
	public void put(short a) throws Exception{
		put((byte)((a >> 8) & 0xFF));
		put((byte)((a) & 0xFF));
	}
	
	public void put(int a) throws Exception{
		put((byte)((a >> 24) & 0xFF));
		put((byte)((a >> 16) & 0xFF));
		put((byte)((a >> 8) & 0xFF));
		put((byte)((a) & 0xFF));
	}
	
	public void put(long a) throws Exception{
		put((byte)((a >> 54) & 0xFF));
		put((byte)((a >> 48) & 0xFF));
		put((byte)((a >> 40) & 0xFF));
		put((byte)((a >> 32) & 0xFF));
		put((byte)((a >> 24) & 0xFF));
		put((byte)((a >> 16) & 0xFF));
		put((byte)((a >> 8) & 0xFF));
		put((byte)((a) & 0xFF));
	}
	
	public void put(char a) throws Exception{
		put((byte)a);
	}
	
	public void put(String a) throws Exception{
		put(a.length());
		for(int i = 0;i < a.length();i++)put(a.charAt(i));
	}
	
	public byte getByte() throws Exception{
		if(p >= SIZE)throw new Exception("Overflow !");
		if(p + 1>used)used = p + 1;
		return data[p++];
	}
	
	public short getShort() throws Exception{
		return ByteBuffer.wrap(new byte[]{getByte(),getByte()}).getShort();
	}
	
	public int getInt() throws Exception{
		return ByteBuffer.wrap(new byte[]{getByte(),getByte(),getByte(),getByte()}).getInt();
	}
	
	public long getLong() throws Exception{
		return ByteBuffer.wrap(new byte[]{getByte(),getByte(),getByte(),getByte(),getByte(),getByte(),getByte(),getByte()}).getLong();
	}
	
	public char getChar() throws Exception{
		return (char)getByte();
	}
	
	public String getString() throws Exception{
		int length = getInt();
		char[] b = new char[length];
		for(int i = 0;i < length;i++)b[i] = getChar();
		return new String(b);
	}
	
	public void flip(){
		p = 0;
	}
	
	public int available(){
		return SIZE - used;
	}
	
	public int limit(){
		return SIZE;
	}
	
	public byte[] getData(){
		return this.data;
	}
	
}
