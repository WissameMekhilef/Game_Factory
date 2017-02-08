package game;

import game.entities.Tile;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class Game{

	public static float xScroll, yScroll;

	public Level level;

	public int bound;

	private boolean paused = false;

	public Game() {
		xScroll = 0;
		yScroll = 0;
		level = new Level(200, Component.height / Tile.SIZE);

		bound = level.width * Tile.SIZE;
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
		if (Keyboard.isKeyDown(Keyboard.KEY_P)){
			System.out.println("PAUSE WANTED");
			paused = (paused)?false:true;
		}
		if(!paused){
			translateView(-1, 0);
			level.update();
		}
	}

	public void render() {
		GL11.glTranslatef(xScroll, yScroll, 0);
		level.render();
	}
}
