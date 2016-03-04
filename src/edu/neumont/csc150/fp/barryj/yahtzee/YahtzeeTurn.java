package edu.neumont.csc150.fp.barryj.yahtzee;

import java.util.Random;
import javax.swing.ImageIcon;

public class YahtzeeTurn implements ControlListener {
	int rollCount;
	YahtzeeDie[] dieHeld;
	YahtzeeGame c;
	YahtzeeGUI yg;
	YahtzeePlayer player;

	public YahtzeeTurn(YahtzeeGame control, YahtzeePlayer p, YahtzeeGUI newUI) {
		rollCount = 0;
		c = control;
		player = p;
		yg = newUI;
		dieHeld = new YahtzeeDie[] { new YahtzeeDie(yg), new YahtzeeDie(yg), new YahtzeeDie(yg), new YahtzeeDie(yg),
				new YahtzeeDie(yg) };
		for (int j = 0; j < 5; j++) {
			yg.updateDie(dieHeld[j]);
			yg.updateUI(j, dieHeld[j]);
		}
		yg.initialize(p, this);
	}

	@Override
	public void holdDie(int i) {
		dieHeld[i].flipBoolean();
		yg.updateDie(dieHeld[i]);
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
		player.setLowScore(i, (numCount(i + 1) * (i + 1)));
		player.setSum(player.getSum() + (numCount(i + 1) * (i + 1)));
		player.setTotal(player.getTotal() + (numCount(i + 1) * (i + 1)));
		for (int j = 0; j < 5; j++) {
			if (dieHeld[j].isHeld()) {
				dieHeld[j].flipBoolean();
				dieHeld[j].setDieFace(
						new ImageIcon("/edu/neumont/csc150/fp/barryj/images/" + dieHeld[j].getDieSymbol() + ".jpg"));
				yg.updateDie(dieHeld[j]);
				yg.updateUI(j, dieHeld[j]);
			}
		}
		yg.scoreNum[6].setText(Integer.toString(player.getSum()));
		if (player.getSum() > 63) {
			yg.scoreNum[7].setText(Integer.toString(35));
			player.setTotal(player.getTotal() + 35);
		}
		yg.scoreNum[15].setText(Integer.toString(player.getTotal()));
		rollCount = 0;
		player.setTakingTurn(false);
		c.switchTurn();
	}

	@Override
	public void scoreThrees() {
		int three = 0;
		for (int i = 0; i < 5; i++) {
			if (numCount(dieHeld[i].getDieSymbol()) >= 3) {
				three = dieHeld[i].getDieSymbol() * 3;
			}
		}
		player.setToaK(three);
		for (int j = 0; j < 5; j++) {
			if (dieHeld[j].isHeld()) {
				dieHeld[j].flipBoolean();
				dieHeld[j].setDieFace(
						new ImageIcon("/edu/neumont/csc150/fp/barryj/images/" + dieHeld[j].getDieSymbol() + ".jpg"));
				yg.updateDie(dieHeld[j]);
				yg.updateUI(j, dieHeld[j]);
			}
		}
		yg.scoreNum[8].setText(Integer.toString(player.getToaK()));
		rollCount = 0;
		player.setTotal(player.getTotal() + player.getToaK());
		yg.scoreNum[15].setText(Integer.toString(player.getTotal()));
		player.setTakingTurn(false);
		c.switchTurn();
	}

