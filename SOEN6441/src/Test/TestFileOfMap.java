package Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import Model.ReadMap;
import Model.NodeOfMap;
import Model.NodeOfCountry;
/**
 * this class is a test class for testing a file map
 * @author samansoltani
 */
public class TestFileOfMap {
	private ReadMap RM;

	@Before
	public void setUp() throws Exception {
		RM = new ReadMap();
	}

	@Test
	public void testReadingMap() {
		ArrayList <NodeOfMap> result = RM.mapreader(".//Map_Data//test//testMap3.map");
		String Eresult = "AmericaN,5,Alaska,Canada,Canada,Alaska,Montana,Groenlandia,Montana,Montana,Canada,Groenlandia,";
		String Aresult ="";
		for (NodeOfMap m: result) {
			Aresult += (m.getContinent()+","+m.getControlValue()+",");
			for (NodeOfCountry n : m.getCountries()) {
				Aresult += (n.getNameOfCountry()+",");
				for (String s: n.getNeighboursString()) {
					Aresult += (s+",");
				}
			}
		}
		assertEquals(Eresult,Aresult);
	}

}
