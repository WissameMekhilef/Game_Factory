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

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Game {

	private static Context context;
    private static Menu menu;
	private static World world;

	private enum Context {INGAME, INMENU, INPAUSE}

	private ExecutorService service =  Executors.newFixedThreadPool(3);

	public Game() {
	    context = null;
	    world = null;

        menu = new Menu();

        switchTo(Context.INMENU);
    }

    private void pollInput() {

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
            if(Mouse.isButtonDown(0))
                world.getPauseDisplay().receiveClick(Mouse.getX(), Mouse.getY());
    		break;

    	}

    }

    private static void switchTo(Context toContext){
	    if(context == null && toContext == Context.INMENU){
            menu.playBackgroundSound();
            context = Context.INMENU;
        }else if(context == Context.INGAME && toContext == Context.INMENU){
            menu.playBackgroundSound();
            context = Context.INMENU;
        }else if(context == Context.INGAME && toContext == Context.INPAUSE){
            Sound.pause();
            context = Context.INPAUSE;
        }else if(context == Context.INPAUSE && toContext == Context.INMENU){
            menu.playBackgroundSound();
            context = Context.INMENU;
        }else if(context == Context.INPAUSE && toContext == Context.INGAME){
            Sound.play();
            context = Context.INGAME;
        }else if(context == Context.INMENU && toContext == Context.INGAME){
            world.playBackgroundSound();
            context = Context.INGAME;
        }else if(context == Context.INPAUSE && toContext == Context.INMENU){
            menu.playBackgroundSound();
            context = Context.INMENU;
        }
    }

	public void update() {
        pollInput();
		switch (context) {

            case INGAME:
                if(world.isInProgress()) {
                    world.update();
                } else {

                    world = null;
                    switchTo(Context.INMENU);
                }
                break;

            case INMENU:
                menu.update();
                if(menu.getLastButtonClicked() != null) {
                    service.execute(menu.getLastButtonClicked().getAction());
                }
                break;

            case INPAUSE:
                world.getPauseDisplay().update();
                if(world.getPauseDisplay().getLastButtonClicked() != null) {
                    service.execute(world.getPauseDisplay().getLastButtonClicked().getAction());
                }
                break;

		}

	}

	static void worldCreation(String worldToCreate) {
        try {
            world = WorldReader.worldFromJSON("worlds/"+worldToCreate+".json");
        } catch (CameraTypeException | IOException e) {
            e.printStackTrace();
        }
        switchTo(Context.INGAME);
    }

    public static void hardBackToMenu(){
        switchTo(Context.INMENU);
        world = null;
    }

    public static void backToPlay(){
        switchTo(Context.INGAME);
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
                world.getPauseDisplay().render();
                break;

            }
	}

}
