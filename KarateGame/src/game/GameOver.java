package game;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;

import java.awt.image.BufferedImage;
public class GameOver {
	private BufferedImage background;
	private Soundplayer sound;
	private Button back;
	private Font gameOverFont;
	private Font highscoreFont;
	
	
	
	/**
	 * Konstruktor
	 */
	public GameOver() {
		Font f = new Font("SansSerif", 0, 30);
		BufferedImage[] textures = { ImageLoader.loadImage("Button"), ImageLoader.loadImage("ButtonMaus"),
				ImageLoader.loadImage("ButtonGedrueckt") };
		
		background = ImageLoader.loadImage("Background");
		highscoreFont = ImageLoader.loadFont("DIGITALE", 30);
		gameOverFont = ImageLoader.loadFont("DIGITALE", 60);
		sound = new Soundplayer("sounds/menusound.wav");
		back = new Button(600, "Back", textures, f);
	}
	/**
	 * 	Diese Methode zeichnet den GameOver screen
	 * @param g
	 * @param score
	 */
	public void draw(Graphics g, int score) {
		g.drawImage(background, 0, 0, null);
		g.setColor(Color.red);
		g.setFont(gameOverFont);
		g.drawString("Game Over" , 750 , 300);
		g.setFont(highscoreFont);
		g.setColor(Color.white);
		g.drawString(Integer.toString(score) , 780, 350);
		back.draw(g);
	}
	
	public void update(float tslf) {
		if (back.update()) {
			sound.play();
			Frame.state = 0;
		}
	}
	
	
}


