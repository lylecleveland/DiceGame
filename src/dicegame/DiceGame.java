package dicegame;

import java.util.Arrays;

import dice.DSix;
import dice.DiceCup;
import dice.Die;
import dice.LoadedDSix;

/**
 * This class is a dice game with the following rules: Throws a certain amount
 * of dice for each player than compares them to find a winner based on the
 * following criteria in order: Five of a kind beats everything, Four of a kind,
 * Three of a kind, Pair, Unmatched. The person with the highest unmatched value
 * wins a tie, if the highest unmatched is the same it is considered a tie game.
 * 
 * @author Lyle Cleveland
 *
 */
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

	// Constructors
	public DiceGame(Die die) {
		this(2, die, 5);
	}

	public DiceGame(int numPlayers) {
		this(numPlayers, new DSix(), 5);
	}

	public DiceGame(int numPlayers, DiceCup diceCup) {
		this.setNumPlayers(numPlayers);
		this.setDiceCup(diceCup);
		this.setDie(diceCup.getTypeOfDie());
		this.setNumDice(diceCup.getNumOfDice());
	}

	public DiceGame(int numPlayers, Die die, int numDice) {
		this.setNumPlayers(numPlayers);
		this.setDiceCup(new DiceCup(new DSix(), numDice));
		this.setDie(die);
	}

	// End Constructors

	/**
	 * Takes a score string and parses it to return the serialized double for
	 * ease of use. Ex. a pair of 2s with an unmatched 5 high would be 2.25
	 * 
	 * @param one
	 *            The score string to parse.
	 * @return double
	 */
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

	/**
	 * Evaluates a players roll in accordance with the game rules and returns
	 * the players score string. Ex. a pair of 2s with an unmatched 5 high would
	 * be pa25
	 * 
	 * @param playerRoll
	 *            Array containing players rolls.
	 * @return String
	 */
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

	/**
	 * Find the highest unmatched number in the array. Pass -1 to matchedNum to
	 * parse an array of unmatched numbers.
	 * 
	 * @param array
	 *            The array of numbers to parse.
	 * @param matchedNum
	 *            The matched number to skip
	 * @return int
	 */
	private int findHighestNum(int[] array, int matchedNum) {
		int temp = -1;
		for (int i : array)
			if (i > temp && i != matchedNum)
				temp = i;
		return temp;
	}

	/**
	 * Used to compare the players rolls and find a winner. Returns the winner.
	 * 
	 * @return int
	 */
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

	/**
	 * Function to populate the player rolls array.
	 */
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

	/**
	 * Get the dice cup that the game is using.
	 * 
	 * @return DiceCup
	 */
	public DiceCup getDiceCup() {
		return diceCup;
	}

	/**
	 * Get the die that is currently being used in the game.
	 * 
	 * @return Die
	 */
	public Die getDie() {
		return die;
	}

	/**
	 * Get the winner of the game. Returns -1 for tie and 0 for no winner.
	 * 
	 * @return int
	 */
	public int getGameWinner() {
		return gameWinner;
	}

	/**
	 * Get the loaded player. Returns -1 if no player is loaded.
	 * 
	 * @return int
	 */
	public int getLoadedPlayer() {
		return loadedPlayer;
	}

	/**
	 * Get the number of dice being used to play the game
	 * 
	 * @return int
	 */
	public int getNumDice() {
		return numDice;
	}

	/**
	 * Get the number of players in the game.
	 * 
	 * @return int
	 */
	public int getNumPlayers() {
		return numPlayers;
	}

	/**
	 * Get the array of rolls that was generated.
	 * 
	 * @return int[][]
	 */
	public int[][] getPlayerRolls() {
		return playerRolls;
	}

	/**
	 * The main function to play the game. Returns the winner of the game, -1
	 * for a tie.
	 * 
	 * @return int
	 */
	public int play() {
		this.generateRolls();
		this.setGameWinner(this.findWinner());
		return this.getGameWinner();
	}

	/**
	 * Prints the rolls of the last played game to the command line.
	 */
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

	/**
	 * Set the dice cup to use in the game
	 * 
	 * @param diceCup
	 *            The DiceCup to use.
	 */
	public void setDiceCup(DiceCup diceCup) {
		this.diceCup = diceCup;
	}

	/**
	 * Sets the die to use in the game.
	 * 
	 * @param die
	 *            The die to use.
	 */
	public void setDie(Die die) {
		if (this.getDiceCup().getTypeOfDie() != die)
			this.getDiceCup().setTypeOfDie(die);
		this.die = die;
	}

	/**
	 * Sets the game winner.
	 * 
	 * @param gameWinner
	 *            The winner of the game.
	 */
	public void setGameWinner(int gameWinner) {
		this.gameWinner = gameWinner;
	}

	/**
	 * Sets a player to use a loaded die. Default is a LoadedDSix
	 * 
	 * @param loadedPlayer
	 *            The player to use a loaded die.
	 */
	public void setLoadedPlayer(int loadedPlayer) {
		this.loadedPlayer = loadedPlayer;
		this.loadedDie = new LoadedDSix();
	}

	/**
	 * Sets the player to use a loaded die and the die to use.
	 * 
	 * @param playerNum
	 *            The player to use a loaded die.
	 * @param loadedDie
	 *            The die to use.
	 */
	public void setLoadedPlayer(int playerNum, Die loadedDie) {
		this.loadedPlayer = playerNum;
		this.loadedDie = loadedDie;
	}

	/**
	 * Set the number of dice to use for the game
	 * 
	 * @param numDice
	 *            Number of dice to use.
	 */
	public void setNumDice(int numDice) {
		this.numDice = numDice;
	}

	/**
	 * Set the number of players in the game.
	 * 
	 * @param numPlayers
	 *            Number of players in game.
	 */
	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}

	/**
	 * Method used to reset loaded player. Alternatively call setLoadedPlayer()
	 * with a value of -1.
	 */
	public void unloadPlayer() {
		this.setLoadedPlayer(-1);
	}
}
