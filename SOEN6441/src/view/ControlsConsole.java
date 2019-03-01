package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
/**
 * 
 * @author raghavsharda
 *
 */
public class ControlsConsole extends JPanel {
	/**
	 * Number of armies available 
	 */
	private String AvailableArmies;
	/**
	 * creating the spinner for amries 
	 */
	private JSpinner spinnerArmies;
	/**
	 * Craeting combox box for list of countries
	 */
	JComboBox<String> ListOfCountries;
	/**
	 * combo box for neighbor List
	 */
	JComboBox<String> ListOfNeighbors;
	/**
	 * creating button to add armies
	 */
	JButton Add_Armies;
	/**
	 * JButton to end a phase
	 */
	JButton End_Phase;
	/**
	 * Control constructor for various control funtions
	 */
	/**
	 * button for the move of player
	 */
	JButton Player_Move;
	
	public ControlsConsole(){
		JLabel lab=  new JLabel("Controls");
		this.setLayout(new FlowLayout());
		this.add(lab);

		this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(BorderFactory.createDashedBorder(Color.black));
		this.setPreferredSize(new Dimension(450,170));
	}
	/**
	 * this displays the reinforcement part
	 * @param
	 * @param 
	 */
	public void reinforcementConrols(int ac, String[] cl) {
		this.removeAll();
		AvailableArmies = "Armies Available:" + String.valueOf(ac);
		System.out.println(ac);
		SpinnerModel s_m = new SpinnerNumberModel(1, 1, ac, 1); 
		spinnerArmies = new JSpinner(s_m);
		ListOfCountries = new JComboBox<String>(cl);
		ListOfCountries.setSelectedIndex(0);
		Add_Armies = new JButton("Add Armies");
		End_Phase = new JButton(" To End Reinforcement Phase");
		
		this.add(new Label("Armies Avaliable"));
		this.add(new Label("Country Name"));
		this.add(ListOfCountries);
		this.add(spinnerArmies);
		this.add(Add_Armies);
		this.add(End_Phase);
		this.revalidate();
		this.repaint();
	}
	/**
	 *  Fortification Phase is implemented by this function
	 * @param countryList String array that contains the names of the country owned by the current player.
	 */
	public void fortificationControls(String[] countryList) {
		this.removeAll();

		ListOfCountries = new JComboBox<String>(countryList);
		ListOfCountries.setSelectedIndex(0);
		
		ListOfNeighbors = new JComboBox<String>();
		ListOfNeighbors.setEnabled(false);;
		spinnerArmies = new JSpinner();
		spinnerArmies.setEnabled(false);;
		Player_Move = new JButton("Armie Movement");
		
		this.add(new Label("Country Name "));
		this.add(ListOfCountries);
		this.add(new Label("Neighbours Name"));
		this.add(ListOfNeighbors);
		this.add(spinnerArmies);
		this.add(Player_Move);
		
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * Function to add ActionListener on countries list combobox.
	 * @param ae ActionListener to be attached to the combobox.
	 */
	public void countrieslistAction(ActionListener ae) {
		this.ListOfCountries.addActionListener(ae);
	}
	/**
	 * Function to add action listener to Add_Amry
	 * @param actionlistener
	 */
	public void Armies_Add_Button_Action(ActionListener ae) {
		this.Player_Move.addActionListener(ae);
	}
	/**
	 * Sets ActionListener on end phase button.
	 * @param ae ActionListener to be attached to the button.
	 */
	public void End_Phase_Button_Action(ActionListener ae) {
		this.End_Phase.addActionListener(ae);
	}
	
	/**
	 * Function to add action listener to Player_Move
	 * @param actionlistener
	 */

	public void Play_Move_Button_Action(ActionListener ae) {
		this.Add_Armies.addActionListener(ae);
	
	
}
	public void updateFortification(int a, String[] NeighbourNames) {
		this.spinnerArmies.setModel(new SpinnerNumberModel(1, 0, a-1, 1));
		this.spinnerArmies.setEnabled(true);;
		this.ListOfNeighbors.setModel(new DefaultComboBoxModel<String>(NeighbourNames));
		this.ListOfNeighbors.setSelectedIndex(0);
		this.ListOfNeighbors.setEnabled(true);;
		this.Player_Move.setEnabled(true);;

}
	/**
	 * To Checks neighbor list combobox is enabled.
	 * @return boolean value
	 */
	public boolean isNeighbourSelected() {
		return this.ListOfNeighbors.isEnabled();
	}
	
	/**
	 * Gets the country selected.
	 * @return selected country.
	 */
	public String SelectedCountry() {
		return (String) this.ListOfCountries.getSelectedItem();
	}
	/**
	 * This gets the value of armies
	 * @return number of armies from spinner
	 */
	public int ValueOfArmies() {
		return (int) this.spinnerArmies.getValue();
	}
	/**
	 * Gets neighbor country
	 * @return neighbor country
	 */
	public String getNeighborSelected() {
		return (String) this.ListOfNeighbors.getSelectedItem();
	}
}

