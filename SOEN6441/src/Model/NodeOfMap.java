package Model;

import java.util.ArrayList;


/**
 * Map data is updated/stored using this class
 * @author YashNarra
 * @version 2.0
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
	
	private int CountCountries;
	
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
		newcountry.setContinent(this);
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
	
	
	/**
	 * checking if two objects are equal or not
	 * @return true if they are equal
	 */
	public boolean equals(Object c) {
		if(c instanceof NodeOfMap) {
			NodeOfMap t = (NodeOfMap) c;
			if(t.getContinent()==this.Continent && t.ControlValue == this.ControlValue) {
				if(t.countrylist.size()==this.countrylist.size()) {
					for(int i=0;i<this.getCountries().length;i++) {
						if(!this.getCountries()[i].equals(t.getCountries()[i])) {
							return false;
						}
					}
					return true;
				}
			}
		}
		return false;
	}
	 
	
	public int getCountOfCountries() {
		this.CountCountries = getCountryList().size();
		return this.CountCountries;
	}
		
}
	


