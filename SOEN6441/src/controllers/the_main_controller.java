package controllers;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import view.CardsConsole;
import view.ControlsConsole;
import view.DiceRollConsole;
import view.GameConsole;
import view.MapConsole;
import view.PlayerConsole;
import view.openingdialog;
/**
 * maincr variable stores reference of class MainController
 */


public class the_main_controller {
	private static the_main_controller maincr ;
	
	private ActionListener editmap;
	
	private ActionListener playthegame;

	private ActionListener editthemaps;
	private CardsConsole csr;
	private ControlsConsole crc;
	private DiceRollConsole drc;
	private MapConsole mpc;
	private PlayerConsole plc;
	
	
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
		
	}
	
	
	public static the_main_controller getInstance() {
		if(maincr==null) {
			maincr = new the_main_controller();
		}
		return maincr;
	}
	
	public void tostart()
	{
		chooseplayoredit();
		gameplay();
	}

/**
 * calling a method of openingdialog class to choose to play or edit map
 */
public void chooseplayoredit()
{
	opendialog.chooseplayoredit();
}

/**
 * calling for single mode or tournament mode to act as per chosen
 */
public void gameplay()
{
	playthegame = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String m =opendialog.decideMode();
			if(m=="s")
			{
				System.out.println("Single mode opted");
				opendialog.games_option();
			}
				else if(m=="m")
				{
				System.out.println("tournament mode opted");
				opendialog.tournament_mode();
				}

			opendialog.returnframe().dispose();	
		}
	};
	this.opendialog.Actiongameplay(playthegame);
}

/**
 * select the map file and image file to start game for single mode
 */
public void Single_Mode_Start() {
	System.out.println("Coming here new game");
	foc= new File_open_Controller();
	String loc = foc.map_location("map");
	String image = foc.map_location("bmp");

	System.out.println(loc);
	System.out.println(image);
	forward(image);
	
}
public void forward(String mapimage)
{
	if(mapimage== null)
	{
		mpc = new MapConsole();
	}
	else
	{
		mpc = new MapConsole();
	
	}
	//mpc = new MapConsole();
	crc= new ControlsConsole();
	csr = new CardsConsole();
	drc = new DiceRollConsole();
	plc = new PlayerConsole();
	GameConsole.createInstance(plc, mpc, drc, csr, crc);
		
}
public void single_Mode_Saved_Start() {

	System.out.println("Coming here saved mode");
	// TODO Auto-generated method stub
	//File_open_Controller foc= new File_open_Controller();
}
}