package game;

public class EnemyBall extends Enemy
{
	public EnemyBall(boolean left, int speed) 
	{
		super(left, speed);
		this.ypos = 450;
		if(left)
		{
			this.xpos = 1600;
			look = ImageLoader.loadImage("Feuerball");
		}
		else
		{
			this.xpos = -340;
			look = ImageLoader.loadImage("Feuerballgespiegelt");
		}
		
	}
	
	public int getExtraDamage()
	{
		return 10;
	}

}
