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
	 * Number of Map's total countries
	 */
	private int count_Country;
	
	
	/**
	 * creating a arraylist for saving map data
	 */
	public static  ArrayList<NodeOfMap> DataMap;
	
	 /**
	  * this constructor handles getting map data from map reader
	  * @param FileName the address of map
	 * @throws IOException 
	  */
	public Map(String FileName) throws IOException 
	{
		    ReadMap Reader = new ReadMap();
			DataMap = Reader.mapreader(FileName);		 
	}
	
	public Map() {
		
	}

	/**
	 * returning the map data as an arraylist
	 * @return return the map data as an arraylist
	 */
	public  ArrayList<NodeOfMap> GetMapData()
	{
		
		return this.DataMap;
	
	}
	
	/**
	 * returning the map data 
	 * @return multidimensional array of map data
	 */
	public String[][] GetMapInfo()
	{
		ArrayList<Object[]> NewData = new ArrayList<Object[]>();
		for (NodeOfMap n : DataMap) 
		{
			for (NodeOfCountry m : n.getCountries()) 
			{
				String[] tempObject = new String [5];
				tempObject[1] = m.getNameOfCountry();
				if(m.getPlayerCountry() != null)
				{
					tempObject [3] = m.getPlayerCountry().getPlayerName();
				}
				else
				{
					tempObject[3] = "";
				}
				tempObject[2] = String.valueOf(m.getArmyCount());
				NewData.add(tempObject);
				tempObject[0] = Integer.toString(m.getCoordinate()[0]);
				tempObject[4] = Integer.toString(m.getCoordinate()[1]);
			}
		} 
		return NewData.toArray(new String[NewData.size()][]);
	}
	
	
	/**
	 * returning the map data
	 * @return multidimensional array of map data
	 */
	public String[][] GetMap()
	{
		ArrayList<Object[]> NewData = new ArrayList<Object[]>();
		for (NodeOfMap n : DataMap)
		{
			for(NodeOfCountry m : n.getCountries()) 
			{
				String[] tempObject = new String[5];
				tempObject[0] = n.getContinent() +", "+n.getControlValue();
				tempObject[1] = m.getNameOfCountry();
				String Neighbours ="";
				for(String s : m.getNeighboursString())
				{
					Neighbours = Neighbours + s + ", ";
				}
				tempObject[4]= Neighbours;
				if(m.getPlayerCountry() != null) 
				{
					tempObject[3] = m.getPlayerCountry().getPlayerName();
				}
				else 
				{
					tempObject[3] = "";
				}
				tempObject[2] = String.valueOf(m.getArmyCount());
				NewData.add(tempObject);
			}
		}
		return NewData.toArray(new String[NewData.size()][]);
	}
	
	/**
	 * This method displays the data on the console
	 */
	public void MapPrint()
	{
		Object[][] map = this.GetMapInfo();
		for(Object[] m : map)
		{
			System.out.println("Continent Name: "+ m[0]);
			System.out.println("Country Name: "+m[1]+", Neighbours: "+m[4]+", Owner: "+m[3]+", Armies: "+m[2]);
		}
	}
	
	
	/**
	 * notify Observer for a change
	 */
	public void UpdateMap() 
	{
		setChanged();
		notifyObservers(this); 
		
	}
	
	/**
	 * checking if the loaded map is valid
	 * @return true if map is valid
	 */
	public boolean MapValid() 
	{
		if (isMapConnectedGragh() && isMapNodesContainUniqueCountries())
		{
			return true;
		}
		return false;
	}
	
	
	/**
	 * checking if a map is a connected graph or not
	 * @return true if it is
	 */
	public boolean isMapConnectedGragh() 
	{
		for (NodeOfMap n : this.DataMap) 
		{
			for (NodeOfCountry m : n.getCountries()) 
			{
				if (m.getNeighboursCountries() == null || m.getNeighboursCountries().length ==0) 
				{
					return false;
				}
				for (NodeOfCountry b : m.getNeighboursCountries()) 
				{
					if(!b.getNeighbours().contains(m)) 
					{
						return false;
					}
				}
			}
		}
		return true;
	}
	
	
	/**
	 *check if every country belongs to a map
	 *@return true if all continents have unique countries ;else false 
	 */
	public boolean isMapNodesContainUniqueCountries() 
	{
		ArrayList <NodeOfCountry> s = new ArrayList<NodeOfCountry>();
		for (NodeOfMap n : this.DataMap) 
		{
			for (NodeOfCountry m : n.getCountries()) 
			{
				if (s.contains(m)) 
				{
					return false;
				}
				else 
				{
					s.add(m);
				}
			}
		}
		return true;
	}
	
		
	
	public ArrayList<String> getPlayerNeighbours(NodeOfCountry newCountry,Player newPLayer,boolean v){
		ArrayList<String> NeighbourCountries= new ArrayList<String>();
		for (NodeOfCountry country : newCountry.getNeighboursCountries()){
			if(v && country.getOwner().equals(newPLayer)) 
					NeighbourCountries.add(country.getNameOfCountry());
			
			if(!v && !country.getOwner().equals(newPLayer)) 
					NeighbourCountries.add(country.getNameOfCountry());	
			
		}
		return NeighbourCountries;
		
	}
	
	
	
	
	/**
	 * to check if the continent is owned by the player who won the  country.
	 * @param player For whom the continent is checked
	 * @param c COuntry for which continent check is done.
	 * @return  false is continent does not belong to the player  and true if it belongs
	 */
	public boolean WonPlayerContinent(Player player,NodeOfCountry c) {
		boolean b=false;
		NodeOfMap m = c.getContinent();	
		for(NodeOfCountry noc : m.getCountries()) {
			if(c.getOwner()!=player) {
				b=false;
			}
			else b=true;
		}
		return b;

	}
	
	
	
	public int CountryCount()
	{
		this.count_Country=0;
		for(NodeOfMap cont:this.DataMap)
		{
			count_Country+=cont.getCountOfCountries();
		}
		return this.count_Country;
	}
	
	/**
	 * This method gets the object of country from its name
	 * @return Total map countries
	 */
	
	public NodeOfCountry gettingCountry(String CN)
	{
		NodeOfCountry noc= null;
		for(NodeOfMap nom: this.DataMap)
		{
			noc= NodeOfCountry.getCountry(nom.getClst(), CN);
			if(noc!=null)
			{
			break;	
			}
		}
		return noc;
	}

	/**
	 * Returns the number of countries in map
	 * @return number of countries in map
	 */
	public int getCountryCount(){
		this.count_Country = 0;
		for (NodeOfMap continent : this.DataMap){
			count_Country += continent.getCountOfCountries();
		}
		return this.count_Country;
	}

	/**
	 * Check if a continent is owned by a player to which the specific country belongs
	 * @param player is player for which the continent is to be checked
	 * @param country a country from which continent is to be checked
	 * @return false if continent not belongs to player and true if continent belongs to player 
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
}




