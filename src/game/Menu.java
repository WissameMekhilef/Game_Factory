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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.stream.Stream;

import static game.Game.worldCreation;

/**
 * Created by wissamemekhilef on 14/02/2017.
 */
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

    void playBackgroundSound(){
        Sound.play(menuSound);
    }

    private void init() {
        menuSound = Data.soundsMap.get("DryOut");

        try {
            Stream<Path> paths = Files.walk(Paths.get("worlds"));
            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                	String worldName = Data.getFileName(filePath);
                    Runnable action = createAction(worldName);
                    worldList.add(new MenuButton(sizeXbutton, sizeYbutton, Data.texturesMap.get("brique"), new Text(worldName, Data.fontsMap.get("new_super_mario_1"), Color.green), action));
                }
            });
            paths.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        exit = new MenuButton(50, 50, Data.texturesMap.get("red_cross"), null, exitGame());

    }

    private Runnable createAction(String x){
        final String worldToCreate = x;
        return () -> {
            lastButtonClicked = null;
            worldCreation(worldToCreate);
        };
    }

    private Runnable exitGame() {
    	return () -> {
    	    Launcher.running = false;
            boutonTraite();
    	};
    }

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

    void render(){
    	Graphics.renderText(gameTitle, Launcher.width / 2 - Data.fontsMap.get("tron").getWidth(gameTitle.getTextToDisplay())/2, 3 * Launcher.height / 4 - Data.fontsMap.get("tron").getHeight(gameTitle.getTextToDisplay())/2);
        for (MenuButton aWorldList : worldList) {
            aWorldList.render();
        }
        exit.render();
    }

    MenuButton getLastButtonClicked() {
        return lastButtonClicked;
    }

    public void boutonTraite() {
        this.lastButtonClicked = null;
    }
}
