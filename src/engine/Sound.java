package engine;

import org.newdawn.slick.openal.Audio;

/**
 * Created by wissamemekhilef on 09/02/2017.
 */
public class Sound {
    private static Audio backgroundSound;

    private static float backgroundPosition = 0;

    public Sound(){

    }

    public static void play(){
    	backgroundSound.playAsMusic(1.0f, 1.0f, false);
    	backgroundSound.setPosition(backgroundPosition);
    }

    public static void play(Audio pBackgroundSound) {
        stop();
        backgroundSound = pBackgroundSound;
        play();
    }

    public static void stop() {
        if (backgroundSound != null) {
            backgroundSound.stop();
            backgroundPosition = 0;
        }
    }

    public static void pause(){
        backgroundPosition = backgroundSound.getPosition();
        backgroundSound.stop();
    }




}
