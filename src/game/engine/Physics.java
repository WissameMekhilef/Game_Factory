package game.engine;

import game.Component;
import game.Game;
import game.entities.Movable;
import game.entities.Player;
import game.entities.Solid;

/**
 * Created by wissamemekhilef on 27/01/2017.
 */
public class Physics {
    private static double Gamma = 30.81;
    private static double G = 2;
    private static double deltaT = 1.0/60.0;

	/**
	 * Fonction de simulation de gravite
	 * @param toMove
	 * 	L'objet sur lequel on applique la gravité
	 *
	 */
	public static void gravite(Movable toMove){
		if(!toMove.isBlockedByBottom() && !toMove.isBlockedByTop()){

			if( !(toMove.getCoordonnee()[1] <= toMove.getSize()) || (toMove.getVitessePrev()[1] < 0) )
				toMove.getVitesse()[1] = toMove.getVitessePrev()[1] + Gamma * deltaT ;

			toMove.getCoordonnee()[1] =  (int) Math.ceil(toMove.getCoordonnee()[1] - toMove.getVitesse()[1]);
			toMove.getCoordonneePrev()[1] = toMove.getCoordonnee()[1];

			toMove.getCoordonnee()[1] = (toMove.getCoordonnee()[1] <= toMove.getSize())?toMove.getSize():toMove.getCoordonnee()[1];
			toMove.getVitesse()[1] = (toMove.getCoordonnee()[1] <= toMove.getSize())?0:toMove.getVitesse()[1];
			toMove.getVitessePrev()[1] = toMove.getVitesse()[1];
		}else {
			toMove.getVitessePrev()[1] = 0;
		}
    }

    public static void freinage(Movable toMove) {
		if(!toMove.isBlockedByLeft() && !toMove.isBlockedByRight()){
			if(toMove.getVitessePrev()[0] > 1){
				toMove.getVitesse()[0] = toMove.getVitessePrev()[0] - G * deltaT ;
			}else if(toMove.getVitessePrev()[0] < -1)
				toMove.getVitesse()[0] = toMove.getVitessePrev()[0] + G * deltaT ;
			else if((toMove.getVitessePrev()[0] < 1) && (toMove.getVitesse()[0] > -1))
				toMove.getVitesse()[0] = 0;

			toMove.getCoordonnee()[0] =  (int) Math.ceil(toMove.getCoordonneePrev()[0] + toMove.getVitesse()[0]);
			toMove.getCoordonneePrev()[0] = toMove.getCoordonnee()[0];

			toMove.getVitesse()[0] = (toMove.getCoordonnee()[0] + Game.xScroll >= 0)?toMove.getVitesse()[0]:0;
			toMove.getVitesse()[0] = (toMove.getCoordonnee()[0] + Game.xScroll - Component.width + toMove.getSize() <= 0)?toMove.getVitesse()[0]:0;

			toMove.getVitessePrev()[0] = toMove.getVitesse()[0];
		} else {
			toMove.getVitessePrev()[0] = 0;
		}

    }

    public static int isAbove(Player p, Solid s) {
		return p.getCoordonnee()[1] - p.getSize() - s.getCoordonnee()[1];
	}

	public static int isBelow(Player p, Solid s) {
		return s.getCoordonnee()[1] - s.getSize() - p.getCoordonnee()[1];
	}

	public static int isOnTheLeft(Player p, Solid s) {
		return s.getCoordonnee()[0] - p.getCoordonnee()[0] - p.getSize();
	}

	public static int isOnTheRight(Player p, Solid s) {
		return p.getCoordonnee()[0] - s.getCoordonnee()[0] - s.getSize();
	}

	public static void isStuck(Player p, Solid s) {
		if((isAbove(p, s) < 0) && (p.getVitesse()[1] > 0)){
			if((isBelow(p, s) < 0) && (isOnTheLeft(p, s) < 0) && (isOnTheRight(p, s) < 0)) {
				//System.out.println("stuck above");
				p.setBlockedByBottom(true);
				p.getCoordonnee()[1] = p.getSize() + s.getCoordonnee()[1];
			}
			return;
		}else {
			p.setBlockedByBottom(false);
		}

		if((isBelow(p, s) < 0) && (p.getVitesse()[1] < 0)) {
			if ((isAbove(p, s) < 0) && (isOnTheLeft(p, s) < 0) && (isOnTheRight(p, s) < 0)) {
				//System.out.println("stuck Below");
				p.setBlockedByTop(true);
				p.getCoordonnee()[1] = s.getCoordonnee()[1] - s.getSize();
			}
			return;
		}else {
			p.setBlockedByTop(false);
		}

		if((isOnTheLeft(p, s) < 0) && (p.getVitesse()[0] > 0)) {
			if ((isAbove(p, s) < 0) && (isBelow(p, s) < 0) && (isOnTheRight(p, s) < 0)) {
				p.setBlockedByRight(true);
				p.getCoordonnee()[0] = s.getCoordonnee()[0] - p.getSize();
			}
			return;
		}else {
			p.setBlockedByRight(false);
		}

		if((isOnTheRight(p, s) < 0) && (p.getVitesse()[0] < 0)){
			if((isAbove(p, s) < 0) && (isBelow(p, s) < 0) && (isOnTheLeft(p, s) < 0)) {
				p.setBlockedByLeft(true);
				p.getCoordonnee()[0] = s.getCoordonnee()[0] + s.getSize();
			}
			return;
		} else {
			p.setBlockedByLeft(false);
		}

	}

}
