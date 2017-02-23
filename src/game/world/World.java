package game.world;

import dataMapping.FontMap;
import engine.Graphics;
import engine.Physics;
import engine.Sound;
import game.Game;
import game.graphicItems.Text;
import game.world.camera.Camera;
import game.world.entities.*;
import org.newdawn.slick.Color;

import java.util.ArrayList;
import java.util.List;

public class World {

	private Player player;
	private List<Obstacle> plateau;
    private List<Coin> coinsList;
    private Door doorOut;

	private List<PotentialCollision> listPC;

	private Camera scroller;

	private static boolean inProgress;

    private Pause pauseDisplay;

	public World(int width, int height, Player player, Camera camera, Door door, List<Obstacle> plateau) {
	    pauseDisplay = new Pause(new Text("Pause", FontMap.map.get("Tron"), Color.red));

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

		for (Obstacle obstacle : plateau) {
			listPC.add(new PotentialCollision(player, obstacle));
		}

		for (Coin coin : coinsList)
		    listPC.add(new PotentialCollision(player, coin));

		listPC.add(new PotentialCollision(player, doorOut));
	}

	public void init() {

	}

	public void update() {
        String collisionSide;
        scroller.translateView();
		if(player.isAlive()){
			for(PotentialCollision pc : listPC){
                collisionSide = Physics.isStuck(pc);
			    if(pc.getSolid() instanceof Obstacle){
                    Physics.replaceAfterCollision(pc, collisionSide);
                }else if(pc.getSolid() instanceof Door && !collisionSide.equals("")){
                    levelEnd("outByDoor");
                }
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
