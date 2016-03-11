package edu.neumont.csc150.fp.barryj.crown;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import edu.neumont.csc150.fp.barryj.Die;
import edu.neumont.csc150.fp.barryj.ObjectListener;

public class CandAGUI implements ActionListener, MouseListener, ObjectListener {
	GameController gc;
	JFrame window;
	ImageIcon frameIcon;
	JPanel backgroundPanel;
	JPanel westPanel;
	JPanel scorePanel;
	JPanel rollPanel;
	JPanel exitPanel;
	JPanel centerPanel;
	JPanel centerCenter;
	JPanel centerSouth;

	JButton roll;
	JButton exit;

	JLabel[] selections;
	CrownDie[] topBoard;
	JLabel[] picHolders;
	JLabel scoreLbl;

	public CandAGUI(GameController game, int s) {
		gc = game;
		window = new JFrame("Crown and Anchor");
		frameIcon = new ImageIcon(this.getClass().getResource("/edu/neumont/csc150/fp/barryj/images/crownIcon.png"));
		window.setIconImage(frameIcon.getImage());
		backgroundPanel = new JPanel(new BorderLayout());
		westPanel = new JPanel(new GridLayout(3, 1));
		scorePanel = new JPanel();
		rollPanel = new JPanel();
		exitPanel = new JPanel();
		centerPanel = new JPanel(new BorderLayout());
		centerCenter = new JPanel(new GridLayout(2, 3));
		centerSouth = new JPanel(new GridLayout(1, 3));

		roll = new JButton("Roll");
		exit = new JButton("Exit");
		roll.addActionListener(this);
		exit.addActionListener(this);

		scoreLbl = new JLabel("Score: ");
		scorePanel.add(scoreLbl);

		selections = new JLabel[6];
		topBoard = new CrownDie[6];
		for (int i = 0; i < selections.length; i++) {
			selections[i] = new JLabel("");
			topBoard[i] = new CrownDie(this);
			topBoard[i].setDieSymbol(i + 1);
			topBoard[i].changeImage("/edu/neumont/csc150/fp/barryj/images/topc" + topBoard[i].getDieSymbol() + "p.png");
			updateSelection(i, topBoard[i]);
			selections[i].setHorizontalAlignment(SwingConstants.CENTER);
		    selections[i].setVerticalAlignment(SwingConstants.CENTER);
			selections[i].addMouseListener(this);
			centerCenter.add(selections[i]);
		}

		picHolders = new JLabel[3];
		for (int i = 0; i < 3; i++) {
			picHolders[i] = new JLabel();
			picHolders[i].setHorizontalAlignment(SwingConstants.CENTER);
		    picHolders[i].setVerticalAlignment(SwingConstants.CENTER);
			centerSouth.add(picHolders[i]);
		}

		centerCenter.setBackground(Color.white);
		centerSouth.setBackground(Color.white);
		centerPanel.setBackground(Color.white);
		rollPanel.setBackground(Color.white);
		exitPanel.setBackground(Color.white);
		scorePanel.setBackground(Color.white);
		westPanel.setBackground(Color.white);
		backgroundPanel.setBackground(Color.white);

		centerPanel.add(centerCenter, BorderLayout.NORTH);
		centerPanel.add(centerSouth, BorderLayout.SOUTH);
		rollPanel.add(roll);
		exitPanel.add(exit);
		westPanel.add(scorePanel);
		westPanel.add(rollPanel);
		westPanel.add(exitPanel);
		backgroundPanel.add(westPanel, BorderLayout.WEST);
		backgroundPanel.add(centerPanel, BorderLayout.CENTER);
		window.add(backgroundPanel);
		window.setVisible(true);
		window.setSize(700, 500);
		window.setLocationRelativeTo(null);
	}

	public void setDice(CrownDie[] dice) {
		for (int i = 0; i < 3; i++) {
			picHolders[i].setIcon(dice[i].getDieFace());
		}
	}

	@Override
	public void updateDie(Die die) {
		if (die.isHeld()) {
			die.changeImage("/edu/neumont/csc150/fp/barryj/images/topc" + die.getDieSymbol() + ".png");
		} else if (!die.isHeld()) {
			die.changeImage("/edu/neumont/csc150/fp/barryj/images/topc" + die.getDieSymbol() + "p.png");
		}
	}

	@Override
	public void updateSelection(int i, Die die) {
		selections[i].setIcon(die.getDieFace());
	}

	@Override
	public void updateUI(int i, Die die) {
		picHolders[i].setIcon(die.getDieFace());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == roll) {
			gc.roll();
			scoreLbl.setText("Score: " + gc.getScore());
		}

		if (e.getSource() == exit) {
			System.exit(0);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < 6; i++) {
			if (e.getSource() == selections[i]) {
				for (int j = 0; j < 6; j++) {
					if (topBoard[j].isHeld()) {
						topBoard[j].flipBoolean();
						topBoard[j].setDieFace(new ImageIcon(
								"/edu/neumont/csc150/fp/barryj/images/c" + topBoard[j].getDieSymbol() + ".png"));
						updateDie(topBoard[j]);
						updateSelection(j, topBoard[j]);
					}
				}
				topBoard[i].setisHeld(true);
				updateDie(topBoard[i]);
				updateSelection(i, topBoard[i]);
				gc.setSelected(topBoard[i]);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
