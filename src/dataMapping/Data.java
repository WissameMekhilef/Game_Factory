package dataMapping;

import org.newdawn.slick.Font;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.awt.Font.TRUETYPE_FONT;
import static java.awt.Font.createFont;

public class Data {

	public static final String[] folders = new String[]{"fonts", "images/skins", "images/textures", "sounds"};

	public static Map<String , Audio> soundsMap = new HashMap<>();
    public static Map<String , Font> fontsMap = new HashMap<>();
    public static Map<String , Texture> texturesMap = new HashMap<>();
    public static Map<String , Texture[]> skinsMap = new HashMap<>();

    public static Runnable generateFonts(){
        return () -> {
            try {
                //System.out.println("Thread : "+Thread.currentThread().getName());
                Stream<Path> paths1 = Files.walk(Paths.get(folders[0]));
                paths1.forEach((Path filePath1) -> {
                    //System.out.println(Thread.currentThread().getName()+" : generateFonts : "+filePath1);
                    if (Files.isRegularFile(filePath1)) {
                        String fileName1 = getFileName(filePath1);
                        try {
                            fontsMap.put(fileName1, createTTF(folders[0], fileName1));
                        } catch (FontFormatException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                paths1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    public static Runnable generateTextures(){
        return () -> {
            try {
                //System.out.println("Thread : "+Thread.currentThread().getName());
                Stream<Path> paths2 = Files.walk(Paths.get(folders[2]));
                paths2.forEach((Path filePath2) -> {
                    //System.out.println(Thread.currentThread().getName()+" : generateTextures : "+filePath2);
                    if (Files.isRegularFile(filePath2)) {
                        String fileName2 = getFileName(filePath2);
                        try {
                            texturesMap.put(fileName2, createTexture(folders[2], fileName2));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                paths2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    public static Runnable generateSkins(){
        return () -> {
            try {
                //System.out.println("Thread : "+Thread.currentThread().getName());
                Stream<Path> paths3 = Files.walk(Paths.get(folders[1]));
                paths3.forEach(filePath3 -> {
                    //System.out.println(Thread.currentThread().getName()+" : generateSkins : "+filePath3);
                    if (Files.isRegularFile(filePath3)) {
                        String fileName3 = getFileName(filePath3);
                        try {
                            if(!fileName3.contains("_b"))
                                skinsMap.put(fileName3, createSkin(folders[1], fileName3));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                paths3.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    public static Runnable generateSounds(){
        return () -> {
            try {
                //System.out.println("Thread : "+Thread.currentThread().getName());
                Stream<Path> paths4 = Files.walk(Paths.get(folders[3]));
                paths4.forEach(filePath4 -> {
                    //System.out.println(Thread.currentThread().getName()+" : generateSounds : "+filePath4);
                    if (Files.isRegularFile(filePath4)) {
                        String fileName4 = getFileName(filePath4);

                        try {
                            soundsMap.put(fileName4, createAudio(folders[3], fileName4));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                paths4.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
