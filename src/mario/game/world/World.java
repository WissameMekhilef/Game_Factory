package mario.game.world;

import mario.engine.Graphics;
import mario.engine.Physics;
import mario.game.Game;
import mario.game.world.camera.AttachedScroller;
import mario.game.world.camera.Camera;
import mario.game.world.entities.Coin;
import mario.game.world.entities.Door;
import mario.game.world.entities.Obstacle;
import mario.game.world.entities.Player;
import mario.game.world.entities.PotentialCollision;

import java.util.ArrayList;
import java.util.List;

public class World {

	private Player player;
	private List<Obstacle> plateau;
    private List<Coin> coinsList;
    private Door doorOut;

	private List<PotentialCollision> listPC;
	private Game inWhichGameAmI;

	private Camera scroller;

	private boolean inProgress;

	public World(Game gameOwner, int width, int height) {
	    inWhichGameAmI = gameOwner;

		player = new Player(this, 50, 50, 3, 3, 10,  500, inWhichGameAmI.getTextures().skinMap.get("player1"));

		plateau = new ArrayList<>();
        coinsList = new ArrayList<>();
		listPC = new ArrayList<>();

        WorldParameters.setBordBas(0);
        WorldParameters.setBordHaut(height);
        WorldParameters.setBordGauche(0);
        WorldParameters.setBordDroit(width);

        //scroller = new ForceScroller(2, 2);
        scroller = new AttachedScroller(true, true, player);

		generate();

		inProgress = true;
	}

    public Player getPlayer(){
	    return player;
    }

	public void generate() {
		//Genere les obstacles (plateau de jeu)
		plateau.add(new Obstacle(100, 200, 600, 200, inWhichGameAmI.getTextures().textureMap.get("brique")));
        plateau.add(new Obstacle(100, 100, 800, 200, inWhichGameAmI.getTextures().textureMap.get("brique")));
        plateau.add(new Obstacle(100, 200, 1000, 800, inWhichGameAmI.getTextures().textureMap.get("brique")));
        plateau.add(new Obstacle(100, 200, 1200, 1000, inWhichGameAmI.getTextures().textureMap.get("brique")));
        plateau.add(new Obstacle(100, 200, 1200, 200, inWhichGameAmI.getTextures().textureMap.get("brique")));

        //Simulation sol
		plateau.add(new Obstacle(1200, 50, 0, 10, inWhichGameAmI.getTextures().textureMap.get("herbe")));

		//Genere coins
        //coinsList.add(new Coin(100,100, 100, 100, inWhichGameAmI.getTextures().textureMap.get("coin_1")));

        //Place porte de sortie
        doorOut = new Door(200, 200, 600, 400, inWhichGameAmI.getTextures().textureMap.get("door_1"));


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
