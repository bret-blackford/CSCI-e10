package CSCI_e10.unit_2b;



import java.util.Scanner;

public class Temperature {
	
	// M Bret Blackford
	// ID: 20849347

	static void checkTemp(double inTemp) {
		String sport = new String();
		 
		 System.out.println("Looks good for ...");
		 
		 if( inTemp <= 10 ) {
			 sport = "Checkers";
			 System.out.println("  " + sport);
		 }
		 if ( inTemp <= 32 && inTemp > 10) {
			 sport = "Skiing";
			 System.out.println("  " + sport);
		 }
		 if ( inTemp <=70 && inTemp > 32 ) {
			 sport = "Soccer";
			 System.out.println("  " + sport);
		 }
		 if( inTemp > 60 && inTemp <= 85 ) {
			 sport = "Tennis";
			 System.out.println("  " + sport);
		 }
		 if ( inTemp >= 75 ) {
			 sport = "Swimming";
			 System.out.println("  " + sport);
		 }
	}
	
	
	public static void main(String[] args) {
		
	double temp = 0.0;
	 
	System.out.println("Enter a temperature and the program will tell you the appropriate sport to be played");
	 
	 Scanner input = new Scanner(System.in);
	 
	 System.out.println();
	 
	 System.out.print("OK, mate, what's the temperature outside? ");
	 temp = input.nextDouble();
	
	 checkTemp(temp);
	 
	}

}

