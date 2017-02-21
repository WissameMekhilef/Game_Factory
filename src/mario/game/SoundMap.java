package mario.game;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wissamemekhilef on 21/02/2017.
 */
public class SoundMap {

    public static Map<String , Audio> soundMap = new HashMap<>();

    static {
        try{
            soundMap.put("BaseAfterBase" ,AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("sounds/BaseAfterBase.ogg")));
            soundMap.put("CantLetGo" ,AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("sounds/CantLetGo.ogg")));
            soundMap.put("DryOut" ,AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("sounds/DryOut.ogg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
