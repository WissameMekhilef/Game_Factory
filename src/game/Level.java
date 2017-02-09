package game;

import game.engine.Component;
import game.engine.Physics;
import game.entities.Obstacle;
import game.entities.Player;
import game.entities.PotentialCollision;
import game.entities.Tile;
import game.entities.Tile.Tiles;

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
		player = new Player(inWhichGameAmI, 50, 5, -7, 0, Component.height - 8);
		background = new ArrayList<>();
		plateau = new ArrayList<>();
		listPC = new ArrayList<>();
		generate();
	}

    public Player getPlayer(){
	    return player;
    }

	public void generate() {
		//Genere background (damier)
		for(int x = 0; x < width; x++) {
			for(int y = 1; y <= height; y++) {
				if((x + y) % 2 == 0)
					background.add(new Tile(x, y, Tiles.YELLOW));
				else
					background.add(new Tile(x, y, Tiles.WHITE));
			}
		}

		//Genere les obstacles (plateau de jeu)
		plateau.add(new Obstacle(100, 600, 200, inWhichGameAmI.getTextureMap().textureMap.get("brique")));
		//plateau.add(new Obstacle(100, 500, 700));
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
