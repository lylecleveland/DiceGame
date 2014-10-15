import dice.DSix;
import dice.DiceCup;
import dice.LoadedDSix;

public class FiveDice {
	public static void main(String[] args) {
		int[] playerOneDice = new DiceCup(new DSix(), 5).shake();
		int[] playerTwoDice = new DiceCup(new LoadedDSix(), 5).shake();

		// Display the rolls
		System.out.println("Player\tFirst\tSecond\tThird\tFourth\tFifth");

		// Player One
		System.out.print("One\t");
		for (int i : playerOneDice) {
			System.out.print(i + "\t");
		}

		System.out.println("\r\n");

		// Player Two
		System.out.print("Two\t");
		for (int i : playerTwoDice) {
			System.out.print(i + "\t");
		}
	}
}
