package Model;

import controllers.ControllerForGame;
import controllers.the_main_controller;

public class Tournoment implements Mode {

	
	/**
	 * mController varaiable for storing the reference of class the_main_controller
	 */
	private the_main_controller mController;
	
	/**
	 * Number of games to be played on each map;
	 */
	private int games = 0;
	
	/**
	 * Number of moves limit for every game.
	 */
	private int moveLimit = 0;
	
	/**
	 * List of players and behaviors.
	 */
	private String[][] behaviors;
	
	/**
	 * List of URLs of Maps 
	 */
	private String[] maps;
	
	/**
	 * List of Winners
	 */
	private String[][] winners;
	
	/**
	 * Current map on which game is playing
	 */
	private int currentMap;
	
	/**
	 *  Current game number playing
	 */
	private int currentGame;
	
	/**
	 *  Constructor for Tournoment class.
	 *  @param newController object of the_main_controller class.
	 */
	public Tournoment(the_main_controller newController) {
		this.mController = newController;
	}

	public Tournoment(int gamesCount, String[] mapDetails, String[][] playerBehaviorDetails, int movesCount, the_main_controller newController) {
		mController = newController;
		games = gamesCount;
		maps = mapDetails;
		behaviors = playerBehaviorDetails;
		moveLimit = movesCount;
		winners = new String[mapDetails.length][gamesCount+1];
		for(int i=0; i<maps.length; i++) {
			winners[i][0] = maps[i];
		}
		currentMap = 0;
		currentGame = 1;
	}
	
	/**
	* initializing the instance variable of the class 
	*/
	public void start() {
		ControllerForGame gController = new ControllerForGame(maps[currentMap],behaviors,moveLimit);
	}
	
	/**
	* updating results using winner obtained
	*/
	public void updateResults(String winner) {
		winners[currentMap][currentGame] = winner;
		if(currentGame==games) {
			if(currentMap<(maps.length-1)) {
				currentMap++;
				currentGame = 1;
				start();
			}
			else {
				mController.setResults(winners);
			}
		}
		else {
			currentGame++;
			start();
		}
	}

	public static void main(String[] arg) {
		String[][] myPs = {{"Gur","aggressive"},{"Raj","aggressive"}};
		String[] maps = {"D:\\Gurpreet\\Study\\Meng\\SEM6\\SOEN6441\\project\\World2005.map","D:\\Gurpreet\\Study\\Meng\\SEM6\\SOEN6441\\project\\World2005.bmp"};
		Tournoment s = new Tournoment(2,maps, myPs, 10, the_main_controller.getInstance());
		the_main_controller.getInstance().setMode(s);
		s.start();
	}
	


}
