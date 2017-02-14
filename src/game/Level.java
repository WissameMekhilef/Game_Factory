package game;

import game.engine.Component;
import game.engine.Graphics;
import game.engine.Physics;
import game.entities.Coin;
import game.entities.Obstacle;
import game.entities.Player;
import game.entities.PotentialCollision;

import java.util.ArrayList;
import java.util.List;

public class Level {

	private Player player;
	private List<Obstacle> plateau;
    private List<Coin> coinsList;

	private List<PotentialCollision> listPC;
	private Game inWhichGameAmI;

	private Scroller scroller;

	public Level(Game gameOwner, int width, int height) {
	    inWhichGameAmI = gameOwner;

		player = new Player(this, 50, 50, 3, 3, 10,  500, inWhichGameAmI.getTextures().skinMap.get("player1"));

		plateau = new ArrayList<>();
        coinsList = new ArrayList<>();
		listPC = new ArrayList<>();

        LevelParameters.setBordBas(0);
        LevelParameters.setBordHaut(height);
        LevelParameters.setBordGauche(0);
        LevelParameters.setBordDroit(width);

        //scroller = new ForceScroller(2, 2);
        scroller = new AttachedScroller(true, true, player);

		generate();
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
        coinsList.add(new Coin(100,100));


		for (Obstacle obstacle : plateau) {
			listPC.add(new PotentialCollision(player, obstacle));
		}

		for (Coin coin : coinsList)
		    listPC.add(new PotentialCollision(player, coin));
	}

	public void init() {

	}

	public void update() {
        String collisionSide;
        scroller.translateView();
		if(player.isAlive()){
			for(PotentialCollision pc : listPC){
                collisionSide = Physics.isStuck(pc);
			    if(pc.getSolid() instanceof Obstacle)
			        Physics.replaceAfterCollision(pc, collisionSide);
			    if(pc.getSolid() instanceof Coin)

            }


			player.update();
		}else{
			System.out.println("DEAD");
			Component.running = false;
		}
	}

	public void render() {
        Graphics.scroll(LevelParameters.getxScroll(), LevelParameters.getyScroll());
		for(Obstacle obstacle : plateau) {
			obstacle.render();
		}
		player.render();
	}
}
