package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Button {
	private int x;
	private int y;
	//private String center;
	private Font font;
	private java.awt.FontMetrics fm;
	private String name;
	private BufferedImage[] look;
	private int show;
	public Button(int y, String name, BufferedImage[] look, Font font) {
		this.y = y;
		this.name = name;
		this.look = look;
		this.font = font;
		x = Main.width/2 - look[show].getWidth()/2;
	}
	
	public void draw(Graphics g) {
		g.setFont(font);
		fm = g.getFontMetrics();
		g.drawImage(look[show], x, y, null);
		g.setColor(Color.BLACK);
		g.drawString(name, x + look[show].getWidth()/2 - fm.stringWidth(name)/2, y + look[show].getHeight()/2 + font.getSize()/2);
	}
	
	public static boolean collision(float rect1x, float rect1y, int width1, int height1, float rect2x, float rect2y, int width2, int height2) {
		if (rect1x <= rect2x + width2 && rect1x + width1 >= rect2x && rect1y <= rect2y + height2 && rect1y + height1 >= rect2y) return true;
		return false;
	}
	
	public boolean update() {
		if (Keyboard.getButton() != 1 && show == 2) {
			show = 0;
			return true;
		}
		show = 0;
		if (collision(Keyboard.getMouseX(), Keyboard.getMouseY(), 0, 0, x, y, look[show].getWidth(), look[show].getHeight())) {
			if (Keyboard.getButton() == 1) show = 2;
			else show = 1;
		}
		return false;
	}
}
