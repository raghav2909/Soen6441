package TestCases;

import static org.junit.Assert.*;
/**
 * this test case is checking that the map is a connected graph.
 * @author samansoltani
 */

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import Model.Map;

public class MapConnected {
	private Map map;
	private String FileName;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testisMapConnectedGragh() throws IOException{
		FileName = ".//SOEN6441//testCases//MapTest1.map";
		map = new Map(FileName);
		assertTrue(map.isMapConnectedGragh());
	}

}
