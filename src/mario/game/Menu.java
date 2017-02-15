package mario.game;

import mario.game.graphicItems.MenuButton;

/**
 * Created by wissamemekhilef on 14/02/2017.
 */
public class Menu {
    private MenuButton startButton, lastButtonClicked;

    public Menu(){
    	startButton = new MenuButton(300, 100, 200, 500, GameTextureMap.textureMap.get("brique"), "start");
    }

    public void receiveClick(int x0, int y0){
        //System.out.println("x0 = "+x0+", y0 = "+y0);
        if(startButton.isClicked(x0, y0))
            lastButtonClicked = startButton;
    }

    public void render(){
        startButton.render();
    }

    public MenuButton getLastButtonClicked() {
        return lastButtonClicked;
    }

    public void setLastButtonClicked(MenuButton lastButtonClicked) {
        this.lastButtonClicked = lastButtonClicked;
    }
}
