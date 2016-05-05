package CSCI_e10.unit_5;

import java.util.Arrays;
import java.util.ArrayList;


/**
 * 
 * @author mBlackford  M Bret Blackford
 * ID: 20849347
 * CSCI e-10b
 * Unit 5, Problem Set, part 1
 * problem [6] 
 *
 */
public class LowestGrade {

	public static void main(String[] args) {

		int [] a = removeLowest( 23, 90, 47, 55, 88 );
		int [] b = removeLowest( 85 );
		int [] c = removeLowest( );
		int [] d = removeLowest( 59, 92, 93, 47, 88, 47 );
		
		System.out.println("array[] a = " + arrayPrint(a));
		System.out.println("array[] b = " + arrayPrint(b));
		System.out.println("array[] c = " + arrayPrint(c));
		System.out.println("array[] d = " + arrayPrint(d));

	}
	
	public static int[] removeLowest(int... numbers) {

		if (numbers.length < 2) {
			return numbers;
		}
		
		int currentSmallest = numbers[0];
		int index = 0;

		// iterate through the numbers array
		for (int number : numbers) {

			if (number < currentSmallest) {
				currentSmallest = number;
			}
			index++;
		}
		
		int[] newNumbers = removeElement(numbers, currentSmallest);
		return newNumbers;
	}
	
	/**
	 * Helper method to provide functionality similar t
	 * the Arrays.toString() method.
	 * @param inArray
	 * @return
	 */
	public static String arrayPrint(int[] inArray) {

		if (inArray.length < 1) {
			return "[]";
		}

		String outString = "[";
		for (int i = 0; i < inArray.length; i++) {
			outString += inArray[i];

			if (i < inArray.length - 1) {
				outString += ", ";
			}
		}

		return outString + "]";
	}
	
	
	/**
	 * Method will copy an array but not include the first 
	 * instance of the element item passed in
	 * @param original
	 * @param element
	 * @return
	 */
	public static int[] removeElement(int[] original, int element) {
		
		int[] newArray = new int[original.length - 1];
		int index = 0;
		int x = 0;
		boolean itemFound = false;
		
		while (index < original.length) {
			if (original[index] == element && itemFound == false) {
				index++;
				itemFound = true;
			}

			newArray[x] = original[index];
			index++;
			x++;
		}
		return newArray;
	}

}
