package mrdev023.opengl.gui;

import mrdev023.opengl.*;

public class Message{

	public static final int TIME_OUT = 5000;
	private long current, previous, time = 0;
	private String message;
	
	
	public Message(String message){
		current = System.currentTimeMillis();
		this.message = message;
	}
	
	public void render(int x,int y){
		previous = current;
		current = System.currentTimeMillis();
		time += current - previous;
		
		if(time < TIME_OUT - 1000){
			TextFont.drawText(message, x, y, 16, 1);
		}else{
			TextFont.drawText(message, x, y, 16, (TIME_OUT - time)/1000f);
		}
		
	}
	
	public boolean isTimeOut(){
		return time > TIME_OUT;
	}
	
}
