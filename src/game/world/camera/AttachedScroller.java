package game.world.camera;

import engine.Launcher;
import game.world.WorldParameters;
import game.world.entities.Player;

public class AttachedScroller implements Camera {

    private boolean xAttached;
    private boolean yAttached;
    private Player player;

    public AttachedScroller(boolean xAttached, boolean yAttached, Player player){
        this.xAttached = xAttached;
        this.yAttached = yAttached;
        this.player = player;
    }

    /**
     * Permet au scrolling de suivre le Player lorsqu'il se déplace sur l'axe des abscisses,
     * afin que ce dernier soit au centre de la fenêtre tant qu'il n'a pas atteint un bord du World.
     */
    public void translateViewX() {
	    boolean aDroite = player.getCoordonnee()[0] + WorldParameters.getxScroll() > Launcher.width / 2;
        boolean aGauche = ! aDroite;

	    boolean bordDroitAfficher = 0 >= WorldParameters.getBordDroit() - Launcher.width + WorldParameters.getxScroll() ;
        boolean bordGaucheAfficher = WorldParameters.getxScroll() >= WorldParameters.getBordGauche();

        if(aDroite){
            if(bordDroitAfficher) {
                return;
            }
        }
        if(aGauche){
            if(bordGaucheAfficher){
                WorldParameters.setxScroll(WorldParameters.getBordGauche());
                return;
            }
        }

        WorldParameters.setxScroll(WorldParameters.getxScroll() - player.getCoordonnee()[0] + player.prevX);
	}

    /**
     * Permet au scrolling de suivre le Player lorsqu'il se déplace sur l'axe des ordonnées,
     * afin que ce dernier soit au centre de la fenêtre tant qu'il n'a pas atteint un bord du World.
     */
	public void translateViewY(){
        boolean moitieHaute = player.getCoordonnee()[1] - WorldParameters.getyScroll() > Launcher.height / 2;
        boolean moitieBasse = !moitieHaute;

        boolean bordHautAfficher = 0 >= WorldParameters.getBordHaut() - Launcher.height - WorldParameters.getyScroll();
        boolean bordBasAfficher = 0 <= WorldParameters.getBordBas() - WorldParameters.getyScroll();

        if(moitieBasse && bordBasAfficher){
            WorldParameters.setyScroll(WorldParameters.getBordBas());
            return;
        }
        if(moitieHaute && bordHautAfficher){
            WorldParameters.setyScroll(WorldParameters.getBordHaut() - Launcher.height);
            return;
        }

        WorldParameters.setyScroll(WorldParameters.getyScroll() + player.getCoordonnee()[1] - player.prevY);
    }

	/**
	 * Suit les déplacements du Player.
	 */
    public void translateView(){
	    if(xAttached)
	        translateViewX();
	    if(yAttached)
	        translateViewY();
    }

}
