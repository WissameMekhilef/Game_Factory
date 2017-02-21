package game;

import mario.engine.Launcher;
import mario.engine.Sound;
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

/**
 * Created by wissamemekhilef on 14/02/2017.
 */
public class Menu {
    private MenuButton lastButtonClicked;

    private int sizeXbutton = 200;
    private int sizeYbutton = 100;
    private int spacebetween = 10;

    private TreeSet<MenuButton> worldList;

    private Audio menuSound;

    public Menu(){
        worldList = new TreeSet<>();
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
                    worldList.add(new MenuButton(sizeXbutton, sizeYbutton, GameTextureMap.textureMap.get("brique"), new Text(worldName,FontMap.map.get("Mario"), Color.green), worldName));
                }
            });
            paths.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void receiveClick(int x0, int y0){
        //System.out.println("x0 = "+x0+", y0 = "+y0);
        Iterator<MenuButton> it = worldList.iterator();
        while (it.hasNext()){
            MenuButton current = it.next();
            if(current.isClicked(x0, y0)){
                //System.out.println("debug");
                lastButtonClicked = current;
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
    }

    public void render(){
        Iterator<MenuButton> it = worldList.iterator();
        while (it.hasNext()){
            it.next().render();
        }
    }

    public MenuButton getLastButtonClicked() {
        return lastButtonClicked;
    }

    public void setLastButtonClicked(MenuButton lastButtonClicked) {
        this.lastButtonClicked = lastButtonClicked;
    }
}
