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
		System.out.println(" sdadas"+filename);
		FileReader fr=new FileReader(filename);
		BufferedReader br=new BufferedReader(fr);
		ArrayList<NodeOfMap> maplist=new ArrayList<NodeOfMap>();
		ArrayList<NodeOfCountry> countrylist=new ArrayList<NodeOfCountry>();
		try {
			String sCurrentLine;
			fr = new FileReader(filename);
			br = new BufferedReader(fr);
			boolean t = false;
			boolean c = false;
			while ((sCurrentLine = br.readLine()) != null){
				if(!sCurrentLine.equals("")){
					if(sCurrentLine.contains("[Territories]")){
						t = true;
						c = false;
						continue;
					}
					if(sCurrentLine.contains("[Continents]")){
						c = true;
						t = false;
						continue;
					}
					if(c){
						int indexEqualTo = sCurrentLine.indexOf("=");
						maplist.add(new NodeOfMap(sCurrentLine.substring(0, indexEqualTo), null, Integer.parseInt(sCurrentLine.substring(indexEqualTo+1).trim())));
					}else if(t){
						String[] temp = sCurrentLine.split(",");
						for(NodeOfMap n : maplist){
							if(n.getContinent().equals(temp[3])){
								if(!NodeOfCountry.Contains(countrylist, temp[0])){
									countrylist.add(new NodeOfCountry(temp[0], null, null));
								}
								
								NodeOfCountry newCountry = NodeOfCountry.getCountry(countrylist, temp[0]);
								int[] newCoordinates = {Integer.parseInt(temp[1]),Integer.parseInt(temp[2])};
								newCountry.SetCoordinate(newCoordinates);
								if(temp.length>4) {
									for(int i=4;i<temp.length;i++){
										if(!NodeOfCountry.Contains(countrylist, temp[i])){
											countrylist.add(new NodeOfCountry(temp[i], null, null));
										}
										newCountry.AddNeighbour(NodeOfCountry.getCountry(countrylist, temp[i]));
									}
								}
								n.addcountry(newCountry);
								break;
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return maplist;		
		
	}

	
}
