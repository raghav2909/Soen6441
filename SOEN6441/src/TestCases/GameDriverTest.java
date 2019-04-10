package TestCases;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Model.GameDriver;
import Model.NodeOfCountry;
import player.Player;
import Model.NodeOfMap;
/**
 * this test class is testing game driver methods
 * @author samansoltani
 */
public class GameDriverTest {
	
	private GameDriver gd;
	private NodeOfCountry c1;
	private NodeOfCountry c2;
	private NodeOfCountry c3;
	private NodeOfCountry c4;
	private NodeOfCountry c5;
	private Player p1;
	private Player p2;
	private ArrayList<NodeOfMap> mp;
	@Before
	public void setUp() throws Exception {
		gd = GameDriver.GetInit();
		c1 = new NodeOfCountry("c1", null, null, null);
		c2 = new NodeOfCountry("c2", null, null, null);
		c3 = new NodeOfCountry("c3", null, null, null);
		c4 = new NodeOfCountry("c4", null, null, null);
		c5 = new NodeOfCountry("c5", null, null, null);
		c1.AddNeighbour(c2);
		c2.AddNeighbour(c1);
		c1.AddNeighbour(c3);
		c3.AddNeighbour(c1);
		c2.AddNeighbour(c3);
		c3.AddNeighbour(c2);
		c4.AddNeighbour(c2);
		c4.AddNeighbour(c3);
		c5.AddNeighbour(c1);
		c1.AddNeighbour(c5);
	}
	
	
	
/**
 * test fight method
 */
	@Test
	public void testFight() {
		ArrayList<NodeOfCountry> C =new ArrayList<NodeOfCountry>();
		C.add(c1);
		C.add(c2);
		C.add(c3);
		ArrayList<NodeOfCountry> C1 =new ArrayList<NodeOfCountry>();
		C1.add(c4);
		C1.add(c5);
		p1 = new Player("player1",15,C);
		p2 = new Player("player2",10,C1);
		c1.AddArmy(1);
		c2.AddArmy(1);
		c3.AddArmy(1);
		c4.AddArmy(1);
		c5.AddArmy(1);
		p1.SetTurnTrue();
		p2.SetTurnFalse();
		gd.ListOfPlayers(p1);
		gd.ListOfPlayers(p2);
		gd.setCurrentPlayer(p1);
		ArrayList<Integer> AttackResults = new ArrayList<Integer>();
		AttackResults.add(4);
		AttackResults.add(2);
		AttackResults.add(6);
		ArrayList<Integer> DefendResults = new ArrayList<Integer>();
		DefendResults.add(4);
		DefendResults.add(2);
		c1.AddArmy(3);
		c4.AddArmy(1);
		gd.Fight(c4, p2, c1, 3, 2, AttackResults, DefendResults);
		assertEquals(0,c4.getArmyCount());
	}
	
	
<<<<<<< HEAD
=======
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
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441
	
	/**
	 * test for AllocatingCountries method
	 */
	@Test
	public void testAllocatingCountries() {
		ArrayList<NodeOfCountry> C = new ArrayList<NodeOfCountry>();
		C.add(c1);
		C.add(c2);
		C.add(c3);
		C.add(c4);
		NodeOfMap map = new NodeOfMap("America",C,2);
		mp = new ArrayList<NodeOfMap>();
		mp.add(map);
		String[] pd = {"Player5","Player3"};
		gd.AllocatingCountries(pd, mp);
		assertEquals(2,gd.GetCurrent().getPlayerCountryNumber());
	}
	
	
	
	
	/**
	 * test for Maximum method
	 */
	@Test
	public void testMaximum() {
		ArrayList<NodeOfCountry> C = new ArrayList<NodeOfCountry>();
		C.add(c1);
		C.add(c2);
		C.add(c3);
		ArrayList<NodeOfCountry> C1 = new ArrayList<NodeOfCountry>();
		C1.add(c4);
		C1.add(c5);
		p1 = new Player("Player1",15,C);
		p2 = new Player("Player2",10,C1);
		c1.AddArmy(1);
		c2.AddArmy(1);
		c3.AddArmy(1);
		c4.AddArmy(1);
		c5.AddArmy(1);
		p1.SetTurnTrue();
		p2.SetTurnFalse();
		gd.ListOfPlayers(p1);
		gd.ListOfPlayers(p2);
		gd.setCurrentPlayer(p1);
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(4);
		result.add(2);
		result.add(3);
		result.add(6);
		assertEquals(3,gd.Maximum(result));
	}

}
