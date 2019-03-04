package controllers;


import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


import Model.GameDriver;
import Model.Map;
import Model.NodeOfCountry;
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
	 * This action listner for editmap
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
	 * ActionListener to add listener to "Add Armies" button.
	 */
	private ActionListener addArmiesListner;
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
	openingdialog opendialog;

	Map map;
	
	/**
	 *  constructor
	 */
	
	public the_main_controller()
	{ 
		
		opendialog= new openingdialog();
		GD = new GameDriver();
		opendialog.start();
	}
	public the_main_controller(String s) {
		// TODO Auto-generated constructor stub
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
	 * Sets Action Listeners for reinforcement controls.
	 */
	public void setActionListner() {
		addArmiesListner = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				NodeOfCountry country = GD.getCountryNode(crc.SelectedCountry());
				int armies = crc.ValueOfArmies();
				shiftArmiesOnReinforcement(country, armies);
				GD.ContinuePhase();
			}
		};
		crc.Armies_Add_Button_Action(this.addArmiesListner);
		
		crc.End_Phase_Button_Action(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GD.ChangePhase();
			}
		}); 
	}
	
	/**
	 * Shifts(or places) the armies of the player on each reinforcement.
	 * @param country the country node to which armies are added.
	 * @param armies the number of armies to be reinforced.
	 * @return the army count left for the player.
	 */
	public int shiftArmiesOnReinforcement(NodeOfCountry country, int armies) {
		country.AddArmy(armies);
		GD.getCurrent().RemovedArmies(armies);
		return GD.getArmyCount();
	}

	/**
	 * Sets Action Listeners for fortification controls.
	 */
	public void setListenersFortification() {
		crc.countrieslistAction(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				String countrySelected = (String) crc.SelectedCountry();
				NodeOfCountry countrySelect = GameDriver.getInstance().getCurrent().getCountry(countrySelected);
				if(countrySelect.getArmyCount()>1) {
					ArrayList<String> neighborList = getNeighbors(countrySelect);
					crc.updateFortification(countrySelect.getArmyCount(), neighborList.toArray(new String[neighborList.size()]));
				}
			}
		});
		
		crc.Play_Move_Button_Action(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(crc.isNeighbourSelected()) {
					String countrySelected = (String) crc.SelectedCountry();
					int selectedArmies = crc.ValueOfArmies();
					NodeOfCountry countrySelect = GameDriver.getInstance().getCurrent().getCountry(countrySelected);
					String neighbourSelected = crc.getNeighborSelected();
					getArmiesShiftedAfterFortification(countrySelect, neighbourSelected, selectedArmies);
				}
				GD.ChangePhase();
			}
		});
	}
	
	/**
	 * Gets the neighbor countries owned by the current player for a given country.
	 * @param countrySelect Country Node whose neighbors are to be displayed.
	 * @return list of owned neighbors.
	 */
	public ArrayList<String> getNeighbors(NodeOfCountry countrySelect){
		ArrayList<String> neighborList = new ArrayList<String>();
		for(String name: countrySelect.getPlayerNeighboursName()) {
			neighborList.add(name);
		}
		return neighborList;
	}

	public int getArmiesShiftedAfterFortification(NodeOfCountry countrySelect, String neighbourSelected, int selectedArmies){
		NodeOfCountry required = null;
		countrySelect.SetArmies(countrySelect.getArmyCount()-selectedArmies); 
		for(NodeOfCountry j : countrySelect.getNeighboursCountries()) {
			if(j.getNameOfCountry() == neighbourSelected) {
				required = j;
				j.SetArmies(j.getArmyCount() + selectedArmies);
			}
		}
		return required.getArmyCount();
	}



/**
 * select the map file and image file to start game for single mode
 * @throws IOException 
 */
public void Single_Mode_Start() throws IOException {
	System.out.println("Coming here new game");
	foc= new File_open_Controller();
	try {
		GD.CreateMapObject(foc.map_location("map"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ReadMap rm= new ReadMap();
	
	String image_loc = foc.map_location("bmp");
   if(image_loc!=null) {
		mpc = new MapConsole(image_loc);
}else {
		 mpc = new MapConsole();
}
	System.out.println(image_loc);

	mpc = new MapConsole(image_loc);
	
	crc= new ControlsConsole();
	csr = new CardsConsole();
	drc = new DiceRollConsole();
	plc = new PlayerConsole();

	GameConsole.createInstance(plc, mpc, drc, csr, crc); 
	System.out.println("wokringh frint");
	
	

    GD.setPlayerConsole(plc);
	GD.SetConsolMap(mpc);
	GD.SetConsolControl(crc);
	GD.GameOn();





}
public void single_Mode_Saved_Start() {

	System.out.println("Coming here saved mode");
	// TODO Auto-generated method stub
	//File_open_Controller foc= new File_open_Controller();
}
}

