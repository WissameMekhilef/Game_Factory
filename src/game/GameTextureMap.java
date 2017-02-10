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

    public Map<String, Texture[]> skinMap;

    public GameTextureMap() throws IOException {
        textureMap = new HashMap<>();
        textureMap.put("brique", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/brique.png")));
        textureMap.put("herbe", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/herbe.png")));

        skinMap = new HashMap<>();
        skinMap.put("player1", new Texture[]{TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/player.png")) , TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/player_b.png"))});
        skinMap.put("flappy", new Texture[]{TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/flappy.png")) , TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/flappy_b.png"))});
    }
}
