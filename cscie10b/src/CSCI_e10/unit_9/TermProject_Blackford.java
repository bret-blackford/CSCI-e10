package CSCI_e10.unit_9;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
 
/**
 * M BRET BLACKFORD
 * 
 * @author mblackford  M Bret Backford
 * ID: 208493447
 * CSCI e-10b  Spring 2016
 * Term Project
 * 
 * This class build a GUI client front-end for a
 * financial option model calculation.  The GUI builds
 * an interface to collect key required input data which
 * will be sent to the BlackScholesFormula class for
 * processing. The BlackScholesFormula class will return a
 * modeled valuation (fair value) for the option data along
 * with the Delta (no other Greeks will be implemented). 
 *
 */
public class TermProject_Blackford {

	/**
	 * Very basic main() to start the application
	 * @param args
	 */
	public static void main(String[] args) {
		BlackScholesGUI valuation = new BlackScholesGUI();
	}
}
 
/**
 * Main GUI for the Black-Scholes calculator
 * @author mblackford
 *
 */
class BlackScholesGUI extends JFrame implements ActionListener {
 
	private final int HEIGHT = 400;
	private final int WIDTH = 500;
	private final String delim = "|";
	private final String OUTPUT_FILE = "valuationFile.txt";
	private final int MIN_DECIMALS = 2;
	private final int MAX_DECIMALS = 8;
 
	private Double underlyingPrice = 0.0;
	private Double strikePrice = 0.0;
	private Double volatility = 0.0;
	private Double intRate = 0.01;
	private Double valuation = 0.0;
	private Double delta = 0.0;
	private Double timeToExpiration = 0.0;
	private Date expirationDt;
	private Date valuationDt;
	private Date rightNow;
 
	private JTextField jtxt_underlyingPrice;
	private JTextField jtxt_strikePrice;
	private JTextField jtxt_expirationDt;
	private JTextField jtxt_valuationDt;
	private JTextField jtxt_volatility;
	private JTextField jtxt_interestRate;
	private JTextField jtxt_optionType;
	private JTextField jtxt_fairValue;
	private JTextField jtxt_delta;
	 
	private JLabel[] jlbl_label;
 
	private String[] fields = { "underlying price (in USD)", "strike price (in USD)", 
			" expiration date (mm/dd/yyyy)", " valuation date (mm/dd/yyyy)", 
			"volatility (ex. 0.107)", "interest rate (ex. 0.002)", 
			"option type", "fair value (in USD)", "delta" };
 
	private JTextArea notes;
	private JTextArea fileName;//stub to allow future user input of file name
	private JComboBox<String> jcbox_optionType;
 
	public JButton jbtn_runValuation;
	public JButton jbtn_print;
	public JButton jbtn_clear;
 
	// private JFrame frame;
	private JPanel pnl_labels;
	private JPanel pnl_txtFields;
	private JPanel pnl_input;
	private JPanel pnl_buttons;
 
	/**
	 * Constructor sets up initial variables, calls JFrame 
	 * constructor with super(), and then kicks-off the 
	 * GUI creation with do_layout()
	 */
	public BlackScholesGUI() {
		super("Option Valuation: fields in yellow are required");
		rightNow = new Date();
		expirationDt = rightNow; //seed date
		valuationDt  = rightNow; //seed date
		do_layout();	// build the GUI
	}
 
