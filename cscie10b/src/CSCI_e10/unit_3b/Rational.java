
package CSCI_e10.unit_3b;
public class Rational {

	// define instance variables
	//
	private int numerator;
	private int denominator;

	// define constructors
	//
	public Rational(int n, int d) {
		numerator = n;
		denominator = d;
	}

	public Rational(int n) {
		numerator = n;
		denominator = 1;
	}

	public Rational(Rational r) {
		numerator = r.numerator;
		denominator = r.denominator;
	}

	public Rational() {
		numerator = 0;
		denominator = 1;
	}

	// now the instance methods!
	//
	public Rational mulRat(Rational multiplier) {
		int topPartAnswer = numerator * multiplier.numerator;
		int bottomPartAnswer = denominator * multiplier.denominator;
		return new Rational(topPartAnswer, bottomPartAnswer);
	}

	public Rational addRat(Rational addend) {
		int top = numerator * addend.denominator + denominator * addend.numerator;
		int bottom = denominator * addend.denominator;
		return new Rational(top, bottom);
	}
	
	/**
	 * Method added to compare two rational numbers
	 * @param inRat
	 * @return
	 */
	public boolean lessThanRat(Rational inRat){
		double thisRat = (double)numerator / (double)denominator;
		double thatRat = (double)inRat.numerator / (double)inRat.denominator;
		
		if( thisRat < thatRat ) {
			return true;
		}
		return false;
	}
}
