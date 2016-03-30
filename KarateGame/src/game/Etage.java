package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Etage {
	private BufferedImage background;
	private Player player;
	
	public Etage() {
		player = new Player(800, 450);
		background = ImageLoader.loadImage("Background");
	}
	
	public void draw(Graphics g) {
		g.drawImage(background, 0, 0, null);
		player.draw(g);
	}
	
	public void update(float tslf) {
		player.update(tslf);
	}
}
