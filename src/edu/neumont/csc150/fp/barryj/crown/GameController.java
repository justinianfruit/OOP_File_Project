package edu.neumont.csc150.fp.barryj.crown;

public interface GameController {
	
	public void roll();
	
	public void checkOutcome();
	
	public int checkNum(int num);
	
	public int getScore();
	
	public CrownDie getSelected();
	
	public void setSelected(CrownDie cd);

}
