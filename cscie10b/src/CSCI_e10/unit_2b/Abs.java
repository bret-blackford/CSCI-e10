package CSCI_e10.unit_2b;

import java.util.Scanner;

public class Abs {
	
	// M Bret Blackford
	// ID: 20849347

	static int largerAbsValue(int x, int y) {
		
		int newX = x;
		if( x < 0) {
			newX = x * (-1);
		}
		
		int newY = y;
		if( y < 0) {
			newY = y * (-1);
		}
		
		if( newX > newY) {
			return newX;
		}
		
		return newY;
	}
	
	public static void main(String[] args) {

		 int int1 = 0;
		 int int2 = 0;
		 
		 System.out.println("Enter two integers and program will return the larger absolute value of the two");
		 Scanner input = new Scanner(System.in);
		 
		 System.out.println();
		 
		 System.out.print("Enter first integer:");
		 int1 = input.nextInt();
		 
		 System.out.print("Enter second integer:");
		 int2 = input.nextInt();
		 
		 System.out.println();

		 int largestInt = largerAbsValue(int1, int2);
		 System.out.println("The largest absolute value of the integers is :" + largestInt);

	}

}

