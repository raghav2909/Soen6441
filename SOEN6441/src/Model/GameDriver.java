

package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Random;

import controllers.ControllerForGame;
import controllers.TheMainController;
import player.AActionStrategy;
import player.BActionStrategy;

import player.HActionStrategy;
import player.*;
import player.RActionStrategy;
import player.StrategyOfPlayer;
import view.openingdialog;

/**
 * This class controls the turns - Startup phase, Fortification, reinforcement and attack phase.
 * @author samansoltani
 * @author raghavsharda
 * @version 3.0
 */
public class GameDriver extends Observable {
	
	/**
	 * map variable to store reference of class Map
	 */
	private Map map;
	
	public String modes;
	
	/**
	 * ArrayList to store elements of player type.
	 */
	private ArrayList<Player> players;
	
	/**
	 * controller variable to store reference of class ControllerForGame
	 */
	private ControllerForGame controller;
		
	/**
	 * turnManager variable to store reference of class GameTurnDriver
	 */
	private GameTurnDriver turnManager;
	
	private openingdialog openDialog;
	
	/**
	 * currentPlayer variable to store reference of class Player
	 */
	private Player currentPlayer;

	/**
	 * List of cards
	 */
	private ArrayList<Card> cards;
	
	/**
	 * Observer notification string.
	 */
	private String resultNotify;
	
	/**
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
	 */
	public GameDriver() {
		turnManager = new GameTurnDriver("Reinforcement", this);
		cards = Card.cardPileGenerator();
		openDialog = new openingdialog();
	}

