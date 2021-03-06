package game;

import java.awt.BorderLayout;

public class Main{
	
	static int width = 1600;
	static int height = 900;
	/**
	 * Diese Methode erzeugt das Frame
	 * @param args
	 */
	public static void main(String[] args) {
		
		Frame frame = new Frame();
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(3); // 3 = EXIT_ON_CLOSE
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.setUndecorated(true);
		frame.setVisible(true);
		
		frame.makestrat();
		
		long lastFrame = System.currentTimeMillis();
		int laststate = -1;
		try
		{
		while(true) {
			laststate = frame.getState();
			long thisFrame = System.currentTimeMillis();
			float timeSinceLastFrame = (float) ((thisFrame - lastFrame)/1000.0);
			lastFrame = thisFrame;
			frame.update(timeSinceLastFrame, laststate);
			frame.repaint();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
