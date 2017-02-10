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
    public int bordBas = 0;
    public int bordHaut = height;
    public int bordGauche = 0;
    public int bordDroit = width;
	private List<PotentialCollision> listPC;
	private Game inWhichGameAmI;

	public Level(Game gameOwner, int width, int height) {
	    inWhichGameAmI = gameOwner;
		this.width = width;
		this.height = height;
		player = new Player(inWhichGameAmI, 50, 50, 3, 3, 10,  500, inWhichGameAmI.getTextures().skinMap.get("player1"));
		background = new ArrayList<>();
		plateau = new ArrayList<>();
		listPC = new ArrayList<>();

        bordBas = 0;
        bordHaut = height;
        bordGauche = 0;
        bordDroit = width;

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
