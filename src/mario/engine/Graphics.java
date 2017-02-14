package mario.engine;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Graphics {

    public static void renderTexture(int x, int y, int w, int h, Texture texture){

        boolean textureMoinsLarge = texture.getImageWidth() < w;
        boolean textureMoinsHaute = texture.getImageHeight() < h;
        float coeffX = 1;
        if(textureMoinsLarge){
            /*System.out.println("################");

            System.out.println("textureMoinsLarge = "+textureMoinsLarge);
            System.out.println("textureMoinsHaute = "+textureMoinsHaute);
            System.out.println("texture.getImageWidth() = "+texture.getImageWidth());
            System.out.println("w = "+w);*/

            coeffX = (float) w / (float) texture.getImageWidth();
            //System.out.println("coeffX = "+coeffX);

           // System.out.println("################");
        }


        //Point en haut a gauche
        glTexCoord2f(0,0);
        glVertex2f(x, y);

        //Point en haut a droite
        glTexCoord2f(1,0);
        glVertex2f(x + w, y);

        //Point en bas a droite
        glTexCoord2f(1,1);
        glVertex2f(x + w, y + h);

        //Point en bas a gauche
        glTexCoord2f(0,1);
        glVertex2f(x, y + h);



    }

    public static void renderQuad(int x, int y, int w, int h, Texture texture) {
	    y = Launcher.height - y;

        Color.white.bind();
        texture.bind();

        glBegin(GL_QUADS);
            renderTexture( x, y, w, h, texture);
        glEnd();
    }

	public static void scroll(float xScroll, float yScroll){
        glTranslatef(xScroll, yScroll, 0);
    }

}
