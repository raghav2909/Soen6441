package Util;

import java.awt.Dimension;
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
public class GameLogging extends JFrame implements Observer {

	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = -8766321280014020596L;
	
	/**
	 * Panel for the logging window.
	 */
	private JPanel panel;
	
	private JScrollPane scroll;
	
	/**
	 * Constructor to initialize GameLogger
	 */
	public GameLogging(){
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		scroll = new JScrollPane(panel);
		scroll.setPreferredSize(new Dimension(400,800));
		this.add(scroll);
		this.pack();
		this.validate();
		this.setVisible(true);
	}

	/**
	 * Observer method called on notifyObservers for GameLogger.
	 */
	@Override
	public void update(Observable o, Object arg) {
		panel.add(new JLabel((String) arg));
		this.validate();
	}
	
	public static void main(String[] s) {
		GameLogging g = new GameLogging();
		for(int i=0;i<60;i++) {
			g.update(null,"Line "+i);
		}
	}
	
}
