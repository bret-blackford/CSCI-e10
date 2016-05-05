package CSCI_e10.unit_2b;

import java.util.Scanner;

public class Easter {

// M Bret Blackford
// ID: 20849347

public static String getMonth(int monthNumber) {
		
		if( monthNumber > 12 || monthNumber < 1) {
			return "ERROR";
		}
		
		String monthName = "";
		
		if(monthNumber == 1){
			monthName = "January";
		}
		if (monthNumber == 2) {
			monthName = "February";
		}
		if (monthNumber == 3) {
			monthName = "March";
		}
		if (monthNumber == 4) {
			monthName = "April";
		}
		if (monthNumber == 5) {
			monthName = "May";
		}
		if (monthNumber == 6) {
			monthName = "June";
		}
		if (monthNumber == 7) {
			monthName = "July";
		}
		if (monthNumber == 8) {
			monthName = "August";
		}
		if (monthNumber == 9) {
			monthName = "September";
		}
		if (monthNumber == 10) {
			monthName = "October";
		}
		if (monthNumber == 11) {
			monthName = "November";
		}
		if (monthNumber == 12) {
			monthName = "December";
		}
                
		return monthName;
	}


	    public static String computeEater(int y)
	    {
	    	//y = year; // (step 1)
	    	
	        int a = y % 19; //(step 2)
	        int b = y / 100; //(step 3)
	        int c = y % 100; //(step 3)
	        int d = b / 4; //(step 4)
	        int e = b % 4; //(step 4)
	        int g = (8 * b + 13) / 25; //(step 5)
	        int h = (19 * a + b - d - g + 15) % 30; //(step 6)
	        int j = c / 4; //(step 7)
	        int k = c % 4; //(step 7)
	        int m = (a + 11 * h) / 319; //(step 8)
	        int r = (2 * e + 2 * j - k - h + m + 32) % 7; //(step 9)
	        int n = (h - m + r + 90) / 25; //(step 10) ** MONTH **
	        int p = (h - m + r + n + 19) % 32; //(step 11) ** DAY **
	        
	        int day = p;
	        int month = n;

	        String monthName = getMonth(n);
	        String result = (monthName + ", " + day);
	        
	        System.out.println("Easter in the year " + y + " is on " + result);

	        return result;
	    }
	
	public static void main(String[] args) {
		 System.out.print("Enter a year <YYYY> to find the date of Easter: ");
	     
		 int year = 0;
		 
		 Scanner input = new Scanner(System.in);
	        year = input.nextInt();
	        	        
	        System.out.println();
	        String eaterDay = computeEater(year);
	    }

}

