package game.entities;

import game.Game;
import game.GameParameters;
import game.engine.Component;
import game.engine.Graphics;
import game.engine.Physics;
import org.newdawn.slick.opengl.Texture;

public class Player extends Movable {
    private Game inWichGameAmI;

    private Texture forward;
    private Texture backward;

	private boolean isBlockedByLeftScreen = false;
	private boolean isBlockedByRightScreen = false;

	private boolean isAlive = true;

	//GESTION SAUT
    private boolean jumped = false;
    private long before;
    private long timer = System.currentTimeMillis();

	public Player(Game gameParent, int size, int v0, int v1, int x0, int y0) {
		super(size, v0, v1, x0, y0, gameParent.getTextureMap().textureMap.get(GameParameters.getForwardTexture()));
        inWichGameAmI = gameParent;
        forward = texture;
        backward = inWichGameAmI.getTextureMap().textureMap.get(GameParameters.getBackwardTexture());

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
            vitessePrev[1] -= GameParameters.getGainVitesseY();
            jumped = true;
            before = System.currentTimeMillis();
        }
    }

    public void leftWanted(){
	    if(coordonnee[0] + Game.xScroll > 0  && vitessePrev[0] + GameParameters.getMAXSPEED() > 0)
	        if(vitessePrev[0] > 0)
	            vitessePrev[0] = 0;
            else
	            vitessePrev[0] -= GameParameters.getGainVitesseX() * (-1);
    }

    public void rightWanted(){
        if((coordonnee[0] + size + Game.xScroll - Component.width <= 0) && vitessePrev[0] < GameParameters.getMAXSPEED())
            if(vitessePrev[0] < 0)
                vitessePrev[0] = 0;
            else
                vitessePrev[0] += GameParameters.getGainVitesseX();
    }

    public void render(){
        Graphics.renderQuad(coordonnee[0], coordonnee[1], size, size, texture);
    }

}
