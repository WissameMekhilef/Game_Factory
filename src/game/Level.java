package game;

import game.engine.Physics;
import game.entities.Character;
import game.entities.*;
import game.entities.Tile.Tiles;

import java.util.ArrayList;
import java.util.List;

public class Level {

	public int width, height;
	public Character character;
	public Player player;
	public List<Tile> background;
	public List<Obstacle> plateau;
	private List<PotentialCollision> listPC;

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		character = new Character(0, 50);
		player = new Player(50, 5, -7, 0, Component.height - 8);
		background = new ArrayList<>();
		plateau = new ArrayList<>();
		listPC = new ArrayList<>();
		generate();
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
		plateau.add(new Obstacle(100, 10, 2, 600, 200, true));
		plateau.add(new Obstacle(100, 10, 2, 500, 700, true));
		plateau.add(new Obstacle(100, 10, 2, 500, 160, true));

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
