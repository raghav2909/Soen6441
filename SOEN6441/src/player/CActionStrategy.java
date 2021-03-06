package player;

import java.util.ArrayList;
import java.util.Random;

import Model.GameDriver;
import Model.GameTurnDriver;
import Model.NodeOfCountry;

/**
 * This class handles the cheater strategy of a player.
 */
public class CActionStrategy implements StrategyOfPlayer{


	/**
	 * GameDriver instance for Cheater player.
	 */
	private GameDriver driver;
	
	private GameTurnDriver turnDriver;
	
	public CActionStrategy(GameDriver nDriver) {
		driver = nDriver;
		turnDriver = driver.getGameTurnDriver();
	}
	
	/**
	 * Reinforcement phase of cheater player that doubles the number of armies on all its countries
	 */
	@Override
	public void reinforcementPhase(int armies, String[] countryList) {
		reinforcement(countryList);
		driver.nottifyObservers("Cheater player has doubled the armies of all its countries in Reinforcement phase");
		driver.switchPhase();
		turnDriver.playTurn();

	}

	/**
	 * Attack phase: cheater player automatically conquers all the neighbors of all its countries.
	 */
	@Override
	
	public void attackPhase(ArrayList<String> countryList) {
		for (String country : countryList) {
			NodeOfCountry aCountry = driver.getCountry(country);		 
			for (NodeOfCountry neighbour : (aCountry).getNeighbours()) {
				if(neighbour.getOwner()!=driver.getCurrent()) {
					if(neighbour.getOwner()==null) {
						neighbour.setOwner(aCountry.getOwner());
					}
					else {
						Player defender = neighbour.getOwner();
						neighbour.setOwner(aCountry.getOwner());
						driver.nottifyObservers("Country "+neighbour.getNameOfCountry()+" won by player "+driver.getCurrent());
						driver.setPlayerOut(defender); 
					}
				}	
				}
			}
		System.out.println(driver.moveCounter);
		if(driver.moveCounter > 10) {
			driver.callGameOver(driver.getCurrent().getPlayerName());
		}else {
			driver.switchPhase();
			turnDriver.playTurn();
		}

		
		//System.exit(0);
	}

	/**
	 * Fortification phase of cheater player: doubles the number of armies on 
	 * its countries that have neighbors that belong to other players.  
	 */
	@Override
	public void fortificationPhase(ArrayList<String> countryList) {
		fortify(countryList);
		driver.nottifyObservers("Cheater player has doubled the armies of its countries with diffrent owner of neighbouring countries");
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
			driver.getCountry(country).addArmy(driver.getCountry(country).getConutOfArmies()*2);
		}
		driver.getCurrent().setArmies(0);
	}
	
	public void fortify(ArrayList<String> countryList) {
		for (String country : countryList) {
			for (NodeOfCountry neighbour : driver.getCountry(country).getNeighbours()) {
				if(neighbour.getOwner() != null) {
					if (!neighbour.getOwner().equals(driver.getCurrent())) {
						NodeOfCountry pCountry = driver.getCountry(country);
						pCountry.addArmy(pCountry.getConutOfArmies()*2);
					}
				}
				
			}
		}
	}



}
