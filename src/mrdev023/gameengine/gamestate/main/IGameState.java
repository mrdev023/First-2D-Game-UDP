package mrdev023.gameengine.gamestate.main;

public interface IGameState {

	public void init();
	public void destroy();
	public void preRender2D();
	public void preRenderGUI();
	public void renderGUI();
	public void render2D();
	public void update();
	public void updateKeyboard();
	public void updateMouse();
	
}
