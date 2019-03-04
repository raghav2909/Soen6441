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
 * class containing methods for various controls in reinforcement and fortification phase
 * @author raghavsharda
 *
 */
public class ControlsConsole extends JPanel {
	/**
	 * Number of armies available
	 */
	private String AvailableArmies;
	
	/**
	 * creating the spinner for armies
	 */
	private JSpinner spinnerArmies;
	
	/**
	 * creating combo-box for list of countries
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
	 * GameDriver constructor for various control functions
	 */
	GameDriver GD;
	
	/**
	 * button for the move of player
	 */
	JButton Player_Move;
	
	/**
	 * action listener to add army
	 */
	private ActionListener addarmies;

	/**
	 * serial version id for JFrame
	 */
	private static final long serialVersionUID = -2537153242382941763L;

	/**
	 * constructor to display control view on main frame
	 */
	public ControlsConsole() {
		JLabel lab = new JLabel("Controls");
		this.setLayout(new FlowLayout());
		this.add(lab);

		this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(BorderFactory.createDashedBorder(Color.black));
		this.setPreferredSize(new Dimension(450, 170));
	}

	/**
	 * displays the reinforcement part
	 * 
	 * @param ac armies available for reinforcement part to the player
	 * @param cl array containing all countries owned by current player
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
	 * implements fortification phase
	 * 
	 * @param countryList array containing country names owned by current player
	 */
	public void fortificationControls(String[] countryList) {
		this.removeAll();

		ListOfCountries = new JComboBox<String>(countryList);
		ListOfCountries.setSelectedIndex(0);

		ListOfNeighbors = new JComboBox<String>();
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
	 * adds ActionListener on countries list combo-box.
	 * 
	 * @param ae ActionListener to be attached to the combo-box.
	 */
	public void countrieslistAction(ActionListener ae) {
		this.ListOfCountries.addActionListener(ae);
	}

	/**
	 * adds action listener to Add_Amry
	 * 
	 * @param ae ActionListener attached to add army button
	 */
	public void Armies_Add_Button_Action(ActionListener ae) {
		this.Player_Move.addActionListener(ae);
	}

	/**
	 * Sets ActionListener on end phase button.
	 * 
	 * @param ae ActionListener attached to the end phase button.
	 */
	public void End_Phase_Button_Action(ActionListener ae) {
		this.End_Phase.addActionListener(ae);
	}

	/**
	 * adds action listener to Player_Move
	 * 
	 * @param ae ActionListener attached to player move button
	 */
	public void Play_Move_Button_Action(ActionListener ae) {
		this.Add_Armies.addActionListener(ae);
	}

	/**
	 * fortification control updated when needed
	 * @param count contains number of armies of a player
	 * @param Neighbours array containing list of neighbouring countries of country selected
	 */
	public void updateFortification(int count, String[] Neighbours) {
		this.spinnerArmies.setModel(new SpinnerNumberModel(1, 0, count - 1, 1));
		this.spinnerArmies.setEnabled(true);
		;
		this.ListOfNeighbors.setModel(new DefaultComboBoxModel<String>(Neighbours));
		this.ListOfNeighbors.setSelectedIndex(0);
		this.ListOfNeighbors.setEnabled(true);
		;
		this.Player_Move.setEnabled(true);
		;

	}

	/**
	 * Checks for neighbor list combo-box enabled or not.
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
	 * gets the value of armies
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

		/**
		 * Sets Action Listeners for play move button
		 */
		Play_Move_Button_Action(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (isNeighbourSelected()) {
					String countrySelected = (String) SelectedCountry();
					int selectedArmies = ValueOfArmies();
					//NodeOfCountry countrySelect = NodeOfCountry.getCountry(pla,countrySelected));
					NodeOfCountry countrySelect = GameDriver.getInstance().getCurrent().getCountry(countrySelected);
					String neighbourSelected = getNeighborSelected();
					getArmiesShiftedAfterFortification(countrySelect, neighbourSelected, selectedArmies);
				}
				GD.ChangePhase();
			}
		});

	}

	/**
	 * gets neighbor countries for a selected country owned by current player 
	 * @param info object of class whose neighbors are to be displayed
	 * @return neighbor countries
	 */
	public ArrayList<String> getNeighbors(NodeOfCountry info) {
		ArrayList<String> neighborList = new ArrayList<String>();
		for (String name : info.getPlayerNeighboursName()) {
			neighborList.add(name);
		}
		return neighborList;
	}

	/**
	 * shift the player armies after fortification
	 * @param info object of class whose neighbors are to be displayed
	 * @param neighbourSelected contains selected neighbor 
	 * @param selectedArmies contains number of armies selected
	 * @return shifted army count for player
	 */
	public int getArmiesShiftedAfterFortification(NodeOfCountry info, String neighbourSelected,
			int selectedArmies) {
		NodeOfCountry required = null;
		info.SetArmies(info.getArmyCount() - selectedArmies);
		for (NodeOfCountry j : info.getNeighboursCountries()) {
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
	 * @param country the country node to which armies are added.
	 * @param armies the number of armies to be reinforced.
	 * @return the army count left for the player.
	 */
	public int shiftArmiesOnReinforcement(NodeOfCountry country, int armies) {
		country.AddArmy(armies);
		GD.getCurrent().RemovedArmies(armies);
		return GD.getArmyCount();
	}
	
}
