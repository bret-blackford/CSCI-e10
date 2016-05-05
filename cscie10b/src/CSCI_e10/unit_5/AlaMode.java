package CSCI_e10.unit_5;

import java.util.Arrays;

/**
 * EXTRA CREDIT
 * 
 * @author mBlackford  M Bret Blackford
 * CSCI E-10b spring 2016
 * Unit 5 Problem Set, part 1 
 * EXTRA CREDIT [11]
 *
 */
public class AlaMode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[] a = {1, 2, 5, 21, 2, 17, -2 };
		int[] b = {0,0,1,0,-3, 21, 22, 21, 0, 21, 7};
		int[] c = {0, 2, 31, -2, 31, -2, 9, -2};
		int[] d = {7};
		
		System.out.println("mode of " + Arrays.toString(a) + " is [" + mode(a) + "]");
		System.out.println("mode of " + Arrays.toString(b) + " is [" + mode(b) + "]");
		System.out.println("mode of " + Arrays.toString(c) + " is [" + mode(c) + "]");
		System.out.println("mode of " + Arrays.toString(d) + " is [" + mode(d) + "]");
	}
	
	/**
	 * Method returns the mode (most often occurring number) in
	 * an array of integers
	 * @param inArray
	 * @return
	 */
	public static int mode(int[] inArray) {
		int modeElement = inArray[0];
		int maxElementCount = 0;

		//OUTER loop gets each successive element in the array and
		//then the INNER loop checks how many such integers exist
		//in the array (by looking at each element)
		for (int outer = 0; outer < inArray.length; outer++) {
			int element = inArray[outer];
			int count = 1;
			
			for (int inner = 0; inner < inArray.length; inner++) {
				if (inArray[inner] == element)
					count++;
				
				//check to see if we have a new winner
				if (count > maxElementCount) {
					modeElement = element;
					maxElementCount = count;
				}
			}//end of inner loop
		}//end of outer loop
		
		return modeElement;
	}

}