	/**
	 * Set controller in GameDriver class.
	 * @param newController Used to set the Controller object.
	 */
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
		}
	}
	
	/**
	 * Create PlayerStartegy object from string
	 * @param strategy strategy for which object is required
	 * @return object of StrategyOfPlayer
	 */
	public StrategyOfPlayer behaviorCreation(String strategy) {
			StrategyOfPlayer pStrategy = null;
			if(strategy.equals("human")){
				pStrategy = new HActionStrategy(this);
			}
			else if(strategy.equals("benevolent")) {
				pStrategy = new BActionStrategy(this);
			}
			else if(strategy.equals("aggressive")){
				pStrategy = new AActionStrategy(this);
			}
			else if(strategy.equals("cheater")) {
				pStrategy = new CActionStrategy(this);
			}
			else if(strategy.equals("random")) {
				pStrategy = new RActionStrategy(this);
			}
			return pStrategy;
	}

	/**
	 * This method starts the startup phase of game. It assigns countries to players.
	 * @see #updateMap()
	 */
	public void startUpPhase() {
		allocatingCountries(map.getMapData());
		updatePlayerConsole();
		/*Distribute armies to countries as per player's choice.*/
		int totalArmiesDiv = players.get(0).getCountOfArmies();
		for(int i1=0;i1<totalArmiesDiv ;i1++){
			for(Player p: players){
				String s = p.placeArmy();
				p.getCountry(s).addArmy(1);
				nottifyObservers(p.getPlayerName()+" placed 1 army on "+s);
				p.removeArmies(1);
			}
		}
		updateMap();
	}
	
	/**
	 * This method create player objects and divide countries among them.
	 * @see notifyObservers
	 * @param mapData arraylist containing NodeOfMap Objects representing continents
	 */
	public void allocatingCountries(ArrayList<NodeOfMap> mapData) {
		players.get(0).setTrue();
		this.currentPlayer = players.get(0);
		nottifyObservers("Player "+players.get(0)+" has first turn");
		int i = 0;
		/*Random distribution of countries among the players.*/
		for(NodeOfMap m : mapData){
			for(NodeOfCountry c: m.getCountries()){
				c.setOwner(players.get(i));
				if(++i>=players.size()){
					i=0;
				}
			}
		}
		nottifyObservers("Countries divided to players");
	}
	
	/**
	 * This method show players information on GUI.
	 */
	public void updatePlayerConsole() {
		String[] playerNames = new String[players.size()];
		int i=0;
		for(Player p: players){
			playerNames[i] = p.getPlayerName();
			i++;
		}
	}

	/**
	 * Gets the player with the current turn.
	 * @return current player 
	 */
	public Player getCurrent() {
		return this.currentPlayer;
	}

	/**
	 * Sets the next player's turn.
	 */
	public void setNextPlayer() {
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
		}
	}
	
	/**
	 * Gives the list of the neighbors of the country passed as a parameter.
	 * @param countryname Name of the country.
	 * @return Neighbors of the country.
	 */
	public String [] getNameOfNeighbors(String countryname) {
		for(NodeOfCountry country: getCurrent().getCountries()){
			if(country.getNameOfCountry().equals(countryname)){
				return country.getNeighbourCountriesString();
			}
		}
		return null;
	}

	/**
	 * Gets the army count of the current player.
	 * @return army count of the current player.
	 */
	public int getArmiesOfPlayer() {
		return getCurrent().getCountOfArmies();
	}

	/**
	 * Gives the countries owned by a player.
	 * @return The list of country nodes.
	 */
	public ArrayList<NodeOfCountry> getCountriesOfPlayer() {
		return getCurrent().getCountries();
	}

	/**
	 * Gives the neighbors of a particular country.
	 * @param countrynode Country whose neighbors are to be fetched.
	 * @return list of neighbor countries.
	 */
	public NodeOfCountry [] getNeighbourCountries(NodeOfCountry countrynode) {
		for(NodeOfCountry country: getCurrent().getCountries()){
			if(country.getNameOfCountry().equals(countrynode.getNameOfCountry())){
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
		return this.currentPlayer.getCountry(countryname);
	}
	
	/**
	 * Sets action listener for reinforcement phase.
	 */
	public void setControlsActionListeners() {
		this.controller.setActionListner();
	}

	/**
	 * Delegate method to call method from GameTurnDriver class to continue phases.
	 * @see #updateMap()
	 */
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
	}
	
	/**
	 * Delegate method to call updateMap method from map class.
	 * @see #updateMap()
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
	public void setListOfPlayer(Player newPlayer){
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
		if(this.currentPlayer.shiftArmiesOnReinforcement(countrySelected, armies)==0) {
			nottifyObservers(getGameTurnDriver().getPhase());
			switchPhase();
			turnManager.playTurn();
		}
		else {
			nottifyObservers(getGameTurnDriver().getPhase());
			continuePhase();
			turnManager.playTurn();
		}
	}
	
	/**
	 * This method get list of neighbor countries of the specified country owned by same player from map class
	 * and update the controls view through controller.
	 * @param countrySelected the country whose neighbors are to be listed
	 */
	public void fortificationNeighbourListUpdate(String countrySelected) {
		NodeOfCountry countrySelect = this.currentPlayer.getCountry(countrySelected);
		if(countrySelect.getConutOfArmies()>1) {
			ArrayList<String> neighborList = map.getPlayerNeighbourCountries(countrySelect,this.currentPlayer,true);
			controller.updateControlsFortification(countrySelect.getConutOfArmies(), neighborList.toArray(new String[neighborList.size()])); 
		}
	}
	
	/**
	 * A delegate method to call getArmiesShiftedAfterFortification in Player class.
	 * @param newCountry country from where armies are to be moved
	 * @param newNeighbour country where armies are to be moved
	 * @param newArmies number of armies to be moved
	 */
	public void getArmiesShiftedAfterFortification(String newCountry, String newNeighbour, int newArmies) {
		this.currentPlayer.getArmiesShiftedAfterFortification(newCountry, newNeighbour, newArmies);
	}
	
	/**
	 * A delegate method to call setAttackListeners in Controller class
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
		NodeOfCountry countrySelect = this.currentPlayer.getCountry(countrySelected);
		if(countrySelect.getConutOfArmies()>1) {
			ArrayList<String> neighborList = map.getPlayerNeighbourCountries(countrySelect,this.currentPlayer,false);
			controller.updateNeighborList(neighborList.toArray(new String[neighborList.size()]));
		}
	}
	
	/**
	 * This method announce the attack, get number of dice from both attacker and defender. If a country loose all its armies, the other player occupy the country.
	 * @param attackerCountry country attacking
	 * @param defenderCountry country defending against attack
	 */
	public void declareAttack(String attackerCountry, String defenderCountry) {
		nottifyObservers("Attack announced Attacker Country: "+attackerCountry+"  Defender Country: "+defenderCountry);
		/*Announce attack on phase view.*/
		NodeOfCountry dCountry = map.getCountry(defenderCountry);
		Player defender = dCountry.getOwner();
		NodeOfCountry aCountry = currentPlayer.getCountry(attackerCountry);
		/*Show dialog boxes and get input from attacker and defender on how many dice to roll.*/
		int aArmies = this.currentPlayer.selectDice(attackerCountry);
		int dArmies = defender.selectDice(defenderCountry);
		/*Rolling dice for attacker and defender.*/
		ArrayList<Integer> aResults = diceRoll(aArmies);
		ArrayList<Integer> dResults = diceRoll(dArmies);
		String s = this.currentPlayer+" dice : ";
		for(int i : aResults) {
			s += i +", ";
		}
		s+= defender+" dice: ";
		for(int j : dResults) {
			s += j +" ";
		}
		nottifyObservers(s);
		battle(dCountry, defender, aCountry, aArmies, dArmies, aResults, dResults);
		nottifyObservers("Armies left in attacker Country "+ aCountry.getNameOfCountry()+" "+aCountry.getConutOfArmies());
		nottifyObservers("Armies left in defender Country "+ dCountry.getNameOfCountry()+" "+dCountry.getConutOfArmies());
		/*check if defender country has armies left.*/
		if(dCountry.getConutOfArmies()==0) {
			dCountry.setOwner(currentPlayer);
			turnManager.setWonCard(true);
			/*Notify change in ownership of a country.*/
			nottifyObservers("Country "+ dCountry.getNameOfCountry() +" won by " + dCountry.getOwner().getPlayerName() + ", new armies "+dCountry.getConutOfArmies());
			/*move countries from attacker country to new acquired country.*/
			int moveArmies = currentPlayer.moveArmies(aArmies, aCountry.getConutOfArmies()-1, "Select armies to move:");
			dCountry.addArmy(moveArmies);
			aCountry.removedArmies(moveArmies);
			if(map.continentPlayerWon(currentPlayer, dCountry)) {
				nottifyObservers("Player "+ currentPlayer.getPlayerName() +" conquered " + dCountry.getContinent());
				currentPlayer.addContinent(dCountry.getContinent());
			}
		}
		map.updateMap();
		setPlayerOut(defender);
		if(!checkStateOfGame()) {
			continuePhase();
			turnManager.playTurn();
		}
		else {
			callGameOver(players.get(0).getPlayerName());
		}
	}
	
	/**
	 * This method decides the result of battle between attacking country and defending country and update the state of countries.
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
		int i=1;
		while(!aResults.isEmpty() && !dResults.isEmpty()) {
			int aMax = max(aResults);
			int dMax = max(dResults);
			if(aResults.get(aMax)>dResults.get(dMax)) {
				dCountry.removedArmy();
				/*Show army removed from defender country.*/
				nottifyObservers("Battle "+i+" result : Winner Country: "+aCountry.getNameOfCountry()+" Army removed from "+ dCountry.getNameOfCountry());
			}
			else {
				aCountry.removedArmy();
				nottifyObservers("Battle result : Winner Country: "+dCountry.getNameOfCountry()+" Army removed from "+ aCountry.getNameOfCountry());
				
			}
			aResults.remove(aMax);
			dResults.remove(dMax);
			i++;
		}
	}
	
	/**
	 * This method declares the game end if all the countries are owned by one player only.
	 * @return true if game if over, false if there is at least two players own at least one country on map
	 */
	public boolean checkStateOfGame() {
		if(players.size()<2) {
			turnManager.setGameOver(true);
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
	 * @param max maximum value user can select
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
	 * This method return maximum value in a arraylist.
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
	public int getCountOfCurrentCountries(){
		return getCurrent().getCountPlayerCountries();
	}
	
	/**
	 * 
	 * @return list of all players
	 */
	public ArrayList<Player> getPlayers(){
		return this.players;
	}

	/**
	 * Call Phase View to show game over.
	 * @param winner Name of the winner or Draw if no winner.
	 */
	public void callGameOver(String winner) {
		nottifyObservers("GameOver");
		controller.removeAllControls();
		System.out.print("Thw winner is :"+winner);
		TheMainController.getInitialize().notifyResultOfGame(winner);
	}
	
	/**
	 * If a player wins a territory during a attack, at the end of the attack phase one card 
	 * is removed from pile and given to player.
	 */
	public void cardIssued() {
		this.currentPlayer.addCard(cards.remove(0));
	}

	/**
	 * Set current player
	 * @param player1 player to be set as current player
	 */
	public void setCurrent(Player player1) {
		this.currentPlayer = player1;
	}

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

}
