package game;

/**
 * Created by wissamemekhilef on 09/02/2017.
 */
public class WorldParameters {
    //BEGIN PHYSICS
    private static double Gamma = 20.81;
    private static double G = 4;
    private static double deltaT = 1.0 / 60.0;
    //END PHYSICS

    //BEGIN SOUNDS
    private static String pathToBackgroundMusic = "sounds/CantLetGo.ogg";
    //END SOUNDS

    //BEGIN PLAYER
    private static int MAXSPEED = 7;
    private static double jumpTime = 1000;
    private static String forwardTexture = "player_1_f";
    private static String backwardTexture = "player_1_b";
    private static int gainVitesseX = 2;
    private static int gainVitesseY = -20;
    //END PLAYER

    //BEGIN SCROLL
    private static int xScroll = 0;
    private static int yScroll = 0;
    //END SCROLL

    //BEGIN LEVEL BOUND$
    private static int bordBas = 0;
    private static int bordHaut = 0;
    private static int bordGauche = 0;
    private static int bordDroit = 0;
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

    public static String getPathToBackgroundMusic() {
        return pathToBackgroundMusic;
    }

    public static void setPathToBackgroundMusic(String pathToBackgroundMusic) {
        WorldParameters.pathToBackgroundMusic = pathToBackgroundMusic;
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

    public static void setJumpTime(double jumpTime) {
        jumpTime = jumpTime;
    }

    public static String getForwardTexture() {
        return forwardTexture;
    }

    public static void setForwardTexture(String forwardTexture) {
        WorldParameters.forwardTexture = forwardTexture;
    }

    public static void setForwardPathToTexture(String forwardPathToTexture) {
        WorldParameters.forwardTexture = forwardPathToTexture;
    }

    public static String getBackwardTexture() {
        return backwardTexture;
    }

    public static void setBackwardTexture(String backwardTexture) {
        WorldParameters.backwardTexture = backwardTexture;
    }

    public static void setBackwardPathToTexture(String backwardPathToTexture) {
        WorldParameters.backwardTexture = backwardPathToTexture;
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