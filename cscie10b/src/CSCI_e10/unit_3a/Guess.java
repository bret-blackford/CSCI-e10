package CSCI_e10.unit_3a;



import java.util.Scanner;

public class Guess {

	public static int makeGuess(int lower, int upper) {
		return (lower + upper) / 2;
	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.println();
		System.out.println(
				"This program has you, the user, choose a number between 1 and 100. Then I, the computer, will try my best to guess it.");
		System.out.println();
		System.out.println(
				"If I guess a number that's SMALLER than the secret number, respond by typing the letter s.  If I guess a number that's BIGGER than the secret number, repond by typing the letter b.  And if I guess CORRECTLY, respond by typing the letter c.");
		System.out.println();

		int uperLimit = 100;
		int lowerLimit = 1;
		int guess = 0;

		int noOfGuesses = 0;
		boolean stillGuessing = true;
		boolean inErrorState = false;

		while (stillGuessing) {

			guess = makeGuess(lowerLimit, uperLimit);
			System.out.print("Is it " + guess + "? (type s, b, or c): ");

			String answer = "";
			answer = input.nextLine();

			noOfGuesses++;

			if (answer.equalsIgnoreCase("c")) {
				stillGuessing = false;
			} else if (answer.equalsIgnoreCase("s")) {
				uperLimit = guess;
			} else if (answer.equalsIgnoreCase("b")) {
				lowerLimit = guess;
			} else {
				System.out.println("Sorry, I didn't understand that response.");
				stillGuessing = false;
				inErrorState = true;
			}

		}
		if (!inErrorState) {
			System.out.println("I got it after making just " + noOfGuesses + " guesses");
		}
	}

}
