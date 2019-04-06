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
public class GameConsole extends JFrame  {
	/**
	 * Serial Version id for JFrame.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Gameconsole object
	 */
	private static GameConsole gmc;
	/**
	 * PlayerConsole class object.
	 */
	private PlayerConsole plc;
	
	/**
	 * MapConsole class object.
	 */
	private MapConsole mpc;
	
	/**
	 * DiceRollConsole class object.
	 */
	private DiceRollConsole drc;
	/**
	 * Domination in the world by players
	 */
	private DominationConsole dc;
	/**
	 * CardsConsole class object.
	 */
	private CardsConsole csc;
	
	/**
	 * ControlsConsole class object.
	 */
	private ControlsConsole controlc;
	/**
	 * PhaseConsole object
	 */
	private PhaseConsole phc;
	
	/**class constructor setting main game window by calling starts method
	 * @param palyerinfo PlayerConsole object 
	 * @param mapsinfo MapConsole object
	 * @param controlsinfo ControlsConsole object
	 *@param phaseinfo phase console object
	 *@param dominationinfo domination console object
	 */
	public GameConsole(PlayerConsole playerinfo, MapConsole mapsinfo,ControlsConsole controlsinfo,PhaseConsole phaseinfo,DominationConsole dominationinfo )
	{
		plc = playerinfo;
		mpc = mapsinfo;
		//drc =  diceinfo;
		//csc = cardsinfo;
		phc= phaseinfo;
	    controlc = controlsinfo;
	   dc = dominationinfo;
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    starts();
	    this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	    this.setVisible(true);
	    
		
		
	}
	/**
	 * making the frame for the main game console
	 */
	public void starts() {
		Container contentPane = this.getContentPane();
  SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);
        this.setBackground(Color.GRAY);
        dc.setPreferredSize(new Dimension(mpc.getWidth(),300));
        plc.setPreferredSize(new Dimension(400,150));
        
        contentPane.add(plc);
        contentPane.add(mpc);
        contentPane.add(dc);
       // contentPane.add(csc);
        contentPane.add(controlc);
        contentPane.add(phc);
        /*constraints for controls console*/
        layout.putConstraint(SpringLayout.WEST, controlc, 5,  SpringLayout.EAST, plc);
        layout.putConstraint(SpringLayout.EAST, controlc, -5,  SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, controlc, 0, SpringLayout.NORTH, plc);
        layout.putConstraint(SpringLayout.SOUTH, controlc, -5, SpringLayout.SOUTH, contentPane);
        /* constraints for map console*/
        layout.putConstraint(SpringLayout.WEST, mpc, 5,  SpringLayout.EAST, plc);
       layout.putConstraint(SpringLayout.NORTH, mpc, 5, SpringLayout.NORTH, contentPane);
       layout.putConstraint(SpringLayout.SOUTH, mpc, -50, SpringLayout.NORTH, controlc);
       layout.putConstraint(SpringLayout.EAST, mpc, -5, SpringLayout.EAST, contentPane);
       
    	/*
    	 *  constraints for phase console
    	 */
        layout.putConstraint(SpringLayout.WEST, phc, 5,  SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.EAST, phc, 0,  SpringLayout.EAST, mpc);
        layout.putConstraint(SpringLayout.NORTH, phc, 5, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, phc, -5,  SpringLayout.NORTH, plc);
        
      /* constraints for Player console*/
        layout.putConstraint(SpringLayout.WEST, plc, 5,  SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, plc, -5,  SpringLayout.SOUTH, contentPane);
        
        /* constraints for map console*/
         layout.putConstraint(SpringLayout.WEST, mpc, 5,  SpringLayout.EAST, plc);
        layout.putConstraint(SpringLayout.NORTH, mpc, 5, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, mpc, -50, SpringLayout.NORTH, controlc);
        layout.putConstraint(SpringLayout.EAST, mpc, -5, SpringLayout.EAST, contentPane);
        
        /*constraints for domination console.*/
        layout.putConstraint(SpringLayout.NORTH, dc, 5, SpringLayout.SOUTH, mpc);
        layout.putConstraint(SpringLayout.SOUTH, dc, -5, SpringLayout.NORTH, controlc);
        layout.putConstraint(SpringLayout.WEST, dc, 0, SpringLayout.WEST, mpc);
        layout.putConstraint(SpringLayout.EAST, dc, -5, SpringLayout.EAST, contentPane);
       
          this.pack();   
	
	}
	/**
	 * Gives the instance of the singleton MainView class.
	 * @return MainView object.
	 */
	public static GameConsole getInstance() {
		return gmc;
    }
	
	/**
	 * creates instance of this class
	 * @param palyerinfo PlayerConsole object 
	 * @param mapsinfo MapConsole object
	 * @param phaseinfo phase Console object
	 * @param dominationinfo domination Console object
	 * @param controlsinfo ControlsConsole object
	 */
	public static void createInstance(PlayerConsole playerinfo, MapConsole mapsinfo,ControlsConsole controlsinfo, PhaseConsole phaseinfo, DominationConsole dominationinfo ){
		if(gmc == null){
			gmc = new GameConsole(playerinfo,mapsinfo, controlsinfo, phaseinfo,dominationinfo);
		}
	}
	
	}
