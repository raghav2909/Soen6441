package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import controllers.Player_Information_Controller;
import controllers.the_main_controller;
//import sun.security.provider.DSAKeyPairGenerator.Current;
import view.ControlsConsole;
import view.MapConsole;
import view.PlayerConsole;
import view.openingdialog;
import Model.Map;

/**
 * This class handles the game driver.
 * 
 * @author samansoltani
 * @author raghavsharda
 * @version 1.0
 */
public class GameDriver extends Observable {
	
	/**
	 * Object of GameDriver class.
	 */
	private static GameDriver driver;
	
	/**
	 * Object of Player_Information_Controller class.
	 */
	private Player_Information_Controller InfoOfPlayer;
	
	/**
	 * Object of Map class.
	 */
	private Map map;
	
	/**
	 * ArrayList to store the player type.
	 */
	private ArrayList<Player> players;
	
	/**
	 * Object of the_main_controller class.
	 */
	private the_main_controller controller;
	
	/**
	 * Object of ControlsConsole class.
	 */
	private ControlsConsole Controls;
		
	/**
	 * Object of GameTurnManager class.
	 */
	private GameTurnManager TurnManagment;
	
	/**
	 * Object of Player class.
	 */
	private Player CurrentP;

	/**
	 * Pack of Cards
	 */
	private ArrayList<Card> Cards;
	
	/**
	 * Observer notification string.
	 */
	private String Notification;
	
	/**
	 * Constructor for initializing .
	 */
	private GameDriver() {
		TurnManagment = new GameTurnManager("Reinforcement");
		Cards = Card.CardGeneration();
	}

	/**
	 * This method is used to access  object of this class.
	 * @return instance of GameDriver class.
	 */
	public static GameDriver GetInit() {
		if(driver==null){
			driver = new GameDriver();
		}
		return driver;
	}
	/**
	 * Set controller in this class.
	 * @param NewC for setting the the_main_controller object.
	 */
	public void setController(the_main_controller NewC) {
		this.controller = NewC;
	}
	
	/**
	 * Starting the game.
	 */
	public void Start() {
		setChanged();
		notifyObservers("Startup");
		String[] NewPlayer = InfoOfPlayer.Information_OF_Playres();
		StartUp(NewPlayer);
		TurnManagment.startTurn(this.CurrentP);
		setChanged();
		notifyObservers("Reinforcement");
	}
	
	/**
	 * This method starts the startup phase of game.
	 * @param PD String array to store the player type.
	 */
	public void StartUp(String[] PD) {
		
		AllocatingCountries(PD,map.GetMapData());
		
		UpdatePlayer();
		
		int AllArmies= players.get(0).getCountArmies();
		for(int i1=0;i1<AllArmies;i1++){
			System.out.print("Armies allocated"+players.get(0).getCountArmies());
			for(Player p: players){
				String s;
				if(p.getEmptyCountriesName().length!=0){
					s = controller.placeArmyDialog(p.getEmptyCountriesName(), p.getPlayerName()+" Place your army");
				}else{
					s= controller.placeArmyDialog(p.getEmptyCountriesName(),p.getPlayerName()+" Place your army");
				}
				p.getCountry(s).AddArmy(1);
				p.RemovedArmies(1);
			}
		}
		updateMap();
	}
	
	/**
	 * This method create player objects and allocating countries to them.
	 * @param PD list of players
	 * @param MD arraylist containing NodeOfMap Objects representing continents
	 */
	public void AllocatingCountries(String[] PD, ArrayList<NodeOfMap> MD) {
		players = new ArrayList<Player>();
		for(String p: PD){
			Player t = new Player(p,ArmyCount.getarmycount(PD.length));
			players.add(t);
			setChanged();
			notifyObservers(t.getPlayerName());
		}
		players.get(0).SetTurnTrue();
		this.CurrentP = players.get(0);
		int i = 0;
		for(NodeOfMap m : MD){
			for(NodeOfCountry c: m.getCountries()){
				c.SetOwner(players.get(i));
				if(++i>=players.size()){
					i=0;
				}
			}
		}
	}

	/**
	 * Sets Player information Console.
	 * @param nc Player_Information_Controller object initialized.
	 */
	public void setPlayerView(Player_Information_Controller nc) {
		this.InfoOfPlayer = nc;
	}

	/**
	 * Sets Map view.
	 * @param newGui MapView object initialized.
	 */
	public void setMapView(MapView newGui) {
		map.addObserver(newGui);
	}

	/**
	 * Sets Controls view.
	 * @param controlView ControlsConsole object initialized.
	 */
	public void setControlsView(ControlsConsole controlView) {
		this.Controls = controlView;
	}
	
