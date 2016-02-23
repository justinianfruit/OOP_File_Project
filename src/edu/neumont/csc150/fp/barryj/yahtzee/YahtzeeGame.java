package edu.neumont.csc150.fp.barryj.yahtzee;

public class YahtzeeGame implements ControlListener {
	int rollCount;
	int sum;
	int total;
	YahtzeeDie[] dieHeld;
	int[] diceVals;
	
	public YahtzeeGame() {
		rollCount = 0;
		sum = 0;
		total = 0;
		YahtzeeGUI yg = new YahtzeeGUI(this);
		dieHeld = new YahtzeeDie[]{new YahtzeeDie(yg),new YahtzeeDie(yg),new YahtzeeDie(yg),new YahtzeeDie(yg),new YahtzeeDie(yg)};
		for (int i = 0; i < diceVals.length; i++) {
			diceVals[i] = dieHeld[i].getDieSymbol();
		}
	}

	@Override
	public void holdDie(int i) {
		dieHeld[i].flipBoolean();
	}

}
