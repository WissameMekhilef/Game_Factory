package game.world.entities;

import engine.Graphics;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by wissamemekhilef on 14/02/2017.
 */
public class Door extends Tangible {

    public Door(int sizeX, int sizeY, int x, int y, Texture texture){
        super(sizeX, sizeY, x, y, texture);
    }

    public void render(){
        Graphics.renderQuad(coordonnee[0], coordonnee[1], sizeX, sizeY, texture, false);
    }
}
