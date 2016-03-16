package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.newdawn.slick.Input;
import org.newdawn.slick.command.InputProvider;

public class Frame extends JFrame implements ActionListener{
	private JButton start;
	private JButton options;
	private JButton exit;
	static Frame frame;
	static JFrame gameFrame;
	static JFrame optionsFrame;
	
	public Frame(String title) {
		super(title);
		
		start = new JButton("Start Game");
		start.setBounds(120, 40, 160, 40);
		start.addActionListener(this);
		add(start);
		
		options = new JButton("Options");
		options.setBounds(120, 120, 160, 40);
		options.addActionListener(this);
		add(options);
		
		exit = new JButton("Exit");
		exit.setBounds(120, 200, 160, 40);
		exit.addActionListener(this);
		add(exit);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == start) {
			this.setVisible(false);
			gameFrame();
		}
		if (e.getSource() == options) {
			optionsFrame();
		}
		if (e.getSource() == exit) {
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		frame = new Frame("Kung-Fu Menü");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	public static void gameFrame() {
		gameFrame = new JFrame("Kung-Fu Game");
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setSize(1920, 980);
		gameFrame.setLayout(null);
		gameFrame.setVisible(true);
		gameFrame.setResizable(false);
	}
	
	public static void optionsFrame() {
		optionsFrame = new Frame("Kung-Fu Options");
		optionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		optionsFrame.setSize(400, 400);
		optionsFrame.setLayout(null);
		optionsFrame.setVisible(true);
		optionsFrame.setResizable(false);
	}
}