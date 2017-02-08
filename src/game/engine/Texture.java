package game.engine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;





public class Texture {
	public static Texture TexObstacles = chargementTexture("/files/textures.png");
	public static Texture brique = chargementTexture("/files/brique.png");
	//public static Texture joueur_texture_proto = chargementTexture("/files/gb_walk.png");
	
	int largeur, hauteur, id;

	public Texture(int largeur, int hauteur, int id) {
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.id = id;
	}

	public static Texture chargementTexture(String s) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(Texture.class.getResource(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int w = image.getWidth();
		int h = image.getHeight();
		int[] pixels = new int[w * h];
		image.getRGB(0, 0, w, h, pixels, 0, w);
		ByteBuffer buffer = BufferUtils.createByteBuffer(w * h * 4);
		
		for (int y = 0; y < w; y++) {
			for (int x = 0; x < h; x++) {
				int i = pixels[x + y * w];
				buffer.put((byte) ((i >> 16) & 0xFF));
				buffer.put((byte) ((i >> 8) & 0xFF));
				buffer.put((byte) ((i) & 0xFF));
				buffer.put((byte) ((i >> 24) & 0xFF));
			}
		}
		
		buffer.flip();
		
		int id = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, id);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, w, h, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
		
		return new Texture(w, h, id);
	}
	public Texture DecTexture(int t, int size , String s){
			BufferedImage image = null;
			URL entree;
			int larg, haut, startX, startY, scanSize, i, j, indice;
			int[] RGBArray;
			try {
				image = null;
				entree = Texture.class.getResource(s);
				image = ImageIO.read(entree);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			haut = image.getHeight();
			larg = image.getWidth();
			scanSize = larg;
			startX = 0;
			startY = 0;
			RGBArray = new int[larg * haut];
			image.getRGB(startX, startY, larg, haut, RGBArray, 0, size);
			ByteBuffer buffer = BufferUtils.createByteBuffer(larg * haut * 4);

			for (j = 0; j < larg; j++) {
				for (i = 0; i < haut; i++) {
					indice = RGBArray[i + j * scanSize];

					buffer.put((byte) ((indice) & 0xFF));
					buffer.put((byte) ((indice >> 8) & 0xFF));
					buffer.put((byte) ((indice >> 16) & 0xFF));
					buffer.put((byte) ((indice >> 24) & 0xFF));
	/*
					buffer.put((byte) (((RGBArray[i + j * scanSize])) & 0xFF));
					buffer.put((byte) (((RGBArray[i + j * scanSize]) >> 8) & 0xFF));
					buffer.put((byte) (((RGBArray[i + j * scanSize]) >> 16) & 0xFF));
					buffer.put((byte) (((RGBArray[i + j * scanSize]) >> 24) & 0xFF));*/

				}
			}

			 buffer.flip();

			int id = glGenTextures();
			glBindTexture(GL_TEXTURE_2D, id);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, larg, haut, 0, GL_RGBA,
					GL_UNSIGNED_BYTE, buffer);

			return new Texture(larg, haut, id);
		
	}

	public void lier() {
		glBindTexture(GL_TEXTURE_2D,id);
	}

	public void delier() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}

}
