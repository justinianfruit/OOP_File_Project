package edu.neumont.csc150.fp.barryj;

import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public abstract class Die extends JComponent {
	protected ObjectListener ol;
	private boolean isHeld;
	private int dieSymbol;
	private ImageIcon dieFace;
	private Random randGen;

	public void rollDie() {
		randGen = new Random();
		int symbol = randGen.nextInt(6) + 1;
		setDieSymbol(symbol);
	}
	
	public void changeImage(String imagePath) {
		dieFace = new ImageIcon(this.getClass().getResource(imagePath));
		this.setSize(dieFace.getImage().getWidth(null), dieFace.getImage().getHeight(null));
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

	public int getDieSymbol() {
		return dieSymbol;
	}
	public void setDieSymbol(int dieSymbol) {
		this.dieSymbol = dieSymbol;
	}
	
	public ImageIcon getDieFace() {
		return dieFace;
	}
	public void setDieFace(ImageIcon dieFace) {
		this.dieFace = dieFace;
	}
}
