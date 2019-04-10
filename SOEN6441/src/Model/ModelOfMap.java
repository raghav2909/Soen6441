package Model;
import controllers.the_main_controller;
import player.Player;
import Model.*;
//import risk.model.map.MapNode;
import view.Map_Frame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Hashtable;
import controllers.the_main_controller;

/**
 * contains main logic functionality behind map editor
 * @author Gursharan
 *@version 2.0
 */
public class ModelOfMap {
	/**
	 * reference of NodeOfMap object
	 */
	NodeOfMap MapNode;
	/**
	 * Stores the path of new created map file
	 */
	private String NewFileMap;
	/**
	 * It will store the path of existing map file
	 */
	private String OldFileMap;

	/**
 * Array list containing of all information about map
 */
ArrayList <NodeOfMap> continents = new ArrayList<NodeOfMap>();

/**
 * writing the map contents to map file
 */
WriteMap MapWriter = new WriteMap();
	
	/**
	 * creates hash table to store all the countries of a continent
	 * the country names are stored as keys and their corresponding boolean visited values.
	 */
	Hashtable<String, Boolean> TableOfContinent = new Hashtable<String, Boolean>();
	
	/**
	 * It will  create hash table for storing the data of all countries and the time it has been visited
	 */
	Hashtable<String, Boolean> TableOfCountry = new Hashtable<String,Boolean>();

	/**
	 * The_Main_Controller object
	 */
	the_main_controller tmc= new the_main_controller();
	/**
	 * This method initializes the data with existing continents of the map
	 * @param continents it is the arraylist of NodeOfMap that contains continents
	 */
	public void WritingOldMap(ArrayList<NodeOfMap> continents) {
		this.continents = continents;
	}
	/**
	 * This functions checks for the unique continents
	 * @param c it the continent to be checked
	 * @return true if continent exists
	 */
	public boolean CheckContinentExist(String c) {
		boolean ContinentExist = false;
		for (NodeOfMap n : continents) {
			if (n.getContinent().compareTo(c)!=0) {
				
			}else
			{ContinentExist = true;
			
			}
		}
		return ContinentExist;
	}
	
	public void AddContinents(String c1,ArrayList<NodeOfCountry> CountryArray,int v1) {
		continents.add(new NodeOfMap(c1,CountryArray,v1));
	}
	
	
	
	public ArrayList<NodeOfMap> getContinents(){
		return continents;
	}
	/**
	 * implement map-validation before saving
	 * @return true, if Map is found to be valid
	 */
	public boolean CheckSaveMap() {
		boolean SaveMap = true;
		for (NodeOfMap n: continents) {
			if (n.getCountries().length == 0) {
				SaveMap = false;
			}
			for(NodeOfCountry c : n.getCountries()) {
				if(c.getNeighboursCountries().length ==0) {
					SaveMap = false;
				}
			}
		}
		return SaveMap;
	}
	/**
	 * checks that all the country nodes form a connected graph.
	 * @return true if the map is a connected graph.
	 */
	public boolean connectedMap() {
		for (NodeOfMap nom : continents) {
			for (NodeOfCountry noc : nom.getCountries()) {
				TableOfCountry.put(noc.getNameOfCountry(), false);
			}
		}
		String f = TableOfCountry.keySet().iterator().next();
		/*call to search function which recurs over the neighbor countries*/
		Searching(f);
		if(TableOfCountry.containsValue(false)) {
			return false;
		}else {
			return true;
		}
	}
	/**
	 * To check that every continent is connected in itself
	 * @return true if all continents are connected.
	 */
	public boolean ContinentConnectedCheck()
	{
		for (NodeOfMap nom: continents) {
			TableOfContinent.clear();
			for (NodeOfCountry noc : nom.getCountries()) {
				TableOfContinent.put(noc.getNameOfCountry(), false);
			}
			
			String fc = TableOfContinent.keySet().iterator().next();
			UnconnectedContinentSearch(fc);
			if(TableOfContinent.containsValue(false)) {
				return false;
			}
		}
		return true;
	}
	/**
	 * This function recurs over the countries of each continent to check for connectivity
	 * @param fc receives the country to traverse over the neighbors.
	 */
	
	private void UnconnectedContinentSearch(String fc) {
       TableOfContinent.put(fc, true);
		
		for (NodeOfMap nom : continents) {
			for (NodeOfCountry noc : nom.getCountries()) {
				if(noc.getNameOfCountry().compareTo(fc)==0) {
			        Iterator<NodeOfCountry> i = noc.getNeighbours().listIterator();
			        while (i.hasNext())
			        {
			        	NodeOfCountry n = i.next();
			        	/*if this neighbor belongs to the same continent, then recur*/
			        	if(TableOfContinent.containsKey(n.getNameOfCountry())) {
				            if (TableOfContinent.get(n.getNameOfCountry())==false)
				            	UnconnectedContinentSearch(n.getNameOfCountry());
			        	}
			            
			        }
				}
			}
		}
	
		
	}

/**
 * This function implements search algo on NodeOfMap to check connectivity of graph.
 * @param f receives a country to traverse over its neighbors 
 */

	private void Searching(String f) {
		
		 TableOfCountry.put(f, true);
	        for (NodeOfMap nom : continents) {
				for (NodeOfCountry noc : nom.getCountries()) {
					if(noc.getNameOfCountry().compareTo(f)==0) {
						/* Recur for all the connected neighbor countries*/
				        Iterator<NodeOfCountry> i = noc.getNeighbours().listIterator();
				        while (i.hasNext())
				        {
				            NodeOfCountry n = i.next();
				            if (TableOfCountry.get(n.getNameOfCountry())==false)
				                Searching(n.getNameOfCountry());
				        }
					}
				}
			}
	}
	public void SaveMapFile() throws IOException {
		MapWriter.MapWriter(continents);
		NewFileMap = MapWriter.getMapFilePath();
	}
	
	
	public void SaveToOldFile(String p) throws IOException {
		MapWriter.ExistingMapWriter(continents,p);
		OldFileMap = MapWriter.getMapFilePath();
	}
	
	
	
	public String newFileMap() {
		return NewFileMap;
	}
	
	public String oldFileMap() {
		return OldFileMap;
	}
		public String getOlfFileMap() {
			return OldFileMap;
		}
	
	public String getFinalMap() {
		if (Map_Frame.ActionChoosen().compareTo("new")==0) {
		System.out.println(newFileMap());
			return newFileMap();
			
		}
		else if (Map_Frame.ActionChoosen().compareTo("existing")==0) {
			System.out.println(oldFileMap());
			return oldFileMap();
		}
		return null;
	}
	public boolean CheckCountryExist(String c) {
		boolean ce = false;
		for (NodeOfMap n : continents) {
			for (NodeOfCountry i : n.getCountries()) {
				if(i.getNameOfCountry().compareTo(c)==0) {
					ce = true;
				}
			}
		}
		return ce;
	}
	
	
}
