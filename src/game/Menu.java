package game;

/**
 * Created by wissamemekhilef on 14/02/2017.
 */
public class Menu {
    private MenuButton startButton;
    private Game inWichGameAmI;

    public Menu(Game gameParent){
        inWichGameAmI = gameParent;

        startButton = new MenuButton(300, 100, 200, 500, inWichGameAmI.getTextures().textureMap.get("brique"));
    }

    public void receiveClick(int x0, int y0){

    }

    public void render(){
        startButton.render();
    }
}
