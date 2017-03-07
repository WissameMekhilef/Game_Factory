package game.graphicItems;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;

public class Text {

    private String textToDisplay;
    private Font font;
    private Color color;
    private int sizeX, sizeY;

    public Text(String textToDisplay, Font font, Color color) {
        this.textToDisplay = textToDisplay;
        this.font = font;
        this.color = color;
        this.sizeX = font.getWidth(textToDisplay);
        this.sizeY = font.getHeight(textToDisplay);
    }

    public int getSizeX() {
    	return sizeX;
    }

    public int getSizeY() {
    	return sizeY;
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
