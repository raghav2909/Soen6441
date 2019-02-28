/**
 * 
 */
package view;
import controllers.*;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JList;


import Model.*;


/**
 * This class opens the view for addition and deletion of continents and countries while creating new map 
 * @author raghavsharda
 *@author Gursharan
 */
public class NewMapCreation extends JFrame {
	/**
	 * NewMap constructor calls begin method of the class
	 */
	private WriteMap mapWriter = new WriteMap();
	private NodeOfMap nodeOfMap;
	public NewMapCreation() {
		begin();
	}
		private JPanel MaincontentPane;
		private JTextField continentNameText;
		private JTextField continentControlValueText;
		private JTextField countryNameText;
		
		private JComboBox continentComboBox;
		private JComboBox countryListCombobox;
		private JComboBox continentDeletionComboBox;
		private JComboBox countriesComboBox;
				
		private String continentName;
		private String cv;
		private JList list;
		
		private DefaultListModel<String> model2;
		
		private JButton neighbourChooseButton;
		private JButton btnComplete;
		private JButton addNeighboursButton;
		private JButton deleteContinentButton;
		private JButton saveMapButton;
		private JButton deleteCountryButton;
		
		private JButton addButton;
		private JButton addCountryButton;
		private JButton addContinentButton;
		
		/**
		 * all the contents of the frame are initialized.
		 */
		@SuppressWarnings("Not Checked!")
		public void begin() {
			this.setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(130, 130, 480, 320);
			this.setTitle("New-Map");
			this.setExtendedState(JFrame.MAXIMIZED_BOTH); 

			
			
			GridBagLayout grid_contentPane = new GridBagLayout();
			grid_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
			grid_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			grid_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
			grid_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			MaincontentPane.setLayout(grid_contentPane);
			MaincontentPane = new JPanel();
			MaincontentPane.setBackground(Color.LIGHT_GRAY);
			MaincontentPane.setBorder(new EmptyBorder(6, 6, 6, 6));
			setContentPane(MaincontentPane);

			

			JButton addContinentButton = new JButton("New Continent");
			addContinentButton.setForeground(Color.blue);
			GridBagConstraints grid_addContinentButton = new GridBagConstraints();
			grid_addContinentButton.fill = GridBagConstraints.HORIZONTAL;
			grid_addContinentButton.insets = new Insets(0, 0, 5, 5);
			grid_addContinentButton.gridx = 1;
			grid_addContinentButton.gridy = 2;
			addContinentButton.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 17));
			addContinentButton.setBackground(new Color(240, 255, 255));
			
			MaincontentPane.add(addContinentButton, grid_addContinentButton);
			JLabel createOwnMapLabel = new JLabel("Create own map for Game!");
			createOwnMapLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 48));
			GridBagConstraints grid_createOwnMapLabel = new GridBagConstraints();
			grid_createOwnMapLabel.insets = new Insets(0, 0, 5, 0);
			grid_createOwnMapLabel.gridwidth = 6;
			grid_createOwnMapLabel.gridx = 0;
			grid_createOwnMapLabel.gridy = 0;
			MaincontentPane.add(createOwnMapLabel, grid_createOwnMapLabel);

			JLabel continentNameLabel = new JLabel("Continent Name");
			GridBagConstraints global_continentNameLabel = new GridBagConstraints();
			global_continentNameLabel.anchor = GridBagConstraints.EAST;
			global_continentNameLabel.insets = new Insets(0, 0, 5, 5);
			global_continentNameLabel.gridx = 5;
			global_continentNameLabel.gridy = 2;
			MaincontentPane.add(continentNameLabel, global_continentNameLabel);

			continentNameText = new JTextField();
			continentNameText.setFont(new Font("Bookman Old Style", Font.ITALIC, 13));
			continentNameText.setForeground(Color.BLUE);
			GridBagConstraints gbc_continentNameText = new GridBagConstraints();
			gbc_continentNameText.insets = new Insets(0, 0, 5, 0);
			gbc_continentNameText.fill = GridBagConstraints.HORIZONTAL;
			gbc_continentNameText.gridx = 6;
			gbc_continentNameText.gridy = 2;
			continentNameText.setEnabled(false);
			MaincontentPane.add(continentNameText, gbc_continentNameText);
			continentNameText.setColumns(10);

			continentControlValueText = new JTextField();
			continentControlValueText.setFont(new Font("Bookman Old Style", Font.ITALIC, 13));
			continentControlValueText.setForeground(Color.BLUE);
			continentControlValueText.setEnabled(false);
			GridBagConstraints global_continentControlValueText = new GridBagConstraints();
			global_continentControlValueText.insets = new Insets(0, 0, 5, 0);
			global_continentControlValueText.fill = GridBagConstraints.HORIZONTAL;
			global_continentControlValueText.gridx = 6;
			global_continentControlValueText.gridy = 3;
			MaincontentPane.add(continentControlValueText, global_continentControlValueText);
			continentControlValueText.setColumns(10);

			JLabel controlValueLabel = new JLabel("Control Value");
			GridBagConstraints gbc_lblValue = new GridBagConstraints();
			gbc_lblValue.anchor = GridBagConstraints.EAST;
			gbc_lblValue.insets = new Insets(0, 0, 5, 5);
			gbc_lblValue.gridx = 5;
			gbc_lblValue.gridy = 3;
			MaincontentPane.add(controlValueLabel, gbc_lblValue);

			btnComplete = new JButton("Add Continent");
			btnComplete.setBackground(new Color(240, 255, 255));
			btnComplete.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
			btnComplete.setForeground(Color.BLUE);
			GridBagConstraints global_btnComplete = new GridBagConstraints();
			global_btnComplete.anchor = GridBagConstraints.EAST;
			global_btnComplete.insets = new Insets(0, 0, 5, 0);
			global_btnComplete.gridx = 6;
			global_btnComplete.gridy = 4;
			MaincontentPane.add(btnComplete, global_btnComplete);

			addCountryButton = new JButton("New Country");
			addCountryButton.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
			addCountryButton.setForeground(Color.BLUE);
			addCountryButton.setBackground(new Color(240, 255, 255));
			GridBagConstraints global_addCountryButton = new GridBagConstraints();
			global_addCountryButton.fill = GridBagConstraints.HORIZONTAL;
			global_addCountryButton.insets = new Insets(0, 0, 5, 5);
			global_addCountryButton.gridx = 1;
			global_addCountryButton.gridy = 5;
			MaincontentPane.add(addCountryButton, global_addCountryButton);

			continentComboBox = new JComboBox();			/*continent list.*/
			continentComboBox.setToolTipText("Choose existing continent to add new country");
			GridBagConstraints global_continentComboBox = new GridBagConstraints();
			global_continentComboBox.gridwidth = 3;
			global_continentComboBox.insets = new Insets(0, 0, 5, 5);
			global_continentComboBox.fill = GridBagConstraints.HORIZONTAL;
			global_continentComboBox.gridx = 2;
			global_continentComboBox.gridy = 5;
			MaincontentPane.add(continentComboBox, global_continentComboBox);

			JLabel CountryNameLabel = new JLabel("Name of Country");
			GridBagConstraints grid_CountryNameLabel = new GridBagConstraints();
			grid_CountryNameLabel.anchor = GridBagConstraints.EAST;
			grid_CountryNameLabel.insets = new Insets(0, 0, 5, 5);
			grid_CountryNameLabel.gridx = 5;
			grid_CountryNameLabel.gridy = 6;
			MaincontentPane.add(CountryNameLabel, grid_CountryNameLabel);

			countryNameText = new JTextField();
			countryNameText.setFont(new Font("Bookman Old Style", Font.ITALIC, 13));
			countryNameText.setForeground(Color.BLUE);
			countryNameText.setEnabled(false);
			GridBagConstraints grid_countryNameText = new GridBagConstraints();
			grid_countryNameText.insets = new Insets(0, 0, 5, 0);
			grid_countryNameText.fill = GridBagConstraints.HORIZONTAL;
			grid_countryNameText.gridx = 6;
			grid_countryNameText.gridy = 6;
			MaincontentPane.add(countryNameText, grid_countryNameText);
			countryNameText.setColumns(10);

			deleteCountryButton = new JButton("Delete Country");
			deleteCountryButton.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
			deleteCountryButton.setForeground(Color.BLUE);
			deleteCountryButton.setBackground(new Color(240, 255, 255));
			GridBagConstraints grid_deleteCountryButton = new GridBagConstraints();
			grid_deleteCountryButton.fill = GridBagConstraints.HORIZONTAL;
			grid_deleteCountryButton.insets = new Insets(0, 0, 5, 5);
			grid_deleteCountryButton.gridx = 1;
			grid_deleteCountryButton.gridy = 8;
			MaincontentPane.add(deleteCountryButton, grid_deleteCountryButton);

			/**
			 * includes list of countries to be deleted
			 */
			countryListCombobox = new JComboBox();			/*list of countries.*/
			GridBagConstraints grid_countryListCombobox = new GridBagConstraints();
			ArrayList<String> choice = new ArrayList<>();
			grid_countryListCombobox.gridwidth = 3;
			grid_countryListCombobox.insets = new Insets(0, 0, 5, 5);
			grid_countryListCombobox.fill = GridBagConstraints.HORIZONTAL;
			grid_countryListCombobox.gridx = 2;
			grid_countryListCombobox.gridy = 8;
			MaincontentPane.add(countryListCombobox, grid_countryListCombobox);

			addButton = new JButton("Add Country");			/*adding countries*/
			addButton.setBackground(new Color(240, 255, 255));
			addButton.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
			GridBagConstraints grid_addButton = new GridBagConstraints();
			grid_addButton.insets = new Insets(0, 0, 5, 0);
			grid_addButton.anchor = GridBagConstraints.EAST;
			grid_addButton.gridx = 6;
			grid_addButton.gridy = 8;
			MaincontentPane.add(addButton, grid_addButton);

			deleteContinentButton = new JButton("Delete Continent");			/*deleting continent*/
			deleteContinentButton.setBackground(new Color(240, 255, 255));
			deleteContinentButton.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
			deleteContinentButton.setForeground(Color.BLACK);
			GridBagConstraints grid_deleteContinentButton = new GridBagConstraints();
			grid_deleteContinentButton.fill = GridBagConstraints.HORIZONTAL;
			grid_deleteContinentButton.insets = new Insets(0, 0, 5, 5);
			grid_deleteContinentButton.gridx = 1;
			grid_deleteContinentButton.gridy = 11;
			MaincontentPane.add(deleteContinentButton, grid_deleteContinentButton);

			/**
			 * includes list of continents to be deleted 
			 */
			continentDeletionComboBox = new JComboBox();
			GridBagConstraints grid_continentDeletionComboBox = new GridBagConstraints();
			grid_continentDeletionComboBox.gridwidth = 3;
			grid_continentDeletionComboBox.insets = new Insets(0, 0, 5, 5);
			grid_continentDeletionComboBox.fill = GridBagConstraints.HORIZONTAL;
			grid_continentDeletionComboBox.gridx = 2;
			grid_continentDeletionComboBox.gridy = 11;

			MaincontentPane.add(continentDeletionComboBox, grid_continentDeletionComboBox);

			addNeighboursButton = new JButton("Choose Neighbours to add");
			addNeighboursButton.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
			GridBagConstraints gbc_btnAddNeighbours = new GridBagConstraints();
			gbc_btnAddNeighbours.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnAddNeighbours.insets = new Insets(0, 0, 5, 5);
			gbc_btnAddNeighbours.gridx = 1;
			gbc_btnAddNeighbours.gridy = 14;
			MaincontentPane.add(addNeighboursButton, gbc_btnAddNeighbours);

			/**
			 * include list of neighbours to be included
			 */
			countriesComboBox = new JComboBox();
			GridBagConstraints grid_countriesComboBox = new GridBagConstraints();
			countriesComboBox.setToolTipText("Select country for adding neighbours");
			grid_countriesComboBox.insets = new Insets(0, 0, 5, 5);
			grid_countriesComboBox.fill = GridBagConstraints.HORIZONTAL;
			grid_countriesComboBox.gridx = 2;
			grid_countriesComboBox.gridy = 14;
			countriesComboBox.setEnabled(true);
			MaincontentPane.add(countriesComboBox, grid_countriesComboBox);

			GridBagConstraints grid_chooseNeighbourLabel = new GridBagConstraints();
			grid_chooseNeighbourLabel.insets = new Insets(0, 0, 5, 5);
			grid_chooseNeighbourLabel.gridx = 1;
			grid_chooseNeighbourLabel.gridy = 16;
			JLabel chooseNeighbourLabel = new JLabel("Possible neighbours for the country selected");
			chooseNeighbourLabel.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
			
			MaincontentPane.add(chooseNeighbourLabel, grid_chooseNeighbourLabel);

			model2 = new DefaultListModel<String>();
			list = new JList();
			GridBagConstraints grid_list = new GridBagConstraints();
			grid_list.insets = new Insets(0, 0, 5, 5);
			grid_list.fill = GridBagConstraints.BOTH;
			grid_list.gridx = 2;
			grid_list.gridy = 16;
			JScrollPane scrollPane = new JScrollPane(list);
			list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			list.setModel(model2);
		
			MaincontentPane.add(scrollPane, grid_list);
			list.setEnabled(false);

			GridBagConstraints grid_neighboutchooseButton = new GridBagConstraints();
			grid_neighboutchooseButton.anchor = GridBagConstraints.EAST;
			grid_neighboutchooseButton.insets = new Insets(0, 0, 5, 5);
			grid_neighboutchooseButton.gridx = 2;
			grid_neighboutchooseButton.gridy = 17;
			neighbourChooseButton = new JButton("Add selected countries as neighbours");
			
			MaincontentPane.add(neighbourChooseButton, grid_neighboutchooseButton);

			saveMapButton = new JButton("click to Save Map!");
			saveMapButton.setBackground(new Color(152, 251, 152));
			saveMapButton.setFont(new Font("Bookman Old Style", Font.BOLD, 16));
			GridBagConstraints grid_saveMapButton = new GridBagConstraints();
			grid_saveMapButton.fill = GridBagConstraints.HORIZONTAL;
			grid_saveMapButton.gridx = 6;
			grid_saveMapButton.gridy = 29;
			MaincontentPane.add(saveMapButton, grid_saveMapButton);
		}
