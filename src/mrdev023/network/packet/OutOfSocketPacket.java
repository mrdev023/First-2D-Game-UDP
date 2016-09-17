package mrdev023.network.packet;

import java.net.*;

import javax.swing.*;

import mrdev023.gameengine.*;
import mrdev023.network.common.*;
import mrdev023.network.packet.main.*;

public class OutOfSocketPacket implements IPacket{

	public void write(DataBuffer buff) throws Exception {
		
	}

	public void read(DataBuffer buff) throws Exception {
		
	}

	public void manage(Client client, IPacket packet) throws Exception {
		JOptionPane.showMessageDialog(null,"OutOfSocket","Error",JOptionPane.ERROR_MESSAGE);
		GameEngine.setRunning(false);
	}

	public void manage(Client client, IPacket packet, DatagramSocket server) throws Exception {
		
	}

}
