
package controllers;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 * This class will return all the information required for a player 
 * This information will include the number of players and name of players 
 * @author raghavsharda
 * @author harmandeep
 *@version 2.0
 */
public class InformationOfPlayerController {
	
	
	/**
	 * This function will give the information regarding the players-like number of players.
	 * In this function Spinner-model is  being used to define the minimum and maximum number of players
	 * as game play begins. 
	 * @param minimum number of minimum players
	 * @param Mesg message to be passed 
	 * @param	maximum maximum number of players
	 * @return This method will return the Number of players.
	 */
	public int NumberOfPlayer(int minimum,String Mesg,int maximum)
	{
		
       SpinnerModel S_M = new SpinnerNumberModel(minimum, minimum, maximum, 1);
       JSpinner jsm= new JSpinner(S_M);
       JPanel jp = new JPanel();
       jp.add(new JLabel("Enter Number Of Players"));
       jp.add(jsm);
       
       int getResult = JOptionPane.showConfirmDialog(null, jp, "Enter Number Of Players", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
       if (getResult == JOptionPane.OK_OPTION) {
    	   return (int) jsm.getValue();
    	   
       }
       return minimum;
	
	}

	/**
	 *  stores the player-names in game.
	 */
	public static String[] Name_Of_Player; 
	
	/**
	 * contains the information regarding the players
	 * @return returns the names-of-players in the form of a string array.
	 */
	public String[] informationOFPayres()
	{
		int n = NumberOfPlayer(2,"Player Numbers",6);
		System.out.println("Number-of-players are: " + n);
		
		 Name_Of_Player= new String[n];
		 JFrame jf = new JFrame("Player-Names");
		 
		jf.setBackground(Color.BLUE);
		 
		for (int i=1;i<=n;i++)
		 {
			 String name= (String)JOptionPane.showInputDialog(
						jf,
	                    "Enter name "+ i,
	                    "Player Information",
	                    JOptionPane.PLAIN_MESSAGE);
			 if((name!= null )&& (name.length()>0))
			 {
				 Name_Of_Player[i-1]=name; 
			 }
			
			 System.out.println(name);
		 }
		return Name_Of_Player;
	}

	/**
	 *The countries that are choosen by players to place armies in
	 * @param CL List of countries where the player can place armies.
	 * @param Msg message to be passed
	 * @return country name selected.
	 */
	public String ArmyPlacing(String[] CL, String Msg) {
		JComboBox<String> List_Of_countries = new JComboBox<String>(CL);
		String[] opt= {"O"};
		JOptionPane.showOptionDialog(null, CL, Msg, JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null,		
				opt, opt[0]);
		String country = CL[List_Of_countries.getSelectedIndex()];
		return country;
	}
}