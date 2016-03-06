package edu.neumont.csc150.fp.barryj.zombie;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ZombieGUI implements ActionListener {
	JFrame zombieWindow = new JFrame("Zombie Dice");
	JPanel backgroundPanel = new JPanel(new GridLayout(1,3));
	JPanel westPanel = new JPanel(new GridLayout(4,2));
	JPanel centerPanel = new JPanel(new GridLayout(3,1));
	JPanel eastPanel = new JPanel(new GridLayout(3,1));
	
	JPanel[] buttonHolders = new JPanel[3];
	JButton[] buttons = {new JButton("Roll"), new JButton("End Turn"), new JButton("Exit")};
	JLabel[] scoreLabels = new JLabel[4];
	JLabel[] scoreValues = new JLabel[4];
	String[] labels = {"Player: ", "Survivors Cornered: ", "Blasts Taken: ", "Brains Eaten: "};
	
	ImageIcon backgroundPic = new ImageIcon("src/images/unnamed.jpg");
	JLabel backLabel = new JLabel();
	JLabel[] picHolders = new JLabel[3];
	
	public ZombieGUI() {
		for(int i = 0; i < 3; i++) {
			buttons[i].addActionListener(this);
			buttonHolders[i] = new JPanel();
			buttonHolders[i].add(buttons[i]);
			eastPanel.add(buttonHolders[i]);
			picHolders[i] = new JLabel();
			centerPanel.add(picHolders[i]);
		}
		
		for (int i = 0; i < 4; i++) {
			scoreLabels[i] = new JLabel(labels[i]);
			scoreValues[i] = new JLabel("0");
			westPanel.add(scoreLabels[i]);
			westPanel.add(scoreValues[i]);
		}
		
		westPanel.setOpaque(false);
		centerPanel.setOpaque(false);
		eastPanel.setOpaque(false);
		backgroundPanel.add(westPanel);
		backgroundPanel.add(centerPanel);
		backgroundPanel.add(eastPanel);
		//backLabel.add(backgroundPic);
		//backgroundPanel.setBackground(backgroundPic);
		zombieWindow.add(backgroundPanel);
		zombieWindow.setVisible(true);
		zombieWindow.setSize(720,450);
		zombieWindow.setLocationRelativeTo(null);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
