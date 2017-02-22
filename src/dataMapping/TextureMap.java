package dataMapping;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wissamemekhilef on 09/02/2017.
 */
public class TextureMap {

    public static Map<String , Texture> textureMap = new HashMap<>();
    public static Map<String, Texture[]> skinMap = new HashMap<>();

    static {
    	try {
			textureMap.put("brique", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/textures/brique.png")));
			textureMap.put("herbe", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/textures/herbe.png")));
	        //A changer
	        textureMap.put("pause", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/textures/pause.png")));
	        //Doors
	        textureMap.put("door_1", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/textures/door.png")));
	        //Coins
	        textureMap.put("coin_1", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/textures/pause.png")));
	        skinMap.put("player1", new Texture[]{TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/textures/player.png")) , TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/textures/player_b.png"))});
	        skinMap.put("flappy", new Texture[]{TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/textures/flappy.png")) , TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/textures/flappy_b.png"))});
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
