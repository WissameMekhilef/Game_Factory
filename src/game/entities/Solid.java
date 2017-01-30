package game.entities;

import game.engine.Graphics;

import java.util.Random;

public abstract class Solid {

    protected float[] color ;
    protected int[] coordonnee;
    protected int size;

    public Solid(int size, int x, int y) {
    	Random random = new Random();
    	color = new float[]{0.5f, 0.5f, 0.5f, 1};
        //color = new float[]{random.nextFloat(), random.nextFloat(), random.nextFloat(), 1};
        coordonnee = new int[]{x, y};
        this.size = size;
    }

    public void render(){
        Graphics.renderQuad(coordonnee[0], coordonnee[1], size, size, color);
    }

    public int[] getCoordonnee() {
		return coordonnee;
	}

	public int getSize() {
        return size;
    }

}
