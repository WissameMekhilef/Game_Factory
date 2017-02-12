package game;

import game.engine.Component;

public class ForceScroller implements Scroller {
    private int vX;
    private int vY;

    public ForceScroller(int vX, int vY){
        this.vX = vX;
        this.vY = vY;
    }

    public void translateView(){
        if(-LevelParameters.getxScroll() >= LevelParameters.getBordDroit() - Component.width)
            return;
        LevelParameters.setxScroll(LevelParameters.getxScroll() - vX);
        LevelParameters.setyScroll(LevelParameters.getyScroll() + vY);
    }
}
