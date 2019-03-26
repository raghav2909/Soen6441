package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.GameDriver;
import player.Player;

/**
 * Implements the Phase View panel of the main window using Observer Pattern.
 * @author Harman
 * @version 2.0
 */
public class phaseConsole extends JPanel implements Observer {

	private static  final long serialVersionUID = 5240015440245324453L;
	/**
	 * Constructor to initialize view of phase
	 */
	public phaseConsole() {
		JLabel label = new JLabel("PHASE CONSOLE");
		this.setLayout(new FlowLayout());
		this.add(label);
		this.setBorder(BorderFactory.createDashedBorder(Color.BLUE));
	}
	
	@Override
	public void update(Observable o, Object obj) {
		// TODO Auto-generated method stub
		Player current = GameDriver.GetInit().GetCurrent();
		/*Startup Phase View Display.*/
		if(obj.equals("Startup")){
			this.removeAll();
			this.add(new JLabel("<html><div><b>Startup Phase</b></div><br/><br/></html>"));
		}
		/*Cards Exchange View Display.*/
		else if(obj.equals("Cards")){
			this.removeAll();
			this.add(new JLabel("<html><div><b>Cards Exchange</b></div><br/><br/></html>"));
			this.add(new JLabel("Player: "));
			this.add(new JLabel(current.getPlayerName()));
			this.add(new JLabel(" can exchange cards to get more armies  "));
		}
		/*Reinforcement Phase View Display.*/
		else if(obj.equals("Reinforcement")){
			this.removeAll();
			this.add(new JLabel("<html><div><b>Reinforcement Phase</b></div><br/><br/></html>"));
			this.add(new JLabel("Player:"));
			this.add(new JLabel(current.getPlayerName()));
			this.add(new JLabel("Armies:"));
			this.add(new JLabel(String.valueOf(current.getCountArmies())));
			this.add(new JLabel("Countries:"));
			String[] countries = current.getNameOfCountries();
			for(String s: countries){
				this.add(new JLabel(s));
			}
		}
		/*Attack Phase View Display.*/
		else if(obj.toString().contains("Attack")){
			this.removeAll();
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			this.add(new JLabel("<html><div><b>Attack Phase</b></div><br/><br/></html>"));
			this.add(new JLabel("Player: "+ current.getPlayerName()));
			if(obj.toString().length() > 7){
				String words[] = obj.toString().substring(6).split("<br>");
				for( String word : words){
					this.add(new JLabel(word));
				}
			}
		}
		/*Fortification Phase View Display.*/
		else if(obj.equals("Fortification")){
			this.removeAll();
			this.setLayout(new FlowLayout());
			this.add(new JLabel("<html><div><b>Fortification Phase</b></div><br/><br/></html>"));
			this.add(new JLabel("Player: "));
			this.add(new JLabel(current.getPlayerName()));
			this.add(new JLabel("Player can move armies to other country once"));
		}
		/*Players Display for Startup Phase.*/
		else{
			this.add(new JLabel("<html><div>Player:</div></html>"));
			this.add(new JLabel((String) obj));
		}
		System.out.println("Observer");
		this.validate();
	}

}
