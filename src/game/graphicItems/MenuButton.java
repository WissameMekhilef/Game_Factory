package game.graphicItems;

import engine.Graphics;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by wissamemekhilef on 14/02/2017.
 */
public class MenuButton implements Comparable{

    protected Texture texture;
    private int[] coordonnee;
    protected int sizeX;
    protected int sizeY;

    private Text content;

    private Runnable action;

    public MenuButton(int sizeX, int sizeY, Texture texture, Text text, Runnable action){
        this.coordonnee = new int[2];
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        this.texture = texture;
        this.content = text;
        this.action = action;
    }

    public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setX(int x){
        coordonnee[0] = x;

    }

    public void setY(int y){
        coordonnee[1] = y;
    }

    public Runnable getAction() {
        return action;
    }

    public void setAction(Runnable action) {
        this.action = action;
    }

    public boolean isClicked(int x, int y){
        return (x >= coordonnee[0] && x <= coordonnee[0] + sizeX && y <= coordonnee[1] && y >= coordonnee[1] - sizeY);
    }

    public void render(){
        Graphics.renderQuad(coordonnee[0], coordonnee[1], sizeX, sizeY, texture, true);
        if(content != null)
        	Graphics.renderText(content, coordonnee[0] + (sizeX / 2 - content.getFont().getWidth(content.getTextToDisplay())/2), coordonnee[1] - (sizeY / 2 - content.getFont().getHeight(content.getTextToDisplay())/2));
    }

    public int compareTo(Object toCompare){
    	if(content == null)
    		return 0;
        if(toCompare instanceof MenuButton){
            return this.content.getTextToDisplay().compareTo( ( (MenuButton) toCompare ).content.getTextToDisplay() );
        }
        return 0;
    }

    public Text getContent() {
        return content;
    }

    public void setContent(Text content) {
        this.content = content;
    }
}
