package game.entities;

import game.Component;
import game.Game;
import game.engine.Graphics;
import org.lwjgl.input.Keyboard;

public class Character {

	public boolean isJumping;
	public float[] color;
	public int size, speed, x, y;

	public Character(int x, int y) {
		isJumping = false;
		color = new float[]{0.5f, 0.5f, 0.5f, 1};
		size = 25;
		speed = 5;
		this.x = x;
		this.y = y;
	}

	public void jump() {
		if((Keyboard.isKeyDown(Keyboard.KEY_UP)) && (y == size))
			isJumping = true;
		if(isJumping) {
			if(y < (size * 12))
				y+= speed * 2;
			else
				isJumping = false;
		} else {
			y-= speed * 2;
		}
	}

	public void render() {
		Graphics.renderQuad(x, y, size, size, color);
	}

	public void replace() {
		if(y > Component.height)
			y = Component.height;
		if(y < size)
			y = size;
		if(x < -Game.xScroll)
			x = (int) -Game.xScroll;
		if(x > (Component.width - (size + Game.xScroll)))
			x = Component.width - (size + (int) Game.xScroll);
	}

	public void update() {
		jump();
		move();
		replace();
	}

	public void move() {
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			x-= speed;
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			x+= speed;
	}

}
