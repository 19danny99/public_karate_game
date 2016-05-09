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
	
	public boolean IsDead()
	{
		return dead;
	}
	public void draw(Graphics g)
	{
		g.drawImage(look, (int) xpos, (int) ypos, null);
	}
	
	public void update(float tslf, Player p)
	{
		if(!hold || !CanGrab())
		{
			xpos += speed * tslf;
			hold = checkhold(p);
			if(hold)
			{
				if(CanGrab())
				{
					p.SetDamage(GetExtraDamage());
					p.setHold();
				}
				else
				{
					if(!p.GetCrouch())
					{
						p.SetDamage(GetExtraDamage());
						dead = true;
					}
				}
			}
		}
	}
	
	public int GetExtraDamage()
	{
		return 0;
	}
	
	public boolean CanGrab()
	{
		return false;
	}
	
	public boolean checkhold(Player p)
	{
		//if(speed > 0)
		{
			return ((xpos >= p.GetXPos()-100) && (xpos <= p.GetXPos()+100));
		}
		//else
		//{
		//	return ((xpos <= p.GetXPos()+100));
		//}
	}
	
	public boolean Hit(boolean left, Player p){
		return false;
	}
	
	public void Die(Player p){
		dead = true;
		if(hold)
		{
			p.unsetHold();
		}
	}
}
