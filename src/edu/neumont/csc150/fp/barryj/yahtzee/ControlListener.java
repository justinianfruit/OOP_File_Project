package edu.neumont.csc150.fp.barryj.yahtzee;

public interface ControlListener {

	public void holdDie(int i);
	
	public void roll();
	
	public void lowScores(int i);
	
	public void scoreThrees();
	
	public void scoreFours();
	
	public void scoreFullH();
	
	public void scoreSmallS();
	
	public void scoreLargeS();
	
	public void scoreChance();
	
	public void scoreYahtzee();
	
	public void resetGame();
	
	public int numCount(int num);
	
	public int returnRollsUsed();
	
}
