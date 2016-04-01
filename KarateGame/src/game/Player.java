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
	private boolean left = true;
	private boolean schlagen = false;
	private boolean treten = false;
	private boolean ducken = false;
	
	public Player(int xpos, int ypos) {
		this.xpos = xpos;
		this.ypos = ypos;
		
		
		look = ImageLoader.loadImage("LinksStehend");
	}
	
	public String debugInfo()
	{
		String ret = "";
		ret += "Face: ";
		ret += left ? "left" : "right";
		ret += " Crouch: ";
		ret += ducken ? "yes" : "no";
		
		ret += " Kick: ";
		ret += treten ? "yes" : "no";
		
		ret += " Punch: ";
		ret += schlagen ? "yes" : "no";
		
		
		return ret;
	}
	
	public void draw(Graphics g) {
		g.drawImage(look, (int) xpos, (int) ypos, null);
	}
	
	public void update(float tslf) {
		xspeed = 0;
		yspeed = 0;
		String image;
		
		schlagen = false;
		treten = false;
		ducken = false;
		if(Keyboard.isKeyPressed(KeyEvent.VK_S) || Keyboard.isKeyPressed(KeyEvent.VK_DOWN))
		{
			image = left ? "LinksDuckend" : "RechtsDuckend";
			ducken = true;
			
		}
		else if (Keyboard.isKeyPressed(KeyEvent.VK_D) || Keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
			image = "RechtsLaufend";
			xspeed = 200;
			left = false;
		}
		else if (Keyboard.isKeyPressed(KeyEvent.VK_A) || Keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
			image = "LinksLaufend";
			xspeed = -200;
			left = true;
		}
		else if (Keyboard.isKeyPressed(KeyEvent.VK_SPACE))
		{
			image = left ? "LinksTretend" : "RechtsTretend";
			treten = true;
		}
		else if (Keyboard.isKeyPressed(KeyEvent.VK_M)) 
		{
			image = left ? "LinksSchlagend" : "RechtsSchlagend";
			schlagen = true;
		}
		else 
		{
			image = left ? "LinksStehend" : "RechtsStehend";
		}
		look = ImageLoader.loadImage(image);
		
		xpos += xspeed * tslf;
		ypos += yspeed * tslf;
		
		if (xpos < 0 ) xpos = 0;
		if (xpos + width > Main.width) xpos = Main.width - width;
		if (ypos < 0 ) ypos = 0;
		if (ypos + height > Main.height) ypos = Main.height - height;
	}
}
