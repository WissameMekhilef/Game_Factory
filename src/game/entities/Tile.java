package game.entities;

import game.Game;
import game.engine.Component;
import game.engine.Graphics;

/**
 * La class Tile permet d'implementer des éléments du background
 *
 * Aucune gestion des collisons ne sera effectué sur les Tiles,
 * elle sera effectué sur les Obstacles
 */
public class Tile {

	public static final int SIZE = 100;

	public float[] color;
	public int x, y;
	Tiles tile;

	public Tile(int x, int y, Tiles tile) {
		this.x = x;
		this.y = y;
		this.tile = tile;
		if(tile == Tiles.WHITE)
			color = new float[]{1.0f, 1.0f, 1.0f, 1};
		if(tile == Tiles.YELLOW)
			color = new float[]{1.0f, 1.0f, 0.5f, 1};
	}

	public void render() {
		float x0 = x + Game.xScroll / SIZE;
		float y0 = y + Game.yScroll / SIZE;
		float x1 = x + 1 + Game.xScroll / SIZE;
		float y1 = y + 1 + Game.yScroll / SIZE;
		if(x1 < 0 || y1 < 0 || x0 > (Component.width / SIZE + 1) || y0 > (Component.height / SIZE + 1))
			return;
		Graphics.renderQuad(x * SIZE, y * SIZE, SIZE, SIZE, null);
	}

	public enum Tiles {
		WHITE, YELLOW
	}

}