	/**
	 * Method sets-up the GUI interface. Separates the client GUI 
	 * interface from the implementation 
	 */
	private void do_layout() {
		System.out.println("in do_layout()");
 
		this.setLayout(new BorderLayout(10, 10));
 
		// set font
		Font font = new Font("Helvitica", Font.BOLD, 18);
 
		// create labels
		jlbl_label = new JLabel[fields.length];
		for (int i = 0; i < fields.length; i++) {
			jlbl_label[i] = new JLabel(fields[i] + ":", SwingConstants.RIGHT);
			jlbl_label[i].setFont(font);
		}
 
		createTextFields();
		setTextFieldAlignment();
 
		jtxt_fairValue.setBackground(Color.lightGray);
		jtxt_fairValue.setEditable(false); // not an input field
		jtxt_delta.setBackground(Color.lightGray);
		jtxt_delta.setEditable(false); // not an input field
 
		// JComboBox (drop-down) options
		String[] optionTypes = { "PUT", "CALL" };
		jcbox_optionType = new JComboBox<>(optionTypes);
 
		// create JButtons
		jbtn_runValuation = new JButton("Calculate Valuation");
		jbtn_runValuation.addActionListener(this);
 
		jbtn_print = new JButton("Print to File");
		jbtn_print.addActionListener(this);
		
		jbtn_clear = new JButton("clear data");
		jbtn_clear.addActionListener(this);
 
		// notes & FileName area
		fileName = new JTextArea();
		notes = new JTextArea();
		String txtAreaMessage = "Type any notes here";
		notes.setBorder(BorderFactory.createTitledBorder(txtAreaMessage));
		notes.setBackground(Color.white);
		notes.setForeground(Color.BLUE);
		notes.setFont( font );
 
		pnl_buttons = new JPanel(new GridLayout(1, 0, 5, 5));
		pnl_buttons.add(jbtn_runValuation);
		pnl_buttons.add(jbtn_print);
		// pnl_buttons.add( jlbl_label[7] );
		pnl_buttons.add(jbtn_clear);
 
		pnl_labels = new JPanel(new GridLayout(9, 0, 0, 0));
		pnl_txtFields = new JPanel(new GridLayout(9, 0, 1, 1));
 
		for (int i = 0; i < jlbl_label.length; i++) {
			pnl_labels.add(jlbl_label[i]);
		}
 
		addTxtFieldsToPanel();
		
		pnl_input = new JPanel(new BorderLayout());
		pnl_input.add(pnl_labels, BorderLayout.WEST);
		pnl_input.add(pnl_txtFields, BorderLayout.CENTER);
 
		this.add(pnl_input, BorderLayout.CENTER);
		this.add(pnl_buttons, BorderLayout.SOUTH);
		this.add(notes, BorderLayout.NORTH);
 
		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
		// this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
	}
 
	//==================================================================
	//==================================================================
	
	/**
	 * Action Events determine which buttons are pressed and how
	 * to respond 
	 */
	public void actionPerformed(ActionEvent ae) {
 
		if (ae.getSource() == jbtn_runValuation) {
			System.out.println("You pressed the VALUATION button");
			getValues();
			runValuation();
		} else if (ae.getSource() == jbtn_print) {
			System.out.println("You pressed the PRINT button");
			writeTextToFile();
		}  else if( ae.getSource() == jbtn_clear ) {
			System.out.println("You pressed the CLEAR button");
			clearFields();
		}
		else {
			System.out.println("How did you get here ???");
		}
	}
 
	/**
	 * Method obtains the values entered in the GUI
	 * @return boolean = true is values obtained without error
	 */
	private boolean getValues() {
 
		try {
			underlyingPrice = getDoubleFromString(jtxt_underlyingPrice.getText(), "underlying price");
			strikePrice = getDoubleFromString(jtxt_strikePrice.getText(), "strike price");
			volatility = getDoubleFromString(jtxt_volatility.getText(), "volatility");
			intRate = getDoubleFromString(jtxt_interestRate.getText(), "interest rate");
			
			expirationDt = getDateFromString(jtxt_expirationDt.getText(), "expiration date");
			valuationDt = getDateFromString(jtxt_valuationDt.getText(), "valuation date");
			Double dateDiff =  (double) (expirationDt.getTime() - valuationDt.getTime());
			timeToExpiration = ( dateDiff / (1000 * 60 * 60 * 24) )/365;
			//System.out.println("## dateDiff-[" + dateDiff + "] timeToExpirartion-[" + timeToExpiration + "]");
			if( dateDiff < 0 ) {
				JOptionPane.showMessageDialog(null, "expiration date must be later than valuaton date");
			}
			
		} catch (NumberFormatException nfe) {
			System.out.println("Input issue: retype underlyingPrice");
			return false;
		}
		System.out.println(toString());
		return true;
	}
 
	/**
	 * This toString() method allows a convenient way to output the 
	 * results of the Black-Scholes calculation.  
	 * @return String = String formatted output of Black-Scholes valuation
	 */
	public String toString() {
		String outString = "Underlying Price: [" + underlyingPrice + "] \n";
		outString += "    Strike Price: [" + strikePrice + "] \n";
		outString += "      volatility: [" + volatility + "] \n";
		outString += "   interest rate: [" + intRate + "] \n";
		outString += " expiration date: [" + expirationDt.toString() + "] \n";
		outString += "  valuation date: [" + valuationDt.toString() + "] \n";
		outString += "time to expiration[" + timeToExpiration + "] \n";
		outString += "       option type[" + jcbox_optionType.getSelectedItem() + "] \n";
		outString += "         valuation[" + valuation + "] \n";
		outString += "             delta[" + delta + "] \n";
		outString += "             notes[" + notes.getText() + "] \n";
 
		return outString;
	}
	
