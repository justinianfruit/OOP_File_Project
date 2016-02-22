package edu.neumont.csc150.fp.barryj;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameStarter implements ActionListener {
	private JFrame firstWindow = new JFrame("Starter");
	private JPanel firstPanel = new JPanel(new BorderLayout());
	private JLabel prompt = new JLabel("Which game would you like to play?");
	private JButton[] buttons = {new JButton("Zombie Dice"), new JButton("Yahtzee"), new JButton("Crown and Anchor")};
	
	public GameStarter() {
		firstPanel.add(prompt);
		for (int i = 0; i < buttons.length; i++) {
			firstPanel.add(buttons[i]);
			buttons[i].setPreferredSize(new Dimension(25, 15));
			buttons[i].addActionListener(this);
		}
		firstWindow.add(firstPanel, BorderLayout.CENTER);
		firstWindow.setVisible(true);
		firstWindow.setSize(400, 400);
		firstWindow.setLocationRelativeTo(null);
		firstWindow.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
