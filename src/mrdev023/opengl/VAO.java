package mrdev023.opengl;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.*;

import org.lwjgl.*;


public class VAO {

	public int vao,vertexBuffer,colorBuffer,texBuffer;
	
	public VAO(){
		vao = glGenVertexArrays();
		vertexBuffer = glGenBuffers();
		colorBuffer = glGenBuffers();
		texBuffer = glGenBuffers();
	}
	
	
	public void setVertexBuffer(float... a){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(8);
		for(float c : a)buffer.put(c);
		buffer.flip();
		
		glBindVertexArray(vao);
		glBindBuffer(GL_ARRAY_BUFFER,vertexBuffer);
		glBufferData(GL_ARRAY_BUFFER,buffer,GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER,0);
		glBindVertexArray(0);
	}
	
	public void setColorBuffer(float... a){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
		for(float c : a)buffer.put(c);
		buffer.flip();
		
		glBindVertexArray(vao);
		glBindBuffer(GL_ARRAY_BUFFER,colorBuffer);
		glBufferData(GL_ARRAY_BUFFER,buffer,GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER,0);
		glBindVertexArray(0);
	}
	
	public void setTexBuffer(float... a){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(8);
		for(float c : a)buffer.put(c);
		buffer.flip();
		
		glBindVertexArray(vao);
		glBindBuffer(GL_ARRAY_BUFFER,texBuffer);
		glBufferData(GL_ARRAY_BUFFER,buffer,GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER,0);
		glBindVertexArray(0);
	}
	
	public void render(){
		
		glBindVertexArray(vao);
		
		glBindBuffer(GL_ARRAY_BUFFER,vertexBuffer);
		glVertexPointer(2, GL_FLOAT, 8, 0);
		
		glBindBuffer(GL_ARRAY_BUFFER,colorBuffer);
		glColorPointer(4,GL_FLOAT,16,0);
		
		glBindBuffer(GL_ARRAY_BUFFER,texBuffer);
		glTexCoordPointer(2,GL_FLOAT,8,0);
		
		glDrawArrays(GL_QUADS,0,4);		
		
		glBindBuffer(GL_ARRAY_BUFFER,0);
		
		glBindVertexArray(0);
		
	}
	
	
	public void destroy(){
		glDeleteBuffers(vertexBuffer);
		glDeleteBuffers(colorBuffer);
		glDeleteBuffers(texBuffer);
		glDeleteVertexArrays(vao);
	}
	
}
