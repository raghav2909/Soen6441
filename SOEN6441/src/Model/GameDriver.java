package Model;
<<<<<<< HEAD
=======

import java.util.ArrayList;

import controllers.the_main_controller;
import view.ControlsConsole;
import view.PlayerConsole;

>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441.git
/**
 * This class handles the game driver.
 * @author samansoltani
 *@version 1.0
 */
public class GameDriver {
<<<<<<< HEAD
=======
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
	private ArrayList<Player> players;
	
	
	
	/**
	 * TheMainController class object
	 */
	private the_main_controller controller;
	
	
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
			if (c.getCountryName().equals(cn.getCountryName())) {
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
			if (c.getCountryName().equals(cn)) {
				return c;
			}
		}
		return null;
	}
	
	
	
	/**
	 * listener for reinforcement phase
	 */
	public void setControlListenerForR() {
		this.controller.setRListener();
	}
	
	
	
	/**
	 * creating a instance for ControlConsol class
	 * @return ControlClass instance
	 */
	public ControlsConsole getControl() 
	{
		return this.controls();
	}
	
	
	
	/**
	 * change between phases
	 */
	public void ChangePhase() 
	{
		if(this.CurrentPhase.equals(GamePhase.reinforcement){
			CurrentPhase.rphase();
		}
		else if (this.CurrentPhase.equals(GamePhase.attack)) {
			CurrentPhase.aphase();
		}
		else if (this.CurrentPhase.equals(GamePhase.fortification)) {
			this.getNextPlayer();
			CurrentPhase.fphase();
		}
		map.UpdateMap();
	}
	
	
	
	
	/**
	 * refreshes the phases
	 */
	public void ContinuePhase(){
		if(this.CurrentPhase.equals(GamePhase.reinforcement){
			CurrentPhase.rphase();
		}
		else if (this.CurrentPhase.equals(GamePhase.attack)) {
			CurrentPhase.aphase();
		}
		else if (this.CurrentPhase.equals(GamePhase.fortification)) {
			CurrentPhase.fphase();
		}
		map.UpdateMap();
	}
	
	
	/**
	 * listener for fortification phase
	 */
	public void setControlListenerForF() 
	{
		this.controller.setFListener();
	}
	
	
	
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
			PlayerNeighboursNames.add(c.getCountryName());
		}
		return (String[]) PlayerNeighboursNames.toArray();
	}
	
	
	
	/**
	 * adding the new player
	 * @param np new player
	 */
	public void setPlayerList(Player np) 
	{
		if(this.players==null) {
			this.players = new ArrayList<Player>();
		}
		this.players.add(np);
	}
}
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441.git

}
