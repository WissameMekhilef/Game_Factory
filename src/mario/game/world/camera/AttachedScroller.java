package mario.game.world.camera;

import mario.engine.Launcher;
import mario.game.world.WorldParameters;
import mario.game.world.entities.Player;

/**
 * Created by wissamemekhilef on 10/02/2017.
 */
public class AttachedScroller implements Camera {
    private boolean xAttached;
    private boolean yAttached;

    private Player player;

    public AttachedScroller(boolean xAttached, boolean yAttached, Player player){
        this.xAttached = xAttached;
        this.yAttached = yAttached;
        this.player = player;
    }
    public void translateViewX() {
	    boolean aDroite = player.getCoordonnee()[0] + WorldParameters.getxScroll() > Launcher.width / 2;
        boolean aGauche = ! aDroite;

	    boolean bordDroitAfficher = 0 >= WorldParameters.getBordDroit() - Launcher.width + WorldParameters.getxScroll() ;
        boolean bordGaucheAfficher = WorldParameters.getxScroll() >= WorldParameters.getBordGauche();

        if(aDroite){
            if(bordDroitAfficher) {
                return;
            }
        }else if(aGauche){
            if(bordGaucheAfficher){
                WorldParameters.setxScroll(0);
                return;
            }
        }

        WorldParameters.setxScroll(WorldParameters.getxScroll() - player.getCoordonnee()[0] + player.prevX);
	}

	public void translateViewY(){
        boolean moitieHaute = player.getCoordonnee()[1] - WorldParameters.getyScroll() > Launcher.height / 2;
        boolean moitieBasse = !moitieHaute;

        boolean bordHautAfficher = WorldParameters.getyScroll() >= WorldParameters.getBordHaut() - Launcher.height ;
        boolean bordBasAfficher = WorldParameters.getyScroll() <= WorldParameters.getBordBas();

        if(moitieBasse){
            if(bordBasAfficher) {
                WorldParameters.setyScroll(0);
                return;
            }
        }else if(moitieHaute){
            if(bordHautAfficher){
                return;
            }
        }

        WorldParameters.setyScroll(WorldParameters.getyScroll() + player.getCoordonnee()[1] - player.prevY);
    }
    public void translateView(){
	    if(xAttached)
	        translateViewX();
	    if(yAttached)
	        translateViewY();
    }

}
