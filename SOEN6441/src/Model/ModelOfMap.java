package Model;
import controllers.TheMainController;
import player.Player;

import Model.*;
//import risk.model.map.NodeOfMap;
import view.FrameForMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Hashtable;
import controllers.TheMainController;

/**
 * contains main logic functionality behind map editor
 * @author Gursharan
 *@version 2.0
 */
public class ModelOfMap {
	
	/**
	 * Creates hashtable for storing all countries as keys and their corresponding boolean visited values.
	 */
	Hashtable<String, Boolean> countryTable = new Hashtable<String, Boolean>();
	
	/**
	 * creates hashtable for a continent to store all its countries.
	 * the country names are stored as keys and their corresponding boolean visited values.
	 */
	Hashtable<String, Boolean> continentTable = new Hashtable<String, Boolean>();
	
	/**
	 * mapNode variable stores reference of the class NodeOfMap
	 */
	NodeOfMap mapNode;
	
	/**
	 * Stores path of newly created map file.
	 */
	private String newFilePath;
	
	/**
	 * Stores path of existing map file.
	 */
	private String existingFilePath;
	
	/**
	 * NodeOfMap arraylist containing all the map information
	 */
	ArrayList<NodeOfMap> continents = new ArrayList<NodeOfMap>();
		
	/**
	 * WriteMap object for writing the map contents to the map file
	 */
	WriteMap mapWriter = new WriteMap();
	
	/**
	 * Initializes the continent list with existing continents in the map file. 
	 * @param continents ArrayList of NodeOfMap containing continents.
	 */
	public void existingMapWriter(ArrayList<NodeOfMap> continents) {
		this.continents = continents;
	}
	
	/**
	 * checks for unique continents.
	 * @param cn receives the continent to be checked for uniqueness.
	 * @return true if the continent already exist.
	 */
	public boolean checkContinentExistence(String cn) {
		Boolean continentExist = false;
		for (NodeOfMap con: continents){
			/*if the continent with same name is found, return true*/
			if(con.getNameOfContinent().compareTo(cn)==0){
				continentExist = true;
			}
		}
		return continentExist;
	}
	
	/**
	 * Function to add a new continent
	 * @param cn1 receives continent name.
	 * @param countryArr arrayList of countries within the continent.
	 * @param cv1 control value of the continent.
	 */
	public void addContinents(String cn1,ArrayList<NodeOfCountry> countryArr,int cv1) {
		continents.add(new NodeOfMap(cn1, countryArr, cv1));
	}
	
	/**
	 * function to get the list of continents.
	 * @return the continent arrayList.
	 */
	public ArrayList<NodeOfMap> getContinents() {
		return continents;
	}
	
	/**
	 * function to implement validations before saving the map file.
	 * @return true if map is valid.
	 */
	public boolean checkSavedMap() {
		Boolean saveMap = true;
		for (NodeOfMap i :continents) {
			//a map file with zero countries cannot be saved.
			if(i.getCountries().length == 0) {
				saveMap = false;
			}
			/*connected map check*/
			if(!connectedMap()) {
				saveMap = false;
			}
		}
		return saveMap;
	}
	
