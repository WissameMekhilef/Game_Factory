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
        //System.out.println("-LevelParameters.getxScroll() >= LevelParameters.getBordDroit() - Component.width = "+(-LevelParameters.getxScroll() >= LevelParameters.getBordDroit() - Component.width));
        if(-LevelParameters.getxScroll() >= LevelParameters.getBordDroit() - Component.width)
            return;
        LevelParameters.setxScroll(-vX);
        LevelParameters.setyScroll(vY);
    }
}
