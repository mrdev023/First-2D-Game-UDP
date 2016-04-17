package mrdev023.entities;

import mrdev023.math.*;
import mrdev023.opengl.*;
import mrdev023.opengl.gui.*;

import static org.lwjgl.opengl.GL11.*;

public abstract class Entity {

	private Vector2f position;
	private String name;
	private Color4f color;
	
	public Entity(Vector2f pos,String name){
		this.name = name;
		this.position = pos;
		this.color = new Color4f(Mathf.random(),Mathf.random(),Mathf.random(),1.0f);
	}
	
	public Entity(Vector2f pos){
		this.name = "No Name";
		this.position = pos;
	}
	
	public void render(){
		TextFont.drawText(name,(int)position.getX() - (name.length() * 16)/2 + 25, (int)position.getY() + 50, 16, 1);
		glBegin(GL_QUADS);
			color.bind();
			glVertex2f(position.getX(), position.getY());
			glVertex2f(position.getX(), position.getY() + 50);
			glVertex2f(position.getX() + 50, position.getY() + 50);
			glVertex2f(position.getX() + 50, position.getY());
		glEnd();
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color4f getColor() {
		return color;
	}

	public void setColor(Color4f color) {
		System.out.println(color);
		this.color = color;
	}
	
	
	
}
