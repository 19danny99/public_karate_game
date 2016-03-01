package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Menu extends JFrame implements ActionListener{
	private JButton start;
	private JButton options;
	private JButton exit;
	
	
	public Menu(String title) {
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
			// spiel starten aufrufen
		}
		if (e.getSource() == options) {
			// options aufrufen
		}
		if (e.getSource() == exit) {
			System.exit(0);
		}
	}
	
	public static void main(String[] args0) {
		Menu menu = new Menu("Kung-Fu");
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setSize(400, 400);
		menu.setLayout(null);
		menu.setVisible(true);
	}
}
