package CSCI_e10.unit_3a;



/**
 * 
 * @author bret blackford 
 * CSCI e10a
 */
public class Drunk {

	static final int ATTEMPTS = 5;

	static int drunkWalk() {

		int steps = 0;
		int block = 5; // midway between 0 and 10
		boolean left = false;
		int turn = 0;

		// while( Math.abs(block) <=5 ) {
		while (block != 0 && block != 10) {
			turn = (int) (Math.random() * 2);
			// System.out.println("turn-" + turn);

			if (turn == 0) {
				left = true;
				steps++;
				block--;
			} else {
				steps++;
				block++;
			}

			// System.out.println("steps-[" + steps + "] block-[" + block +
			// "]");
		}
		if (block == 10) {
			// System.out.println("JAIL");
			steps = steps * (-1);
		} else {
			// System.out.println(" HOME ");
		}
		return steps;
	}

	public static void main(String[] args) {

		int attemptNo = 0;
		while (attemptNo <= ATTEMPTS) {

			System.out.println();
			System.out.println("Here we go again ... time for a walk!");

			int temp = drunkWalk();
			System.out.println("Took " + Math.abs(temp) + " steps, and ");

			if (temp < 0) {
				System.out.println("Landed in JAIL");
			} else {
				System.out.println("Landed at HOME");
			}
			
			attemptNo++;
		}

	}

}

