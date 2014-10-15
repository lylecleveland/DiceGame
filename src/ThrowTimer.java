import dice.DSix;
import dice.DiceCup;

public class ThrowTimer {

	DSix d = new DSix();

	public static void main(String[] args) {
		long startTime = System.nanoTime();
		int[] test = new DiceCup(new DSix(), 1000000).shake();
		long stopTime = System.nanoTime();
		System.out.println("Execution Time: " + (stopTime - startTime) / 1000000);
		int ones = 0;
		int twos = 0;
		int threes = 0;
		int fours = 0;
		int fives = 0;
		int sixes = 0;
		double sum = 0.0;
		for (int i : test) {
			sum += i;
			switch (i) {
			case 1:
				ones++;
				break;
			case 2:
				twos++;
				break;
			case 3:
				threes++;
				break;
			case 4:
				fours++;
				break;
			case 5:
				fives++;
				break;
			case 6:
				sixes++;
				break;
			}
		}
		
		System.out.println("Ones: " + ones);
		System.out.println("Twos: " + twos);
		System.out.println("Threes: " + threes);
		System.out.println("Fours: " + fours);
		System.out.println("Fives: " + fives);
		System.out.println("Sixes: " + sixes);
		System.out.println("Average: " + sum / test.length);
	}
}
