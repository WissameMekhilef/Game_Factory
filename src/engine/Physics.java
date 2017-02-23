package engine;

import game.world.WorldParameters;
import game.world.entities.Player;
import game.world.entities.PotentialCollision;
import game.world.entities.Solid;

/**
 * Created by wissamemekhilef on 27/01/2017.
 */
public class Physics {

	/**
	 * Fonction de simulation de gravite
	 * @param toMove
	 * 	L'objet sur lequel on applique la gravité
     *
     * 	    Les paramètres de la gravité sont issues de WorldParameters
	 *
	 */
	public static void gravite(Player toMove){
		if( !(toMove.getCoordonnee()[1] <= toMove.getSizeY()) || (toMove.getVitessePrev()[1] < 0) )
		    //On calcule la nouvelle vitesse uniquement si necessaire
			toMove.getVitesse()[1] = toMove.getVitessePrev()[1] + WorldParameters.getGamma() * WorldParameters.getDeltaT();


		if(toMove.isBlockedByBottom() && toMove.getVitesse()[1] > 0)
		    //Si on est bloqué par le bas est que la vitesse est supérieur à zero alors on met la vitesse a zero pour ne pas traverser l'obstacle
			toMove.getVitesse()[1] = 0;
		if(toMove.isBlockedByTop() && toMove.getVitesse()[1] < 0)
		    //De meme manière que dans le teste précédent mais dans le cas d'un blocage par le haut
			toMove.getVitesse()[1] = 0;

        //Une fois la bonne vitesse calculé en fonction de toute les conditions précédentes alors on peut mettre à jour la nouvelle coordonée
		toMove.getCoordonnee()[1] =  (int) Math.ceil(toMove.getCoordonnee()[1] - toMove.getVitesse()[1]);
		//On passe la coordonnée précédente à 0 pour que au tour d'apès on puisse calculer la nouvelle position en fonction de la position précédente
		toMove.getCoordonneePrev()[1] = toMove.getCoordonnee()[1];

        //La fonction detecte si l'objet est passé sous la surface affiché ou pas
		boolean isBelowTheSurface = toMove.getCoordonnee()[1] <= WorldParameters.getyScroll();
		toMove.getCoordonnee()[1] = (isBelowTheSurface)? toMove.getSizeY() : toMove.getCoordonnee()[1];
		toMove.getVitesse()[1] = (isBelowTheSurface)? 0 : toMove.getVitesse()[1];
        toMove.setBelowTheSurface(isBelowTheSurface);

        //La fonction detecte si l'objet est passé sous la surface affiché ou pas
        boolean isUpTheSurface = toMove.getCoordonnee()[1] > WorldParameters.getBordHaut();
        toMove.getCoordonnee()[1] = (isUpTheSurface)? WorldParameters.getBordHaut() : toMove.getCoordonnee()[1];
        toMove.getVitesse()[1] = (isUpTheSurface)? 0 : toMove.getVitesse()[1];

		toMove.getVitessePrev()[1] = toMove.getVitesse()[1];
    }

    /**
     * Fonction de simulation d'un freinage
     * @param toMove
     *  L'objet sur lequel on applique le freinage
     *
     *      Les paramètres du freinage sont issues de WorldParameters
     */
    public static void freinage(Player toMove) {

		if(toMove.getVitessePrev()[0] > 1){
			toMove.getVitesse()[0] = toMove.getVitessePrev()[0] - WorldParameters.getG() * WorldParameters.getDeltaT();
		}else if(toMove.getVitessePrev()[0] < -1)
			toMove.getVitesse()[0] = toMove.getVitessePrev()[0] + WorldParameters.getG() * WorldParameters.getDeltaT();
		else if((toMove.getVitessePrev()[0] < 1) && (toMove.getVitessePrev()[0] > -1))
			toMove.getVitesse()[0] = 0;

		if(toMove.isBlockedByLeft() && toMove.getVitesse()[0] < 0)
			toMove.getVitesse()[0] = 0;
		if(toMove.isBlockedByRight() && toMove.getVitesse()[0] > 0)
			toMove.getVitesse()[0] = 0;

		toMove.getCoordonnee()[0] =  (int) Math.ceil(toMove.getCoordonneePrev()[0] + toMove.getVitesse()[0]);
		toMove.getCoordonneePrev()[0] = toMove.getCoordonnee()[0];

		toMove.getVitesse()[0] = (toMove.getCoordonnee()[0] + WorldParameters.getxScroll() >= 0)?toMove.getVitesse()[0]:0;
		toMove.getVitesse()[0] = (toMove.getCoordonnee()[0] + WorldParameters.getxScroll() - Launcher.width + toMove.getSizeX() <= 0)?toMove.getVitesse()[0]:0;

		toMove.getVitessePrev()[0] = toMove.getVitesse()[0];
    }