	@Override
	public void scoreFours() {
		int four = 0;
		for (int i = 0; i < 5; i++) {
			if (numCount(dieHeld[i].getDieSymbol()) >= 4) {
				four = dieHeld[i].getDieSymbol() * 4;
			}
		}
		player.setFoaK(four);
		for (int j = 0; j < 5; j++) {
			if (dieHeld[j].isHeld()) {
				dieHeld[j].flipBoolean();
				dieHeld[j].setDieFace(
						new ImageIcon("/edu/neumont/csc150/fp/barryj/images/" + dieHeld[j].getDieSymbol() + ".jpg"));
				yg.updateDie(dieHeld[j]);
				yg.updateUI(j, dieHeld[j]);
			}
		}
		yg.scoreNum[9].setText(Integer.toString(player.getFoaK()));
		rollCount = 0;
		player.setTotal(player.getTotal() + player.getFoaK());
		yg.scoreNum[15].setText(Integer.toString(player.getTotal()));
		player.setTakingTurn(false);
		c.switchTurn();
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
				yg.scoreNum[10].setText(Integer.toString(player.getFullHouse()));
			}
		}
		player.setFullHouse(ref);
		player.setTotal(player.getTotal() + player.getFullHouse());
		yg.scoreNum[15].setText(Integer.toString(player.getTotal()));
		rollCount = 0;
		for (int j = 0; j < 5; j++) {
			if (dieHeld[j].isHeld()) {
				dieHeld[j].flipBoolean();
				dieHeld[j].setDieFace(
						new ImageIcon("/edu/neumont/csc150/fp/barryj/images/" + dieHeld[j].getDieSymbol() + ".jpg"));
				yg.updateDie(dieHeld[j]);
				yg.updateUI(j, dieHeld[j]);
			}
		}
		player.setTakingTurn(false);
		c.switchTurn();
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
		}
		player.setSmallS(smallS);
		player.setTotal(player.getTotal() + player.getSmallS());
		for (int j = 0; j < 5; j++) {
			if (dieHeld[j].isHeld()) {
				dieHeld[j].flipBoolean();
				dieHeld[j].setDieFace(
						new ImageIcon("/edu/neumont/csc150/fp/barryj/images/" + dieHeld[j].getDieSymbol() + ".jpg"));
				yg.updateDie(dieHeld[j]);
				yg.updateUI(j, dieHeld[j]);
			}
		}
		yg.scoreNum[11].setText(Integer.toString(player.getSmallS()));
		yg.scoreNum[15].setText(Integer.toString(player.getTotal()));
		rollCount = 0;
		player.setTakingTurn(false);
		c.switchTurn();
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
		}
		player.setLargeS(largeS);
		player.setTotal(player.getTotal() + player.getLargeS());
		for (int j = 0; j < 5; j++) {
			if (dieHeld[j].isHeld()) {
				dieHeld[j].flipBoolean();
				dieHeld[j].setDieFace(
						new ImageIcon("/edu/neumont/csc150/fp/barryj/images/" + dieHeld[j].getDieSymbol() + ".jpg"));
				yg.updateDie(dieHeld[j]);
				yg.updateUI(j, dieHeld[j]);
			}
		}
		yg.scoreNum[12].setText(Integer.toString(player.getLargeS()));
		yg.scoreNum[15].setText(Integer.toString(player.getTotal()));
		rollCount = 0;
		player.setTakingTurn(false);
		c.switchTurn();
	}

	@Override
	public void scoreChance() {
		int diceSum = 0;
		for (int i = 0; i < 5; i++) {
			diceSum += dieHeld[i].getDieSymbol();
		}
		player.setChance(diceSum);
		yg.scoreNum[13].setText(Integer.toString(player.getChance()));
		player.setTotal(player.getTotal() + player.getChance());
		yg.scoreNum[15].setText(Integer.toString(player.getTotal()));
		rollCount = 0;
		for (int j = 0; j < 5; j++) {
			if (dieHeld[j].isHeld()) {
				dieHeld[j].flipBoolean();
				dieHeld[j].setDieFace(
						new ImageIcon("/edu/neumont/csc150/fp/barryj/images/" + dieHeld[j].getDieSymbol() + ".jpg"));
				yg.updateDie(dieHeld[j]);
				yg.updateUI(j, dieHeld[j]);
			}
		}
		player.setTakingTurn(false);
		c.switchTurn();
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
				dieHeld[j].flipBoolean();
				dieHeld[j].setDieFace(
						new ImageIcon("/edu/neumont/csc150/fp/barryj/images/" + dieHeld[j].getDieSymbol() + ".jpg"));
				yg.updateDie(dieHeld[j]);
				yg.updateUI(j, dieHeld[j]);
			}
		}
		player.setYahtzee(five);
		player.setTotal(player.getTotal() + player.getYahtzee());
		yg.scoreNum[14].setText(Integer.toString(player.getYahtzee()));
		rollCount = 0;
		player.setTotal(player.getTotal() + player.getYahtzee());
		yg.scoreNum[15].setText(Integer.toString(player.getTotal()));
		player.setTakingTurn(false);
		c.switchTurn();
	}

	@Override
	public void resetGame() {
		rollCount = 0;
		for (int i = 0; i < 5; i++) {
			dieHeld[i].setDieSymbol(0);
			dieHeld[i].setDieFace(new ImageIcon(""));
			if (dieHeld[i].isHeld()) {
				dieHeld[i].flipBoolean();
			}
			yg.updateDie(dieHeld[i]);
			yg.updateUI(i, dieHeld[i]);
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
