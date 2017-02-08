package game.entities;

import game.engine.Graphics;
import game.engine.Physics;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;

/**
 * Obstacle permet de décrire tout les éléments du décors à prendre en compte lors de la gestion de la physics
 *
 * Si c'est un élément fixe il suffit de ne pas appliquer gravite/freinage
 */
public class Obstacle extends Movable {
	private boolean fixe;

	private Texture texture;

	public Obstacle(int size, int v0, int v1, int x0, int y0, boolean pfixe) {
		super(size, v0, v1, x0, y0);
		fixe = pfixe;
        try {
            // load texture from PNG file
            texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/brique.png"));

            System.out.println("Texture loaded: "+texture);
            System.out.println(">> Image width: "+texture.getImageWidth());
            System.out.println(">> Image height: "+texture.getImageHeight());
            System.out.println(">> Texture width: "+texture.getTextureWidth());
            System.out.println(">> Texture height: "+texture.getTextureHeight());
            System.out.println(">> Texture ID: "+texture.getTextureID());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public void update() {
		if(!fixe){
			Physics.gravite(this);
			Physics.freinage(this);
		}
		//Le pattern de l'obstacle devra etre implemente ici.
	}

    public void render(){
        Graphics.renderQuad(coordonnee[0], coordonnee[1], size, size, texture);
    }



}
