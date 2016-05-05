package CSCI_e10.unit_6;


import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Collections;
import javax.swing.*;


/**
 * 
 * @author MBret Blackford
 * ID: 20849347
 * CSCI e-10b  Spring 2016
 * Unit 6 Problem Set
 * Question [6] - FifteenPuzzle.java
 * 
 * The application is a sliding puzzle, requiring the player
 * to arrange a scrambled grid of numbers into order from
 * 1 to 15.
 *
 */
public class FifteenPuzzle  {
	public static void main(String[] args) {
		Puzzle game = new Puzzle();
	}
}


/*
 * This class contains the programming logic. 
 * 
 */
class Puzzle  implements ActionListener  {

	private JFrame frame;
	private int ROWS = 4; //code only works for a 4x4 puzzle (15 tiles)
	private JButton[][] jbtnBoard = new JButton[ROWS][ROWS];
	private int SIZE = ROWS * ROWS;
	String [] CORRECT = new String[SIZE-1];
	String [] WIN = new String[SIZE];
	private final String[][] WIN_STATE = new String[][] {
		{  "blank", "1", "2", "3"  },
		{  "4", "5", "6", "7"  },
		{  "8", "9","10","11"},
		{ "12","13","14","15"} };
	
	private JPanel panel = new JPanel();
	private final int FRAME_HEIGHT = 450;
	private final int FRAME_WIDTH = 400;
	private JButton jbtnShuffle = new JButton( "Shuffle");
	private ArrayList<Integer> puzzleState = new ArrayList<>();
	private ArrayList<String> puzzleState2 = new ArrayList<>();
	private RowAndColumn blankCoordinate = new RowAndColumn(0,0);
	
	/**
	 * Constructor sets things up
	 */
	public Puzzle() {
		System.out.println("in FifteenPuzzle()");
		
		//order of correct = 1 thru SIZE - 1 (less 1 for blank square)
		for( int i = 0; i < SIZE-1; i++ ) {
			CORRECT[i] = Integer.toString(i+1);
		}
		for( int j = 0; j < SIZE; j++ ) {
			WIN[j] = Integer.toString(j);
			puzzleState.add(j);
			puzzleState2.add( Integer.toString(j) );
		}
		
		do_layout(WIN);
		setupFrame();
		
		panel.setLayout( new GridLayout(ROWS, ROWS));
		panel.setBackground( Color.lightGray );
		
		Container container = frame.getContentPane();
		container.add( panel,  BorderLayout.CENTER );
		
		//add a "shuffle" button at bottom of JPanel
		jbtnShuffle.addActionListener(this);
		container.add( jbtnShuffle, BorderLayout.SOUTH );
		
		container.setBackground( Color.GRAY );
		frame.setVisible(true);
	}

	
	/**
	 * Method will build the puzzle board - add the JButtons into
	 * a JPanel which goes into the JFrame 
	 * @param setup
	 */
	public void do_layout(String[] setup) {
		
		int count = 0;
		for (int outer = 0; outer < ROWS; outer++) {
			for (int inner = 0; inner < ROWS; inner++) {
				
				if ( setup[count].equalsIgnoreCase("0") ) {
					jbtnBoard[outer][inner] =  new JButton("blank");
					blankCoordinate.setRow(outer);
					blankCoordinate.setColumn(inner);
					jbtnBoard[outer][inner].addActionListener(this);
				} else {
					jbtnBoard[outer][inner] = new JButton( setup[(count)] );
					jbtnBoard[outer][inner].setForeground(Color.GREEN);
					jbtnBoard[outer][inner].setBackground( Color.BLACK );
					jbtnBoard[outer][inner].addActionListener(this);
					jbtnBoard[outer][inner].setVisible(true);
				}
				panel.add(jbtnBoard[outer][inner]);
				count++;
			} //end of inner loop
		} //end of outer loop
		System.out.println();
	}
	
	/**
	 * JFrame holds the puzzle board with buttons and panels
	 */
	public void setupFrame() { 
		frame = new JFrame("Fifteen Puzzle");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.pack();
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setVisible(true);
	}
	
