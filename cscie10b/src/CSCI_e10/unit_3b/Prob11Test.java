
package CSCI_e10.unit_3b;


/**
 * 
 * @author M Bret Backford   ID: 20849347
 *
 */
public class Prob11Test {

	/**
	 * Program designed to test the Name class
	 * @param args
	 */
	public static void main(String[] args) {

		Name teacher = new Name("Henry", 'H', "Leitner");
		Name tf = new Name("Jan", "Jackson");

		System.out.println("            teacher is :" + teacher.getNormalOrder());
		System.out.println("       teacher is also :" + teacher.getReverseOrder());
		System.out.println("teacher's initials are :" + teacher.getInitials());
		System.out.println();

		System.out.println("            teaching fellow is :" + tf.getNormalOrder());
		System.out.println("       teaching fellow is also :" + tf.getReverseOrder());
		System.out.println("teaching fellow's initials are :" + tf.getInitials());

	}

}
