package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.sun.glass.events.KeyEvent;


public class Player implements Comparable<Player>{
	private Soundplayer sound;
	private Soundplayer sound1;
	private Soundplayer sound2;
	private float xpos;
	private float ypos;
	private int xspeed;
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
	String name;
	
	public Player(int xpos, int ypos) {
		this.xpos = xpos;
		this.ypos = ypos;
		
		
		look = ImageLoader.loadImage("LinksStehend");
		
		sound =  new Soundplayer("sounds/Punch.wav");
		sound1 = new Soundplayer("sounds/schlagen.wav");
		sound2 = new Soundplayer("sounds/schlagen2.wav");
	}
	
	public Player(String name, String score) {
		this.name = name;
		score = Float.toString(this.score);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	public boolean getCrouch()
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
	
	public float getLife()
	{
		return life;
	}
	
	public float getXPos()
	{
		return xpos;
	}
	public int getScore()
	{
		return (int)score;
	}
	public float getscore() {
		return score;
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
	
	public void setDamage(int damage)
	{
		life -= damage;
	}
	
	public void update(float tslf, ArrayList<Enemy> e) {
		xspeed = 0;
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
		else if (Keyboard.isKeyPressed(KeyEvent.VK_Y) || Keyboard.isKeyPressed(KeyEvent.VK_N))
		{
			if(!sound.isPlaying())
				sound.play();
			image = left ? "LinksTretend" : "RechtsTretend";
			
			treten = true;
	
		}
		else if (Keyboard.isKeyPressed(KeyEvent.VK_M) || Keyboard.isKeyPressed(KeyEvent.VK_X)) 
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
					if(oneEnemy.hit(left, this))
					{
						oneEnemy.die(this);
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
		
		width = look.getWidth();
		height = look.getHeight();
		
		if (xpos < 0 ) xpos = 0;
		if (xpos + width > Main.width) xpos = Main.width - width;
		if (ypos < 0 ) ypos = 0;
		if (ypos + height > Main.height) ypos = Main.height - height;
	}

	@Override
	public int compareTo(Player p)
	{
		// TODO Auto-generated method stub
		return getName().compareTo(p.getName());
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (name == null)
		{
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
