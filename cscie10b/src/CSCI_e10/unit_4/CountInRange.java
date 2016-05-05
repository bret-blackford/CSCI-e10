package CSCI_e10.unit_4;



import java.util.Scanner;

/**
 * 
 * @author M Bret Blackford  ID:20849347
 *  CSCI E-10a  Fall 2015
 *  Unit 4 Problem Set  question [8]
 *
 */
public class CountInRange {

	/**
	 * Method counts number of items in an array are between a MIN and MAX value
	 * @param inArray
	 * @param min
	 * @param max
	 * @return
	 */
	public static int countInRange(int[] inArray, int min, int max){
		
		int count = 0;
		
		for( int i = 0; i < inArray.length; i++ ){
			if( inArray[i] >= min && inArray[i] <= max ) {
				count++;
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println();
		System.out.print("How many integers would you like to enter? ");

		int arraySize = input.nextInt();
		int[] arrayOfInts = new int[arraySize];
		
		for( int i = 0; i < arrayOfInts.length; i++ ){
			System.out.print("Enter number [" + (i + 1) + "]:");
			arrayOfInts[i] = input.nextInt();
		}
		System.out.println();
		
		System.out.print("Now eneter MIN value:");
		int minValue = input.nextInt();
		
		System.out.print("Now eneter MAX value:");
		int maxValue = input.nextInt();
		
		System.out.println();
		
		int count = countInRange(arrayOfInts, minValue, maxValue);
		
		System.out.println("There are " + count + " numbers in the array between the MIN and MAX.");
		System.out.println();
		
	}

}
