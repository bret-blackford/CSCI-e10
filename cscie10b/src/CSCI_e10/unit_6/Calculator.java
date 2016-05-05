
package CSCI_e10.unit_6;


import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.*;

/**
 * @author M Bret Blackford
 * ID: 20849347
 * CSCI E-10b  Spring 2016
 * 
 * Unit 6 Problem Set
 * Question [7] -- Calculator.java
 *
 */
public class Calculator {

	public static void main(String[] args) {
		CalculatorApplication calc = new CalculatorApplication();
	}
}


/**
 * 
 * CalculatorApplication - this is the main class that performs the
 * GUI setup and logic to simulate a calculator.
 * 
 * Implementation is a basic calculator with +, -, X, /, and SQRT.
 * Unlike many, this implementation will not continue processing an 
 * operation when it is pressed continually. That is, if you press
 * 1 + 2 + 3 + + + you will get the display of 6 (does not continue to add
 * with subsequent presses of "+" key).
 * Basic testing was performed using my daughter's Hello Kitty calculator. All
 * errors should be attributed to Ms Kitty.  
 */
class CalculatorApplication extends JFrame implements ActionListener {

	// 8 is max number on my Hello Kitty calculator I used for testing but
	// I increased to 20 
	private static final int NUMBER_LENGTH = 20; 
	private final int FRAME_WIDTH = 450;
	private final int FRAME_HEIGHT = 350;
	
	double valuePrior = 0.0; // 1st number entered
	double valueCurrent = 0.0; // 2nd (or latest) number entered
	boolean secondOperation = false;// true if this is the second operation
	boolean priorIsNum = true; // is the prior button push a number?
	boolean usingDecPoint = false; // manages decimal point use in display
	boolean operators = true; // set if +, -, /, x
	boolean repeatedEquals = false; // makes sure subsequent = presses do nothing
	boolean isEquals = false; // checks initial = button press
	boolean isStart = true;
	boolean doClear = false; // determines is screen should be cleared
	private String operator; // hold operation selected (as a string)

	// use Dr. L's favorite font
	Font font = new Font("Helvetica", Font.BOLD, 22);
	Font smallFnt = new Font("Helvetica", Font.BOLD, 15);

	private final int NO_OF_BUTTONS = 20;
	JButton[] button = new JButton[NO_OF_BUTTONS];
	JTextArea display = new JTextArea(1, 20);

	JPanel pnl_buttons = new JPanel(new GridLayout(5, 4, 5, 5)); // (5 x 4 grid)

	// array of all buttons
	String[] buttons = { "C", "SQRT", "/", "x", 
						 "7",    "8", "9", "-", 
						 "4",    "5", "6", "+", 
						 "1",    "2", "3", "=", 
						 "0",    ".",  "", "+/-" };

	// array of "special" (non-numeric) buttons
	String[] specialBtns = { "C", "SQRT", "/", 
			                 "x",    "-", "+", 
			                 "=",  "+/-", "" };

	public CalculatorApplication() {
		super("Calculator"); // JFrame text
		do_layout();
		do_plumbing();
	}

	/**
	 * set-up the GUI layout
	 */
	public void do_layout() {
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout(10, 10));

		display.setFont(font);
		display.setBackground(Color.YELLOW);
		display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		display.setEditable(false); // do not allow user to type in the display

		// set up the calculator buttons
		for (int i = 0; i < NO_OF_BUTTONS; i++) {
			button[i] = new JButton();
			button[i].setText(buttons[i]);

			if (isSpecial(buttons[i])) {
				button[i].setBackground(Color.pink);
			}

			// need to make SQRT font smaller to fit on button
			if (buttons[i].equalsIgnoreCase("SQRT")) {
				button[i].setFont(smallFnt);
			} else {
				button[i].setFont(font);
			}

			//button[i].addActionListener( this );//done in plumbing below
			pnl_buttons.add(button[i]); // add buttons to JPanel
		}

