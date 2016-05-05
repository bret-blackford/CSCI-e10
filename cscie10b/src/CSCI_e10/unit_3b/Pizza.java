package CSCI_e10.unit_3b;


/**
 * @author M Bret Blackford  ID: 20849347
 * This program test the Pizza class
 */
public class Pizza {

	public int getSlices() {
		return slices;
	}

	public void setSlices(int slices) {
		this.slices = slices;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	private int slices;
	private double cost;
	private String name = "cheese";
	private double radius;
	private double area;

	/**
	 * Pizza constructor
	 * @param type
	 * @param inRadiusInches
	 * @param inCost
	 * @param inSlices
	 */
	public Pizza(String type, int inRadiusInches, double inCost, int inSlices) {

		name = type;
		slices = inSlices;
		cost = inCost;
		radius = inRadiusInches;
	}

	/**
	 * calculates the are per slice of pizza
	 * (based on parameters passed in constructor)
	 * @return
	 */
	public double areaPerSlice() {
		area = Math.PI * Math.pow(radius, 2);
		return (area / slices);
	}
	

	/**
	 * Calculates the cost per slice of pizza
	 * (based on parameters passed in constructor)
	 * @return
	 */
	public double costPerSlice() {
		return cost / slices;
	}
	
	/**
	 * Calculates the cost per square inch of pizza slice
	 * (based on parameters passed in constructor)
	 * @return
	 */
	public double costPerSquareInch() {
		return costPerSlice() / areaPerSlice();
	}

}
