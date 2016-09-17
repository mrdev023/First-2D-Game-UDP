package mrdev023.gameengine.gamestate.main;

import mrdev023.gameengine.gamestate.*;

public enum GameState {

	MAIN(new MainState());
	
	IGameState state;
	
	GameState(IGameState state){
		this.state = state;
	}
	
	public void init(){
		this.state.init();
	}
	
	public void destroy(){
		this.state.destroy();
	}
	
	public void preRender2D(){
		this.state.preRender2D();
	}
	
	public void preRenderGUI(){
		this.state.preRenderGUI();
	}
	
	public void renderGUI(){
		this.state.renderGUI();
	}
	
	public void render2D(){
		this.state.render2D();
	}
	
	public void update(){
		this.state.update();
	}
	
	public void updateKeyboard(){
		this.state.updateKeyboard();
	}
	
	public void updateMouse(){
		this.state.updateMouse();
	}
	
}
