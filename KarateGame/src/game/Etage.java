package game;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;;

public class Etage {
	private BufferedImage background;
	private Player player;
	private ArrayList<Enemy> enemies= new ArrayList<Enemy>();
	
	public Etage() {
		player = new Player(800, 450);
		background = ImageLoader.loadImage("Background");
		enemies.add(new EnemyNormal(true,200));
		enemies.add(new EnemyNormal(false,200));
	}
	
	public void draw(Graphics g) {
		g.drawImage(background, 0, 0, null);
		player.draw(g);
		g.drawString(player.debugInfo() ,20,20);
		enemies.forEach(e -> e.draw(g));
	}
	
	public void update(float tslf) {
		player.update(tslf);
		enemies.forEach(e -> e.update(tslf));
	}
	
	
}

