package TestCases;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Model.GameDriver;
import Model.GameDriverParameter;
import Model.NodeOfCountry;
import player.Player;
import Model.NodeOfMap;
/**
 * this test class is testing game driver methods
 * @author samansoltani
 */
public class GameDriverTest {
	
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
	
	/**
	 * This function is called before each test case is run.
	 */
	@Before
	public void setPlayerAndMapData(){
		driver = new GameDriver(".//SOEN6441//Map_Data//test//MapTest3.map", 0);
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
	}
	
	/**
	 * This method tests the battle method from GameDriver class.
	 */
	@Test
	public void testBattle() {
		ArrayList<NodeOfCountry> countries = new ArrayList<NodeOfCountry>();
		countries.add(country1);
		countries.add(country2);
		countries.add(country3);
		ArrayList<NodeOfCountry> countries1 = new ArrayList<NodeOfCountry>();
		countries1.add(country4);
		countries1.add(country5);
		player1 = new Player("Player1", 15, countries, driver);
		player2 = new Player("Player2", 10, countries1, driver);
		country1.addArmy(1);
		country2.addArmy(1);
		country3.addArmy(1);
		country4.addArmy(1);
		country5.addArmy(1);
		player1.setTrue();
		player2.setFalse();
		driver.setListOfPlayer(player1);
		driver.setListOfPlayer(player2);
		driver.setCurrent(player1);
		ArrayList<Integer> aResults = new ArrayList<Integer>();
		aResults.add(4);
		aResults.add(2);
		aResults.add(6);
		ArrayList<Integer> dResults = new ArrayList<Integer>();
		dResults.add(4);
		dResults.add(2);
		country1.addArmy(3);
		country4.addArmy(1);
		driver.battle(country4, player2, country1, 3, 2, aResults, dResults);
		assertEquals(0, country4.getConutOfArmies());
	}
	
	
	@Test
	public void testBattle2() {
		ArrayList<NodeOfCountry> countries = new ArrayList<NodeOfCountry>();
		countries.add(country3);
		countries.add(country4);
		countries.add(country5);
		ArrayList<NodeOfCountry> countries1 = new ArrayList<NodeOfCountry>();
		countries1.add(country1);
		countries1.add(country2);
		player1 = new Player("Player1", 20, countries, driver);
		player2 = new Player("Player2", 15, countries1, driver);
		country1.addArmy(1);
		country2.addArmy(1);
		country3.addArmy(1);
		country4.addArmy(1);
		country5.addArmy(1);
		player1.setTrue();
		player2.setFalse();
		driver.setListOfPlayer(player1);
		driver.setListOfPlayer(player2);
		driver.setCurrent(player1);
		ArrayList<Integer> aResults = new ArrayList<Integer>();
		aResults.add(5);
		aResults.add(3);
		aResults.add(6);
		ArrayList<Integer> dResults = new ArrayList<Integer>();
		dResults.add(6);
		dResults.add(6);
		country3.addArmy(3);
		country1.addArmy(1);
		driver.battle(country1, player2, country3, 3, 2, aResults, dResults);
		assertEquals(2, country1.getConutOfArmies());
	}
	
	/**
	 * This method tests the max method from GameDriver class.
	 */
	@Test
	public void testMax() {
		ArrayList<NodeOfCountry> countries = new ArrayList<NodeOfCountry>();
		countries.add(country1);
		countries.add(country2);
		countries.add(country3);
		ArrayList<NodeOfCountry> countries1 = new ArrayList<NodeOfCountry>();
		countries1.add(country4);
		countries1.add(country5);
		player1 = new Player("Player1", 15, countries, driver);
		player2 = new Player("Player2", 10, countries1, driver);
		country1.addArmy(1);
		country2.addArmy(1);
		country3.addArmy(1);
		country4.addArmy(1);
		country5.addArmy(1);
		player1.setTrue();
		player2.setFalse();
		driver.setListOfPlayer(player1);
		driver.setListOfPlayer(player2);
		driver.setCurrent(player1);
		ArrayList<Integer> aResults = new ArrayList<Integer>();
		aResults.add(4);
		aResults.add(2);
		aResults.add(3);
		aResults.add(6);
		assertEquals(3,driver.max(aResults));
	}
	
	/**
	 * This method tests the dividingCountries()
	 */
	@Test
	public void testDividingCountries() {
		ArrayList<NodeOfCountry> countries = new ArrayList<NodeOfCountry>();
		countries.add(country1);
		countries.add(country2);
		countries.add(country3);
		countries.add(country4);
		NodeOfMap m = new NodeOfMap("Asia", countries, 2);
		mapData = new ArrayList<NodeOfMap>();
		mapData.add(m);
		String[][] playerData = {{"Player3","human"},{"Player4","human"}};
//		driver.getMap().setMapData(mapData);
		driver.playerCreation(playerData);
		driver.allocatingCountries(mapData);
		assertEquals(2,driver.getCurrent().getCountPlayerCountries());
	}
}
