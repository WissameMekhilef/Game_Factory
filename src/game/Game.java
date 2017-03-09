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

	/**
	 * Récupère les entrées clavier et souris.
	 */
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
     * Change de Context si le joueur sélectionne un World dans le Menu, ou s'il quitte un World et revient au Menu.
     * @param newContext	le nouveau Context
     */
    private static void switchTo(Context newContext){
	    if(newContext == Context.INMENU)
            menu.playBackgroundSound();
	    else if(newContext == Context.INWORLD)
            world.playBackgroundSound();
	    context = newContext;
    }

    /**
     * Actualise les données du jeu.
     */
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
     * Crée un World à partir d'un fichier .json.
     * @param worldToCreate	le nom du fichier
     */
    static void worldCreation(String worldToCreate) {
        try {
            world = WorldReader.worldFromJSON("worlds/"+worldToCreate+".json");
        } catch (CameraTypeException | IOException e) {
            e.printStackTrace();
            System.out.println("Error catch");
        }
        switchTo(Context.INWORLD);
    }

    /**
     * Selon le Context, affiche le World ou le Menu du jeu.
     */
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
