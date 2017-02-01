package game.engine;

import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Texture {
	
int largeur,hauteur,id;

public Texture(int largeur, int hauteur, int id) {
	this.largeur = largeur;
	this.hauteur = hauteur;
	this.id = id;
}

public void chargementTexture(String s){
	BufferedImage image=null;
	URL entree;
	int larg,haut,i,j;
	try {	
	image=null;
	entree=Texture.class.getResource(s);
	image=ImageIO.read(entree);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	haut=image.getHeight();
	larg=image.getWidth();
	
}
public void lier() {
	glBindTexture(GL_TEXTURE_2D, this.id);
}

public void delier() {
	glBindTexture(GL_TEXTURE_2D, 0);
}


}
