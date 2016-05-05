package CSCI_e10.unit_2a;

public class Pattern {

	
	public static void main(String[] args) {
		final int HEIGHT = 5;
		int stars = HEIGHT;
		int space=0;
		int line=0;
		
		for (int i=1; i<=HEIGHT; i++){
			
			for( int skip=0; skip<(space); skip++){
				System.out.print(" ");
			}
			space++;
			
			for( ; line<stars; line++){
				System.out.print("*");
			}
			line = i;
			System.out.println();
		}

	}

}

