package edu.neumont.csc150.fp.barryj.zombie;

import java.util.Random;

public class ZombieCup {

	private String dieColor;
	private int dieSymbol;
	Random randNumGen = new Random();
	private int[] greenSymbols = {1,1,1,2,2,3};
	private int[] yellowSymbols = {1,1,2,2,3,3};
	private int[] redSymbols = {1,2,2,3,3,3};
	private int numOfFootsteps = 0;
	private static ZombieDie[] insideCup = new ZombieDie[13];
	ZombieDie[] diceRolled;
	
	public ZombieCup() {
		initializeCup();
		rollDice(3);
	}
	
	public void initializeCup() {
		determineColor();
		shuffleDice(insideCup);
	}
	
	public void determineColor() {
		for (int i = 0; i < 13; i++) {
			if (i <= 5) {
				dieColor = "Green";
				determineSymbol();
				insideCup[i] = new ZombieDie(dieColor, dieSymbol);
			} else if (i > 5 && i < 10) {
				dieColor = "Yellow";
				determineSymbol();
				insideCup[i] = new ZombieDie(dieColor, dieSymbol);
			} else if (i >= 10) {
				dieColor = "Red";
				determineSymbol();
				insideCup[i] = new ZombieDie(dieColor, dieSymbol);
			}
		}
	}
	
	public void shuffleDice(ZombieDie[] insideCup) {
		for (int i = 0; i < 5; i++) {
			for (int j = insideCup.length - 1; j > 0; j--) {
				int index = randNumGen.nextInt(j + 1);
				ZombieDie swap = insideCup[index];
				insideCup[index] = insideCup[j];
				insideCup[j] = swap;
			}
		}
	}
	
	public int determineSymbol() {
		if (dieColor.equals("Green")) {
			int index = randNumGen.nextInt(5);
			dieSymbol = greenSymbols[index];
		} else if (dieColor.equals("Yellow")) {
			int index = randNumGen.nextInt(5);
			dieSymbol = yellowSymbols[index];
		} else if (dieColor.equals("Red")) {
			int index = randNumGen.nextInt(5);
			dieSymbol = redSymbols[index];
		}
		return dieSymbol;
	}
	
	public void rollDice(int numOfDice) {
		System.out.println();
		diceRolled = new ZombieDie[numOfDice];
		int index = 0;
		for (int i = 0; i < numOfDice; i++) {
			index = randNumGen.nextInt(13);
			if (insideCup[index] != null) {
				diceRolled[i] = insideCup[index];
				insideCup[index] = null;
			} else if (insideCup[index] == null) {
				i--;
			}
		}
		
	}
	public int getInsideCup() {
		int num = 0;
		for (int i = 0; i < insideCup.length; i++) {
			if (insideCup[i] != null) {
				num++;
			}
		}
		return num;
	}
	
	public void setInsideCup(ZombieDie returnDie) {
		for (int i = 0; i < insideCup.length; i++) {
			if (insideCup[i] == null) {
				insideCup[i] = returnDie;
			}
		}
	}

	public String getDieColor() {
		return dieColor;
	}

	public int getDieSymbol() {
		return dieSymbol;
	}

	public void setDieSymbol(int dieSymbol) {
		this.dieSymbol = dieSymbol;
	}

	public int getNumOfFootsteps() {
		return numOfFootsteps;
	}

	public void setNumOfFootsteps(int numOfFootsteps) {
		this.numOfFootsteps = numOfFootsteps;
	}

	public ZombieDie[] getDiceRolled() {
		return diceRolled;
	}
}
