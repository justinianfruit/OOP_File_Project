package edu.neumont.csc150.fp.barryj.yahtzee;

public class YahtzeeGame {
	YahtzeePlayer[] players;
	YahtzeePlayer winner;
	YahtzeeTurn p1;
	YahtzeeTurn p2;

	public YahtzeeGame() {
		players = new YahtzeePlayer[2];
		for (int i = 0; i < 2; i++) {
			players[i] = new YahtzeePlayer(i + 1);
		}
		//for (int i = 0; i < 13; i++) {
			p1 = new YahtzeeTurn(players[0]);
			p2 = new YahtzeeTurn(players[1]);
		//}
		winner = winEval(players);
	}
	
	public YahtzeePlayer winEval(YahtzeePlayer[] ps) {
		int totalOne = ps[0].getTotal();
		int totalTwo = ps[1].getTotal();
		YahtzeePlayer toReturn;
		if (totalOne > totalTwo) {
			toReturn = ps[0];
		} else {
			toReturn = ps[1];
		}
		return toReturn;
	}

}