package edu.neumont.csc150.fp.barryj;

public class ZombieDie extends Die {
	private String dieColor;

	public ZombieDie() {
		
	}
	
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