		// add JPanel of buttons and display JTextArea to JFrame
		this.add(display, BorderLayout.NORTH);
		this.add(pnl_buttons, BorderLayout.CENTER);
		this.setVisible(true);
		//this.pack();
	}

	/**
	 * identifies special buttons
	 * 
	 * @param inText
	 * @return
	 */
	public boolean isSpecial(String inText) {

		for (int i = 0; i < specialBtns.length; i++) {
			if (inText.equalsIgnoreCase(specialBtns[i])) {
				return true;
			}
		}
		return false;
	}

	// =================================================================================
	// do_layout() above - sets GUI interface
	//
	// do_plumbing() below - implementation of logic
	// =================================================================================

	/**
	 * do_plumbing() handles the implementation logic of the calculator
	 */
	public void do_plumbing() {
		// set up the calculator buttons
		for (int i = 0; i < NO_OF_BUTTONS; i++) {

			if (button[i].getText().equalsIgnoreCase("")) {
				// blank button does not need an ActionListener
				// blank button a holding place for EXTRA CREDIT print fx()
			} else {
				button[i].addActionListener(this);//
			}
		}
	}

	/**
	 * called when a button with an ActionListener is pressed
	 */
	public void actionPerformed(ActionEvent ae) {
		JButton buttonPressed = (JButton) ae.getSource();
		String btnText = buttonPressed.getText();
		String displayValue = display.getText();
		System.out.println("in ActionPerformed() and you pressed [" + btnText + "]");

		// clear display if new number is to be entered
		if (doClear) {
			display.setText(null);
		}

		// if decimal point is clicked for the first time in a number
		if ((btnText.equals(".")) && (usingDecPoint == false)) {
			display.append(".");
			// attempt to deal with leading zeros and decimals 
			if ( displayValue.equals(".") || displayValue.equals("") ) {
				display.setText("0.");
			}
			if( display.getText().equals("") || display.getText().equals(".") ) {
				display.setText("0.");
			}

			valueCurrent = Double.parseDouble(display.getText());
			usingDecPoint = true; // disallow additional decimal points
			doClear = false; // do not clear display the next time a button is clicked
			secondOperation = false;// this is a number, not an operation (+, -, /, x)
			repeatedEquals = false; // a subsequent equal sign will be allowed
			isStart = false; // a button has been pressed
		} else if (btnText.equals(".")) {
			System.out.println("Decimal issue -- SHOULD NOT GET HERE");
			// unexpected decimal - IGNORE
		}

		// if an operator (+, -, /, x) was clicked
		else if ( (btnText.equals("+")) || (btnText.equals("-")) || 
				  (btnText.equals("/")) || (btnText.equals("x")) ) {

			System.out.println("looks like you enterd the operator [" + btnText + "]");

			usingDecPoint = false; // decimal point allowed again

			if (secondOperation == false) { // does operation button follow a
											// number button ?
				if (operators) { 
					// if this is the first operator
					// the number last entered becomes the first number
					valuePrior = valueCurrent;
				} else {
					doCalculation(); // if this is the second operator then calculate
				}

				operator = btnText; // store the operator that was clicked
				operators = false;
				doClear = true;
				secondOperation = true;
			}
			// does an operation button press immediately follow an operation
			// button press?
			else {
				operator = btnText; // store operation

				display.setText( String.valueOf(format(valuePrior)) );
				usingDecPoint = false; // decimal point allowed again
			}
			isEquals = false;
		} // end of "if" checking for operators +,-,/,x

		else if (btnText.equals("SQRT")) { // calculate square root
			squareRoot();
			isEquals = false;
		} else if (btnText.equals("+/-")) { // switchPosNeg
			setPosNeg();
			isEquals = false;
		} else if (btnText.equals("C")) { // clear
			clear();
			isEquals = false;
		} // end of "if" checking for operators C

		// if equal sign is clicked (once), calculate
		else if ( btnText.equals("=") ) {
			if (repeatedEquals == false) {
				System.out.println("YOU ARE HITTING = [" + btnText + "]");

				doCalculation();
				usingDecPoint = true;
				repeatedEquals = true;
				isEquals = true;
			} // end of "if" checking for operators =
				// MUST BE MULTPLE "=" presses
			System.out.println("Stop presseing = !!");
		} else {
			// MUST BE A NUMBER
			// display should not exceed NUMBER_LENGTH
			if ( displayValue.length() < NUMBER_LENGTH ) {

				System.out.println("Looks like you enterd a digit [" + btnText + "]");

				display.append( btnText );

				// assumes that if the 1st button pressed is zero the
				// number will be a decimal (less than zero). 
				// Avoids multiple leading zeros
				if ( displayValue.equals("0") ) {
					display.setText("0.");
					usingDecPoint = true; // no more decimals
				}

				valueCurrent = Double.parseDouble( display.getText() );
				
				// this is a number, so a subsequent equal sign (=) will 
				// be allowed
				if ( !isEquals ) {
					repeatedEquals = false;
				}
				doClear = false;
				
				// this is a number, so subsequent operator will be the
				// one applied
				secondOperation = false;
			} // CHECKING for NUMBERS
		} // end of number processing

	}

	/**
	 * changes sign of displayed number 
	 */
	public void setPosNeg() {
		valueCurrent *= (-1);
		display.setText(String.valueOf(format(valueCurrent)));
	}

	/**
	 * Calculates square root Checks for zeros and negative numbers
	 */
	public void squareRoot() {
		// square root of zero or negatives is an ERROR
		if (valueCurrent <= 0.0) {
			display.setText("ERROR");
			// reset
			valuePrior = 0.0;
			valueCurrent = 0.0;
			operators = true;
			doClear = true;
		}
		// positive numbers OK
		else {
			valueCurrent = Math.sqrt(valueCurrent);
			display.setText(String.valueOf(format(valueCurrent)));
			// it's now OK to enter a decimal point again
			usingDecPoint = false;
		}
	}

	/**
	 * Calculates (+, -, x, and /)
	 */
	public void doCalculation() {
		System.out.println("in doCalc(" + operator + ") [" + valuePrior + "],[" + valueCurrent + "]");

		if (operator == "+") {
			valuePrior = add(valuePrior, valueCurrent);
		}
		if (operator == "-") {
			valuePrior = subtract(valuePrior, valueCurrent);
		}
		if (operator == "x") {
			valuePrior = multiply(valuePrior, valueCurrent);
		}
		// division by non-zero
		if ((operator == "/") && valueCurrent != 0.0) {
			valuePrior = divide(valuePrior, valueCurrent);
		}
		// division by zero
		if ((operator == "/") && valueCurrent == 0.0) {
			display.setText("ERROR");
			// reset
			valuePrior = 0.0;
			valueCurrent = 0.0;
			operators = true;
			doClear = true;
		}

		// if no ERROR on screen
		// set state but do not clear() state
		if ((display.getText().equals("ERROR"))) {
			// don't reset if in ERROR state
		} else {
			operators = true;
			usingDecPoint = false; // can use decimals
			repeatedEquals = true; // equals button no longer works
			display.setText(String.valueOf(format(valuePrior))); 
			
			// set proper value
			valueCurrent = valuePrior;
		}
	}

	/**
	 * =========================================================
	 * The following 4 methods may not be necessary (could be done
	 * in the doCalculation() method) but I wanted to separate as 
	 * they seem independent.  May even be beneficial to have in
	 * a separate class.
	 */
	/**
	 * simple addition
	 * @param x
	 * @param y
	 * @return
	 */
	public Double add(Double x, Double y) {
		return (x + y);
	}

	/**
	 * simple subtraction
	 * @param x
	 * @param y
	 * @return
	 */
	public Double subtract(Double x, Double y) {
		return (x - y);
	}

	/**
	 * simple multiplication
	 * @param x
	 * @param y
	 * @return
	 */
	public Double multiply(Double x, Double y) {
		return (x * y);
	}

	// x = numerator, y = divisor
	// y = 0 must be addressed before passing
	public Double divide(Double x, Double y) {
		if (y == 0.0) {
			return null;
		}
		return (x / y);
	}
	//=========================================================

	/**
	 * Clears the display and resets values for new calculation
	 */
	public void clear() {
		operators = true;
		display.setText("");
		usingDecPoint = false;
		valuePrior = 0.0;
		valueCurrent = 0.0;
		repeatedEquals = false;
		isStart = true;
	}

	/**
	 * Format display output (very basic)
	 * @param n	The number to be formatted
	 * @return	Formatted number
	 */
	public static String format(Number n) {
		NumberFormat format = DecimalFormat.getInstance();
		format.setMaximumFractionDigits(NUMBER_LENGTH);
		String formattedNumber = format.format(n);
		
		//going scientific notation or truncating is not a 
		//good option for this calculator implementation. 
		if(  formattedNumber.length() > NUMBER_LENGTH ) {
			return "NUMBER TO LARGE - click 'C' to clear";
		}
		return format.format(n);
	}

}

