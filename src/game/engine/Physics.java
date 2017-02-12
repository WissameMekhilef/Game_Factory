package game.engine;

import game.LevelParameters;
import game.entities.Player;
import game.entities.PotentialCollision;
import game.entities.Solid;

/**
 * Created by wissamemekhilef on 27/01/2017.
 */
public class Physics {

	/**
	 * Fonction de simulation de gravite
	 * @param toMove
	 * 	L'objet sur lequel on applique la gravité
	 *
	 */
	public static void gravite(Player toMove){
		if( !(toMove.getCoordonnee()[1] <= toMove.getSizeY()) || (toMove.getVitessePrev()[1] < 0) )
			toMove.getVitesse()[1] = toMove.getVitessePrev()[1] + LevelParameters.getGamma() * LevelParameters.getDeltaT();

		if(toMove.isBlockedByBottom() && toMove.getVitesse()[1] > 0)
			toMove.getVitesse()[1] = 0;
		if(toMove.isBlockedByTop() && toMove.getVitesse()[1] < 0)
			toMove.getVitesse()[1] = 0;

		toMove.getCoordonnee()[1] =  (int) Math.ceil(toMove.getCoordonnee()[1] - toMove.getVitesse()[1]);
		toMove.getCoordonneePrev()[1] = toMove.getCoordonnee()[1];

		boolean isBelowTheSurface = toMove.getCoordonnee()[1] <= LevelParameters.getyScroll();
		toMove.getCoordonnee()[1] = (isBelowTheSurface)? toMove.getSizeY() : toMove.getCoordonnee()[1];
		toMove.getVitesse()[1] = (isBelowTheSurface)? 0 : toMove.getVitesse()[1];
        toMove.setBelowTheSurface(isBelowTheSurface);

		toMove.getVitessePrev()[1] = toMove.getVitesse()[1];
    }

    public static void freinage(Player toMove) {

		if(toMove.getVitessePrev()[0] > 1){
			toMove.getVitesse()[0] = toMove.getVitessePrev()[0] - LevelParameters.getG() * LevelParameters.getDeltaT();
		}else if(toMove.getVitessePrev()[0] < -1)
			toMove.getVitesse()[0] = toMove.getVitessePrev()[0] + LevelParameters.getG() * LevelParameters.getDeltaT();
		else if((toMove.getVitessePrev()[0] < 1) && (toMove.getVitessePrev()[0] > -1))
			toMove.getVitesse()[0] = 0;

		if(toMove.isBlockedByLeft() && toMove.getVitesse()[0] < 0)
			toMove.getVitesse()[0] = 0;
		if(toMove.isBlockedByRight() && toMove.getVitesse()[0] > 0)
			toMove.getVitesse()[0] = 0;

		toMove.getCoordonnee()[0] =  (int) Math.ceil(toMove.getCoordonneePrev()[0] + toMove.getVitesse()[0]);
		toMove.getCoordonneePrev()[0] = toMove.getCoordonnee()[0];

		toMove.getVitesse()[0] = (toMove.getCoordonnee()[0] + LevelParameters.getxScroll() >= 0)?toMove.getVitesse()[0]:0;
		toMove.getVitesse()[0] = (toMove.getCoordonnee()[0] + LevelParameters.getxScroll() - Component.width + toMove.getSizeX() <= 0)?toMove.getVitesse()[0]:0;

		toMove.getVitessePrev()[0] = toMove.getVitesse()[0];
    }

    public static int isAbove(Player p, Solid s) { return p.getCoordonnee()[1] - p.getSizeY() - s.getCoordonnee()[1]; }

	public static int isBelow(Player p, Solid s) { return s.getCoordonnee()[1] - s.getSizeY() - p.getCoordonnee()[1]; }

	public static int isOnTheLeft(Player p, Solid s) { return s.getCoordonnee()[0] - p.getCoordonnee()[0] - p.getSizeX(); }

	public static int isOnTheRight(Player p, Solid s) { return p.getCoordonnee()[0] - s.getCoordonnee()[0] - s.getSizeX(); }

	public static void isStuck(PotentialCollision pc) {
		boolean newAbove = isAbove(pc.getPlayer(), pc.getObstacle()) <= 0;
		boolean newBelow = isBelow(pc.getPlayer(), pc.getObstacle()) <= 0;
		boolean newRight = isOnTheRight(pc.getPlayer(), pc.getObstacle()) <= 0;
		boolean newLeft  = isOnTheLeft(pc.getPlayer(), pc.getObstacle()) <= 0;

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

		if(collisionSide == "above" && newRight && newLeft){
			pc.getPlayer().setBlockedByBottom(true);
			pc.getPlayer().getCoordonnee()[1] = pc.getPlayer().getSizeY() + pc.getObstacle().getCoordonnee()[1];
			pc.setAbove(false);
		}else if(collisionSide == "below" && newRight && newLeft){
			pc.getPlayer().setBlockedByTop(true);
			pc.getPlayer().getCoordonnee()[1] = pc.getObstacle().getCoordonnee()[1] - pc.getObstacle().getSizeY();
			pc.setBelow(false);
		}else if(collisionSide == "right" && newAbove && newBelow){
			pc.getPlayer().setBlockedByLeft(true);
			pc.getPlayer().getCoordonneePrev()[0] = pc.getObstacle().getCoordonnee()[0] + pc.getObstacle().getSizeX();
			pc.setRight(false);
		}else if(collisionSide == "left" && newAbove && newBelow){
			pc.getPlayer().setBlockedByRight(true);
			pc.getPlayer().getCoordonneePrev()[0] = pc.getObstacle().getCoordonnee()[0] - pc.getPlayer().getSizeX();
			pc.setLeft(false);
		}
	}

}
