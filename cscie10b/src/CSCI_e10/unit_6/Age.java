
package CSCI_e10.unit_6;


import javax.swing.*;

/**
 * 
 * @author M Bret Blackford
 * 	ID: 20849347 
 *  CSCI E-10b Sprint 2016 
 *  Unit 6 Problem Set 
 *  Question [1] -- Age.java
 *
 * Application is a simple use of the JDOptionPane to
 * ask for user input, accept user input, and respond to user input
 * with various pop-up windows
 */
public class Age {

	public static void main(String[] args) {

		String ageString = JOptionPane
				.showInputDialog("What's your are, Cowboy?");
		String output = "";

		try {
			//obtain user input as a String and convert to a number
			double age = Double.parseDouble(ageString);

			if (age < 40) {
				output = "Young Whipper-Snapper!";
			} else {
				output = "Dang!  You are old!!";
			}
			JOptionPane.showMessageDialog(null, output);
		} catch (NumberFormatException nfe) {
			output = "Please enter a valid age";
			JOptionPane.showMessageDialog(null, output, "** INPUT ERROR **",
					JOptionPane.ERROR_MESSAGE);
		} catch(Exception e){
			System.out.println("Some crazy uneXpected error");
		}
	}
}