	/**
	 * This method show players information on GUI.
	 */
	public void UpdatePlayer() {
		String[] playerNames = new String[players.size()];
		int i=0;
		for(Player p: players){
			playerNames[i] = p.getPlayerName();
			i++;
		}
		InfoOfPlayer.setPlayerInfo(playerNames);
	}

	/**
	 * Gets the player with the current turn.
	 * @return current player 
	 */
	public Player getCurrentPlayer() {
		return this.CurrentP;
	}

	/**
	 * Sets the next player's turn.
	 */
	public void setNextPlayerTurn() {
		int currentPlayerIndex = players.indexOf(getCurrentPlayer());
		this.CurrentP.setTurnFalse();
		if (currentPlayerIndex == players.size()-1){
			this.CurrentP = players.get(0);
		}else{
			this.CurrentP = players.get(currentPlayerIndex+1);
		}
		this.CurrentP.SetTurnTrue();
		this.getCurrentPlayer().setArmies(this.getCurrentPlayer().getArmies());
		setChanged();
		notifyObservers("Cards");
	}

	/**
	 * Creates the object of Map Class by passing the map file path.
	 * @param mapPath stores the path of the map file.
	 */
	public void createMapObject(String mapPath) {
		map = new Map(mapPath);
	}
	
	/**
	 * Gives the list of the neighbors of the country passed as a parameter.
	 * @param countryname Name of the country.
	 * @return Neighbors of the country.
	 */
	public String [] getNeighbourCountryNames(String countryname) {
		for(NodeOfCountry country: getCurrentPlayer().getCountries()){
			if(country.getCountryName().equals(countryname)){
				return country.getNeighbourCountriesString();
			}
		}
		return null;
	}

	/**
	 * Gets the army count of the current player.
	 * @return army count of the current player.
	 */
	public int getPlayerArmies() {
		return getCurrentPlayer().getArmiesCount();
	}

	/**
	 * Gives the countries owned by a player.
	 * @return The list of country nodes.
	 */
	public ArrayList<NodeOfCountry> getPlayerCountries() {
		return getCurrentPlayer().getCountries();
	}

	/**
	 * Gives the neighbors of a particular country.
	 * @param countrynode Country whose neighbors are to be fetched.
	 * @return list of neighbor countries.
	 */
	public NodeOfCountry [] getNeighbourCountries(NodeOfCountry countrynode) {
		for(NodeOfCountry country: getCurrentPlayer().getCountries()){
			if(country.getCountryName().equals(countrynode.getCountryName())){
				return country.getNeighbourCountries();
			}
		}
		return null;
	}
	
	/**
	 * Gives the country node of the given country name.
	 * @param countryname name of a country
	 * @return country node for the given country name
	 */
	public NodeOfCountry getCountry(String countryname) {
		return this.CurrentP.getCountry(countryname);
	}
	
	/**
	 * Sets action listener for reinforcement phase.
	 */
	public void setControlsActionListeners() {
		this.controller.setActionListner();
	}
	
	/**
	 * Gives the instance of ControlsConsole class.
	 * @return ControlsConsole class object.
	 */
	public ControlsConsole getControlGUI() {
		return this.Controls;
	}

	/**
	 * Delegate method to call method from GameTurnManager class to continue phases.
	 */
	public void continuePhase() {
		TurnManagment.continuePhase();
		updateMap();
		setChanged();
		notifyObservers(TurnManagment.getPhase());
	}
	
	/**
	 * Delegate method to call method from GameTurnManager class to change between phases.
	 */
	public void changePhase() {
		TurnManagment.changePhase();
		updateMap();
		setChanged();
		notifyObservers(TurnManagment.getPhase());
	}
	
	/**
	 * Delegate method to call updateMap method from map class.
	 */
	public void updateMap() {
		map.updateMap();
	}
	
	/**
	 * Adds listener for fortification phase.
	 */
	public void setFortificationLiteners() {
		this.controller.setFortificationListeners();
	}
	
	/**
	 * Returns object of Map class
	 *  @return map 
	 */
	public Map getMap(){
		return this.map;
	}
	
	/**
	 * Adds the new player to the arraylist of players.
	 * @param newPlayer Player object.
	 */
	public void setPlayerList(Player newPlayer){
		if(this.players==null) {
			this.players = new ArrayList<Player>();
		}
		this.players.add(newPlayer);
	}

