package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import view.Map_Frame;

/**
 * This class handles the map data and also map editor.
 * @author samansoltani
 *@version 1.0
 */
public class Map extends Observable
{
	/**
	 * reference of NodeOfMap object
	 */
	NodeOfMap MapNode;
	private String NewFileMap;
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
	 * new controller object
	 */
	
	
	
	/**
	 * creating a arraylist for saving map data
	 */
	private  ArrayList<NodeOfMap> MapData;
	
	
	 /**
	  * this constructor handles getting map data from map reader
	  * @param FileName the address of map
	 * @throws IOException 
	  */
	public Map(String FileName) throws IOException 
	{
		System.out.println(" mapsssss"+FileName);
		ReadMap Reader = new ReadMap();
			MapData = Reader.mapreader(FileName);
			System.out.println(MapData);
		 
	}
	
	

	/**
	 * returning the map data as an arraylist
	 * @return return the map data as an arraylist
	 */
	public  ArrayList<NodeOfMap> GetMapData()
	{
		//System.out.println("sasasas");
		//ArrayList<NodeOfMap> mm = MapData;
		return MapData;
	
	}
	
	/**
	 * returning the map data 
	 * @return multidimensional array of map data
	 */
	public String[][] GetMapInfo()
	{
		ArrayList<Object[]> NewData = new ArrayList<Object[]>();
		for (NodeOfMap n : MapData) 
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
		for (NodeOfMap n : MapData)
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
	 * show the map data on console
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
		for (NodeOfMap n : this.MapData) 
		{
			for (NodeOfCountry m : n.getCountries()) 
			{
				if (m.getNeighboursCountries() == null || m.getNeighboursCountries().length ==0) 
				{
					return false;
				}
				for (NodeOfCountry b : m.getNeighboursCountries()) 
				{
					if(b.getNeighbours().contains(m)) 
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
		for (NodeOfMap n : this.MapData) 
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
	
	
	public void WritingOldMap(ArrayList<NodeOfMap> continents) {
		this.continents = continents;
	}
	
	public boolean CheckContinentExist(String c) {
		boolean ContinentExist = false;
		for (NodeOfMap n : continents) {
			if (n.getContinent().compareTo(c)==0) {
				ContinentExist = true;
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
			System.out.println(newFileMap());
			return newFileMap();
		}
		else if (Map_Frame.ActionChoosen().compareTo("existing")==0) {
			System.out.println(oldFileMap());
			return oldFileMap();
		}
		return null;
	}
	
	
	
	public String getOlfFileMap() {
		return OldFileMap;
	}
	
	
	/*public boolean CheckCountryExist(String c) {
		boolean ce = false;
		for (NodeOfMap n : continents) {
			for (NodeOfCountry i : n.getCountries()) {
				if(i.getNameOfCountry().compareTo(c)==0) {
					ce = true;
				}
			}
		}
		return ce;
	}*/
}


