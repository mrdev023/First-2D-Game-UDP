package mrdev023.opengl;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import mrdev023.opengl.exception.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.glfw.Callbacks.*;
import org.lwjgl.glfw.GLFWWindowSizeCallback.SAM;

import java.nio.*;

public class Frame {
	
	//Variables static de configuration de la fenetre
	//-----------------------------------------------------------------------
	public static final int
		POSITION_CENTER = -1;
	//-----------------------------------------------------------------------
	
	//Variables internes de la fenetre
	//-----------------------------------------------------------------------
	private GLFWErrorCallback errorCallback;
	private boolean resized = false;
	private long windowID = 0;
	private int width = 0,height = 0;
	private String TITLE;
	private boolean isResizable;
	private int px,py;
	private boolean isVSync;
	//-----------------------------------------------------------------------
	
	public Frame(String title,int w,int h,boolean isResizable,boolean isVSync,int px,int py) throws FrameException{
		//definie la sortie d'erreur
		errorCallback = new GLFWErrorCallback() {
			public void invoke(int error, long description) {
				//System.err.println("ID : " + error + " | Description :" + description);				
			}
		};
		glfwSetErrorCallback(errorCallback);
		
		//init
		if(glfwInit() != GL11.GL_TRUE)throw new FrameException("GLFW not init");
		
		//config de la fenetre
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GL11.GL_FALSE);	
		setResizable(isResizable);
		this.isResizable = isResizable;
		
		//creation de la fenetre
		windowID = glfwCreateWindow(w,h,title,NULL,NULL);
		this.TITLE = title;
		this.width = w;
		this.height = h;
		if(windowID == NULL)throw new FrameException("Frame not created");
		
		//definie la position de la fenetre
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		if(px == POSITION_CENTER){
			this.px = (vidmode.width()-width)/2;
			px = this.px;
		}
		if(py == POSITION_CENTER){
			this.py = (vidmode.height()-height)/2;
			py = this.py;
		}
		glfwSetWindowPos(windowID,px,py);
		
		//Creer un context OpenGL
		glfwMakeContextCurrent(windowID);
		
		//VSync
		if(isVSync){
			glfwSwapInterval(1);
		}
		
		glfwSetWindowSizeCallback(windowID, new GLFWWindowSizeCallback() {
			public void invoke(long window, int w, int h) {
				resized=true;
	            width = w;
	            height = h;
			}
		});  
		
		glfwShowWindow(windowID);
		glfwMakeContextCurrent(windowID);
		GL.createCapabilities();
	}
	
	public Frame(String title,int w,int h,boolean isResizable,boolean isVSync) throws FrameException{
		//definie la sortie d'erreur
		errorCallback = new GLFWErrorCallback() {
			public void invoke(int error, long description) {
				//System.err.println("ID : " + error + " | Description :" + description);				
			}
		};
		glfwSetErrorCallback(errorCallback);
		
		//init
		if(glfwInit() != GL11.GL_TRUE)throw new FrameException("GLFW not init");
		
		//config de la fenetre
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GL11.GL_FALSE);	
		setResizable(isResizable);
		this.isResizable = isResizable;
		
		//creation de la fenetre
		windowID = glfwCreateWindow(w,h,title,NULL,NULL);
		this.TITLE = title;
		this.width = w;
		this.height = h;
		if(windowID == NULL)throw new FrameException("Frame not created");
		
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		this.px = (vidmode.width()-width)/2;
		this.py = (vidmode.height()-height)/2;
		glfwSetWindowPos(windowID,px,py);
		
		//Creer un context OpenGL
		glfwMakeContextCurrent(windowID);
		
		//VSync
		if(isVSync){
			glfwSwapInterval(1);
		}
		
		glfwSetWindowSizeCallback(windowID, new GLFWWindowSizeCallback() {
			public void invoke(long window, int w, int h) {
				resized=true;
	            width = w;
	            height = h;
			}
		});  
		
		
		glfwShowWindow(windowID);
		glfwMakeContextCurrent(windowID);
		GL.createCapabilities();
	}
	
	public void setFramePosition(int px,int py){
		//definie la position de la fenetre
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		if(px == POSITION_CENTER){
			this.px = (vidmode.width()-width)/2;
		}
		if(py == POSITION_CENTER){
			this.py = (vidmode.height()-height)/2;
		}
		glfwSetWindowPos(windowID,px,py);
	}
	
	public void setResizable(boolean resizable){
		if(resizable){
			glfwWindowHint(GLFW_RESIZABLE, GL11.GL_TRUE);
			isResizable = true;
		}else{
			glfwWindowHint(GLFW_RESIZABLE, GL11.GL_FALSE);
			isResizable = false;
		}
	}
	
	/**
	 * @Info Mets à jour les entrées du clavier 
	 */
	public void updateEvent(){
		glfwPollEvents();
	}
	
	/**
	 * @Info Mets à jour l'affichage de la fenêtre
	 */
	public void updateDisplay(){
		glfwSwapBuffers(windowID);
	}
	
	/**
	 * @Info Détruit la fenêtre
	 */
	public void destroy(){
		glfwDestroyWindow(windowID);
		glfwTerminate();
	}
	
	/**
	 * @return true si la fenetre à été agrandi ou pas
	 */
	public boolean wasResized(){
		if(resized)return !(resized = !resized);
		else return false;
	}
	
	/**
	 * @return index 0 = width et 1 = height
	 */
	public int[] getWindowSize(){
		return new int[]{width,height};
	}
	
	/**
	 * @return width la largeur de l'ecran
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * @return height la hauteur de l'ecran
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * @return true si la croix rouge a��t� cliquer
	 */
	public boolean isClosedRequested(){
		if(glfwWindowShouldClose(windowID) == GL11.GL_FALSE){
			return false;
		}else{
			return true;
		}
	}
	
	public void setTitle(String title){
		glfwSetWindowTitle(windowID, title);
	}
	
	public long getWindowID(){
		return windowID;
	}
}
