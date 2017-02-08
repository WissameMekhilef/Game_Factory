package game.entities;

import game.Component;
import game.Game;
import game.engine.Physics;

public class Player extends Movable {

	private boolean isBlockedByLeftScreen = false;
	private boolean isBlockedByRightScreen = false;

	private boolean isAlive = true;

	private int MAXSPEED = 7;

	//GESTION SAUT
    private boolean jumped = false;
    private long before;
    private long timer = System.currentTimeMillis();
    private double jumpTime = 1000;

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
	    if(jumped){
            timer = System.currentTimeMillis();
            if(timer - before > jumpTime)
                jumped = false;
        }

		Physics.gravite(this);
		Physics.freinage(this);

		scrollReplace();

		isAlive = checkAlive();


		//On repasse tout a false pour recommencer au tour d'après avec les conditions initiale
		setBlockedByBottom(false);
		setBlockedByLeft(false);
		setBlockedByRight(false);
		setBlockedByTop(false);
	}

	public void jumpWanted(){
	    if(!jumped && coordonnee[1] < Component.height){
            vitessePrev[1] -= 20;
            jumped = true;
            before = System.currentTimeMillis();
        }
    }

    public void leftWanted(){
	    if(coordonnee[0] + Game.xScroll > 0  && vitessePrev[0] + MAXSPEED > 0)
	        if(vitessePrev[0] > 0)
	            vitessePrev[0] = 0;
            else
	            vitessePrev[0] -= 2;
    }

    public void rightWanted(){
        if((coordonnee[0] + size + Game.xScroll - Component.width <= 0) && vitessePrev[0] < MAXSPEED)
            if(vitessePrev[0] < 0)
                vitessePrev[0] = 0;
            else
                vitessePrev[0] += 2;
    }

}