/**
 * action events  for button to add New country
 * @param newAction return action taken
 */
		public void addActionsToAddCountryButton(ActionListener newAction) {
			addCountryButton.addActionListener(newAction);
		}
		/**
		 * @see Edit_Create_Map_Controller
		 * @param newAction actionListener for addButton
		 */
		public void addActionsToAddButton(ActionListener newAction) {
			addButton.addActionListener(newAction);
		}
		/**
		 * enabling the list
		 */
		public void JListEnabled() {
			list.setEnabled(true);
		}

		/**
		 * disabling the country-field
		 */
		public void countryFieldDisable() {
			countryNameText.setText("");
			countryNameText.setEnabled(false);
		}

		/**
		 * disabling the continent-field
		 */
		public void continentFieldDisabled() {
			continentNameText.setText("");
			continentControlValueText.setText("");
			continentNameText.setEnabled(false);
			continentControlValueText.setEnabled(false);
		}
		/**Action events for button to delete country
		 * @see Edit_Create_Map_Controller
		 * @param newAction actionListener for deleteCountryButton
		 */
		public void addActionsToDeleteCountryButton(ActionListener newAction) {
			deleteCountryButton.addActionListener(newAction);
		}

		/**
		 * checking whether continent exist or nor
		 * @return boolean value whether country exist or not
		 */
		public boolean continentExistCheck() {
			Boolean ContinentPresent = false;
			if(continentComboBox.getItemCount()!=0) {
				ContinentPresent = true;
			}
			return ContinentPresent;
		}

		/**action events  for button to finish adding neighbours
		 * @see Edit_Create_Map_Controller
		 * @param newAction actionListener for addNeighboursButton
		 */
		public void addActionsToAddNeighboursButton(ActionListener newAction) {
			addNeighboursButton.addActionListener(newAction);
		}

		/**
		 * @see Edit_Create_Map_Controller
		 * @param newAction actionListener for neighbourChooseButton
		 */
		public void addActionsToNeighbourChooseButton(ActionListener newAction) {
			neighbourChooseButton.addActionListener(newAction);
		}
		/**
		 * get the selected countries for corresponding neighbours
		 * @return list of countries selected through countryListCombobox that includes selecting the countries
		 */
		public String getSelectedCountriesForNeighbours() {
			return (countriesComboBox.getSelectedItem().toString());
		}

		/**
		 * get countries for deletion in map modifier
		 * @return list of countries selected for deletion 
		 */
		public String getCountriesToRemove() {
			return (countryListCombobox.getSelectedItem().toString());
		}

		/**
		 * getting continents for deletion
		 * @return list of continents to be deleted
		 */
		public String continentForDeletion() {
			return (continentDeletionComboBox.getSelectedItem().toString());
		}


		/**
		 * action events  for button to add New country
		 * @param newAction return action taken
		 * @see Edit_Create_Map_Controller
		 */
		public void addActionsToAddContinentButton(ActionListener newAction) {
			addContinentButton.addActionListener(newAction);
		}

		/**action events  for button to finish adding New country
		 * @see Edit_Create_Map_Controller
		 * @param newAction actionListener for btnComplete
		 */
		public void addActionsToBtnComplete(ActionListener newAction) {
			btnComplete.addActionListener(newAction);
		}

		
		/** Action events for button to delete continent
		 * @see Edit_Create_Map_Controller
		 * @param newAction actionListener for deleteContinentButton
		 */
		public void addActionsToDeleteContinentButton(ActionListener newAction) {
			deleteContinentButton.addActionListener(newAction);
		}

		/**Action events for button to save new map
		 * @see Edit_Create_Map_Controller
		 * @param newAction actionListener for saveMapButton
		 */
		public void addActionsToSaveMapButton(ActionListener newAction) {
			saveMapButton.addActionListener(newAction);
		}

		

		/** 
		 * getting the country name
		 * @return country name
		 */
		public String getNAmeOfCountry() {
			return (countryNameText.getText());
		}

		/**
		 * getting the neighbours countries from list
		 * @return list of neighbours countries selected
		 */
		public List getListOfNeighbours() {
			return (list.getSelectedValuesList());
		}

		

		
		/**
		 * getting selected continent
		 * @return list of continents selected
		 */
		public String getSelectedContinent() {
			return (continentComboBox.getSelectedItem().toString());
		}

		/**
		 * clearing the neighbours list
		 */
		public void clearNeighboursJList() {
			model2.removeAllElements();
		}

		/**
		 * adding neighbour countries to the list
		 * @param neighbour include countries
		 */
		public void addPossibleNeighboursToJList(String neighbour) {
			model2.addElement(neighbour);
		}

		/**
		 * getting the continent name
		 * @return continent name
		 */
		public String getContinentName() {
			continentName = continentNameText.getText();
			return continentName;
		}

		/**
		 * dialogue box for error messages
		 */
		public void errorEnterValues() {
			JOptionPane.showMessageDialog(MaincontentPane, "Values are expected to enter first", "Error", JOptionPane.ERROR_MESSAGE);
		}

		/**
		 * dialogue box for error messages
		 */
		public void noNeighboursSelecteError() {
			JOptionPane.showMessageDialog(MaincontentPane, "Values are expected to select neighbours first", "Error", JOptionPane.ERROR_MESSAGE);
		}

		/**
		 * dialogue box for error messages
		 */
		public void errorcontinentAlreadyExist() {
			JOptionPane.showMessageDialog(MaincontentPane, "this Continent already exist, choose other country", "Error", JOptionPane.ERROR_MESSAGE);
		}

		/**
		 * dialogue box for error messages
		 */
		public void errorCountryAlreadyExist() {
			JOptionPane.showMessageDialog(MaincontentPane, "this country already exist, choose other country", "Error", JOptionPane.ERROR_MESSAGE);
		}

		/**
		 * dialogue box for error messages
		 */
		public void errorNullContinent() {
			JOptionPane.showMessageDialog(MaincontentPane, "Add continent first", "Error", JOptionPane.ERROR_MESSAGE);
		}

		/**
		 * dialogue box for error messages
		 */
		public void errorNullCountry() {
			JOptionPane.showMessageDialog(MaincontentPane, "Add country firstr", "Error", JOptionPane.ERROR_MESSAGE);
		}

		/**
		 * getting the control value of continent
		 * @return control value of continent
		 */
		public String getControlValue() {
			cv = (continentControlValueText.getText());
			return cv;
		}

		/**
		 * easing or enabling continent fields that are continent name and its control value
		 */
		public void removeContinentFields() {
			continentNameText.setText("");
			continentControlValueText.setText("");
			continentNameText.setEnabled(false);
			continentControlValueText.setEnabled(false);
		}

		public void countryfieldEnable() {
			countryNameText.setEnabled(true);
		}

		public void continentFieldsEnable() {
			continentNameText.setEnabled(true);
			continentControlValueText.setEnabled(true);
		}
		/**
		 * clearing the all data in continent lists displayed in continentComboBox
		 */
		public void continentContentsCleared() {
			continentComboBox.removeAllItems();
			continentDeletionComboBox.removeAllItems();
		}

		/**
		 * clearing list of countries
		 */
		public void countriesContentsCleared() {
			countryListCombobox.removeAllItems();
			countriesComboBox.removeAllItems();
		}

		/**
		 * setting the continents in the list
		 * @param continent name of continent
		 */
		public void setDataInContinentsComboBox(String continent) {
			continentComboBox.addItem(continent);
			continentDeletionComboBox.addItem(continent);
		}

		/**
		 * adding country to the list
		 * @param country name of country
		 */
		public void setDataInCountriesComboBox(String country) {
			countriesComboBox.addItem(country);
			countryListCombobox.addItem(country);
		}
	
}
