package CSCI_e10.unit_3a;



public class Craps {

	static int rollDie() {
		return (int) ((Math.random() * 6) + 1);
	}

	static boolean isPoint(int in) {
		boolean point = false;

		if (in == 4)
			point = true;
		if (in == 5)
			point = true;
		if (in == 6)
			point = true;
		if (in == 8)
			point = true;
		if (in == 9)
			point = true;
		if (in == 10)
			point = true;

		return point;
	}
	
	public static int die1;
	static int die2;
	public static int total1;
	public static int total2;

	public static void main(String[] args) {

		boolean pointSet = false;

		while (!pointSet) {
			die1 = rollDie();
			die2 = rollDie();
			total1 = die1 + die2;

			System.out.println("Computer rolls a " + die1 + " and a " + die2 + ", for a total of " + total1 + ".");

			if (isPoint(total1)) {
				System.out.println(total1 + " is now the established POINT.");
				pointSet = true;
			}
		}

		boolean done = false;

		while (!done) {
			die1 = rollDie();
			die2 = rollDie();
			total2 = die1 + die2;
			
			System.out.println("Computer rolls a " + die1 + " and a " + die2 + ", for a total of " + total2 + ".");

			if (total2 == 7) {
				System.out.println("YOU LOSE");
				done = true;
			}
			if (total2 == total1) {
				System.out.println("YOU WIN");
				done = true;
			}
		}
	}

}

