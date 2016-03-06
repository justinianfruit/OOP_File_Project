package edu.neumont.csc150.fp.barryj;

import edu.neumont.csc150.fp.barryj.yahtzee.YahtzeeDie;

public interface ObjectListener {

	public void updateDie(YahtzeeDie die);
	
	public void updateUI(int i, YahtzeeDie die);
	
}
