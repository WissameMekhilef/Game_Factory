package game;

import dataMapping.Data;
import engine.Graphics;
import engine.Launcher;
import engine.Sound;
import game.graphicItems.MenuButton;
import game.graphicItems.Text;
import org.newdawn.slick.Color;
import org.newdawn.slick.openal.Audio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.concurrent.Callable;

import static game.Game.worldCreation;

public class Menu {
    private MenuButton lastButtonClicked;

    private int sizeXbutton = 200;
    private int sizeYbutton = 100;
    private int spacebetween = 10;

    private MenuButton exit;
    private TreeSet<MenuButton> worldList;

    private Audio menuSound;

    private Text gameTitle;

    Menu(){
        worldList = new TreeSet<>();
        gameTitle = new Text(Launcher.TITLE, Data.fontsMap.get("tron"), Color.green);
        init();
    }

    /**
     * Lance la musique du Menu.
     */
    void playBackgroundSound(){
        Sound.play(menuSound);
    }

    /**
     * Initialise le Menu en créant un MenuButton pour chaque fichier .json stocké dans le projet,
     * ainsi qu'un MenuButton pour quitter le jeu.
     */
    private void init() {
        menuSound = Data.soundsMap.get("DryOut");

        try {
            Files.walk(Paths.get("worlds")).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                	String worldName = Data.getFileName(filePath);
                    Callable<Integer> action = createAction(worldName);
                    Text t = new Text(worldName, Data.fontsMap.get("new_super_mario_1"), Color.green);
                    worldList.add(new MenuButton(sizeXbutton, sizeYbutton, Data.texturesMap.get("brique"), t, action));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        exit = new MenuButton(50, 50, Data.texturesMap.get("red_cross"), null, exitGame());

    }

    /**
     * Crée un Callable afin qu'un clic sur un MenuButton lance le World correspondant.
     * @param worldName	le nom du World à lancer
     * @return	un Callable qui sera associé à un MenuButton
     */
    private Callable<Integer> createAction(String worldName){
        final String worldToCreate = worldName;
        return () -> {
            lastButtonClicked = null;
            worldCreation(worldToCreate);
            return 0;
        };
    }

    /**
     * Crée un Callable afin qu'un clic sur la croix rouge ferme la fenêtre.
     * @return	un Callable qui sera associé au MenuButton "Quitter"
     */
    private Callable<Integer> exitGame() {
    	return () -> {
            lastButtonClicked = null;
    	    Launcher.running = false;
            return 0;
    	};
    }

    /**
     * Sauvegarde le dernier MenuButton cliqué.
     * @param x0	la position du clic sur l'axe des abscisses
     * @param y0	la position du clic sur l'axe des ordonnées
     */
    void receiveClick(int x0, int y0) {
        if(exit.isClicked(x0, y0)) {
    		lastButtonClicked = exit;
    	} else {
            for (MenuButton current : worldList) {
                if (current.isClicked(x0, y0)) {
                    lastButtonClicked = current;
                }
            }
    	}
    }

    /**
     * Actualise la position des MenuButtons et du titre du jeu afin que l'affichage du Menu
     * reste cohérent lorque la fenêtre est redimensionnée.
     */
    void update(){

        Iterator<MenuButton> it = worldList.iterator();

        int x0 = Launcher.width / 2 - ((spacebetween + sizeXbutton) / 2) * worldList.size();
        int y0 = Launcher.height / 2 - spacebetween - sizeYbutton;
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

        exit.setX(Launcher.width - (exit.getSizeX() + 10));
        exit.setY(Launcher.height - 10);

    }

    /**
     * Affiche le Menu à l'écran.
     */
    void render(){
    	Graphics.renderText(gameTitle, (Launcher.width - gameTitle.getSizeX()) / 2, 3 * (Launcher.height - gameTitle.getSizeY()) / 4);
        worldList.forEach(world -> world.render());
        exit.render();
    }

    MenuButton getLastButtonClicked() {
        return lastButtonClicked;
    }

}