	/**
	 * checks that all the country nodes form a connected graph.
	 * @return true if the map is a connected graph.
	 */
	public boolean connectedMap() {
		for (NodeOfMap mapNode : continents) {
			for (NodeOfCountry cNode : mapNode.getCountries()) {
				countryTable.put(cNode.getNameOfCountry(), false);
			}
		}
		/* Get the first country from the hashtable*/
		String first = countryTable.keySet().iterator().next();
		/*call to search function which recurs over the neighbor countries*/
		search(first);
		if(countryTable.containsValue(false)) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * Checks that each continent in itself is a connected continent.
	 * @return true if all continents are connected continents.
	 */
	public boolean checkConnectedContinent() {
		for (NodeOfMap mapNode : continents) {
			continentTable.clear();
			for (NodeOfCountry cNode : mapNode.getCountries()) {
				continentTable.put(cNode.getNameOfCountry(), false);
			}
			/*Get the first country from the hashtable for the current continent*/
			String firstCountry = continentTable.keySet().iterator().next();
			searchForUnconnectedContinent(firstCountry);
			if(continentTable.containsValue(false)) {
				return false;
			}
		}
		return true;	
	}
	
	/**
	 * This function recurs over the countries of each continent to check their connectivity within the continent.
	 * @param f receives the country to traverse over its neighbors.
	 */
	public void searchForUnconnectedContinent(String f) {
		/* Mark the current node as visited by setting it true*/ 
		continentTable.put(f, true);
		
		for (NodeOfMap mapNode : continents) {
			for (NodeOfCountry cNode : mapNode.getCountries()) {
				if(cNode.getNameOfCountry().compareTo(f)==0) {
					/* get the list of all possible neighbors.*/
			        Iterator<NodeOfCountry> i = cNode.getNeighbours().listIterator();
			        while (i.hasNext())
			        {
			        	/*pick a neighbor for this country*/
			        	NodeOfCountry n = i.next();
			        	/*if this neighbor belongs to the same continent, then recur*/
			        	if(continentTable.containsKey(n.getNameOfCountry())) {
				            if (continentTable.get(n.getNameOfCountry())==false)
				            	searchForUnconnectedContinent(n.getNameOfCountry());
			        	}
			            
			        }
				}
			}
		}
	}
	
	/**
	 * this function implements search algorithm on mapNode to check connectedness of graph.
	 * @param s receives a country to traverse over its neighbors and set its boolean visited value as true.
	 */
	public void search(String s)
        {
        /* Mark the current node as visited by setting it true */
        countryTable.put(s, true);
        for (NodeOfMap mapNode : continents) {
			for (NodeOfCountry cNode : mapNode.getCountries()) {
				if(cNode.getNameOfCountry().compareTo(s)==0) {
					/* Recur for all the connected neighbor countries*/
			        Iterator<NodeOfCountry> i = cNode.getNeighbours().listIterator();
			        while (i.hasNext())
			        {
			            NodeOfCountry n = i.next();
			            try {
			            	if (countryTable.get(n.getNameOfCountry())==false)
			            		search(n.getNameOfCountry());
			            }catch(NullPointerException e) {
			            }
			        }
				}
			}
		}
        
    }
	
	/**
	 * Saving the new Map file.
	 */
	public void saveMapFile() {
		mapWriter.writeMap(continents);
		newFilePath = mapWriter.getMapPath();
	}
	
	/**
	 * Function to save existing map file.
	 * @param path receives the path of the existing map file.
	 */
	public void saveToExistingMapFile(String path) {
		mapWriter.existingMapWriter(continents, path);
		existingFilePath = mapWriter.getMapPath();
	}
	
	/**
	 * Function to get the path of the new map file.
	 * @return path of the new map file.
	 */
	public String newFilePath() {
		return newFilePath;
	}
	
	/**
	 * Function to get the path of the existing map file.
	 * @return the path of the existing map file.
	 */
	public String existingFilePath() {
		return existingFilePath;
	}
	
	/**
	 * function to get the final path of the file saved.
	 * @return the path of the file saved.
	 */
	public String getFinalPath() {
		/*if new map file is created, get its path*/
		if(FrameForMap.selectedAction().compareTo("new")==0) {
			System.out.println(newFilePath());
			return newFilePath(); 
		}
		/*if existing file is edited, get its path*/
		else if(FrameForMap.selectedAction().compareTo("existing")==0){
			System.out.println(existingFilePath());
			return existingFilePath();
		}
		return null;
	}
	
	/**
	 * function to check for unique countries.
	 * @param cn1 receives the country name
	 * @return true if the country is already present.
	 */
	public boolean checkCountryExist(String cn1) {
		Boolean countryExist = false;
		for (NodeOfMap node: continents) {
			for (NodeOfCountry country : node.getCountries()) {
				/*if country with same name is found, return true*/
				if(country.getNameOfCountry().compareTo(cn1)==0) {
					countryExist=true;
				}
			}
		}
		return countryExist;
	}
}