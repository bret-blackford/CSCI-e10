package CSCI_e10.unit_2b;


import java.util.Scanner;

public class Increase {
	
	// M Bret Blackford
	// ID: 20849347

	public static void main(String[] args) {

	       final int noOfDays = 10;
	       
	       boolean firstDay = true;
	       double higher = 0;
	       double lower = 0; 
	       double delta = 0; 
	       int day = 0;
	       double stockPricePrior = 0.0;
	       //int x = in.nextInt();
	       double change = 0.0;
	       
	       System.out.println("Enter " + noOfDays + " days of stock prices: ");
	       System.out.println();

	       Scanner input = new Scanner(System.in);
	       
	       for (int i = 1; i <= noOfDays; i++)
	       {
	    	System.out.print(" Enter stock price for day [" + i + "]:");
	        
	    	double stockPrice = input.nextDouble();
	        
	        change = Math.abs(stockPrice - stockPricePrior);
	        
	        if( firstDay ) {
	        	change = 0.0;
	        	firstDay = false;
	        }
	        
	         if (delta < change)
	         { 
	           delta = (stockPrice - stockPricePrior);
	           higher = stockPrice;
	           lower = stockPricePrior;
	           day = i; 
	         }
	         stockPricePrior = stockPrice;
	       }    

	       System.out.println();
	       System.out.println("The largest increase is " + delta);
	       System.out.println("  from  " + lower + " to " + higher );
	       System.out.println("  occured between day #" + (day - 1) + " and day #" + day);

	}

}

