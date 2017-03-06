package game.world;

import dataMapping.Data;
import engine.Graphics;
import engine.Launcher;
import game.graphicItems.Text;
import org.newdawn.slick.Color;

/**
 * Created by wissamemekhilef on 02/03/2017.
 */
public class EndingScreen {

    private Text textWorldStatus;
    private Text textTimeElapsed;

    public EndingScreen(boolean win, long timeElapsed){
        this.textWorldStatus = new Text((win)? "Game Won":"Game Lost", Data.fontsMap.get("chlorinap"), Color.blue);

        this.textTimeElapsed = new Text(timeElapsed+"", Data.fontsMap.get("chlorinap"), Color.blue);
    }

    public void render() {

    	int xStatus = (Launcher.width - textWorldStatus.getSizeX()) / 2;
    	int yStatus = 3 * (Launcher.height - textWorldStatus.getSizeY()) / 4;
        Graphics.renderText(textWorldStatus, xStatus, yStatus);

        int xTime = (Launcher.width - textTimeElapsed.getSizeX()) / 2;
        int yTime = (Launcher.height - textWorldStatus.getSizeY()) / 4;
        Graphics.renderText(textTimeElapsed, xTime, yTime);

    }

}
