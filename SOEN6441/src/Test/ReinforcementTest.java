package Test;

import static org.junit.Assert.*;

import java.lang.ModuleLayer.Controller;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Model.GameDriver;
import Model.NodeOfCountry;
import Model.NodeOfMap;
import Model.Player;

public class ReinforcementTest {

	ArrayList<String> AON;
	Controller controle;
	NodeOfCountry c1 = new NodeOfCountry("Country1", null, null);
	NodeOfCountry c2 = new NodeOfCountry("Country2", null, null);
	NodeOfCountry c3 = new NodeOfCountry("Country3", null, null);
	NodeOfCountry c4 = new NodeOfCountry("Country4", null, null);
	NodeOfCountry c5 = new NodeOfCountry("Country5", null, null);
	Player p1;
	
	@Before
	public void setPlayerAndMapData(){
		controle = new Controller(GameDriver.getInstance());
		ArrayList<NodeOfMap> MD = new ArrayList<NodeOfMap>();
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
		
		NodeOfMap C = new NodeOfMap("Continent1", c, 6);
		MD.add(C);
		p1 = new Player("Player1", 10, MD);
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
		
		p1.SetTurnTrue();
		p2.SetTurnFalse();
		GameDriver.getInstance().setPlayerList(p1);
		GameDriver.getInstance().setPlayerList(p2);
	}
	
	@Test
	public void testLeftPlayerArmiesOnReinforcement() {
		int left = controle.shiftArmiesOnReinforcement(c1, 4);
		assertEquals(6, left);
	}

}