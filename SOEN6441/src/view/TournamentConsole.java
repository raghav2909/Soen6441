package view;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class defines a menu for game for tournament-mode after entering specific information
 * for Tournament mode only maximum of 4 players can play
 * @author Gursharan
 * @version 2.0
*/

public class TournamentConsole extends JFrame{
	
	/**
	 * Jspinner to get player count
	 */
	private JSpinner countOfPlayer;
	
	/**
	 * shows the player panel
	 */
	private JPanel playerPanel;
	
	/**
	 * list contains behaviour of player
	 */
	private ArrayList<JComboBox<String>> behaviors;
	
	/**
	 * list for player names
	 */
	private ArrayList<JTextField> names;
	
	/**
	 * spinner to get count of game
	 */
	private JSpinner gameCount;
	
	/**
	 * spinner for count of moves
	 */
	private JSpinner countOfMoves;
	
	/**
	 * container for main frame
	 */
	private Container frame;
	
	/**
	 * button to submit information
	 */
	private JButton buttonToSubmit;
	
	/**
	 * spinner for count of map
	 */
	private JSpinner countForMap;
	
	/**
	 * panel for map
	 */
	private JPanel panelForMap;
	
	/**
	 * list for maps
	 */
	private ArrayList<JTextField> maps;
	
	 /**
	  * add action for countForMap 
	  */
	private void listenerFormapCount() {
		countForMap.addChangeListener( new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				addContentToMapPanel();
			}
		});
	}

	/**
	  * add action for countOfPlayer
	  */
	private void listenerForPlayerCount() {
		countOfPlayer.addChangeListener( new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				addContentToPlayerPanel();
			}
		});
	}

	/**
	 * shows the main tournament frame to get tournament information
	 */
	public TournamentConsole(){
		super("Enter details for tournament!");
		countForMap = new JSpinner(new SpinnerNumberModel(1,1,5,1));
		panelForMap = new JPanel();
		countOfPlayer= new JSpinner(new SpinnerNumberModel(2,2,4,1));
		playerPanel = new JPanel();
		gameCount = new JSpinner(new SpinnerNumberModel(1,1,5,1));
		countOfMoves = new JSpinner(new SpinnerNumberModel(10,10,50,1));
		buttonToSubmit = new JButton("OK!");
		frame = this.getContentPane();
		initTournament();
		listenerFormapCount();
		listenerForPlayerCount();
		addContentToPlayerPanel();
		addContentToMapPanel();
		this.pack();
		this.validate();
		this.setVisible(true);
	}
	
	/**
	 * sets the tournament information
	 */
	private void initTournament() {
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setLayout(new GridBagLayout());
		frame.add(panel);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(3,3,3,3);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Enter number of maps: "), c);
		c.gridx = 1;
		panel.add(countForMap, c);
		c.gridwidth = 2;
		c.gridy = 2;
		c.gridx = 0;
		panel.add(panelForMap, c);
		c.gridwidth = 1;
		c.gridy = 3;
		c.gridx = 0;
		panel.add(new JLabel("Enter number of players: "), c);
		c.gridx = 1;
		panel.add(countOfPlayer, c);
		c.gridwidth = 2;
		c.gridy = 4;
		c.gridx = 0;
		panel.add(playerPanel, c);
		c.gridwidth = 1;
		c.gridy = 5;
		c.gridx = 0;
		panel.add(new JLabel("Enter number of games to play on each map: "), c);
		c.gridx = 1;
		panel.add(gameCount, c);
		c.gridy = 6;
		c.gridx = 0;
		panel.add(new JLabel("Enter number of moves for each game: "), c);
		c.gridx = 1;
		panel.add(countOfMoves, c);
		c.gridy = 7;
		c.gridx = 0;
		panel.add(buttonToSubmit, c);
	}
	
	/**
	 * adds action for submit button
	 * @param actionListener action listener
	 */
	public void setListeners(ActionListener actionListener) {
		buttonToSubmit.addActionListener(actionListener);
	}
	
	/**
	 * adds the information to the map panel
	 */
	private void addContentToMapPanel() {
		panelForMap.removeAll();
		openingdialog sBox = new openingdialog();
		panelForMap.setLayout(new GridBagLayout());
		maps = new ArrayList<JTextField>();
		
		for(int i=0; i< (int) countForMap.getValue(); i++) {
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(2,2,2,2);
			c.gridx = 0;
			c.gridy = i;
			panelForMap.add(new JLabel("Select map "+(i+1)+": "), c);
			c.gridx = 1;
			JTextField map = new JTextField(15);
			JButton browse = new JButton("Browse!");
			browse.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					map.setText(sBox.getMapData("map"));
				}
			});
			map.setEnabled(false);
			maps.add(map);
			panelForMap.add(map, c);
			c.gridx = 2;
			panelForMap.add(browse,c);
		}
		revalidate();
		pack();
		repaint();
	}
	
	/**
	 * adds the information to player panel
	 */
	private void addContentToPlayerPanel() {
		playerPanel.removeAll();
		playerPanel.setLayout(new GridBagLayout());
		behaviors = new ArrayList<JComboBox<String>>();
		names = new ArrayList<JTextField>();
		String[] tempBeh = {"aggressive", "benevolent", "cheater", "human","random"};
		for(int i=0; i< (int) countOfPlayer.getValue(); i++) {
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = i;
			playerPanel.add(new JLabel("Player "+(i+1)+" behavior: "), c);
			c.gridx = 1;
			JComboBox<String> behavior = new JComboBox<String>(tempBeh);
			behaviors.add(behavior);
			playerPanel.add(behavior, c);
			c.gridx = 2;
			playerPanel.add(new JLabel(" Name: "),c);
			c.gridx = 3;
			JTextField name = new JTextField(8);
			names.add(name);
			playerPanel.add(name,c);
		}
		revalidate();
		pack();
		repaint();
	}
	
	/**
	 * gets the game count
	 * @return number of games
	 */
	public int getCountOfGames() {
		return (int) gameCount.getValue();
	}
	
	/**
	 * gets all details of map
	 * @return array of maps
	 */
	public String[] getDetailsOfMap() {
		ArrayList<String> b = new ArrayList<String>();
		for(JTextField cb: maps) {
			b.add(cb.getText());
		}
		return b.toArray(new String[b.size()]);
	}
	
	/**
	 * gets the all players behaviour
	 * @return array of behaviours
	 */
	public String[][] getDetailsOfPlayerBehavior() {
		ArrayList<String[]> b = new ArrayList<String[]>();
		for(int i=0;i<names.size();i++) {
			String[] temp = new String[2];
			temp[1] = (String) behaviors.get(i).getSelectedItem();
			temp[0] = names.get(i).getText();
			b.add(temp);
		}
		return b.toArray(new String[b.size()][]);
	}
	
	/**
	 * gets the number of moves
	 * @return count of moves
	 */
	public int getCountOfMoves() {
		return (int) countOfMoves.getValue();
	}
}