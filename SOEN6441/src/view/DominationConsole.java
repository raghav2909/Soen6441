package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.JPanel;

import Model.GameDriver;
import player.Player;

/**
 * This class will implement the domination by different players in the world.
 * @author raghavsharda
 * @version 2.0
 *
 */
public class DominationConsole extends JPanel implements Observer {
	/**
	 * serial version id
	 */

	private static final long serialVersionUID = -1084389039052002294L;
	
	 public DominationConsole() {
		JLabel lbl= new JLabel("<html><b><I> Domination of players</I></B></html>");
	this.setLayout(new FlowLayout());
	this.add(lbl);
	this.setBorder(BorderFactory.createDashedBorder(Color.BLUE));
	 
	 
	 }
	/**
	 * observer pattern function to update when there is a notification.
	 */
	
	@Override
	public void update(Observable o, Object notify) {
		if(notify.equals("Reinforcement")|| notify.equals("Fortication"))
		{
			this.removeAll();
			JLabel jbl= new JLabel("<html><b><I> Domination of players</I></B></html>");
		this.add(jbl);
		int Countries_total;
	Countries_total= GameDriver.GetInit().GetMap().CountryCount();
	System.out.println("Total Country: "+Countries_total);
	for(Player p : GameDriver.GetInit().getAllPlayers())
	{
		int n= p.getPlayerCountryNumber();
		System.out.println(n);
		String name= p.getPlayerName();
		float x=((float)(p.getPlayerCountryNumber()));
			float y=(float)Countries_total;
			float z= (x/y)%100;
			this.add(new JLabel(" "+ name+": "+z+ "% "));
			}
		
	}
		}
	}