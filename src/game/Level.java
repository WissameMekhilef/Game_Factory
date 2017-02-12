package game;

import game.engine.Component;
import game.engine.Graphics;
import game.engine.Physics;
import game.entities.Obstacle;
import game.entities.Player;
import game.entities.PotentialCollision;
import game.entities.Tile;

import java.util.ArrayList;
import java.util.List;

public class Level {

	public int width, height;
	public Player player;
	public List<Tile> background;
	public List<Obstacle> plateau;

	private List<PotentialCollision> listPC;
	private Game inWhichGameAmI;

	private Scroller scroller;

	public Level(Game gameOwner, int width, int height) {
	    inWhichGameAmI = gameOwner;
		this.width = width;
		this.height = height;
		player = new Player(this, 50, 50, 3, 3, 10,  500, inWhichGameAmI.getTextures().skinMap.get("player1"));
		background = new ArrayList<>();
		plateau = new ArrayList<>();
		listPC = new ArrayList<>();

        LevelParameters.setBordBas(0);
        LevelParameters.setBordHaut(height);
        LevelParameters.setBordGauche(0);
        LevelParameters.setBordDroit(width);

        //scroller = new ForceScroller(2, 0);
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


		for (Obstacle obstacle : plateau) {
			listPC.add(new PotentialCollision(player, obstacle));
		}
	}

	public void init() {

	}

	public void update() {
        scroller.translateView();
		if(player.isAlive()){
			for(PotentialCollision pc : listPC)
				Physics.isStuck(pc);
			player.update();
		}else{
			System.out.println("DEAD");
			Component.running = false;
		}
	}

	public void render() {
        Graphics.scroll(LevelParameters.getxScroll(), LevelParameters.getyScroll());
		for(Tile tile : background)
			tile.render();
		for(Obstacle obstacle : plateau) {
			obstacle.render();
		}
		player.render();
	}
}
