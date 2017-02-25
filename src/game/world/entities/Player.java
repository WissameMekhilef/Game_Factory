package game.world.entities;

import engine.Graphics;
import engine.Launcher;
import engine.Physics;
import game.world.WorldParameters;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;

import static engine.Launcher.poolThread;

public class Player extends Movable {
    //BEGIN Scroll
    public int prevX;
    public int prevY;

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


    private Collection<Callable<Integer>> updatePar;

	public Player(int sizeX, int sizeY, int v0, int v1, int x, int y, Texture[] skin) {
		super(sizeX, sizeY, v0, v1, x, y, skin[0]);

        forward = skin[0];
        backward = skin[1];

        prevX = x;
        prevY = y;

        updatePar = new ArrayList<>();
        updatePar.add(Physics.gravite(this));
        updatePar.add(Physics.freinage(this));
	}

	private void scrollReplace(){
		//Left replace
		if((coordonnee[0] + WorldParameters.getxScroll()) < 0) {
			coordonnee[0] = -WorldParameters.getxScroll();
			coordonneePrev[0] = coordonnee[0];
			isBlockedByLeftScreen = true;
		}else{
			isBlockedByLeftScreen = false;
		}

		//Right replace
		if((coordonnee[0] + sizeX + WorldParameters.getxScroll()) > Launcher.width){
			coordonnee[0] = Launcher.width - (sizeX + WorldParameters.getxScroll());
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
            if(timer - before > WorldParameters.getJumpTime())
                jumped = false;
        }

        prevX = coordonneePrev[0];
	    prevY = coordonneePrev[1];

        try {
            poolThread.invokeAll(updatePar);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scrollReplace();

		isAlive = checkAlive();

		//On repasse tout a false pour recommencer au tour d'après avec les conditions initiale
		setBlockedByBottom(false);
		setBlockedByLeft(false);
		setBlockedByRight(false);
		setBlockedByTop(false);
	}

	public void jumpWanted(){
	    if(!jumped && coordonnee[1] < Launcher.height){
            vitessePrev[1] += WorldParameters.getGainVitesseY();
            jumped = true;
            before = System.currentTimeMillis();
        }
    }

    public void leftWanted(){
	    if(coordonnee[0] + WorldParameters.getxScroll() > 0  && vitessePrev[0] + WorldParameters.getMAXSPEED() > 0)
	        if(vitessePrev[0] > 0)
	            vitessePrev[0] = 0;
            else
	            vitessePrev[0] -= WorldParameters.getGainVitesseX();
    }

    public void rightWanted(){
        if((coordonnee[0] + sizeX + WorldParameters.getxScroll() - Launcher.width <= 0) && vitessePrev[0] < WorldParameters.getMAXSPEED())
            if(vitessePrev[0] < 0)
                vitessePrev[0] = 0;
            else
                vitessePrev[0] += WorldParameters.getGainVitesseX();
    }

    public void setBelowTheSurface(boolean situation){
        isBelowTheSurface = situation;
    }


    public void render(){
        Graphics.renderQuad(coordonnee[0], coordonnee[1], sizeX, sizeY, texture, false);
    }

}
