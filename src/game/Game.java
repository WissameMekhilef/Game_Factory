package game;

import dataMapping.TextureMap;
import engine.Graphics;
import engine.Launcher;
import engine.Sound;
import exceptions.CameraTypeException;
import game.world.World;
import game.world.WorldParameters;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

import org.json.JSONException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Game {

	private Context context;
    private Menu menu;
    private Sound soundContext;
	private static World world;

	private enum Context {INGAME, INMENU, INPAUSE}

	public Game() {
	    context = null;
	    world = null;

        menu = new Menu();
        soundContext = new Sound();

        switchTo(Context.INMENU);
    }

    public void pollInput() {

    	switch (context) {

    	case INGAME:
    		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
            	world.getPlayer().jumpWanted();
            if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
            	world.getPlayer().leftWanted();
            if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
            	world.getPlayer().rightWanted();
    		while (Keyboard.next())
    			if ((!Keyboard.getEventKeyState()) && (Keyboard.getEventKey() == Keyboard.KEY_P))
    				switchTo(Context.INPAUSE);
    		break;

    	case INMENU:
    		if(Mouse.isButtonDown(0))
                menu.receiveClick(Mouse.getX(), Mouse.getY());
    		break;

    	case INPAUSE:
    		while (Keyboard.next())
    			if ((!Keyboard.getEventKeyState()) && (Keyboard.getEventKey() == Keyboard.KEY_P))
                    switchTo(Context.INGAME);
    		break;

    	}

    }

    public void switchTo(Context newContext){
	    if(context == null && newContext == Context.INMENU){
            context = Context.INMENU;
            menu.playBackgroundSound();
        }else if(context == Context.INGAME && newContext == Context.INMENU){
            context = Context.INMENU;
            world = null;
            menu.playBackgroundSound();
        }else if(context == Context.INGAME && newContext == Context.INPAUSE){
            Sound.pause();
            context = Context.INPAUSE;
        }else if(context == Context.INPAUSE && newContext == Context.INMENU){
            context = Context.INMENU;
            menu.playBackgroundSound();
        }else if(context == Context.INPAUSE && newContext == Context.INGAME){
            context = Context.INGAME;
            Sound.play();
        }else if(context == Context.INMENU && newContext == Context.INGAME){
            context = Context.INGAME;
            world.playBackgroundSound();
        }
    }

	public void update() {
        pollInput();

		switch (context) {

            case INGAME:
                if(world.isInProgress()) {
                    world.update();
                } else {
                    switchTo(Context.INMENU);
                }
                break;

            case INMENU:
                menu.update();
                if(menu.getLastButtonClicked() != null) {
                    ExecutorService service =  Executors.newFixedThreadPool(2);
                    service.execute(menu.getLastButtonClicked().getAction());
                    menu.setLastButtonClicked(null);
                    if(world != null)
                        switchTo(Context.INGAME);
                }
                break;

            case INPAUSE:
                break;

		}

	}

	public static void worldCreation(String worldToCreate) {
        try {
            world = WorldReader.worldFromJSON("worlds/"+worldToCreate+".json");
        } catch (CameraTypeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

	public void render() {

		switch (context) {

            case INGAME:
                world.render();
                break;

            case INMENU:
                menu.render();
                break;

            case INPAUSE:
                //Il faut créer un objet pause.
                int size = 200;
                int x = (Launcher.width - size) / 2 - WorldParameters.getxScroll();
                int y = (Launcher.height + size) / 2 + WorldParameters.getyScroll();
                Graphics.renderQuad(x, y, size, size, TextureMap.textureMap.get("pause"));

            }

	}

}
