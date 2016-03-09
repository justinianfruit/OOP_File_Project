package edu.neumont.csc150.fp.barryj.zombie;

import edu.neumont.csc150.fp.barryj.Die;

public class ZombieDie extends Die {
	private String dieColor;
	
	public ZombieDie(String color, int symbol) {
		dieColor = color;
		super.setDieSymbol(symbol);
		if (symbol == 1) {
			changeImage("/edu/neumont/csc150/fp/barryj/images/z" + getDieSymbol() + ".jpg");
		} else if(symbol == 2) {
			changeImage("/edu/neumont/csc150/fp/barryj/images/z" + getDieSymbol() + ".jpg");
		} else if(symbol == 3) {
			changeImage("/edu/neumont/csc150/fp/barryj/images/z" + getDieSymbol() + ".jpg");
		} else if(symbol == 4) {
			changeImage("/edu/neumont/csc150/fp/barryj/images/z" + getDieSymbol() + ".jpg");
		} else if(symbol == 5) {
			changeImage("/edu/neumont/csc150/fp/barryj/images/z" + getDieSymbol() + ".jpg");
		} else if(symbol == 6) {
			changeImage("/edu/neumont/csc150/fp/barryj/images/z" + getDieSymbol() + ".jpg");
		} else if(symbol == 7) {
			changeImage("/edu/neumont/csc150/fp/barryj/images/z" + getDieSymbol() + ".jpg");
		} else if(symbol == 8) {
			changeImage("/edu/neumont/csc150/fp/barryj/images/z" + getDieSymbol() + ".jpg");
		} else if(symbol == 9) {
			changeImage("/edu/neumont/csc150/fp/barryj/images/z" + getDieSymbol() + ".jpg");
		}
	}

	public String getDieColor() {
		return dieColor;
	}

	public void setDieColor(String dieColor) {
		this.dieColor = dieColor;
	}
}
