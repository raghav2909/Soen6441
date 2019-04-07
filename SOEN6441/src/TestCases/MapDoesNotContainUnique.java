package TestCases;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import Model.Map;
/**
 * this test case is checking that the map does not contain unique countries 
 * @author samansoltani
 *
 */

public class MapDoesNotContainUnique {
	private Map map;
	private String FileName;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testisMapNodesContainUniqueCountries2() throws IOException{
		FileName = ".//SOEN6441//Map_Data//test//MapTest4.map";
		map = new Map (FileName);
		boolean t = map.isNodeOfMapsContainUniqueCountries();
		assertFalse(t);
	}
	

}
