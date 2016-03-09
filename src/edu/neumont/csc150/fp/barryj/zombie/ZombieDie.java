package edu.neumont.csc150.fp.barryj.zombie;

import edu.neumont.csc150.fp.barryj.Die;

public class ZombieDie extends Die {
	private String dieColor;
	
	public ZombieDie(String color, int symbol) {
		dieColor = color;
		super.setDieSymbol(symbol);
	}

	public String getDieColor() {
		return dieColor;
	}

	public void setDieColor(String dieColor) {
		this.dieColor = dieColor;
	}
}
