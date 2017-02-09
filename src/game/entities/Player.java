package game.entities;

import game.Component;
import game.Game;
import game.engine.Graphics;
import game.engine.Physics;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;

public class Player extends Movable {

    private Texture texture;
    private Texture forward;
    private Texture backward;

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
        try {
            // load texture from PNG file
            forward = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/player.png"));
            backward = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/player_b.png"));
            texture = forward;

            System.out.println("Texture loaded: "+texture);
            System.out.println(">> Image width: "+texture.getImageWidth());
            System.out.println(">> Image height: "+texture.getImageHeight());
            System.out.println(">> Texture width: "+texture.getTextureWidth());
            System.out.println(">> Texture height: "+texture.getTextureHeight());
            System.out.println(">> Texture ID: "+texture.getTextureID());
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void render(){
        Graphics.renderQuad(coordonnee[0], coordonnee[1], size, size, texture);
    }

}
