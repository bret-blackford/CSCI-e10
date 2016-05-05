package CSCI_e10.unit_4;


import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author M Bret Blackford   ID:20849347
 * CSCI E-10a  Fall 2015
 * Unit 4 Problem Set  question [12]
 *
 * EXTRA CREDIT
 */
public class RepeatedDigits {

	
	/**
	 * Method finds how many times each of the digit 0 - 9 appear
	 * in an input value.
	 * 
	 * Acknowledgement to page 498 in text
	 * Building Java Programs: A Back to Basics Approach
	 * @param inArray
	 */
	public static void frequencyOfOccurance(int[] inArray) {
		
		int[] count = new int[10];
		
		for( int i = 0; i < inArray.length; i++ ){
			int next = inArray[i];
			count[next]++;
		}
		
		
		System.out.print("Digit:      ");
		for( int j = 0; j < count.length; j++ ){
			System.out.print( j + " ");
		}
		System.out.println();
		
		System.out.print("Occurances: ");
		for( int k = 0; k < count.length; k++ ){
			System.out.print( count[k] + " ");
		}
		System.out.println();
	}
	
	/**
	 * Helper method to turn a String into an array of integers
	 * @param in
	 * @return
	 */
	public static int[] stringToIntArray(String in) {
		int[] intArray = new int[in.length()];
		char[] charArray = in.toCharArray(); 
		
		for(int i = 0; i < charArray.length; i++) {
			intArray[i] = charArray[i] - '0';
		}
		return intArray;
	}
	
	/**
	 * Helper method to remove a leading zero
	 * @param inArray
	 * @return
	 */
	public static int[] checkForLeadingZero(int[] inArray){
		
		if( inArray[0] == 0 ) {
			int[] newArray = new int[inArray.length - 1];
			
			for(int i = 0; i < (inArray.length - 1); i++ ){
				newArray[i] = inArray[i+1];
			}
			return newArray;
		}
		
		return inArray;
	}
	
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		
		System.out.println();
		System.out.print("Please enter an integer:");
		
		//int inValue = input.nextInt();
		String inValue = input.next();
		String inputString = String.valueOf(inValue);
		
		int[] intArray = stringToIntArray(inputString);
		System.out.println( "Array of values enterd: " + Arrays.toString(intArray) );
		System.out.println();
		
		int[] cleanArray = checkForLeadingZero(intArray);
		frequencyOfOccurance(cleanArray);

	}

}
