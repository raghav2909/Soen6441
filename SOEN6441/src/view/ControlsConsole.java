package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import Model.GameDriver;
import Model.NodeOfCountry;

/**
 * class containing methods for all controls in reinforcement and fortification phase
 * @author raghavsharda
 * @version 2.0
 */
 public class ControlsConsole extends JPanel {
	
	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = -2537156060382941763L;
	
	/**
	 * display armies count available to the player for Phases of Reinforcement Phase, Fortification and attack
	 */
	private JSpinner spinnerForArmies;
	
	/**
	 * Spinner to display armies count available to the player for his attack-phase.
	 */
	private JSpinner spinnerForArmies2;
	
	/**
	 * ComboBox to display the countries owned by the current player.
	 */
	private JComboBox<String> countriesList;
	
	/**
	 * Button for the fortification phase move.
	 */
	private JButton 	movePlayButton;
	
	/**
	 * Dropdown for list of neighbors.
	 */
	private JComboBox<String> neighborList;
	
	/**
	 * Armies available to the player for reinforcement phase.
	 */
	private String availableArmies;
	
	/**
	 * Number of armies selected to move to the neighboring country for Fortification phase.
	 */
	private JButton ArmiesToAddButton;
	
	/**
	 * Button to end a phase.
	 */
	private JButton endPhaseButton;
	
	/**
	 * Button to save game.
	 */
	private JButton saveGameButton;
	
	/**
	 * Constructor to display the Control section of the game for Reinforcement, Attack and Fortification phases.
	 */
	public ControlsConsole() {
		JLabel label = new JLabel("Controls are Here.");
		this.setLayout(new FlowLayout());
		saveGameButton = new JButton("Save Game!");
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(label);
		
		
	}
	
	/**
	 * Displays the Reinforcement Phase controls.
	 * @param armiesCount Number of armies available to the player for Reinforcement phase.
	 * @param countryList String array containing the countries owned by the current player.
	 */
	public void controlsReinforcement(int armiesCount, String[] countryList) {
		this.removeAll();

		System.out.print("Checkpoint 3");
		availableArmies = "Armies Available:" + String.valueOf(armiesCount);
		System.out.println(armiesCount);
		SpinnerModel sm = new SpinnerNumberModel(1, 1, armiesCount, 1); 
		spinnerForArmies = new JSpinner(sm);
		countriesList = new JComboBox<String>(countryList);
		countriesList.setSelectedIndex(0);
		ArmiesToAddButton = new JButton("Add Armies");
		
		this.add(new Label(availableArmies));
		this.add(new Label("Country"));
		this.add(countriesList);
		this.add(spinnerForArmies);
		this.add(ArmiesToAddButton);
		this.add(saveGameButton);
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * This function implements the Fortification Phase.
	 * @param countryList String array containing the names of the countries owned by the current player.
	 */
	public void controlsFortification(String[] countryList) {
		this.removeAll();

		countriesList = new JComboBox<String>(countryList);
		countriesList.setSelectedIndex(0);
		
		neighborList = new JComboBox<String>();
		neighborList.setEnabled(false);
		spinnerForArmies = new JSpinner();
		spinnerForArmies.setEnabled(false);
		movePlayButton = new JButton("Move Armies");
		
		this.add(new Label("Country "));
		this.add(countriesList);
		this.add(new Label("Neighbours"));
		this.add(neighborList);
		this.add(spinnerForArmies);
		this.add(movePlayButton);
		this.add(saveGameButton);
		
		this.revalidate();
		this.repaint();
	}

	/**
	 * Play a move for fortification phase.
	 * @param a ActionListener for the movePlayButton button.
	 */
	public void actionForPlayButton(ActionListener a) {
		this.movePlayButton.addActionListener(a);
	}
	/**
	 * Sets ActionListener on countries list combobox.
	 * @param actionNew ActionListener to be attached to the combobox.
	 */
	public void actionForCountriesList(ActionListener actionNew) {
		this.countriesList.addActionListener(actionNew);
	}
	
	/**
	 * Sets ActionListener on save game button.
	 * @param actionNew ActionListener to be attached to the button.
	 */
	public void actionForSaveGameButton(ActionListener actionNew){
		this.saveGameButton.addActionListener(actionNew);
	}
	
	/**
	 * Sets ActionListener on add armies button.
	 * @param actionNew ActionListener to be attached to the button.
	 */
	public void actionForAddArmiesButton(ActionListener actionNew) {
		this.ArmiesToAddButton.addActionListener(actionNew);
	}
	
	/**
	 * Sets ActionListener on end phase button.
	 * @param actionNew ActionListener to be attached to the button.
	 */
	public void actionForEndPhase(ActionListener actionNew) {
		this.endPhaseButton.addActionListener(actionNew);
	}
	
	
	
	/**
	 * Update fortification control view whenever required.
	 * @param armies Count of the armies the player has.
	 * @param neighbourNames List of neighbors of the country selected.
	 */
	public void fortificationUpdate(int armies, String[] neighbourNames) {
		this.spinnerForArmies.setModel(new SpinnerNumberModel(1, 0, armies-1, 1));
		this.spinnerForArmies.setEnabled(true);
		putListOfNeighbours(neighbourNames);
	}
	/**
	 * This method reset the controls for attack phase
	 * @param countriesNames list of countries
	 */
	public void controlsForAttack(String[] countriesNames) {
		this.removeAll();
		countriesList = new JComboBox<String>(countriesNames);
		countriesList.setSelectedIndex(0);
		neighborList = new JComboBox<String>();
		neighborList.setEnabled(false);
		movePlayButton = new JButton("Announce attack");
		movePlayButton.setEnabled(false);
		endPhaseButton = new JButton("Skip attack");
		
		this.add(new Label("Country "));
		this.add(countriesList);
		this.add(new Label("Neighbours"));
		this.add(neighborList);
		this.add(movePlayButton);
		this.add(endPhaseButton);
		this.add(saveGameButton);
		this.revalidate();
		this.repaint();
	}
	/**
	 * This method set list of neighbors to neighborList combobox.
	 * @param newNeighbourNames list of neighbors
	 */
	public void putListOfNeighbours(String[] newNeighbourNames) {
		this.neighborList.setModel(new DefaultComboBoxModel<String>(newNeighbourNames));
		this.neighborList.setSelectedIndex(0);
		this.neighborList.setEnabled(true);
		this.movePlayButton.setEnabled(true);
	}
	
	/**
	 * Gets the value of armies from the spinner.
	 * @return integer value from the spinner.
	 */
	public int getValueForArmies() {
		return (int) this.spinnerForArmies.getValue();
	}
	
	/**
	 * Gets the country selected in the combobox.
	 * @return country selected in the combobox.
	 */
	public String getSelectedCountry() {
		return (String) this.countriesList.getSelectedItem();
	}

	/**
	 * Gets the neighbor selected in the combobox.
	 * @return neighbor selected in the combobox.
	 */
	public String getSelectedNeighbor() {
		return (String) this.neighborList.getSelectedItem();
	}
	
	/**
	 * Checks if neighbor list combobox is enabled or not.
	 * @return boolean value depending on the combobox enabled or not.
	 */
	public boolean ifNeighbourSelected() {
		return this.neighborList.isEnabled();
	}
	
	
		
}
