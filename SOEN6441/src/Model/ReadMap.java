package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

/**
 * This class is used to read the map file
 * @author YashNarra
 * @version 1.0
 */
public class ReadMap{
	
	/**
	 * Method to read map.
	 * Takes Filename as input
	 * @param filename 
	 * @return maplist
	 */
	public ArrayList<NodeOfMap> mapreader(String filename) throws IOException{
		
		FileReader F=null;;
		BufferedReader B= null;
		ArrayList<NodeOfMap> maplist=new ArrayList<NodeOfMap>();
		ArrayList<NodeOfCountry> countrylist=new ArrayList<NodeOfCountry>();
			String s;
			/**
			 * Reads filename
			 */
			
			F=new FileReader(filename);
			/**
			 * Reads data from file
			 */
			 B=new BufferedReader(F);
			 boolean Territories = false;
			 boolean Continents= false;
			
			while ((s =B.readLine()) != null){
				if(!s.equals("")){
					if(s.contains("[Territories]")){
						Territories= true;
						Continents = false;
						continue;
					}
					if(s.contains("[Continents]")){
						Continents= true;
						Territories= false;
						continue;
					}
					
					/**
					 * Adds continents to the arraylist maplist.
					 */
					if(Continents){
						maplist.add(new NodeOfMap(s.substring(0, s.indexOf("=")), null, Integer.parseInt(s.substring(s.indexOf("=")+1).trim())));
					}
					
					/**
					 * Adds new countries and set coordinates
					 */
					else if(Territories){
						String[] temp = s.split(",");
						for(NodeOfMap n : maplist){
							if(n.getContinent().equals(temp[3])){
								if(!NodeOfCountry.Contains(countrylist, temp[0])){
									countrylist.add(new NodeOfCountry(temp[0], null, null,null));
								}
								
								NodeOfCountry AddCountry= NodeOfCountry.getCountry(countrylist, temp[0]);
								int[] newCoordinates = {Integer.parseInt(temp[1]),Integer.parseInt(temp[2])};
								AddCountry.SetCoordinate(newCoordinates);
								if(temp.length>4) {
									for(int i=4;i<temp.length;i++){
										if(!NodeOfCountry.Contains(countrylist, temp[i])){
											countrylist.add(new NodeOfCountry(temp[i], null, null,null));
										}
										AddCountry.AddNeighbour(NodeOfCountry.getCountry(countrylist, temp[i]));
									}
								}
								n.addcountry(AddCountry);
								break;
							}
						}
					}
				}
			}
	B.close();
	F.close();
	
		return maplist;		
		
	}
}

	

