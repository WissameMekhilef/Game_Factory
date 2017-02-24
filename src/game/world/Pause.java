package game.world;

import dataMapping.Data;
import engine.Graphics;
import engine.Launcher;
import game.Game;
import game.graphicItems.MenuButton;
import game.graphicItems.Text;
import org.newdawn.slick.Color;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by wissamemekhilef on 23/02/2017.
 */
public class Pause {
    private MenuButton lastButtonClicked;

    private Text textPause; //which is an Object to handle a specific font type, color
    private TreeSet<MenuButton> listButton;

    private int sizeXbutton = 200;
    private int sizeYbutton = 100;
    private int spacebetween = 10;

    public Pause(Text textPause){
        this.textPause = textPause;
        this.listButton = new TreeSet<>();

        Runnable backToMenu = () -> {
            lastButtonClicked = null;
            Game.hardBackToMenu();
        };
        listButton.add(new MenuButton(sizeXbutton, sizeYbutton, Data.texturesMap.get("brique"), new Text("Menu", Data.fontsMap.get("new_super_mario_1"), Color.green), backToMenu));

        Runnable backToPlay = () -> {
            lastButtonClicked = null;
            Game.backToPlay();
        };
        listButton.add(new MenuButton(sizeXbutton, sizeYbutton, Data.texturesMap.get("brique"), new Text("Back", Data.fontsMap.get("new_super_mario_1"), Color.green), backToPlay));

    }

    public void receiveClick(int x0, int y0) {
        for (MenuButton current : listButton) {
            if (current.isClicked(x0, y0)) {
                lastButtonClicked = current;
            }
        }
    }

    public void update(){
        Iterator<MenuButton> it = listButton.iterator();

        int x0 = Launcher.width / 2 - ((spacebetween + sizeXbutton) / 2) * listButton.size();
        int y0 = Launcher.height / 3 - spacebetween - sizeYbutton;
        while (it.hasNext()){
            MenuButton current = it.next();
            if (x0 + sizeXbutton < Launcher.width) {
                current.setX(x0);
                current.setY(y0);
                x0 += spacebetween + sizeXbutton;
            }else{
                x0 = spacebetween;
                y0 -= spacebetween + sizeYbutton;
                current.setX(x0);
                current.setY(y0);
            }
        }
    }

    public void render(){
        Graphics.renderText(textPause, Launcher.width / 2 - Data.fontsMap.get("tron").getWidth(textPause.getTextToDisplay())/2, 3 * Launcher.height / 4 - Data.fontsMap.get("tron").getHeight(textPause.getTextToDisplay())/2);
        for (MenuButton aWorldList : listButton) {
            aWorldList.render();
        }
    }

    public MenuButton getLastButtonClicked() {
        return lastButtonClicked;
    }

    public void setLastButtonClicked(MenuButton lastButtonClicked) {
        this.lastButtonClicked = lastButtonClicked;
    }
}
