package CSCI_e10.unit_4;



/**
 * @author M Bret Blackford   ID:20849347
 * CSCI E-10a  Fall 2015
 * Unit 4 Problem Set  question [15]
 *
 */
public class TicTacToeBoard {

	//Initial default setting of blank board
	private char[][] board = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };

	private boolean isLegalMove = true;
	public boolean gameOn = true;
	private boolean xTurn = true;
	private String  playerX = "";
	private String playerO = "";
	private String intro = "Players take turns marking a square. Only squares \n" +
	 "not already marked can be picked. Once a player has \n" +
	 "marked three squares in a row, he or she wins!  If all squares \n" +
	"are marked and no three squares are the same, a tied game is declared.\n" +
	 "Have Fun!";
	
	/**
	 * Constructor that takes player X name and player O name
	 * @param x
	 * @param o
	 */
	TicTacToeBoard(String x, String o) {
		playerX = x;
		playerO = o;
	}

	public String getPlayerX() {
		return playerX;
	}

	/*
	 * Method keeps track of which player is taking a turn and
	 * will then give move to next player
	 */
	public void switchPlayer(){
		if( xTurn ){
			xTurn = false;
		} else {
			xTurn = true;
		}
		
	}
	
	/**
	 * Provides the String name of the current player
	 * @return
	 */
	public String getCurrentPlayer(){
		if( xTurn ){
			return playerX;
		}
		return playerO;
	}
	
	public void setPlayerX(String playerX) {
		this.playerX = playerX;
	}

	public String getPlayerO() {
		return playerO;
	}

	public void setPlayerO(String playerO) {
		this.playerO = playerO;
	}


	/**
	 * Will return a String of the long introduction to the game.
	 * @return
	 */
	public String getIntro() {
		return intro;
	}

	/**
	 * Method records the player move. Returns following values:
	 *   return -1 = illegal move
	 *   return  1 = move wins game
	 *   return  2 = move ties game
	 *   return  0 = good valid move
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	public int makeMove(int row, int column){
		
		char player = 'X';
		if( xTurn ) {
			player = 'X';
		}else {
			player = 'O';
		}
		
		return makeMove(row, column, player);
	}
	
	/**
	 * populated and called from makeMove(int, int)
	 * 
	 * @param row
	 * @param column
	 * @param player
	 * @return
	 */
	private int makeMove(int row, int column, char player) {

		if (legalMove(row, column)) {
			board[row][column] = player;

			if (isWin()) {
				System.out.println("Win!");
				return 1;
			}
			if(isTie()){
				System.out.println("Tie Game!");
				return 2;
			}
			return 0;
		}
		return -1;
	}
	
	/**
	 * Method check board matrix to see if any open spots. If
	 * no open spots and board is full with no winner then
	 * game is tied.
	 * 
	 * @return
	 */
	private boolean isTie() {
		
		//check for ant blank/open squares
		for(int outer = 0; outer < 3; outer++ ){
			for(int inner = 0; inner < 3; inner++ ){
				if( board[outer][inner] == ' '){
					return false;
				}
			}
		}
		
		//No black squares found
		gameOn = false;
		return true;
	}

	/**
	 * Method checks to see if move wins game.  If there are 3
	 * adjacent marks (X or O) horizontally, vertically, or diaganally
	 * then there is a win and return TRUE
	 * 
	 * @return
	 */
	private boolean isWin() {

		gameOn = false;
		for (int i = 0; i < 3; i++) {
			if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
				return true;
			}
		}
		for (int j = 0; j < 3; j++) {
			if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[0][j] == board[2][j]) {
				return true;
			}
		}

		// check diagonals
		if (board[1][1] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			return true;
		}
		if (board[1][1] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return true;
		}

		gameOn = true;
		return false;
	}

	/**
	 * Method checks to see if move is legal.  
	 * Move must be in bounds of the board (between 0 and 3)
	 * Move must be in an open space 
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	private boolean legalMove(int row, int column) {

		isLegalMove = false;
		// Check for values outside limit of board
		if (row < 0 || row > 2) {
			return false;
		}
		if (column < 0 || column > 2) {
			return false;
		}

		// Check if square already selected
		if (board[row][column] != ' ') {
			return false;
		}

		isLegalMove = true;
		return true;
	}

	/**
	 * Allows display of the game board and current state. Note: because matrix
	 * so small did not use loops to print elements.
	 */
	public String toString() {

		//System.out.println();
		//System.out.println("Game board:");
		//System.out.println("|" + " " + board[0][0] + " " + board[0][1] + " " + board[0][2] + " " + "|");
		//System.out.println("|" + " " + board[1][0] + " " + board[1][1] + " " + board[1][2] + " " + "|");
		//System.out.println("|" + " " + board[2][0] + " " + board[2][1] + " " + board[2][2] + " " + "|");

		
		String  gameStateString = "\t Game board: \n" +
				"\t |" + " " + board[0][0] + " " + board[0][1] + " " + board[0][2] + " " + "|  X = " + playerX + "\n" +
				"\t |" + " " + board[1][0] + " " + board[1][1] + " " + board[1][2] + " " + "|  O = " + playerO + "\n" +
				"\t |" + " " + board[2][0] + " " + board[2][1] + " " + board[2][2] + " " + "| \n";
		
		return gameStateString;
	}
	
	/**
	 * Method returns a String which player is currently taking a turn
	 * 
	 * @return
	 */
	public String turn(){
		if( xTurn ){
			return "It is " + playerX + "'s turn.";
		}
		return "It is " + playerO + "'s turn.";
	}

}

