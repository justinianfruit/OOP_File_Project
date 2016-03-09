package edu.neumont.csc150.fp.barryj.zombie;

public class ZombiePlayer {

	private String playerName;
	private int survivorsCornered = 0;
	private int brainsEaten = 0;
	private int blastsTaken = 0;
	private boolean winner = false;
	
	public ZombiePlayer(String enteredName) {
		playerName = enteredName;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayerName(String name) {
		this.playerName = name;
	}
	
	public int getSurvivors() {
		return survivorsCornered;
	}

	public void setSurvivors(int survivorsCornered) {
		this.survivorsCornered = survivorsCornered;
	}
	
	public int getBrainsEaten() {
		return brainsEaten;
	}
	
	public void setBrainsEaten(int addBrains) {
		brainsEaten += addBrains;
	}
	
	public int getBlastsTaken() {
		return blastsTaken;
	}

	public void setBlastsTaken(int blastsTaken) {
		this.blastsTaken = blastsTaken;
	}
	
	public int addSurvivors(int addSurvivors) {
		int newTotalS = survivorsCornered + addSurvivors;
		return newTotalS;
	}
	
	public int addBlasts(int addBlasts) {
		int newTotalB = blastsTaken + addBlasts;
		return newTotalB;
	}
	
	public boolean getWinStatus() {
		return winner;
	}
	
	public void setWinStatus(boolean winner) {
		this.winner = winner;
	}
}
