package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

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
 * @version 2.0
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
	 * Object of GameTurnDriver class.
	 */
	private GameTurnDriver TurnManagment;
	
	/**
	 * Object of Player class.
	 */
	private Player CurrentP;

	/**
	 * Pack of Cards
	 */
	private ArrayList<Card> Cards;
	
	
	/**
	 * Object for playerConsole 
	 */
	private PlayerConsole plc;
	/**
	 * Observer notification string.
	 */
	private String Notification;
	
	/**
	 * Constructor for initializing .
	 */
	public GameDriver() {
		TurnManagment = new GameTurnDriver("Reinforcement");
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
		TurnManagment.StartPlayerTurn(this.CurrentP);
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
					s = InfoOfPlayer.ArmyPlacing(p.getEmptyCountriesName(), p.getPlayerName()+" Place your army");
				}else{
					s= InfoOfPlayer.ArmyPlacing(p.getEmptyCountriesName(),p.getPlayerName()+" Place your army");
				}
				p.getCountry(s).AddArmy(1);
				p.RemovedArmies(1);
			}
		}
		UpdateMap();
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

	 * Setting Map console.
	 * @param ng MapConsole object initialized.
	 */
	public void setMapView(MapConsole ng) {
		map.addObserver(ng);
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
		plc.setPlayerData(playerNames);
	}

	/**
	 * Gets the current player.
	 * @return current player 
	 */
	public Player GetCurrent() {
		return this.CurrentP;
	}

	/**
	 * Sets the next player's turn.
	 */
	public void setNextPlayerTurn() {
		int currentPlayerIndex = players.indexOf(GetCurrent());
		this.CurrentP.SetTurnFalse();
		if (currentPlayerIndex == players.size()-1){
			this.CurrentP = players.get(0);
		}else{
			this.CurrentP = players.get(currentPlayerIndex+1);
		}
		this.CurrentP.SetTurnTrue();
		this.GetCurrent().AddedArmies(this.GetCurrent().getCountArmies());
		setChanged();
		notifyObservers("Cards");
	}


	
	
	public void NextPlayer() {
		int CurrentPlayer = Player.indexOf(GetCurrent());
		GetCurrent().SetTurnFalse();
		if (CurrentPlayer == Player.size() - 1) {
			Player.get(0).SetTurnTrue();
		} else {
			Player.get(CurrentPlayer + 1).SetTurnTrue();
		}
	}

	/**
	 * getting object of map class
	 * @param Path save the map path
	 * @throws IOException exception for no path found
	 */
	public void MapCreation(String Path) throws IOException {
		
		map = new Map(Path);
	}

	/**
	 * show the country neighbours as a parameter
	 * 
	 * @param cn name of the country
	 * @return a list containing of country neighbours
	 */
	public String[] getNeighboursNames(String cn) {
		for (NodeOfCountry c : GetCurrent().getCountries()) {
			if (c.getNameOfCountry().equals(cn)) {
				return c.getNeighboursString();
			}
		}
		return null;
	}

	/**
	 * Gets the army count of the current player.
	 * @return army count of the current player.
	 */
	public int getPlayerArmies() {
		return GetCurrent().getCountArmies();
	}

	/**
	 * Gives the countries owned by a player.
	 * @return The list of country nodes.
	 */
	public ArrayList<NodeOfCountry> getPlayerCountries() {
		return GetCurrent().getCountries();
	}

	/**
	 * Gives the neighbors of a particular country.
	 * @param c Country whose neighbors are to be fetched.
	 * @return list of neighbor countries.
	 */
	public NodeOfCountry [] getNeighbourCountries(NodeOfCountry c) {
		for(NodeOfCountry country: GetCurrent().getCountries()){
			if(country.getNameOfCountry().equals(c.getNameOfCountry())){
				return country.getNeighboursCountries();
			}
		}
		return null;
	}
	
	/**
	 * Gives the country node of the given country name.
	 * @param cn name of a country
	 * @return country node for the given country name
	 */
	public NodeOfCountry getCountry(String cn) {
		return this.CurrentP.getCountry(cn);
	}
	
	/**
	 * Sets action listener for reinforcement phase.
	 */
	public void SetActionListeners() {
		this.controller.setActionListner();
	}
	
	/**
	 * Gives the instance of ControlsConsole class.
	 * @return ControlsConsole class object.
	 */
	public ControlsConsole GetControle() {
		return this.Controls;
	}

	/**
	 * Delegate method to call method from GameTurnDriver class to continue phases.
	 */
	public void ContinuePhase() {
		TurnManagment.runningPhase();
		UpdateMap();
		setChanged();
		notifyObservers(TurnManagment.getPhase());
	}
	
	/**
	 *  method to call method from GameTurnDriver class to change between phases.
	 */
	public void ChangePhase() {
		TurnManagment.switchphase();;
		UpdateMap();
		setChanged();
		notifyObservers(TurnManagment.getPhase());
	}
	
	/**
	 *  method to call UpdateMap method from map class.
	 */
	public void UpdateMap() {
		map.UpdateMap();
	}
	
	/**
	 * Adds listener for fortification phase.
	 * getting the map class object
	 * @return map Map object is returned
	 */
	public void SetFLiteners() {
		this.controller.setListenersFortification();;
	}
	
	/**
	 * Returns object of Map class
	 *  @return map 
	 */
	public Map GetMap(){
		return this.map;
	}
	
	/**
	 * Adds the new player to the arraylist of players.
	 * @param np Player object.
	 */
	public void ListOfPlayers(Player np){
		if(this.players==null) {
			this.players = new ArrayList<Player>();
		}
		this.players.add(np);
	}

	/**
	 * This method call the shiftArmiesOnReinforcement method from player class
	 * @param SelectedCountry Country where Army should be placed
	 * @param Army number of Army to be placed
	 */
	public void shiftArmiesOnReinforcement(String SelectedCountry, int Army) {
		if(this.CurrentP.shiftArmiesOnReinforcement(SelectedCountry, Army)==0) {
			ChangePhase();
		}
		else {
			ContinuePhase();
		}
	}
	
	/**
	 * This method get list of neighbor countries of the specified country owned by same player.
	 * @param SelectedCountry the country whose neighbors are to be listed
	 */
	public void UpdateFNeighbour(String SelectedCountry) {
		NodeOfCountry countrySelect = this.CurrentP.getCountry(SelectedCountry);
		if(countrySelect.getArmyCount()>1) {
			ArrayList<String> neighborList = map.getPlayerNeighbours(countrySelect,this.CurrentP,true);
			Controls.updateFortification(countrySelect.getArmyCount(), neighborList.toArray(new String[neighborList.size()])); 
		}
	}  
	   
	/**
	 * A delegate method to call getArmiesShiftedAfterFortification in Player class.
	 * @param newCountry country from where Army are to be moved
	 * @param newNeighbour country where Army are to be moved
	 * @param newArmies number of Army to be moved
	 */
	public void getArmiesShiftedAfterFortification(String newCountry, String newNeighbour, int newArmies) {
		this.CurrentP.getArmiesShiftedAfterFortification(newCountry, newNeighbour, newArmies);
	}
	
	/**
	 * A delegate method to call setAttackListeners in the_main_controller class
	 */
	public void setAttackListeners() {
		controller.ListenerForAttackPhase();
	}
	
	/**
	 * This method create a list of neighbour countries for a selected country whith different owners than the current player.
	 * Then update list on the controls view through controller.
	 * @param SelectedCountry selected country whose neighbors are required.
	 */
	public void attackNeighbourListUpdate(String SelectedCountry) {
		NodeOfCountry countrySelect = this.CurrentP.getCountry(SelectedCountry);
		if(countrySelect.getArmyCount()>1) {
			ArrayList<String> neighborList = map.getPlayerNeighbours(countrySelect,this.CurrentP,false);
			Controls.SetNList(neighborList.toArray(new String[neighborList.size()]));
		}
	}
	
	/**
	 * This method call the attack, get number of dice from both attacker and def.
	 * @param AttackerC country of attacker
	 * @param DefenderC country of def
	 */
	public void AttackCall(String AttackerC, String DefenderC) {
		this.Notification = "Attacker Country: "+AttackerC+"  Defender Country: "+DefenderC+"  ";
		setChanged();
		notifyObservers(Notification);
		NodeOfCountry DC = map.getCountry(DefenderC);
		Player def = DC.getOwner();
		NodeOfCountry AC = CurrentP.getCountry(AttackerC);
		int AA = this.CurrentP.AttackDice(AttackerC);
		int DA = def.AttackDice(DefenderC);
		ArrayList<Integer> AR = DiceRoll(AA);
		ArrayList<Integer> DR = DiceRoll(DA);
		String s = this.CurrentP+" dice : ";
		for(int i : AR) {
			s += i +" ";
		}
		s+= "<br>" + def+" dice: ";
		for(int j : DR) {
			s += j +" ";
		}
		Notification += "<br>" + s;
		System.out.println(Notification);
		setChanged();
		notifyObservers(Notification);
		Fight(DC, def, AC, AA, DA, AR, DR);
		setChanged();
		notifyObservers(Notification);
		if(DC.getArmyCount()==0) {
			DC.SetOwner(CurrentP);
			TurnManagment.cardwinset(true);
			Notification += "<br>" + " Country "+ DC.getNameOfCountry() +" won by " + DC.getOwner().getPlayerName() + ", new Army "+DC.getArmyCount();
			setChanged();
			notifyObservers(Notification);
			System.out.println("Country "+ DC.getNameOfCountry() +" won by " + DC.getOwner().getPlayerName() + ", new Army "+DC.getArmyCount());
			int moveArmies = controller.InputSetUp(AA, AC.getArmyCount()-1, "Select Army to move:");
			DC.AddArmy(moveArmies);
			AC.RemoveArmies(moveArmies);
			if(map.WonContinentPlayer(CurrentP, DC)) {
				CurrentP.AddContinent(DC.getContinent());
			}
		}
		map.UpdateMap();
		setPlayerOutOfGame(def);
		GameStateCheck();
		ContinuePhase();
	}
	
	/**
	 * this method contains attack results and update the countries.
	 * @param DC country defending the attack
	 * @param def player defending the attack
	 * @param AC attacking country
	 * @param AA number of dice rolled by attacker for Fight
	 * @param DA number of dice rolled by def
	 * @param AR results of the dice rolled by attacker
	 * @param DR results of dice rolled by def
	 */
	public void Fight(NodeOfCountry DC, Player def, NodeOfCountry AC, int AA, int DA,ArrayList<Integer> AR,ArrayList<Integer> DR) {
		while(!AR.isEmpty() && !DR.isEmpty()) {
			int AM = Maximum(AR);
			int DM = Maximum(DR);
			if(AR.get(AM)>DR.get(DM)) {
				DC.removeArmy();
				Notification += "<br>" + " Winner Country: "+AC.getNameOfCountry();
				System.out.println("Army removed from defender country, new Army "+DC.getArmyCount());
			}
			else {
				AC.removeArmy();
				Notification += "<br>" + "Winner Country: "+DC.getNameOfCountry();
				System.out.println("Army removed from attacker country, new Army "+AC.getArmyCount());
			}
			AR.remove(AM);
			DR.remove(DM);
		}
	}
	
	/**
	 * This method show the winner if a player own all countries.
	 * @return true if game if over
	 */
	public boolean GameStateCheck() {
		if(players.size()<2) {
			TurnManagment.setGameStatus(true);
			return true;
		}
		return false;
	}
	
	/**
	 * player with no country removed by this class.
	 * @param DPlayer player to be removed
	 */
	public void setPlayerOutOfGame(Player DPlayer) {
		if(DPlayer.getCountries().isEmpty()) {
			players.remove(DPlayer);
		}
	}
	
	/**
	 *  method to call InputSetUp from controller class.
	 * @param min  value user can select 
	 * @param Maximum  value user can select
	 * @param message  explaining the purpose 
	 * @return a number selected by user
	 */
	public int InputSetUp(int minimum, String message, int Maximum) {
		return InfoOfPlayer.NumberOfPlayer(minimum,message, Maximum);
	}
	
	/**
	 *  random values between 1 and 6 
	 * @param n number of values to be generated.
	 * @return  number that shows the value on the dice.
	 */
	public ArrayList<Integer> DiceRoll(int n) {
		Random random = new Random();
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i=0;i<n;i++) {
			result.add(random.nextInt(6) + 1);
		}		
		return result;
	}
	
	/**
	 * This method return maximum value 
	 * @param a list from which Maximum value to be found
	 * @return  maximum value in list
	 */
	public int Maximum(ArrayList<Integer> a) {
        int n = a.size();
        int Maximum = 0;
        for(int i=1;i<n;i++) {
			if(a.get(i)>a.get(Maximum)) {
				Maximum = i;
			}
		}
        return Maximum;
    }
	
	/**
	 * This method returns the number of countries owned by current player.
	 * @return countries owned by current player
	 */
	public int getNumberOfCountryOwnedByCurrentPlayer(){
		return GetCurrent().getPlayerCountryCount();
	}
	
	/**
	 * 
	 * @return list of all players
	 */
	public ArrayList<Player> getAllPlayers(){
		return this.players;
	}

	/**
	 * Call game over
	 */
	public void CallGameOver() {
		notifyObservers("GameOver");
		controller.AllControlsOver();
	}
	
	/**
	 * getting a card to player who won a attack.
	 */
	public void GivenCard() {
		this.CurrentP.AddCard(Cards.remove(0));
	}

	/**
	 * Set current player
	 * @param p player to be current player
	 */
	public void setCurrentPlayer(Player p) {
		this.CurrentP = p;
	}

}