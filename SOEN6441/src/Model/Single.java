package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import controllers.ControllerForGame;
import controllers.TheMainController;
/**
 * 
 * This class handles the single game mode.
 *
 */
public class Single implements Mode{

	private TheMainController mController;
	private static String myMap;
	private String myBmp;
	private String[][] myPlayers;
	private int myMoveLimit;
	private ControllerForGame controller;
	
	/**
	 * Constructor for initializing the instance variables.
	 * @param map stores .map file path
	 * @param bmp stores .bmp file path
	 * @param players player data.
	 * @param moveLimit number of moves allowed.
	 * @param nController the_mainController object.
	 */
	public Single(String map, String bmp, String[][] players, int moveLimit, TheMainController nController) {
		mController = nController;
		myMap = map;
		myBmp = bmp;
		myPlayers = players;
		myMoveLimit = moveLimit;
	}

	/**
	* constructor for initialzing the instance variables.
	* @param players player names
	* @param moveLimit limit of number of moves
	* @param nController main controller object
	* @param map map name
	*/
	public  Single(String map, String[][] players, int moveLimit, TheMainController nController) {
		mController = nController;
		myMap = map;
		myPlayers = players;
		myMoveLimit = moveLimit;
	}

	/**
	 * constructor of class
	 */
	public Single() {}

	/**
	* udating results using the winner player
	* @param winnerPlayer winner player name
	*/
	public void updateResults(String winnerPlayer) {
		String[][] data = {{myMap , winnerPlayer}};
		mController.setResults(data);
	}
	
	/**
	* initializing the instance variables depending upon condition 
	*/
	public void start() {
		if(myBmp!=null) {
			controller = new ControllerForGame(myMap, myBmp, myPlayers, myMoveLimit);
		}
		else {
			controller = new ControllerForGame(myMap, myPlayers, myMoveLimit);
		}
	}
	
	public static void main(String[] arg) {
		String[][] myPs = {{"Saman","human"},{"Raghav","human"}};
		Single s = new Single("C:\\Users\\saman\\git\\SOEN6441final\\SaveGame2019.04.09.16.41.14.sav", "C:\\Users\\saman\\git\\SOEN6441final\\SaveGame2019.04.09.16.41.14.sav", myPs, 0, TheMainController.getInitialize());
		TheMainController.getInitialize().setGameMode(s);
		Single s1 = new Single();
		s1.loadGameFromAFile(new File("C:\\Users\\saman\\git\\SOEN6441final\\SaveGame2019.04.09.16.41.14.sav"));
		s.start();
		
	}
	
	/**
	* @return map
	*/
	public static String getNameOfMap(){
		return myMap;
	}
	
	/**
	 * 
	 * @param file file name
	 */
	public void loadGameFromAFile(File file){ 

		try{
			FileInputStream saveFile = new FileInputStream(file);
			ObjectInputStream save = new ObjectInputStream(saveFile);
			
			/*Map file path.*/
			String filePath = (String) save.readObject();
			
			/*Number of players.*/
			int playerCount = (int) save.readObject();
			
			/*Player data.*/
			String[][] players= new String[playerCount][2];
			ArrayList<ArrayList<String>> countryList = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<Integer>> armyCountList = new ArrayList<ArrayList<Integer>>();
			for(int i= 0; i< playerCount; i++){
				String[] player = new String[2];
				player[0] = (String) save.readObject();
				player[1] = (String) save.readObject();
				players[i] = player;
				ArrayList<String> countries = new ArrayList<String>();
				ArrayList<Integer> armies = new ArrayList<Integer>();
				int countryCount = (int) save.readObject();
				for(int j = 0; j < countryCount; j++){
					countries.add((String) save.readObject());
					armies.add((Integer) save.readObject());
				}
				countryList.add(countries);
				armyCountList.add(armies);
			}
			
			/*Current player.*/
			String currentPlayer = (String) save.readObject();
			
			/*Current phase.*/
			String phaseName = (String) save.readObject();

			controller = new ControllerForGame(filePath, players, countryList, armyCountList, currentPlayer, phaseName);

			save.close();
			
		}catch(Exception exc){
			System.out.println("Failed to load file\n"+exc);
		}
	}
	
}

