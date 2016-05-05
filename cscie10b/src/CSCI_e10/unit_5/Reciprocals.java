package CSCI_e10.unit_5;


/**
 * 
 * @author mBlackford  M Bret Blackford
 * CSCI e-10b
 * Unit 5, Problem Set, part 1
 * question [7]
 *
 */
public class Reciprocals {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

                System.out.println();
		System.out.println(" sumTo(2)=[" + sumTo(2) + "]");
                System.out.println(" sumTo(0)=[" + sumTo(0) + "]");
		System.out.println(" sumTo(5)=[" + sumTo(5) + "]");
                System.out.println(" sumTo(-2)=[" + sumTo(-2) + "]");
	}
	
	public static double sumTo(int n){
	     
		if (n < 0) {
	         throw new IllegalArgumentException("Illegal Argument - negatives not allowed");
	      } 

	      if(n == 0)
	         return 0.0; 

	      else
	         return (1/(double)n) + sumTo(n-1); 
	}

}

