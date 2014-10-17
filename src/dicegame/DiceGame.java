package dicegame;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dice.DSix;
import dice.DiceCup;
import dice.Die;
import dice.LoadedDSix;

public class DiceGame implements Playable {
	private int numPlayers;
	
	private int numDice;
	
	private Die die;
	
	private DiceCup diceCup;
	
	private int[][] playerRolls;
	
	private final String UNMATCH = "un";
	
	private final String PAIR = "pa";
	
	private final String THREE_OF_KIND = "th";
	
	private final String FOUR_OF_KIND = "fo";
	
	private final String FIVE_OF_KIND = "fi";
	
	private int loadedPlayer = -1;
	
	private Die loadedDie;
	
	private int gameWinner = -1;
	
	public int getGameWinner() {
		return gameWinner;
	}
	
	public void setGameWinner(int gameWinner) {
		this.gameWinner = gameWinner;
	}
	
	public DiceGame(Die die) {
		this(2, die, 5);
	}
	
	public DiceGame(int numPlayers) {
		this(numPlayers, new DSix(), 5);
	}
	
	public DiceGame(int numPlayers, DiceCup diceCup) {
		this.setNumPlayers(numPlayers);
		this.setDie(diceCup.getTypeOfDie());
		this.setDiceCup(diceCup);
		this.setNumDice(diceCup.getNumOfDice());
	}
	
	public DiceGame(int numPlayers, Die die, int numDice) {
		this.setNumPlayers(numPlayers);
		this.setDie(die);
		this.setDiceCup(new DiceCup(new DSix(), numDice));
	}
	
	private double evalMatch(String one) {
		String temp = one.substring(0, 2);
		double num = Integer.parseInt(String.valueOf(one.charAt(2))) / 10.0;
		double numTwo = Integer.parseInt(String.valueOf(one.charAt(3))) / 100.0;
		if (temp.equals(this.UNMATCH))
			return 1 + num + numTwo;
		if (temp.equals(this.PAIR))
			return 2 + num + numTwo;
		if (temp.equals(this.THREE_OF_KIND))
			return 3 + num + numTwo;
		if (temp.equals(this.FOUR_OF_KIND))
			return 4 + num + numTwo;
		if (temp.equals(this.FIVE_OF_KIND))
			return 5 + num + numTwo;
		return -1.0;
	}
	
	private String evaluateRoll(int[] playerRoll) {
		Arrays.sort(playerRoll);
		int highest = -1;
		int matchNum = -1;
		for (int i = 0; i < playerRoll.length; ++i) {
			int match = 1;
			for (int a = 0; a < playerRoll.length; ++a) {
				if (a == i)
					continue;
				if (playerRoll[i] == playerRoll[a])
					match++;
			}
			if (match >= highest) {
				if (playerRoll[i] > matchNum) {
					highest = match;
					matchNum = playerRoll[i];
				}
			}
		}
		switch (highest) {
		case 1:
			return this.UNMATCH + matchNum
					+ this.findHighestNum(playerRoll, -1);
		case 2:
			return this.PAIR + matchNum
					+ this.findHighestNum(playerRoll, matchNum);
		case 3:
			return this.THREE_OF_KIND + matchNum
					+ this.findHighestNum(playerRoll, matchNum);
		case 4:
			return this.FOUR_OF_KIND + matchNum
					+ this.findHighestNum(playerRoll, matchNum);
		case 5:
			return this.FIVE_OF_KIND + matchNum + "0";
		default:
			return "";
		}
	}
	
	private int findHighestNum(int[] array, int matchedNum) {
		int temp = -1;
		for (int i : array)
			if (i > temp && i != matchedNum)
				temp = i;
		return temp;
	}
	
	private int findWinner() {
		double highestMatch = -1.0;
		int winner = -1;
		for (int i = 0; i < playerRolls.length; ++i) {
			double rollScore = this.evalMatch(this
					.evaluateRoll(this.playerRolls[i]));
			if (rollScore > highestMatch) {
				highestMatch = rollScore;
				winner = i + 1;
			} else if (rollScore == highestMatch)
				winner = -1; // Tie
		}
		return winner;
	}
	
	private void generateRolls() {
		this.playerRolls = new int[this.numPlayers][this.numDice];
		for (int i = 0; i < playerRolls.length; ++i) {
			if (i == this.loadedPlayer - 1) {
				this.diceCup.setTypeOfDie(this.loadedDie);
				playerRolls[i] = this.diceCup.shake();
				this.diceCup.setTypeOfDie(this.getDie());
			} else
				playerRolls[i] = this.diceCup.shake();
		}
	}
	
	public DiceCup getDiceCup() {
		return diceCup;
	}
	
	public Die getDie() {
		return die;
	}
	
	public int getLoadedPlayer() {
		return loadedPlayer;
	}
	
	public int getNumDice() {
		return numDice;
	}
	
	public int getNumPlayers() {
		return numPlayers;
	}
	
	public int[][] getPlayerRolls() {
		return playerRolls;
	}
	
	public int play() {
		this.generateRolls();
		this.setGameWinner(this.findWinner());
		return this.getGameWinner();
	}
	
	public void printPlayerRolls() {
		int count = 1;
		for (int[] i : this.playerRolls) {
			System.out.print("Player " + count + ": ");
			for (int a : i)
				System.out.print(a);
			count++;
			System.out.println();
		}
	}
	
	public void setDiceCup(DiceCup diceCup) {
		this.diceCup = diceCup;
	}
	
	public void setDie(Die die) {
		this.die = die;
	}
	
	public void setLoadedPlayer(int loadedPlayer) {
		this.loadedPlayer = loadedPlayer;
		this.loadedDie = new LoadedDSix();
	}
	
	public void setLoadedPlayer(int playerNum, Die loadedDie) {
		this.loadedPlayer = playerNum;
		this.loadedDie = loadedDie;
	}
	
	public void setNumDice(int numDice) {
		this.numDice = numDice;
	}
	
	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}
}
