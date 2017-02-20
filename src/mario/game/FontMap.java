package mario.game;

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
            map.put("Mario", new TrueTypeFont(createFont(TRUETYPE_FONT, ResourceLoader.getResourceAsStream("fonts/chlorinap/CHLORINP.TTF")).deriveFont(24f), false));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
