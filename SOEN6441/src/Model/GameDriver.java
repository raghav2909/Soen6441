package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

<<<<<<< HEAD
import controllers.Player_Information_Controller;
import controllers.the_main_controller;
import player.Player;
//import sun.security.provider.DSAKeyPairGenerator.Current;
import view.ControlsConsole;
import view.MapConsole;
import view.PlayerConsole;
import view.openingdialog;
import Model.Map;
=======
import controllers.ControllerForGame;
import controllers.TheMainController;
import player.AActionStrategy;
import player.BActionStrategy;

import player.HActionStrategy;
import player.*;
import player.RActionStrategy;
import player.StrategyOfPlayer;
import view.openingdialog;
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441

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
	
	public boolean saveGame =false;
	
	public String modes;
	
	/**
	 * Object of Player_Information_Controller class.
	 */
	private Player_Information_Controller InfoOfPlayer;
	
	/**
	 * Object of Map class.
	 */
	private Map map;
	/**
	 * opendialog class object
	 */
	private openingdialog opc;
	
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
	
	private openingdialog openDialog;
	
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
<<<<<<< HEAD
	 * Constructor for initializing .
=======
	 * Number of limits for game
	 */
	private int moveLimit = 0;
	
	/**
	 * Counts the number of moves
	 */
	public int moveCounter = 0;
	
	/**
	 * Constructor initialize the GUI and  map class object.
	 * Constructor is private so objects can not be created directly for this class.
	 * @param newMoveLimit Number of moves limited to game
	 * @param newMap url of map game to be played on
	 */
	public GameDriver(String newMap, int newMoveLimit) {
		this();
		moveLimit = newMoveLimit;
		map = new Map(newMap);
		openDialog = new openingdialog();
	}
	
	/**
	 * Constructor initialize the GUI and  map class object.
	 * Constructor is private so objects can not be created directly for this class.
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441
	 */
	public GameDriver() {
<<<<<<< HEAD
		TurnManagment = new GameTurnDriver("Reinforcement");
		Cards = Card.CardGeneration();
=======
		turnManager = new GameTurnDriver("Reinforcement", this);
		cards = Card.cardPileGenerator();
		openDialog = new openingdialog();
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441
	}

	/**
	 * This method is used to access  object of this class.
	 * @return instance of GameDriver class.
	 */
