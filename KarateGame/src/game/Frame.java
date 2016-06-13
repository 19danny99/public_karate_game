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
	private GameOver gameover;
	private Menu menu;
	private Options options;
	static int state;
	private Soundplayer sound2 = new Soundplayer("sounds/videogamemusik.wav");
	private Soundplayer sound3 = new Soundplayer("sounds/titlemusik.wav");
	/**
	 * Konstruktor
	 */
	public Frame() {
		super("Game");
		menu = new Menu();
		etage = new Etage();
		gameover = new GameOver();
		options = new Options();
		Keyboard kb = new Keyboard();
		addKeyListener(kb);
		addMouseMotionListener(kb);
		addMouseListener(kb);
		setIconImage(new ImageIcon(getClass().getResource("/gfx/LinksStehend.png")).getImage());

	}
	/**
	 * Diese Methode 
	 */
	public void makestrat() {
		createBufferStrategy(2);
		strat = getBufferStrategy();
	}
	/**
	 * Diese Methode zeichnet alles neu
	 */
	public void repaint() {
		Graphics g = strat.getDrawGraphics();
		draw(g);
		g.dispose();
		strat.show();
	}
	/**
	 * Diese methode zeichnet einer der untenstehenden cases
	 * @param g
	 */
	public void draw(Graphics g) {
		switch (state) {
		case 0:
			menu.draw(g);
			break;
		case 1:
			etage.draw(g);
			break;
		case 2:
			gameover.draw(g, etage.getFinalScore());
			break;
		case 3:
			options.draw(g);
		default:
			break;
		}

	}
	/**
	 * Diese Methode macht die sounds
	 * @param tslf
	 * @param laststate
	 */
	public void update(float tslf, int laststate) {
		switch (state) {
		case 0:

			etage = new Etage();
			if (sound2.isPlaying()) {
				sound2.stop();
			}
			if (!sound3.isPlaying()) {
				sound3.loop();
			}
			menu.update(tslf, etage);
			break;
		case 1:
			if (sound3.isPlaying()) {
				sound3.stop();
			}
			if (!sound2.isPlaying()) {
				sound2.loop();
			}
			etage.update(tslf);
			if (Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE))
				state = 0;
			if (etage.getGameOver())
				state = 2;
			break;
		case 2:
			if (sound2.isPlaying()) {
				sound2.stop();
			}
			if (!sound3.isPlaying()) {
				sound3.loop();
			}
			gameover.update(tslf);
			if (Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE))
				state = 0;
			break;
		case 3:
			if (Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE))
				state = 0;
			options.update(tslf);
		default:
			break;
		}
	}
}
