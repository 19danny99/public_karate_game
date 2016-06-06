package game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JSlider;

public class Options {
	private Soundplayer sound;
	private BufferedImage background;
	private BufferedImage title;
	private JSlider slider;
	private Button button;
	private float x;
	private float xspeed;

	public Options() {
		xspeed = 50;
		sound = new Soundplayer("sounds/menusound.wav");

		Font f = new Font("SansSerif", 0, 30);

		BufferedImage[] textures = { ImageLoader.loadImage("Button"), ImageLoader.loadImage("ButtonMaus"),
				ImageLoader.loadImage("ButtonGedrueckt") };

		button = new Button(500, "Back", textures, f);
		background = ImageLoader.loadImage("MenuBackground");
		title = ImageLoader.loadImage("Title");
		
		slider = new JSlider();
		slider.setMinimum(0);
		slider.setMaximum(100);
		slider.setValue(30);
		slider.setMinorTickSpacing(10);
		slider.setMajorTickSpacing(20);
		slider.setVisible(true);
	}

	public void draw(Graphics g) {
		g.drawImage(background, 0, 0, Main.width, Main.height, (int) -x, 0, (int) -x + Main.width, Main.height, null);
		g.drawImage(title, Main.width / 2 - title.getWidth() / 2, 30, null);
		button.draw(g);
	}

	public void update(float tslf) {
		x += xspeed * tslf;
		if (x > 0) {
			x = 0;
			xspeed *= -1;
		} else if (x < -background.getWidth() + Main.width) {
			x = -background.getWidth() + Main.width;
			xspeed *= -1;
		}

		if (button.update()) {
			sound.play();
			Frame.state = 0;
		}
	}
}