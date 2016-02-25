package edu.neumont.csc150.fp.barryj.zombie;

import java.util.ArrayList;
import java.util.Scanner;

public class ZombieGame {
	Scanner scanPlayerInput = new Scanner(System.in);
	ZombiePlayer[] players;
	boolean winner = false;
	int numOfPlayers;

	public static void main(String[] args) {
		ZombieGame zombie = new ZombieGame();
	}

	public ZombieGame() {
		playGame();
	}

	public void playGame() {
		greetPlayers();
		while (!winner) {
			for (int i = 0; i < players.length; i++) {
				playerTurn(players[i]);
				winEvaluation(players[i]);
			}
		}
		if (winner) {
			for (int i = 0; i < players.length - 1; i++) {
				if (players[i].getWinStatus() != true) {
					playerTurn(players[i]);
				}
			}
			declareWinner(players);
		}
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

	public void playerTurn(ZombiePlayer currentPlayer) {
		ZombieCup cup = new ZombieCup();
		ArrayList<ZombieDie> diceSetAside = new ArrayList<ZombieDie>();
		int br = 0, fs = 0, sb = 0;
		boolean takingTurn = true;
		ZombieDie[] diceHeld = new ZombieDie[3];
		System.out.println("\n" + currentPlayer.getPlayerName().toUpperCase());
		while (takingTurn) {
			if (cup.getInsideCup() < 3 && diceSetAside.size() > 0) {
				for (int i = 0; i < diceSetAside.size(); i++) {
					if (diceSetAside.get(i).getDieSymbol() == 1) {
						cup.setInsideCup(diceSetAside.get(i));
						diceSetAside.remove(i);
					}
				}
			}
			cup.rollDice(3);
			ZombieDie[] roll = cup.getDiceRolled();
			for (int i = 0; i < roll.length; i++) {
				if (diceHeld[i] != null) {
					roll[i] = diceHeld[i];
					diceHeld[i] = null;
				}
			}
			String symbol = "";
			fs = 0;

			for (int i = 0; i < roll.length; i++) {
				if (roll[i].getDieSymbol() == 1) {
					br += 1;
					symbol = "brain";
					diceSetAside.add(roll[i]);
				} else if (roll[i].getDieSymbol() == 2) {
					fs += 1;
					symbol = "footsteps";
					for (int j = 0; j < 3; j++) {
						if (diceHeld[j] == null) {
							diceHeld[j] = roll[i];
							j = 3;
						}
					}
				} else if (roll[i].getDieSymbol() == 3) {
					sb += 1;
					symbol = "shotgun blast";
					diceSetAside.add(roll[i]);
				}
				System.out.println(roll[i].getDieColor() + " " + symbol);
			}
			currentPlayer.setSurvivors(br);
			cup.setNumOfFootsteps(fs);
			currentPlayer.setBlastsTaken(sb);
			System.out.println("\t" + "Survivors cornered: " + currentPlayer.getSurvivors());
			System.out.println("\t" + "Shotgun blasts taken: " + currentPlayer.getBlastsTaken());
			if (currentPlayer.getBlastsTaken() >= 3) {
				System.out.println("/n" + "You have taken too much damage!");
				br = 0;
				fs = 0;
				sb = 0;
				currentPlayer.setSurvivors(0);
				cup.setNumOfFootsteps(0);
				currentPlayer.setBlastsTaken(0);
				takingTurn = false;
			} else {
				System.out.println("Would you like to roll again? (Y/N)");
				String rollAgain = scanPlayerInput.nextLine();
				if (rollAgain.equalsIgnoreCase("n")) {
					currentPlayer.setSurvivors(0);
					cup.setNumOfFootsteps(0);
					currentPlayer.setBrainsEaten(br);
					br = 0;
					fs = 0;
					sb = 0;
					takingTurn = false;
					if (currentPlayer.getBrainsEaten() >= 13) {
						currentPlayer.setWinStatus(true);
					}
				}
				if (rollAgain.equalsIgnoreCase("y")) {
					for (int i = 0; i < cup.getNumOfFootsteps(); i++) {
						diceHeld[i].setDieSymbol(cup.determineSymbol());
					}
				}
			}

		}
		System.out.println("\n" + "Brains eaten: " + currentPlayer.getBrainsEaten());
	}

	public void winEvaluation(ZombiePlayer currentPlayer) {
		if (currentPlayer.getBrainsEaten() >= 13) {
			String name = currentPlayer.getPlayerName();
			System.out.println(name + " has reached 13 brains! Take your final turn.");
			winner = true;
			currentPlayer.setWinStatus(winner);
		}
	}

	public void declareWinner(ZombiePlayer[] players) {
		int brains = 0;
		ZombiePlayer theyWon = null;
		for (int i = 0; i < players.length; i++) {
			if (players[i].getWinStatus() == true) {
				if (players[i].getBrainsEaten() > brains) {
					brains = players[i].getBrainsEaten();
					theyWon = players[i];
				}
			}
		}
		String name = theyWon.getPlayerName();
		System.out.println("\n" + name + " won the game!");
	}
}
