package mario.game.graphicItems;

import org.newdawn.slick.opengl.Texture;

import mario.game.world.entities.Solid;

/**
 * Created by wissamemekhilef on 14/02/2017.
 */
public class MenuButton extends Solid {

    private String content;

    public MenuButton(int sizeX, int sizeY, int x, int y, Texture texture){
        super(sizeX, sizeY, x, y, texture);
    }

    public boolean isClicked(int x, int y){
        return (x >= coordonnee[0] && x <= coordonnee[0] + sizeX && y <= coordonnee[1] + sizeY);
    }
}
