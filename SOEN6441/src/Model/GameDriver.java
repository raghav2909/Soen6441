package Model;

import java.util.ArrayList;

import controllers.the_main_controller;

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
	//private PlayerInfoView PlayerInfo;
	
	
	/**
	 * the map class object
	 */
	private Map map;
	
	
	/**
	 * Save player types in an arraylist
	 */
	private ArrayList<Player> players;
	
	
	
	/**
	 * TheMainController class object
	 */
	private the_main_controller controller;
	
	
	/**
	 * ControlsConsol class object
	 */
	//private ControlsConsol controls;
	
	
	
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
	public void RunGame() 
	{
		//StartUpPhase();
		CurrentPhase.rphase();
	}
	
	
	
	/**
	 * starting the startup phase 
	 */
	/*public void StartUpPhase() 
	{
		String [] NewPlayer = controller.getPlayerInfo();
		players = new ArrayList<Player>();
		for (String np : NewPlayer) {
			players.add(new Player(np,ArmyCount.InitalData.getarmycount(NewPlayer.length),map.GetMapData()));
		}
		players.get(0).SetTurnTrue();
		UpdatePlayerView();
		int i =0 ;
		for (NodeOfMap n: map.GetMapData()) {
			for(NodeOfCountry m : n.getCountries()) {
				m.SetOwner(players.get(i));
				if(i++>= players.size()) {
					i=0;
				}
			}
		}
		for (int j=0; j<players.get(0).getCountArmies();j++) {
			for(Player p : players) {
				String l;
				if(p.getEmptyCountriesName().length !=0) {
					l = controller.PlaceArmyDialog(p.getEmptyCountriesName());
				}
				else {
					l = controller.PlaceArmyDialog(p.getNameOfCountries());
				}
				p.getCountry(l).AddArmy(1);
			}
		}
		map.UpdateMap();
	}*/
	
	
	
	
	/**
	 * set up the map view
	 * @param NewGUI the object of map view
	 */
	/*public void SetConsolMap(GameConsol NewGUI) 
	{
		map.addObserver(NewGUI);
	}*/
	
	
	
	/**
	 * set up the controls view
	 * @param cv the object of control view
	 */
	/*public void SetConsolControl(ControlsConsol cv) 
	{
		this.controls = ControlsConsol;
	}*/
	
	
	
	/**
	 * show the player information on consol
	 */
	public void UpdatePlayerConsol() 
	{
		String [] PlayerNames = new String[players.size()];
		int i=0;
		for (Player p : players) {
			PlayerNames[i] = p.getPlayerName();
			i++;
		}
		//PlayerInfo.setPlayerInfo(PlayerNames);
	}
	
	
	
	/**
	 * getting the player with the correct turn
	 * @return current player
	 */
	public Player getCurrent() 
	{
		for (Player p : players) {
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
		int CurrentPlayer = players.indexOf(getCurrent());
		getCurrent().SetTurnFalse();
		if (CurrentPlayer == players.size()-1) {
			players.get(0).SetTurnTrue();
		}
		else
		{
			players.get(CurrentPlayer+1).SetTurnTrue();
		}
	}
	
	
	
	/**
	 * getting object of map class
	 * @param Path save the map path
	 */
	public void CreateMapObject(String Path) {
		map = new Map(Path);
	}
	
	
	
	/**
	 * show the country neighbours as a parameter
	 * @param cn name of the country
	 * @return a list containing of country neighbours
	 */
	public String[] getNeighboursCountryName(String cn)
	{
		for (NodeOfCountry c : getCurrent().getCountries()) {
			if(c.getCountryName().equals(cn)) {
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
	 * getting the countries of current player
	 * @return countries name
	 */
	public String [] getPlayerCountries() 
	{
		return getCurrent().getNameOfCountries();
	}
}

