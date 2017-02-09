package game;

/**
 * Created by wissamemekhilef on 09/02/2017.
 */
public class GameParameters {
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
        GameParameters.deltaT = deltaT;
    }

    public static String getPathToBackgroundMusic() {
        return pathToBackgroundMusic;
    }

    public static void setPathToBackgroundMusic(String pathToBackgroundMusic) {
        GameParameters.pathToBackgroundMusic = pathToBackgroundMusic;
    }

    public static int getMAXSPEED() {
        return MAXSPEED;
    }

    public static void setMAXSPEED(int MAXSPEED) {
        GameParameters.MAXSPEED = MAXSPEED;
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

    public static void setForwardPathToTexture(String forwardPathToTexture) {
        GameParameters.forwardTexture = forwardPathToTexture;
    }

    public static String getBackwardTexture() {
        return backwardTexture;
    }

    public static void setBackwardPathToTexture(String backwardPathToTexture) {
        GameParameters.backwardTexture = backwardPathToTexture;
    }

    public static int getGainVitesseX() {
        return gainVitesseX;
    }

    public static void setGainVitesseX(int gainVitesseX) {
        GameParameters.gainVitesseX = gainVitesseX;
    }

    public static int getGainVitesseY() {
        return gainVitesseY;
    }

    public static void setGainVitesseY(int gainVitesseY) {
        GameParameters.gainVitesseY = gainVitesseY;
    }
}