	/**
	 * Method just randomizes the tiles in the puzzle
	 * @param inList
	 */
	public void shuffleInt(ArrayList<Integer> inList) {
		Collections.shuffle(inList);
	}
	
	/**
	 * Method will change/switch tiles when appropriate
	 * @param inList
	 */
	public void changeButtonNames(ArrayList<String> inList) {
		
		int count = 0;
		for (int outer = 0; outer < ROWS; outer++) {
			for (int inner = 0; inner < ROWS; inner++) {

				if (inList.get( count ).equalsIgnoreCase("0")) {
					jbtnBoard[outer][inner].setText("blank");
					blankCoordinate.setRow(outer);
					blankCoordinate.setColumn(inner);
					jbtnBoard[outer][inner].setBackground( Color.lightGray );
					jbtnBoard[outer][inner].setForeground( Color.BLACK );
				} else {
					jbtnBoard[outer][inner].setText( inList.get( count ) );
					jbtnBoard[outer][inner].setForeground(Color.GREEN);
					jbtnBoard[outer][inner].setBackground( Color.BLACK );
				}
				count++;
			} // end of inner loop
		}
	}
	
	/**
	 * Method just randomizes the tiles in the puzzle. Will
	 * continue to shuffle tiles on board until a solvable
	 * board is found
	 * @param inList
	 */
	public void shuffle(ArrayList<String> inList) {
		boolean stillRunning = true;
		while (stillRunning) {
			Collections.shuffle(inList);

			ArrayList<Integer> numberList = new ArrayList<Integer>();

			for(int i = 0; i < inList.size(); i++) {
			   numberList.add(Integer.parseInt(inList.get(i)));   
			}

			if( isSolvable( numberList )) {
				stillRunning = false;
			}
		}
	}


	
	/**
	 * Method performs desired action depending on which buttones/tiles 
	 * are selected via the mouse button.
	 * if SHUFFLE button is clicked the board will randomly shuffle tiles
	 *    until a solvable board is found
	 * if a tile button/square is selected the program will determine if action 
	 *    can be taken (if square is adjacent will switch).
	 */
	public void actionPerformed(ActionEvent ae) {
		RowAndColumn rowCol;
		if (ae.getSource() == jbtnShuffle) {
			shuffle(puzzleState2);
			changeButtonNames( puzzleState2 );
		} else {
			JButton btnPressed = (JButton) ae.getSource();
			System.out.println(" you pressed " + btnPressed.getText() );
			rowCol = indexOf(btnPressed.getText());
			System.out.println(" you pressed [" + rowCol.getRow() + "][" + rowCol.getColumn() + "]");
			
			if( isAdjacent(rowCol) ){
				switchSquares( rowCol, btnPressed.getText() );
				
				if( finished() ) {
					System.out.println(" FINISHED !!");
					JOptionPane.showMessageDialog(null, "Winner Winner Chicken Dinner !!");
				}
			}
		}
	}
	
	/**
	 * Method finds the X/Y coordinates of the selected tile (JButton)
	 * @param cellNum
	 * @return
	 */
	public RowAndColumn indexOf(String cellNum) {
		RowAndColumn rowAndCol;
		
		for (int row = 0; row < jbtnBoard.length; row++) {
			for (int col = 0; col < jbtnBoard[row].length; col++) {
				if (jbtnBoard[row][col].getText().equals(cellNum)) {
					rowAndCol = new RowAndColumn(row, col);
					return rowAndCol;
				}
			}
		}
		return new RowAndColumn(0, 0);
	}
	
