package game.engine;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Graphics {

    public static void renderQuad(int x, int y, int w, int h, Texture texture) {
	    y = Component.height - y;
        Color.white.bind();
        texture.bind();

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
