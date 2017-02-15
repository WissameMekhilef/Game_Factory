package mario.game.world;

import mario.engine.Graphics;
import mario.engine.Physics;
import mario.game.world.camera.AttachedScroller;
import mario.game.world.camera.Camera;
import mario.game.world.entities.*;

import java.util.ArrayList;
import java.util.List;

public class World {

	private Player player;
	private List<Obstacle> plateau;
    private List<Coin> coinsList;
    private Door doorOut;

	private List<PotentialCollision> listPC;

	private Camera scroller;

	private boolean inProgress;

	public World(int width, int height, Player player, Door door, List<Obstacle> plateau) {

		WorldParameters.setBordBas(0);
        WorldParameters.setBordHaut(height);
        WorldParameters.setBordGauche(0);
        WorldParameters.setBordDroit(width);

		this.player = player;
		this.doorOut = door;
		this.plateau = plateau;

        coinsList = new ArrayList<>();
		listPC = new ArrayList<>();

        WorldParameters.setxScroll(-WorldParameters.getxScroll());
        WorldParameters.setyScroll(-WorldParameters.getyScroll());

        //scroller = new ForceScroller(2, 2);
        scroller = new AttachedScroller(true, true, player);

		generate();

		inProgress = true;
	}

    public Player getPlayer(){
	    return player;
    }

	public void generate() {

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
                }else if(pc.getSolid() instanceof Door && collisionSide != ""){
                    levelEnd("outByDoor");
                }else if(pc.getSolid() instanceof Coin){

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

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }
}
