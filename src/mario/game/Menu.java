package mario.game;

import mario.game.graphicItems.MenuButton;
import mario.game.graphicItems.Text;
import org.newdawn.slick.Color;

/**
 * Created by wissamemekhilef on 14/02/2017.
 */
public class Menu {
    private MenuButton startMario, startFlappy, lastButtonClicked;

    public Menu(){
        startMario = new MenuButton(300, 100, 200, 500, GameTextureMap.textureMap.get("brique"), new Text("Mario",FontMap.map.get("Mario"), Color.green, 200, 500), "start_mario");
        startFlappy = new MenuButton(300, 100, 600, 500, GameTextureMap.textureMap.get("brique"), new Text("Flappy",FontMap.map.get("Flappy"), Color.yellow, 600, 500), "start_flappy");
    }

    public void receiveClick(int x0, int y0){
        //System.out.println("x0 = "+x0+", y0 = "+y0);
        if(startMario.isClicked(x0, y0))
            lastButtonClicked = startMario;
        if(startFlappy.isClicked(x0, y0))
            lastButtonClicked = startFlappy;
    }

    public void render(){
        startMario.render();
        startFlappy.render();
    }

    public MenuButton getLastButtonClicked() {
        return lastButtonClicked;
    }

    public void setLastButtonClicked(MenuButton lastButtonClicked) {
        this.lastButtonClicked = lastButtonClicked;
    }
}
