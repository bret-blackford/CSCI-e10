package CSCI_e10.unit_3b;


public class ReverseTest {

	public static String printReverse(String in){
		
		int length = in.length();
		
		if( length < 1 ) {
			return "";
		}
		
		String backwards = "";
		for(int i = length; i > 0; i--) {
			backwards += in.charAt(i-1);
		}
		return backwards;
	}
	
	public static void main(String[] args) {
		
		System.out.println(printReverse("Bret"));
		System.out.println(printReverse("Bret Blackford"));
		System.out.println(printReverse(""));
		System.out.println(printReverse("k hjhjhjhb JjkhHH"));
		System.out.println(printReverse("Uncle Foobar"));

	}

}
