package game.engine;

import game.Component;

import static org.lwjgl.opengl.GL11.*;

public class Graphics {
/*
	public static void quadData(int x, int y, int w, int h, float[] color) {
		glColor4f(color[0], color[1], color[2], color[3]);
		glVertex2f(x, y);
		glVertex2f(x + w, y);
		glVertex2f(x + w, y + h);
		glVertex2f(x, y + h);
	}
	*/
/*
	public static void renderQuad(int x, int y, int w, int h, float[] color) {
		glBegin(GL_QUADS);
		Graphics.quadData(x, Component.height - y, w, h, color);
		glEnd();
	}
	*/
	
	
	public static void quadData(int x, int y, int w, int h, float[] color,int xO, int yO) {
		float tailleGrille =32.0f;
		glColor4f(color[0], color[1], color[2], color[3]);
		glTexCoord2f(((0 + xO) / tailleGrille), (0 + yO) / tailleGrille);
		glVertex2f(x, y);
		glTexCoord2f(((1 + xO) / tailleGrille), (0 + yO) / tailleGrille);
		glVertex2f(x + w, y);
		glTexCoord2f(((1 + xO) / tailleGrille), (1 + yO) / tailleGrille);
		glVertex2f(x + w, y + h);
		glTexCoord2f(((0 + xO) / tailleGrille), (1 + yO) / tailleGrille);
		glVertex2f(x, y + h);
	}

	public static void renderQuad(int x, int y, int w, int h, float[] color,int xO, int yO) {
		glBegin(GL_QUADS);
		quadData(x, y, w, h, color, xO, yO);
		glEnd();
	}

	public static void quadData(int x, int y, int w, int h, float[] color) {
		glColor4f(color[0], color[1], color[2], color[3]);
		glTexCoord2f(0, 0);
		glVertex2f(x, y);
		glTexCoord2f(1, 0);
		glVertex2f(x + w, y);
		glTexCoord2f(1, 1);
		glVertex2f(x + w, y + h);
		glTexCoord2f(1, 1);
		glVertex2f(x, y + h);
	}
	public static void renderQuad(int x, int y, int w, int h, float[] color) {
		glBegin(GL_QUADS);
		quadData(x, y, w, h, color);
		glEnd();
	}
	public static void render_entite(float x, float y, int w, int h, float[] color,float taille,int xO, int yO){
		glBegin(GL_QUADS);
		
		glColor4f(color[0], color[1], color[2], color[3]);
		glTexCoord2f(((0 + xO) / taille), (0 + yO) / taille);
		glVertex2f(x, y);
		glTexCoord2f(((1 + xO) / taille), (0 + yO) / taille);
		glVertex2f(x + w, y);
		glTexCoord2f(((1 + xO) / taille), (1 + yO) / taille);
		glVertex2f(x + w, y + h);
		glTexCoord2f(((0 + xO) / taille), (1 + yO) / taille);
		glVertex2f(x, y + h);
		
		
		glEnd();
	}

}
