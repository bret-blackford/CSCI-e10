
package CSCI_e10.unit_4;


import java.util.Arrays;

/**
 * 
 * @author M Bret Blackford  ID:20849347
 *  CSCI E-10a  Fall 2015
 *  Unit 4 Problem Set question [10]
 *
 */
public class MindTheGap {

	/**
	 * Method returns the minimum gap between adjacent values in an array.
	 * @param inArray
	 * @return
	 */
	public static int minGap(int[] inArray){
		
		if( inArray.length < 2) {
			return 0;
		}
		
		int minGap = inArray[1] - inArray[0];
		
		for( int i = 0; i < (inArray.length - 1); i++ ){
			
			//Bellow displays for debugging
			//System.out.print("looking at [" + i + "]:" + inArray[i]);
			//System.out.println(" and [" + i + "]:" + inArray[i+1]);
			
			int temp = inArray[i+1] - inArray[i];
			
			if( temp < minGap ) {
				minGap = temp;
			}
			
			//System.out.println("minGap = " + minGap);
		}
		
		return minGap;
	}
	
	public static void main(String[] args) {

		int[] array1 = {1, 3, 6, 7, 12};
		int[] array2 = {3, 5, 11, 4, 8};
		int[] array3 = {3};
		int[] array4 = {-3, -4, -526, 23, 687, 2, 1, 0, 2, -2, 47};
		int[] array5 = {7, 10};
		
		System.out.println();
		System.out.print("Array " + Arrays.toString(array1) );
		int gap = minGap(array1);
		System.out.println(" has a minimum gap of " + gap);
		
		System.out.println();
		System.out.print("Array " + Arrays.toString(array2) );
		gap = minGap(array2);
		System.out.println(" has a minimum gap of " + gap);
		
		System.out.println();
		System.out.print("Array " + Arrays.toString(array3) );
		gap = minGap(array3);
		System.out.println(" has a minimum gap of " + gap);
		
		System.out.println();
		System.out.print("Array " + Arrays.toString(array4) );
		gap = minGap(array4);
		System.out.println(" has a minimum gap of " + gap);
		
		System.out.println();
		System.out.print("Array " + Arrays.toString(array5) );
		gap = minGap(array5);
		System.out.println(" has a minimum gap of " + gap);
		
		System.out.println();
	}

}
