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

    public void render(){
        Graphics.renderText(textWorldStatus, Launcher.width / 2 - Data.fontsMap.get("tron").getWidth(textWorldStatus.getTextToDisplay())/2, 3 * Launcher.height / 4 - Data.fontsMap.get("tron").getHeight(textWorldStatus.getTextToDisplay())/2);
        Graphics.renderText(textTimeElapsed, Launcher.width / 2 - Data.fontsMap.get("tron").getWidth(textTimeElapsed.getTextToDisplay())/2, 1 * Launcher.height / 4 - Data.fontsMap.get("tron").getHeight(textTimeElapsed.getTextToDisplay())/2);
    }

}
