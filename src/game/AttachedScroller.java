package game;

import game.engine.Component;
import game.entities.Player;

/**
 * Created by wissamemekhilef on 10/02/2017.
 */
public class AttachedScroller implements Scroller {
    private boolean xAttached;
    private boolean yAttached;

    private Player player;

    public AttachedScroller(boolean xAttached, boolean yAttached, Player player){
        this.xAttached = xAttached;
        this.yAttached = yAttached;
        this.player = player;
    }
    public void translateViewX() {
	    boolean aDroite = player.getCoordonnee()[0] + LevelParameters.getxScroll() > Component.width / 2;
        boolean aGauche = ! aDroite;

	    boolean bordDroitAfficher = 0 >= LevelParameters.getBordDroit() - Component.width + LevelParameters.getxScroll() ;
        boolean bordGaucheAfficher = LevelParameters.getxScroll() >= LevelParameters.getBordGauche();

        if(aDroite){
            if(bordDroitAfficher) {
                return;
            }
        }else if(aGauche){
            if(bordGaucheAfficher){
                LevelParameters.setxScroll(0);
                return;
            }
        }

        LevelParameters.setxScroll(LevelParameters.getxScroll() - player.getCoordonnee()[0] + player.prevX);
	}

	public void translateViewY(){
        boolean moitieHaute = player.getCoordonnee()[1] - LevelParameters.getyScroll() > Component.height / 2;
        boolean moitieBasse = !moitieHaute;

        boolean bordHautAfficher = LevelParameters.getyScroll() >= LevelParameters.getBordHaut() - Component.height ;
        boolean bordBasAfficher = LevelParameters.getyScroll() <= LevelParameters.getBordBas();

        if(moitieBasse){
            if(bordBasAfficher) {
                LevelParameters.setyScroll(0);
                return;
            }
        }else if(moitieHaute){
            if(bordHautAfficher){
                return;
            }
        }

        LevelParameters.setyScroll(LevelParameters.getyScroll() + player.getCoordonnee()[1] - player.prevY);
    }
    public void translateView(){
	    if(xAttached)
	        translateViewX();
	    if(yAttached)
	        translateViewY();
    }

}
