package mario.game.world.entities;

import org.newdawn.slick.opengl.Texture;

/**
 * Created by wissamemekhilef on 11/02/2017.
 */
public abstract class Tangible extends Solid{

    public Tangible(int sizeX, int sizeY, int x0, int y0, Texture texture){
        super(sizeX, sizeY, x0, y0, texture);
    }
}
