package edu.neumont.csc150.fp.barryj.yahtzee;

import javax.swing.JOptionPane;

public class YahtzeeGame {
	YahtzeePlayer[] players;
	YahtzeePlayer winner;
	YahtzeeTurn pt;
	YahtzeeGUI yg;
	int counter;

	public YahtzeeGame() {
		players = new YahtzeePlayer[2];
		counter = 0;
		for (int i = 0; i < 2; i++) {
			players[i] = new YahtzeePlayer(i + 1);
		}
		yg = new YahtzeeGUI();
		switchTurn();
		if (counter >= 24) {
			yg.closeWindow();
			winEval(players);
		}
	}

	public void switchTurn() {
		counter++;
		if (counter % 2 == 0) {
			players[1].setTakingTurn(true);
			pt = new YahtzeeTurn(this, players[1], yg);
		} else {
			players[0].setTakingTurn(true);
			pt = new YahtzeeTurn(this, players[0], yg);
		}
	}

	public void winEval(YahtzeePlayer[] ps) {
		int totalOne = ps[0].getTotal();
		int totalTwo = ps[1].getTotal();
		YahtzeePlayer toReturn;
		if (totalOne > totalTwo) {
			toReturn = ps[0];
		} else {
			toReturn = ps[1];
		}
		JOptionPane.showMessageDialog(null, "Player " + toReturn.getNumber() + " wins!", "Alert",
				JOptionPane.INFORMATION_MESSAGE);
	}

}