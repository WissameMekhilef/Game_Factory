package game.graphicItems;

import mario.engine.Graphics;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by wissamemekhilef on 14/02/2017.
 */
public class MenuButton implements Comparable{

    protected Texture texture;
    protected int[] coordonnee;
    protected int sizeX;
    protected int sizeY;

    private Text content;

    private String action;

    public MenuButton(int sizeX, int sizeY, Texture texture, Text text, String action){
        this.coordonnee = new int[2];
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        this.texture = texture;
        this.content = text;
        this.action = action;
    }

    public void setX(int x){
        coordonnee[0] = x;

    }

    public void setY(int y){
        coordonnee[1] = y;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isClicked(int x, int y){
        return (x >= coordonnee[0] && x <= coordonnee[0] + sizeX && y <= coordonnee[1] + sizeY);
    }

    public void render(){
        Graphics.renderQuad(coordonnee[0], coordonnee[1], sizeX, sizeY, texture);
        Graphics.renderText(content, coordonnee[0] + 10, coordonnee[1]);
    }

    public int compareTo(Object toCompare){
        if(toCompare instanceof MenuButton){
            return this.action.compareTo(((MenuButton) toCompare).action);
        }
        return 0;
    }
}
