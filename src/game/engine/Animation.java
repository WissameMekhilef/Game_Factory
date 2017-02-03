package game.engine;

public class Animation {
	
	/**
	 * longueur = le nb d'images dans la boucle
	 * vitesse = le nb de ticks par frames 
	 * 
	 * */
int frame, longueur, vitesse, temps;
boolean boucle, joue;
	public Animation(int longueur, int vitesse, boolean boucle) {
		this.frame = 0;//le numero de la frame 
		this.longueur = longueur;
		this.vitesse = vitesse;
		this.boucle = boucle;//si la derniere frame precede la premiere
		this.temps = 0;
	}
	/***
	 * Gere la boucle de l'animation en fonction du temps 
	 * à mettre dans game update
	 */
	public void update(){
	if (joue) {
		temps++;
		if (temps>vitesse) {
			frame++;
			if (frame>=longueur) {
				if (boucle) {
					frame=0;
				}else {
					frame=longueur-1;
				}
			}
			temps=0;
		}
	}
	}
	public void play(){
		joue=true;
	}
	public void pause(){
		joue=false;
	}
	public void stop(){
		joue=false;
		frame=0;
	}
	public int [] testAnimation(){
		return(new int []{this.frame,this.longueur,this.vitesse,this.temps,(this.boucle)?1:0,(this.joue)?1:0});
	}
	
}