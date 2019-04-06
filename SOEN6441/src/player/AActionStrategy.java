package player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import Model.GameDriver;
import Model.GameTurnDriver;
import Model.NodeOfCountry;


public class AActionStrategy implements StrategyOfPlayer{

	/**
	 * GameDriver instance for benevolent player.
	 */
	private GameDriver driver;
	
	/**
	 * Object of GameTurnDriver class.
	 */
	private GameTurnDriver GameTurnDriver;
	
	public AActionStrategy(GameDriver nDriver) {
		driver = nDriver;
		GameTurnDriver = driver.getGameTurnDriver();
	}
	
	/**
	 * Reinforcement phase of aggressive player that reinforces its strongest countries.
	 * @see risk.model.player.PlayerStrategy#reinforcementPhase(int, java.lang.String[])
	 */
	@Override
	public void reinforcementPhase(int armies, String[] countryList) {
		reinforcement(armies,countryList);
		driver.notifyObservers("Armies moved according to Aggresive Strategy reinforcement");
		driver.changePhase();
		
	}

	/**
	 * Attack phase: aggressive player always attack with it until it cannot attack anymore.
	 * @see risk.model.player.PlayerStrategy#attackPhase(java.util.ArrayList)
	 */
	@Override
	public void attackPhase(ArrayList<String> countryList) {
		ArrayList<NodeOfCountry> countries = new ArrayList<NodeOfCountry>();
		/*get country node for corresponding country name.*/
		for(String c: countryList){
			countries.add(driver.getCountry(c));
		}
		
		/*sort countries according to armies count in descending order.*/
		countries = sortCountries(countries);
		NodeOfCountry aCountry = countries.get(0);
		
		/*randomly select a country to be attacked.*/
		NodeOfCountry dCountry = null;
		Collections.shuffle(aCountry.getNeighbours());
		for (NodeOfCountry neighbour : aCountry.getNeighbours()) {
			if (!neighbour.getOwner().getName().equals(driver.getCurrentPlayer().getName())) {
				dCountry = neighbour;
				break;
			}
		}
		driver.announceAttack(aCountry.getCountryName(), dCountry.getCountryName());
	}

	/**
	 * Fortification phase of aggressive player: maximizes aggregation of forces in one country.
	 * @see risk.model.player.PlayerStrategy#fortificationPhase(java.util.ArrayList)
	 */
	@Override
	public void fortificationPhase(ArrayList<String> countryList) {

		fortify(countryList);
		
//		NodeOfCountry weakest = countries.get(countries.size()-1);
//		int average = (int)(weakest.getArmiesCount() + strongest.getArmiesCount()) / 2;
//		strongest.addArmy(average);
//		weakest.removeArmies(average);
		driver.notifyObservers("Armies moved according to Aggresive Strategy fortification");
		driver.changePhase();

	}

	/**
	 * Distribute armies in startup phase.
	 */
	@Override
	public String placeArmy(String[] strings, String string) {
		return strings[new Random().nextInt(strings.length)];
	}

	/**
	 * Sort countries in descending order as per the armies.
	 * @param countryList list of country nodes to be sorted.
	 * @return sorted list of country nodes.
	 */
	private ArrayList<NodeOfCountry> sortCountries(ArrayList<NodeOfCountry> countryList){
		Collections.sort(countryList, new Comparator<NodeOfCountry>(){
			@Override
			public int compare(NodeOfCountry o1, NodeOfCountry o2) {
				return o2.getArmiesCount() - o1.getArmiesCount();
			}
		});
		return countryList;
	}

	
	public int selectDiceNumber(int diceToRoll, String name) {
		return diceToRoll; //Assuming player chooses maximum number of dice to roll
	}

	@Override
	public int moveArmies(int aArmies, int maxArmies, String message) {
		return new Random().nextInt(maxArmies+1-aArmies) + aArmies;
	}
	

	public void reinforcement(int armies,String [] countryList) {
		ArrayList<NodeOfCountry> countries = new ArrayList<NodeOfCountry>();
		/*get country node for corresponding country name.*/
		for(String c: countryList){
			countries.add(driver.getCountry(c));
		}
		/*sort countries according to armies count in descending order.*/
		countries = sortCountries(countries);
		NodeOfCountry strongest = countries.get(0);
		driver.getCurrentPlayer().shiftArmiesOnReinforcement(strongest.getCountryName(), armies);
	}
	
	public void fortify(ArrayList<String> countryList) {
		if(countryList.size()>1) {
			ArrayList<NodeOfCountry> countries = new ArrayList<NodeOfCountry>();
			/*get country node for corresponding country name.*/
			for(String c: countryList){
				countries.add(driver.getCountry(c));
			}
			
			/*sort countries according to armies count in descending order.*/
			countries = sortCountries(countries);
			
			NodeOfCountry strongest = countries.get(0);
			NodeOfCountry c = countries.get(1);
			/*fortify the strongest country.*/
			if(c.getArmiesCount()>1) {
				int mArmies = c.getArmiesCount()-1;
				driver.getCurrentPlayer().getArmiesShiftedAfterFortification(c.getCountryName(), strongest.getCountryName(), mArmies);
			}
		}
	}

	@Override
	public String getStrategyName() {
		return "aggressive";
	}


}