	/**
	 * This method call the shiftArmiesOnReinforcement method from player class, depending on the result returned by method
	 * either changes the Phase or continue with the current phase.
	 * @param countrySelected Country where armies should be placed
	 * @param armies number of armies to be placed
	 */
	public void shiftArmiesOnReinforcement(String countrySelected, int armies) {
		if(this.CurrentP.shiftArmiesOnReinforcement(countrySelected, armies)==0) {
			changePhase();
		}
		else {
			continuePhase();
		}
	}
	
	/**
	 * This method get list of neighbor countries of the specified country owned by same player from map class
	 * and update the controls view through controller.
	 * @param countrySelected the country whose neighbors are to be listed
	 */
	public void fortificationNeighbourListUpdate(String countrySelected) {
		NodeOfCountry countrySelect = this.CurrentP.getCountry(countrySelected);
		if(countrySelect.getArmiesCount()>1) {
			ArrayList<String> neighborList = map.getPlayerNeighbourCountries(countrySelect,this.CurrentP,true);
			controller.updateControlsFortification(countrySelect.getArmiesCount(), neighborList.toArray(new String[neighborList.size()])); 
		}
	}
	
	/**
	 * A delegate method to call getArmiesShiftedAfterFortification in Player class.
	 * @param newCountry country from where armies are to be moved
	 * @param newNeighbour country where armies are to be moved
	 * @param newArmies number of armies to be moved
	 */
	public void getArmiesShiftedAfterFortification(String newCountry, String newNeighbour, int newArmies) {
		this.CurrentP.getArmiesShiftedAfterFortification(newCountry, newNeighbour, newArmies);
	}
	
	/**
	 * A delegate method to call setAttackListeners in the_main_controller class
	 */
	public void setAttackListeners() {
		controller.setAttackListeners();
	}
	
	/**
	 * This method create a list of neighbour countries for a selected country whith different owners than the current player.
	 * Then update list on the controls view through controller.
	 * @param countrySelected selected country whose neighbors are required.
	 */
	public void attackNeighbourListUpdate(String countrySelected) {
		NodeOfCountry countrySelect = this.CurrentP.getCountry(countrySelected);
		if(countrySelect.getArmiesCount()>1) {
			ArrayList<String> neighborList = map.getPlayerNeighbourCountries(countrySelect,this.CurrentP,false);
			controller.updateNeighborList(neighborList.toArray(new String[neighborList.size()]));
		}
	}
	
	/**
	 * This method announce the attack, get number of dice from both attacker and defender. If a country loose all its armies, the other player occupy the country.
	 * @param attackerCountry country attacking
	 * @param defenderCountry country defending against attack
	 */
	public void announceAttack(String attackerCountry, String defenderCountry) {
		this.Notification = "Attack Attacker Country: "+attackerCountry+"  Defender Country: "+defenderCountry+"  ";
		setChanged();
		notifyObservers(Notification);
		/*Announce attack on phase view.*/
		NodeOfCountry dCountry = map.getCountry(defenderCountry);
		Player defender = dCountry.getOwner();
		NodeOfCountry aCountry = CurrentP.getCountry(attackerCountry);
		/*Show dialog boxes and get input from attacker and defender on how many dice to roll.*/
		int aArmies = this.CurrentP.selectDiceForAttack(attackerCountry);
		int dArmies = defender.selectDiceForAttack(defenderCountry);
		/*Rolling dice for attacker and defender.*/
		ArrayList<Integer> aResults = diceRoll(aArmies);
		ArrayList<Integer> dResults = diceRoll(dArmies);
		String s = this.CurrentP+" dice : ";
		for(int i : aResults) {
			s += i +" ";
		}
		s+= "<br>" + defender+" dice: ";
		for(int j : dResults) {
			s += j +" ";
		}
		Notification += "<br>" + s;
		System.out.println(Notification);
		setChanged();
		notifyObservers(Notification);
		battle(dCountry, defender, aCountry, aArmies, dArmies, aResults, dResults);
		setChanged();
		notifyObservers(Notification);
		/*check if defender country has armies left.*/
		if(dCountry.getArmiesCount()==0) {
			dCountry.SetOwner(CurrentP);
			TurnManagment.setWonCard(true);
			/*Notify change in ownership of a country.*/
			Notification += "<br>" + " Country "+ dCountry.getCountryName() +" won by " + dCountry.getOwner().getName() + ", new armies "+dCountry.getArmiesCount();
			setChanged();
			notifyObservers(Notification);
			System.out.println("Country "+ dCountry.getCountryName() +" won by " + dCountry.getOwner().getName() + ", new armies "+dCountry.getArmiesCount());
			/*move countries from attacker country to new acquired country.*/
			int moveArmies = controller.setUpBoxInput(aArmies, aCountry.getArmiesCount()-1, "Select armies to move:");
			dCountry.addArmy(moveArmies);
			aCountry.removeArmies(moveArmies);
			if(map.continentWonByPlayer(CurrentP, dCountry)) {
				CurrentP.addContinent(dCountry.getContinent());
			}
		}
		map.updateMap();
		setPlayerOut(defender);
		checkGameState();
		continuePhase();
	}
	
