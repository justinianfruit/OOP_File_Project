package edu.neumont.csc150.fp.barryj.yahtzee;

import java.util.Random;
import javax.swing.ImageIcon;

public class YahtzeeTurn implements ControlListener {
	int rollCount;
	int sum;
	int total;
	YahtzeeDie[] dieHeld;
	YahtzeeGUI yg;
	YahtzeePlayer player;
	
	public YahtzeeTurn(YahtzeePlayer p) {
		rollCount = 0;
		sum = 0;
		total = 0;
		player = p;
		yg = new YahtzeeGUI(this, p);
		dieHeld = new YahtzeeDie[] { new YahtzeeDie(yg), new YahtzeeDie(yg), new YahtzeeDie(yg), new YahtzeeDie(yg),
				new YahtzeeDie(yg) };
	}
	
	@Override
	public void holdDie(int i) {
		dieHeld[i].flipBoolean();
		yg.updateUI(i, dieHeld[i]);
	}

	@Override
	public void roll() {
		if (rollCount < 3) {
			Random gen = new Random();
			for (int i = 0; i < 5; i++) {
				if (!dieHeld[i].isHeld()) {
					dieHeld[i].setDieSymbol(gen.nextInt(6) + 1);
					dieHeld[i].setDieFace(new ImageIcon(
							"/edu/neumont/csc150/fp/barryj/images/" + dieHeld[i].getDieSymbol() + ".jpg"));
					yg.updateDie(dieHeld[i]);
					yg.updateUI(i, dieHeld[i]);
				}
			}
			rollCount++;
		}
	}

	@Override
	public void lowScores(int i) {
		yg.scoreNum[i].setText(Integer.toString(numCount(i + 1) * (i + 1)));
		player.setSum(player.getSum() + (numCount(i + 1) * (i + 1)));
		total += (numCount(i + 1) * (i + 1));
		for (int j = 0; j < 5; j++) {
			if (dieHeld[j].isHeld()) {
				dieHeld[j].setDieFace(
						new ImageIcon("/edu/neumont/csc150/fp/barryj/images/" + dieHeld[j].getDieSymbol() + ".jpg"));
				dieHeld[j].flipBoolean();
				yg.updateDie(dieHeld[j]);
				yg.updateUI(j, dieHeld[j]);
			}
		}
		yg.scoreNum[6].setText(Integer.toString(sum));
		if (sum > 63) {
			yg.scoreNum[7].setText(Integer.toString(35));
			total += 35;
		}
		yg.scoreNum[15].setText(Integer.toString(total));
		rollCount = 0;
	}

	@Override
	public void scoreThrees() {
		int three = 0;
		for (int i = 0; i < 5; i++) {
			if (numCount(dieHeld[i].getDieSymbol()) >= 3) {
				three = dieHeld[i].getDieSymbol() * 3;
			}
		}
		for (int j = 0; j < 5; j++) {
			if (dieHeld[j].isHeld()) {
				dieHeld[j].setDieFace(
						new ImageIcon("/edu/neumont/csc150/fp/barryj/images/" + dieHeld[j].getDieSymbol() + ".jpg"));
				dieHeld[j].flipBoolean();
				yg.updateDie(dieHeld[j]);
				yg.updateUI(j, dieHeld[j]);
			}
		}
		yg.scoreNum[8].setText(Integer.toString(three));
		rollCount = 0;
		total += three;
		yg.scoreNum[15].setText(Integer.toString(total));
	}

	@Override
	public void scoreFours() {
		int four = 0;
		for (int i = 0; i < 5; i++) {
			if (numCount(dieHeld[i].getDieSymbol()) >= 4) {
				four = dieHeld[i].getDieSymbol() * 4;
			}
		}
		for (int j = 0; j < 5; j++) {
			if (dieHeld[j].isHeld()) {
				dieHeld[j].setDieFace(
						new ImageIcon("/edu/neumont/csc150/fp/barryj/images/" + dieHeld[j].getDieSymbol() + ".jpg"));
				dieHeld[j].flipBoolean();
				yg.updateDie(dieHeld[j]);
				yg.updateUI(j, dieHeld[j]);
			}
		}
		yg.scoreNum[9].setText(Integer.toString(four));
		rollCount = 0;
		total += four;
		yg.scoreNum[15].setText(Integer.toString(total));
	}

	@Override
	public void scoreFullH() {
		int ref = 0;
		boolean threeOfAKind = false;
		for (int i = 0; i < 5; i++) {
			if (numCount(dieHeld[i].getDieSymbol()) == 3) {
				threeOfAKind = true;
			}
		}
		for (int i = 0; i < 5; i++) {
			if (numCount(dieHeld[i].getDieSymbol()) == 2 && threeOfAKind) {
				ref = 25;
				yg.scoreNum[10].setText(Integer.toString(ref));
			}
		}
		total += ref;
		yg.scoreNum[15].setText(Integer.toString(total));
		rollCount = 0;
		for (int j = 0; j < 5; j++) {
			if (dieHeld[j].isHeld()) {
				dieHeld[j].setDieFace(
						new ImageIcon("/edu/neumont/csc150/fp/barryj/images/" + dieHeld[j].getDieSymbol() + ".jpg"));
				dieHeld[j].flipBoolean();
				yg.updateDie(dieHeld[j]);
				yg.updateUI(j, dieHeld[j]);
			}
		}
	}

