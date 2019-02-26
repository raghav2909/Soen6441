package Model;
/**
 * This class handles all actions about card.
 * @author samansoltani
 * @version 1.0
 */

public class Card
{
	/**
	 * Save the name of the card
	 */
	String Name;
	/**
	 * Save the type of the card
	 */
	String Type;
	/**
	 * Set up a card with name and type
	 * @param Name the name of card
	 * @param Type the type of the card
	 */
	Card (String Name ,String Type)
	{
		this.Name = Name;
		this.Type = Type;
	}
	/**
	 * getting the name of card
	 * @return the name of card
	 */
	String getName()
	{
		return this.Name;
	}
	/**
	 * getting the type of card
	 * @return the type of card
	 */
	String getType()
	{
		return this.Type;
	}
}