	/**
	 * This method decides the result of battle between attacking country and defecding country and update the state of countries.
	 * @param dCountry country defending the attack
	 * @param defender player defending the attack
	 * @param aCountry attacking country
	 * @param aArmies number of dice rolled by attacker for battle
	 * @param dArmies number of dice rolled by defender
	 * @param aResults results of the dice rolled by attacker
	 * @param dResults results of dice rolled by defender
	 */
	public void battle(NodeOfCountry dCountry, Player defender, NodeOfCountry aCountry, int aArmies, int dArmies,ArrayList<Integer> aResults,ArrayList<Integer> dResults) {
		/*Compare the results to decide battle result.*/
		while(!aResults.isEmpty() && !dResults.isEmpty()) {
			int aMax = max(aResults);
			int dMax = max(dResults);
			if(aResults.get(aMax)>dResults.get(dMax)) {
				dCountry.removeArmy();
				/*Show army removed from defender country.*/
				Notification += "<br>" + " Winner Country: "+aCountry.getCountryName();
				System.out.println("Army removed from defender country, new armies "+dCountry.getArmiesCount());
			}
			else {
				aCountry.removeArmy();
				Notification += "<br>" + "Winner Country: "+dCountry.getCountryName();
				/*Show army removed from attacker country*/
				System.out.println("Army removed from attacker country, new armies "+aCountry.getArmiesCount());
			}
			aResults.remove(aMax);
			dResults.remove(dMax);
		}
	}
	
	/**
	 * This method declares the game end if all the countries are owned by one player only.
	 * @return true if game if over, false if there is at least two players own at least one country on map
	 */
	public boolean checkGameState() {
		if(players.size()<2) {
			TurnManagment.setGameOver(true);
			return true;
		}
		return false;
	}
	
	/**
	 * remove player from players list, if player has not country.
	 * @param defenderPlayer player to be removed
	 */
	public void setPlayerOut(Player defenderPlayer) {
		if(defenderPlayer.getCountries().isEmpty()) {
			players.remove(defenderPlayer);
		}
	}
	
	/**
	 * delegate method to call setUpBoxInput from controller class.
	 * @param min minimum value user can select 
	 * @param max maximum vlaue user can select
	 * @param message message explaining the purpose of input
	 * @return a number selected by user
	 */
	public int setUpBoxInput(int min, int max, String message) {
		return controller.setUpBoxInput(min, max, message);
	}
	
	/**
	 * Generate random values between 1 and 6 and add them to an arraylist.
	 * @param n number of values to be generated.
	 * @return integer number that represents the value on the dice.
	 */
	public ArrayList<Integer> diceRoll(int n) {
		Random rand = new Random();
		ArrayList<Integer> diceResults = new ArrayList<Integer>();
		for(int i=0;i<n;i++) {
			diceResults.add(rand.nextInt(6) + 1);
		}		
		return diceResults;
	}
	
	/**
	 * This method return maxuimum value in a arraylist.
	 * @param array list from which max value to be searched
	 * @return index of maximum value in list
	 */
	public int max(ArrayList<Integer> array) {
        int n = array.size();
        int max = 0;
        for(int i=1;i<n;i++) {
			if(array.get(i)>array.get(max)) {
				max = i;
			}
		}
        return max;
    }
	
	/**
	 * This method returns the number of countries owned by current player.
	 * @return countries owned by current player
	 */
	public int getCurrentplayerCountryCount(){
		return getCurrentPlayer().getPlayerCountryCount();
	}
	
	/**
	 * 
	 * @return list of all players
	 */
	public ArrayList<Player> getPlayers(){
		return this.players;
	}

	/**
	 * Call Phase View to show game over
	 */
	public void announceGameOver() {
		notifyObservers("GameOver");
		controller.removeAllControls();
	}
	
	/**
	 * If a player wins a territory during a attack, at the end of the attack phase one card 
	 * is removed from pile and given to player.
	 */
	public void issueCard() {
		this.CurrentP.addCard(Cards.remove(0));
	}

	/**
	 * Set current player
	 * @param player1 player to be set as current player
	 */
	public void setCurrentPlayer(Player player1) {
		this.CurrentP = player1;
	}

}