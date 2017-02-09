package game;

import game.engine.Component;
import game.engine.Graphics;
import game.engine.Sound;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.io.IOException;

public class Game{

	public static float xScroll, yScroll;

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

		xScroll = 0;
		yScroll = 0;
		level = new Level(this,5000, 5000);

        context = Context.INGAME;

        soundContext = new Sound();

        soundContext.setBackgroundSound(GameParameters.getPathToBackgroundMusic());
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

	public void translateView() {
		if(level.player.getCoordonnee()[0] > Component.width / 2)
            xScroll -= level.player.getCoordonnee()[0] - level.player.prevX;
        if(level.player.getCoordonnee()[1] > Component.width / 2)
            yScroll += level.player.getCoordonnee()[1] - level.player.prevY;
	}

	public void update() {
        pollInput();
		if(!paused){
			translateView();
			level.update();
		}
	}

	public void render() {
        Graphics.scroll(xScroll, yScroll);
		level.render();
	}

	public void drawPause(){

    }

    public GameTextureMap getTextures() {
        return textures;
    }

    private enum Context {INGAME, INMENU, INPAUSE}
}
