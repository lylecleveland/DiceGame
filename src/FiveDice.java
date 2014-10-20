import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import dicegame.DiceGame;

/**
 * This class was made to meet the requirements of the project and is
 * essentially a demo of the DiceGame.
 * 
 * @author Lyle Cleveland
 *
 */
public class FiveDice {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		DiceGame dg = new DiceGame(2);
		while (true) {
			int win = dg.play();
			dg.printPlayerRolls();
			switch (win) {
			case 1:
				System.out.println("Player One Wins!\n");
				break;
			case 2:
				System.out.println("Player Two Wins!\n");
				break;
			case -1:
				System.out.println("Tie!");
				break;
			}
			System.out.print("Enter anything to continue or q to quit: ");
			if (in.next().charAt(0) == 'q')
				break;
		}
		in.close();
		PrintWriter pw;
		try {
			pw = new PrintWriter("results.txt", "UTF-8");
		} catch (IOException e) {
			System.out.println(e);
			return;
		}
		System.out.println("Playing 1000 Games...");
		int one = 0;
		int two = 0;
		int tie = 0;
		for (int i = 0; i < 1000; ++i) {
			int win = dg.play();
			switch (win) {
			case 1:
				one++;
				break;
			case 2:
				two++;
				break;
			case -1:
				tie++;
				break;
			}
		}
		pw.write("1000 Unloaded Plays\r\n");
		pw.write("Player One Wins:\t" + one + "\r\n");
		pw.write("Player Two Wins:\t" + two + "\r\n");
		pw.write("Ties:\t" + tie + "\r\n");
		pw.flush();
		dg.setLoadedPlayer(2);
		one = 0;
		two = 0;
		tie = 0;
		System.out.println("Playing 1000 Loaded Games...");
		for (int i = 0; i < 1000; ++i) {
			int win = dg.play();
			switch (win) {
			case 1:
				one++;
				break;
			case 2:
				two++;
				break;
			case -1:
				tie++;
				break;
			}
		}
		pw.write("1000 Loaded Plays\r\n");
		pw.write("Player One Wins:\t" + one + "\r\n");
		pw.write("Loaded Player Two Wins:\t" + two + "\r\n");
		pw.write("Ties:\t" + tie + "\r\n");
		pw.flush();
		pw.close();
	}
}
