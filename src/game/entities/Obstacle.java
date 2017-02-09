package game.entities;

import game.engine.Graphics;
import org.newdawn.slick.opengl.Texture;

/**
 * Obstacle permet de décrire tout les éléments du décors à prendre en compte lors de la gestion de la physics
 *
 * Si c'est un élément fixe il suffit de ne pas appliquer gravite/freinage
 */
public class Obstacle extends Solid {

	public Obstacle(int size, int x0, int y0, Texture texture) {
		super(size, x0, y0, texture);
	}

	public void update() {

	}

    public void render(){
        Graphics.renderQuad(coordonnee[0], coordonnee[1], size, size, texture);
    }



}
