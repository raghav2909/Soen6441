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

}
