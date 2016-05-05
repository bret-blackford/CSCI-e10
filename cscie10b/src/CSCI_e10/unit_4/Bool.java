package CSCI_e10.unit_4;



import java.util.Arrays;

/**
 * 
 * @author M Bret Blackford  ID:20849347
 *  CSCI E-10a Fall 2015
 *  Unit 4 Problem Set question [8]
 *
 */
public class Bool {

	/**
	 * Method reads an array of boolean values and
	 * outputs various criteria of the area
	 * @param inArray
	 */
	public static void fullOfBool(boolean[] inArray){
		
		System.out.print("Analyzing  the following array:");
		System.out.println( Arrays.toString(inArray) );
		
		//Locate for 1st TRUE
		boolean foundTrue = false;
		int firstTrue = 0;
		int count = 0;
		while( count < inArray.length && !foundTrue ){
			if( inArray[count] == true ) {
				firstTrue = count;
				foundTrue = true;
			}
			count++;
		}
		
		//Locate last TRUE
		int lastTrue = 0;
		for( int i = (inArray.length - 1); i > 0; i-- ){
			if( inArray[i] == true){
				lastTrue = i;
				i = -1;
			}
		}
		
		//Count TRUE occurrences  in array
		int numOfTrue = 0;
		for( int i = 0; i < inArray.length; i++ ){
			if( inArray[i] == true ){
				numOfTrue++;
			}
		}
		
		//Print results ...
		//System.out.println();
		System.out.println("Location of the first TRUE in the boolean array:[" + firstTrue + "]" );
		System.out.println("Location of the last TRUE in the boolean array:[" + lastTrue + "]" );
		System.out.println("Total number of TRUE values in the string of consecutive TRUE values:[" + numOfTrue + "]" );
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		boolean [] test1 = { false, true, true, true };
		boolean [] test2 = { true };
		boolean [] test3 = { true, true, true, true, false };
		
		System.out.println();
		System.out.println("Let's test ...");
		System.out.println();
		
		fullOfBool(test1);
		fullOfBool(test2);
		fullOfBool(test3);
		
		System.out.println();
		System.out.println("Done");
		
	}

}
