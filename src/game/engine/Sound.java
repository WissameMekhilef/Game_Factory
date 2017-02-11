package game.engine;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;

/**
 * Created by wissamemekhilef on 09/02/2017.
 */
public class Sound {
    private Audio backgroundSound;

    public Sound(){

    }

    public void play(){
        backgroundSound.playAsMusic(1.0f, 1.0f, false);
    }

    public Audio getBackgroundSound() {
        return backgroundSound;
    }

    public void setBackgroundSound(String pathBackgroundSound) {
        try {
            backgroundSound = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream(pathBackgroundSound));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
