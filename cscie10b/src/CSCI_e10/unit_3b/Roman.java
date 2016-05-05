
package CSCI_e10.unit_3b;


import java.util.Scanner;

/**
 * EXTRA CREDIT
 *
 * @author M Bret Blackford   ID: 20849347
 *
 */
public class Roman {

	/**
	 * Method generates values for Roman letters
	 * @param in
	 * @return
	 */
	public static int charToRomanNum(char in){
		if( in == 'M' || in == 'm') return 1000;
		if( in == 'D' || in == 'd') return 500;
		if( in == 'C' || in == 'c') return 100;
		if( in == 'L' || in == 'l') return 50;
		if( in == 'X' || in == 'x') return 10;
		if( in == 'V' || in == 'v') return 5;
		if( in == 'I' || in == 'i') return 1;
		
		return 0;
	}
	
	/**
	 * Converts a Roman numeral (passed as a String) into 
	 * a standrad integer value
	 * @param inRomanNum
	 * @return
	 */
	public static int convertRomanNumber(String inRomanNum){
		
		int value = 0;
		int length = inRomanNum.length();
		for(int i = 0; i<length - 1; i++) {
			
			if( charToRomanNum(inRomanNum.charAt(i)) < charToRomanNum(inRomanNum.charAt(i + 1) )){
				value -= charToRomanNum(inRomanNum.charAt(i));
			}else {
				value += charToRomanNum(inRomanNum.charAt(i));
			}
		}
		value += charToRomanNum(inRomanNum.charAt(length - 1));
		return value;
	}
	
	/**
	 * Main program used to test logic of Roman
	 * numeral conversion
	 * @param args
	 */
	public static void main(String[] args) {

		String inputRomanNumber;
		System.out.print("Please enter a Roma numeral :");
		Scanner input = new Scanner(System.in);
		inputRomanNumber = input.nextLine();
		
		int converted = convertRomanNumber(inputRomanNumber);
		
		System.out.println();
		System.out.println("You entered:" + inputRomanNumber);
		System.out.println("Which is:" + converted);
		

	}

}
