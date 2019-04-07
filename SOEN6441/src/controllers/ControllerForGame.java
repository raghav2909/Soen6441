
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import Model.*;
import view.*;
import Util.*;
import player.*;

import view.CardsConsole;
import view.ControlsConsole;
import view.MapConsole;
import view.PhaseConsole;
import view.PlayerConsole;
import view.openingdialog;


/**
 * MVC - Controller that controls the interaction between models and view.
 * @author raghavsharda
 */
public class ControllerForGame {
	
	/**
	 * driver variable storing the reference of the class GameDriver.
	 */
	private GameDriver driver;

	/**
	 * phaseView variable storing the reference of the class PhaseView.
	 */
	private PhaseConsole phaseView;
	
	/**
	 * dominationView variable storing the reference of the class WorldDominationView.
	 */
	private DominationConsole dominationView;
	
	/**
	 * cardsGUI variable storing the reference of the class CardsView.
	 */
	private CardsConsole cardsGUI;
	
	/**
	 * controlsGUI variable storing the reference of the class ControlsView.
	 */
	private ControlsConsole controlsGUI;
	
	/**
	 * mapGUI variable storing the reference of the class MapView.
	 */
	private MapConsole mapGUI;
	
	/**
	 * playerInfoGUI variable storing the reference of the class PlayerInfoView.
	 */
	private PlayerConsole playerInfoGUI;
	
	/**
	 * setupBox variable storing the reference of the class openingdialog.
	 */
	private openingdialog setupBox;
	
	/**
	 * ActionListener to add listener to "Add Armies" button.
	 */
	private ActionListener addArmiesListner;
	
	/**
	 * ActionListener to add listener to "Save Game" button.
	 */
	private ActionListener saveGameListener;
	
	/**
	 * gameLogger variable storing the instance of the class GameLogger.
	 */
	private GameLogging gameLogger;
	
	/**
	 * Constructor for object creation
	 * @param newSetupBox openingdialog object
	 */
	public ControllerForGame(openingdialog newSetupBox){
		this(new GameDriver(), newSetupBox);
	}
	
	/**
	 * Controller class constructor to initialize GameDriver and openingdialog class objects.
	 * @param newDriver GameDriver instance.
	 * @param newSetupBox openingdialog object
	 */
	public ControllerForGame(GameDriver newDriver, openingdialog newSetupBox) {
		this.setupBox = newSetupBox;
		this.driver = newDriver;
		driver.setController(this);
		init();
	}
	
	/**
	 * Controller class constructor to initialize GameDriver and openingdialog class objects.
	 * @param newMap Map file path.
	 * @param newMapImage Map file image path.
	 * @param moveLimit number of turns
	 * @param playerNames names of the players.
	 */
	public ControllerForGame(String newMap, String newMapImage, String[][] playerNames, int moveLimit) {
		mapGUI = new MapConsole(newMapImage);
		setupBox = new openingdialog();
		driver = new GameDriver(newMap, moveLimit);
		driver.setController(this);
		playerInfoGUI = new PlayerConsole();
		playerInfoGUI.setPlayerInfo(playerNames);
		init();
		driver.runGame(playerNames);
	}
	
	/**
	 * Controller class constructor to initialize GameDriver and openingdialog class objects.
	 * @param newMap Map file path.
	 * @param moveLimit number of turns
	 * @param playerNames names of the players.
	 */
	public ControllerForGame(String newMap, String[][] playerNames, int moveLimit) {
		mapGUI = new MapConsole();
		setupBox = new openingdialog();
		driver = new GameDriver(newMap, moveLimit);
		driver.setController(this);
		playerInfoGUI = new PlayerConsole();
		playerInfoGUI.setPlayerInfo(playerNames);
		init();
		driver.runGame(playerNames);
	}
	
	/**
	 * Controller constructor to load the saved game state.
	 * @param newMap map file path
	 * @param currentPlayer current player of the game
	 * @param armyCountList army count for each country
	 * @param countryList country list of each player
	 * @param players game players
	 * @param phaseName name of the phase ongoing.
	 */
	public ControllerForGame(String newMap, String[][] players, ArrayList<ArrayList<String>> countryList, ArrayList<ArrayList<Integer>> armyCountList, String currentPlayer, String phaseName){
		mapGUI = new MapConsole();
		setupBox = new openingdialog();
		driver = new GameDriver(newMap, 0);
		driver.setController(this);
		playerInfoGUI = new PlayerConsole();
		int i = 0;
		
		for (ArrayList<String> countrylist: countryList){
			int j=0;
			ArrayList<NodeOfCountry> list = new ArrayList<NodeOfCountry>();
			for(String country: countrylist){
				NodeOfCountry cn = driver.getMap().getCountry(country);
				cn.setArmies(armyCountList.get(i).get(j));
				list.add(cn);
				j++;
			}
			Player player = new Player(players[i][0], 0, list, driver);
			player.setStrategy(driver.createBehavior(players[i][1]));
			if(player.getName().equals(currentPlayer)){
				driver.setCurrentPlayer(player);
			}
			driver.setPlayerList(player);
			i++;
		}

		playerInfoGUI.setPlayerInfo(players);
		init();
		driver.getGameTurnDriver().setPhase(phaseName);
		if(phaseName.trim().equals("Reinforcement")){
			driver.getCurrentPlayer().assignArmies(driver.getCurrentPlayer().getArmies());
		}
		driver.continuePhase();
	}
	
