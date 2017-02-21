package mario.game.world.entities;

import org.newdawn.slick.opengl.Texture;

/**
 * Obstacle permet de décrire tout les éléments du décors à prendre en compte lors de la gestion de la physics
 *
 * Si c'est un élément fixe il suffit de ne pas appliquer gravite/freinage
 */
public class Obstacle extends Solid {

	public Obstacle(int sizeX, int sizeY, int x, int y, Texture texture) {
		super(sizeX, sizeY, x, y, texture);
	}

	public void update() {

	}

}
