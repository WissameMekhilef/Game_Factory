package game;

import game.entities.Tile;
import org.lwjgl.opengl.GL11;

public class Game {

	public static float xScroll, yScroll;

	public Level level;

	public int bound;

	public Game() {
		xScroll = 0;
		yScroll = 0;
		level = new Level(2000, Component.height / Tile.SIZE);

		bound = 15 * Tile.SIZE;
	}

	public void init() {
		level.init();
	}

	public void translateView(float x, float y) {
		if(-xScroll >= bound - Component.width)
			return;
		xScroll += x;
		yScroll += y;
	}

	public void update() {
		//translateView(-1, 0);
		level.update();
	}

	public void render() {
		GL11.glTranslatef(xScroll, yScroll, 0);
		level.render();
	}

}
