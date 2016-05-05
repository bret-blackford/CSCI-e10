package CSCI_e10.unit_2a;

public class Prob11 {

	static void drawFigure() {
		final int N = 5;
		for (int line = 1; line <= N; line++){
			for (int i = 1; i<=((N-1)*(N-(line)));i++){
				System.out.print("/");
			}
			
			for (int i = 1; i<=((line-1)*(N-1))*2;i++){
				System.out.print("*");
			}
			
			for (int i = 1; i<=((N-1)*(N-(line)));i++) {
				System.out.print("\\");
			}
			
			System.out.println();			
		}
	}
	
	public static void main(String[] args) {

		drawFigure();

	}

}

