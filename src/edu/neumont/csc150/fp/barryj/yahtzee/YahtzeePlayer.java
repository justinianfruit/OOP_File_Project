package edu.neumont.csc150.fp.barryj.yahtzee;

public class YahtzeePlayer {
	int number;
	Integer[] playerVals;
	boolean takingTurn;
	
	public YahtzeePlayer(int i) {
		number = i;
		playerVals = new Integer[16];
		takingTurn = true;
	}

	public boolean isTakingTurn() {
		return takingTurn;
	}
	public void setTakingTurn(boolean takingTurn) {
		this.takingTurn = takingTurn;
	}

	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getLowScore(int i) {
		return playerVals[i];
	}
	public void setLowScore(int i, int score) {
		playerVals[i] = score;
	}

	public int getSum() {
		return playerVals[6];
	}
	public void setSum(int sum) {
		playerVals[6] = sum;
	}

	public int getBonus() {
		return playerVals[7];
	}
	public void setBonus(int bonus) {
		playerVals[7] = bonus;
	}

	public int getToaK() {
		return playerVals[8];
	}
	public void setToaK(int toaK) {
		playerVals[8] = toaK;
	}

	public int getFoaK() {
		return playerVals[9];
	}
	public void setFoaK(int foaK) {
		playerVals[9] = foaK;
	}

	public int getFullHouse() {
		return playerVals[10];
	}
	public void setFullHouse(int fullHouse) {
		playerVals[10] = fullHouse;
	}

	public int getSmallS() {
		return playerVals[11];
	}
	public void setSmallS(int smallS) {
		playerVals[11] = smallS;
	}
	
	public int getLargeS() {
		return playerVals[12];
	}
	public void setLargeS(int largeS) {
		playerVals[12] = largeS;
	}

	public int getChance() {
		return playerVals[13];
	}
	public void setChance(int chance) {
		playerVals[13] = chance;
	}

	public int getYahtzee() {
		return playerVals[14];
	}
	public void setYahtzee(int yahtzee) {
		playerVals[14] = yahtzee;
	}

	public int getTotal() {
		return playerVals[15];
	}
	public void setTotal(int total) {
		playerVals[15] = total;
	}

}
