package CSCI_e10.unit_5;


/**
 * 
 * @author mBlackford  M Bret Blackford
 * CSCI e-10b
 * Unit 5, Problem Set, part 1 
 * question [9]
 *
 */
public class Index {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String [] source = {"Barack Obama", "A rose is a rose", "Java is fun!", "Beatles"};
		String [] sub = {"bam", "in a", "un!", "B"};
		
		for( int i = 0; i < source.length; i++ ){
			int index = indexOf( source[i], sub[i] );
			
			if( index < 0 ) {
				System.out.println( "\"" + sub[i] + "\" is NOT a substring of \"" + source[i] + "\"");
			} else {
				System.out.println( "\"" + sub[i] + "\" is located at [" + index + "] of \"" + source[i] + "\"");
			}
		}
		

	}

	/**
	 * Recursive method will return the starting index of the 
	 * first occurrence of the second argument string
	 * @param source
	 * @param sub
	 * @return
	 */
	public static int indexOf(String source, String sub) {

		if (source.length() < sub.length()) {
			return -1;
		}

		String sourceSub = source.substring(0, sub.length());

		if (sourceSub.equals(sub)) {
			return 0;
		}

		String shorterSource = source.substring(1, source.length());

		int retValue = indexOf(shorterSource, sub);
		if (retValue < 0) {
			return -1;
		}

		return retValue + 1;
	}

}

