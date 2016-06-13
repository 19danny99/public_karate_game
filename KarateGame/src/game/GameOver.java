package game;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;

import java.awt.image.BufferedImage;
public class GameOver {
	private BufferedImage background;
	private Font gameOverFont;
	private Font highscoreFont;
	
	
	
	
	public GameOver() {
		background = ImageLoader.loadImage("Background");
		highscoreFont = ImageLoader.loadFont("DIGITALE", 30);
		gameOverFont = ImageLoader.loadFont("DIGITALE", 60);
	}
	
	public void draw(Graphics g, int score) {
		g.drawImage(background, 0, 0, null);
		g.setColor(Color.red);
		g.setFont(gameOverFont);
		g.drawString("Game Over" , 750 , 300);
		g.setFont(highscoreFont);
		g.setColor(Color.white);
		g.drawString(Integer.toString(score) , 780, 350);
	}
	
	public void update(float tslf)
	{
	}
	
	
}


