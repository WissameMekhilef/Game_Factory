package game.entities;

import game.engine.Physics;
import game.engine.Texture;

/**
 * Obstacle permet de décrire tout les éléments du décors à prendre en compte lors de la gestion de la physics
 *
 * Si c'est un élément fixe il suffit de ne pas appliquer gravite/freinage
 */
public class Obstacle extends Movable {
	private boolean fixe;
	private Texture texture;
	public enum type {
		EAU,BETON,BOIS,BRIQUE;
	}

	public Obstacle(int size, int v0, int v1, int x0, int y0, boolean pfixe) {
		super(size, v0, v1, x0, y0);
		fixe = pfixe;
	}
	public Obstacle(int size, int v0, int v1, int x0, int y0, boolean pfixe,type t) {
		super(size, v0, v1, x0, y0);
		fixe = pfixe;
		if (t==type.EAU) {
			
			//x=2 y=0
		}
		if (t==type.BETON) {
			//x=1 y=0
		}
		if (t==type.BRIQUE) {
			//x=0 y=0
			this.texture=Texture.brique;
		}
	
	}
	public void update() {
		if(!fixe){
			Physics.gravite(this);
			Physics.freinage(this);
		}
		//Le pattern de l'obstacle devra etre implemente ici.
	}

}
