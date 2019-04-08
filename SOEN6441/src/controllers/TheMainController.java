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
	 * setupBox variable used to store reference of class SetUpDialog
	 */
	private openingdialog setupBox;
	
	/**
	 * ActionListener to add listener to "Edit Map" button.
	 */
	private ActionListener mapEditListener;
	
	/**
	 * ActionListener to add listener to "Play Game" button.
	 */
	private ActionListener playGameListener;
	
	/**
	 * gameMode variable stores reference of class Mode
	 */
	private Mode gameMode;
	
	/**
	 * mController variable stores reference of class MainController
	 */
	private static TheMainController mController;
	
	/**
	 * private constructor for Singleton pattern imlementation
	 */
	private TheMainController() {}
	
	/**
	 * Get instance of MainController class
	 * @return initialize the instance of class MainController
	 */
	public static TheMainController getInit() {
		if(mController==null) {
			mController = new TheMainController();
		}
		return mController;
	}
	
	/**
	 * Method to initialize setupBox and listeners.
	 */
	public void start() {
		setupBox = new openingdialog();
		chooseMapEditorOrPlayGame();
		mapEditorListener();
		playGameListener();
	}

	/**
	 * Sets listener for Edit Map button.
	 */
	public void mapEditorListener() {
		mapEditListener =  new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameForMap newMapFrame = new FrameForMap();
				setupBox.chooseOptionFrame().dispose();
			}
		};
		this.setupBox.mapEditAction(mapEditListener);
	}
	
	/**
	 * Sets listener for Play Game button.
	 */
	public void playGameListener() {
		playGameListener =  new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				init();
				setupBox.chooseOptionFrame().dispose();
			}
		};
		this.setupBox.actionToPlayGameButton(playGameListener);
	}
	
	/**
	 * Calls chooseMapEditorOrPlayGame() function of the SetUpDialog class to display Edit Map and Play Game options.
	 */
	public void chooseMapEditorOrPlayGame() {
		this.setupBox.selectMapEditorOrPlayGame();
	}
	
	/**
	 * This method is responsible for taking input from user to whether user wants 
	 * to play tournament or single game, and accordingly create the tournament or single game object.
	 */
	private void init() {
		String mode = this.setupBox.gameMode();
		if(mode.equals("single")) {
			System.out.println("1");
			this.setupBox.selectSaveOrLoadGame();
		}
		else if(mode.equals("tournament")){
			getTournamentInfo();
		}
		else {
			init();
		}
	}
	
	/**
	* starting the game mode
	*/
	private void getTournamentInfo() {
		TournamentConsole infoView = new TournamentConsole();
		TheMainController mC = this;
		infoView.setListeners(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				System.out.print("Hello");
				gameMode = new Tournament(infoView.getGamesCount(), infoView.getMapDetails(),
						infoView.getPlayerBehaviorDetails(), infoView.getMovesCount(), mC);
				gameMode.start();
				infoView.dispose();
			}
		});
	}

	/**
	* string array of winners passed to initialize the winners
	*/
	public void setResults(String[][] winners) {
		ResultConsole result = new ResultConsole(winners);
	}
	
	/**
	 * notify mode (TournamentMode/SingleMode) class about winner of game
	 * @param winnerPlayer the name of the winner or draw.
	 */
	public void notifyGameResult(String winnerPlayer) {
		if(gameMode!=null) {
			gameMode.updateResults(winnerPlayer);
		}
		else {
			System.out.print("Error here");
		}
	}
	
	/**
	* initialize mode of the game.
	* @param mode game mode to be set
	*/
	public void setMode(Mode mode) {
		this.gameMode = mode;
	}
	
	/**
	* @return game mode
	*/
	public Mode getMode(){
		return this.gameMode;
	}

	public void singleGameInit() {
		String map = setupBox.getMapData("map");
		String bmp = setupBox.getMapData("bmp");
		String[][] players = setupBox.getInfoOnPlayerData();
		if(bmp!=null) {
			gameMode = new Single(map, bmp, players, 0, this);
		}else {
			gameMode = new Single(map, players, 0, this);	
		}
		gameMode.start();
	}

	public void singleGameLoadInit(String saveFileRead) {
		gameMode = new Single();
		((Single) gameMode).loadGameFromAFile(new File(saveFileRead));
	}
	
}