	/**
	 * Method generates a pipe-delimited string used for
	 * output to file
	 * @return
	 */
	public String pipeString(){
		String outString  = rightNow.toString() + delim;
			   outString += underlyingPrice + delim + strikePrice + delim;
		       outString += volatility + delim + intRate + delim;
		       outString += expirationDt.toString() + delim;
		       outString += valuationDt + delim + timeToExpiration + delim;
		       outString += jcbox_optionType.getSelectedItem() + delim;
		       outString += valuation + delim + delta + delim;
		       outString += notes.getText() + delim;
		       outString += "\n";
		
		return outString;
	}
	
	/**
	 * Method gets column header info used when output sent to file
	 * @return
	 */
	public String getHeader() {
		String outString  = "processing date" + delim;
		   outString += "underlying price" + delim + "strike price" + delim;
	       outString += "volatility" + delim + "intRate" + delim ;
	       outString += "expiration date" + delim;
	       outString += "valuation date" + delim + "timeToExpiration" + delim;
	       outString += "option type" + delim + "valuation" + delim + "delta" + delim;
	       outString += "notes" + delim;
	       outString += "\n";
	       
	       return outString;
	}

 
	/**
	 * Helper method to obtain a Double from a String
	 * @param inText
	 * @param textField
	 * @return
	 */
	private Double getDoubleFromString(String inText, String textField) {
		Double tempDouble = 0.0;
		try {
			tempDouble = Double.valueOf(inText);
		} catch (NumberFormatException nfe) {
 
			String message = textField + " is unpopulated or does not have a properly enterd number";
			message += "\n Please re-enter and try again";
 
			System.out.println(message);
			JOptionPane.showMessageDialog( null, message,textField,
					JOptionPane.INFORMATION_MESSAGE );
 
			throw nfe;
		}
		return tempDouble;
	}
	
	/**
	 * Method manages date/time conversion and formatting
	 * @param inDate
	 * @param inText
	 * @return
	 */
	private Date getDateFromString(String inDate, String inText){
		String expectedPattern = "MM/dd/yyyy";
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat( expectedPattern );
		try {
			date = formatter.parse( inDate );
		} catch( ParseException pe ) {
			String message = inText + " is unpopulated or does not have a properly enterd number";
			message += "\n Please re-enter using MM/dd/yyyy and try again";
 
			System.out.println(message);
			JOptionPane.showMessageDialog(null, message, "ERROR",JOptionPane.ERROR_MESSAGE);
		}
		return date;
	}
	
	/**
	 * Method adds JTextField widgets to JPanel
	 */
	private void addTxtFieldsToPanel(){
		pnl_txtFields.add(jtxt_underlyingPrice);
		pnl_txtFields.add(jtxt_strikePrice);
		pnl_txtFields.add(jtxt_expirationDt);
		pnl_txtFields.add(jtxt_valuationDt);
		pnl_txtFields.add(jtxt_volatility);
		pnl_txtFields.add(jtxt_interestRate);
		pnl_txtFields.add(jcbox_optionType);
		// pnl_txtFields.add( notes );
		pnl_txtFields.add(jtxt_fairValue);
		pnl_txtFields.add(jtxt_delta);
	}
	
	/**
	 * Method creates the text fields
	 */
	private void createTextFields(){
		// create text entry fields for each label
		jtxt_underlyingPrice = new JTextField();
		jtxt_strikePrice = new JTextField();
		jtxt_expirationDt = new JTextField();
		jtxt_valuationDt = new JTextField();
		jtxt_volatility = new JTextField();
		jtxt_interestRate = new JTextField();
		jtxt_optionType = new JTextField();
		// jtxt_notes = new JTextField();
		jtxt_fairValue = new JTextField();
		jtxt_delta = new JTextField();
	}
	
