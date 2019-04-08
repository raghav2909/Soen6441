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
	private PlayerConsole dataForPlayer;
	
	/**
	 * Map-View class object.
	 */
	private MapConsole map;
	
	/**
	 * Controls-View class object.
	 */
	private ControlsConsole controlsArea;
	
	/**
	 * Phase-View class object.
	 */
	private PhaseConsole phaseArea;
	
	/**
	 * View for WorldDomination class object.
	 */
	private DominationConsole dominationConsole;
	
	/**
	 * Initialize each view with the object of corresponding type.
	 * @param newPlayerData PlayerInfoView object.
	 * @param mapNew MapView object.
	 * @param newControls ControlsView object.
	 * @param phaseNew PhaseView Object.
	 * @param newDominationView WorldDominationView object.
	 */
	private GameConsole(PlayerConsole newPlayerData, MapConsole mapNew, ControlsConsole newControls, PhaseConsole phaseNew, DominationConsole newDominationView){	
		dataForPlayer = newPlayerData;
        map = mapNew;
        controlsArea = newControls;
        phaseArea = phaseNew;
        dominationConsole = newDominationView;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        init();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setVisible(true);
	}

	/**
	 * Makes the container for the main-window.
	 */
	private void init(){
		Container contentPane = this.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);
        
        dominationConsole.setPreferredSize(new Dimension(map.getWidth(), 400));
        dataForPlayer.setPreferredSize(new Dimension(400, 150));
        
        contentPane.add(dataForPlayer);
        contentPane.add(map);
        contentPane.add(controlsArea);
        contentPane.add(phaseArea);
        contentPane.add(dominationConsole);
        
       
        
		/* dataForPlayer .*/
        layout.putConstraint(SpringLayout.WEST, dataForPlayer, 5,  SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, dataForPlayer, -5,  SpringLayout.SOUTH, contentPane);
        
        /*constraints for phaseArea .*/
        layout.putConstraint(SpringLayout.WEST, phaseArea, 5,  SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.EAST, phaseArea, -5,  SpringLayout.WEST, map);
        layout.putConstraint(SpringLayout.NORTH, phaseArea, 5, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, phaseArea, -5,  SpringLayout.NORTH, dataForPlayer);
        
        /*constraints for map constraints.*/
        layout.putConstraint(SpringLayout.WEST, map, 5,  SpringLayout.EAST, dataForPlayer);
        layout.putConstraint(SpringLayout.NORTH, map, 5, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, map, -50, SpringLayout.NORTH, controlsArea);
        layout.putConstraint(SpringLayout.EAST, map, -5, SpringLayout.EAST, contentPane);
        
        /*constraints for dominationConsole.*/
        layout.putConstraint(SpringLayout.NORTH, dominationConsole, 5, SpringLayout.SOUTH, map);
        layout.putConstraint(SpringLayout.SOUTH, dominationConsole, -5, SpringLayout.NORTH, controlsArea);
        layout.putConstraint(SpringLayout.WEST, dominationConsole, 0, SpringLayout.WEST, map);
        layout.putConstraint(SpringLayout.EAST, dominationConsole, -5, SpringLayout.EAST, contentPane);
        
        /* constraints for controlsArea .*/
        layout.putConstraint(SpringLayout.WEST, controlsArea, 5,  SpringLayout.EAST, dataForPlayer);
        layout.putConstraint(SpringLayout.EAST, controlsArea, -5,  SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, controlsArea, 0, SpringLayout.NORTH, dataForPlayer);
        layout.putConstraint(SpringLayout.SOUTH, controlsArea, -5, SpringLayout.SOUTH, contentPane);
        this.pack();
        this.validate();
	}

	/**
	 * return the instance of the singleton-Main-View class.
	 * @return Main-View object.
	 */
	public static GameConsole getInstance() {
		return mainView;
    }
	
	/**
	 * Creates the instance of the Main-console class.
	 * @param newPlayerData Player console object.
	 * @param mapNew MapConsole object.
	 * @param newControls ControlsConsole object.
	 * @param phaseNew PhaseConsole object.
	 * @param newDominationView WorldDominationConsole object.
	 */
	public static void createInstance(PlayerConsole newPlayerData, MapConsole mapNew, ControlsConsole newControls, PhaseConsole phaseNew, DominationConsole newDominationView)
	{
		if(mainView == null){
			mainView = new GameConsole(newPlayerData, mapNew, newControls, phaseNew, newDominationView);
		}
	}
}
