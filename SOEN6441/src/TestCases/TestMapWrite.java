package TestCases;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Model.NodeOfCountry;
import Model.NodeOfMap;
import Model.WriteMap;
/**
 * this class is testing WriteMap class
 * @author samansoltani
 *
 */
public class TestMapWrite {

	
	private WriteMap map;
	
	@Before
	public void setUp() throws Exception {
		map = new WriteMap();
	}

	@Test
	public void testWriteMap() {
		ArrayList<NodeOfMap> c = new ArrayList<NodeOfMap>();
		String cn = "America";
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
		c.add(new NodeOfMap(cn,ca,v));
		map.writeMap(c);
		String file = map.getMapFilePath();
		Assert.assertTrue(file!=null);
	}

}
