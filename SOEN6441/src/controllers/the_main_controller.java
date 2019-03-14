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
 *@author gursharandeep
 *@version 2.0
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
		opendialog.chooseplayoredit();
	}
	/**
	 * Listeners for attack phase to set components in control console
	 */
	public void ListenerForAttackPhase()
	{
		crc.ListOfCountriesAction(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				GD.attackNeighbourListUpdate((String)crc.SelectedCountry()); 
				
			}
		});
		crc.Play_Move_Button_Action(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				GD.ChangePhase();
				
			}
		});
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
	 * This method helps to fetch the  GameDriver class instance
	 * @return instance of GameDriver class
	 */
	public GameDriver getGD()
	{
	return this.GD;
	}
	
	/**
	 * Sets Action Listeners for reinforcement controls.
	 */
	public void setActionListner() {
		addArmiesListner = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				//NodeOfCountry country = GD.getCountryNode(crc.SelectedCountry());
				int armyCount = crc.ValueOfArmies();
				GD.shiftArmiesOnReinforcement(crc.SelectedCountry(), armyCount);
			}
		};
		crc.Armies_Add_Button_Action(this.addArmiesListner);
	
		
	}
	/**
	 * Sets Action Listeners for fortification controls.
	 */
	public void setListenersFortification() {
		crc.ListOfCountriesAction(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent Ae) {
				String selectedCountry = (String) crc.SelectedCountry();
				//NodeOfCountry countrySelect = GameDriver.getInstance().getCurrent().getCountry(countrySelected);
//				if(countrySelect.getArmyCount()>1) {
//					ArrayList<String> neighborList = getNeighbors(countrySelect);
//				crc.updateFortification(countrySelect.getArmyCount(), neighborList.toArray(new String[neighborList.size()]));
//				}
				GD.fortificationNeighbourListUpdate(selectedCountry);
			}
		});
		
		crc.Play_Move_Button_Action(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent Ae) {
				if(crc.isNeighbourSelected()) {
					GD.getArmiesShiftedAfterFortification(crc.SelectedCountry(), crc.getNeighborSelected(),crc.ValueOfArmies());
					
				}
				GD.ChangePhase();
			}
		});
	}
	
	



/**
 * select the map file and image file to start game for single mode
 * @throws IOException 
 */
public void Single_Mode_Start() throws IOException {
	System.out.println("New Game");
	foc= new File_open_Controller();
	try {
		GD.MapCreation(foc.map_location("map"));
	} catch (IOException e) {
		e.printStackTrace();
	}
	ReadMap rm= new ReadMap();
	
	String image_loc = foc.map_location("bmp");
   if(image_loc!=null) {
		mpc = new MapConsole(image_loc);
}else {
		 mpc = new MapConsole();
}

	mpc = new MapConsole(image_loc);
	
	crc= new ControlsConsole();
	csr = new CardsConsole();
	drc = new DiceRollConsole();
	plc = new PlayerConsole();

	GameConsole.createInstance(plc, mpc, drc, csr, crc); 
	
	

    GD.setPlayerConsole(plc);
	GD.SetConsolMap(mpc);
	GD.SetConsolControl(crc);
	GD.GameOn();





}
public void single_Mode_Saved_Start() {

	System.out.println("Saved Mode");
	
}

}

