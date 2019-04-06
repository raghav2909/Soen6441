package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;



/**
 *This class is the main view of the front end of the game console where the players will play the game
 *and all other information while playing the game will be displayed.
 *@author raghavsharda
 *@version 2.0
 */
public class GameConsole extends JFrame{	
	
	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * MainView object.
	 */
	private static GameConsole mainView;
	
	/**
	 * PlayerInfoView class object.
	 */
	private PlayerConsole playerData;
	
	/**
	 * MapView class object.
	 */
	private MapConsole map;
	
	/**
	 * ControlsView class object.
	 */
	private ControlsConsole controlsArea;
	
	/**
	 * PhaseView class object.
	 */
	private PhaseConsole phaseArea;
	
	/**
	 * WorldDominationView class object.
	 */
	private DominationConsole dominationView;
	
	/**
	 * Initialize each view with the object of corresponding type.
	 * @param newPlayerInfo PlayerInfoView object.
	 * @param newMap MapView object.
	 * @param newControls ControlsView object.
	 * @param newPhase PhaseView Object.
	 * @param newDominationView WorldDominationView object.
	 */
	private GameConsole(PlayerConsole newPlayerInfo, MapConsole newMap, ControlsConsole newControls, PhaseConsole newPhase, DominationConsole newDominationView){	
		playerData = newPlayerInfo;
        map = newMap;
        controlsArea = newControls;
        phaseArea = newPhase;
        dominationView = newDominationView;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        init();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setVisible(true);
	}

	/**
	 * Makes the container for the main window.
	 */
	private void init(){
		Container contentPane = this.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);
        
        dominationView.setPreferredSize(new Dimension(map.getWidth(), 400));
        playerData.setPreferredSize(new Dimension(400, 150));
        
        contentPane.add(playerData);
        contentPane.add(map);
        contentPane.add(controlsArea);
        contentPane.add(phaseArea);
        contentPane.add(dominationView);
        
        /*phaseArea constraints.*/
        layout.putConstraint(SpringLayout.WEST, phaseArea, 5,  SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.EAST, phaseArea, -5,  SpringLayout.WEST, map);
        layout.putConstraint(SpringLayout.NORTH, phaseArea, 5, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, phaseArea, -5,  SpringLayout.NORTH, playerData);
        
		/*playerData constraints.*/
        layout.putConstraint(SpringLayout.WEST, playerData, 5,  SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, playerData, -5,  SpringLayout.SOUTH, contentPane);
        
        /*map constraints.*/
        layout.putConstraint(SpringLayout.WEST, map, 5,  SpringLayout.EAST, playerData);
        layout.putConstraint(SpringLayout.NORTH, map, 5, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, map, -50, SpringLayout.NORTH, controlsArea);
        layout.putConstraint(SpringLayout.EAST, map, -5, SpringLayout.EAST, contentPane);
        
        /*dominationView constraints.*/
        layout.putConstraint(SpringLayout.NORTH, dominationView, 5, SpringLayout.SOUTH, map);
        layout.putConstraint(SpringLayout.SOUTH, dominationView, -5, SpringLayout.NORTH, controlsArea);
        layout.putConstraint(SpringLayout.WEST, dominationView, 0, SpringLayout.WEST, map);
        layout.putConstraint(SpringLayout.EAST, dominationView, -5, SpringLayout.EAST, contentPane);
        
        /*controlsArea constraints.*/
        layout.putConstraint(SpringLayout.WEST, controlsArea, 5,  SpringLayout.EAST, playerData);
        layout.putConstraint(SpringLayout.EAST, controlsArea, -5,  SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, controlsArea, 0, SpringLayout.NORTH, playerData);
        layout.putConstraint(SpringLayout.SOUTH, controlsArea, -5, SpringLayout.SOUTH, contentPane);
        this.pack();
        this.validate();
	}

	/**
	 * Gives the instance of the singleton MainView class.
	 * @return MainView object.
	 */
	public static GameConsole getInstance() {
		return mainView;
    }
	
	/**
	 * Creates the instance of the MainView class.
	 * @param newPlayerInfo PlayerInfoView object.
	 * @param newMap MapView object.
	 * @param newControls ControlsView object.
	 * @param newPhase PhaseView object.
	 * @param newDominationView WorldDominationView object.
	 */
	public static void createInstance(PlayerConsole newPlayerInfo, MapConsole newMap, ControlsConsole newControls, PhaseConsole newPhase, DominationConsole newDominationView)
	{
		if(mainView == null){
			mainView = new GameConsole(newPlayerInfo, newMap, newControls, newPhase, newDominationView);
		}
	}
}
