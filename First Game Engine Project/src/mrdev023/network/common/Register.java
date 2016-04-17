package mrdev023.network.common;

import java.util.*;

import mrdev023.network.packet.*;

public class Register {

	private static ArrayList<String> classReg = new ArrayList<String>();
	
	public static void registerClass(){
		addClass(ChangePseudoPacket.class);
		addClass(ClientConnect.class);
		addClass(ClientDisconnect.class);
		addClass(MessagePacket.class);
		addClass(MessageTestPacket.class);
		addClass(OutOfSocketPacket.class);
		addClass(PingClientPacket.class);
		addClass(UpdateXPacket.class);
		addClass(UpdateYPacket.class);
		addClass(UpdateColor.class);
	}
	
	public static void addClass(Class cl){
		classReg.add(cl.getName());
	}
	
	public static String getClass(int i){
		return classReg.get(i);
	}
	
	public static int getClassID(Class cl){
		for(int i = 0;i < classReg.size();i++)if(cl.getName().equals(classReg.get(i)))return i;
		return -1;
	}
	
}
