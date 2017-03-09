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
        if(Launcher.width >= WorldParameters.getBordDroit())
            return;

        WorldParameters.setxScroll( -(player.getCoordonnee()[0] - Launcher.width/2) );

	    boolean bordDroitAfficher = WorldParameters.getBordDroit() <= Launcher.width - WorldParameters.getxScroll() ;
        boolean bordGaucheAfficher = WorldParameters.getxScroll() >= WorldParameters.getBordGauche();

        if(bordGaucheAfficher){
            WorldParameters.setxScroll(WorldParameters.getBordGauche());
            return;
        }
        if(bordDroitAfficher) {
            WorldParameters.setxScroll(-(WorldParameters.getBordDroit() - Launcher.width));
            return;
        }
	}

    /**
     * Permet au scrolling de suivre le Player lorsqu'il se déplace sur l'axe des ordonnées,
     * afin que ce dernier soit au centre de la fenêtre tant qu'il n'a pas atteint un bord du World.
     */
	public void translateViewY(){
	    if(Launcher.height >= WorldParameters.getBordHaut())
	        return;

        WorldParameters.setyScroll(player.getCoordonnee()[1] - Launcher.height/2);
        //WorldParameters.setyScroll(100);

        boolean bordHautAfficher = 0 >= WorldParameters.getBordHaut() - Launcher.height - WorldParameters.getyScroll();
        boolean bordBasAfficher = 0 <= WorldParameters.getBordBas() - WorldParameters.getyScroll();

        if(bordBasAfficher){
            WorldParameters.setyScroll(WorldParameters.getBordBas());
            return;
        }
        if(bordHautAfficher){
           WorldParameters.setyScroll(WorldParameters.getBordHaut() - Launcher.height);
            return;
        }
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