    /**
     * Fonction qui determine la position d'un Player par rapport au haut d'un obstacle
     * @param p
     *      Le Player voulue
     * @param s
     *      Le Solid par rapport auquel il est comparé
     * @return
     *      Retourne un entier qui est l'écart entre le bas du Player p et le dessus du Solid s, on ne prend en compte que le décalage sur l'axe des Y
     */
    private static int isAbove(Player p, Solid s) { return p.getCoordonnee()[1] - p.getSizeY() - s.getCoordonnee()[1]; }

    /**
     * Fonction qui determine la position d'un Player par rapport au bas d'un obstacle
     * @param p
     *      Le Player voulue
     * @param s
     *      Le Solid par rapport auquel il est comparé
     * @return
     *      Retourne un entier qui est l'écart entre le haut du Player p et le dessous du Solid s, on ne prend en compte que le décalage sur l'axe des Y
     */
	private static int isBelow(Player p, Solid s) { return s.getCoordonnee()[1] - s.getSizeY() - p.getCoordonnee()[1]; }

    /**
     * Fonction qui determine la position d'un Player par rapport au coté gauche d'un obstacle
     * @param p
     *      Le Player voulue
     * @param s
     *      Le Solid par rapport auquel il est comparé
     * @return
     *      Retourne un entier qui est l'écart entre le coté droit du Player p et le coté gauche du Solid s, on ne prend en compte que le décalage sur l'axe des X
     */
	private static int isOnTheLeft(Player p, Solid s) { return s.getCoordonnee()[0] - p.getCoordonnee()[0] - p.getSizeX(); }

    /**
     * Fonction qui determine la position d'un Player par rapport au coté droit d'un obstacle
     * @param p
     *      Le Player voulue
     * @param s
     *      Le Solid par rapport auquel il est comparé
     * @return
     *      Retourne un entier qui est l'écart entre le coté gauche du Player p et le coté droit du Solid s, on ne prend en compte que le décalage sur l'axe des X
     */
	private static int isOnTheRight(Player p, Solid s) { return p.getCoordonnee()[0] - s.getCoordonnee()[0] - s.getSizeX(); }

    /**
     * Fonction qui à partir d'un couple Player <-> Solid determine si il y'a collision ou pas
     * @param pc
     *      Le couple Player <-> Solid
     */
	public static String isStuck(PotentialCollision pc) {
	    //On determine la position du player par rapport à l'obstacle
        //On dispose de 1 boolean par direction
		boolean newAbove = isAbove(pc.getPlayer(), pc.getSolid()) <= 0;
		boolean newBelow = isBelow(pc.getPlayer(), pc.getSolid()) <= 0;
		boolean newRight = isOnTheRight(pc.getPlayer(), pc.getSolid()) <= 0;
		boolean newLeft  = isOnTheLeft(pc.getPlayer(), pc.getSolid()) <= 0;

		//Ce string permettra de determiner d'ou vient la collison
		String collisionSide = "";

		if((newAbove != pc.isAbove()) && !pc.getPlayer().isBlockedByBottom())
			collisionSide = "above";
		else if((newBelow != pc.isBelow()) && !pc.getPlayer().isBlockedByTop())
			collisionSide = "below";
		else if((newRight != pc.isRight()) && !pc.getPlayer().isBlockedByLeft())
			collisionSide = "right";
		else if((newLeft != pc.isLeft()) && !pc.getPlayer().isBlockedByRight())
			collisionSide = "left";

		pc.setAbove(newAbove);
		pc.setBelow(newBelow);
		pc.setRight(newRight);
		pc.setLeft(newLeft);

        if(collisionSide == "above" && pc.isRight() && pc.isLeft())
		    return "above";
        else if(collisionSide == "below" && pc.isRight() && pc.isLeft())
            return "below";
        else if(collisionSide == "right" && pc.isAbove() && pc.isBelow())
            return "right";
        else if(collisionSide == "left" && pc.isAbove() && pc.isBelow())
            return "left";

        return "";

    }

	public static void replaceAfterCollision(PotentialCollision pc, String collisionSide){
        if(collisionSide == "above"){
            pc.getPlayer().setBlockedByBottom(true);
            pc.getPlayer().getCoordonnee()[1] = pc.getPlayer().getSizeY() + pc.getSolid().getCoordonnee()[1];
            pc.setAbove(false);
        }else if(collisionSide == "below"){
            pc.getPlayer().setBlockedByTop(true);
            pc.getPlayer().getCoordonnee()[1] = pc.getSolid().getCoordonnee()[1] - pc.getSolid().getSizeY();
            pc.setBelow(false);
        }else if(collisionSide == "right"){
            pc.getPlayer().setBlockedByLeft(true);
            pc.getPlayer().getCoordonneePrev()[0] = pc.getSolid().getCoordonnee()[0] + pc.getSolid().getSizeX();
            pc.setRight(false);
        }else if(collisionSide == "left"){
            pc.getPlayer().setBlockedByRight(true);
            pc.getPlayer().getCoordonneePrev()[0] = pc.getSolid().getCoordonnee()[0] - pc.getPlayer().getSizeX();
            pc.setLeft(false);
        }
    }

}
