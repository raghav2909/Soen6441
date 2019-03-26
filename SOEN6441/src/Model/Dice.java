
package Model;

import java.util.Random;

/**
 * This class handles the dice actions.
 * @author samansoltani
 *@version 2.0
 */
public class Dice {
	/**
	 * setting up a random value for dice.
	 * @return the value of dice as an integer.
	 */
	public int Roll() {
		Random rand = new Random();
		return rand.nextInt(6) + 1;
	}
}