	/**
	 * Method formats text alignment 
	 */
	private void setTextFieldAlignment(){
		jtxt_underlyingPrice.setHorizontalAlignment(JTextField.RIGHT);
		jtxt_strikePrice.setHorizontalAlignment(JTextField.RIGHT);
		jtxt_expirationDt.setHorizontalAlignment(JTextField.RIGHT);
		jtxt_valuationDt.setHorizontalAlignment(JTextField.RIGHT);
		jtxt_volatility.setHorizontalAlignment(JTextField.RIGHT); 
		jtxt_interestRate.setHorizontalAlignment(JTextField.RIGHT); 
		jtxt_optionType.setHorizontalAlignment(JTextField.RIGHT); 
		jtxt_fairValue.setHorizontalAlignment(JTextField.RIGHT);
		
		jtxt_delta.setHorizontalAlignment(JTextField.RIGHT);
		jtxt_fairValue.setHorizontalAlignment(JTextField.RIGHT);
		
		jtxt_underlyingPrice.setBackground(Color.yellow);
		jtxt_strikePrice.setBackground(Color.yellow);
		jtxt_expirationDt.setBackground(Color.yellow);
		jtxt_valuationDt.setBackground(Color.yellow);
		jtxt_volatility.setBackground(Color.yellow);
		jtxt_interestRate.setBackground(Color.yellow);
		
		Font f = new Font("Helvetica", Font.BOLD, 20);
		jtxt_underlyingPrice.setFont(f);
		jtxt_strikePrice.setFont(f);
		jtxt_expirationDt.setFont(f);
		jtxt_valuationDt.setFont(f);
		jtxt_volatility.setFont(f);
		jtxt_interestRate.setFont(f);
		
		jtxt_delta.setFont(f);
		jtxt_fairValue.setFont(f);
	}

	
	
	
	/**
	 * This is the key method that runs the Black-Scholes option
	 * model based on the given input.
	 */
	private void runValuation() {
		BlackScholesFormula bs = new BlackScholesFormula();
		boolean isCallOption = true;
		
		if (jcbox_optionType.getSelectedItem().toString()
				.equalsIgnoreCase("PUT")) {
			isCallOption = false;
		}

		OptionDetails resp = bs.calculate(isCallOption, underlyingPrice,
				strikePrice, intRate, timeToExpiration, volatility);
		

		System.out.println("in runValuation() \n" + resp.toString());

		jtxt_delta.setText(format(resp.getDelta()));
		delta = resp.getDelta();
		jtxt_fairValue.setText( format(resp.getOptionValue()) );
		valuation = resp.getOptionValue();
		System.out.println(toString());
		System.out.println(" ===============");
	}
	
	/**
	 * Method clears input fields and prepares
	 * for next entry
	 */
	public void clearFields(){
		jtxt_underlyingPrice.setText("");
		jtxt_strikePrice.setText("");
		jtxt_expirationDt.setText("");
		jtxt_valuationDt.setText("");
		jtxt_volatility.setText("");
		jtxt_interestRate.setText("");
		
		jtxt_delta.setText("");
		jtxt_fairValue.setText("");
		notes.setText("");
	}
	
	/**
	 * Basic display format. Note that financial calculations
	 * are usually not overly formatted, as we could be dealing with
	 * commodities with a unit price of $0.000001 (like Yen), or 
	 * $600.00 (like gold).  
	 * @param n
	 * @return
	 */
	public String format(Number n) {
		NumberFormat format = DecimalFormat.getInstance();
		format.setMinimumFractionDigits(MIN_DECIMALS);
		format.setMaximumFractionDigits(MAX_DECIMALS);
		return format.format(n);
	}
	
	/**
	 * Method will send data to a set output file.  In
	 * future iterations I would consider adding an input
	 * field to allow the user to direct the file to a 
	 * specific directory.
	 * File is pipe ("|") delimited as that is useful when
	 * using Excel.
	 * Method will also print column titles if creating a new file, 
	 * otherwise will just continue to add data to exisiting file
	 * (each entry is date/time stamped)
	 */
	private void writeTextToFile() {
		File fileName = new File(OUTPUT_FILE);
		Boolean writeHeader = true;
		if( fileName.exists() && !fileName.isDirectory()) { 
		    writeHeader = false;
		}
		
		rightNow = new Date();
 
		try{
			// by setting the file writer boolean to "true" append is turned on 
			FileWriter outStream =  new FileWriter (fileName, true); 

			if( writeHeader ) {
				outStream.append( getHeader() );
			}
			outStream.append( pipeString() );
			System.out.println("attempting to write to file: \n" + pipeString() );
			outStream.close();
			JOptionPane.showMessageDialog(null, "output written to " + OUTPUT_FILE, "FILE UPDATED",
					JOptionPane.INFORMATION_MESSAGE );
		} catch(IOException e){
			JOptionPane.showMessageDialog(null, "error writting to file", "ERROR",JOptionPane.ERROR_MESSAGE);
			System.out.println("ERROR");
		}
	}
	
}

