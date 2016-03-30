package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.sun.glass.events.KeyEvent;

public class Player {
	private float xpos;
	private float ypos;
	private int xspeed;
	private int yspeed;
	private int width;
	private int height;
	private BufferedImage look;
	
	public Player(int xpos, int ypos) {
		this.xpos = xpos;
		this.ypos = ypos;
		
		look = ImageLoader.loadImage("LinksStehend");
	}
	
	public void draw(Graphics g) {
		g.drawImage(look, (int) xpos, (int) ypos, null);
	}
	
	public void update(float tslf) {
		xspeed = 0;
		yspeed = 0;
		if(Keyboard.isKeyPressed(KeyEvent.VK_W) || Keyboard.isKeyPressed(KeyEvent.VK_UP)) look = ImageLoader.loadImage("LinksStehend");
		if(Keyboard.isKeyPressed(KeyEvent.VK_S) || Keyboard.isKeyPressed(KeyEvent.VK_DOWN)) look = ImageLoader.loadImage("LinksDuckend");
		if (Keyboard.isKeyPressed(KeyEvent.VK_D) || Keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
			look = ImageLoader.loadImage("RechtsLaufend");
			xspeed = 200;
		}
		if (Keyboard.isKeyPressed(KeyEvent.VK_A) || Keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
			look = ImageLoader.loadImage("LinksLaufend");
			xspeed = -200;
		}
		
		if (Keyboard.isKeyPressed(KeyEvent.VK_SPACE)) look = ImageLoader.loadImage("LinksTretend");
		if (Keyboard.isKeyPressed(KeyEvent.VK_M)) look = ImageLoader.loadImage("LinksSchlagend");
		
		xpos += xspeed * tslf;
		ypos += yspeed * tslf;
		
		if (xpos < 0 ) xpos = 0;
		if (xpos + width > Main.width) xpos = Main.width - width;
		if (ypos < 0 ) ypos = 0;
		if (ypos + height > Main.height) ypos = Main.height - height;
	}
}
