package game.graphicItems;

import engine.Graphics;
import org.newdawn.slick.opengl.Texture;

import java.util.concurrent.Callable;

public class MenuButton implements Comparable{

    protected Texture texture;
    private int[] coordonnee;
    protected int sizeX;
    protected int sizeY;

    private Text content;

    private Callable<Integer> action;

    public MenuButton(int sizeX, int sizeY, Texture texture, Text text, Callable<Integer> action){
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

    public Callable<Integer> getAction() {
        return action;
    }

    public void setAction(Callable<Integer> action) {
        this.action = action;
    }

    /**
     * Vérifie si le MenuButton a été cliqué.
     * @param x	la localisation du clic sur l'axe des abscisses
     * @param y	la localisation du clic sur l'axe des ordonnées
     * @return	vrai si le MenuButton a été cliqué, faux sinon
     */
    public boolean isClicked(int x, int y){
        return (x >= coordonnee[0] && x <= coordonnee[0] + sizeX && y <= coordonnee[1] && y >= coordonnee[1] - sizeY);
    }

    /**
     * Affiche le MenuButton à l'écran.
     */
    public void render(){
        Graphics.renderQuad(coordonnee[0], coordonnee[1], sizeX, sizeY, texture, true);
        if(content != null) {
        	int x = coordonnee[0] + (sizeX - content.getSizeX()) / 2;
        	int y = coordonnee[1] - (sizeY - content.getSizeY()) / 2;
        	Graphics.renderText(content, x, y);
        }
    }

    /**
     * Compare deux MenuButtons selon l'ordre alphabétique de leur nom.
     */
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
