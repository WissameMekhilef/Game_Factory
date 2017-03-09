package engine;

import org.newdawn.slick.openal.Audio;

public class Sound {

    private static Audio backgroundSound;
    private static float backgroundPosition = 0;

    public Sound() {}

    /**
     * Lance la musique jouée précédemment.
     * Si la musique avait été mise en pause, elle reprend là où elle s'était coupée.
     */
    public static void play() {
    	//backgroundSound.playAsMusic(1.0f, 1.0f, false);
    	//backgroundSound.setPosition(backgroundPosition);
    }

    /**
     * Joue une musique.
     * @param pBackgroundSound	la musique à lancer
     */
    public static void play(Audio pBackgroundSound) {
        stop();
        backgroundSound = pBackgroundSound;
        play();
    }

    /**
     * Arrête la musique.
     */
    public static void stop() {
        if (backgroundSound != null) {
            backgroundSound.stop();
            backgroundPosition = 0;
        }
    }

    /**
     * Met la musique en pause.
     * Cette méthode permet ensuite à la musique de reprendre là où elle s'était coupée.
     */
    public static void pause() {
        backgroundPosition = backgroundSound.getPosition();
        backgroundSound.stop();
    }

}
