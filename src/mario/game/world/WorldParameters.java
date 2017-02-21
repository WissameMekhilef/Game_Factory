package mario.game.world;

import org.newdawn.slick.openal.Audio;

/**
 * Created by wissamemekhilef on 09/02/2017.
 */
public class WorldParameters {
    //BEGIN PHYSICS
    private static double Gamma;
    private static double G;
    private static double deltaT;
    //END PHYSICS

    //BEGIN SOUNDS
    private static Audio backgroundMusic;
    //END SOUNDS

    //BEGIN PLAYER
    private static int MAXSPEED;
    private static double jumpTime;
    private static int gainVitesseX;
    private static int gainVitesseY;
    //END PLAYER

    //BEGIN SCROLL
    private static int xScroll;
    private static int yScroll;
    //END SCROLL

    //BEGIN LEVEL BOUND$
    private static int bordBas;
    private static int bordHaut;
    private static int bordGauche;
    private static int bordDroit;
    //END LEVEL BOUNDS

    public static double getGamma() {
        return Gamma;
    }

    public static void setGamma(double gamma) {
        Gamma = gamma;
    }

    public static double getG() {
        return G;
    }

    public static void setG(double g) {
        G = g;
    }

    public static double getDeltaT() {
        return deltaT;
    }

    public static void setDeltaT(double deltaT) {
        WorldParameters.deltaT = deltaT;
    }

    public static Audio getBackgroundMusic() {
        return backgroundMusic;
    }

    public static void setBackgroundMusic(Audio backgroundMusic) {
        WorldParameters.backgroundMusic = backgroundMusic;
    }

    public static int getMAXSPEED() {
        return MAXSPEED;
    }

    public static void setMAXSPEED(int MAXSPEED) {
        WorldParameters.MAXSPEED = MAXSPEED;
    }

    public static double getJumpTime() {
        return jumpTime;
    }

    public static void setJumpTime(double jt) {
        jumpTime = jt;
    }

    public static int getGainVitesseX() {
        return gainVitesseX;
    }

    public static void setGainVitesseX(int gainVitesseX) {
        WorldParameters.gainVitesseX = gainVitesseX;
    }

    public static int getGainVitesseY() {
        return gainVitesseY;
    }

    public static void setGainVitesseY(int gainVitesseY) {
        WorldParameters.gainVitesseY = gainVitesseY;
    }

    public static int getxScroll() {
        return xScroll;
    }

    public static void setxScroll(int xScroll) {
        WorldParameters.xScroll = xScroll;
    }

    public static int getyScroll() {
        return yScroll;
    }

    public static void setyScroll(int yScroll) {
        WorldParameters.yScroll = yScroll;
    }

    public static int getBordBas() {
        return bordBas;
    }

    public static void setBordBas(int bordBas) {
        WorldParameters.bordBas = bordBas;
    }

    public static int getBordHaut() {
        return bordHaut;
    }

    public static void setBordHaut(int bordHaut) {
        WorldParameters.bordHaut = bordHaut;
    }

    public static int getBordGauche() {
        return bordGauche;
    }

    public static void setBordGauche(int bordGauche) {
        WorldParameters.bordGauche = bordGauche;
    }

    public static int getBordDroit() {
        return bordDroit;
    }

    public static void setBordDroit(int bordDroit) {
        WorldParameters.bordDroit = bordDroit;
    }
}