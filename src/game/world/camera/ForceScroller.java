package game.world.camera;

import mario.engine.Launcher;
import game.world.WorldParameters;

public class ForceScroller implements Camera {
    private int v0;
    private int v1;

    public ForceScroller(int v0, int v1){
        this.v0 = v0;
        this.v1 = v1;
    }

    public void translateView(){
        if(-WorldParameters.getxScroll() >= WorldParameters.getBordDroit() - Launcher.width)
            return;
        WorldParameters.setxScroll(WorldParameters.getxScroll() - v0);
        WorldParameters.setyScroll(WorldParameters.getyScroll() + v1);
    }
}
