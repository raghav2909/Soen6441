package TestCases;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Model.Map;
import Model.ModelOfMap;
import Model.NodeOfCountry;
import Model.NodeOfMap;
import junit.framework.Assert;
/**
 * this class is testing Map class methods
 * @author samansoltani
 *
 */
public class MapTest {
	
	private ModelOfMap map;

	@Before
	public void setUp() throws Exception {
		map = new ModelOfMap();
	}
	
	/**
	 * test CheckSaveMap method
	 */

	@Test
	public void testCheckSaveMap() {
		String c = "America";
		int v =2;
		int[] coor = {300,300};
		ArrayList<NodeOfCountry> ca =new ArrayList<NodeOfCountry>();
		ArrayList<NodeOfCountry> cn1 =new ArrayList<NodeOfCountry>();
		ArrayList<NodeOfCountry> cn2 =new ArrayList<NodeOfCountry>();
		ArrayList<NodeOfCountry> cn3 =new ArrayList<NodeOfCountry>();
		ArrayList<NodeOfCountry> cn4 =new ArrayList<NodeOfCountry>();
		cn2.add(new NodeOfCountry("Canada",cn1,coor,null));
		cn3.add(new NodeOfCountry("USA",cn1,coor,null));
		cn4.add(new NodeOfCountry("Mexico",cn1,coor,null));
		ca.add(new NodeOfCountry("Mexico",cn2,coor,null));
		ca.add(new NodeOfCountry("Canada",cn3,coor,null));
		ca.add(new NodeOfCountry("USA",cn4,coor,null));
		map.addContinents(c, ca, v);
		boolean a = map.checkOnSaveMap();
		boolean e = true;
		Assert.assertEquals(e, a);
	}

	
	/**
	 * test ContinentConnectedCheck method
	 */
	@Test
	public void testContinentConnectedCheck() {
		String c1 = "A";
		int v1 =2;
		String c2 = "B";
		int v2 =3;
		int [] coor = {300,300};
		ArrayList<NodeOfCountry> ca1 =new ArrayList<NodeOfCountry>();
		ArrayList<NodeOfCountry> ca2 =new ArrayList<NodeOfCountry>();
		ArrayList<NodeOfCountry> cn1 =new ArrayList<NodeOfCountry>();
		ArrayList<NodeOfCountry> cn2 =new ArrayList<NodeOfCountry>();
		ArrayList<NodeOfCountry> cn3 =new ArrayList<NodeOfCountry>();
		ArrayList<NodeOfCountry> cn4 =new ArrayList<NodeOfCountry>();
		ArrayList<NodeOfCountry> cn5 =new ArrayList<NodeOfCountry>();
		cn2.add(new NodeOfCountry("G3",cn1,coor,null));
		cn3.add(new NodeOfCountry("G4",cn1,coor,null));
		cn4.add(new NodeOfCountry("G4",cn1,coor,null));
		cn5.add(new NodeOfCountry("G2",cn1,coor,null));
		ca1.add(new NodeOfCountry("G1",cn2,coor,null));
		ca1.add(new NodeOfCountry("G2",cn3,coor,null));
		ca2.add(new NodeOfCountry("G3",cn4,coor,null));
		ca2.add(new NodeOfCountry("G4",cn5,coor,null));
		map.addContinents(c1, ca1, v1);
		map.addContinents(c2, ca2, v2);
		boolean a = map.checkConnectedContinent();
		boolean e = false;
		Assert.assertEquals(e, a);
		
			
		}
	
	/**
	 * test for add continent method
	 */
	@Test
	public void testAddContinent() {
		String c = "Asia";
		String c1 = "America";
		int v = 2;
		int v1 = 4;
		String C ="";
		ArrayList<NodeOfCountry> ca = new ArrayList<NodeOfCountry>();
		map.addContinents(c, ca, v);
		map.addContinents(c1, ca, v1);
		ArrayList<NodeOfMap> TC = map.getContinents();
		for(NodeOfMap mn : TC) {
			C = mn.getContinentName();
			break;
		}
		Assert.assertEquals(C, "Asia");
	}
	
	
	/**
	 * test CheckCountryExist method
	 */
	@Test
	public void testCheckCountryExist() {
		String c = "Asia";
		int v=2;
		int[] coor = {350,350};
		ArrayList<NodeOfCountry> ca = new ArrayList<NodeOfCountry>();
		ArrayList<NodeOfCountry> cn = new ArrayList<NodeOfCountry>();
		ca.add(new NodeOfCountry("Iran",cn,coor,null));
		map.addContinents(c, ca, v);
		boolean a = map.checkCountryExist("Iran");
		boolean e = true;
		Assert.assertEquals(e, a);
	}
	
	
	
	/**
	 * test CheckContinentExist method
	 */
	@Test
	public void testCheckContinentExist() {
		String c = "Asia";
		int v=2;
		String c1 = "Europe";
		int v2 =4;
		int[] coor = {350,350};
		ArrayList<NodeOfCountry> ca = new ArrayList<NodeOfCountry>();
		map.addContinents(c1, ca, v2);
		map.addContinents(c, ca, v);
		boolean a = map.checkContinentExist(c);
		boolean e = true;
		Assert.assertEquals(e, a);
	}
	
	
	/**
	 * 
	 */
}
