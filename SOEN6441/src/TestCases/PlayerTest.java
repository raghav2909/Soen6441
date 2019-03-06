package TestCases;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Model.NodeOfCountry;
import Model.Player;
import Model.NodeOfMap;
/**
 * this class is a testing the correct number of armies
 * @author samansoltani
 * @verison 1.0
 */
public class PlayerTest {
	private Player TP;

	@Before
	public void setUp() throws Exception {
		NodeOfCountry c1 = new NodeOfCountry("Country1",null,null);
		NodeOfCountry c2 = new NodeOfCountry("Country2",null,null);
		NodeOfCountry c3 = new NodeOfCountry("Country3",null,null);
		NodeOfCountry c4 = new NodeOfCountry("Country4",null,null);
		
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
		TP = new Player("TestPlayer",0,MD);
		TP.AddCountry(c1);
		TP.AddCountry(c2);
		TP.AddCountry(c3);
	}

	@Test
	public void testArmies() {
		assertEquals(3,TP.getCountArmies());
	}

}
