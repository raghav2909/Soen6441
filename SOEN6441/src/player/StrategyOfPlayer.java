package player;

import java.util.ArrayList;
/**
 * this is the interface for Player Strategy classes.
 * @author NARRA
 * @version 3.0
 */
public interface StrategyOfPlayer {
	
	public void reinforcementPhase(int armies, String[] countryList);
	
	public void attackPhase(ArrayList<String> countryList);
	
	public void fortificationPhase(ArrayList<String> countryList);

	public String armyPlacing(String[] strings, String string);

	public int selectNumberOfDice(int diceToRoll, String pName);

	public int moveArmies(int aArmies, int maxArmies, String message);
	
	public String getNameOfStrategy();

}
