package game;

import game.engine.Physics;
import game.entities.Character;
import game.entities.Obstacle;
import game.entities.Player;
import game.entities.Tile;
import game.entities.Tile.Tiles;

import java.util.ArrayList;
import java.util.List;

public class Level {

	public int width, height;
	public Character character;
	public Player player;
	public List<Tile> background;
	public List<Obstacle> plateau;

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		character = new Character(0, 50);
		player = new Player(50, 5, -7, 0, Component.height - 8);
		background = new ArrayList<>();
		plateau = new ArrayList<>();
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
		plateau.add(new Obstacle(30, 10, 2, 1000, 40, true));
		plateau.add(new Obstacle(250, 10, 2, 500, 350, true));
	}

	public void init() {

	}

	public void update() {
		System.out.println("above : "+player.isBlockedByBottom()+" , below : "+player.isBlockedByTop()+" , right : "+player.isBlockedByLeft()+" , left : "+player.isBlockedByRight());
		for(Obstacle obstacle : plateau)
			Physics.isStuck(player, obstacle);
		player.update();
		for(Obstacle obstacle : plateau)
			obstacle.update();
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
