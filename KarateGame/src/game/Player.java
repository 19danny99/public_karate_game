package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.sun.glass.events.KeyEvent;


public class Player {
	private Soundplayer sound;
	private Soundplayer sound1;
	private Soundplayer sound2;
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
	private float life = 100;
	private int hold = 0;
	private float score = 0;
	
	
	public Player(int xpos, int ypos) {
		this.xpos = xpos;
		this.ypos = ypos;
		
		
		look = ImageLoader.loadImage("LinksStehend");
		
		sound =  new Soundplayer("sounds/Punch.wav");
		sound1 = new Soundplayer("sounds/schlagen.wav");
		sound2 = new Soundplayer("sounds/schlagen2.wav");
	}
	
	public boolean GetCrouch()
	{
		return ducken;
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
		ret += " life: " + life;
		ret += " score: " + (int)score;
		
		
		return ret;
	}
	
	public float GetLife()
	{
		return life;
	}
	
	public float GetXPos()
	{
		return xpos;
	}
	public int GetScore()
	{
		return (int)score;
	}
	public void setHold()
	{
		hold++;
	}
	
	public void unsetHold()
	{
		hold--;
	}
	
	public void draw(Graphics g) {
		g.drawImage(look, (int) xpos, (int) ypos, null);
	}
	
	public void SetDamage(int damage)
	{
		life -= damage;
	}
	
	public void update(float tslf, ArrayList<Enemy> e) {
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
			if(hold==0)
			{
			xspeed = 200;
			}
			left = false;
		}
		else if (Keyboard.isKeyPressed(KeyEvent.VK_A) || Keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
			image = "LinksLaufend";
			if(hold==0)
			{
			xspeed = -200;
			}
			left = true;
		}
		else if (Keyboard.isKeyPressed(KeyEvent.VK_SPACE))
		{
			if(!sound.isPlaying())
				sound.play();
			image = left ? "LinksTretend" : "RechtsTretend";
			
			treten = true;
	
		}
		else if (Keyboard.isKeyPressed(KeyEvent.VK_M)) 
		{
			if(!sound1.isPlaying() && !sound2.isPlaying())
			{
				if(Math.random() < 0.5)
				{
					sound1.play();
				}
				else
				{
					sound2.play();	
				}
			}
			image = left ? "LinksSchlagend" : "RechtsSchlagend";
				
			schlagen = true;
			
		}
		else 
		{
			image = left ? "LinksStehend" : "RechtsStehend";
		}
		look = ImageLoader.loadImage(image);
		if(treten || schlagen){
			synchronized (e)
			  {
				for(int i = e.size() - 1; i >= 0; --i)
				{
					Enemy oneEnemy = e.get(i);
					if(oneEnemy.Hit(left, this))
					{
						oneEnemy.Die(this);
						score += 100;
					}
				}
			  }
		}
		if( hold>0)
		{
			life -= (tslf * hold);
			if(life < 0)
			{
				life = 0;
			}
			
		}
		else
		{
			score+= tslf*100;
		}
		
		xpos += xspeed * tslf;
		ypos += yspeed * tslf;
		
		width = look.getWidth();
		height = look.getHeight();
		
		if (xpos < 0 ) xpos = 0;
		if (xpos + width > Main.width) xpos = Main.width - width;
		if (ypos < 0 ) ypos = 0;
		if (ypos + height > Main.height) ypos = Main.height - height;
	}
}
