package engine;

import game.graphicItems.Text;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Graphics {

	/**
	 * Affiche un Text à l'écran.
	 * @param textToDisplay	le Text à afficher
	 * @param x				la distance en pixels entre le bord gauche de la fenêtre et celui de la Texture
	 * @param y				la distance en pixels entre le bord supérieur de la fenêtre et celui de la Texture
	 */
    public static void renderText(Text textToDisplay, int x, int y){
        y = Launcher.height - y;
        textToDisplay.getFont().drawString(x, y, textToDisplay.getTextToDisplay(), textToDisplay.getColor());
    }

    /**
     * Affiche une Texture à l'écran.
     * @param x			la distance en pixels entre le bord gauche de la fenêtre et celui de la Texture
     * @param y			la distance en pixels entre le bord supérieur de la fenêtre et celui de la Texture
     * @param w			la largeur de la Texture
     * @param h			la hauteur de la Texture
     * @param texture	la Texture à afficher
     * @param mosaique	vrai si la Texture doit être dupliquée pour respecter les dimensions demandées
     * 					faux si la Texture doit être étirée
     */
    public static void renderQuad(int x, int y, int w, int h, Texture texture, boolean mosaique) {

	    y = Launcher.height - y;

	    float m = 1;
        float n = 1;

        if(mosaique) {
            m = ((float) w / (float) texture.getImageWidth());
            n = ((float) h / (float) texture.getImageHeight());
        }

        Color.white.bind();
        texture.bind();

        glBegin(GL_QUADS);

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

        glEnd();

    }

    /**
     * Déplace le scrolling du jeu.
     * @param xScroll	le déplacement sur l'axe des abscisses
     * @param yScroll	le déplacement sur l'axe des ordonnées
     */
	public static void scroll(float xScroll, float yScroll){
        glTranslatef(xScroll, yScroll, 0);
    }

}
