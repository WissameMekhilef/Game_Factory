package mario.game.world.camera;

import mario.engine.Launcher;
import mario.game.world.WorldParameters;

public class ForceScroller implements Camera {
    private int vX;
    private int vY;

    public ForceScroller(int vX, int vY){
        this.vX = vX;
        this.vY = vY;
    }

    public void translateView(){
        if(-WorldParameters.getxScroll() >= WorldParameters.getBordDroit() - Launcher.width)
            return;
        WorldParameters.setxScroll(WorldParameters.getxScroll() - vX);
        WorldParameters.setyScroll(WorldParameters.getyScroll() + vY);
    }
}
