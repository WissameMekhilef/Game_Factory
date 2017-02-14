package game.camera;

import game.WorldParameters;
import game.engine.Component;

public class ForceScroller implements Camera {
    private int vX;
    private int vY;

    public ForceScroller(int vX, int vY){
        this.vX = vX;
        this.vY = vY;
    }

    public void translateView(){
        if(-WorldParameters.getxScroll() >= WorldParameters.getBordDroit() - Component.width)
            return;
        WorldParameters.setxScroll(WorldParameters.getxScroll() - vX);
        WorldParameters.setyScroll(WorldParameters.getyScroll() + vY);
    }
}
