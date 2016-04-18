package mrdev023.io;

import java.io.*;

public class IOFile {

	public static String readFile(String path) throws Exception{
		String r = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
		String buffer = "";
		while ((buffer = reader.readLine()) != null) {
				r += buffer + "\n";
		}
		reader.close();
		return r;
	}
	
	public static void writeFile(String path,String data)throws Exception{
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
		write.write(data);
		write.close();
	}
	
}
