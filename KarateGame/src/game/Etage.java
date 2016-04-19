package game;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;;

public class Etage {
	private BufferedImage background;
	private Player player;
	private ArrayList<Enemy> enemies= new ArrayList<Enemy>();
	
	private Timer timer = new Timer();
	private int enemySpeed = 200;
	private int enemyCount = 0;
	
	class AddEnemyTask extends TimerTask
	{
	  @Override public void run()
	  {
		  double rand = Math.random();
		  if(rand < 0.5)
		  {
			  synchronized (enemies)
			  {
				  enemies.add(new EnemyNormal(true,enemySpeed));
			  }
		  }
		  else
		  {
			  synchronized (enemies)
			  {
				  enemies.add(new EnemyNormal(false,enemySpeed));
			  }
		  }
		  ++enemyCount;
		  if(enemyCount % 10 == 0)
		  { 
			  enemyCount = 0;
			  enemySpeed++;
		  }
		  int newDelay = (int)(Math.random() * 5000D) + 500;
		  timer.schedule(new AddEnemyTask(), newDelay);
	  }
	}
	
	public Etage() {
		player = new Player(800, 450);
		background = ImageLoader.loadImage("Background");
		timer.schedule(new AddEnemyTask(), 1000);
		
	}
	
	
	
	public void draw(Graphics g) {
		g.drawImage(background, 0, 0, null);
		player.draw(g);
		g.drawString(player.debugInfo() ,20,20);
		synchronized (enemies)
		{
			enemies.forEach(e -> e.draw(g));
		}
	}
	
	public void update(float tslf) {
		player.update(tslf);
		synchronized (enemies)
		  {
			for(int i = enemies.size() - 1; i >= 0; --i)
			{
				Enemy e = enemies.get(i);
				e.update(tslf);
				if(e.xpos > 1600 || e.xpos < -340)
				{
					enemies.remove(i);
				}
			}
		  }
	}
	
	
}

