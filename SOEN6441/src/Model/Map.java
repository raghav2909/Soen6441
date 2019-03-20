package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Observable;

import player.Player;
import view.Map_Frame;

/**
 * This class handles the map data and also map editor.
 * @author samansoltani
 *@version 2.0
 */
public class Map extends Observable
{
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
	 * It will  create hash table for storing the data of all countries and the time it has been visited
	 */
	Hashtable<String, Boolean> TableOfCountry = new Hashtable<String,Boolean>();
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
	 * Number of Map's total countries
	 */
	private int count_Country;
	
	
	/**
	 * creating a arraylist for saving map data
	 */
	public   ArrayList<NodeOfMap> DataMap;
	
	
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
		notifyObservers(this); 
		setChanged();
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
	
	
	public String getFinalMap() {
		if (Map_Frame.ActionChoosen().compareTo("new")==0) {
		
			return newFileMap();
		}
		else if (Map_Frame.ActionChoosen().compareTo("existing")==0) {
			
			return oldFileMap();
		}
		return null;
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
	
	
	
	public String getOlfFileMap() {
		return OldFileMap;
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

}


