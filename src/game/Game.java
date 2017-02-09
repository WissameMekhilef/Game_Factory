package game;

import game.engine.Graphics;
import game.engine.Sound;
import game.entities.Tile;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Game{

	public static float xScroll, yScroll;

	public Level level;

	public int bound;

	private boolean paused = false;
    private Context context;;

    private Sound backgroundMusic;

	public Game() {
		xScroll = 0;
		yScroll = 0;
		level = new Level(200, Component.height / Tile.SIZE);

		bound = level.width * Tile.SIZE;

        context = Context.INGAME;

        backgroundMusic = new Sound("sounds/CantLetGo.ogg");
        //backgroundMusic = new Sound("sounds/BaseAfterBase.ogg");

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
            }
        }
    }

	public void init() {
		level.init();
        backgroundMusic.play();
	}

	public void translateView(float x, float y) {
		if(-xScroll >= bound - Component.width)
			return;
		xScroll += x;
		yScroll += y;
	}

	public void update() {
        pollInput();
		if(!paused){
			translateView(-1, 0);
			level.update();
		}
	}

	public void render() {
        Graphics.scroll(xScroll, yScroll);
		level.render();
	}

	public void drawPause(){

    }

    private enum Context {INGAME, INMENU, INPAUSE}
}
