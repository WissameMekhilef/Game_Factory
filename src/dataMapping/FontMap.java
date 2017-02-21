package dataMapping;

import org.newdawn.slick.Font;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

import static java.awt.Font.TRUETYPE_FONT;
import static java.awt.Font.createFont;

/**
 * Created by wissamemekhilef on 20/02/2017.
 */
public class FontMap {
    public static Map<String , Font> map = new HashMap<>();

    static {
        try {
            map.put("Mario_1", new TrueTypeFont(createFont(TRUETYPE_FONT, ResourceLoader.getResourceAsStream("fonts/mario/new_super_mario_1.ttf")).deriveFont(46f), false));
            map.put("Mario_2", new TrueTypeFont(createFont(TRUETYPE_FONT, ResourceLoader.getResourceAsStream("fonts/mario/new_super_mario_2.ttf")).deriveFont(46f), false));
            map.put("Mario_3", new TrueTypeFont(createFont(TRUETYPE_FONT, ResourceLoader.getResourceAsStream("fonts/mario/new_super_mario_3.ttf")).deriveFont(46f), false));
            map.put("Flappy", new TrueTypeFont(createFont(TRUETYPE_FONT, ResourceLoader.getResourceAsStream("fonts/flappy_bird/flappy_bird.ttf")).deriveFont(46f), false));
            map.put("GameOver", new TrueTypeFont(createFont(TRUETYPE_FONT, ResourceLoader.getResourceAsStream("fonts/other/game_over.ttf")).deriveFont(46f), false));
            map.put("Sonic", new TrueTypeFont(createFont(TRUETYPE_FONT, ResourceLoader.getResourceAsStream("fonts/sonic_mega/sonic.ttf")).deriveFont(46f), false));
            map.put("Tron", new TrueTypeFont(createFont(TRUETYPE_FONT, ResourceLoader.getResourceAsStream("fonts/tron/tron.ttf")).deriveFont(46f), false));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
