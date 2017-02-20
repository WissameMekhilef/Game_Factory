package mario.game;

import mario.engine.Graphics;
import mario.engine.Launcher;
import mario.engine.Sound;
import mario.exceptions.CameraTypeException;
import mario.game.world.World;
import mario.game.world.WorldParameters;

import java.io.IOException;

import org.json.JSONException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Game {

	private float soundPosition;
	private Context context;
    private Menu menu;
    private Sound soundContext;
	private World world;

	private enum Context {INGAME, INMENU, INPAUSE}

	public Game() {
		context = Context.INMENU;
        menu = new Menu();
        soundContext = new Sound();
        soundPosition = 0;
    }

    public void pollInput() {

    	switch (context) {

    	case INGAME:
    		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
            	world.getPlayer().jumpWanted();
            if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
            	world.getPlayer().leftWanted();
            if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
            	world.getPlayer().rightWanted();
    		while (Keyboard.next())
    			if ((!Keyboard.getEventKeyState()) && (Keyboard.getEventKey() == Keyboard.KEY_P))
    				context = Context.INPAUSE;
    		break;

    	case INMENU:
    		if(Mouse.isButtonDown(0))
                menu.receiveClick(Mouse.getX(), Mouse.getY());
    		break;

    	case INPAUSE:
    		while (Keyboard.next())
    			if ((!Keyboard.getEventKeyState()) && (Keyboard.getEventKey() == Keyboard.KEY_P))
    				context = Context.INGAME;
    		break;

    	}

    }

	public void update() {
        pollInput();

		switch (context) {

		case INGAME:
			if(world.isInProgress()) {
		    	world.update();
                if(!soundContext.isPlaying())
                    soundContext.play(soundPosition);
            } else {
                context = Context.INMENU;
            }
			break;

		case INMENU:
			if(menu.getLastButtonClicked() != null) {
                String actionWanted = menu.getLastButtonClicked().getAction();
                switch (actionWanted) {
                    case "start":
                    	try {
                			world = WorldReader.worldFromJSON("worlds/world_test.json");
                    	} catch (CameraTypeException e) {
                			e.printStackTrace();
                    	} catch (IOException e) {
                			e.printStackTrace();
                		} catch (JSONException e) {
                			e.printStackTrace();
                		}
                        soundContext.setBackgroundSound(WorldParameters.getPathToBackgroundMusic());
                        menu.setLastButtonClicked(null);
                        context = Context.INGAME;
                        break;
                }
            }
			break;

		case INPAUSE:
			if(soundContext.isPlaying())
				soundPosition = soundContext.stop();
			break;

		}

	}

	public void render() {

		switch (context) {

		case INGAME:
			world.render();
			break;

		case INMENU:
			menu.render();
			break;

		case INPAUSE:
			//Il faut créer un objet pause.
			int size = 200;
			int x = (Launcher.width - size) / 2 - WorldParameters.getxScroll();
			int y = (Launcher.height + size) / 2 + WorldParameters.getyScroll();
			Graphics.renderQuad(x, y, size, size, GameTextureMap.textureMap.get("pause"));

		}

	}

}
