package TestCases;

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
	 * this test case is checking the map is valid or not
	 * @throws IOException it will throw input output exception
	 */
	@Test
	public void testMapValid() throws IOException{
		FileName = ".//SOEN6441//Map_Data//test//MapTest2.map";
		map = new Map(FileName);
		assertFalse(map.MapValid());
	}

}
