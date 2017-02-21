package mario.game.graphicItems;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;

/**
 * Created by wissamemekhilef on 20/02/2017.
 */
public class Text {
    private String textToDisplay;
    private Font font;
    private Color color;

    public Text(String textToDisplay, Font font, Color color){
        this.textToDisplay = textToDisplay;
        this.font = font;
        this.color = color;

    }

    public String getTextToDisplay() {
        return textToDisplay;
    }

    public Font getFont() {
        return font;
    }

    public Color getColor() {
        return color;
    }
}
