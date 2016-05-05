package CSCI_e10.unit_3b;


import java.util.Scanner;

/**
 * 
 * @author M Bret Blackford  ID:20849347
 *
 */
public class RealEstate {

	/**
	 * method checks to determine if charachter passed is a vowel. If 
	 * character is a vowel wil return boolean TRUE
	 * @param inChar
	 * @return
	 */
	public static boolean isVowel(char inChar) {

		if (inChar == 'a' || inChar == 'A')	return true;
		if (inChar == 'e' || inChar == 'E')	return true;
		if (inChar == 'i' || inChar == 'I')	return true;
		if (inChar == 'o' || inChar == 'O')	return true;
		if (inChar == 'u' || inChar == 'U')	return true;

		return false;
	}

	/**
	 * Method will remove vowels (except leading vowels) from a phrase
	 * @param inString
	 * @return
	 */
	public static String removeVowels(String inString) {

		String noVowels = "";
		boolean space = true;
		int length = inString.length();

		for (int i = 0; i < length; i++) {
			char temp = inString.charAt(i);

			// if prior character is a space then use even if vowel
			if (space) {
				noVowels += temp;
			} else if (!isVowel(temp)) {
				noVowels += temp;
			}
			space = isBlank(temp);
		}

		return noVowels;
	}

	/**
	 * Method checks to determine if charachter passed is a blank
	 * space. If blank space sets boolean to TRUE.
	 * @param inChar
	 * @return
	 */
	public static boolean isBlank(char inChar) {
		if (inChar == ' ') {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {

		String description = "Desirable unfurnished flat in quiet residential area";
		String newDesc = removeVowels(description);
		// System.out.println(description);
		// System.out.println(newDesc);

		System.out.print("Please enter your description of the property:");
		Scanner input = new Scanner(System.in);
		description = input.nextLine();
		newDesc = removeVowels(description);
		System.out.println(newDesc);

	}

}
