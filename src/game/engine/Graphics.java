package game.engine;

import game.Component;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

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

    public static void renderQuad(int x, int y, int w, int h, Texture texture) {
	    y = Component.height - y;
        Color.white.bind();
        texture.bind(); // or GL11.glBind(texture.getTextureID());

        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f(x,y);
        glTexCoord2f(1,0);
        glVertex2f(x + w,y);
        glTexCoord2f(1,1);
        glVertex2f(x + w, y + h);
        glTexCoord2f(0,1);
        glVertex2f(x, y + h);
        glEnd();
    }

	public static void scroll(float xScroll, float yScroll){
        glTranslatef(xScroll, yScroll, 0);
    }

}
