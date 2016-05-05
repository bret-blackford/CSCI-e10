
package CSCI_e10.unit_3b;


/**
 * 
 * @author M Bret Blackfordd ID: 20849347
 *
 */
public class Name {

	private String firstName;
	private char middleInitial;
	private String lastName;
	private boolean hasMiddleName;

	/**
	 * Constructor that accepts standard name inputs
	 * @param first
	 * @param middle
	 * @param last
	 */
	public Name(String first, char middle, String last) {
		firstName = first;
		middleInitial = middle;
		lastName = last;
		hasMiddleName = true;
	}

	/**
	 * Constructor for only first and last named individuals
	 * @param first
	 * @param last
	 */
	public Name(String first, String last) {
		firstName = first;
		lastName = last;
		hasMiddleName = false;
	}

	public String getNormalOrder() {
		String fullName = firstName + " ";

		if (hasMiddleName) {
			fullName += middleInitial + ". ";
		}
		fullName += lastName;

		return fullName;
	}

	public String getReverseOrder() {
		String fullName = lastName + ", ";
		if (hasMiddleName) {
			fullName += middleInitial + ". ";
		}
		fullName += firstName;
		
		return fullName;
	}
	
	/**
	 * Strips first character from first, middle, and
	 * last name and returns initials
	 * @return
	 */
	public String getInitials(){
		String initials = "" + firstName.charAt(0);
		if (hasMiddleName) {
			initials += middleInitial;
		}
		initials += lastName.charAt(0);
		
		return initials;
	}

}
