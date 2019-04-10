package controllers;


import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


import Model.GameDriver;
import Model.Map;
import Model.Mode;
import Model.NodeOfCountry;
import Model.NodeOfMap;
import Model.ReadMap;
import Model.Single;
import Model.Tournament;
import Util.GameLogging;

import view.CardsConsole;
import view.ControlsConsole;
import view.DiceRollConsole;
import view.DominationConsole;
import view.GameConsole;
import view.MapConsole;
import view.FrameForMap;
import view.PlayerConsole;
import view.ResultConsole;
import view.TournamentConsole;
import view.openingdialog;
import view.PhaseConsole;
/**
 * This class is the main controller class which controls the main functioning of the game in the initial phase
 *@author raghavsharda
 *@author gursharandeep
 *@version 2.0
 */




import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class TheMainController {
	
	/**
	 *  this is Action event defined to add listener relating "Play-Game" Button.
	 */

	private ActionListener listenerForPlayGame;
	
	/**
	 * modeOfGame variable stores reference of class Mode
	 */
	private Mode modeOfGame;
	/**
	 * setupBoxOpenDialogue variable used to store the reference for the class-VIEW openingdialog
	 */

	private openingdialog setupBoxOpenDialogue;
	
	/**
	 * this is Action event defined to add listener relating to "Edit-Map" Button.
	 */
	private ActionListener editTheMapListener;
	
	
	/**
	 * private constructor for Singleton-pattern implementation (TheMainController)
	 */
	private TheMainController() {}
	/**
	 *  stores-reference for class MainController
	 */
	private static TheMainController ControllerForMain;
	
	
	
	/**
	 * stores-reference of MainController class
	 * @return  the instance of class-MainController
	 */
	public static TheMainController getInitialize() {
		if(ControllerForMain==null) {
			ControllerForMain = new TheMainController();
		}
		return ControllerForMain;
	}
	
	/**
	 * Method that initialize setupBoxOpenDialogue and other-listeners.
	 */
	public void start() {
		setupBoxOpenDialogue = new openingdialog();
		choosePlayGameOrMapEditor();
		listenerForMapEditor();
		
		
		listenerForPlayGame();
	}

	/**
	 * this is Action event defined for functionality of Edit-Map-button.
	 */
	public void listenerForMapEditor() {
		editTheMapListener =  new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameForMap newMapFrame = new FrameForMap();
				setupBoxOpenDialogue.chooseOptionFrame().dispose();
			}
		};
		this.setupBoxOpenDialogue.actionForMapEditButton(editTheMapListener);
	}
	
	/**
	 * Calls choosePlayGameOrMapEditor() function from openingdialog class to display Edit-Map and Play-Game selection-options
	 */
	public void choosePlayGameOrMapEditor() {
		this.setupBoxOpenDialogue.selectMapEditorOrPlayGame();
	}
	
	/**
	 * this is Action event defined for play Game Button
	 */
	public void listenerForPlayGame() {
		listenerForPlayGame =  new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initialize();
				setupBoxOpenDialogue.chooseOptionFrame().dispose();
			}
		};
		this.setupBoxOpenDialogue.actionToPlayGameButton(listenerForPlayGame);
	}
	
	
	
	/**
	 * Takes input from user - to play tournament-mode or single-game-mode, and creates the required game object accordingly.
	 */
	private void initialize() {
		String gameMode = this.setupBoxOpenDialogue.modeOfGame();
		if(gameMode.equals("single")) {
			System.out.println("1");
			this.setupBoxOpenDialogue.selectSaveOrLoadGame();
		}
		else if(gameMode.equals("tournament")){
			getInfoForTournament();
		}
		else {
			initialize();
		}
	}
	
	/**
	* starts the game: game-Mode for tournament
	*/
	private void getInfoForTournament() {
		
		
		TheMainController mC = this;
		TournamentConsole infoView = new TournamentConsole();
		System.out.println("getInfoForTournament");
		infoView.setListeners(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				System.out.print("Hello");
				modeOfGame = new Tournament(infoView.getCountOfGames(), infoView.getDetailsOfMap(),
						infoView.getDetailsOfPlayerBehavior(), infoView.getCountOfMoves(), mC);
				modeOfGame.start();
				infoView.dispose();
			}
		});
	}

	/**
	* string-array of game-gameWinners passed to initialize the gameWinners in REsultConsole
	* @param gameWinners gameWinners-name
	*/
	public void setResults(String[][] gameWinners) {
		ResultConsole result = new ResultConsole(gameWinners);
	}
	
	/**
	 *  send notification for game-Mode (Tournament-Mode/Single-Mode) class for game winner
	 * @param winnerPlayer the name of-the-winner or Game-draw.
	 */
	public void notifyResultOfGame(String winnerPlayer) {
		if(modeOfGame!=null) {
			modeOfGame.updateResults(winnerPlayer);
		}
		else {
			System.out.print("Error!!!");
		}
	}
	/**
	 * to-load Single-game initilaization
	 * @param saveFileRead read the saved file
	 */
	public void initLoadOfSingleGame(String saveFileRead) {
		modeOfGame = new Single();
		((Single) modeOfGame).loadGameFromAFile(new File(saveFileRead));
	}
	/**
	* sets the contents for set-gameMode of the-game.
	* @param gameMode game gameMode to be set
	*/
	public void setGameMode(Mode gameMode) {
		this.modeOfGame = gameMode;
	}
	
	/** sets the contents for  get-gameMode of the-game.
	* @return game gameMode
	*/
	public Mode getGameMode(){
		return this.modeOfGame;
	}

	public void singleGameInit() {
		String map= setupBoxOpenDialogue.getMapData("map");
		String bmp = setupBoxOpenDialogue.getMapData("bmp");
		
	
		String[][] gamePlayers = setupBoxOpenDialogue.getInfoOnPlayerData();
		if(bmp!=null) {
			modeOfGame = new Single(map, bmp, gamePlayers, 0, this);
		}else {
			modeOfGame = new Single(map, gamePlayers, 0, this);	
		}
		modeOfGame.start();
	}

	
	
}
