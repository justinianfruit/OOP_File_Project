package edu.neumont.csc150.fp.barryj.yahtzee;

import java.util.Random;

import edu.neumont.csc150.fp.barryj.Die;

public class YahtzeeDie extends Die {
	private boolean isHeld;
	private ObjectListener ol;
	private Random randGen = new Random();

	public YahtzeeDie(ObjectListener listener) {
		isHeld = false;
		ol = listener;
		setDieSymbol(randGen.nextInt(6) + 1);
		changeImage("/edu/neumont/csc150/fp/barryj/images/" + getDieSymbol() + ".jpg");
	}

	public boolean isHeld() {
		return isHeld;
	}
	public void setisHeld(boolean dieHeld) {
		this.isHeld = dieHeld;
	}
	
	public void flipBoolean() {
		setisHeld(!isHeld());
		ol.updateDie(this);
	}
}
