package dataMapping;

import static java.awt.Font.TRUETYPE_FONT;
import static java.awt.Font.createFont;

import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Stream;

import org.newdawn.slick.Font;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Data {

	public static String[] folders = new String[]{"fonts", "images/skins", "images/textures", "sounds"};

	public static Map<String , Audio> soundsMap = new HashMap<>();
    public static Map<String , Font> fontsMap = new HashMap<>();
    public static Map<String , Texture> texturesMap = new HashMap<>();
    public static Map<String , Texture[]> skinsMap = new HashMap<>();

    public static ExecutorService service =  Executors.newFixedThreadPool(4);

    public static boolean doneFonts = false;
    public static boolean doneSkins = false;
    public static boolean doneTextures = false;
    public static boolean doneSounds = false;

    public Data() {
        System.out.println("DEBUG");

        try {
            generateFonts().call();
            generateSkins().call();
            generateSounds().call();
            generateTextures().call();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*service.submit(generateFonts());
        service.submit(generateTextures());
        service.submit(generateSkins());
        service.submit(generateSounds());*/

        /*List<Callable<Void>> callables = new ArrayList<>();
        callables.add(generateFonts());
        //callables.add(generateTextures());
        //callables.add(generateSkins());
        //callables.add(generateSounds());
        try {
            service.invokeAll(callables, 60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public static Callable<Void> generateFonts(){
        return () -> {
            try {
                Stream<Path> paths = Files.walk(Paths.get(folders[0]));
                paths.forEach(filePath -> {
                    if (Files.isRegularFile(filePath)) {
                        String fileName = getFileName(filePath);
                        System.out.println("generateFonts : "+fileName);
                        try {
                            fontsMap.put(fileName, createTTF(folders[0], fileName));
                        } catch (FontFormatException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                paths.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Data.doneFonts = true;
            return null;
        };
    }

    public static Callable<Void> generateTextures(){
        return () -> {
            try {
                Stream<Path> paths = Files.walk(Paths.get(folders[2]));
                paths.forEach(filePath -> {
                    if (Files.isRegularFile(filePath)) {
                        String fileName = getFileName(filePath);
                        System.out.println("generateTextures : "+fileName);
                        try {
                            texturesMap.put(fileName, createTexture(folders[2], fileName));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                paths.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Data.doneTextures = true;
            return null;
        };
    }

    public static Callable<Void> generateSkins(){
        return () -> {
            try {
                Stream<Path> paths = Files.walk(Paths.get(folders[1]));
                paths.forEach(filePath -> {
                    if (Files.isRegularFile(filePath)) {
                        String fileName = getFileName(filePath);
                        System.out.println("generateSkins : "+fileName);
                        try {
                            if(!fileName.contains("_b"))
                                skinsMap.put(fileName, createSkin(folders[1], fileName));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                paths.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Data.doneSkins = true;
            return null;
        };
    }

    public static Callable<Void> generateSounds(){
        return () -> {
            try {
                Stream<Path> paths = Files.walk(Paths.get(folders[3]));
                paths.forEach(filePath -> {
                    if (Files.isRegularFile(filePath)) {
                        String fileName = getFileName(filePath);
                        System.out.println("generateSounds : "+fileName);
                        try {
                            soundsMap.put(fileName, createAudio(folders[3], fileName));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                paths.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Data.doneSounds = true;
            return null;
        };
    }

    public static Audio createAudio(String folder, String fileName) throws IOException {
    	InputStream stream = ResourceLoader.getResourceAsStream(folder + "/" + fileName + ".ogg");
    	return AudioLoader.getAudio("OGG", stream);
    }

    public static Texture createTexture(String folder, String fileName) throws IOException {
    	InputStream stream = ResourceLoader.getResourceAsStream(folder + "/" + fileName + ".png");
    	return TextureLoader.getTexture("PNG", stream);
    }

    public static TrueTypeFont createTTF(String folder, String fileName) throws FontFormatException, IOException {
    	InputStream stream = ResourceLoader.getResourceAsStream(folder + "/" + fileName + ".ttf");
		return new TrueTypeFont(createFont(TRUETYPE_FONT, stream).deriveFont(46f), false);
    }

    public static Texture[] createSkin(String folder, String fileName) throws IOException {
    	InputStream stream = ResourceLoader.getResourceAsStream(folder + "/" + fileName + ".png");
    	InputStream stream2 = ResourceLoader.getResourceAsStream(folder + "/" + fileName + "_b.png");
    	return new Texture[]{TextureLoader.getTexture("PNG", stream) , TextureLoader.getTexture("PNG", stream2)};
    }

    public static String getFileName(Path filePath) {
    	String str = filePath.toString().split("\\.")[0];
    	String[] array = str.split("/");
    	if(array.length > 1) {
    		//Cas où le chemin est de la forme : "dossier1/dossier2/.../fichier.extension" (Unix)
    		return array[array.length - 1];
    	} else {
    		//Cas où le chemin est de la forme : "dossier1\\dossier2\\...\\fichier.extension" (Windows)
    		array = str.split("\\\\");
    		return array[array.length - 1];
    	}
    }
}
