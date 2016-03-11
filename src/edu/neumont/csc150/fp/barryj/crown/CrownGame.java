package edu.neumont.csc150.fp.barryj.crown;

import java.util.Random;
import javax.swing.ImageIcon;

public class CrownGame implements GameController {
	Random gen = new Random();
	int score;
	CandAGUI cg;
	CrownDie selectedDie;
	CrownDie[] dice;

	public CrownGame() {
		score = 0;
		cg = new CandAGUI(this, score);
		selectedDie = new CrownDie(cg);
		dice = new CrownDie[] { new CrownDie(cg), new CrownDie(cg), new CrownDie(cg) };
		for (int j = 0; j < 3; j++) {
			cg.updateUI(j, dice[j]);
		}
		cg.setDice(dice);
	}

	@Override
	public void roll() {
		for (int i = 0; i < 3; i++) {
			dice[i].setDieSymbol(gen.nextInt(6) + 1);
			dice[i].changeImage("/edu/neumont/csc150/fp/barryj/images/c" + dice[i].getDieSymbol() + ".png");
			cg.updateUI(i, dice[i]);
		}
		checkOutcome();
	}
	
	@Override
	public int checkNum(int num) {
		int ref = 0;
		for (int i = 0; i < 3; i++) {
			if (num == dice[i].getDieSymbol()) {
				ref += 1;
			}
		}
		return ref;
	}

	@Override
	public void checkOutcome() {
		int increment = 0;
		for (int i = 0; i < 6; i++) {
			if (checkNum(selectedDie.getDieSymbol()) < 1) {
				increment = -3;
			} else if (checkNum(selectedDie.getDieSymbol()) == 1) {
				increment = 1;
			} else if (checkNum(selectedDie.getDieSymbol()) == 2) { 
				increment = 5;
			} else {
				increment = 10;
			}
		}
		score += increment;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public CrownDie getSelected() {
		return null;
	}

	@Override
	public void setSelected(CrownDie cd) {
		selectedDie = cd;
	}


}
