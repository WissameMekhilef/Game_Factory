package mario.game;

<<<<<<< HEAD
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.newdawn.slick.opengl.Texture;

import mario.game.world.World;
import mario.game.world.entities.Door;
import mario.game.world.entities.Obstacle;
import mario.game.world.entities.Player;
=======
import mario.game.world.World;
>>>>>>> refs/remotes/origin/Wissame

public class WorldReader {

<<<<<<< HEAD
	public static Player playerFromJson(String path) throws JSONException, IOException {

		JSONObject obj = new JSONObject(new String(Files.readAllBytes(Paths.get(path))));

		int sizeX = obj.getInt("sizeX");
		int sizeY = obj.getInt("sizeY");
		int v0 = obj.getInt("v0");
		int v1 = obj.getInt("v1");
		int x = obj.getInt("x");
		int y = obj.getInt("y");
		Texture[] skin = GameTextureMap.skinMap.get(obj.getString("skin"));

		return new Player(sizeX, sizeY, v0, v1, x, y, skin);

	}

	public static World worldFromJson(String path) throws JSONException, IOException {

		JSONObject obj = new JSONObject(new String(Files.readAllBytes(Paths.get(path))));

		int width = obj.getInt("width");
		int height = obj.getInt("height");
		Player player = playerFromJson(obj.getString("player"));

		JSONObject obj2 = obj.getJSONObject("door");

		int sizeX = obj2.getInt("sizeX");
		int sizeY = obj2.getInt("sizeY");
		int x = obj2.getInt("x");
		int y = obj2.getInt("y");
		Texture texture = GameTextureMap.textureMap.get(obj2.getString("texture"));

		Door door = new Door(sizeX, sizeY, x, y, texture);

		JSONArray arr = obj.getJSONArray("plateau");

		ArrayList<Obstacle> plateau = new ArrayList<>();

		for (int i = 0; i < arr.length(); i++) {

			sizeX = arr.getJSONObject(i).getInt("sizeX");
			sizeY = arr.getJSONObject(i).getInt("sizeY");
			x = arr.getJSONObject(i).getInt("x");
			y = arr.getJSONObject(i).getInt("y");
			texture = GameTextureMap.textureMap.get(arr.getJSONObject(i).getString("texture"));

			plateau.add(new Obstacle(sizeX, sizeY, x, y, texture));

		}

		return new World(width, height, player, door, plateau);

=======
	public static World worldFromJson(String path) {
        return null;
>>>>>>> refs/remotes/origin/Wissame
	}

}
