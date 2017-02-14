package game;

import game.entities.Solid;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by wissamemekhilef on 14/02/2017.
 */
public class MenuButton extends Solid {

    private String content;

    public MenuButton(int sizeX, int sizeY, int x, int y, Texture texture){
        super(sizeX, sizeY, x, y, texture);


    }
}
