package edu.neumont.csc150.fp.barryj.yahtzee;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class YahtzeeGUI implements MouseListener, ActionListener, ObjectListener {
	ControlListener cl;

	JFrame yahtzeeWindow;
	JPanel backgroundPanel;
	JPanel northWestPanel;
	JPanel westPanel;
	JPanel centerPanel;
	JPanel eastPanel;

	JPanel buttonPanel1;
	JPanel buttonPanel3;
	JPanel labelPanel;

	JButton roll;
	JButton exit;
	JLabel rollLeft;

	JLabel[] picHolders;

	JLabel playerNum;
	JLabel[] scoreNum;
	JLabel[] scoreLbl;
	String[] labels = { "Ones", "Twos", "Threes", "Fours", "Fives", "Sixes", "Sum", "Bonus", "Three of a kind",
			"Four of a kind", "Full House", "Small Straight", "Large Straight", "Chance", "YAHTZEE", "Total" };

	Color over;
	Color sbt;
	Color player1;
	Color player2;

	public YahtzeeGUI() {
		yahtzeeWindow = new JFrame();
		backgroundPanel = new JPanel(new GridLayout(1, 3));
		westPanel = new JPanel(new GridLayout(5, 1));
		centerPanel = new JPanel(new GridLayout(5, 1));
		eastPanel = new JPanel(new GridLayout(16, 2));
		northWestPanel = new JPanel();

		buttonPanel1 = new JPanel();
		buttonPanel3 = new JPanel();
		labelPanel = new JPanel();

		roll = new JButton("Roll");
		exit = new JButton("Exit");
		rollLeft = new JLabel("3 rolls left");

		picHolders = new JLabel[5];

		playerNum = new JLabel();
		scoreNum = new JLabel[16];
		scoreLbl = new JLabel[16];

		over = new Color(0, 157, 255);
		sbt = new Color(149, 26, 162);
		player1 = new Color(199,185,197);
		player2 = new Color(197,224,220);

		for (int i = 0; i < 16; i++) {
			scoreLbl[i] = new JLabel(labels[i]);
			scoreLbl[i].addMouseListener(this);
			scoreNum[i] = new JLabel();
			eastPanel.add(scoreLbl[i]);
			eastPanel.add(scoreNum[i]);
		}

		for (int i = 0; i < 5; i++) {
			picHolders[i] = new JLabel("");
			centerPanel.add(picHolders[i]);
			picHolders[i].addMouseListener(this);
		}

		scoreLbl[6].setForeground(sbt);
		scoreLbl[7].setForeground(sbt);
		scoreLbl[15].setForeground(sbt);

		roll.addActionListener(this);
		exit.addActionListener(this);

		northWestPanel.add(playerNum);
		buttonPanel1.add(roll);
		buttonPanel3.add(exit);
		labelPanel.add(rollLeft);

		westPanel.add(northWestPanel);
		westPanel.add(buttonPanel1);
		westPanel.add(buttonPanel3);
		westPanel.add(labelPanel);
		backgroundPanel.add(westPanel);
		backgroundPanel.add(centerPanel);
		backgroundPanel.add(eastPanel);
		yahtzeeWindow.add(backgroundPanel);
		yahtzeeWindow.setVisible(true);
		yahtzeeWindow.setSize(700, 550);
		yahtzeeWindow.setLocationRelativeTo(null);
	}
	
	public void closeWindow() {
		yahtzeeWindow.dispose();
	}
	
	public void initialize(YahtzeePlayer p, YahtzeeTurn yt) {
		cl = yt;
		playerNum.setText("Player " + p.getNumber());
		for (int i = 0; i < 16; i++) {
			if (p.playerVals[i] == null) {
				scoreNum[i].setText("");
				scoreLbl[i].setForeground(Color.DARK_GRAY);
			} else {
				scoreNum[i].setText("" + p.playerVals[i]);
				scoreLbl[i].setForeground(Color.white);
			}
		}
		rollLeft.setText((3 - cl.returnRollsUsed()) + " roll(s) left");
		scoreLbl[6].setForeground(sbt);
		scoreLbl[7].setForeground(sbt);
		scoreLbl[15].setForeground(sbt);
		if (p.getNumber() == 1) {
			backgroundPanel.setBackground(player1);
			northWestPanel.setBackground(player1);
			westPanel.setBackground(player1);
			centerPanel.setBackground(player1);
			eastPanel.setBackground(player1);
			buttonPanel1.setBackground(player1);
			buttonPanel3.setBackground(player1);
			labelPanel.setBackground(player1);
		} else {
			backgroundPanel.setBackground(player2);
			northWestPanel.setBackground(player2);
			westPanel.setBackground(player2);
			centerPanel.setBackground(player2);
			eastPanel.setBackground(player2);
			buttonPanel1.setBackground(player2);
			buttonPanel3.setBackground(player2);
			labelPanel.setBackground(player2);
		}
	}

	@Override
	public void updateDie(YahtzeeDie die) {
		if (die.isHeld()) {
			die.changeImage("/edu/neumont/csc150/fp/barryj/images/" + die.getDieSymbol() + "L.jpg");
		} else if (!die.isHeld()) {
			die.changeImage("/edu/neumont/csc150/fp/barryj/images/" + die.getDieSymbol() + ".jpg");
		}
	}

	@Override
	public void updateUI(int i, YahtzeeDie die) {
		picHolders[i].setIcon(die.getDieFace());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		rollLeft.setText((3 - cl.returnRollsUsed()) + " roll(s) left");

		for (int i = 0; i < 5; i++) {
			if (e.getSource() == picHolders[i]) {
				cl.holdDie(i);
			}
		}

		for (int i = 0; i < 6; i++) {
			if (e.getSource() == scoreLbl[i]) {
				cl.lowScores(i);
			}
		}
		if (e.getSource() == scoreLbl[8]) {
			cl.scoreThrees();
		}
		if (e.getSource() == scoreLbl[9]) {
			cl.scoreFours();
		}
		if (e.getSource() == scoreLbl[10]) {
			cl.scoreFullH();
		}
		if (e.getSource() == scoreLbl[11]) {
			cl.scoreSmallS();
		}
		if (e.getSource() == scoreLbl[12]) {
			cl.scoreLargeS();
		}
		if (e.getSource() == scoreLbl[13]) {
			cl.scoreChance();
		}
		if (e.getSource() == scoreLbl[14]) {
			cl.scoreYahtzee();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		for (int i = 0; i < 15; i++) {
			if (e.getSource() == scoreLbl[i] && scoreLbl[i].getForeground() != Color.white && i != 6 && i != 7) {
				scoreLbl[i].setForeground(over);
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		for (int i = 0; i < 15; i++) {
			if (e.getSource() == scoreLbl[i] && scoreLbl[i].getForeground() != Color.white && i != 6 && i != 7) {
				scoreLbl[i].setForeground(Color.DARK_GRAY);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == roll) {
			cl.roll();
			rollLeft.setText((3 - cl.returnRollsUsed()) + " roll(s) left");
		}

		if (e.getSource() == exit) {
			System.exit(0);
		}

	}
}