	/**
	 * Method determines if selected tile is adjacent to the blank square. If
	 * so it returns true 
	 * @param rowCol
	 * @return
	 */
	public boolean isAdjacent(RowAndColumn rowCol){

		int val = (Math.abs(rowCol.getRow() - blankCoordinate.getRow())) - (Math.abs(rowCol.getColumn() - blankCoordinate.getColumn()));
		
		// ignore clicks on blank square
		if( rowCol.getRow() == blankCoordinate.getRow() && rowCol.getColumn() == blankCoordinate.getColumn() ) {
			System.out.println(" you pressed the BLANK square ");
			return false;
		}
		
		// ignore diagonal squares
		if( val == 0 ) {
			System.out.println(" you pressed a diagonal square ");
			return false;
		}
		
		// adjacent squares are good !
		if( Math.abs(rowCol.getRow() - blankCoordinate.getRow()) < 2 ) {
			if( Math.abs(rowCol.getColumn() - blankCoordinate.getColumn()) < 2 ) {
				System.out.println(" you pressed an adjacent square ");
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method will switch squares - actually switches JButton text and
	 * JButton formatting (colors)
	 * @param rowCol
	 * @param text
	 */
	public void switchSquares(RowAndColumn rowCol, String text) {
	
		RowAndColumn temp = new RowAndColumn( blankCoordinate.getRow(), blankCoordinate.getColumn() );
		
		// set new blank
		blankCoordinate.setRow( rowCol.getRow() );
		blankCoordinate.setColumn( rowCol.getColumn() );
		
		jbtnBoard[blankCoordinate.getRow() ][blankCoordinate.getColumn() ].setText( "blank" );
		jbtnBoard[blankCoordinate.getRow() ][blankCoordinate.getColumn()].setBackground( Color.lightGray );
		jbtnBoard[blankCoordinate.getRow() ][blankCoordinate.getColumn()].setForeground( Color.BLACK );
		
		jbtnBoard[temp.getRow() ][temp.getColumn()].setText( text );
		jbtnBoard[temp.getRow() ][temp.getColumn()].setForeground(Color.GREEN);
		jbtnBoard[temp.getRow() ][temp.getColumn()].setBackground( Color.BLACK );
	}
	
	/**
	 * Method determined is shuffled board is solvable
	 * I struggled with a good thorough way to implement so 
	 * used code section found at http://mathworld.wolfram.com/15Puzzle.html
	 * This is NOT a trivial algorithm 
	 * @param list
	 * @return
	 */
	public boolean isSolvable(ArrayList<Integer> list) {

		int inversionSum = 0; // if even puzzle is solvable

		for (int outer = 0; outer < list.size(); outer++) {
			if (list.get(outer) == 0) {
				inversionSum += ((outer / ROWS) + 1); // add ROW number
			}

			int count = 0;
			for (int inner = outer + 1; inner < list.size(); inner++) {
				if (list.get(inner) == 0) {
					continue;
				} else if (list.get(outer) > list.get(inner)) {
					// if element greater than seed increase the inversionSum
					count++;
				}
			} // end of inner loop
			inversionSum += count;
		} // end of outer loop

		if ((inversionSum & 1) == 0) {
			System.out.println("Shuffle is solvable! " + list.toString());
			return true;
		}
		System.out.println("Shuffle is NOT solvable! " + list.toString());
		return false;
	}
	
	/**
	 * Method checks to see if puzzle is solved, if so returns true
	 * @return
	 */
	public boolean finished() {
		//compare current state with WIN state
		for( int outer = 0; outer < WIN_STATE.length; outer++) {
			for( int inner = 0; inner < WIN_STATE[outer].length; inner++ ) {
				String tmpWinState = WIN_STATE[outer][inner];
				String tmpButtonTxt = jbtnBoard[outer][inner].getText();
				//System.out.println("tmpWinState[" + tmpWinState + "] tmpButtonTxt[" + tmpButtonTxt + "]");
				
				if( !tmpWinState.equalsIgnoreCase(tmpButtonTxt) ) {
					return false;
				}
			} // end of inner loop
		} // end of outer loop

		return true;
	}
	

}

/**
 * class used to pass the X/Y coordinates of the 
 * various JButtons
 * @author M Bret Blackford
 *
 */
class RowAndColumn {

	private int row;
	private int column;

	public RowAndColumn(int inRow, int inCol) {
		row = inRow;
		column = inCol;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

}

