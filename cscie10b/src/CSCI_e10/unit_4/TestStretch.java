package CSCI_e10.unit_4;


import java.util.Arrays;

/**
 * 
 * @author M Bret Blackford ID:20849347
 * CSCI E-10a Fall 2015
 * Unit 4 Problem Set question [9]
 *
 */
public class TestStretch {

	/**
	 * Method will "stretch" the original int array.
	 * Each value is replaced with two values half the size.
	 * @param inArray
	 * @return
	 */
	public static int[] stretch(int[] inArray){
		
		int[] doubleSize = new int[ (inArray.length * 2) ];
		int doubleCount = 0;
		
		for( int i = 0; i < inArray.length; i++ ){
			
			if( (inArray[i] % 2) == 0 ){ //EVEN number
				doubleSize[doubleCount] = ( inArray[i] / 2 );
				doubleCount++;
				doubleSize[doubleCount] = ( inArray[i] / 2 );
				doubleCount++;
			} else {
				doubleSize[doubleCount] = ( inArray[i] / 2 ) + 1;
				doubleCount++;
				doubleSize[doubleCount] = ( inArray[i] / 2 );
				doubleCount++;
			}
		}
		
		return doubleSize;
		
	}
	
	public static void main(String[] args) {
		
		System.out.println();
		
		// list is test data
		int[] list = { 18, 7, 4, 14, 11 };
		int[] list2 = stretch(list);
		
		System.out.println( Arrays.toString(list));
		System.out.println( Arrays.toString(list2));
		System.out.println();
		
		// list3 is test data
		int[] list3 = { 18, 7, 4, 24, 11 };
		int[] list4 = stretch(list3);
		
		System.out.println( Arrays.toString(list3));
		System.out.println( Arrays.toString(list4));
		System.out.println();
	}

}
