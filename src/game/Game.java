package game;

import game.engine.Component;
import game.engine.Sound;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.io.IOException;

public class Game{
	public Level level;

	private boolean paused = false;
    private Context context;

    private Sound soundContext;

    private GameTextureMap textures;

	public Game() {
        try {
            textures = new GameTextureMap();
        } catch (IOException e) {
            e.printStackTrace();
        }

		level = new Level(this,1500, 1000);

        context = Context.INGAME;

        soundContext = new Sound();

        soundContext.setBackgroundSound(LevelParameters.getPathToBackgroundMusic());
    }

    public void pollInput() {

        if (Mouse.isButtonDown(0)) {
            int x = Mouse.getX();
            int y = Mouse.getY();

            //System.out.println("MOUSE DOWN @ X: " + x + " Y: " + y);
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            level.getPlayer().jumpWanted();
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            level.getPlayer().leftWanted();
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            level.getPlayer().rightWanted();
        }

        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {

            } else {
                if (Keyboard.getEventKey() == Keyboard.KEY_P) {
                    if(paused){
                        paused = false;
                        context = Context.INGAME;
                    }else{
                        paused = true;
                        context = Context.INPAUSE;
                    }
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_Q) {
                    System.out.println("DEBUG");
                    Component.stop();
                }
            }
        }
    }

	public void init() {
		level.init();
        soundContext.play();
	}

    /**
     * Be aware that xScroll is a gap between the displayed screen and the level origin : Counted with a -
     * yScroll is positive WHAT A MESS !!!
     */
	/*public void translateViewX() {
	    boolean aDroite = level.player.getCoordonnee()[0] + xScroll > Component.width / 2;
        boolean aGauche = ! aDroite;

	    boolean bordDroitAfficher = 0 >= level.bordDroit - Component.width + xScroll ;
        boolean bordGaucheAfficher = xScroll >= level.bordGauche;

        if(aDroite){
            if(bordDroitAfficher) {
                return;
            }
        }else if(aGauche){
            if(bordGaucheAfficher){
                xScroll = 0;
                return;
            }
        }

        xScroll -= level.player.getCoordonnee()[0] - level.player.prevX;
	}

	public void translateViewY(){
        boolean moitieHaute = level.player.getCoordonnee()[1] - yScroll > Component.height / 2;
        boolean moitieBasse = !moitieHaute;

        boolean bordHautAfficher = yScroll >= level.bordHaut - Component.height ;
        boolean bordBasAfficher = yScroll <= level.bordBas;

        if(moitieBasse){
            if(bordBasAfficher) {
                yScroll = 0;
                return;
            }
        }else if(moitieHaute){
            if(bordHautAfficher){
                return;
            }
        }

        yScroll += level.player.getCoordonnee()[1] - level.player.prevY;
    }
    public void translateView(){
	    translateViewX();
	    translateViewY();
    }*/

	public void update() {
        pollInput();
		if(!paused){
			level.update();
		}
	}

	public void render() {
		level.render();
	}

	public void drawPause(){

    }

    public GameTextureMap getTextures() {
        return textures;
    }

    private enum Context {INGAME, INMENU, INPAUSE}
}
