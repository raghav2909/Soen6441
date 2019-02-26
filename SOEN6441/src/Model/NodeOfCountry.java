package Model;


import java.util.ArrayList;

/**
 * This is class is used for storing the data of country
 * @author samansoltani
 * version 1.0
 */

public class NodeOfCountry 
{
	/**
	 * Save the country name
	 */
	
	private String CountryName;
	/**
	 * Save country neighbours
	 */
	
	private ArrayList<NodeOfCountry> Neighbours;
	/**
	 * Save the country coordinates with 1 and 0 index in x and y 
	 */
	
	private int[] Coordinate;
	/**
	 * Save the player name who owns the country
	 */
	
	private Player PlayerCountry;
	/**
	 * Save the number of armies in this country
	 */
	
	private int Armies;
	/**
	 * Save the neighbours of player
	 */
	
	private ArrayList<NodeOfCountry> PlayerNeighbours;
	/**
	 * Save the name of neighbours f player
	 */
	
	private ArrayList<String> PlayerNeighboursName;
	/**
	 * Set up country 
	 * @param name name of country
	 * @param neighbor neighbour countries
	 * @param coordinate coordinate of the country
	 */
	
	public NodeOfCountry (String name , ArrayList<NodeOfCountry> neighbour , int[] coordinate)
	{
		this.CountryName = name;
		this.Neighbours = neighbour;
		this.Coordinate = coordinate;
		this.PlayerCountry = null;
		this.Armies = 0;
	}
	/**
	 * Give the name of country
	 * @return the name of country
	 */
	
	public String getCountryName()
	{
		return this.CountryName;
	}
	
	/**
	 * Save a list of neighbour countries of a country
	 * @return ArrayList of neighbour countries
	 */
	public ArrayList<NodeOfCountry> getNeighbours()
	{
		return this.Neighbours;
	}
	
	
	/**
	 * give a list of neighbours country
	 * @return Array of countries
	 */
	public NodeOfCountry[] getNeighboursCountries() 
	{
		if(this.Neighbours != null) 
	    return this.Neighbours.toArray(new NodeOfCountry[this.Neighbours.size()]);
		return null;
	}
	
	
	/**
	 * Save a list of neighbour countries of a country
	 * @return Array of neighbour countries
	 */
	public String[] getNeighboursString()
	{
		ArrayList <String> countries = new ArrayList<String>();
		for(NodeOfCountry c : this.Neighbours)
		{
			countries.add(c.CountryName);
		}
		return countries.toArray(new String [this.Neighbours.size()]);
	}
	
	/**
	 * Save the coordinates of a country
	 * @return coordinates of a country
	 */
	 public int[] getCoordinate()
	 {
		 return this.Coordinate;
	 }
	 
	 /**
	  * gets the Player who owns the country
	  * @return the player who owns the country
	  */
	 public Player getPlayerCountry()
	 {
		 return this.PlayerCountry;
	 }
	 /**
	  * Save the number of armies in the country
	  * @return number of armies 
	  */
	 public int getArmyCount()
	 {
		 return this.Armies;
	 }
	 /**
	  * Sets owner
	  * @param player 
	  */
	 public void SetOwner(Player player)
	 {
		 this.PlayerCountry = player;
		 player.AddCountry(this);
	 }
	 /**
	  * places armies in the country
	  * @param armies number of armies
	  */
	 public void SetArmies(int armies)
	 {
		 this.Armies = armies;
	 }
	 /**
	  * Add armies to the  country
	  * @param NewArmy number of armies
	  */
	 public void AddArmy(int NewArmy)
	 {
		 this.Armies += NewArmy;
	 }
	 /**
	  * Add new neighbour
	  * @param NewNieghbour 
	  */
	 public void AddNeighbour(NodeOfCountry NewNeighbour)
	 {
		 if (this.Neighbours==null)
		 {
			 this.Neighbours = new ArrayList<NodeOfCountry>();
		 }
		 if(!this.Neighbours.contains(NewNeighbour))
		 {
			 this.Neighbours.add(NewNeighbour);
			 NewNeighbour.AddNeighbour(this);
		 }
	 }
	  /**
	   * checking for same countries 
	   * @param C country
	   * @return true or false
	   */
	 public boolean equal(Object C)
	 {
		 if (C instanceof NodeOfCountry)
		 {
			 if(((NodeOfCountry) C).CountryName.equals(CountryName))
			 {
				 for (int i=0; i< this.getNeighboursString().length;i++)
				 {
					 if(!this.getNeighboursString()[i].equals(((NodeOfCountry) C).getNeighboursString()[i]))
					 {
						 return false;
					 }
				 }
				 return true;
			 }
		 }
		 return false;
	 }
	 /**
	  * checking for arraylist contains country
	  * @param list ArrayList list of countries
	  * @param country country to be found
	  * @return true or false
	  */
	 public static boolean Contains(ArrayList<NodeOfCountry> list,String country)
	 {
		 for (NodeOfCountry l: list)
		 {
			 if(l.CountryName.equals(country))
			 {
				 return true;
			 }
		 }
		 return false;
	 }
	 /**
	  * getting a country using name
	  * @param list ArrayList of countries
	  * @param country name of the country
	  * @return object of the country
	  */
	 public static NodeOfCountry getCountry(ArrayList<NodeOfCountry> list,String country)
	 {
		 for (NodeOfCountry l : list)
		 {
			 if(l.CountryName.equals(country))
			 {
				 return l;
			 }
		 }
		 return null;
	 }
	 /**
	  * Set coordinate of country
	  * @param NewCoo new coordinate
	  */
	 public void SetCoordinate(int[] NewCoo)
	 {
		 this.Coordinate =NewCoo;
	 }
	 /**
	  * give a ArrayList of neighbours of country with same player
	  * @return PlayerNeighbours ArrayList
	  */
	 public ArrayList<NodeOfCountry> getPlayerNeighbours()
	 {
		 PlayerNeighbours = new ArrayList<NodeOfCountry>();
		 for (NodeOfCountry country : getNeighbours())
		 {
			 if(country.getPlayerCountry().equals(this.PlayerCountry))
			 {
				 PlayerNeighbours.add(country);
			 }
		 }
		 return PlayerNeighbours;
	 }
	 /**
	  * give a ArrayList of name of neighbours of country with same player
	  * @return PlayerNeighboursName List
	  */
	 public ArrayList<String> getPlayerNeighboursName()
	 {
		 PlayerNeighboursName = new ArrayList<String>();
		 for(NodeOfCountry country : getPlayerNeighbours())
		 {
			 PlayerNeighboursName.add(country.getCountryName());
		 }
		 return PlayerNeighboursName;
	 }
}
