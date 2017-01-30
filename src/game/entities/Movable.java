package game.entities;

public abstract class Movable extends Solid {



	protected int[] coordonneePrev;
	protected double[] vitesse, vitessePrev;

	public Movable(int size, int v0, int v1, int x, int y) {
		super(size, x, y);
		coordonneePrev = new int[]{coordonnee[0], coordonnee[1]};
		vitesse = new double[]{v0, v1};
		vitessePrev = new double[]{vitesse[0], vitesse[1]};
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

}
