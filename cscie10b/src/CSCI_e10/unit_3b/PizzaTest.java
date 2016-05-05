package CSCI_e10.unit_3b;



/**
 * @author MX-guest
 *
 */
public class PizzaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Pizza myPizza = new Pizza("Pepperoni", 8, 10.50, 10);
		Pizza myPizza2 = new Pizza("Pineapple & Pepper", 10, 11.95, 8);

		System.out.printf("Your %s pizza has %.2f square inches " + " per slice.\n", myPizza.getName(),
				myPizza.areaPerSlice());
		System.out.printf("One slice costs $%.2f, which comes" + " to $%.3f per square inch.\n", myPizza.costPerSlice(),
				myPizza.costPerSquareInch());
		System.out.println();
		System.out.printf("Your %s pizza has %.2f square inches " + " per slice.\n", myPizza2.getName(),
				myPizza2.areaPerSlice());
		System.out.printf("One slice costs $%.2f, which comes" + " to $%.3f per square inch.\n",
				myPizza2.costPerSlice(), myPizza2.costPerSquareInch());

	}

}
