package mrdev023.opengl;

import static org.lwjgl.opengl.GL11.*;

public class TextFont {
	
	public static final String font = 	"ABCDEFGHIJKLMNOPQRSTUVWXYZ      "+
										"0123456789.,!?'\"-+=/\\%()<>:;     ";

	public static void drawText(String title,int x,int y,int size,float alpha){
		int id = 0;
		title = title.toUpperCase();
		glBindTexture(GL_TEXTURE_2D,Texture.FONT.getID());
		glBegin(GL_QUADS);
		for(int i = 0;i < title.length();i++){
			id = font.indexOf(title.charAt(i));
			int xx = x + size * i;
			int yy = y;
			int ty = (id >= 32)?1:0;
			int tx = id % 32;
	
			glColor4f(1,1,1,alpha);
			
			glTexCoord2f((0 + tx)/32.0f, (0 + ty)/2.0f);
			glVertex2i(xx,yy + size);
			
			glTexCoord2f((1 + tx)/32.0f, (0 + ty)/2.0f);
			glVertex2i(xx + size,yy + size);
			
			glTexCoord2f((1 + tx)/32.0f, (1 + ty)/2.0f);
			glVertex2i(xx + size,yy);
			
			glTexCoord2f((0 + tx)/32.0f, (1 + ty)/2.0f);
			glVertex2i(xx,yy);
			
		}
		glEnd();
		glBindTexture(GL_TEXTURE_2D,0);
	}
	
}
