package Util;

import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Model.GameDriver;
import player.Player;

/**
 * this class handle the game logger 
 * @author samansoltani
 * @version 2.0
 */
public class Logger extends JFrame implements Observer {
	/**
	 * Serial Version for JFrame
	 */
	private static final long serialVersionUID = -8766321280012020596L;
	
	/**
	 * logging window frame.
	 */
	private JPanel frame;
	/**
	 * Constructor to start Logger
	 */
	public Logger(){
		frame = new JPanel();
		frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
	}

	/**
	 *  calling on notifyObservers for Logger.
	 */
	public void update(Observable obs, Object x) {
		Player CurrentPlayer = GameDriver.GetInit().GetCurrent();
		if(x.equals("Startup")){
			frame.add(new JLabel("<html><div><b>Startup Phase</b></div><br/><br/></html>"));
		}
		
		else if(x.equals("Cards")){
			frame.add(new JLabel("<html><p></p><br/><br/></html>"));
			frame.add(new JLabel("<html><div><b>Cards Exchange</b></div><br/><br/></html>"));
			frame.add(new JLabel("Player: "));
			frame.add(new JLabel(CurrentPlayer.getPlayerName()));
			frame.add(new JLabel(" player is able to exchange card for more armies  "));
		}
		
		else if(x.equals("Reinforcement")){
			frame.add(new JLabel("<html><p></p><br/><br/></html>"));
			if(!(CurrentPlayer.AMoved > 0)){
				frame.add(new JLabel("<html><p><b>Reinforcement Phase</b></p><br/><br/></html>"));
				frame.add(new JLabel("Player:"));
				frame.add(new JLabel(CurrentPlayer.getPlayerName()));
				frame.add(new JLabel("Armies:"));
				frame.add(new JLabel(String.valueOf(CurrentPlayer.getCountArmies())));
				frame.add(new JLabel("Countries:"));
				String[] countries = CurrentPlayer.getNameOfCountries();
				for(String s: countries){
					frame.add(new JLabel(s));
				}
			}
			if(CurrentPlayer.AMoved>0){
				frame.add(new JLabel(String.valueOf(CurrentPlayer.AMoved)+" armies Reinforced to: "+CurrentPlayer.ChosenCountry));
			}
		}
		
		else if(x.toString().contains("Attack")){
			frame.add(new JLabel("<html><p></p><br/><br/></html>"));
			frame.add(new JLabel("<html><p><b>Attack Phase</b></p><br/><br/></html>"));
			frame.add(new JLabel("Player: "+ CurrentPlayer.getPlayerName()));
			if(x.toString().length() > 7){
				String words[] = x.toString().substring(6).split("<br>");
				for( String word : words){
					frame.add(new JLabel(word));
				}
			}
		}
		
		else if(x.equals("Fortification")){
			frame.add(new JLabel("<html><p></p><br/><br/></html>"));
			frame.add(new JLabel("<html><p><b>Fortification Phase</b></p><br/><br/></html>"));
			frame.add(new JLabel("Player: "));
			frame.add(new JLabel(CurrentPlayer.getPlayerName()));
			frame.add(new JLabel(" Armies moved from: " + CurrentPlayer.getSelectCountry().getNameOfCountry()+ " to "+ CurrentPlayer.getSelectNeighbour().getNameOfCountry()));
		}
		
		else{
			frame.add(new JLabel("<html><p>Player:</p></html>"));
			frame.add(new JLabel((String) x));
		}
		frame.add(new JLabel("<html><p></p><br/><br/></html>"));
		System.out.println("Observer");
		this.setSize(400, 800);
		JScrollPane scroll = new JScrollPane(frame);
		scroll.setPreferredSize(getSize());
		this.add(scroll);
		this.validate();
		this.setVisible(true);
	}

}
