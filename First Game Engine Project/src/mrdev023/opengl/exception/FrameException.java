package mrdev023.opengl.exception;

public class FrameException extends Exception{

	public FrameException(String content){
		super("Frame not be created : " + content);
	}
	
	public FrameException(){
		super("Frame not be created !");
	}
	
}
