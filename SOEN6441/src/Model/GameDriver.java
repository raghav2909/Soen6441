package Model;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Observer;

import controllers.Player_Information_Controller;
import controllers.the_main_controller;
import view.ControlsConsole;
import view.MapConsole;
import view.PlayerConsole;
import Model.Map;




/**
 * This class handles the game driver.
 * @author samansoltani
 *@version 1.0
 */
public class GameDriver {

	/**
	 * the game driver class object
	 */
	private static GameDriver driver;
	
	
	/**
	 * the player view class object
	 */
	private PlayerConsole PlayerInfo;
	
	
	/**
	 * the map class object
	 */
	private Map map;
	
	
	/**
	 * Save player types in an arraylist
	 */
	private ArrayList<Player> Player;
	
	
	
	/**
	 * TheMainController class object
	 */
	private the_main_controller controller;
	/**
	 * Player_Information_Controller object
	 */
	private Player_Information_Controller pic;
	
	/**
	 * ControlsConsol class object
	 */
	private ControlsConsole controls;
	
	
	
	/**
	 * GamePhase class object
	 */
	private GamePhase CurrentPhase;
	
	
	
	/**
	 * Player class object
	 */
	private Player CurrentPlayer;
	
	
	/**
	 * getting an array list of player neighbours
	 */
	private ArrayList<NodeOfCountry> PlayerNeighbours;
	
	
	
	/**
	 * getting the name of a player neighbours
	 */
	private ArrayList<String> PlayerNeighboursNames;
	
	
	
	private GameDriver() 
	{
		//controller = new the_main_controller(this);
		CurrentPhase = new GamePhase("reinforcement");
	}
	
	
	
	/**
	 * Creating a instance of GameDriver class
	 * @return instance of GameDriver class
	 */
	public static GameDriver getInstance() 
	{
		if(driver==null) 
		{
			driver = new GameDriver();
		}
		return driver;
	}
	
	
	
	
	/**
	 * starting the game
	 */
	public void GameOn() 
	{
		StartUpPhase();
		CurrentPhase.rphase();
	}
	
	
	
	/**
	 * starting the startup phase 
	 */
public void StartUpPhase() 
	{
	int j=0;
	int i =0 ;
		String [] NewPlayer = pic.Information_OF_Playres();
		Player = new ArrayList<Player>();
		for (String np : NewPlayer) {
			Player.add(new Player(np,ArmyCount.InitalData.getarmycount(NewPlayer.length),map.GetMapData()));
		}
		Player.get(0).SetTurnTrue();
		UpdatePlayerConsol();
	
		for (NodeOfMap n: map.GetMapData()) {
			for(NodeOfCountry m : n.getCountries()) {
				m.SetOwner(Player.get(i));
				if(i++>= Player.size()) {
					i=0;
				}
			}
		}

			while (j<Player.get(0).getCountArmies())
			{
			for(Player p : Player) {
				String l;
				if(p.getEmptyCountriesName().length !=0) {
					l = pic.ArmyPlacing(p.getEmptyCountriesName());
							
				}
				else {
					l = pic.ArmyPlacing(p.getNameOfCountries());
						
				}
				p.getCountry(l).AddArmy(1);
			}
			j++;
		}
		map.UpdateMap();
	}
	
/**
 * Sets PlayerInfo view.
 * @param newView PlayerInfoView object initialized.
 */
public void setPlayerConsole(PlayerConsole newView) {
	this.PlayerInfo= newView;
}

	/**
	 * set up the map view
	 * @param NewGUI the object of map view
	 */
	public void SetConsolMap(MapConsole view) 
	{
		map.addObserver(view);
	}
	
	
	

	/**
	 * set up the controls view
	 * @param cv the object of control view
	 */
	public void SetConsolControl(ControlsConsole cv) 
	{
		this.controls = cv;
	}
	
	
	
	/**
	 * Displaying the information of player on console
	 */
	public void UpdatePlayerConsol() 
	{
		String [] PlayerNames = new String[Player.size()];
		int i=0;
		for (Player p : Player) {
			PlayerNames[i] = p.getPlayerName();
			i++;
		}
		PlayerInfo.setPlayerData(PlayerNames);
	}
	
	
	
	/**
	 * getting the player with the correct turn
	 * @return current player
	 */
	public Player getCurrent() 
	{
		for (Player p : Player) {
			if (p.getTurn()) {
				return p;
			}
		}
		return null;
	}
	
	
	
	
	/**
	 * getting the next player
	 */
	public void getNextPlayer() 
	{
		int CurrentPlayer = Player.indexOf(getCurrent());
		getCurrent().SetTurnFalse();
		if (CurrentPlayer == Player.size()-1) {
			Player.get(0).SetTurnTrue();
		}
		else
		{
			Player.get(CurrentPlayer+1).SetTurnTrue();
		}
	}
	
	
	
