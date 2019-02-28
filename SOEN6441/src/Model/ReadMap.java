package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

/**
 * This class is used to read the map file
 * @author YashNarra
 * version 1.0
 */
public class ReadMap{
	
	/**
	 * Method to read map.
	 */
	public ArrayList<NodeOfMap> mapreader(String filename) throws IOException{
		
		FileReader F=new FileReader(filename);
		BufferedReader B=new BufferedReader(F);
		ArrayList<NodeOfMap> maplist=new ArrayList<NodeOfMap>();
		ArrayList<NodeOfCountry> countrylist=new ArrayList<NodeOfCountry>();
		String s =B.readLine();
		
		while(s!=null) {
		if(s.contains("Continents")) {
			NodeOfMap m=new NodeOfMap(s.substring(0,s.indexOf("=")),null,Integer.parseInt(s.substring(s.indexOf("=")+1).trim()));
			maplist.add(m);
		}
		
		
		else if(s.contains("Territories")) { 
			String[] a=s.split(",");
			for(NodeOfMap n: maplist) {
				NewCountry(n,a,countrylist);
			}
			
		}
		
		}
		return maplist;		
		
	}
	public void NewCountry(NodeOfMap n, String[] a,ArrayList<NodeOfCountry> countrylist) {
		if(n.getContinent().equals(a[3])) {
			if(!NodeOfCountry.Contains(countrylist,a[0])) {
				countrylist.add(new NodeOfCountry(a[0],null,null));
			}
			NodeOfCountry AddCountry=NodeOfCountry.getCountry(countrylist, a[0]);
			int[] AddCoordinates= {Integer.parseInt(a[1],Integer.parseInt(a[2]))};
			AddCountry.SetCoordinate(AddCoordinates);
			
		}
	}
	
}
