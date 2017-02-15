package mario.game.graphicItems;

import mario.game.world.entities.Solid;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by wissamemekhilef on 14/02/2017.
 */
public class MenuButton extends Solid {

    private String content;

    private String action;

    public MenuButton(int sizeX, int sizeY, int x, int y, Texture texture, String action){
        super(sizeX, sizeY, x, y, texture);
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isClicked(int x, int y){
        return (x >= coordonnee[0] && x <= coordonnee[0] + sizeX && y <= coordonnee[1] + sizeY);
    }
}
