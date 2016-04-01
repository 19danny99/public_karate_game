package game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.sun.glass.events.KeyEvent;

public class Frame extends JFrame {
	private static final long serialVersionUID = -6632969291746977124L;
	private BufferStrategy strat;
	private Etage etage;
	private Menu menu;
	static int state;

	public Frame() {
		super("Game");
		menu = new Menu();
		etage = new Etage();
		Keyboard kb = new Keyboard();
		addKeyListener(kb);
		addMouseMotionListener(kb);
		addMouseListener(kb);
		setIconImage(new ImageIcon(getClass().getResource("/gfx/LinksStehend.png")).getImage());

	}

	public void makestrat() {
		createBufferStrategy(2);
		strat = getBufferStrategy();
	}

	public void repaint() {
		Graphics g = strat.getDrawGraphics();
		draw(g);
		g.dispose();
		strat.show();
	}

	public void draw(Graphics g) {
		switch (state) {
		case 0:
			menu.draw(g);
			break;

		case 1:
			etage.draw(g);
			break;

		default:
			break;
		}

	}

	public void update(float tslf) {
		switch (state) {
		case 0:
			menu.update(tslf);
			break;

		case 1:
			etage.update(tslf);
			if (Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE))
				state = 0;
			break;

		default:
			break;
		}
	}
}
