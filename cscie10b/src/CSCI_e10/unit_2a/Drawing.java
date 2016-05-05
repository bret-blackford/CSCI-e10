package CSCI_e10.unit_2a;

public class Drawing {

	static void top() {
		System.out.println("  _______");
		System.out.println(" /       \\");
		System.out.println("/         \\");
	}
	
	static void bottom() {
		System.out.println("\\         /");
		System.out.println(" \\       /");
		System.out.println("  -------");
	}
	
	static void middle() {
		System.out.println("-\"-\'-\"-\'-\"-");
	}
	
	public static void main(String[] args) {
		
		top();
		middle();
		bottom();

	}

}

