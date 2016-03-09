package edu.neumont.csc150.fp.barryj.zombie;

import java.util.ArrayList;
import java.util.Scanner;

public class ZombieTurn{
	Scanner scanPlayerInput = new Scanner(System.in);
	boolean winner = false;
	
	/**
	 * 
	 * @param currentPlayer
	 * currentPlayer is an instance of the ZombiePlayer class, passed
	 * 		in from the ZombieGame class which owns the collection
	 * 		of ZombiePlayers.
	 * This method keeps track of the currentPlayer's scores for their 
	 * 		turn. Additionally, it stores dice that are held and prints
	 * 		the roll outcome to the console. Finally, it evaluates if
	 * 		the currentPlayer has taken too much damage to continue
	 * 		with their turn. If currentPlayer ends their turn willingly,
	 * 		this method adds the score collected for the turn to the
	 * 		currentPlayer total score.
	 */
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
				if (roll[i].getDieSymbol() == 1 || roll[i].getDieSymbol() == 4 || roll[i].getDieSymbol() == 7) {
					br += 1;
					symbol = "brain";
					diceSetAside.add(roll[i]);
				} else if (roll[i].getDieSymbol() == 2 || roll[i].getDieSymbol() == 5 || roll[i].getDieSymbol() == 8) {
					fs += 1;
					symbol = "footsteps";
					for (int j = 0; j < 3; j++) {
						if (diceHeld[j] == null) {
							diceHeld[j] = roll[i];
							j = 3;
						}
					}
				} else if (roll[i].getDieSymbol() == 3 || roll[i].getDieSymbol() == 6 || roll[i].getDieSymbol() == 9) {
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

	/**
	 * 
	 * @param currentPlayer
	 * currentPlayer is an instance of the ZombiePlayer class, passed
	 * 		in from the ZombieGame class which owns the collection
	 * 		of ZombiePlayers.
	 * This method evaluates the total score of currentPlayer in 
	 * 		order to see if they have reached the base score for
	 * 		a "winning status". If the condition is met, this method
	 * 		sets the game boolean of winner to true and sets the 
	 * 		currentPlayer's winStatus to true also. 
	 */
	public void winEvaluation(ZombiePlayer currentPlayer) {
		if (currentPlayer.getBrainsEaten() >= 13) {
			String name = currentPlayer.getPlayerName();
			System.out.println(name + " has reached 13 brains! Take your final turn.");
			winner = true;
			currentPlayer.setWinStatus(winner);
		}
	}

	/**
	 * 
	 * @param players
	 * players is an array of ZombiePlayer instances that belongs
	 * 		to the ZombieGame class and is passed in.
	 * This method checks and compares the total scores of each
	 * 		ZombiePlayer and evaluates which has the highest
	 * 		score. It then prints this announcement. 
	 */
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
