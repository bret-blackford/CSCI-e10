package CSCI_e10.unit_2b;


public class Digits_extraCredit {
	
	// M Bret Blackford
	// ID: 20849347

	static boolean works(int in) {
		
		boolean good = false;
		int temp = in * 4;
		
		//could find no numbers * 5 that worked
		//int temp = in * numberSize; 
		
		int reversed = 0;
		int forward = in;
		String reverseString = new String();
		String tempString = new String();
		
		while (temp > 0) {
		    reversed = ( temp % 10);
		    reverseString = reverseString + tempString.valueOf(reversed);
		    temp = temp / 10;
		    //System.out.println("reversed-[" + reversed + "] in-[" + in + "] reverseString-[" + reverseString + "]");
		}
		
		if( reverseString.equals(new String().valueOf(in) ) ) {
			good = true;
			System.out.println(" GOOD!! " + in);
			
			return good;
		}

		//System.out.println("Returning from works()");
		return good;
	}
	
	static final int numberSize = 5;
	
	public static void main(String[] args) {
		
		for ( int i=10000; i <= 99999; i++ ) {
			
			//System.out.println(" i=" + i);
			if ( works(i) ) {
				System.out.println("We have a winner! - " + i );
			}
		}
		System.out.println("Done");

	}

}

