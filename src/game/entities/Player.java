package game.entities;

import game.Game;
import game.GameParameters;
import game.engine.Component;
import game.engine.Physics;
import org.newdawn.slick.opengl.Texture;

public class Player extends Movable {
    //BEGIN Scroll
    public int prevX;
    public int prevY;
    private Game inWichGameAmI;
    private Texture forward;
    private Texture backward;
    //BEGIN Alive variables
	private boolean isBlockedByLeftScreen = false;
	private boolean isBlockedByRightScreen = false;
    //END Alive variables
	private boolean isBelowTheSurface = false;
	private boolean isAlive = true;
    //END Scroll
	//GESTION SAUT
    private boolean jumped = false;
    private long before;
    private long timer = System.currentTimeMillis();

	public Player(Game gameParent, int sizeX, int sizeY, int v0, int v1, int x0, int y0, Texture[] skin) {
		super(sizeX, sizeY, v0, v1, x0, y0, skin[0]);
        inWichGameAmI = gameParent;
        forward = skin[0];
        backward = skin[1];

        prevX = x0;
        prevY = y0;
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
		if((coordonnee[0] + sizeX + Game.xScroll) > Component.width){
			coordonnee[0] = Component.width - (sizeX + (int) Game.xScroll);
			coordonneePrev[0] = coordonnee[0];
			isBlockedByRightScreen = true;
		}else{
			isBlockedByRightScreen = false;
		}
	}

	private boolean checkAlive(){
		if(isBlockedByLeftScreen && isBlockedByRight)
			return false;
		if(isBelowTheSurface)
		    return false;
		return true;
	}

	public boolean isAlive(){
		return isAlive;
	}

	public void update() {
	    //Texture thing normal or reverse
        if(vitessePrev[0] < 0){
            texture = backward;
        }else{
            texture = forward;
        }

	    //Jump thing
	    if(jumped){
            timer = System.currentTimeMillis();
            if(timer - before > GameParameters.getJumpTime())
                jumped = false;
        }

        prevX = coordonneePrev[0];
	    prevY = coordonneePrev[1];
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
            vitessePrev[1] += GameParameters.getGainVitesseY();
            jumped = true;
            before = System.currentTimeMillis();
        }
    }

    public void leftWanted(){
	    if(coordonnee[0] + Game.xScroll > 0  && vitessePrev[0] + GameParameters.getMAXSPEED() > 0)
	        if(vitessePrev[0] > 0)
	            vitessePrev[0] = 0;
            else
	            vitessePrev[0] -= GameParameters.getGainVitesseX();
    }

    public void rightWanted(){
        if((coordonnee[0] + sizeX + Game.xScroll - Component.width <= 0) && vitessePrev[0] < GameParameters.getMAXSPEED())
            if(vitessePrev[0] < 0)
                vitessePrev[0] = 0;
            else
                vitessePrev[0] += GameParameters.getGainVitesseX();
    }

    public void setBelowTheSurface(boolean situation){
        isBelowTheSurface = situation;
    }

}
