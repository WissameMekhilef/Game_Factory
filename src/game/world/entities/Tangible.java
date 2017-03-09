package game.world.entities;

import org.newdawn.slick.opengl.Texture;

public abstract class Tangible extends Solid{

    public Tangible(int sizeX, int sizeY, int x0, int y0, Texture texture){
        super(sizeX, sizeY, x0, y0, texture);
    }
}