	@Override
	public void scoreSmallS() {
		int ref = 0;
		int count = 0;
		int smallS = 0;
		for (int i = 0; i < 5; i++) {
			ref = dieHeld[i].getDieSymbol();
			for (int j = 0; j < 5; j++) {
				if (dieHeld[j].getDieSymbol() == ref + 1) {
					ref += 1;
					count += 1;
				}
			}
		}
		if (count > 2) {
			smallS = 30;
			total += smallS;
		}
		for (int j = 0; j < 5; j++) {
			if (dieHeld[j].isHeld()) {
				dieHeld[j].setDieFace(
						new ImageIcon("/edu/neumont/csc150/fp/barryj/images/" + dieHeld[j].getDieSymbol() + ".jpg"));
				dieHeld[j].flipBoolean();
				yg.updateDie(dieHeld[j]);
				yg.updateUI(j, dieHeld[j]);
			}
		}
		yg.scoreNum[11].setText(Integer.toString(smallS));
		yg.scoreNum[15].setText(Integer.toString(total));
		rollCount = 0;
	}

	@Override
	public void scoreLargeS() {
		int ref = 0;
		int count = 0;
		int largeS = 0;
		for (int i = 0; i < 5; i++) {
			ref = dieHeld[i].getDieSymbol();
			for (int j = 0; j < 5; j++) {
				if (dieHeld[j].getDieSymbol() == ref + 1) {
					ref += 1;
					count += 1;
				}
			}
		}
		if (count > 3) {
			largeS = 40;
			total += largeS;
		}
		for (int j = 0; j < 5; j++) {
			if (dieHeld[j].isHeld()) {
				dieHeld[j].setDieFace(
						new ImageIcon("/edu/neumont/csc150/fp/barryj/images/" + dieHeld[j].getDieSymbol() + ".jpg"));
				dieHeld[j].flipBoolean();
				yg.updateDie(dieHeld[j]);
				yg.updateUI(j, dieHeld[j]);
			}
		}
		yg.scoreNum[12].setText(Integer.toString(largeS));
		yg.scoreNum[15].setText(Integer.toString(total));
		rollCount = 0;
	}

	@Override
	public void scoreChance() {
		int diceSum = 0;
		for (int i = 0; i < 5; i++) {
			diceSum += dieHeld[i].getDieSymbol();
		}
		yg.scoreNum[13].setText(Integer.toString(diceSum));
		total += diceSum;
		yg.scoreNum[15].setText(Integer.toString(total));
		rollCount = 0;
		for (int j = 0; j < 5; j++) {
			if (dieHeld[j].isHeld()) {
				dieHeld[j].setDieFace(
						new ImageIcon("/edu/neumont/csc150/fp/barryj/images/" + dieHeld[j].getDieSymbol() + ".jpg"));
				dieHeld[j].flipBoolean();
				yg.updateDie(dieHeld[j]);
				yg.updateUI(j, dieHeld[j]);
			}
		}
	}

	@Override
	public void scoreYahtzee() {
		int five = 0;
		for (int i = 0; i < 5; i++) {
			if (numCount(dieHeld[i].getDieSymbol()) == 5) {
				five = 50;
			}
		}
		for (int j = 0; j < 5; j++) {
			if (dieHeld[j].isHeld()) {
				dieHeld[j].setDieFace(
						new ImageIcon("/edu/neumont/csc150/fp/barryj/images/" + dieHeld[j].getDieSymbol() + ".jpg"));
				dieHeld[j].flipBoolean();
				yg.updateDie(dieHeld[j]);
				yg.updateUI(j, dieHeld[j]);
			}
		}
		yg.scoreNum[14].setText(Integer.toString(five));
		rollCount = 0;
		total += five;
		yg.scoreNum[15].setText(Integer.toString(total));
	}

	@Override
	public void resetGame() {
		rollCount = 0;
		total = 0;
		for (int i = 0; i < 5; i++) {
			dieHeld[i].setDieSymbol(0);
			dieHeld[i].setDieFace(new ImageIcon(""));
			if (dieHeld[i].isHeld()) {
				dieHeld[i].flipBoolean();
				yg.updateDie(dieHeld[i]);
				yg.updateUI(i, dieHeld[i]);
			}
		}
	}

	@Override
	public int numCount(int num) {
		int ref = 0;
		for (int i = 0; i < 5; i++) {
			if (num == dieHeld[i].getDieSymbol()) {
				ref += 1;
			}
		}
		return ref;
	}

	@Override
	public int returnRollsUsed() {
		return rollCount;
	}
}
