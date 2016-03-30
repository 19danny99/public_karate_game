package game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Menu {
	private BufferedImage background;
	private BufferedImage title;
	private Button[] buttons;
	private float x;
	private float xspeed;

	public Menu() {
		
		xspeed = 50;
		
		Font f = new Font("SansSerif", 0, 30);
		buttons = new Button[3];
		BufferedImage[] textures = {ImageLoader.loadImage("Button"), ImageLoader.loadImage("ButtonMaus"), ImageLoader.loadImage("ButtonGedrueckt")};
		
		buttons[0] = new Button(200, "Start Game", textures, f);
		buttons[1] = new Button(350, "Options", textures, f);
		buttons[2] = new Button(500, "Exit", textures, f);
		
		background = ImageLoader.loadImage("MenuBackground");
		title = ImageLoader.loadImage("Title");
	}

	public void draw(Graphics g) {
		g.drawImage(background, 0, 0, Main.width, Main.height, (int) -x, 0, (int) -x + Main.width, Main.height, null);
		g.drawImage(title, Main.width / 2 - title.getWidth() / 2, 30, null);
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].draw(g);
		}

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
		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i].update()) {
				if (i == 0)
					Frame.state = 1;
				else if (i == 1)
					;
				else if (i == 2)
					System.exit(0);
			}
		}
	}
}
