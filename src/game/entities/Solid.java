package game.entities;

import game.engine.Graphics;
import org.newdawn.slick.opengl.Texture;

public abstract class Solid {

    protected Texture texture;
    protected int[] coordonnee;
    protected int size;

    public Solid(int size, int x, int y, Texture texture) {

        coordonnee = new int[]{x, y};
        this.size = size;

        this.texture = texture;
    }

    public void render(){
        Graphics.renderQuad(coordonnee[0], coordonnee[1], size, size, texture);
    }

    public int[] getCoordonnee() {
		return coordonnee;
	}

	public int getSize() {
        return size;
    }

}
