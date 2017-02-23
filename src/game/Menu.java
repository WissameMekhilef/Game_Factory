package game;

import dataMapping.FontMap;
import dataMapping.SoundMap;
import dataMapping.TextureMap;
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

    public Menu(){
        worldList = new TreeSet<>();
        gameTitle = new Text(Launcher.TITLE, FontMap.map.get("Tron"), Color.green);
        init();
    }

    public void playBackgroundSound(){
        Sound.play(menuSound);
    }

    private void init() {
        menuSound = SoundMap.soundMap.get("DryOut");

        try {
            Stream<Path> paths = Files.walk(Paths.get("worlds"));
            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                	String worldName = filePath.toString().split("\\.")[0];
                	String[] array = worldName.split("/");
                	if(array.length > 1) {
                		//Cas où le chemin est de la forme : "dossier1/dossier2/.../fichier.extension" (Unix)
                		worldName = array[array.length - 1];
                	} else {
                		//Cas où le chemin est de la forme : "dossier1\\dossier2\\...\\fichier.extension" (Windows)
                		array = worldName.split("\\\\");
                		worldName = array[array.length - 1];
                	}
                    Runnable action = createAction(worldName);
                    worldList.add(new MenuButton(sizeXbutton, sizeYbutton, TextureMap.textureMap.get("brique"), new Text(worldName, FontMap.map.get("Mario_1"), Color.green), action));
                }
            });
            paths.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        exit = new MenuButton(50, 50, TextureMap.iconMap.get("red_cross"), null, exitGame());

    }

    private Runnable createAction(String x){
        final String worldToCreate = x;
        Runnable r = () -> worldCreation(worldToCreate);
        return r;
    }

    private Runnable exitGame() {
    	return () -> Launcher.running = false;
    }

    public void receiveClick(int x0, int y0) {
        if(exit.isClicked(x0, y0)) {
    		lastButtonClicked = exit;
    	} else {
	        Iterator<MenuButton> it = worldList.iterator();
	        while(it.hasNext()) {
	            MenuButton current = it.next();
	            if(current.isClicked(x0, y0)) {
	                lastButtonClicked = current;
	            }
	        }
    	}
    }

    public void update(){

        Iterator<MenuButton> it = worldList.iterator();

        int x0 = spacebetween;
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

    public void render(){
    	Graphics.renderText(gameTitle, Launcher.width / 2 - FontMap.map.get("Tron").getWidth(gameTitle.getTextToDisplay())/2, 3 * Launcher.height / 4 - FontMap.map.get("Tron").getHeight(gameTitle.getTextToDisplay())/2);
        Iterator<MenuButton> it = worldList.iterator();
        while (it.hasNext()){
            it.next().render();
        }
        exit.render();
    }

    public MenuButton getLastButtonClicked() {
        return lastButtonClicked;
    }

    public void setLastButtonClicked(MenuButton lastButtonClicked) {
        this.lastButtonClicked = lastButtonClicked;
    }
}
