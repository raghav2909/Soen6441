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
 * @verison 1.0
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
		assertEquals(3,TP.getCountArmies());
		TP.AddCountry(new NodeOfCountry("T", null, null, null));
		TP.AddCountry(new NodeOfCountry("T1", null, null, null));
		TP.AddCountry(new NodeOfCountry("T2", null, null, null));
		TP.AddCountry(new NodeOfCountry(null, null, null, null));
		TP.AddCountry(new NodeOfCountry(null, null, null, null));
		TP.AddCountry(new NodeOfCountry(null, null, null, null));
		TP.AddCountry(new NodeOfCountry(null, null, null, null));
		TP.AddCountry(new NodeOfCountry(null, null, null, null));
		assertEquals(4,TP.getCountArmies());
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

}
