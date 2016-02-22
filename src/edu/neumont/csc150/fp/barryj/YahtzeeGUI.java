package edu.neumont.csc150.fp.barryj;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.*;

public class YahtzeeGUI implements MouseListener, ActionListener {
	int rollCount;
	int sum;
	int total;
	boolean[] dieHeld = {false, false, false, false, false};
	
	JFrame yahtzeeWindow;
	JPanel backgroundPanel;
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
	int[] diceVals;
	
	JLabel [] scoreNum;
	JLabel[] scoreLbl;
	String[] labels = {"Ones", "Twos", "Threes", "Fours", "Fives", "Sixes", "Sum", "Bonus", "Three of a kind", "Four of a kind", "Full House", "Small Straight", "Large Straight", "Chance", "YAHTZEE", "Total"};
	
	Color over;
	Color click;
	Color sbt;
	
	public YahtzeeGUI() {
		rollCount = 0;
		sum = 0;
		total = 0;
		
		yahtzeeWindow = new JFrame();
		backgroundPanel = new JPanel(new GridLayout(1,3));
		westPanel = new JPanel(new GridLayout(4,1));
		centerPanel = new JPanel(new GridLayout(5,1));
		eastPanel = new JPanel(new GridLayout(16,2));
		
		buttonPanel1 = new JPanel();
		buttonPanel2 = new JPanel();
		buttonPanel3 = new JPanel();
		labelPanel = new JPanel();
		
		roll = new JButton("Roll");
		newGame = new JButton("New Game");
		exit = new JButton("Exit");
		rollLeft = new JLabel("3 rolls left");
		
		picHolders = new JLabel[5];
		diceVals = new int[5];
		
		scoreNum = new JLabel[16];
		scoreLbl = new JLabel[16];
		
		over = new Color(0,157,255);
		click = new Color(0,255,127);
		sbt = new Color(149,26,162);
		
		for (int i = 0; i < 16; i++) {
			scoreLbl[i] = new JLabel(labels[i]);
			scoreNum[i] = new JLabel("0");
			eastPanel.add(scoreLbl[i]);
			eastPanel.add(scoreNum[i]);
			scoreLbl[i].addMouseListener(this);
		}
		
		for (int i = 0; i < 5; i++){
			picHolders[i] = new JLabel("");
			centerPanel.add(picHolders[i]);
			picHolders[i].addMouseListener(this);
		}
		
		for (int i = 0; i < 16; i++) {
			if (i == 6){	
				scoreLbl[i].setForeground(sbt);
			} else if (i == 7) {
				scoreLbl[i].setForeground(sbt);
			} else if (i == 15) {
				scoreLbl[i].setForeground(sbt);
			}
			
		}
		
		roll.addActionListener(this);
		newGame.addActionListener(this);
		exit.addActionListener(this);
		
		buttonPanel1.add(roll);
		buttonPanel2.add(newGame);
		buttonPanel3.add(exit);
		labelPanel.add(rollLeft);
		
		westPanel.add(buttonPanel1);
		westPanel.add(buttonPanel2);
		westPanel.add(buttonPanel3);
		westPanel.add(labelPanel);
		backgroundPanel.add(westPanel);
		backgroundPanel.add(centerPanel);
		backgroundPanel.add(eastPanel);
		yahtzeeWindow.add(backgroundPanel);
		yahtzeeWindow.setVisible(true);
		yahtzeeWindow.setSize(700,550);
		yahtzeeWindow.setLocationRelativeTo(null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < 16; i++) {
			if (e.getSource() == scoreLbl[i]){	
				scoreLbl[i].setForeground(click);
			}
			
		}
		
		for (int i = 0; i < 5; i++) {
			if (e.getSource() == picHolders[i]) {
				dieHeld[i] = !dieHeld[i];
				if (dieHeld[i]) {
					picHolders[i].setIcon(new ImageIcon("src//images//" + diceVals[i] + "L.jpg"));
					
				} else if (!dieHeld[i]) {
					picHolders[i].setIcon(new ImageIcon("src//images//" + diceVals[i] + ".jpg"));
					
				}
				
			}
		}
		
		for (int i = 0; i < 6; i++) {
			if (e.getSource() == scoreLbl[i]) {
				scoreNum[i].setText(Integer.toString(numCount(i + 1) * (i + 1)));
				sum += (numCount(i + 1) * (i + 1));
				total += (numCount(i + 1) * (i + 1));
				for (int j = 0; j < 5; j++) {
					if (dieHeld[j]) {
						picHolders[j].setIcon(new ImageIcon("src//images//" + diceVals[j] + ".jpg"));
						dieHeld[j] = false;
					}
				}
				
				scoreNum[6].setText(Integer.toString(sum));
				
				if (sum > 63) {
					scoreNum[7].setText(Integer.toString(35));
					total += 35;
				}
				
				scoreNum[15].setText(Integer.toString(total));
				rollCount = 0;
				rollLeft.setText((3 - rollCount) + " roll(s) left");
				
				scoreLbl[i].removeMouseListener(this);
			}
		}
		
		if (e.getSource() == scoreLbl[8]) {
			int three = 0;
			for (int i = 0; i < 5; i++) {
				if (numCount(diceVals[i]) >= 3) {
					three = diceVals[i] * 3;
				}
			}
			for (int j = 0; j < 5; j++) {
				if (dieHeld[j]) {
					picHolders[j].setIcon(new ImageIcon("src//images//" + diceVals[j] + ".jpg"));
					dieHeld[j] = false;
				}
			}
			scoreNum[8].setText(Integer.toString(three));
			rollCount = 0;
			rollLeft.setText((3 - rollCount) + " roll(s) left");
			total += three;
			scoreLbl[8].removeMouseListener(this);
		}
		
		if (e.getSource() == scoreLbl[9]) {
			int four = 0;
			for (int i = 0; i < 5; i++) {
				if (numCount(diceVals[i]) >= 4) {
					four = diceVals[i] * 4;
				}
			}
			for (int j = 0; j < 5; j++) {
				if (dieHeld[j]) {
					picHolders[j].setIcon(new ImageIcon("src//images//" + diceVals[j] + ".jpg"));
					dieHeld[j] = false;
				}
			}
			scoreNum[9].setText(Integer.toString(four));
			rollCount = 0;
			rollLeft.setText((3 - rollCount) + " roll(s) left");
			total += four;
			scoreLbl[9].removeMouseListener(this);
		}
		
		if (e.getSource() == scoreLbl[10]) {
			int ref = 0;
			boolean threeOfAKind = false;
			for (int i = 0; i < 5; i++) {
				if (numCount(diceVals[i]) == 3) {
					threeOfAKind = true;
				}
			}
			for (int i = 0; i < 5; i++) {
				if (numCount(diceVals[i]) == 2 && threeOfAKind) {
					ref = 25;
					scoreNum[10].setText(Integer.toString(ref));
				}
			}
			total += ref;
			rollCount = 0;
			rollLeft.setText((3 - rollCount) + " roll(s) left");
			for (int j = 0; j < 5; j++) {
				if (dieHeld[j]) {
					picHolders[j].setIcon(new ImageIcon("src//images//" + diceVals[j] + ".jpg"));
					dieHeld[j] = false;
				}
			}
			scoreLbl[10].removeMouseListener(this);
		}
		
		if (e.getSource() == scoreLbl[11]) {
			int ref = 0;
			int count = 0;
			int smallS = 0;
			for (int i = 0; i < 5; i++) {
				ref = diceVals[i];
				for (int j = 0; j < 5; j++) {
					if (diceVals[j] == ref + 1) {
						ref += 1;
						count += 1;
					}
				}
			}
			if (count > 2) {
				smallS = 30;
				total += smallS;
			}
			for (int j = 0; j < 5; j++) {
				if (dieHeld[j]) {
					picHolders[j].setIcon(new ImageIcon("src//images//" + diceVals[j] + ".jpg"));
					dieHeld[j] = false;
				}
			}
			scoreNum[11].setText(Integer.toString(smallS));
			rollCount = 0;
			rollLeft.setText((3 - rollCount) + " roll(s) left");
			scoreLbl[11].removeMouseListener(this);
		}
		
		if (e.getSource() == scoreLbl[12]) {
			int ref = 0;
			int count = 0;
			int largeS = 0;
			for (int i = 0; i < 5; i++) {
				ref = diceVals[i];
				for (int j = 0; j < 5; j++) {
					if (diceVals[j] == ref + 1) {
						ref += 1;
						count += 1;
					}
				}
			}
			if (count > 3) {
				largeS = 40;
				total += largeS;
			}
			for (int j = 0; j < 5; j++) {
				if (dieHeld[j]) {
					picHolders[j].setIcon(new ImageIcon("src//images//" + diceVals[j] + ".jpg"));
					dieHeld[j] = false;
				}
			}
			scoreNum[12].setText(Integer.toString(largeS));
			rollCount = 0;
			rollLeft.setText((3 - rollCount) + " roll(s) left");
			scoreLbl[12].removeMouseListener(this);
		}
		
		if (e.getSource() == scoreLbl[13]) {
			int diceSum = 0;
			for (int i = 0; i < 5; i++) {
				diceSum += diceVals[i];
			}
			scoreNum[13].setText(Integer.toString(diceSum));
			total += diceSum;
			rollCount = 0;
			rollLeft.setText((3 - rollCount) + " roll(s) left");
			for (int j = 0; j < 5; j++) {
				if (dieHeld[j]) {
					picHolders[j].setIcon(new ImageIcon("src//images//" + diceVals[j] + ".jpg"));
					dieHeld[j] = false;
				}
			}
			scoreLbl[13].removeMouseListener(this);
		}
		
		if (e.getSource() == scoreLbl[14]) {
			int five = 0;
			for (int i = 0; i < 5; i++) {
				if (numCount(diceVals[i]) == 5) {
					five = 50;
				}
			}
			for (int j = 0; j < 5; j++) {
				if (dieHeld[j]) {
					picHolders[j].setIcon(new ImageIcon("src//images//" + diceVals[j] + ".jpg"));
					dieHeld[j] = false;
				}
			}
			scoreNum[14].setText(Integer.toString(five));
			rollCount = 0;
			rollLeft.setText((3 - rollCount) + " roll(s) left");
			total += five;
			scoreLbl[14].removeMouseListener(this);
		}
		
		scoreNum[15].setText(Integer.toString(total));
		
	
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		for (int i = 0; i < 15; i++) {
			if (e.getSource() == scoreLbl[i] && scoreLbl[i].getForeground() != click && i != 6 && i != 7){
				scoreLbl[i].setForeground(over);
			}
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		for (int i = 0; i < 15; i++) {
			if (e.getSource() == scoreLbl[i] && scoreLbl[i].getForeground() != click && i != 6 && i != 7){
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
			if (rollCount < 3) {
				Random gen = new Random();
				for (int i = 0; i < 5; i++) {
					if (!dieHeld[i]) {
						diceVals[i] = gen.nextInt(6) + 1;
						picHolders[i].setIcon(new ImageIcon("src//images//" + diceVals[i] + ".jpg"));
					}
				}
				rollCount++;
				rollLeft.setText((3 - rollCount) + " roll(s) left");
			}
		}
		
		if (e.getSource() == newGame) {
			rollCount = 0;
			rollLeft.setText((3 - rollCount) + " roll(s) left");
			total = 0;
			
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
			
			for (int i = 0; i < 5; i++) {
				diceVals[i] = 0;
				picHolders[i].setIcon(new ImageIcon(""));
			}
			
			for (int i = 0; i < 16; i++){
				scoreLbl[i].removeMouseListener(this);
				scoreLbl[i].addMouseListener(this);
			}
		}
		
		if (e.getSource() == exit) {
			System.exit(0);
		}
		
		
		
	}
	
	public int numCount(int num) {
		int ref = 0;
		for (int i = 0; i < 5; i++) {
			if (num == diceVals[i]) {
				ref += 1;
			}
		}
		return ref;
	}
	
}
