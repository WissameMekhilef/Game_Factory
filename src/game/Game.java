package game;

import engine.Sound;
import exceptions.CameraTypeException;
import game.world.World;
import org.lwjgl.input.Mouse;

import java.io.IOException;
import java.util.concurrent.Future;

import static engine.Launcher.poolThread;

public class Game {

	private static Context context;
    private static Menu menu;
	private static World world;

	public enum Context {INGAME, INMENU, INPAUSE}

	public Game() {
	    context = null;
	    world = null;

        menu = new Menu();

        switchTo(Context.INMENU);
    }

    private void pollInput() {

    	switch (context) {

            case INGAME:
                world.pollInput();
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

    public static void switchTo(Context toContext){
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
                    Future future = poolThread.submit(menu.getLastButtonClicked().getAction());
                    do{
                    }while (!future.isDone());
                }
                break;

            case INPAUSE:
                world.getPauseDisplay().update();
                if(world.getPauseDisplay().getLastButtonClicked() != null) {
                    Future future = poolThread.submit(world.getPauseDisplay().getLastButtonClicked().getAction());
                    do{
                    }while (!future.isDone());
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
