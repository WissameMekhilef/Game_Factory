package game.engine;

import game.Component;

import static org.lwjgl.opengl.GL11.*;

public class Graphics {

	public static void quadData(int x, int y, int w, int h, float[] color) {
		glColor4f(color[0], color[1], color[2], color[3]);
		glVertex2f(x, y);
		glVertex2f(x + w, y);
		glVertex2f(x + w, y + h);
		glVertex2f(x, y + h);
	}

	public static void renderQuad(int x, int y, int w, int h, float[] color) {
		glBegin(GL_QUADS);
		Graphics.quadData(x, Component.height - y, w, h, color);
		glEnd();
	}

	public static void scroll(float xScroll, float yScroll){
        glTranslatef(xScroll, yScroll, 0);
    }

}
