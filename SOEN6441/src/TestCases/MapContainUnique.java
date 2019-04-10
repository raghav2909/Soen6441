package TestCases;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import Model.Map;
/**
 * this test class is testing that map containing unique countries
 * @author samansoltani
 *
 */
public class MapContainUnique {
	private Map map;
	private String FileName;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testisMapNodesContainUniqueCountries() throws IOException {
		FileName = ".//SOEN6441//Map_Data//test//MapTest1.map";
		map = new Map(FileName);
		boolean t = map.isMapNodesContainUniqueCountries();
		assertTrue(t);
	}

}
