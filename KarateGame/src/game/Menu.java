package game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Menu {
	private Soundplayer sound;
	private BufferedImage background;
	private BufferedImage title;
	private Button[] buttons;
	private float x;
	private float xspeed;
	/**
	 * Konstruktor
	 */
	public Menu() {

		xspeed = 50;
		sound = new Soundplayer("sounds/menusound.wav");

		Font f = new Font("SansSerif", 0, 30);
		buttons = new Button[3];
		BufferedImage[] textures = { ImageLoader.loadImage("Button"), ImageLoader.loadImage("ButtonMaus"),
				ImageLoader.loadImage("ButtonGedrueckt") };

		buttons[0] = new Button(200, "Start Game", textures, f);
		buttons[1] = new Button(350, "Options", textures, f);
		buttons[2] = new Button(500, "Exit", textures, f);

		background = ImageLoader.loadImage("MenuBackground");
		title = ImageLoader.loadImage("Title");

	}
	/**
	 * Diese Methode zeichnet das Menu
	 * @param g
	 */
	public void draw(Graphics g) {
		g.drawImage(background, 0, 0, Main.width, Main.height, (int) -x, 0, (int) -x + Main.width, Main.height, null);
		g.drawImage(title, Main.width / 2 - title.getWidth() / 2, 30, null);
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].draw(g);
		}

	}
	/**
	 * Diese Methode macht das bewegliches Menu
	 * @param tslf
	 * @param e
	 */
	public void update(float tslf, Etage e) {
		x += xspeed * tslf;
		if (x > 0) {
			x = 0;
			xspeed *= -1;
		} else if (x < -background.getWidth() + Main.width) {
			x = -background.getWidth() + Main.width;
			xspeed *= -1;
		}
		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i].update()) {
				sound.play();
				if (i == 0) {
					Frame.state = 1;
					e.start();
				} else if (i == 1) {
					Frame.state = 3;
				} else if (i == 2) {
					e.stop();
					System.exit(0);
				}
			}
		}
	}
}
