package game.world;

import dataMapping.Data;
import engine.Graphics;
import engine.Physics;
import engine.Sound;
import game.graphicItems.Text;
import game.world.camera.Camera;
import game.world.entities.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import static engine.Launcher.poolThread;

public class World {

	private Player player;
	private List<Obstacle> plateau;
    private List<Coin> coinsList;
    private Door doorOut;

	private List<PotentialCollision> listPC;
	private Collection<Callable<Integer>> isStuckRoutines;

	private Camera scroller;

    private Pause pauseDisplay;
    private static EndingScreen endingScreen;
    private HashMap<Integer, Callable<Integer>>  keyCommandsToActionInWorld;
    private HashMap<Integer, Callable<Integer>>  keyCommandsToActionInEndingScreen;
    private HashMap<Integer, Callable<Integer>>  keyCommandsToActionInPause;
    private Collection<Callable<Integer>> actionToExecute = new ArrayList<>();

    private enum Context {ISPLAYING , INPAUSE, ISOVER};
    private static Context context;

    private static boolean worldOver;

	public World(Player player, Camera camera, Door door, ArrayList<Obstacle> plateau) {
	    pauseDisplay = new Pause(new Text("Pause", Data.fontsMap.get("tron"), Color.red));
        endingScreen = null;

        genereCommande();

        isStuckRoutines = new ArrayList<>();

		this.player = player;
		this.doorOut = door;
		this.plateau = plateau;

        coinsList = new ArrayList<>();
		listPC = new ArrayList<>();

        scroller = camera;

		generate();

		worldOver = false;
		context = Context.ISPLAYING;
	}

	private void genereCommande(){
        keyCommandsToActionInWorld = new HashMap<>();
        keyCommandsToActionInWorld.put(Keyboard.KEY_LEFT, () -> {
            player.leftWanted();
            return 0;
        });
        keyCommandsToActionInWorld.put(Keyboard.KEY_RIGHT, () -> {
            player.rightWanted();
            return 0;
        });
        keyCommandsToActionInWorld.put(Keyboard.KEY_SPACE, () -> {
            player.jumpWanted();
            return 0;
        });
        keyCommandsToActionInWorld.put(Keyboard.KEY_P, () -> {
            switchTo(Context.INPAUSE);
            return 0;
        });


        keyCommandsToActionInEndingScreen = new HashMap<>();
        keyCommandsToActionInEndingScreen.put(Keyboard.KEY_SPACE, () -> {
            worldOver = true;
            return 0;
        });
    }

	private void generate() {
        Collection<PotentialCollision> pcCollection = new ArrayList<>();
        Collection<PotentialCollision> pcCollectionDoor = new ArrayList<>();

        plateau.forEach(obstacle -> pcCollection.add(new PotentialCollision(player, obstacle)));

        pcCollection.forEach(pc -> pc.setActionIfCollision(() -> Physics.replaceAfterCollision(pc)));

        pcCollectionDoor.add(new PotentialCollision(player, doorOut));
        pcCollectionDoor.forEach(pc -> pc.setActionIfCollision(() -> playerWin().run()));

        listPC.addAll(pcCollection);
        listPC.addAll(pcCollectionDoor);

        listPC.forEach(pc -> {
            isStuckRoutines.add(collisionRoutine(pc));
        });

	}

    public static Callable<Integer> collisionRoutine(PotentialCollision pcX){
        return () -> {
            if(Physics.isStuck(pcX))
                pcX.getActionIfCollision().run();
            return 0;
        };
    }

    public static Runnable playerWin(){
        return () -> {
            endingScreen = new EndingScreen(true, 2000);
            switchTo(Context.ISOVER);
        };
    }

    public static Runnable playerDeath(){
        return () -> {
            endingScreen = new EndingScreen(false, 2000);
            switchTo(Context.ISOVER);
        };
    }

    public static void hardBackToMenu(){
        worldOver = true;
    }

    public static void backToPlay(){
        switchTo(Context.ISPLAYING);
    }


    private static void switchTo(Context toContext){
        if(toContext == Context.INPAUSE){
            Sound.pause();
            context = Context.INPAUSE;
        }else if(toContext == Context.ISPLAYING){
            Sound.play();
            context = Context.ISPLAYING;
        }else if(toContext == Context.ISOVER){
            context = Context.ISOVER;
        }
    }

	public void update() {
        switch (context){
            case INPAUSE:
                pauseDisplay.update();
                if(pauseDisplay.getLastButtonClicked() != null) {
                    Future future = poolThread.submit(pauseDisplay.getLastButtonClicked().getAction());
                    do{
                    }while (!future.isDone());
                }
                break;

            case ISPLAYING:
                scroller.translateView();
                if(player.isAlive()){
                    try {
                        poolThread.invokeAll(isStuckRoutines);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    player.update();
                }else{
                    playerDeath().run();
                }

            case ISOVER:
                break;
        }

	}

	public void render() {
        switch (context){
            case INPAUSE:
                pauseDisplay.render();
                break;

            case ISPLAYING:
                Graphics.scroll(WorldParameters.getxScroll(), WorldParameters.getyScroll());
                for(Obstacle obstacle : plateau) {
                    obstacle.render();
                }
                for(Coin coin : coinsList)
                    coin.render();
                doorOut.render();

                player.render();
                break;

            case ISOVER:
                endingScreen.render();
                break;
        }

	}


    public void pollInput(){
        switch (context){
            case INPAUSE:
                keyCommandsToActionInPause.forEach((keyToCheck, actionToRunIfPressed) -> {
                    if(Keyboard.isKeyDown(keyToCheck))
                        actionToExecute.add(actionToRunIfPressed);
                });
                if(Mouse.isButtonDown(0))
                    pauseDisplay.receiveClick(Mouse.getX(), Mouse.getY());
                break;
            case ISPLAYING:
                keyCommandsToActionInWorld.forEach((keyToCheck, actionToRunIfPressed) -> {
                    if(Keyboard.isKeyDown(keyToCheck))
                        actionToExecute.add(actionToRunIfPressed);
                });
                break;
            case ISOVER:
                keyCommandsToActionInEndingScreen.forEach((keyToCheck, actionToRunIfPressed) -> {
                    if(Keyboard.isKeyDown(keyToCheck))
                        actionToExecute.add(actionToRunIfPressed);
                });
                break;
        }
        try {
            poolThread.invokeAll(actionToExecute);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        actionToExecute.clear();
    }

    public void playBackgroundSound(){
        Sound.play(WorldParameters.getBackgroundMusic());
    }


    public boolean isInProgress(){
        return !worldOver;
    }
}
