package game.world.entities;

import engine.Graphics;
import org.newdawn.slick.opengl.Texture;

public class Door extends Tangible {

    public Door(int sizeX, int sizeY, int x, int y, Texture texture){
        super(sizeX, sizeY, x, y, texture);
    }

    /**
     * Affiche la Door à l'écran.
     */
    public void render(){
        Graphics.renderQuad(coordonnee[0], coordonnee[1], sizeX, sizeY, texture, false);
    }
}
