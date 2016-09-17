package mrdev023.network.packet.main;

public class Utils {

	public static String getStringByStringArray(String[] cmd,int begin,int end){
		String data = "";
		for(int i = begin;i <= end;i++){
			if(i == end){
				data += cmd[i];
				break;
			}
			data += cmd[i] + " ";
		}
		return data;
	}
	
}
