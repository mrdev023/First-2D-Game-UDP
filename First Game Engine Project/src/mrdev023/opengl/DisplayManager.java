package mrdev023.opengl;

import mrdev023.gameengine.*;
import static org.lwjgl.opengl.GL11.*;

public class DisplayManager {

	public static void clear(){
		glClearColor(1,1,1,1);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public static void preRender2D(){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, GameEngine.getFrame().getWidth(), 0, GameEngine.getFrame().getHeight(), -1, 1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glEnable(GL_TEXTURE_2D);
		glEnable (GL_BLEND);
		glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		GameEngine.getGameState().preRender2D();
	}
	
	public static void preRenderGUI(){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, GameEngine.getFrame().getWidth(), 0, GameEngine.getFrame().getHeight(), -1, 1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glEnable(GL_TEXTURE_2D);
		glEnable (GL_BLEND);
		glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		GameEngine.getGameState().preRenderGUI();
	}
	
	public static void render2D(){
		GameEngine.getGameState().render2D();
	}
	
	public static void renderGUI(){
		GameEngine.getGameState().renderGUI();
	}
	
}
