package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

/**
 * This class is used to read the map file
 * @author YashNarra
 * @version 2.0
 */
public class ReadMap{
	
	/**
	 * This method reads the map file.
	 * @see NodeOfMap
	 * @param filename URL of map file.
	 * @return map data in form of <code>ArrayList</code>
	 */
	public ArrayList<NodeOfMap> readingMap(String filename) {	
		BufferedReader br = null;
		FileReader fr = null;
		ArrayList<NodeOfMap> map = new ArrayList<NodeOfMap>();
		ArrayList<NodeOfCountry> stack = new ArrayList<NodeOfCountry>();
		
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
					//check if current line is for continent
					if(c){
						int indexEqualTo = sCurrentLine.indexOf("=");
						map.add(new NodeOfMap(sCurrentLine.substring(0, indexEqualTo), null, Integer.parseInt(sCurrentLine.substring(indexEqualTo+1).trim())));
					}
					else if(t){  //check if current line is for territory
						String[] temp = sCurrentLine.split(",");
						for(NodeOfMap n : map){
							if(n.getNameOfContinent().equals(temp[3])){
								if(!NodeOfCountry.contains(stack, temp[0])){//check if the neighbor country object not already created
									stack.add(new NodeOfCountry(temp[0], null, null,null));
								}
								//get country object from stack and update its neighbours and other information
								NodeOfCountry newCountry = NodeOfCountry.getCountry(stack, temp[0]);
								int[] newCoordinates = {Integer.parseInt(temp[1]),Integer.parseInt(temp[2])};
								newCountry.setCoordinates(newCoordinates);
								if(temp.length>4) {
									for(int i=4;i<temp.length;i++){
										if(!NodeOfCountry.contains(stack, temp[i])){
											stack.add(new NodeOfCountry(temp[i], null, null,null));
										}
										newCountry.addNeighbour(NodeOfCountry.getCountry(stack, temp[i]));
									}
								}
								n.addCountry(newCountry);
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
		return map;		
	}
}
