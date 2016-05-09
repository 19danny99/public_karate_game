package game;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static BufferedImage loadImage(String name) {

		try {
			return ImageIO.read(ImageLoader.class.getClassLoader().getResourceAsStream("gfx/" + name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Font loadFont(String name, int size) {

		try {
			InputStream myStream = ImageLoader.class.getClassLoader().getResourceAsStream("gfx/" + name + ".ttf");
			Font baseFont = Font.createFont(Font.TRUETYPE_FONT, myStream);
			return baseFont.deriveFont(Font.PLAIN, size);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
