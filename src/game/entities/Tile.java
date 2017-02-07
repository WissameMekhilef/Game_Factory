package game.entities;

import static org.lwjgl.opengl.GL11.*;



import game.Component;
import game.Game;
import game.engine.Graphics;
import game.engine.Texture;

/**
 * La class Tile permet d'implementer des éléments du background
 *
 * Aucune gestion des collisons ne sera effectué sur les Tiles, elle sera
 * effectué sur les Obstacles
 */
public class Tile {

	public static final int SIZE = 100;
	public static final int SIZE_T = 32;
	public Texture text;
	public float[] color;
	public int x, y;
	Tiles tile;

	public Tile(int x, int y, Tiles tile) {
		this.x = x;
		this.y = y;
		
		this.tile = tile;
		if (tile == Tiles.WHITE)
			color = new float[]{1.0f, 1.0f, 1.0f, 1};
		if (tile == Tiles.YELLOW)
			color = new float[]{1.0f, 1.0f, 0.5f, 1};
		if (tile==Tiles.BRIQUE) {
			color = new float[]{1.0f, 1.0f, 1.0f, 1};
			text = Texture.brique;
		}
	}
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		
	}
/*
	public void render() {
		float x0 = x + Game.xScroll / SIZE;
		float y0 = y + Game.yScroll / SIZE;
		float x1 = x + 1 + Game.xScroll / SIZE;
		float y1 = y + 1 + Game.yScroll / SIZE;
		if (x1 < 0 || y1 < 0 || x0 > (Component.width / SIZE + 1)
				|| y0 > (Component.height / SIZE + 1))
			return;
		Graphics.renderQuad(x * SIZE, y * SIZE, SIZE, SIZE, color);
	}
	*/
	public void render() {


		if (x + 1 + Game.xScroll / SIZE_T< 0 ||  y + 1 + Game.yScroll / SIZE_T< 0 || x + Game.xScroll / SIZE_T > Component.width / SIZE_T
				||  y + Game.yScroll / SIZE_T> Component.height / SIZE_T) {
			return;
		}
		text.lier();
		glBegin(GL_QUADS);
		Graphics.quadData(x * SIZE_T, y * SIZE_T, SIZE_T, SIZE_T, color);
		Graphics.quadData(x * SIZE_T + SIZE_T/2, y * SIZE_T, SIZE_T, SIZE_T, color);
		Graphics.quadData(x * SIZE_T + SIZE_T/2, y * SIZE_T + SIZE_T/2, SIZE_T, SIZE_T, color);
		Graphics.quadData(x * SIZE_T, y * SIZE_T + SIZE_T/2, SIZE_T, SIZE_T, color);
		glEnd();
		text.delier();
	}

	public enum Tiles {
		WHITE, YELLOW,BRIQUE
	}

}
