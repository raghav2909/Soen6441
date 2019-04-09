package player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import Model.GameDriver;
import Model.GameTurnDriver;
import Model.NodeOfCountry;



public class BActionStrategy implements StrategyOfPlayer{

	
	/**
	 * GameDriver instance for benevolent player.
	 */
	private GameDriver driver = new GameDriver();
	
	private GameTurnDriver turnDriver;
	
	public BActionStrategy(GameDriver nDriver) {
		driver = nDriver;
		turnDriver = driver.getGameTurnDriver();
	}
	
	/**
	 * Reinforcement phase of benevolent player that reinforces its weakest countries.
	 * @see risk.model.player.PlayerStrategy#reinforcementPhase(int, java.lang.String[])
	 */
	@Override
	public void reinforcementPhase(int armies, String[] countryList) {
		reinforcement(armies, countryList);
		driver.nottifyObservers(driver.getGameTurnDriver().getPhase());
		driver.switchPhase();
		turnDriver.playTurn();
	}

	/**
	 * Attack phase: benevolent player never attacks.
	 * @see risk.model.player.PlayerStrategy#attackPhase(java.util.ArrayList)
	 */
	@Override
	public void attackPhase(ArrayList<String> countryList) {
		/*skip attack phase.*/
		driver.nottifyObservers(driver.getGameTurnDriver().getPhase());
		driver.switchPhase();
		turnDriver.playTurn();
	}
	
	/**
	 * Fortification phase of benevolent player: fortifies in order to move armies to weakest country.
	 * @see risk.model.player.PlayerStrategy#fortificationPhase(java.util.ArrayList)
	 */
	@Override
	public void fortificationPhase(ArrayList<String> countryList) {
		fortify(countryList);
		driver.nottifyObservers(driver.getGameTurnDriver().getPhase());
		driver.switchPhase();
		turnDriver.playTurn();
	}

	/**
	 * Distribute armies in startup phase.
	 */
	@Override
	public String armyPlacing(String[] strings, String string) {
		return strings[new Random().nextInt(strings.length)];
	}
	
	/**
	 * sort countries according to armies count.
	 * @param countryList list of country nodes to be sorted.
	 * @return sorted list of country nodes.
	 */
	private ArrayList<NodeOfCountry> sortCountries(ArrayList<NodeOfCountry> countryList){
		Collections.sort(countryList, new Comparator<NodeOfCountry>(){

			@Override
			public int compare(NodeOfCountry o1, NodeOfCountry o2) {
				return o1.getConutOfArmies() - o2.getConutOfArmies();
			}
		});
		return countryList;
	}

	@Override
	public int selectNumberOfDice(int diceToRoll, String pName) {
		
		return diceToRoll;
	}

	@Override
	public int moveArmies(int aArmies, int maxArmies, String message) {
		return new Random().nextInt(maxArmies+1-aArmies) + aArmies;
	}
	
	@Override
	public String getNameOfStrategy() {
		return "benevolent";
	}

	public void reinforcement(int armies, String[] countryList) {
		ArrayList<NodeOfCountry> countries = new ArrayList<NodeOfCountry>();
		/*get country node for corresponding country name.*/
		for(String c: countryList){
			countries.add(driver.getCountry(c));
		}
		
		/*sort countries according to armies count in descending order.*/
		countries = sortCountries(countries);
		
		/*get the list of weak countries.*/
		int countOfWeakCountries = 1;
		ArrayList<NodeOfCountry> weakCountryList = new ArrayList<NodeOfCountry>();
		weakCountryList.add(countries.get(0));
		for(int i= 1; i < countries.size(); i++){
			if(countries.get(i).getConutOfArmies() == countries.get(i-1).getConutOfArmies()){
				weakCountryList.add(countries.get(i));
				countOfWeakCountries++;
			}
			else{
				break;
			}
		}
		Player player = driver.getCurrent();
		
		/*get the integer round-off of the armies to be alloted to each weak country.*/
		int armiesToBeReinforced = (int)(armies/countOfWeakCountries);
		for( NodeOfCountry country: weakCountryList){
			driver.getCurrent().shiftArmiesOnReinforcement(country.getNameOfCountry(), armiesToBeReinforced);
//			country.addArmy(armiesToBeReinforced);
//			player.removeArmies(armiesToBeReinforced);
		}
		
		/*Move the armies remaining into the first weakest country in the list.*/
		int playerArmiesLeft = player.getCountOfArmies();
				
		if(!(playerArmiesLeft == 0)){
			weakCountryList.get(0).addArmy(playerArmiesLeft);
			player.removeArmies(playerArmiesLeft);
		}
	}
	
	public void fortify(ArrayList<String> countryList) {
		ArrayList<NodeOfCountry> countries = new ArrayList<NodeOfCountry>();
		/*get country node for corresponding country name.*/
		for(String c: countryList){
			countries.add(driver.getCountry(c));
		}
		
		/*sort countries according to armies count in descending order.*/
		countries = sortCountries(countries);
		
		/*fortify the weakest country.*/
		NodeOfCountry weakest = countries.get(0);
		NodeOfCountry strongest = countries.get(countries.size()-1);
		int average = (int)(weakest.getConutOfArmies() + strongest.getConutOfArmies()) / 2;
		driver.getCurrent().getArmiesShiftedAfterFortification(strongest.getNameOfCountry(), weakest.getNameOfCountry(), average);
	}



}
