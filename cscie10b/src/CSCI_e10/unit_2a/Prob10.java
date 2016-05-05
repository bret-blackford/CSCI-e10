package CSCI_e10.unit_2a;

public class Prob10 {

	static void print4x(){
		System.out.println("Controlling complexity is the essence of programming!");
		System.out.println("Controlling complexity is the essence of programming!");
		System.out.println("Controlling complexity is the essence of programming!");
		System.out.println("Controlling complexity is the essence of programming!");
	}
	
	static void print4x4(){
		print4x();
		print4x();
		print4x();
		print4x();
	}
	
	static void print4x4x4(){
		print4x4();
		print4x4();
		print4x4();
		print4x4();
	}
	
	
	public static void main(String[] args) {
		print4x4x4();
	}

}

