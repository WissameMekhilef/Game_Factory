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

    public Sound(String path){
        try {
            backgroundSound = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play(){
        backgroundSound.playAsMusic(1.0f, 1.0f, false);
    }
}
