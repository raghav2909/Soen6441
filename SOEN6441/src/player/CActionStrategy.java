package player;

import java.util.ArrayList;
import java.util.Random;

import Model.GameDriver;
import Model.NodeOfCountry;

/**
 * This class handles the cheater strategy of a player.
 */
public class CActionStrategy implements StrategyOfPlayer{


	/**
	 * GameDriver instance for Cheater player.
	 */
	private GameDriver driver;
	
	public CActionStrategy(GameDriver nDriver) {
		driver = nDriver;
	}
	
	/**
	 * Reinforcement phase of cheater player that doubles the number of armies on all its countries
	 * @see risk.model.player.PlayerStrategy#reinforcementPhase(int, java.lang.String[])
	 */
	@Override
	public void reinforcementPhase(int armies, String[] countryList) {
		reinforcement(countryList);
		driver.nottifyObservers("Cheater player has doubled the armies of all its countries in Reinforcement phase");
		driver.switchPhase();

	}

	/**
	 * Attack phase: cheater player automatically conquers all the neighbors of all its countries.
	 * @see risk.model.player.PlayerStrategy#attackPhase(java.util.ArrayList)
	 */
	@Override
	public void attackPhase(ArrayList<String> countryList) {
		for (String country : countryList) {
			for (NodeOfCountry neighbour : driver.getCountry(country).getNeighbours()) {
				Player defender = neighbour.getOwner();
				neighbour.setOwner(driver.getCurrent());
				driver.nottifyObservers("Country "+neighbour.getNameOfCountry()+" won by player "+driver.getCurrent());
				driver.setPlayerOut(defender);
			}
		}
		if(driver.checkStateOfGame()) {
			driver.callGameOver(driver.getCurrent().getPlayerName());
		}
	}

	/**
	 * Fortification phase of cheater player: doubles the number of armies on 
	 * its countries that have neighbors that belong to other players.  
	 * @see risk.model.player.PlayerStrategy#fortificationPhase(java.util.ArrayList)
	 */
	@Override
	public void fortificationPhase(ArrayList<String> countryList) {
		fortify(countryList);
		driver.nottifyObservers("Cheater player has doubled the armies of its countries with diffrent owner of neighbouring countries");
		driver.switchPhase();
	}

	/**
	 * Distribute armies in startup phase.
	 */
	@Override
	public String armyPlacing(String[] strings, String string) {
		return strings[new Random().nextInt(strings.length)];
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
		return "cheater";
	}

	public void reinforcement(String[] countryList) {
		for (String country : countryList) {
			driver.getCountry(country).addArmy(driver.getCountry(country).getConutOfArmies());
		}
		driver.getCurrent().setArmies(0);
	}
	
	public void fortify(ArrayList<String> countryList) {
		for (String country : countryList) {
			for (NodeOfCountry neighbour : driver.getCountry(country).getNeighbours()) {
				if (!neighbour.getOwner().equals(driver.getCurrent())) {
					NodeOfCountry pCountry = driver.getCountry(country);
					pCountry.addArmy(pCountry.getConutOfArmies());
				}
			}
		}
	}



}
