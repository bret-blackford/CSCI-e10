package CSCI_e10.unit_3a;



import java.util.Random;

/**
 * 
 * @author Bret Blackford
 * CSCI e-10a
 *
 */
public class License {
	
	static final int RANDOM_PLATES = 20;
	
		public static void main(String[] args) {
		
		String licensePlate;
		int i = 0;
		while ( i < RANDOM_PLATES) {
			
			licensePlate = "";
			licensePlate += getRandomNum();
			licensePlate += getRandomNum();
			licensePlate += getRandomNum();
			
			licensePlate += getRandomLetter();
			licensePlate += getRandomLetter();
			licensePlate += getRandomLetter();
			
			System.out.println("License Plate [" + (i+1) + "]:" + licensePlate);
			
			i++;
		}
		
	}
	

	public static int getRandomNum() {
		return (int)( (Math.random() * 10) );
	}
	
	public static String getRandomLetter() {
		String capLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char letter = capLetters.charAt( (int)(Math.random() * 26) );
		return "" + letter;
	}
}
