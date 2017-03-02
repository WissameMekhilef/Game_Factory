package mario.game;

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

    public static Map<String , Texture> textureMap = new HashMap<>();
    public static Map<String , Texture> backgroundMap = new HashMap<>();
    public static Map<String, Texture[]> skinMap = new HashMap<>();

    static {
    	try {
    		//////BACKGROUND
    		backgroundMap.put("bg_bleu_blanc_lum",  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/background/background_blue_black_light.png")));
    		backgroundMap.put("bg_arc_ciel_fum",  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/background/background_rainbow_smoke.png")));

    		//////TEXTURES
			textureMap.put("nuage_1", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/clouds/nuage_1.png")));
			textureMap.put("nuage_2", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/clouds/nuage_2.png")));
			textureMap.put("nuage_3", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/clouds/nuage_3.png")));
			textureMap.put("nuage_4", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/clouds/nuage_4.png")));
			//textures
    		textureMap.put("brique_1", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/textures/brique.png")));
    		textureMap.put("brique_2", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/textures/brique_1.png")));
    		textureMap.put("bois_1", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/textures/wood_1.png")));
    		textureMap.put("bois_2", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/textures/wood_2.png")));
    		textureMap.put("bois_3", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/textures/wood_3.png")));
    		textureMap.put("herbe", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/textures/brique.png")));
	        //A changer
	        textureMap.put("pause_1", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/menu/pause.png")));
	        //Doors
	        textureMap.put("porte_simple", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/doors/door.png")));
	        textureMap.put("porte_b_n", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/doors/door_black_white.png")));
	        textureMap.put("porte_metal", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/doors/door_metal.png")));
	        textureMap.put("porte_bois_1", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/doors/door_wood_1.png")));
	        textureMap.put("porte_bois_2", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/doors/door_wood_2.png")));
	        //Coins
	        //textureMap.put("coin_1", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/pause.png")));
	        //items
	        textureMap.put("coin_1", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/items/coin_1.png")));
	        textureMap.put("coin_2", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/items/coin_2.png")));
	        /////////PLAYERS
	        //mario
	        skinMap.put("player1", new Texture[]{TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/player/player.png")) , TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/player/player_b.png"))});
	        //flappy
	        skinMap.put("flappy", new Texture[]{TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/player/flappy.png")) , TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("textures/player/flappy_B.png"))});
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
