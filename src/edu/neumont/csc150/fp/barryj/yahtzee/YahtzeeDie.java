package edu.neumont.csc150.fp.barryj.yahtzee;

import java.util.Random;

import edu.neumont.csc150.fp.barryj.Die;
import edu.neumont.csc150.fp.barryj.ObjectListener;

public class YahtzeeDie extends Die {
	private Random randGen = new Random();

	public YahtzeeDie(ObjectListener listener) {
		setisHeld(false);
		super.ol = listener;
		setDieSymbol(randGen.nextInt(6) + 1);
		changeImage("/edu/neumont/csc150/fp/barryj/images/" + getDieSymbol() + ".jpg");
	}

}
