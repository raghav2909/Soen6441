package Model;

import controllers.ControllerForGame;
import controllers.TheMainController;
/**
 * 
 *This class handles the tournament game mode.
 *
 */
public class Tournament implements Mode {
	
	/**
	 * mController varaiable for storing the reference of class the_main_controller
	 */
	private TheMainController mController;
	
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
	public String[][] winners;
	
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
	public Tournament(TheMainController newController) {
		this.mController = newController;
	}

	/**
	 * parameterized constructor of class
	 * @param gamesCount count number of games
	 * @param mapDetails details of map
	 * @param playerBehaviorDetails behaviour of player
	 * @param movesCount number of moves
	 * @param newController main controller object
	 */
	public Tournament(int gamesCount, String[] mapDetails, String[][] playerBehaviorDetails, int movesCount, TheMainController newController) {
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
	
	
	public String testTournament(int gamesCount, String[] mapDetails, String[][] playerBehaviorDetails, int movesCount, TheMainController newController) {
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
		String x= winners[maps.length-1][0];
		lastWinner(x);
		return x;
		
	}
	
	public String lastWinner(String y) {
	   return y;
	}
	
	/**
	* initializing the instance variable of the class 
	*/
	public void start() {
		ControllerForGame gController = new ControllerForGame(maps[currentMap],behaviors,moveLimit);
	}
	
	/**
	* updating results using winner obtained
	* @param winner winner name
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

		String[][] myPs = {{"Raghav","aggressive"},{"Saman","aggressive"}};
		String[] maps = {"C:\\Users\\Gursharan\\git\\Soen6441\\SOEN6441\\Map_Data\\map\\World2005.map","C:\\Users\\Gursharan\\git\\Soen6441\\SOEN6441\\Map_Data\\map\\World2005.bmp"};
		Tournament s = new Tournament(2,maps, myPs, 10, TheMainController.getInitialize());
		TheMainController.getInitialize().setGameMode(s);



		s.start();
	}

}
