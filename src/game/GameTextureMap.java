package game;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wissamemekhilef on 09/02/2017.
 */
public class GameTextureMap {
    public Map<String , Texture> textureMap;

    public GameTextureMap() throws IOException {
        textureMap = new HashMap<>();
        textureMap.put("brique", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/brique.png")));
        textureMap.put("herbe", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/herbe.png")));
        textureMap.put("player_1_f", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/player.png")));
        textureMap.put("player_1_b", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/player_b.png")));
    }
}
