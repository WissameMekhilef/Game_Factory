package game;

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

	private enum Context {INWORLD, INMENU}

	public Game() {
	    context = null;
	    world = null;

        menu = new Menu();

        switchTo(Context.INMENU);
    }

    private void pollInput() {

    	switch (context) {

            case INWORLD:
                world.pollInput();
                break;

            case INMENU:
                if(Mouse.isButtonDown(0))
                    menu.receiveClick(Mouse.getX(), Mouse.getY());
                break;
    	}
    }

    /**
     *
     * @param toContext
     */
    private static void switchTo(Context toContext){
	    if(toContext == Context.INMENU){
            menu.playBackgroundSound();
            context = Context.INMENU;
        }else if(toContext == Context.INWORLD){
            world.playBackgroundSound();
            context = Context.INWORLD;
        }
    }

	public void update() {
        pollInput();
		switch (context) {

            case INWORLD:

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
		}
	}

    /**
     *
     * @param worldToCreate
     */
    static void worldCreation(String worldToCreate) {
        try {
            world = WorldReader.worldFromJSON("worlds/"+worldToCreate+".json");
        } catch (CameraTypeException | IOException e) {
            e.printStackTrace();
        }
        switchTo(Context.INWORLD);
    }

	public void render() {
		switch (context) {

            case INWORLD:
                world.render();
                break;

            case INMENU:
                menu.render();
                break;

            }
	}

}
