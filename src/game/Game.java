package game;

import game.engine.Component;
import game.engine.Graphics;
import game.engine.Sound;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.io.IOException;

public class Game{
	public Level level;

	private boolean paused = false;
    private Context context;

    private Sound soundContext;
    private float soundPosition;

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
        soundPosition = 0;
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
        soundContext.play(soundPosition);
	}

	public void update() {
        pollInput();
		if(!paused){
			level.update();
			if(!soundContext.isPlaying())
				soundContext.play(soundPosition);
		} else {
			if(soundContext.isPlaying())
				soundPosition = soundContext.stop();
		}
	}

	public void render() {
		level.render();
		if(paused) {
			int size = 200;
			int x = (Component.width - size) / 2 - LevelParameters.getxScroll();
			int y = (Component.height + size) / 2 + LevelParameters.getyScroll();
			Graphics.renderQuad(x, y, size, size, textures.textureMap.get("pause"));
		}
	}

	public void drawPause(){

    }

    public GameTextureMap getTextures() {
        return textures;
    }

    private enum Context {INGAME, INMENU, INPAUSE}
}