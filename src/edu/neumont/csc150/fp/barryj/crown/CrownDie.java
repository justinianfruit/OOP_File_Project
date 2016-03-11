package edu.neumont.csc150.fp.barryj.crown;

import java.util.Random;
import edu.neumont.csc150.fp.barryj.Die;
import edu.neumont.csc150.fp.barryj.ObjectListener;

public class CrownDie extends Die {
	private Random randGen = new Random();
	
	public CrownDie(ObjectListener listener) {
		super.ol = listener;
		setDieSymbol(randGen.nextInt(6) + 1);
		changeImage("/edu/neumont/csc150/fp/barryj/images/c" + getDieSymbol() + ".png");
	}

}
