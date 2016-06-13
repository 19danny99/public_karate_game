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
	/**
	 * Diese Methode schaut ob ein Enemy tot ist
	 * @return dead
	 */
	public boolean isDead()
	{
		return dead;
	}
	/**
	 * Diese Methode zeichnet den Gegner
	 * @param g
	 */
	public void draw(Graphics g)
	{
		g.drawImage(look, (int) xpos, (int) ypos, null);
	}
	/**
	 * Diese Methode setzt den damage ,den speed und 
	 * @param tslf
	 * @param p
	 */
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
	/**
	 * Diese methode setzt den extradamage
	 * @return 0
	 */
	public int getExtraDamage()
	{
		return 0;
	}
	/**
	 * Diese Methode beschreibt ob der gegner den Player grabben kann
	 * @return false
	 */
	public boolean canGrab()
	{
		return false;
	}
	/**
	 * Diese Methode beschreibt 
	 * @param p
	 * @return
	 */
	public boolean checkhold(Player p)
	{
		
		{
			return ((xpos >= p.getXPos()-100) && (xpos <= p.getXPos()+100));
		}
		
	}
	/**
	 * Diese Methode schaut ob der Player schlägt
	 * @param left
	 * @param p
	 * @return
	 */
	public boolean hit(boolean left, Player p){
		return false;
	}
	/**
	 * Diese Methode shcaut ob ein Enemy gestorben ist
	 * @param p
	 */
	public void die(Player p){
		dead = true;
		if(hold)
		{
			p.unsetHold();
		}
	}
}
