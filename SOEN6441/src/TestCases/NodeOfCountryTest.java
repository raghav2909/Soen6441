package TestCases;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Model.NodeOfCountry;
/**
 * this class is testing the node of country class
 */
public class NodeOfCountryTest {
	
	/**
	 * NodeOfCountry variable.
	 */
	NodeOfCountry in;
	
	/**
	 * ArrayList of type NodeOfCountry.
	 */
	ArrayList<NodeOfCountry> list;
	
	/**
	 * Set up dummy data required for test.
	 */
	@Before
	public void setUp(){
		in = new NodeOfCountry("India", null, null, null);
		list = new ArrayList<NodeOfCountry>();
		list.add(new NodeOfCountry("Canada", null, null, null));
		list.add(in);
		list.add(new NodeOfCountry("Pakistan", null, null, null));
		list.add(new NodeOfCountry("Ireland", null, null, null));
		list.add(new NodeOfCountry("Russia", null, null, null));
	}

	/**
	 * Test the getCountries method for retrieving a NodeOfCountry from a list of NodeOfCountry by the name of country.
	 */
	@Test
	public void testGetCountries() {
		NodeOfCountry actual = NodeOfCountry.getCountry(list, "India");
		assertEquals(in.getNameOfCountry(),actual.getNameOfCountry());
	}

}
