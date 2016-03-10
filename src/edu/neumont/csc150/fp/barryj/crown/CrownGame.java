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
		dice = new CrownDie[] { new CrownDie(cg), new CrownDie(cg), new CrownDie(cg) };
		for (int j = 0; j < 3; j++) {
			cg.updateDie(dice[j]);
			cg.updateUI(j, dice[j]);
		}
		cg.setDice(dice);
	}

	@Override
	public void roll() {
		for (int i = 0; i < 3; i++) {
			dice[i].setDieSymbol(gen.nextInt(6) + 1);
			dice[i].setDieFace(
					new ImageIcon("/edu/neumont/csc150/fp/barryj/images/" + dice[i].getDieSymbol() + ".jpg"));
			cg.updateDie(dice[i]);
			cg.updateUI(i, dice[i]);
		}
		checkOutcome();
	}

	@Override
	public void checkOutcome() {

	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public void setScore(int score) {
		this.score = score;
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
