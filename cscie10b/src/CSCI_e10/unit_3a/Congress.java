package CSCI_e10.unit_3a;



import java.util.Scanner;

/**
 * 
 * @author mblackford
 * CSCI e-10a
 */
public class Congress {

	public static boolean eligibleForSenate(int age, int lengthOfCitizenship) {

		boolean eligible = false;

		if (age >= 30 && lengthOfCitizenship >= 9) {
			eligible = true;
		}
		return eligible;
	}

	public static boolean eligibleForHouse(int age, int lengthOfCitizenship) {
		boolean eligible = false;

		if (age >= 25 && lengthOfCitizenship >= 7) {
			eligible = true;
		}
		return eligible;
	}

	public static void main(String[] args) {

		System.out.println("CONGRESS ELIGIBILITY");
		System.out.println();

		Scanner input = new Scanner(System.in);

		System.out.println();

		System.out.print("Enter age of candidate: ");
		int age = 0;
		age = input.nextInt();

		System.out.print("Enter years of U.S citizenship: ");
		int citizen = 0;
		citizen = input.nextInt();

		System.out.println();

		boolean eligibleForSenate = eligibleForSenate(age, citizen);
		boolean eligibleForHouse = eligibleForHouse(age, citizen);

		if (eligibleForSenate && eligibleForHouse) {
			System.out.println(
					"The candidate is eligible for election to both the House of Representatives and the Senate.");
		} else if (eligibleForSenate && !eligibleForHouse) {
			System.out.println(
					"The candidate is eligible for election to the Senate but is NOT eligible for election to the House of Representatives.");

		} else if (!eligibleForSenate && eligibleForHouse) {
			// case included as laws/constitution may change
			System.out.println(
					"The candidate is eligible for election to the House of Representatives but is NOT eligible for election to the Senate.");
		} else {
			System.out.println(
					"The candidate is NOT eligible for election to either the House of Representatives or the Senate.");
		}
		
		System.out.println();
		System.out.println("====================================================");

	}

}
