
 
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
 * @author raghavsharda
 *@verison 1.0
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
	 * CardsConsole class object.
	 */
	private CardsConsole csc;
	
	/**
	 * ControlsConsole class object.
	 */
	private ControlsConsole controlc;
	
	/**class constructor setting main game window by calling starts method
	 * @param palyerinfo PlayerConsole object 
	 * @param mapsinfo MapConsole object
	 * @param diceinfo DiceRollConsole object
	 * @param cardsinfo CardsConsole object
	 * @param controlsinfo ControlsConsole object
	 */
	public GameConsole(PlayerConsole playerinfo, MapConsole mapsinfo, DiceRollConsole diceinfo, CardsConsole cardsinfo, ControlsConsole controlsinfo )
	{
		plc = playerinfo;
		mpc = mapsinfo;
		drc =  diceinfo;
		csc = cardsinfo;
	    controlc = controlsinfo;
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
        mpc.setPreferredSize(new Dimension(400,drc.getHeight()));
        
        contentPane.add(plc);
        contentPane.add(mpc);
        contentPane.add(drc);
        contentPane.add(csc);
        contentPane.add(controlc);
    	
        layout.putConstraint(SpringLayout.WEST, mpc, 5,  SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.EAST, mpc, 0,  SpringLayout.EAST, csc);
        layout.putConstraint(SpringLayout.NORTH, mpc, 5, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, mpc, -5,  SpringLayout.NORTH, csc);
        
       
        layout.putConstraint(SpringLayout.WEST,drc, 5,  SpringLayout.EAST, mpc);
        layout.putConstraint(SpringLayout.NORTH, drc, 5, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, drc, 0, SpringLayout.SOUTH, mpc);
        layout.putConstraint(SpringLayout.EAST, drc, -5, SpringLayout.WEST, plc);
        
       
        layout.putConstraint(SpringLayout.EAST, plc, -5,  SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.NORTH,plc, 5, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, plc, 0, SpringLayout.SOUTH, drc);
        
        
        layout.putConstraint(SpringLayout.WEST, csc, 5,  SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, csc, -5, SpringLayout.SOUTH, contentPane);
        
       
        layout.putConstraint(SpringLayout.WEST, controlc, 5,  SpringLayout.EAST, csc);
        layout.putConstraint(SpringLayout.EAST, controlc, -5,  SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, controlc, 0, SpringLayout.NORTH, csc);
        layout.putConstraint(SpringLayout.SOUTH, controlc, 0, SpringLayout.SOUTH, csc);
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
	 * @param diceinfo DiceRollConsole object
	 * @param cardsinfo CardsConsole object
	 * @param controlsinfo ControlsConsole object
	 */
	public static void createInstance(PlayerConsole playerinfo, MapConsole mapsinfo, DiceRollConsole diceinfo, CardsConsole cardsinfo, ControlsConsole controlsinfo ){
		if(gmc == null){
			gmc = new GameConsole(playerinfo,mapsinfo,diceinfo, cardsinfo, controlsinfo);
		}
	}
	
	}




