package game.entities;

import org.newdawn.slick.opengl.Texture;

/**
 * Obstacle permet de décrire tout les éléments du décors à prendre en compte lors de la gestion de la physics
 *
 * Si c'est un élément fixe il suffit de ne pas appliquer gravite/freinage
 */
public class Obstacle extends Solid {

	public Obstacle(int sizeX, int sizeY, int x0, int y0, Texture texture) {
		super(sizeX, sizeY, x0, y0, texture);
	}

	public void update() {

	}

}
