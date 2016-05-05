package CSCI_e10.unit_3a;
//EXTRA CREDIT

//EXTRA CREDIT

/**
* 
* @author mblackford
* CSCI e-10a
*/
public class TwoRandom {

	/**
	 * Obtains a random number between 1 - 7 (inclusinve)
	 * @return
	 */
	public static int getRandomInt() {
		return (int) ((Math.random() * 7) + 1);
	}

	public static boolean checkForTie(int x, int y) {

		if (x == y) {
			return true;
		}
		return false;
	}

	public static boolean checkForWin(int x, int y) {

		int diff = x - y;
		diff = Math.abs(diff);

		if (diff == 2 || diff == 4 || diff == 6) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {

		System.out.println();

		//loop used for testing
		//for (int i = 0; i < 40; i++) {

			int num1 = getRandomInt();
			int num2 = getRandomInt();

			if (checkForTie(num1, num2)) {
				System.out.println("A tie!");
			} else if (checkForWin(num1, num2)) {
				System.out.println("You win!");
			} else {
				System.out.println("You lose!");
			}

			// below used for testing
			 System.out.println("num1-[" + num1 + "] num2-[" + num2 + "]");
			 //System.out.println(" diff = [" + (num1 - num2) + "]");
			 System.out.println();
		//} // end of loop

	}

}

