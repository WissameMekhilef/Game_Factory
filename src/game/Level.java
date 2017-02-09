package game;

import game.engine.Component;
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

	public Level(Game gameOwner, int width, int height) {
	    inWhichGameAmI = gameOwner;
		this.width = width;
		this.height = height;
		player = new Player(inWhichGameAmI, 50, 70, 5, -7, 0, Component.height - 8);//On pourra lui passer direvtement une texture player il ira chercher sa jumelle tout seul avec une map
		background = new ArrayList<>();
		plateau = new ArrayList<>();
		listPC = new ArrayList<>();
		generate();
	}

    public Player getPlayer(){
	    return player;
    }

	public void generate() {
		//Genere les obstacles (plateau de jeu)
		plateau.add(new Obstacle(100, 100, 600, 200, inWhichGameAmI.getTextureMap().textureMap.get("brique")));
		//Simulation sol
		plateau.add(new Obstacle(10000, 20, 10, 20, inWhichGameAmI.getTextureMap().textureMap.get("herbe")));
		//plateau.add(new Obstacle(100, 500, 160));

		for (Obstacle obstacle : plateau) {
			listPC.add(new PotentialCollision(player, obstacle));
		}
	}

	public void init() {

	}

	public void update() {
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
		for(Tile tile : background)
			tile.render();
		for(Obstacle obstacle : plateau) {
			obstacle.render();
		}
		player.render();
	}

}
