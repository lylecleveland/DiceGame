package dice;

/**
 * Loaded DSix is made to never roll a one.
 * 
 * @author Lyle Cleveland
 *
 */
public class LoadedDSix extends DSix {
	public int roll() {
		return secRand.nextInt(this.getSides() - 2) + 2;
	}
}
