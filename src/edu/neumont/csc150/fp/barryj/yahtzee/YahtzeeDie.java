package edu.neumont.csc150.fp.barryj.yahtzee;

import edu.neumont.csc150.fp.barryj.Die;

public class YahtzeeDie extends Die {
	private String imagePath;
	private boolean isHeld;
	private ObjectListener ol;

	public YahtzeeDie(ObjectListener listener) {
		ol = listener;
		evalSymb(getDieSymbol());
		changeImage(imagePath);
	}
	
	public void evalSymb(int symbol) {
		switch (symbol) {
		case 1:
			imagePath = "edu/neumont/csc150/fp/barryj/images/1.jpg";
			break;
		case 2:
			imagePath = "edu/neumont/csc150/fp/barryj/images/2.jpg";
			break;
		case 3:
			imagePath = "edu/neumont/csc150/fp/barryj/images/3.jpg";
			break;
		case 4: 
			imagePath = "edu/neumont/csc150/fp/barryj/images/4.jpg";
			break;
		case 5:
			imagePath = "edu/neumont/csc150/fp/barryj/images/5.jpg";
			break;
		case 6:
			imagePath = "edu/neumont/csc150/fp/barryj/images/6.jpg";
			break;
		}
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
