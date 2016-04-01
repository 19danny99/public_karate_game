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
			look = ImageLoader.loadImage("LinksStehend");
		}
		else
		{
			this.xpos = -340;
			look = ImageLoader.loadImage("RechtsStehend");
		}
		
	}
	
}
