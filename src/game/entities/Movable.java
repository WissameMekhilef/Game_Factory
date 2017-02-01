package game.entities;

public abstract class Movable extends Solid {

	protected int[] coordonneePrev;
	protected double[] vitesse, vitessePrev;

	protected boolean isBlockedByTop;
	protected boolean isBlockedByBottom;

	protected boolean isBlockedByLeft;
	protected boolean isBlockedByRight;

	public Movable(int size, int v0, int v1, int x, int y) {
		super(size, x, y);
		coordonneePrev = new int[]{coordonnee[0], coordonnee[1]};
		vitesse = new double[]{v0, v1};
		vitessePrev = new double[]{vitesse[0], vitesse[1]};

		isBlockedByBottom = false;
		isBlockedByTop = false;

		isBlockedByLeft = false;
		isBlockedByRight = false;
	}

	public abstract void update();

	public int[] getCoordonneePrev() {
		return coordonneePrev;
	}

	public double[] getVitesse() {
		return vitesse;
	}

	public double[] getVitessePrev() {
		return vitessePrev;
	}

	public boolean isBlockedByTop(){ return isBlockedByTop; }

	public boolean isBlockedByBottom(){ return isBlockedByBottom; }

	public void setBlockedByTop(boolean blocked){ isBlockedByTop = blocked; }

	public void setBlockedByBottom(boolean blocked){ isBlockedByBottom = blocked; }

	public boolean isBlockedByLeft(){ return isBlockedByLeft; }

	public boolean isBlockedByRight(){ return isBlockedByRight; }

	public void setBlockedByLeft(boolean blocked){ isBlockedByLeft = blocked; }

	public void setBlockedByRight(boolean blocked){ isBlockedByRight = blocked; }

}
