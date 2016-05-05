package CSCI_e10.unit_4;



import java.util.Scanner;

/**
 * @author M Bret Blackford   ID:20849347
 * CSCI E-10a  Fall 2015
 * Unit 4 Problem Set  question [15]
 *
 */
public class TicTacToe {

	String x = "";
	String o = "";

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.println("Welcome! Tic-Tac-Toe is a two player game.");
		System.out.print("Enter player one's name:");
		String playerX = input.nextLine();

		System.out.print("Enter player two's name:");
		String playerO = input.nextLine();
		System.out.println();
		System.out.println();

		TicTacToeBoard game = new TicTacToeBoard(playerX, playerO);

		System.out.println(game.getIntro());
		System.out.println(game.toString());

		int gameResult = 0;
		while (game.gameOn) {

			boolean continueLoop = true;
			while (continueLoop) {

				System.out.println();
				System.out.println(game.turn());

				System.out.print("Pick a row between 1 and 3: ");
				int row = input.nextInt();

				System.out.print("Pick a column between 1 and 3: ");
				int column = input.nextInt();

				gameResult = game.makeMove(row - 1, column - 1);
				// System.out.println("gameResult = " + gameResult);
				if (gameResult == 0) {
					System.out.println();
					System.out.println(game.toString());
					game.switchPlayer();
				}
				if (gameResult == -1) {
					System.out.println("ILLEGAL CHOICE!  TRY AGAIN. . . ");
				}
				if (gameResult == 1) {
					System.out.println();
					System.out.println("Game over - " + game.getCurrentPlayer() + " WINS!!!");
					continueLoop = false;
				}
				if(gameResult == 2) {
					System.out.println();
					System.out.println("Tie Game.");
					continueLoop = false;
				}
			}
			System.out.println();
			System.out.println(game.toString());
		}
	}

}
