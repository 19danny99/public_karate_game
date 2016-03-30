package game;

import java.awt.image.BufferedImage;
import java.io.IOException;

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
}
