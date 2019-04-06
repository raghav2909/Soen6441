package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

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
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = -1084389704252002294L;

	/**
	 * Creates World Domination View on the Main frame
	 */
	public DominationConsole() {
		JLabel label = new JLabel("<html><b>World Domination View</b></html>");
		this.setLayout(new FlowLayout());
		this.add(label);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	/**
	 * Observer pattern function for Observers to update when there is a notification from the observable.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(arg.equals("Reinforcement") || arg.equals("Fortification")){
			this.removeAll();
			JLabel label = new JLabel("<html><b>World Domination View</b></html>");
			this.add(label);
			
			int totalCountries = ((GameDriver) o).getMap().getCountryCount();
			System.out.println(totalCountries);
			for (Player player : ((GameDriver) o).getPlayers()){
				System.out.println(player.getPlayerCountryCount());
				this.add(new JLabel(" "+player.getName() + ": " + ((float)(player.getPlayerCountryCount()/(float)totalCountries))*100 + "% "));
			}
		}
	}
}
