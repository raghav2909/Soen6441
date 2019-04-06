package player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import Model.GameDriver;
import Model.GameTurnDriver;
import Model.NodeOfCountry;



public class RActionStrategy implements StrategyOfPlayer {

	
	/**
	 * GameDriver instance for Cheater player.
	 */
	private GameDriver driver;
	
	/**
	 * Object of GameTurnDriver class.
	 */
	private GameTurnDriver turnManager;
	
	/**
	 * Count the number of attacks.
	 */
	private int countAttacks = 0;
	
	private int randomAttacknumber = 0;
	
	public RActionStrategy(GameDriver nDriver) {
		driver = nDriver;
		turnManager = driver.getGameTurnDriver();
	}

	/**
	 * Reinforcement phase of random player that reinforces random a random country.
	 * @see risk.model.player.PlayerStrategy#reinforcementPhase(int, java.lang.String[])
	 */
	@Override
	public void reinforcementPhase(int armies, String[] countryList) {
		countAttacks = 0;
		reinforcement(armies, countryList);
		driver.nottifyObservers("Random Player has reinforced a random country");
		driver.changePhase();
	}

	/**
	 * Attack phase: random player  attacks a random number of times a random country.
	 * @see risk.model.player.PlayerStrategy#attackPhase(java.util.ArrayList)
	 */
	@Override
	public void attackPhase(ArrayList<String> countryList) {
		NodeOfCountry randomCountry = driver.getCountry(countryList.get(new Random().nextInt(countryList.size())));
		if(randomCountry.getArmiesCount() > 1 && randomAttacknumber > countAttacks){
			countAttacks++;
			NodeOfCountry aCountry = randomCountry;
			
			
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
		else{
			driver.nottifyObservers(driver.getGameTurnDriver().getPhase());
			driver.changePhase();
		}

	}

	/**
	 * Fortification phase of random player: fortifies a random country  
	 * @see risk.model.player.PlayerStrategy#fortificationPhase(java.util.ArrayList)
	 */
	@Override
	public void fortificationPhase(ArrayList<String> countryList) {
		fortify(countryList);
		driver.nottifyObservers("Random player has fortified a random country");
		driver.changePhase();
	}

	/**
	 * Distribute armies in startup phase.
	 */
	@Override
	public String placeArmy(String[] strings, String string) {
		return strings[new Random().nextInt(strings.length)];
	}

	@Override
	public int selectDiceNumber(int diceToRoll, String pName) {
		return diceToRoll;
	}

	@Override
	public int moveArmies(int aArmies, int maxArmies, String message) {
		return new Random().nextInt(maxArmies+1-aArmies) + aArmies;
	}

	@Override
	public String getStrategyName() {
		return "random";
	}
	
	public void reinforcement(int armies, String[] countryList) {
		randomAttacknumber = new Random().nextInt(6);
		String country = countryList[new Random().nextInt(countryList.length)];
		driver.getCurrentPlayer().shiftArmiesOnReinforcement(country, armies);
	}
	
	public void fortify(ArrayList<String> countryList) {
		ArrayList<NodeOfCountry> countries = new ArrayList<NodeOfCountry>() ;
		for (String countryName : countryList) {
			if (driver.getCountry(countryName).getArmiesCount() > 1) {
				countries.add(driver.getCountry(countryName));
			}
		}
		NodeOfCountry country = countries.get(new Random ().nextInt(countries.size()));
		int armies = new Random().nextInt(country.getArmiesCount()+1)-1;
		if (armies == 0) {
			armies = 1;
		}
		NodeOfCountry neighbour = country.getNeighbours().get(new Random().nextInt(country.getNeighbours().size()));
		driver.getCurrentPlayer().getArmiesShiftedAfterFortification(country.getCountryName(), neighbour.getCountryName(), armies);
	}



}