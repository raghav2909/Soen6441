package TestCases;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Model.GameDriver;
import Model.NodeOfCountry;
import Model.NodeOfMap;
import player.AActionStrategy;
import player.Player;
import player.RActionStrategy;
import player.StrategyOfPlayer;



public class AStrategyTest {
	
	/**
	 * GameDriver class object to access GameDriver class
	 */
	private GameDriver driver;
	
	/**
	 * NodeOfCountry reference.
	 */
	private NodeOfCountry country1;
	
	/**
	 * NodeOfCountry reference.
	 */
	private NodeOfCountry country2;
	
	/**
	 * NodeOfCountry reference.
	 */
	private NodeOfCountry country3;
	
	/**
	 * NodeOfCountry reference.
	 */
	private NodeOfCountry country4;
	
	/**
	 * NodeOfCountry reference.
	 */
	private NodeOfCountry country5;
	
	/**
	 * Reference for player object.
	 */
	private Player player1;
	
	/**
	 * Reference for Player object.
	 */
	private Player player2;
	
	/**
	 * Reference for NodeOfMap object
	 */
	private ArrayList<NodeOfMap> mapData;

	@Before
	public void setUp() throws Exception {
		driver = new GameDriver();
		
		country1 = new NodeOfCountry("Country1", null, null, null);
		country2 = new NodeOfCountry("Country2", null, null, null);
		country3 = new NodeOfCountry("Country3", null, null, null);
		country4 = new NodeOfCountry("Country4", null, null, null);
		country5 = new NodeOfCountry("Country5", null, null, null);
		country1.addNeighbour(country2);
		country2.addNeighbour(country1);
		country1.addNeighbour(country3);
		country3.addNeighbour(country1);
		country2.addNeighbour(country3);
		country3.addNeighbour(country2);
		country4.addNeighbour(country2);
		country4.addNeighbour(country3);
		country5.addNeighbour(country1);
		country1.addNeighbour(country5);
		ArrayList<NodeOfCountry> countries = new ArrayList<NodeOfCountry>();
		countries.add(country1);
		countries.add(country2);
		countries.add(country3);
		ArrayList<NodeOfCountry> countries1 = new ArrayList<NodeOfCountry>();
		countries1.add(country4);
		countries1.add(country5);
		
		
	}

	@Test
	public void testeinforcement() {
		mapData = new ArrayList<NodeOfMap>();
		ArrayList<NodeOfCountry> countries = new ArrayList<NodeOfCountry>();
		countries.add(country1);
		countries.add(country2);
		countries.add(country3);
		ArrayList<NodeOfCountry> countries1 = new ArrayList<NodeOfCountry>();
		countries1.add(country4);
		countries1.add(country5);
		NodeOfMap continent = new NodeOfMap("Continent", countries, 6);
		mapData.add(continent);
		NodeOfMap continent1 = new NodeOfMap("Continent1", countries1, 6);
		mapData.add(continent1);
		player1 = new Player("Player1", 15, countries, driver);
		player2 = new Player("Player2", 10, countries1, driver);
		player1.setTrue();
		player2.setFalse();
		driver.setListOfPlayer(player1);
		driver.setListOfPlayer(player2);
		driver.setCurrent(player1);
		StrategyOfPlayer aggressive = new AActionStrategy(driver);
		player1.setStrategy(aggressive);
		player2.setStrategy(new RActionStrategy(driver));
		country1.addArmy(4);
		country2.addArmy(6);
		country3.addArmy(3);
		driver.getCurrent().setArmies(driver.getCurrent().getNumberOfArmies());
		((AActionStrategy) aggressive).reinforcement(driver.getArmiesOfPlayer(), driver.getCurrent().getNameOfCountries());
		assertEquals(9, country2.getConutOfArmies());
		
		
	}
	
	@Test
	public void testFortify() {
		mapData = new ArrayList<NodeOfMap>();
		ArrayList<NodeOfCountry> countries = new ArrayList<NodeOfCountry>();
		countries.add(country1);
		countries.add(country2);
		countries.add(country3);
		ArrayList<NodeOfCountry> countries1 = new ArrayList<NodeOfCountry>();
		countries1.add(country4);
		countries1.add(country5);
		NodeOfMap continent = new NodeOfMap("Continent", countries, 6);
		mapData.add(continent);
		NodeOfMap continent1 = new NodeOfMap("Continent1", countries1, 6);
		mapData.add(continent1);
		player1 = new Player("Player1", 15, countries, driver);
		player2 = new Player("Player2", 10, countries1, driver);
		player1.setTrue();
		player2.setFalse();
		driver.setListOfPlayer(player1);
		driver.setListOfPlayer(player2);
		driver.setCurrent(player1);
		StrategyOfPlayer aggressive = new AActionStrategy(driver);
		player1.setStrategy(aggressive);
		player2.setStrategy(new RActionStrategy(driver));
		country1.addArmy(4);
		country2.addArmy(6);
		country3.addArmy(3);
		ArrayList<String> countriesList = new ArrayList<String>();
		for(NodeOfCountry c : driver.getCurrent().getCountries()) {
			if(c.getConutOfArmies()>1) {
				for(NodeOfCountry n: c.getNeighbourCountries()) {
					if(n.getOwner().equals(driver.getCurrent())) {
						countriesList.add(c.getNameOfCountry());
						break;
					}
				}
			}
		}
		
		((AActionStrategy) aggressive).fortify(countriesList);
		assertEquals(9,country2.getConutOfArmies());
	}
	
	@Test
	public void testPlaceArmy() {
		int count = 0;
		String [] countries = new String [5];
		for (int i = 0; i<countries.length;i++) {
			countries[i] = "country" + i;
		}
		StrategyOfPlayer aggressive = new AActionStrategy(driver);
		String value = ((AActionStrategy) aggressive).armyPlacing(countries, "player1");
		for (String country: countries) {
			if (country.equals(value)) {
				count++;
			}
		}
		assertEquals(1, count);
	}

}
