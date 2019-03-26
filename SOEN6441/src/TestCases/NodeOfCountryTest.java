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
	
	private NodeOfCountry c;
	private ArrayList<NodeOfCountry> l;

	@Before
	public void setUp() throws Exception {
		c= new NodeOfCountry("Iran",null,null,null);
		l= new ArrayList<NodeOfCountry>();
		l.add(new NodeOfCountry("America",null,null,null));
		l.add(c);
		l.add(new NodeOfCountry("India",null,null,null));
		l.add(new NodeOfCountry("England",null,null,null));
		l.add(new NodeOfCountry("Sweden",null,null,null));
	}

	
	/**
	 * test getNameOfCountry method
	 */
	@Test
	public void testgetNameOfCountry() {
		NodeOfCountry a = NodeOfCountry.getCountry(l, "Iran");
		assertEquals(c.getNameOfCountry(),a.getNameOfCountry());
	}

}
