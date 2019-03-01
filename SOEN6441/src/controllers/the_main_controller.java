package controllers;


import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import Model.GameDriver;
import Model.NodeOfMap;
import Model.ReadMap;
import view.CardsConsole;
import view.ControlsConsole;
import view.DiceRollConsole;
import view.GameConsole;
import view.MapConsole;
import view.PlayerConsole;
import view.openingdialog;
/**
 * This class is the main controller class which controls the main functioning of the game in the initial phase
 *@author raghavsharda
 */


public class the_main_controller {
	/** this variable stores reference of class MainController
	 * 
	 */
	private static the_main_controller maincr ;
	/**
<<<<<<< HEAD
	 * This actionlistner for editmap option
=======
	 * This action listner for editmap
>>>>>>> refs/remotes/origin/master
	 */
	
	private ActionListener editmap;
	/**
	 * This action listner for playthegame
	 */
	private ActionListener playthegame;
	/**
	 * This actionlistner for editthemaps
	 */
	private ActionListener editthemaps;
	/**
	 * CardsConsole Object
	 */
	private CardsConsole csr;
	/**
	 * ControlsConsole Object
	 */
	private ControlsConsole crc;
	/**
	 * DiceRollConsole Object
	 */
	private DiceRollConsole drc;
	/**
	 * MapConsole Object
	 */
	private MapConsole mpc;
	/**
	 * PlayerConsole Object
	 */
	private PlayerConsole plc;
	/**
	 * GameDriver Object
	 */
	private GameDriver GD;
	/**
	 * Player_Information_Controller object
	 */
	private Player_Information_Controller pic;
	
	/**
	 * Stores the object of File_open_Controller class
	 */
	private File_open_Controller foc;
	
	/**
	 * Creating an object of openingdialog class of view package
	 */
	openingdialog opendialog= new openingdialog();
	
	/**
	 *  constructor
	 */
	public the_main_controller()
	{
		opendialog.start();
	}
	/**
	 * This is parameterized method that instantiated main controller class.
	 * @return the_main_Controller object
	 */
	
	public static the_main_controller getInstance() {
		if(maincr==null) {
			maincr = new the_main_controller();
		}
		return maincr;
	}
	/**
	 * This function has the methods calls to the two main parts of the game 
	 * 1. chooseplayoredit
	 * 2. gameplay
	 */
//	public void tostart()
//	{
//		chooseplayoredit();
////		gameplay();
//	}




/**
 * calling for single mode or tournament mode to act as per chosen
 */
//public void gameplay()
//{
//	playthegame = new ActionListener() {
//		
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			// TODO Auto-generated method stub
//			String m =opendialog.decideMode();
//			if(m=="s")
//			{
//				System.out.println("Single mode opted");
//				opendialog.games_option();
//			}
//				else if(m=="m")
//				{
//				System.out.println("tournament mode opted");
//				opendialog.tournament_mode();
//				}
//
//			opendialog.returnframe().dispose();	
//		}
//	};
//	this.opendialog.Actiongameplay(playthegame);
//}

/**
 * select the map file and image file to start game for single mode
 */
public void Single_Mode_Start() {
	System.out.println("Coming here new game");
	foc= new File_open_Controller();
	//GD.CreateMapObject(foc.map_location("map"));
	ReadMap rm= new ReadMap();
	try {
		ArrayList<NodeOfMap> mapinfo= rm.mapreader(foc.map_location("map"));
		
		for(NodeOfMap n : mapinfo) {
			System.out.println(n.toString());
		}
		
		

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//String map_loc = foc.map_location("map");
	
	String image_loc = foc.map_location("bmp");
	System.out.println(image_loc);
	forward(image_loc);

	GD.GameOn() ;;
	

	
//	GD.RunGame();
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441
	
}

public void forward(String mapimage)
{
	

	mpc = new MapConsole(mapimage);
	
	crc= new ControlsConsole();
	csr = new CardsConsole();
	drc = new DiceRollConsole();
	plc = new PlayerConsole();
	GameConsole.createInstance(plc, mpc, drc, csr, crc);

    GD.setPlayerConsole(plc);
	GD.SetConsolMap(mpc);
	GD.SetConsolControl(crc);
	GD.GameOn();

	GD.setPlayerView(plc);
	GD.SetConsolMap(mpc);
	GD.SetConsolControl(crc);
	GD.GameOn();
	
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441

}
public void single_Mode_Saved_Start() {

	System.out.println("Coming here saved mode");
	// TODO Auto-generated method stub
	//File_open_Controller foc= new File_open_Controller();
}
}