package game.engine;

import game.Component;
import game.Game;
import game.entities.Movable;
import game.entities.Player;
import game.entities.PotentialCollision;
import game.entities.Solid;

/**
 * Created by wissamemekhilef on 27/01/2017.
 */
public class Physics {
    private static double Gamma = 20.81;
    private static double G = 4;
    private static double deltaT = 1.0/60.0;

	/**
	 * Fonction de simulation de gravite
	 * @param toMove
	 * 	L'objet sur lequel on applique la gravité
	 *
	 */
	public static void gravite(Movable toMove){
		if( !(toMove.getCoordonnee()[1] <= toMove.getSize()) || (toMove.getVitessePrev()[1] < 0) )
			toMove.getVitesse()[1] = toMove.getVitessePrev()[1] + Gamma * deltaT;

		if(toMove.isBlockedByBottom() && toMove.getVitesse()[1] > 0)
			toMove.getVitesse()[1] = 0;
		if(toMove.isBlockedByTop() && toMove.getVitesse()[1] < 0)
			toMove.getVitesse()[1] = 0;

		toMove.getCoordonnee()[1] =  (int) Math.ceil(toMove.getCoordonnee()[1] - toMove.getVitesse()[1]);
		toMove.getCoordonneePrev()[1] = toMove.getCoordonnee()[1];

		toMove.getCoordonnee()[1] = (toMove.getCoordonnee()[1] <= toMove.getSize())?toMove.getSize():toMove.getCoordonnee()[1];
		toMove.getVitesse()[1] = (toMove.getCoordonnee()[1] <= toMove.getSize())?0:toMove.getVitesse()[1];
		toMove.getVitessePrev()[1] = toMove.getVitesse()[1];
    }

    public static void freinage(Movable toMove) {

		if(toMove.getVitessePrev()[0] > 1){
			toMove.getVitesse()[0] = toMove.getVitessePrev()[0] - G * deltaT;
		}else if(toMove.getVitessePrev()[0] < -1)
			toMove.getVitesse()[0] = toMove.getVitessePrev()[0] + G * deltaT;
		else if((toMove.getVitessePrev()[0] < 1) && (toMove.getVitessePrev()[0] > -1))
			toMove.getVitesse()[0] = 0;

		if(toMove.isBlockedByLeft() && toMove.getVitesse()[0] < 0)
			toMove.getVitesse()[0] = 0;
		if(toMove.isBlockedByRight() && toMove.getVitesse()[0] > 0)
			toMove.getVitesse()[0] = 0;

		toMove.getCoordonnee()[0] =  (int) Math.ceil(toMove.getCoordonneePrev()[0] + toMove.getVitesse()[0]);
		toMove.getCoordonneePrev()[0] = toMove.getCoordonnee()[0];

		toMove.getVitesse()[0] = (toMove.getCoordonnee()[0] + Game.xScroll >= 0)?toMove.getVitesse()[0]:0;
		toMove.getVitesse()[0] = (toMove.getCoordonnee()[0] + Game.xScroll - Component.width + toMove.getSize() <= 0)?toMove.getVitesse()[0]:0;

		toMove.getVitessePrev()[0] = toMove.getVitesse()[0];
    }

    public static int isAbove(Player p, Solid s) { return p.getCoordonnee()[1] - p.getSize() - s.getCoordonnee()[1]; }

	public static int isBelow(Player p, Solid s) { return s.getCoordonnee()[1] - s.getSize() - p.getCoordonnee()[1]; }

	public static int isOnTheLeft(Player p, Solid s) { return s.getCoordonnee()[0] - p.getCoordonnee()[0] - p.getSize(); }

	public static int isOnTheRight(Player p, Solid s) { return p.getCoordonnee()[0] - s.getCoordonnee()[0] - s.getSize(); }

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
			pc.getPlayer().getCoordonnee()[1] = pc.getPlayer().getSize() + pc.getObstacle().getCoordonnee()[1];
			pc.setAbove(false);
		}else if(collisionSide == "below" && newRight && newLeft){
			pc.getPlayer().setBlockedByTop(true);
			pc.getPlayer().getCoordonnee()[1] = pc.getObstacle().getCoordonnee()[1] - pc.getObstacle().getSize();
			pc.setBelow(false);
		}else if(collisionSide == "right" && newAbove && newBelow){
			pc.getPlayer().setBlockedByLeft(true);
			pc.getPlayer().getCoordonneePrev()[0] = pc.getObstacle().getCoordonnee()[0] + pc.getObstacle().getSize();
			pc.setRight(false);
		}else if(collisionSide == "left" && newAbove && newBelow){
			pc.getPlayer().setBlockedByRight(true);
			pc.getPlayer().getCoordonneePrev()[0] = pc.getObstacle().getCoordonnee()[0] - pc.getPlayer().getSize();
			pc.setLeft(false);
		}
	}

}
