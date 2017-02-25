package game.world;

import dataMapping.Data;
import engine.Graphics;
import engine.Physics;
import engine.Sound;
import game.graphicItems.Text;
import game.world.camera.Camera;
import game.world.entities.*;
import org.newdawn.slick.Color;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import static engine.Launcher.poolThread;

public class World {

	private Player player;
	private List<Obstacle> plateau;
    private List<Coin> coinsList;
    private Door doorOut;

	private static List<PotentialCollision> listPC;
	private static Collection<Callable<Integer>> isStuckRoutines;

	private Camera scroller;

	private static boolean inProgress;

    private Pause pauseDisplay;

	public World(int width, int height, Player player, Camera camera, Door door, ArrayList<Obstacle> plateau) {
	    pauseDisplay = new Pause(new Text("Pause", Data.fontsMap.get("tron"), Color.red));

        isStuckRoutines = new ArrayList<>();

		WorldParameters.setBordBas(0);
        WorldParameters.setBordHaut(height);
        WorldParameters.setBordGauche(0);
        WorldParameters.setBordDroit(width);

		this.player = player;
		this.doorOut = door;
		this.plateau = plateau;

        coinsList = new ArrayList<>();
		listPC = new ArrayList<>();

        WorldParameters.setxScroll(0);
        WorldParameters.setyScroll(0);

        //scroller = new ForceScroller(2, 2);
        scroller = camera;

		generate();

		inProgress = true;


	}

	private void generate() {
        Collection<PotentialCollision> pcCollection = new ArrayList<>();
        Collection<PotentialCollision> pcCollectionDoor = new ArrayList<>();

        plateau.forEach(obstacle -> {
            pcCollection.add(new PotentialCollision(player, obstacle));
        });

        pcCollection.forEach(pc -> {
            pc.setActionIfCollision(() -> Physics.replaceAfterCollision(pc));
        });


        pcCollectionDoor.add(new PotentialCollision(player, doorOut));
        pcCollectionDoor.forEach(pc -> pc.setActionIfCollision(() -> levelEnd("outByDoor")));

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

	public void update() {
        scroller.translateView();
		if(player.isAlive()){
            try {
                poolThread.invokeAll(isStuckRoutines);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.update();
		}else{
            levelEnd("dead");
		}
	}

	public void render() {
        Graphics.scroll(WorldParameters.getxScroll(), WorldParameters.getyScroll());
		for(Obstacle obstacle : plateau) {
			obstacle.render();
		}
		for(Coin coin : coinsList)
		    coin.render();
		doorOut.render();

		player.render();
	}


    /**
     * Cette fonction est appelé a la fin du monde
     */
	private void levelEnd(String status){
	    switch (status){
            case "outByDoor":
                System.out.println("outByDoor");
                setInProgress(false);
                break;
            case "dead":
                System.out.println("dead");
                setInProgress(false);
                break;
        }

    }

    public boolean isInProgress() {
        return inProgress;
    }

    private static void setInProgress(boolean pinProgress) {
        inProgress = pinProgress;
    }

    public void playBackgroundSound(){
        Sound.play(WorldParameters.getBackgroundMusic());
    }

    public Pause getPauseDisplay() {
        return pauseDisplay;
    }

    public void setPauseDisplay(Pause pauseDisplay) {
        this.pauseDisplay = pauseDisplay;
    }

    public Player getPlayer(){
        return player;
    }
}
