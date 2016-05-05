
package CSCI_e10.unit_2b;


public class ThisOldMan {

// M Bret Blackford
// ID: 20849347

	static void verse(int n, String s){
		System.out.println("This old man, he played " + n + ".");
		System.out.println("He played nick-nack " + s + ";");

		chorus();
	}
	
	static void chorus() {
		System.out.println("With a nick-nack paddy-whack, give the dog a bone.");
		System.out.println("This old man came running home.");
		System.out.println();
	}
			
	
	public static void main(String[] args) {
		
		verse(1, "on my tree");
		verse(2, "on my shoe");
		verse(3, "on my tree");
		verse(4, "on my door");
		verse(5, "on my hive");
		verse(6, "on my sticks");
		verse(7, "up in heaven");
		verse(8, "on my gate");
		verse(9, "on my spine");
		verse(10, "on my hen");
	}

}