	/**
	 * getting object of map class
	 * @param Path save the map path
	 */
	public void CreateMapObject(String Path) {
		System.out.println(" gamedriver   "+Path);
		try {
			map = new Map(Path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * show the country neighbours as a parameter
	 * @param cn name of the country
	 * @return a list containing of country neighbours
	 */
	public String[] getNeighboursCountryName(String cn)
	{
		for (NodeOfCountry c : getCurrent().getCountries()) {
			if(c.getNameOfCountry().equals(cn)) {
				return c.getNeighboursString();
			}
		}
		return null;
	}
	
	
	
	/**
	 * getting the army count of the player
	 * @return army count of the current player
	 */
	public int getArmyCount() 
	{
		return getCurrent().getCountArmies();
	}
	
	
	
	/**
	 * getting the name countries of current player
	 * @return countries name
	 */
	public String [] getPlayerCountriesName() 
	{
		return getCurrent().getNameOfCountries();
	}
	
	
	
	
	/**
	 * getting the countries of current player
	 * @return the list of countries
	 */
	public ArrayList<NodeOfCountry> getPlayerCountries()
	{
		return getCurrent().getCountries();
	}
	
	
	
	/**
	 * getting the neighbours of a country
	 * @param cn the country with nieghbours
	 * @return list of nirghbours
	 */
	public NodeOfCountry [] getNeighboursCountry(NodeOfCountry cn) 
	{
		for (NodeOfCountry c : getCurrent().getCountries()) {
			if (c.getNameOfCountry().equals(cn.getNameOfCountry())) {
				return c.getNeighboursCountries();
			}
		}
		return null;
	}
	
	
	
	/**
	 * gives the country node of the country
	 * @param cn name of the country
	 * @return country node of the given country
	 */
	public NodeOfCountry getCountryNode(String cn) {
		for (NodeOfCountry  c: getCurrent().getCountries()) {
			if (c.getNameOfCountry().equals(cn)) {
				return c;
			}
		}
		return null;
	}
	
	
	
	/**
	 * listener for reinforcement phase
	 */
	/*public void setControlListenerForR() {
		this.controller.setRListener();
	}/*
	
	
	
	/**
	 * creating a instance for ControlConsol class
	 * @return ControlClass instance
	 */
	/*public ControlsConsole getControl() 
	{
		return this.controls();
	}/*
	
	/**
	 * change between phases
	 */
	public void ChangePhase() 
	{
		if(this.CurrentPhase.equals(CurrentPhase.reinforcement)){
			CurrentPhase.aphase();
		}
		else if (this.CurrentPhase.equals(CurrentPhase.attack)) {
			CurrentPhase.fphase();
		}
		else if (this.CurrentPhase.equals(CurrentPhase.fortification)) {
			this.getNextPlayer();
			CurrentPhase.rphase();
		}
		map.UpdateMap();
	}
	
	
	
	
	/**
	 * refreshes the phases
	 */
	public void ContinuePhase(){
		if(this.CurrentPhase.equals(CurrentPhase.reinforcement)){
			CurrentPhase.rphase();
		}
		else if (this.CurrentPhase.equals(CurrentPhase.attack)) {
			CurrentPhase.aphase();
		}
		else if (this.CurrentPhase.equals(CurrentPhase.fortification)) {
			CurrentPhase.fphase();
		}
		map.UpdateMap();
	}
	
	
	/**
	 * listener for fortification phase
	 */
	/*public void setControlListenerForF() 
	{
		this.controller.setFListener();
	}*/
	
	
	
	/**
	 * getting the map class object
	 */
	public Map getMap() 
	{
		return this.map;
	}
	
	
	
	
	/**
	 * getting a list of neighbours of a player
	 * @param cn country node of the country
	 * @return array list of neighbours of a player
	 */
	public ArrayList<NodeOfCountry> getPlayerNeighbourCountries(NodeOfCountry cn)
	{
		PlayerNeighbours = new ArrayList<NodeOfCountry>();
		for(NodeOfCountry c : getPlayerNeighbourCountries(cn)) {
			if(cn.getOwner().equals(c.getOwner())){
				PlayerNeighbours.add(c);
			}
		}
		return PlayerNeighbours;
	}
	
	
	
	
	/**
	 * getting a list of neighbours names of a player
	 * @param cn the country node of the country
	 * @return name of neighbours of a player
	 */
	public String[] getPlayerNeighbourCountriesName(String cn) 
	{
		PlayerNeighboursNames = new ArrayList<String>();
		for (NodeOfCountry c : getPlayerNeighbourCountries(getCountryNode(cn))) {
			PlayerNeighboursNames.add(c.getNameOfCountry());
		}
		return (String[]) PlayerNeighboursNames.toArray();
	}
	
	
	
	/**
	 * adding the new player
	 * @param np new player
	 */
	public void setPlayerList(Player np) 
	{
		if(this.Player==null) {
			this.Player = new ArrayList<Player>();
		}
		this.Player.add(np);
	}

}



