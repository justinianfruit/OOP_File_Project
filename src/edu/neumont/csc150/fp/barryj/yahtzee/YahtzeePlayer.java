package edu.neumont.csc150.fp.barryj.yahtzee;

public class YahtzeePlayer {
	int number;
	int one;
	int two;
	int three;
	int four;
	int five;
	int six;
	int sum;
	int bonus;
	int toaK;
	int foaK;
	int fullHouse;
	int smallS;
	int largeS;
	int chance;
	int yahtzee;
	int total;
	
	public YahtzeePlayer(int i) {
		number = i;
		one = 0;
		two = 0;
		three = 0;
		four = 0;
		five = 0;
		six = 0;
		sum = 0;
		bonus = 0;
		toaK = 0;
		foaK = 0;
		fullHouse = 0;
		smallS = 0;
		largeS = 0;
		chance = 0;
		yahtzee = 0;
		total = 0;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getOne() {
		return one;
	}

	public void setOne(int one) {
		this.one = one;
	}

	public int getTwo() {
		return two;
	}

	public void setTwo(int two) {
		this.two = two;
	}

	public int getThree() {
		return three;
	}

	public void setThree(int three) {
		this.three = three;
	}

	public int getFour() {
		return four;
	}

	public void setFour(int four) {
		this.four = four;
	}

	public int getFive() {
		return five;
	}

	public void setFive(int five) {
		this.five = five;
	}

	public int getSix() {
		return six;
	}

	public void setSix(int six) {
		this.six = six;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public int getToaK() {
		return toaK;
	}

	public void setToaK(int toaK) {
		this.toaK = toaK;
	}

	public int getFoaK() {
		return foaK;
	}

	public void setFoaK(int foaK) {
		this.foaK = foaK;
	}

	public int getFullHouse() {
		return fullHouse;
	}

	public void setFullHouse(int fullHouse) {
		this.fullHouse = fullHouse;
	}

	public int getSmallS() {
		return smallS;
	}

	public void setSmallS(int smallS) {
		this.smallS = smallS;
	}

	public int getLargeS() {
		return largeS;
	}

	public void setLargeS(int largeS) {
		this.largeS = largeS;
	}

	public int getChance() {
		return chance;
	}

	public void setChance(int chance) {
		this.chance = chance;
	}

	public int getYahtzee() {
		return yahtzee;
	}

	public void setYahtzee(int yahtzee) {
		this.yahtzee = yahtzee;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
