package player;

import java.util.ArrayList;

import Model.GameDriver;

public class HActionStrategy implements StrategyOfPlayer{

	public void FPhase(ArrayList<String> Country) {
	GameDriver.GetInit().GetControle().fortificationControls(Country.toArray(new String[Country.size()]));
	GameDriver.GetInit().SetFLiteners();
	}

	public void APhase(ArrayList<String> Countries) {
		GameDriver.GetInit().GetControle().resetControls(Countries.toArray(new String[Countries.size()]));
		GameDriver.GetInit().setAttackListeners();
	}

	public void RPhase(int armies, String[] clist) {
		GameDriver.GetInit().GetControle().reinforcementConrols(armies, clist);
		GameDriver.GetInit().SetActionListeners();

	}


	
<<<<<<< HEAD
=======
	/**
	 * instance of GameDriver
	 */
	private GameDriver driver;
	
	/**
	 * sets GameDriver instance
	 * @param newDriver game driver object
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
	 * @param countries countries list
	 * @param name name of players
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
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441

} 
  