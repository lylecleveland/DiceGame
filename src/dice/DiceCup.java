package dice;

/**
 * The DiceCup class is meant to simulate putting dice into a cup and shaking
 * them and retrieving the values, like Yahtzee.
 * 
 * @author Lyle Cleveland
 * 
 */

public class DiceCup {
	private String cupColor = Color.BLACK; // Default to black

	private String cupMaterial = Material.LEATHER; // Default to leather

	private Die typeOfDie;

	private int numOfDice;

	/**
	 * Creates a new DiceCup object.
	 * 
	 * @param typeOfDie
	 *            Type of die to be rolled. Ex. - DSix
	 * @param numOfDice
	 *            Number of dice to be rolled.
	 */
	public DiceCup(Die typeOfDie, int numOfDice) {
		setTypeOfDie(typeOfDie);
		setNumOfDice(numOfDice);
	}

	/**
	 * Returns the color of the cup.
	 * 
	 * @return String
	 */
	public String getCupColor() {
		return cupColor;
	}

	/**
	 * Returns the type of material the cup is made of.
	 * 
	 * @return String
	 */
	public String getCupMaterial() {
		return cupMaterial;
	}

	/**
	 * Returns the number of dice that will be shook in the cup.
	 * 
	 * @return int
	 */
	public int getNumOfDice() {
		return numOfDice;
	}

	/**
	 * Returns the type of die being shook.
	 * 
	 * @return Die
	 */
	public Die getTypeOfDie() {
		return typeOfDie;
	}

	/**
	 * Sets the color of the cup.
	 * 
	 * @param cupColor
	 *            Color of the cup defined in Color.
	 */
	public void setCupColor(String cupColor) {
		this.cupColor = cupColor;
	}

	/**
	 * Sets the material the cup is made of.
	 * 
	 * @param cupMaterial
	 *            Material of the cup defined in Material.
	 */
	public void setCupMaterial(String cupMaterial) {
		this.cupMaterial = cupMaterial;
	}

	/**
	 * Sets the number of dice to be shook.
	 * 
	 * @param numOfDice
	 *            Number of dice to shake.
	 */
	public void setNumOfDice(int numOfDice) {
		this.numOfDice = numOfDice;
	}

	/**
	 * Sets the die to shake.
	 * 
	 * @param typeOfDie
	 *            Type of die to shake.
	 */
	public void setTypeOfDie(Die typeOfDie) {
		this.typeOfDie = typeOfDie;
	}

	/**
	 * Shakes the dice cup and returns the values of the dice.
	 * 
	 * @return int[]
	 */
	public int[] shake() {
		int[] results = new int[this.getNumOfDice()];
		for (int i = 0; i < this.getNumOfDice(); ++i) {
			results[i] = this.getTypeOfDie().roll();
		}
		return results;
	}
}
