package Test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import Model.Map;
/**
 * this test class is testing the validition of a map 
 * @author samansoltani
 *
 */
public class TestOfMapValidition {

	private Map map;
	private String FileName;
	
	
	@Before
	public void setUp() throws Exception {
		
	}
	
	
	/**
	 * this test case is checking that the map contains unique countries
	 * @throws IOException
	 */
	@Test
	public void testisMapNodesContainUniqueCountries() throws IOException {
		FileName = ".//SOEN6441//Map_Data//test//testMap.map";
		map = new Map(FileName);
		boolean t = map.isMapNodesContainUniqueCountries();
		assertTrue(t);
	}
	
	
	
	/**
	 * this test case is checking that the map does not contain unique countries 
	 * @throws
	 */
	@Test
	public void testisMapNodesContainUniqueCountries2() throws IOException{
		FileName = ".//SOEN6441//Map_Data//test//testMap4.map";
		map = new Map (FileName);
		boolean t = map.isMapNodesContainUniqueCountries();
		assertFalse(t);
	}
	
	
	/**
	 * this test case is checking that the map is a connected graph.
	 * @throws
	 */
	@Test
	public void testisMapConnectedGragh() throws IOException{
		FileName = ".//SOEN6441//Map_Data//test//testMap.map";
		map = new Map(FileName);
		assertTrue(map.isMapConnectedGragh());
	}
	
	
	/**
	 * this test case is checking the map is valid or not
	 * @throws
	 */
	@Test
	public void testMapValid() throws IOException{
		FileName = ".//SOEN6441//Map_Data//test//testMap2.map";
		map = new Map(FileName);
		assertFalse(map.MapValid());
	}

}
