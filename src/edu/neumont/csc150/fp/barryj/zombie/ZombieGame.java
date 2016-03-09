package edu.neumont.csc150.fp.barryj.zombie;

import java.util.Scanner;

import edu.neumont.csc150.fp.barryj.Turner;

public class ZombieGame implements Turner {
	Scanner scanPlayerInput = new Scanner(System.in);
	ZombiePlayer[] players;
	ZombieTurn zt;
	int numOfPlayers;

	public ZombieGame() {
		zt = new ZombieTurn();
		playGame();
	}

	public void playGame() {
		greetPlayers();
		switchTurn();
	}

	public void greetPlayers() {
		String name;
		System.out.println("Welcome to the Zombie Dice! Enjoy the game!");
		System.out.println("How many players are there? (2-4)");
		numOfPlayers = Integer.parseInt(scanPlayerInput.nextLine());
		players = new ZombiePlayer[numOfPlayers];
		for (int i = 0; i < numOfPlayers; i++) {
			System.out.println("Please enter a name for Player " + (i + 1) + ": ");
			name = scanPlayerInput.nextLine();
			if (name.length() == 0) {
				name = "Player " + (i + 1);
			}
			players[i] = new ZombiePlayer(name);
		}
	}

	@Override
	public void switchTurn() {
		while (!zt.winner) {
			for (int i = 0; i < players.length; i++) {
				zt.playerTurn(players[i]);
				zt.winEvaluation(players[i]);
			}
		}
		if (zt.winner) {
			for (int i = 0; i < players.length - 1; i++) {
				if (players[i].getWinStatus() != true) {
					zt.playerTurn(players[i]);
				}
			}
			zt.declareWinner(players);
		}
	}


	
}
