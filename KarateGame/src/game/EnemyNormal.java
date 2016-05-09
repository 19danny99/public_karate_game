package game;

public class EnemyNormal extends Enemy
{
	public EnemyNormal(boolean left, int speed) 
	{
		super(left, speed);
		this.ypos = 450;
		if(left)
		{
			this.xpos = 1600;
			look = ImageLoader.loadImage("GegnerLinksStehend");
		}
		else
		{
			this.xpos = -340;
			look = ImageLoader.loadImage("GegnerRechtsStehend");
		}
		
	}
	
	public boolean CanGrab()
	{
		return true;
	}
	
	public boolean Hit(boolean left, Player p)
	{
		if(left && speed > 0)
		{
			return xpos >= p.GetXPos()-150;
		}
		if(!left && speed < 0)
		{
			return xpos <= p.GetXPos()+150;
		}
		return false;
	}
	
}
