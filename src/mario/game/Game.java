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

	private Context context;
    private Menu menu;
    private Sound soundContext;
	private World world;

	private enum Context {INGAME, INMENU, INPAUSE}

	public Game() {
	    context = null;

        menu = new Menu();
        soundContext = new Sound();

        switchTo(Context.INMENU);
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
    				switchTo(Context.INPAUSE);
    		break;

    	case INMENU:
    		if(Mouse.isButtonDown(0))
                menu.receiveClick(Mouse.getX(), Mouse.getY());
    		break;

    	case INPAUSE:
    		while (Keyboard.next())
    			if ((!Keyboard.getEventKeyState()) && (Keyboard.getEventKey() == Keyboard.KEY_P))
                    switchTo(Context.INGAME);
    		break;

    	}

    }

    public void switchTo(Context newContext){
	    if(context == null && newContext == Context.INMENU){
            context = Context.INMENU;
            menu.playBackgroundSound();
        }else if(context == Context.INGAME && newContext == Context.INMENU){
            context = Context.INMENU;
            menu.playBackgroundSound();
        }else if(context == Context.INGAME && newContext == Context.INPAUSE){
            Sound.pause();
            context = Context.INPAUSE;
        }else if(context == Context.INPAUSE && newContext == Context.INMENU){
            context = Context.INMENU;
            menu.playBackgroundSound();
        }else if(context == Context.INPAUSE && newContext == Context.INGAME){
            context = Context.INGAME;
            Sound.play();
        }else if(context == Context.INMENU && newContext == Context.INGAME){
            context = Context.INGAME;
            world.playBackgroundSound();
        }
    }

	public void update() {
        pollInput();

		switch (context) {

            case INGAME:
                if(world.isInProgress()) {
                    world.update();
                } else {
                    switchTo(Context.INMENU);
                }
                break;

            case INMENU:
                menu.update();
                if(menu.getLastButtonClicked() != null) {
                    String actionWanted = menu.getLastButtonClicked().getAction();
                    try {
                        world = WorldReader.worldFromJSON("worlds/"+actionWanted+".json");
                        menu.setLastButtonClicked(null);
                        switchTo(Context.INGAME);
                    } catch (CameraTypeException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case INPAUSE:
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