	/**
	 * Initializes the game after Play Game button selection.
	 */
	public void init() {
		/*Initialize all the views for the main window and run game.*/
        cardsGUI = new CardsConsole();
        controlsGUI = new ControlsConsole();
        phaseView = new PhaseConsole();
        dominationView = new DominationConsole();
        gameLogger = new GameLogging();
        GameConsole.createInstance(playerInfoGUI, mapGUI, controlsGUI, phaseView, dominationView);
		driver.addObserver(phaseView);
		driver.addObserver(dominationView);
		driver.addObserver(cardsGUI);
		driver.addObserver(gameLogger);
		driver.getMap().addObserver(mapGUI);
		this.setSaveGameListener();
	}
	
	/**
	 * Calls the placeArmyDialog function of openingdialog class.
	 * @param countriesNamesNoArmy list of countries with no armies. 
	 * @param message message explaining the purpose of input.
	 * @return the country selected by the user to place army.
	 */
	public String placeArmyDialog(String[] countriesNamesNoArmy, String message) {
		return setupBox.placeArmyDialog(countriesNamesNoArmy, message);
	}
	
	/**
	 * Sets Action Listeners for reinforcement controls.
	 */
	public void setActionListner() {
		addArmiesListner = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int armies = controlsGUI.getArmiesValue();
				driver.shiftArmiesOnReinforcement(controlsGUI.getCountrySelected(), armies);
			}
		};
		controlsGUI.addArmiesButtonAction(this.addArmiesListner);
	}
	
	/**
	 * Sets Action Listeners for fortification controls.
	 */
	public void setFortificationListeners() {
		controlsGUI.countrieslistAction(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				String countrySelected = (String) controlsGUI.getCountrySelected();
				driver.fortificationNeighbourListUpdate(countrySelected);
			}
		});
		
		controlsGUI.playButtonAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(controlsGUI.isNeighbourSelected()) {
					driver.getArmiesShiftedAfterFortification(controlsGUI.getCountrySelected(), 
							controlsGUI.getNeighborSelected(), controlsGUI.getArmiesValue());
				}
				driver.changePhase();
			}
		});
	}
	
	/**
	 * This method updates the neighbor list combobox in controlsview
	 * @param newArmies number of armies user can move
	 * @param newNeighbourList list of neighbor counties
	 */
	public void updateControlsFortification(int newArmies, String[] newNeighbourList) {
		controlsGUI.updateFortification(newArmies, newNeighbourList);
	}
	
	/**
	 * Method set the listeners to components for attack phase in controls view
	 */
	public void setAttackListeners() {
		controlsGUI.countrieslistAction(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				String countrySelected = (String) controlsGUI.getCountrySelected();
				driver.attackNeighbourListUpdate(countrySelected);
			}
		});
		
		controlsGUI.playButtonAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(controlsGUI.isNeighbourSelected()) {
					driver.announceAttack(controlsGUI.getCountrySelected(),controlsGUI.getNeighborSelected());
				}
			}
		});
		
		controlsGUI.endPhaseAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				driver.changePhase();
			}
		});
	}
	
	/**
	 * Update list of neighbors for combobox in controls view
	 * @param neighbourList list of neighbor countries
	 */
	public void updateNeighborList(String[] neighbourList) {
		controlsGUI.setNeighborList(neighbourList);
	}
	
	/**
	 * delegate method to call getInput from openingdialog class. 
	 * @see openingdialog
	 * @param min minimum value user can select 
	 * @param max maximum value user can select
	 * @param message message explaining the purpose of input
	 * @return a number selected by user
	 */
	public int setUpBoxInput(int min, int max, String message) {
		return setupBox.getInput(min, max,message);
	}

	/**
	 * Removes all controls when Game is Over.
	 */
	public void removeAllControls() {
		controlsGUI.removeAll();
	}

	/**
	* set the reinforcement controls using number of armies and names of countries
	* @param countryList country list
	* @param armies Armies assigned on reinforcement
	*/
	public void setReinforcementControls(int armies, String[] countryList) {
		controlsGUI.reinforcementControls(armies, countryList);
	}

	/**
	* set attack controls using string array
	* @param array country list
	*/
	public void setAttackControls(String[] array) {
		controlsGUI.attackControls(array);
	}

	/**
	* set fortification controls using string array
	* @param array country list
	*/
	public void setFortificationControls(String[] array) {
		controlsGUI.fortificationControls(array);		
	}
	
	/**
	 * Sets Action Listeners for save game.
	 */
	public void setSaveGameListener() {
		saveGameListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				driver.saveGameDataToFile();
			}
		};
		controlsGUI.saveGameButtonAction(this.saveGameListener);
	}
	
}