<<<<<<< HEAD
	public static GameDriver GetInit() {
		if(driver==null){
			driver = new GameDriver();
=======
	public void setController(ControllerForGame newController) {
		this.controller = newController;
	}
	
	/**
	 * Starts the game.
	 * @param playerData String array to store elements of player name and type.
	 */
	public void startGame(String[][] playerData) {
		nottifyObservers("Startup phase: ");
		playerCreation(playerData);
		startUpPhase();
		turnManager.startFromReinforcement(this.currentPlayer);
		switchPhase();
		turnManager.playTurn();
	}
	
	/**
	 * Create player objects
	 * @param playerData name of players
	 */
	public void playerCreation(String[][] playerData) {
		players = new ArrayList<Player>();
		for(int i=0; i < playerData.length; i++){
			Player temp = new Player(playerData[i][0],CountArmies.InitialArmiesCount.getArmiesCount(playerData.length), this);
			temp.setMapData(map.getMapData());
			temp.setStrategy(behaviorCreation(playerData[i][1]));
			players.add(temp);
			nottifyObservers("Player created and  added "+temp.getPlayerName());
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441
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
		String[] NewPlayer = controller.InformationOFPlayres();
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
		for(int i=0;i<AllArmies;i++){
			System.out.print("Armies allocated"+players.get(0).getCountArmies());
			for(Player p: players){
				String s;
				if(p.getEmptyCountriesName().length==0){

					s= controller.ArmyPlacing(p.getEmptyCountriesName(),p.getPlayerName()+" Place your army");
				}else{
					s = controller.ArmyPlacing(p.getEmptyCountriesName(), p.getPlayerName()+" Place your army");
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
	public void setPlayerConsole(PlayerConsole nc) {
		this.plc = nc;
	}

	/**

	 * Setting Map console.
	 * @param ng MapConsole object initialized.
	 */
	public void setMapConsole(MapConsole ng) {
		map.addObserver(ng);
	}

	/**
	 * Sets Controls view.
	 * @param controlView ControlsConsole object initialized.
	 */
	public void setControlsConsole(ControlsConsole controlView) {
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
	public void setNextPlayer() {
<<<<<<< HEAD
		int currentPlayerIndex = players.indexOf(GetCurrent());
		this.CurrentP.SetTurnFalse();
		if (currentPlayerIndex == players.size()-1){
			this.CurrentP = players.get(0);
		}else{
			this.CurrentP = players.get(currentPlayerIndex+1);
=======
		int currentPlayerIndex = players.indexOf(getCurrent());
		moveCounter();
		if(!turnManager.isGameOver()) {
			this.currentPlayer.setFalse();
			if (currentPlayerIndex == players.size()-1){
				this.currentPlayer = players.get(0);
			}else{
				this.currentPlayer = players.get(currentPlayerIndex+1);
			}
			this.currentPlayer.setTrue();
			nottifyObservers("Turn changed to "+ this.currentPlayer.getPlayerName());
			this.getCurrent().setArmies(this.getCurrent().getNumberOfArmies());
		}	
		else {
			callGameOver(this.currentPlayer.getPlayerName());
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441
		}
<<<<<<< HEAD
		this.CurrentP.SetTurnTrue();
		
		this.GetCurrent().ArmySet(this.GetCurrent().CalArmy());
		setChanged();
		notifyObservers("Cards");
=======
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441
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
		for(NodeOfCountry C: GetCurrent().getCountries()){
			if(C.getNameOfCountry().equals(c.getNameOfCountry())){
				return C.getNeighboursCountries();
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
<<<<<<< HEAD
	public void ContinuePhase() {
		TurnManagment.runningPhase();
		UpdateMap();
		setChanged();
		notifyObservers(TurnManagment.getPhase());
=======
	public void continuePhase() {
		updateMap();
		turnManager.setContinuePhase();
	}

	/**
	 * Delegate method to call method from GameTurnDriver class to change between phases.
	 * @see #updateMap()
	 */
	public void switchPhase() {
		turnManager.setSwitchPhase();
		updateMap();
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441
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
	 * 
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
	 * Adds the new player to the players arraylist.
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
<<<<<<< HEAD
	public void shiftArmiesOnReinforcement(String SelectedCountry, int Army) {
		if(this.CurrentP.shiftArmiesOnR(SelectedCountry, Army)==0) {
			ChangePhase();
=======
	public void shiftArmiesOnReinforcement(String countrySelected, int armies) {
		if(this.currentPlayer.shiftArmiesOnReinforcement(countrySelected, armies)==0) {
			nottifyObservers(getGameTurnDriver().getPhase());
			switchPhase();
			turnManager.playTurn();
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441
		}
		else {
<<<<<<< HEAD
			
			ContinuePhase();
=======
			nottifyObservers(getGameTurnDriver().getPhase());
			continuePhase();
			turnManager.playTurn();
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441
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
			controller.ControlsForFortification(countrySelect.getArmyCount(), neighborList.toArray(new String[neighborList.size()])); 
		}
	}  
	   
	/**
	 * A delegate method to call getArmiesShiftedAfterFortification in Player class.
	 * @param nc country from where Army are to be moved
	 * @param nn country where Army are to be moved
	 * @param na number of Army to be moved
	 */
	public void getArmiesShiftedAfterFortification(String nc, String nn, int na) {
		this.CurrentP.getArmiesShiftedAfterF(nc, nn, na);
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
			controller.NeighborListUpdate(neighborList.toArray(new String[neighborList.size()]));
		}
	}
	
	/**
	 * This method call the attack, get number of dice from both attacker and def.
	 * @param AttackerCountry country of attacker
	 * @param DefenderCountry country of def
	 */
	public void AttackCall(String AttackerCountry, String DefenderCountry) {
		this.Notification = "Attacker Country: "+AttackerCountry+"  Defender Country: "+DefenderCountry+"  ";
		setChanged();
		notifyObservers(Notification);
		NodeOfCountry DC = map.gettingCountry(DefenderCountry);
		Player def = DC.getOwner();
		NodeOfCountry AC = CurrentP.getCountry(AttackerCountry);
		int AA = this.CurrentP.AttackDice(AttackerCountry);
		int DA = def.AttackDice(DefenderCountry);
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
			TurnManagment.setWonCard(true);
			Notification += "<br>" + " Country "+ DC.getNameOfCountry() +" won by " + DC.getOwner().getPlayerName() + ", new Army "+DC.getArmyCount();
			setChanged();
			notifyObservers(Notification);
			System.out.println("Country "+ DC.getNameOfCountry() +" won by " + DC.getOwner().getPlayerName() + ", new Army "+DC.getArmyCount());
			int moveArmies = InfoOfPlayer.NumberOfPlayer(AA, "Select Army to move:",AC.getArmyCount()-1);
			DC.AddArmy(moveArmies);
			AC.AmriesRemoved(moveArmies);
			if(map.WonPlayerContinent(CurrentP, DC)) {
				CurrentP.AddContinent(DC.getContinent());
			}
		}
<<<<<<< HEAD
		map.UpdateMap();
		setPlayerOutOfGame(def);
		GameStateCheck();
		ContinuePhase();
=======
		map.updateMap();
		setPlayerOut(defender);
		if(!checkStateOfGame()) {
			continuePhase();
			turnManager.playTurn();
		}
		else {
			callGameOver(players.get(0).getPlayerName());
		}
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441
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
				DC.deleteArmy();
				Notification += "<br>" + " Winner Country: "+AC.getNameOfCountry();
				System.out.println("Army removed from defender country, new Army "+DC.getArmyCount());
			}
			else {
				AC.deleteArmy();
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
			TurnManagment.setGameOver(true);
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
	 * @param minimum  value user can select 
	 * @param Maximum  value user can select
	 * @param message  explaining the purpose 
	 * @return a number selected by user
	 */
	public int InputSetUp(int minimum, String message, int Maximum) {
		return controller.input(minimum,message, Maximum);
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
		return GetCurrent().getPlayerCountryNumber();
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
<<<<<<< HEAD
	public void CallGameOver() {
		notifyObservers("GameOver");
		controller.AllControlsOver();
=======
	public void callGameOver(String winner) {
		nottifyObservers("GameOver");
		controller.removeAllControls();
		System.out.print("Thw winner is :"+winner);
		TheMainController.getInitialize().notifyResultOfGame(winner);
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441
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
	/**
	 * <p>
	 * This method create <b>one and only one</b> instance of GameDriver class.
	 * This method is used to access only object of this class.
	 * </p>
	 * @return instance of GameDriver class.
	 */

<<<<<<< HEAD
}
=======
	/**
	 * @return place army dialog
	 * @param countries country list to be displayed for choice.
	 * @param string message for the dialogbox.
	 */
	public Object placeArmyDialog(String[] countries, String string) {
		return controller.placeArmyDialog(countries, string);
	}

	/**
	 * control reinforcements
	 * @param countryList Country list to be displayed.
	 * @param armies Armies assigned to the player.
	 */
	public void reinforcementControls(int armies, String[] countryList) {
		controller.setReinforcementControls(armies, countryList);
	}

	/**
	 * attack controls.
	 * @param array Country list to be displayed.
	 */
	public void attackControls(String[] array) {
		controller.setAttackControls(array);
	}

	/**
	 * controls fortification.
	 * @param array Country list to be displayed.
	 */
	public void fortificationControls(String[] array) {
		controller.setFortificationControls(array);
	}
	
	private boolean moveCounter() {
		if(moveLimit!=0) {
			if(moveCounter==moveLimit) {
				turnManager.setGameOver(true);
				callGameOver("draw");
				return false;
			}
			else {
				moveCounter++;
			}
		}
		return true;
	}
	
	/**
	 * @return turnManager
	 */
	public GameTurnDriver getGameTurnDriver() {
		return this.turnManager;
	}
	
	/**
	 * observer pattern.
	 * @param msg Message to be displayed on game logger.
	 */
	public void nottifyObservers(String msg) {
		setChanged();
		notifyObservers(msg);
	}
	
	
	public void saveGameToFile() {   
		
	    try {
	    	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    	File file = new File("SaveGame"+ timeStamp+".sav");
	        FileOutputStream fileStream = new FileOutputStream(file);   
	        ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);   
	        
	        /*Map file path.*/
	        objectStream.writeObject(Single.getNameOfMap());
	        
	        /*Number of players.*/
	        objectStream.writeObject(players.size());
	        
	        /*Player data.*/
	        for(Player player: this.players){
		        objectStream.writeObject(player.getPlayerName());
		        objectStream.writeObject(player.getStrategyOfPlayer());
		        objectStream.writeObject(player.getCountries().size());
		        for(NodeOfCountry country: player.getCountries()){
		        	objectStream.writeObject(country.getNameOfCountry());
		        	objectStream.writeObject(country.getConutOfArmies());
		        }
	        }
	        
	        /*Current player.*/
	        objectStream.writeObject(getCurrent().getPlayerName());
	        
	        /*Current phase.*/
	        objectStream.writeObject(turnManager.getPhase()+"\n");
	        
	        objectStream.close();   
	        fileStream.close(); 
	        saveGame = true;
	        System.out.println("Game saved successfully");
	    
	    }catch(Exception e) {   
	        System.out.println("Failed to save game state. "+e);   
	    }   
	}
	
	public String getOpen() {
		modes = openDialog.setMode();
		return modes;
	}
	
	public openingdialog setOpen() {
		return this.openDialog;
	}
	
	public boolean saveG() {
		return this.saveGame;
	}

}
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441
