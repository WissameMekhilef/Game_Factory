package game.entities;

import game.Component;
import game.Game;
import game.engine.Physics;
import org.lwjgl.input.Keyboard;

public class Player extends Movable {

	private boolean isBlockedByLeftScreen = false;
	private boolean isBlockedByRightScreen = false;

	private boolean isAlive = true;

	public Player(int size, int v0, int v1, int x0, int y0) {
		super(size, v0, v1, x0, y0);
	}

	private void scrollReplace(){
		//Left replace
		if((coordonnee[0] + Game.xScroll) < 0) {
			coordonnee[0] = (int) -Game.xScroll;
			coordonneePrev[0] = coordonnee[0];
			isBlockedByLeftScreen = true;
		}else{
			isBlockedByLeftScreen = false;
		}


		//Right replace
		if((coordonnee[0] + size + Game.xScroll) > Component.width){
			coordonnee[0] = Component.width - (size + (int) Game.xScroll);
			coordonneePrev[0] = coordonnee[0];
			isBlockedByRightScreen = true;
		}else{
			isBlockedByRightScreen = false;
		}
	}

	private boolean checkAlive(){
		if(isBlockedByLeftScreen && isBlockedByRight)
			return false;
		return true;
	}

	public boolean isAlive(){
		return isAlive;
	}

	public void update() {

		Physics.gravite(this);
		Physics.freinage(this);

		scrollReplace();

		isAlive = checkAlive();

		if((Keyboard.isKeyDown(Keyboard.KEY_UP)) && (coordonnee[1] < Component.height))
			vitessePrev[1] -= 2;

		if((Keyboard.isKeyDown(Keyboard.KEY_LEFT)) && ((coordonnee[0] + Game.xScroll > 0)))
			vitessePrev[0] -= 2;

		if((Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) && ((coordonnee[0] + size + Game.xScroll) < Component.width))
			vitessePrev[0] += 2;

		setBlockedByBottom(false);
		setBlockedByLeft(false);
		setBlockedByRight(false);
		setBlockedByTop(false);

	}

}
