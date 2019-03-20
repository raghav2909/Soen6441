package TestCases;

import static org.junit.Assert.*;
/**
 * this test class is testing fortification phase
 * @author samansoltani
 */

import java.util.ArrayList;
import view.ControlsConsole;

import org.junit.Before;
import org.junit.Test;


import Model.NodeOfCountry;
import Model.NodeOfCountry;
import player.Player;

public class FortificationTest {
	ArrayList<String> RealNeighboursOfOwner;
	ControlsConsole controller = new ControlsConsole();
	NodeOfCountry c1 = new NodeOfCountry("Country1", null, null);
	NodeOfCountry c2 = new NodeOfCountry("Country2", null, null);
	NodeOfCountry c3 = new NodeOfCountry("Country3", null, null);
	NodeOfCountry c4 = new NodeOfCountry("Country4", null, null);
	NodeOfCountry c5 = new NodeOfCountry("Country5", null, null);

	@Before
	public void setUp() throws Exception {
		ArrayList<NodeOfCountry> MD = new ArrayList<NodeOfCountry>();
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
		
		ArrayList<NodeOfCountry> c = new ArrayList<NodeOfCountry>();
		c.add(c1);
		c.add(c2);
		c.add(c3);
		c.add(c4);
		c.add(c5);
		
		NodeOfCountry C = new NodeOfCountry("Continent1", c, 6);
		MD.add(C);
		Player p1 = new Player("Player", 10, MD);
		Player p2 = new Player("Player2", 10, MD);
		
		p1.AddCountry(c1);
		p1.AddCountry(c2);
		p1.AddCountry(c3);
		p2.AddCountry(c4);
		p2.AddCountry(c5);
		
		c1.SetOwner(p1);
		c2.SetOwner(p1);
		c3.SetOwner(p1);
		c4.SetOwner(p2);
		c5.SetOwner(p2);
		
		c1.AddArmy(1);
		c2.AddArmy(1);
		c3.AddArmy(1);
		c4.AddArmy(1);
		c5.AddArmy(1);
	}

	@Test
	public void testListOfNeighbours() {
		RealNeighboursOfOwner = controller.getNeighbors(c1);
		ArrayList<String> EOwnerNeighbours = new ArrayList<String>();
		EOwnerNeighbours.add("Country2");
		EOwnerNeighbours.add("Country3");
		assertEquals(EOwnerNeighbours, RealNeighboursOfOwner);
	}

}
