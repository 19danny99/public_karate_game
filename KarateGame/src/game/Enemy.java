package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Enemy {
	protected float xpos;
	protected float ypos;
	private int speed;
	protected BufferedImage look;
	
	
	
	public Enemy(boolean left, int speed)
	{	
		this.speed = left ? -speed : speed;
	}
	
	
	public void draw(Graphics g)
	{
		g.drawImage(look, (int) xpos, (int) ypos, null);
	}
	
	public void update(float tslf)
	{
		xpos += speed * tslf;
	}
}
