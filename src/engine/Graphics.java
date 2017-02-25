package engine;

import game.graphicItems.Text;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Graphics {

    /**
     *
     * @param x
     * @param y
     * @param w
     * @param h
     * @param texture
     */
    public static void renderTexture(int x, int y, int w, int h, Texture texture, boolean mosaique){
        float m = 1;
        float n = 1;
        if(mosaique){
            m = ((float) w / (float) texture.getImageWidth());

            n = ((float) h / (float) texture.getImageHeight());
        }

        //Point en bas a gauche
        glTexCoord2f(0,0);
        glVertex2f(x, y);

        //Point en bas a droite
        glTexCoord2f(m,0);
        glVertex2f(x + w, y);

        //Point en haut a droite
        glTexCoord2f(m,n);
        glVertex2f(x + w, y + h);

        //Point en haut a gauche
        glTexCoord2f(0,n);
        glVertex2f(x, y + h);
    }

    public static void renderText(Text textToDisplay, int x, int y){
        y = Launcher.height - y;
        textToDisplay.getFont().drawString(x, y, textToDisplay.getTextToDisplay(), textToDisplay.getColor());
    }

    /**
     *
     * @param x
     * @param y
     * @param w
     * @param h
     * @param texture
     */
    public static void renderQuad(int x, int y, int w, int h, Texture texture, boolean mosaique) {
	    y = Launcher.height - y;

        Color.white.bind();
        texture.bind();

        glBegin(GL_QUADS);
            renderTexture( x, y, w, h, texture, mosaique);
        glEnd();
    }

    /**
     * Fonction appeller par les camera pour bouger la vue
     * @param xScroll
     * @param yScroll
     */
	public static void scroll(float xScroll, float yScroll){
        glTranslatef(xScroll, yScroll, 0);
    }

}
