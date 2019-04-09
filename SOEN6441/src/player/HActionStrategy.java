package player;

import java.util.ArrayList;

import Model.GameDriver;

/**
 * This class handles the Human Strategy of a player.
 * @author NARRA
 *@version 3.0
 */
public class HActionStrategy implements StrategyOfPlayer{
	
	/**
	 * instance of GameDriver
	 */
	private GameDriver driver;
	
	/**
	 * sets GameDriver instance
	 * @param newDriver
	 */
	public HActionStrategy(GameDriver newDriver) {
		driver = newDriver;
	}

	public void reinforcementPhase(int armies, String[] countryList) {
		System.out.print("Checkpoint 2");
		driver.reinforcementControls(armies, countryList);		
		driver.setControlsActionListeners();
	}

	public void attackPhase(ArrayList<String> countryList) {
		driver.attackControls(countryList.toArray(new String[countryList.size()]));
		driver.setAttackListeners();
	}

	public void fortificationPhase(ArrayList<String> countryList) {
		driver.fortificationControls(countryList.toArray(new String[countryList.size()]));
		driver.setFortificationLiteners();
	}
	/**
	 * Method for army placing
	 * @param countries
	 * @param name
	 * @return String
	 */
	public String armyPlacing(String[] countries, String name) {
		return (String) driver.placeArmyDialog(countries, name+" Place your army");
	}

	@Override
	public int selectNumberOfDice(int diceToRoll, String pName) {
		return driver.setUpBoxInput(1, diceToRoll, pName+"! Please select number of dice to roll.");
	}

	@Override
	public int moveArmies(int aArmies, int maxArmies, String message) {
		return driver.setUpBoxInput(aArmies, maxArmies, "Select armies to move:");
	}
	/**
	 * returns name of the Strategy
 	 */
	@Override
	public String getNameOfStrategy() {
		return "human";
	}

} 
  