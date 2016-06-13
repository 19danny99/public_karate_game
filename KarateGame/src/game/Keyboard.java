package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Keyboard implements KeyListener, MouseMotionListener, MouseListener{

	private static boolean[] keys = new boolean[1024];
	private static int mouseX;
	private static int mouseY;
	private static int button;
	/**
	 * get-Methode
	 * @return
	 */
	public static int getMouseX() {
		return mouseX;
	}
	/*
	 *get-Methode 
	 */
	public static int getMouseY() {
		return mouseY;
	}
	/**
	 * get-Methode
	 * @return
	 */
	public static int getButton() {
		return button;
	}
	/**
	 * Diese Methode prüft ob eine Taste gedrückt wurde
	 * @param keycode
	 * @return keys
	 */
	public static boolean isKeyPressed(int keycode) {
		return keys[keycode];
	}
	/**
	 * Diese Methode prüft ob eine Taste gedrückt wurde
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
	/**
	 * Diese Methode prüft ob der key losgelassen wurde
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
	/**
	 * Diese Methode prüft die Mauskoordinaten
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	/**
	 * Diese Methode schaut ob die Maus gedrückt wurde
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		button = e.getButton();
	}
	/**
	 * Diese Methode schaut ob die Maus losgelassen wurde
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		button = -1;
	}

}
