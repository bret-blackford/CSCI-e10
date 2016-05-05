package CSCI_e10.unit_3b;

public class RationalTest {

	/**
	 * This program designed to test the 
	 * new Rational.lessThanRat() method
	 * @param args
	 */
	public static void main(String[] args) {

		Rational a = new Rational(8, 16);
		Rational b = new Rational(2, 3);

		System.out.print("is 8/16 less than 2/3? ");
		if (a.lessThanRat(b)) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}

	}

}
