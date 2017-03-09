package game.world;

import dataMapping.Data;
import engine.Graphics;
import engine.Launcher;
import game.graphicItems.MenuButton;
import game.graphicItems.Text;
import org.newdawn.slick.Color;

import java.util.Iterator;
import java.util.TreeSet;
import java.util.concurrent.Callable;

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

        Callable<Integer> backToMenu = () -> {
            lastButtonClicked = null;
            World.hardBackToMenu();
            return 0;
        };
        Text t = new Text("Menu", Data.fontsMap.get("new_super_mario_1"), Color.green);
        listButton.add(new MenuButton(sizeXbutton, sizeYbutton, Data.texturesMap.get("brique"), t, backToMenu));

        Callable<Integer> backToPlay = () -> {
            lastButtonClicked = null;
            World.backToPlay();
            return 0;
        };
        Text t2 = new Text("Back", Data.fontsMap.get("new_super_mario_1"), Color.green);
        listButton.add(new MenuButton(sizeXbutton, sizeYbutton, Data.texturesMap.get("brique"), t2, backToPlay));

    }

    /**
     * Sauvegarde le dernier MenuButton cliqué.
     * @param x0	la position du clic sur l'axe des abscisses
     * @param y0	la position du clic sur l'axe des ordonnées
     */
    public void receiveClick(int x0, int y0) {
        for (MenuButton current : listButton) {
            if (current.isClicked(x0, y0)) {
                lastButtonClicked = current;
            }
        }
    }

    /**
     * Actualise la position des MenuButtons et du titre afin que l'affichage de la Pause
     * reste cohérent lorque la fenêtre est redimensionnée.
     */
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

    /**
     * Affiche la Pause à l'écran.
     */
    public void render(){
        Graphics.renderText(textPause, (Launcher.width - textPause.getSizeX()) / 2, 3 * (Launcher.height - textPause.getSizeY()) / 4);
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
