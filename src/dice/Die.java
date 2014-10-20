package dice;

/**
 * The Die abstract class is used as the base class for creating a die with a set number of sides.
 * 
 * @author Lyle Cleveland
 **/

import java.security.SecureRandom;

public abstract class Die implements Rollable {
	private String dieColor = Color.RED; // Default to red

	private String numColor = Color.WHITE; // Default to white

	private String material = Material.PLASTIC; // Default to plastic

	private String name;

	private double weight; // Measured in grams

	protected SecureRandom secRand = new SecureRandom(); // The RNG generator
															// for the die - can
															// be accessed by
															// children

	private int sides;

	/**
	 * Retrieves the color of the die.
	 * 
	 * @return String
	 */
	public String getDieColor() {
		return this.dieColor;
	}

	/**
	 * Gets the material the die is made of.
	 * 
	 * @return String
	 */
	public String getMaterial() {
		return this.material;
	}

	/**
	 * Returns the name of the die - Ex. D6
	 * 
	 * @return String
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the color of the numbers on the die.
	 * 
	 * @return String
	 */
	public String getNumColor() {
		return this.numColor;
	}

	/**
	 * Returns the number of sides on the die.
	 * 
	 * @return integer
	 */
	public int getSides() {
		return this.sides;
	}

	/**
	 * Gets the weight of the die in grams.
	 * 
	 * @return double
	 */
	public double getWeight() {
		return this.weight;
	}

	/**
	 * Rolls the die using a secure random between 1 and the number of sides on
	 * the die.
	 * 
	 * @return integer
	 */
	public int roll() {
		return secRand.nextInt(sides) + 1;
	}

	/**
	 * Sets the color of the die.
	 * 
	 * @param dieColor
	 *            The die color string.
	 */
	public void setDieColor(String dieColor) {
		this.dieColor = dieColor;
	}

	/**
	 * Sets the material the die is made of.
	 * 
	 * @param material
	 *            Ex. Wood, Plastic - See die.Material for presets
	 */
	public void setMaterial(String material) {
		this.material = material;
	}

	/**
	 * Sets the name of the die.
	 * 
	 * @param name
	 *            Ex. D6 for a six sided die.
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the color of the number on the die.
	 * 
	 * @param numColor
	 *            The color string for the die numbers.
	 */
	public void setNumColor(String numColor) {
		this.numColor = numColor;
	}

	/**
	 * Sets the number of sides the die has.
	 * 
	 * @param sides
	 *            The amount of sides on the die.
	 */
	public void setSides(int sides) {
		this.sides = sides;
	}

	/**
	 * Sets the weight of the die in grams.
	 * 
	 * @param weight
	 *            Weight in grams.
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
}
