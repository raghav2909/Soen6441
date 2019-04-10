package TestCases;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Model.Card;
import Model.NodeOfCountry;
import Model.NodeOfMap;
import player.Player;
/**
 * this class is a testing the correct number of armies
 * @author samansoltani
 * @version 1.0
 */
public class PlayerTest {
	private Player TP;
	
	private NodeOfCountry c1;
	private NodeOfCountry c2;
	private NodeOfCountry c3;
	private NodeOfCountry c4;
	
	private Card C1;
	private Card C2;
	private Card C3;
	private Card C4;
	private Card C5;
	private Card C6;
	private Card C7;
	private Card C8;
	private Card C9;
	@Before
	public void setUp() throws Exception {
		 c1 = new NodeOfCountry("Country1",null,null,null);
		 c2 = new NodeOfCountry("Country2",null,null,null);
		 c3 = new NodeOfCountry("Country3",null,null,null);
		 c4 = new NodeOfCountry("Country4",null,null,null);
		 
		 
		 C1 = new Card("Cavalry","General");
		 C2 = new Card("Infantry","General");
		 C3 = new Card("Artillery","General");
		 C4 = new Card("Artillery","General");
		 C5 = new Card("Infantry","General");
		 C6 = new Card("Cavalry","General");
		 C7 = new Card("Artillery","General");
		 C8 = new Card("Infantry","General");
		 C9 = new Card("Cavalry","General");
		 
		
		ArrayList<NodeOfMap> MD = new ArrayList<NodeOfMap>();
		c1.AddNeighbour(c2);
		c1.AddNeighbour(c3);
		c2.AddNeighbour(c1);
		c2.AddNeighbour(c3);
		c3.AddNeighbour(c1);
		c3.AddNeighbour(c2);
		c4.AddNeighbour(c2);
		c4.AddNeighbour(c3);
		
		ArrayList<NodeOfCountry> c = new ArrayList<NodeOfCountry>();
		c.add(c1);
		c.add(c2);
		c.add(c3);
		c.add(c4);
		
		NodeOfMap C = new NodeOfMap("Continent1",c,6);
		MD.add(C);
		TP = new Player("TP",0,c);

	}
	
	/**
	 * test for count armies
	 */

	@Test
	public void testArmies() {
		assertEquals(3,TP.CalArmy());
		TP.AddCountry(new NodeOfCountry("T", null, null, null));
		TP.AddCountry(new NodeOfCountry("T1", null, null, null));
		TP.AddCountry(new NodeOfCountry("T2", null, null, null));
		TP.AddCountry(new NodeOfCountry("T4", null, null, null));
		TP.AddCountry(new NodeOfCountry(null, null, null, null));
		TP.AddCountry(new NodeOfCountry(null, null, null, null));
		TP.AddCountry(new NodeOfCountry(null, null, null, null));
		TP.AddCountry(new NodeOfCountry(null, null, null, null));
		assertEquals(4,TP.CalArmy());
	}
	
	
	/**
	 * test for shiftArmiesOnR method 
	 */
	@Test
	public void testShiftArmiesOnR() {
		TP.ArmySet(10);
		int l = TP.shiftArmiesOnR("Country1", 4);
		assertEquals(6,l);
		assertEquals(4,c1.getArmyCount());
	}
	
	/**
	 * test for getting empty countries name method
	 */
	@Test
	public void testgetEmptyCountriesName() {
		c1.AddArmy(2);
		c2.AddArmy(5);
		c4.AddArmy(8);
		assertEquals("Country3",TP.getEmptyCountriesName()[0]);
	}
	
	
	
	/**
	 * test HaveICard method
	 */
	@Test
	public void testHaveICard() {
		TP.AddCard(C5);
		assertTrue(TP.HaveICard());
	}

	
	
	/**
	 * test HaveACard method
	 */
	@Test
	public void testHaveACard() {
		TP.AddCard(C3);
		assertTrue(TP.HaveACard());
	}
	
	
	
	/**
	 * test HaveCCard method
	 */
	@Test
	public void testHaveCCard() {
		TP.AddCard(C1);
		assertTrue(TP.HaveCCard());
	}
	
	
	/**
	 * test getArmiesShiftedAfterF method
	 */
	@Test
	public void testgetArmiesShiftedAfterF() {
		c1.SetArmies(5);
		c3.SetArmies(3);
		assertEquals(5,TP.getArmiesShiftedAfterF(c1.getNameOfCountry(), c3.getNameOfCountry(), 2));
	}
	
	
	/**
	 * test HaveDCard method
	 */
	@Test
	public void testHaveDCard() {
		TP.AddCard(C7);
		TP.AddCard(C8);
		TP.AddCard(C9);
		assertTrue(TP.HaveDCard());
	}
	
	
	
	/**
	 * test HaveThreeICards method
	 */
	@Test
	public void testHaveThreeICards() {
		TP.AddCard(C2);
		TP.AddCard(C5);
		TP.AddCard(C8);
		assertTrue(TP.HaveThreeICards());
	}
	
	/**
	 * test HaveThreeACards method
	 */
	@Test
	public void testHaveThreeACards() {
		TP.AddCard(C3);
		TP.AddCard(C4);
		TP.AddCard(C7);
		assertTrue(TP.HaveThreeACards());
	}
	
	/**
	 * test HaveThreeCCards method
	 */
	@Test
	public void testHaveThreeCCards() {
		TP.AddCard(C1);
		TP.AddCard(C6);
		TP.AddCard(C9);
		assertTrue(TP.HaveThreeCCards());
	}
	
	
	/**
	 * test RemoveDCards method
	 */
	@Test
	public void testRemoveDCards() {
		TP.AddCard(C7);
		TP.AddCard(C8);
		TP.AddCard(C9);
		TP.RemoveDCards();
		assertEquals(0,TP.GetCards().size());
	}
	
	/**
	 * test SameTypeCards method
	 */
	@Test
	public void testSameTypeCards() {
		TP.AddCard(C3);
		TP.AddCard(C4);
		TP.AddCard(C7);
		assertTrue(TP.SameTypeCards());
	}
	
	
	/**
	 * test SameThreeCardsRemoved method
	 */
	@Test
	public void testSameThreeCardsRemoved() {
		TP.AddCard(C3);
		TP.AddCard(C4);
		TP.AddCard(C7);
		TP.AddCard(C1);
		TP.SameThreeCardsRemoved();
		assertEquals(1,TP.GetCards().size());
	}
	
}
