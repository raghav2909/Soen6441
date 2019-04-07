package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Observable;

import controllers.the_main_controller;
import player.Player;
import Model.*;

import view.Map_Frame;

/**
 * This class handles the data for new map creation and also map editor.
 * @author samansoltani
 *@version 2.0
 */
public class Map extends Observable
{
	
	/**
	 * ArrayList containing map data.
	 * @see NodeOfMap
	 */
	private ArrayList<NodeOfMap> mapData;
	
	/**
	 * Number of total countries in the Map
	 */
	private int countryCount;
		
	/**
	 * This constructor create object of ReadMap class and read data from map.
	 * @param filename address of the mapfile to be loaded.
	 */
	public Map(String filename) {
		ReadMap reader = new ReadMap();
		mapData = reader.readMap(filename);
	}
	
	/** 
	 * Returns the arraylist of map data.
	 * @return return map data in form of arraylist.
	 */
	public ArrayList<NodeOfMap> getMapData() {
		return this.mapData;
	}
	
	/**
	 * Return map data.
	 * @return multidimensional array containing map data.
	 */
	public String[][] getMapDataObject() {
		ArrayList<Object[]> newData = new ArrayList<Object[]>();
		for(NodeOfMap m : mapData){
			for(NodeOfCountry n : m.getCountries()){
				String[] tempObject = new String[5];
				tempObject[1] = n.getCountryName();
				if(n.getOwner()!=null){
					tempObject[3] = n.getOwner().getName();
				}else{
					tempObject[3] = "";
				}
				tempObject[2] = String.valueOf(n.getArmiesCount());
				newData.add(tempObject);
				tempObject[0] = Integer.toString(n.getCoordinates()[0]);
				tempObject[4] = Integer.toString(n.getCoordinates()[1]);
			}
		}
		return newData.toArray(new String[newData.size()][]);
	}
	
	/**
	 * Return map data
	 * @return multidimensional array containing map data.
	 */
	public String[][] getMapObject() {
		ArrayList<Object[]> newData = new ArrayList<Object[]>();
		for(NodeOfMap m : mapData){
			for(NodeOfCountry n : m.getCountries()){
				
				String[] tempObject = new String[5];
				tempObject[0] = m.getContinentName() +", "+ m.getControlValue();
				tempObject[1] = n.getCountryName();
				String neighbours = "";
				for(String s: n.getNeighbourCountriesString()){
					neighbours = neighbours + s + ", ";
				}
				
				tempObject[4] = neighbours;
				if(n.getOwner()!=null){
					tempObject[3] = n.getOwner().getName();
				}else{
					tempObject[3] = "";
				}
				tempObject[2] = String.valueOf(n.getArmiesCount());
				newData.add(tempObject);
			}
		}
		return newData.toArray(new String[newData.size()][]);
	}
	
	/**
	 * Print map data on console.
	 */
	public void mapConsolePrint() {
		Object[][] map = this.getMapDataObject();
		for(Object[] m : map){
			System.out.println("Continent Name: "+m[0]);
			System.out.println("\tCountry Name: "+m[1]+", Neighbours: "+m[4]+", Owner: "+m[3]+", Armies: "+m[2]);
		}
	}
	
	/**
	 * Notify Observer(MapView) of the change in Observable
	 */
	public void updateMap(){
		setChanged();
		notifyObservers(this);
	}
	
	/**
	 * This method check if loaded map is valid.
	 * @return true if map is valid false if map is not valid
	 * */
	public boolean mapValidation() {
		if(isMapConnectedGraph() && isNodeOfMapsContainUniqueCountries()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Check if map is connected graph.
	 * @return true if map is connected graph false if map is not connected graph
	 */
	public boolean isMapConnectedGraph()
	{
		for(NodeOfMap m: this.mapData){
			for(NodeOfCountry c: m.getCountries()){
				if(c.getNeighbourCountries()==null || c.getNeighbourCountries().length==0){
					return false;
				}
				for(NodeOfCountry n: c.getNeighbourCountries()){
					if(!n.getNeighbours().contains(c)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Check if every country belongs to only one NodeOfMap.
	 * @return true if all continents have unique countries; false if one country belongs to more than one continent
	 */
	public boolean isNodeOfMapsContainUniqueCountries() {
		ArrayList<NodeOfCountry> stack = new ArrayList<NodeOfCountry>();
		for(NodeOfMap m: this.mapData) {
			for(NodeOfCountry c: m.getCountries()) {
				if(stack.contains(c)) {
					return false;
				}
				else {
					stack.add(c);
				}
			}
		}
		return true;
	}
	
	/**
	 * return Arraylist of neighbouring countries owned by a player or not owned by player, depending on flag value
	 * @param newCountry Country node whose neighbors are required.
	 * @param newPlayer player who owns the required neighbours.
	 * @param flag if true, method returns countries owned by newPlayer, if false return countries owned by other players except newPlayer.
	 * @return playerNeighbouringCountries returns neighbouring countries of the country of same owner
	 */
	public ArrayList<String> getPlayerNeighbourCountries(NodeOfCountry newCountry, Player newPlayer,boolean flag) {
		ArrayList<String> playerNeighbourCountries = new ArrayList<String>();
		for (NodeOfCountry country : newCountry.getNeighbourCountries()){
			if(flag) {
				if (country.getOwner().equals(newPlayer)){
					playerNeighbourCountries.add(country.getCountryName());
				}
			}
			else {
				if(!country.getOwner().equals(newPlayer)) {
					playerNeighbourCountries.add(country.getCountryName());
				}
			}
		}
		return playerNeighbourCountries;
	}
	
	/**
	 * Get object of country from its name
	 * @param countryName name of country
	 * @return object of NodeOfCountry required
	 */
	public NodeOfCountry getCountry(String countryName) {
		NodeOfCountry c = null;
		for(NodeOfMap m: this.mapData) {
			c = NodeOfCountry.getCountry(m.getCountryList(), countryName);
			if(c!=null) {
				break;
			}
		}
		return c;
	}

	/**
	 * Returns the number of countries in map
	 * @return number of countries in map
	 */
	public int getCountryCount(){
		this.countryCount = 0;
		for (NodeOfMap continent : this.mapData){
			countryCount += continent.getCountriesCount();
		}
		return this.countryCount;
	}

	/**
	 * Check if a continent is owned by a player to which country belongs
	 * @param player player for which the continent to be checked
	 * @param country a country from continent to be checked
	 * @return true if continent belongs to player, false if continent not belongs to player
	 */
	public boolean continentWonByPlayer(Player player,NodeOfCountry country) {
		NodeOfMap m = country.getContinent();	
		for(NodeOfCountry c : m.getCountries()) {
			if(c.getOwner()!=player) {
					return false;
			}
			return true;
		}
		return false;

	}
	
	/**
	 * Set map Data to mapData
	 * @param mapData2 ArrayList of NodeOfMap objects
	 */
	public void setMapData(ArrayList<NodeOfMap> mapData2) {
		this.mapData = mapData2;
		
	}
}




