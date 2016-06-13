package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Enemy {
	protected float xpos;
	protected float ypos;
	protected int speed;
	protected BufferedImage look;
	private boolean hold = false;
	private boolean dead = false;
	
	public Enemy(boolean left, int speed)
	{	
		this.speed = left ? -speed : speed;
	}
	
	public boolean isDead()
	{
		return dead;
	}
	public void draw(Graphics g)
	{
		g.drawImage(look, (int) xpos, (int) ypos, null);
	}
	
	public void update(float tslf, Player p)
	{
		if(!hold || !canGrab())
		{
			xpos += speed * tslf;
			hold = checkhold(p);
			if(hold)
			{
				if(canGrab())
				{
					p.setDamage(getExtraDamage());
					p.setHold();
				}
				else
				{
					if(!p.getCrouch())
					{
						p.setDamage(getExtraDamage());
						dead = true;
					}
				}
			}
		}
	}
	
	public int getExtraDamage()
	{
		return 0;
	}
	
	public boolean canGrab()
	{
		return false;
	}
	
	public boolean checkhold(Player p)
	{
		//if(speed > 0)
		{
			return ((xpos >= p.getXPos()-100) && (xpos <= p.getXPos()+100));
		}
		//else
		//{
		//	return ((xpos <= p.GetXPos()+100));
		//}
	}
	
	public boolean hit(boolean left, Player p){
		return false;
	}
	
	public void die(Player p){
		dead = true;
		if(hold)
		{
			p.unsetHold();
		}
	}
}
