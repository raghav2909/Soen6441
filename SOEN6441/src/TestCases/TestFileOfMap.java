package TestCases;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import Model.ReadMap;
import Model.NodeOfMap;
import Model.NodeOfCountry;
/**
 * this class is a test class for testing a file map class
 * @author samansoltani
 */
public class TestFileOfMap {
	private ReadMap RM;

	@Before
	public void setUp() throws Exception {
		RM = new ReadMap();
	}

	@Test
	public void testReadingMap() throws IOException {
		ArrayList <NodeOfMap> result = RM.mapRead(".//SOEN6441//Map_Data//test//MapTest3.map");
		String Eresult = "AmericaN,5,Alaska,Canada,Canada,Alaska,Montana,Groenlandia,Montana,Montana,Canada,Groenlandia,";
		String Aresult ="";
		for (NodeOfMap m: result) {
			Aresult += (m.getContinentName()+","+m.getControlValue()+",");
			for (NodeOfCountry n : m.getCountries()) {
				Aresult += (n.getCountryName()+",");
				for (String s: n.getNeighbourCountriesString()) {
					Aresult += (s+",");
				}
			}
		}
		assertEquals(Eresult,Aresult);
	}

}
