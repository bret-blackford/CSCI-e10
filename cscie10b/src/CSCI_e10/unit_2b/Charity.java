package CSCI_e10.unit_2b;


import java.util.Scanner;

public class Charity {

// M Bret Blackford
// ID: 20849347

	static void donor(double donation){
		
		if( donation < 0 ) {
			System.out.println( " Sorry, entry is invalid. Please enter a positive number.");
			return;
		}
		
		if( donation < 100 ) {
			System.out.println("Cheapskate!");
		}
		else if( donation < 500 ) {
			System.out.println("Friends");
		}
		else if( donation < 1000 ) {
			System.out.println("Supporters");
		}
		else if( donation < 10000 ){
			System.out.println("Patrons");
		}
		else if ( donation >= 10000) {
			System.out.println("Benefactors");
		}
		
	}
	
	public static void main(String[] args) {
		
		double contribution = 0.0;
		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter amount of contribution: ");
		contribution = input.nextDouble() ;
	
		donor(contribution);
		
		System.out.println();

	}

}

