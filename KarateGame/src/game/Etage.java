package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;;

public class Etage {
	private BufferedImage background;
	private Player player;
	private ArrayList<Enemy> enemies= new ArrayList<Enemy>();
	private Font fpsFont;
	private Font highscoreFont;
	
	private Timer timer = new Timer();
	private int enemySpeed = 200;
	private int enemyCount = 0;
	private int baseDelay = 3000;
	private boolean gameOver = false;
	
	class AddEnemyTask extends TimerTask
	{
	  @Override public void run()
	  {
		  double rand = Math.random();
		  double rand2 = Math.random();
		  if(rand < 0.6)
		  {
			  synchronized (enemies)
			  {
				  if(rand2 < 0.3)
				  {
					  enemies.add(new EnemyBall(true,enemySpeed*2));
				  }
				  else
				  {
					  enemies.add(new EnemyNormal(true,enemySpeed));
				  }
			  }
		  }
		  else
		  {
			  synchronized (enemies)
			  {
				  if(rand2 < 0.3)
				  {
					  enemies.add(new EnemyBall(false,enemySpeed*2));
				  }
				  else
				  {
					  enemies.add(new EnemyNormal(false,enemySpeed));
				  }
			  }
		  }
		  ++enemyCount;
		  if(enemyCount % 5 == 0)
		  { 
			  enemyCount = 0;
			  enemySpeed++;
			  if(baseDelay > 0)
			  {
				  baseDelay -= 100;
			  }
		  }
		  int newDelay = (int)(Math.random() * (float)baseDelay) + 500;
		  timer.schedule(new AddEnemyTask(), newDelay);
	  }
	}
	
	public void stop()
	{
		timer.cancel();
		enemies.clear();
		player = null;
	}
	
	public void start()
	{
		player = new Player(800, 450);
		timer.schedule(new AddEnemyTask(), 1000);
	}
	
	public Etage() {
		//player = new Player(800, 450);
		background = ImageLoader.loadImage("Background");
		//timer.schedule(new AddEnemyTask(), 1000);
		highscoreFont = ImageLoader.loadFont("DIGITALE", 30);
		fpsFont = ImageLoader.loadFont("DIGITALE", 15);
	}
	
	public boolean getGameOver()
	{
		return gameOver;
	}
	
	public int getFinalScore()
	{
		return player.getScore();
	}
	
	public void draw(Graphics g) {
		g.drawImage(background, 0, 0, null);
		player.draw(g);
		//g.setFont(fpsFont);
		//g.drawString(player.debugInfo() ,20,20);
		g.setFont(highscoreFont);
		g.setColor(Color.white);
		g.drawString(Integer.toString(player.getScore()) , 10,120);
		g.setColor(Color.yellow);
		synchronized (enemies)
		{
			enemies.forEach(e -> e.draw(g));
		}
		int currentLifeShow = (int)(player.getLife() * 2);
		g.fillRect(1550, 150 + (100 - currentLifeShow), 30, currentLifeShow);
	}
	
	public void update(float tslf) {
		player.update(tslf, enemies);
		if(player.getLife() <= 0)
		{
			timer.cancel();
			gameOver=true;
			enemies.clear();
			return;
		}
		synchronized (enemies)
		  {
			for(int i = enemies.size() - 1; i >= 0; --i)
			{
				Enemy e = enemies.get(i);
				if(e.isDead())
				{
					enemies.remove(i);
				}
				else
				{
					e.update(tslf, player);
					if(e.xpos > 1600 || e.xpos < -340)
					{
						enemies.remove(i);
					}
				}
			}
		  }
	}
	
	
}

