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
	YahtzeePlayer p;

	JFrame yahtzeeWindow;
	JPanel backgroundPanel;
	JPanel northWestPanel;
	JPanel westPanel;
	JPanel centerPanel;
	JPanel eastPanel;

	JPanel buttonPanel1;
	JPanel buttonPanel2;
	JPanel buttonPanel3;
	JPanel labelPanel;

	JButton roll;
	JButton newGame;
	JButton exit;
	JLabel rollLeft;

	JLabel[] picHolders;

	JLabel playerNum;
	JLabel[] scoreNum;
	JLabel[] scoreLbl;
	String[] labels = { "Ones", "Twos", "Threes", "Fours", "Fives", "Sixes", "Sum", "Bonus", "Three of a kind",
			"Four of a kind", "Full House", "Small Straight", "Large Straight", "Chance", "YAHTZEE", "Total" };

	Color over;
	Color click;
	Color sbt;

	public YahtzeeGUI(ControlListener listener, YahtzeePlayer p) {
		cl = listener;
		this.p = p;

		yahtzeeWindow = new JFrame();
		backgroundPanel = new JPanel(new GridLayout(1, 3));
		westPanel = new JPanel(new GridLayout(5, 1));
		centerPanel = new JPanel(new GridLayout(5, 1));
		eastPanel = new JPanel(new GridLayout(16, 2));
		northWestPanel = new JPanel();

		buttonPanel1 = new JPanel();
		buttonPanel2 = new JPanel();
		buttonPanel3 = new JPanel();
		labelPanel = new JPanel();

		roll = new JButton("Roll");
		newGame = new JButton("New Game");
		exit = new JButton("Exit");
		rollLeft = new JLabel("3 rolls left");

		picHolders = new JLabel[5];

		playerNum = new JLabel("Player " + p.getNumber());
		scoreNum = new JLabel[16];
		scoreLbl = new JLabel[16];

		over = new Color(0, 157, 255);
		click = new Color(0, 255, 127);
		sbt = new Color(149, 26, 162);

		for (int i = 0; i < 16; i++) {
			scoreLbl[i] = new JLabel(labels[i]);
			if (p.playerVals[i] != null) {
				scoreNum[i] = new JLabel("");
			} else {
				scoreNum[i] = new JLabel("" + p.playerVals[i]);
				scoreLbl[i].setForeground(click);
			}
			eastPanel.add(scoreLbl[i]);
			eastPanel.add(scoreNum[i]);
			scoreLbl[i].addMouseListener(this);
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
		newGame.addActionListener(this);
		exit.addActionListener(this);

		northWestPanel.add(playerNum);
		buttonPanel1.add(roll);
		buttonPanel2.add(newGame);
		buttonPanel3.add(exit);
		labelPanel.add(rollLeft);

		westPanel.add(northWestPanel);
		westPanel.add(buttonPanel1);
		westPanel.add(buttonPanel2);
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
		for (int i = 0; i < 16; i++) {
			if (e.getSource() == scoreLbl[i]) {
				scoreLbl[i].setForeground(click);
			}
		}

		for (int i = 0; i < 5; i++) {
			if (e.getSource() == picHolders[i]) {
				cl.holdDie(i);
			}
		}

		for (int i = 0; i < 6; i++) {
			if (e.getSource() == scoreLbl[i]) {
				cl.lowScores(i);
				rollLeft.setText((3 - cl.returnRollsUsed()) + " roll(s) left");
				scoreLbl[i].removeMouseListener(this);
			}
		}

		if (e.getSource() == scoreLbl[8]) {
			cl.scoreThrees();
			rollLeft.setText((3 - cl.returnRollsUsed()) + " roll(s) left");
			scoreLbl[8].removeMouseListener(this);
		}

		if (e.getSource() == scoreLbl[9]) {
			cl.scoreFours();
			rollLeft.setText((3 - cl.returnRollsUsed()) + " roll(s) left");
			scoreLbl[9].removeMouseListener(this);
		}

		if (e.getSource() == scoreLbl[10]) {
			cl.scoreFullH();
			rollLeft.setText((3 - cl.returnRollsUsed()) + " roll(s) left");
			scoreLbl[10].removeMouseListener(this);
		}

		if (e.getSource() == scoreLbl[11]) {
			cl.scoreSmallS();
			rollLeft.setText((3 - cl.returnRollsUsed()) + " roll(s) left");
			scoreLbl[11].removeMouseListener(this);
		}

		if (e.getSource() == scoreLbl[12]) {
			cl.scoreLargeS();
			rollLeft.setText((3 - cl.returnRollsUsed()) + " roll(s) left");
			scoreLbl[12].removeMouseListener(this);
		}

		if (e.getSource() == scoreLbl[13]) {
			cl.scoreChance();
			rollLeft.setText((3 - cl.returnRollsUsed()) + " roll(s) left");
			scoreLbl[13].removeMouseListener(this);
		}

		if (e.getSource() == scoreLbl[14]) {
			cl.scoreYahtzee();
			rollLeft.setText((3 - cl.returnRollsUsed()) + " roll(s) left");
			scoreLbl[14].removeMouseListener(this);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		for (int i = 0; i < 15; i++) {
			if (e.getSource() == scoreLbl[i] && scoreLbl[i].getForeground() != click && i != 6 && i != 7) {
				scoreLbl[i].setForeground(over);
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		for (int i = 0; i < 15; i++) {
			if (e.getSource() == scoreLbl[i] && scoreLbl[i].getForeground() != click && i != 6 && i != 7) {
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

		if (e.getSource() == newGame) {
			cl.resetGame();
			rollLeft.setText((3 - cl.returnRollsUsed()) + " roll(s) left");
			scoreNum[15].setText("0");

			for (int i = 0; i < 15; i++) {
				scoreLbl[i].setForeground(Color.DARK_GRAY);
				if (i == 6) {
					scoreLbl[i].setForeground(sbt);
				} else if (i == 7) {
					scoreLbl[i].setForeground(sbt);
				}
			}

			for (int i = 0; i < 15; i++) {
				scoreNum[i].setText("0");
			}

			for (int i = 0; i < 16; i++) {
				scoreLbl[i].removeMouseListener(this);
				scoreLbl[i].addMouseListener(this);
			}
		}

		if (e.getSource() == exit) {
			System.exit(0);
		}

	}
}
