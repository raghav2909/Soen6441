package Model;

import java.util.ArrayList;


/**
 * Map data is updated/stored using this class
 * @author YashNarra
 * @version 1.0
 */
public class NodeOfMap {
	
	/**
	 * String of the name of continent
	 */
	
	private String Continent; 
	
	/**
	 * ArrayList of countries of a continent
	 */
	
	private ArrayList<NodeOfCountry> countrylist;
	
	/**
	 * Integer Control Value for a continent
	 */
	
	private int ControlValue;
	
	/** Constructor of NodeOfMap
	 * Initializing the values of continent attributes
	 * @param name continent name
	 * @param newcountrylist list of countries in the continent
	 * @param newcv control value of the continent
	 */
	
	public NodeOfMap(String name, ArrayList<NodeOfCountry> newcountrylist, int newcv) {
		this.Continent=name;
		this.countrylist=newcountrylist;
		this.ControlValue=newcv;	
	}
	
	/**method to return array of countries in a continent
	 * @return clsit array of countries
	 */
	
	public ArrayList<NodeOfCountry>getClst() {
		//NodeOfCountry[] clist=this.countrylist.toArray(new NodeOfCountry[this.countrylist.size()]);;
		return countrylist;
	}
	
	
	/** method to return the name of continent
	 * @return Continent name of Continent
	 */
	public String getContinent() {
		return this.Continent;
	}
	
	/**method to return arraylist of countries in a continent 
	 * @return countrylist list of countries
	 */
	
	public NodeOfCountry[] getCountries(){
		//return this.countrylist;
		return this.countrylist.toArray(new NodeOfCountry[this.countrylist.size()]);
		
	}
	
	public ArrayList<NodeOfCountry> getCountryList(){
		return this.countrylist;
	}

	/** method to get the control value of a continent
	 * @return ContolValue control value of a continent
	 */
	
	public int getControlValue() {
		return this.ControlValue;
	}

	/**
	 * Method to add country to the countrylist of a continent
	 * @param newCountries arraylist of new countries
	 */
	public void addcountry(NodeOfCountry newcountry) {
		if(this.countrylist==null) {
			this.countrylist=new ArrayList<NodeOfCountry>();
		}
		this.countrylist.add(newcountry);
	}
	
	/**
	 * Method to delete a country from list of countries in a continent
	 * @param delcountry is the country to delete
	 */
	public void deletecountry(NodeOfCountry delcountry) {
		countrylist.remove(delcountry);
	}
	
	/**
	 * Method to assign list of countries to a continent
	 * @param newCountries arraylist containing countries
	 */
	
	public void setCountries(ArrayList<NodeOfCountry> newCountries) {
		ArrayList<NodeOfCountry> ncountries= newCountries; 
		
		this.countrylist=ncountries;	
	}

	@Override
	public String toString() {
		return "NodeOfMap [Continent=" + Continent + ", countrylist=" + countrylist + ", ControlValue=" + ControlValue
				+ "]";
	}
	
		
}
	


