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
	private JComboBox<String> ListOfNeighbors;
	/**
	 * creating button to add armies
	 */
	private JButton Add_Armies;
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
	private ActionListener addarmies;
	GameDriver GD;
	JButton Player_Move;
	private static final long serialVersionUID = -2537153242382941763L;

	public ControlsConsole() {
		JLabel lab = new JLabel("Controls");
		this.setLayout(new FlowLayout());
		this.add(lab);
		GD = GameDriver.getInstance();
		ListOfNeighbors = new JComboBox<String>();
		this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(BorderFactory.createDashedBorder(Color.black));
		this.setPreferredSize(new Dimension(450, 170));
	}

	/**
	 * this displays the reinforcement part
	 * 
	 * @param
	 * @param
	 */
	public void reinforcementConrols(int ac, String[] cl) {
		this.removeAll();
		System.out.println("Reinforcement method start");
		AvailableArmies = "Armies Available:" + String.valueOf(ac);
		System.out.println(ac);
		SpinnerModel s_m = new SpinnerNumberModel(1, 1, ac, 1);
		spinnerArmies = new JSpinner(s_m);
		ListOfCountries = new JComboBox<String>(cl);
		ListOfCountries.setSelectedIndex(0);
		Add_Armies = new JButton("Add Armies");
		End_Phase = new JButton(" To End Reinforcement Phase");
		this.ListOfNeighbors.setModel(new DefaultComboBoxModel<String>(cl));

		this.add(new Label(AvailableArmies));
		this.add(new Label("Country Name"));
		this.add(ListOfCountries);
		this.add(spinnerArmies);
		this.add(Add_Armies);
		this.add(End_Phase);
		this.revalidate();
		this.repaint();
	}

	/**
	 * Fortification Phase is implemented by this function
	 * 
	 * @param countryList String array that contains the names of the country owned
	 *                    by the current player.
	 */
	public void fortificationControls(String[] countryList) {
		this.removeAll();

		ListOfCountries = new JComboBox<String>(countryList);
		ListOfCountries.setSelectedIndex(0);

		ListOfNeighbors.setEnabled(false);
		spinnerArmies = new JSpinner();
		spinnerArmies.setEnabled(false);
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
	 * 
	 * @param ae ActionListener to be attached to the combobox.
	 */
	public void countrieslistAction(ActionListener ae) {
		this.ListOfCountries.addActionListener(ae);
	}

	/**
	 * Function to add action listener to Add_Amry
	 * 
	 * @param actionlistener
	 */
	public void Armies_Add_Button_Action(ActionListener ae) {
		this.Player_Move.addActionListener(ae);
	}

	/**
	 * Sets ActionListener on end phase button.
	 * 
	 * @param ae ActionListener to be attached to the button.
	 */
	public void End_Phase_Button_Action(ActionListener ae) {
		this.End_Phase.addActionListener(ae);
	}

	/**
	 * Function to add action listener to Player_Move
	 * 
	 * @param actionlistener
	 */

	public void Play_Move_Button_Action(ActionListener ae) {
		this.Add_Armies.addActionListener(ae);

	}

	public void updateFortification(int a, String[] NeighbourNames) {
		this.spinnerArmies.setModel(new SpinnerNumberModel(1, 0, a - 1, 1));
		this.spinnerArmies.setEnabled(true);
		this.ListOfNeighbors.setModel(new DefaultComboBoxModel<String>(NeighbourNames));
		this.ListOfNeighbors.setSelectedIndex(0);
		this.ListOfNeighbors.setEnabled(true);
		this.Player_Move.setEnabled(true);

	}

	/**
	 * To Checks neighbor list combobox is enabled.
	 * 
	 * @return boolean value
	 */
	public boolean isNeighbourSelected() {
		return this.ListOfNeighbors.isEnabled();
	}

	/**
	 * Gets the country selected.
	 * 
	 * @return selected country.
	 */
	public String SelectedCountry() {
		return (String) this.ListOfCountries.getSelectedItem();
	}

	/**
	 * This gets the value of armies
	 * 
	 * @return number of armies from spinner
	 */
	public int ValueOfArmies() {
		return (int) this.spinnerArmies.getValue();
	}

	/**
	 * Gets neighbor country
	 * 
	 * @return neighbor country
	 */
	public String getNeighborSelected() {
		return (String) this.ListOfNeighbors.getSelectedItem();
	}

	/**
	 * Sets Action Listeners for fortification controls.
	 */
	public void setListenersFortification() {
		countrieslistAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String countrySelected = (String) SelectedCountry();
				NodeOfCountry countrySelect = GameDriver.getInstance().getCurrent().getCountry(countrySelected);
				if (countrySelect.getArmyCount() > 1) {
					ArrayList<String> neighborList = getNeighbors(countrySelect);
					updateFortification(countrySelect.getArmyCount(),
							neighborList.toArray(new String[neighborList.size()]));
				}
			}
		});

		Play_Move_Button_Action(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (isNeighbourSelected()) {
					String countrySelected = (String) SelectedCountry();
					int selectedArmies = ValueOfArmies();
					// NodeOfCountry countrySelect = NodeOfCountry.getCountry(pla,countrySelected));
					NodeOfCountry countrySelect = GameDriver.getInstance().getCurrent().getCountry(countrySelected);
					String neighbourSelected = getNeighborSelected();
					System.out.println("ROHIT " + countrySelect + "neighbourSelected" + neighbourSelected
							+ "selectedArmies" + selectedArmies);
					shiftArmiesOnReinforcement(countrySelect, selectedArmies);
				}
				GD.ChangePhase();
			}
		});

	}

	public ArrayList<String> getNeighbors(NodeOfCountry countrySelect) {
		ArrayList<String> neighborList = new ArrayList<String>();
		for (String name : countrySelect.getPlayerNeighboursName()) {
			neighborList.add(name);
		}
		return neighborList;
	}

	public int getArmiesShiftedAfterFortification(NodeOfCountry countrySelect, String neighbourSelected,
			int selectedArmies) {
		NodeOfCountry required = null;
		countrySelect.SetArmies(countrySelect.getArmyCount() - selectedArmies);
		for (NodeOfCountry j : countrySelect.getNeighboursCountries()) {
			if (j.getNameOfCountry() == neighbourSelected) {
				required = j;
				j.SetArmies(j.getArmyCount() + selectedArmies);
			}
		}
		return required.getArmyCount();
	}

	/**
	 * Sets Action Listeners for reinforcement controls.
	 */
	public void setActionListner() {
		addarmies = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NodeOfCountry country = GameDriver.getInstance().getCountryNode(SelectedCountry());
				int armies = ValueOfArmies();
				shiftArmiesOnReinforcement(country, armies);
				GD.ContinuePhase();
			}
		};
		Armies_Add_Button_Action(this.addarmies);

		End_Phase_Button_Action(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GD.ChangePhase();
			}
		});
	}

	/**
	 * Shifts(or places) the armies of the player on each reinforcement.
	 * 
	 * @param country the country node to which armies are added.
	 * @param armies  the number of armies to be reinforced.
	 * @return the army count left for the player.
	 */
	public int shiftArmiesOnReinforcement(NodeOfCountry country, int armies) {
		country.AddArmy(armies);
		GD.getCurrent().RemovedArmies(armies);
		return GD.getArmyCount();
	}

}
