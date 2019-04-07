package TestCases;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Model.Card;
import Model.GameDriver;
import Model.NodeOfCountry;
import Model.NodeOfMap;
import player.Player;

/**
 * this class is a testing the correct number of armies
 * @author samansoltani
 * @verison 1.0
 */
public class PlayerTest {
	
	/**
	 * Player reference.
	 */
	private Player testPlayer;

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
	 * Card Reference
	 */
	private Card card1;
	
	/**
	 * Card Reference
	 */
	private Card card2;
	
	/**
	 * Card Reference
	 */
	private Card card3;
	
	/**
	 * Card Reference
	 */
	private Card card4;
	
	/**
	 * Card Reference
	 */
	private Card card5;
	
	/**
	 * Card Reference
	 */
	private Card card6;
	
	/**
	 * Card Reference
	 */
	private Card card7;
	
	/**
	 * Card Reference
	 */
	private Card card8;
	
	/**
	 * Card Reference
	 */
	private Card card9;
	
	/**
	 * GameDriver class object to access GameDriver class
	 */
	private GameDriver driver;
	
	/**
	 * Called before each test case of this class is executed.
	 * @throws Exception any throwable exception.
	 */
	@Before
	public void setUp() throws Exception {
		driver = new GameDriver();
		country1 = new NodeOfCountry("Country1", null, null, null);
		country2 = new NodeOfCountry("Country2", null, null, null);
		country3 = new NodeOfCountry("Country3", null, null, null);
		country4 = new NodeOfCountry("Country4", null, null, null);
		
		card1 = new Card("Artillery","General");
		card2 = new Card("Infantry","General");
		card3 = new Card("Cavalry","General");
		card4 = new Card("Artillery","General");
		card5 = new Card("Artillery","General");
		card6 = new Card("Cavalry","General");
		card7 = new Card("Cavalry","General");
		card8 = new Card("Infantry","General");
		card9 = new Card("Infantry","General");
		
		ArrayList<NodeOfMap> mapData = new ArrayList<NodeOfMap>();
		country1.addNeighbour(country2);
		country2.addNeighbour(country1);
		country1.addNeighbour(country3);
		country3.addNeighbour(country1);
		country2.addNeighbour(country3);
		country3.addNeighbour(country2);
		country4.addNeighbour(country2);
		country4.addNeighbour(country3);
		
		ArrayList<NodeOfCountry> countries = new ArrayList<NodeOfCountry>();
		countries.add(country1);
		countries.add(country2);
		countries.add(country3);
		countries.add(country4);
		
		NodeOfMap continent = new NodeOfMap("Continent1", countries, 6);
		mapData.add(continent);
		testPlayer = new Player("TestPlayer",0,countries, driver);
	}

	/**
	 * Tests the getArmies function of Player class.
	 * @see Player
	 */
	@Test
	public void testGetArmies() {
		assertEquals(3,testPlayer.getArmies());
		testPlayer.addCountry(new NodeOfCountry("test", null, null, null));
		testPlayer.addCountry(new NodeOfCountry("test2", null, null, null));
		testPlayer.addCountry(new NodeOfCountry("test3", null, null, null));
		testPlayer.addCountry(new NodeOfCountry("test4", null, null, null));
		testPlayer.addCountry(new NodeOfCountry("test4", null, null, null));
		testPlayer.addCountry(new NodeOfCountry("test4", null, null, null));
		testPlayer.addCountry(new NodeOfCountry("test4", null, null, null));
		testPlayer.addCountry(new NodeOfCountry("test4", null, null, null));
		assertEquals(4,testPlayer.getArmies());
	}
	
	/**
	 * Tests the shiftArmiesOnReinforcement function of Game driver.
	 * @see GameDriver
	 */
	@Test
	public void testShiftArmiesOnReinforcement() {
		testPlayer.setArmies(10);
		int left = testPlayer.shiftArmiesOnReinforcement("Country1", 4);
		assertEquals(6, left);
		assertEquals(4, country1.getArmiesCount());
	}
	
	/**
	 *  Test method getCountriesNamesNoArmy()
	 */
	@Test
	public void testGetCountriesNamesNoArmy() {
		country1.addArmy(1);
		country2.addArmy(7);
		country4.addArmy(3);
		assertEquals("Country3",testPlayer.getCountriesNamesNoArmy()[0]);
	}
	
	/**
	 * Testing method getArmiesShiftedAfterFortification
	 */
	@Test
	public void testGetArmiesShiftedAfterFortification() {
		country1.setArmies(3);
		country2.setArmies(1);
		assertEquals(3,testPlayer.getArmiesShiftedAfterFortification(country1.getCountryName(), country2.getCountryName(), 2));
	}
	
	/**
	 * Testing method haveInfantryCard
	 */
	@Test
	public void testHaveInfantryCard() {
		testPlayer.addCard(card2);
		assertTrue(testPlayer.haveInfantryCard());
	}
	
	/**
	 * Testing method haveCavalryCard
	 */
	@Test
	public void testHaveCavalryCard() {
		testPlayer.addCard(card3);
		assertTrue(testPlayer.haveCavalryCard());
	}
	
	/**
	 * Testing method haveArtilleryCard
	 */
	@Test
	public void testHaveArtilleryCard() {
		testPlayer.addCard(card1);
		assertTrue(testPlayer.haveArtilleryCard());
	}
	
	/**
	 * Testing method haveDistinctCards
	 */
	@Test
	public void testHaveDistinctCard() {
		testPlayer.addCard(card1);
		testPlayer.addCard(card2);
		testPlayer.addCard(card3);
		assertTrue(testPlayer.haveDistinctCards());
	}
	
	/**
	 * Testing method haveThreeArtilleryCards
	 */
	@Test
	public void testHaveThreeArtilleryCards() {
		testPlayer.addCard(card1);
		testPlayer.addCard(card4);
		testPlayer.addCard(card5);
		assertTrue(testPlayer.haveThreeArtilleryCards());
	}
	
	/**
	 * Testing method haveThreeCavalryCards
	 */
	@Test
	public void testHaveThreeCavalryCards() {
		testPlayer.addCard(card3);
		testPlayer.addCard(card6);
		testPlayer.addCard(card7);
		assertTrue(testPlayer.haveThreeCavalryCards());
	}
	
	/**
	 * Testing method haveThreeInfantryCards
	 */
	@Test
	public void testHaveThreeInfantryCards() {
		testPlayer.addCard(card2);
		testPlayer.addCard(card8);
		testPlayer.addCard(card9);
		assertTrue(testPlayer.haveThreeInfantryCards());
	}
	
	/**
	 * Testing method haveThreeSameTypeCards
	 */
	@Test
	public void testHaveThreeSameTypeCards() {
		testPlayer.addCard(card2);
		testPlayer.addCard(card8);
		testPlayer.addCard(card9);
		assertTrue(testPlayer.haveThreeSameTypeCards());
	}
	
	/**
	 * Testing method removeDistinctCards
	 */
	@Test
	public void testRemoveDistinctCards() {
		testPlayer.addCard(card1);
		testPlayer.addCard(card2);
		testPlayer.addCard(card3);
		testPlayer.removeDistinctCards();
		assertEquals(0, testPlayer.getCards().size());
	}
	
	/**
	 * Testing method removeSimilarThreeCards
	 */
	@Test
	public void testRemoveSimilarThreeCards() {
		testPlayer.addCard(card1);
		testPlayer.addCard(card4);
		testPlayer.addCard(card5);
		testPlayer.addCard(card2);
		testPlayer.removeSimilarThreeCards();
		assertEquals(1, testPlayer.getCards().size());
	}	
	
}
