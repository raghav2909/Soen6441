/**
 * 
 */
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
 *@version 1.0
 */
public class Player_Information_Controller {
	/**
	 * This array stores the player names.
	 */
	public static String[] Name_Of_Player;
	/**
	 * 
	 */
	
	/**
	 * This function will give the information regarding the player.
	 * In this function Spinner  model is used to define the minimum and maximum number of players
	 * when the user starts the game play. 
	 * @return This method will return the Number of players.
	 */
	public static int NumberOfPlayer()
	{
		JPanel jp = new JPanel();
       SpinnerModel S_M = new SpinnerNumberModel(2, 2, 4, 1);
       JSpinner jsm= new JSpinner(S_M);
       jp.add(new JLabel("Enter Number Of Players"));
       jp.add(jsm);
       
       int result = JOptionPane.showConfirmDialog(null, jp, "Enter Number Of Players", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
       if (result == JOptionPane.OK_OPTION) {
    	   return (int) jsm.getValue();
    	   
       }
       return 2;
	
	}
	/**
	 * This function will give the information regarding the players
	 * @return It will return the names of players as string array
	 */
	

	public static String[] Information_OF_Playres()
	{
		int n = NumberOfPlayer();
		System.out.println("Number of players" + n);
		
		 Name_Of_Player= new String[n];
		 JFrame jf = new JFrame("Player Names");
		 
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
	 *The countries that are selected to place army
	 * @param countryList List of countries where the player can place armies.
	 * @return country name selected.
	 */
	public String ArmyPlacing(String[] CL) {
		JComboBox<String> List_Of_countries = new JComboBox<String>(CL);
		String country = CL[List_Of_countries.getSelectedIndex()];
		return country;
	}
}
