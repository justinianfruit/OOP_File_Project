package edu.neumont.csc150.fp.barryj.crown;

public interface GameController {
	
	public void roll();
	
	public void checkOutcome();
	
	public int getScore();
	
	public void setScore(int score);
	
	public CrownDie getSelected();
	
	public void setSelected(CrownDie cd);

}
