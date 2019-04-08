
package Model;

import java.util.Random;

/**
 * This class handles the dice actions for the game.
 * @author samansoltani
 *@version 2.0
 */
public class Dice {
	/**
	 * setting up  random value for the dice.
	 * @return the value of dice as an integer.
	 */
	public int Roll() {
		Random random = new Random();
		return random.nextInt(6) + 1;
	}
}
