package game.world.entities;

import engine.Graphics;
import org.newdawn.slick.opengl.Texture;

public abstract class Solid {

    protected Texture texture;
    protected int[] coordonnee;
    protected int sizeX;
    protected int sizeY;

    public Solid(int sizeX, int sizeY, int x, int y, Texture texture) {

        coordonnee = new int[]{x, y};
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        this.texture = texture;
    }

    public int[] getCoordonnee() {
		return coordonnee;
	}

	public int getSizeX() {
        return sizeX;
    }

    public int getSizeY(){
        return sizeY;
    }

    public void render(){
        Graphics.renderQuad(coordonnee[0], coordonnee[1], sizeX, sizeY, texture, true);
    }

}
