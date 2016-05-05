package CSCI_e10.unit_5;

import java.util.Scanner;


/**
 * 
 * @author mBlackford  M Bret Blackford
 * ID: 20849347
 * CSCI E-10b Spring 2016
 * Unit 5 Problem Set, part 1
 * question [5]
 *
 */
public class Palindrome {

	public static void main(String[] args) {

		System.out.println();
		System.out.print("Enter a word or sentence and we'll see if it is a palindrome: ");
		Scanner scanner = new Scanner(System.in);
		String inString = scanner.nextLine();
		System.out.println();
		
		//after testing will need to get string from user input via Scanner
		//String inString = "Cigar? Toss it in a can, it is so tragic!";
		
		String upperString = inString.toUpperCase();
		
		
		System.out.print("\"" + inString + "\" ");
		if( isPalindrome(upperString) ) {
			System.out.print("is ");
		} else {
			System.out.print("is not ");
		}
		System.out.println("a palindrome");
		System.out.println("---------- ---------- ---------- ----------");
	}
	
	public static boolean isPalindrome(String s){
		
		if (s.length() < 2) {
			return true;
		}
		
		String cleanFront = checkFront(s);
		if( cleanFront.length() < 2){
			return true;
		}
		
		String cleanBack = checkBack( cleanFront );
		if( cleanBack.length() < 2){
			return true;
		}

		if (cleanBack.charAt(0) != cleanBack.charAt(cleanBack.length() - 1)) {
			return false;
		}

		String subS = cleanBack.substring(1, cleanBack.length() - 1);
		return isPalindrome(subS);
	}
	


	/**
	 * Method checks for letters at front of a String (ignoring
	 * special characters).  Will return the String without any
	 * preceding special characters.
	 *
	 * @param in
	 * @return
	 */
	private static String checkFront(String in){
		
		for (int i=0; i < in.length(); i++) {
			if( Character.isLetter(in.charAt(i))){
				return( in.substring(i, in.length()) );
			}
		}
		return "";
	}
	
	/**
	 * Method checks for letters at the back of a String (ignoring
	 * any special characters). Will return the String without any
	 * trailing special characters.
	 * @param in
	 * @return
	 */
	private static String checkBack(String in){
		
		for (int i=1; i < in.length()+1; i++) {
			if( Character.isLetter(in.charAt(in.length() - i))){
				return( in.substring(0, in.length() - (i-1)) );
			}
		}
		return "";
	}

	/**
	 * PREFERED METHOD FOR RECURSIVELY DETERMINING PALINDROME
	 * 
	 * This method will determine if the string argument is 
	 * a palindrome (excluding any white space and special characters).
	 * @param s
	 * @return
	 */
	public static boolean isPalindrome2(String s) {

		String cleanString = scrubString(s);
		return checkPalindrome(cleanString);
	}
	
	private static boolean checkPalindrome(String s){	
		if (s.length() < 2) {
			return true;
		}

		if (s.charAt(0) != s.charAt(s.length() - 1)) {
			return false;
		}

		String subS = s.substring(1, s.length() - 1);

		return isPalindrome2(subS);
	}

	/**
	 * Helper method that will "scrub" a string, only retaining characters.
	 * 
	 * @param in
	 * @return
	 */
	private static String scrubString(String in) {

		String outString = "";

		for (int i = 0; i < in.length(); i++) {
			if (Character.isLetter(in.charAt(i))) {
				outString += in.charAt(i);
			}
		}
		return outString;
	}

}

