package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import Model.NodeOfCountry;
import Model.NodeOfMap;
import Model.WriteMap;
import controllers.Edit_create_Map_Controller;


	/** class opens the JFrame view for  
	 *  delete/add country and continent while modifying Map
	 * 
	 * @author Gursharan
	 *
	 */
	public class ExistingMapModifier extends JFrame {

		/**
		 * JFrame-Serial Version id.
		 * {@inheritDoc}
		 */
		private static final long serialVersionUID = -5188102736306067380L;
		
		/**
		 * Creates a new NodeOfMap (imported from class NodeOfMap.
		 */
		ArrayList<NodeOfMap> continents = new ArrayList<NodeOfMap>();

		/**
		 * Stores the JPanel of the main frame for this class
		 */
		private JPanel contentPane;

		/**
		 * Text field to receive continent-name. 
		 */
		private JTextField continentNameText;

		/**
		 * Text field to receive control-values.
		 */
		private JTextField continentControlValueText;

		/**
		 * Text Field to hold country-name
		 */
		private JTextField countryNameText;

		/**
		 * ComboBox for continent Drop-down List.
		 */
		private JComboBox continentComboBox;

		/**
		 * ComboBox for continent Drop-down List.
		 */
		private JComboBox continentDeletionComboBox;

		/**
		 * Stores reference to the NodeOfMap object.
		 */
		private NodeOfMap nodeOfMap;

		/**
		 * Stores add continent button.
		 */
		private JButton btnComplete;

		/**
		 * save add neighbours Button
		 */
		private JButton addNeighboursButton;

		/**
		 * Button to enable addition of continent fields.
		 */
		private JButton addContinentButton;
		
		/**
		 * Creates WriteMap object.
		 */
		private WriteMap writerMap = new WriteMap();

		/**
		 * Stores the continent name.
		 */
		private String nameContinent;

		/**
		 * Stores the continent control value.
		 */
		private String cv;

		/**
		 * Stores list of neighbours.
		 */
		private JList list;

		/**
		 * Stores list of countries.
		 */
		private JComboBox countriesComboBox;

		/**
		 * Stores list model for neighbours JList.
		 */
		private DefaultListModel<String> model2;

		/**
		 * Button for selected neighbours. 
		 */
		private JButton neighbourChooseButton;

		/**
		 * Button for continent deletion.
		 */
		private JButton deleteContinentButton;

		/**
		 * Button for saving map file.
		 */
		private JButton saveMapButton;

		/**
		 * Button for country deletion.
		 */
		private JButton deleteCountryButton;

		/**
		 * Stores and displays list of continents.
		 */
		private JComboBox countryListCombobox;

		/**
		 * Button to add a new country.
		 */
		private JButton addButton;
		/**
		 * Button to add a new country.
		 */
		private JButton addCountryButton;

		/**
		 * MapNew class constructor that calls begin method of the class
		 * @param editMap arraylist of all continents.
		 */
		public ExistingMapModifier(ArrayList<NodeOfMap> editMap) {
			continents = editMap;
			begin();
		}

		/**
		 * Initialize the contents of the frame.
		 */
		public void begin() {
			this.setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(130, 130, 480, 330);
			this.setTitle("Existing Map Modifier Window");
			this.setExtendedState(JFrame.MAXIMIZED_BOTH); 

			GridBagLayout Global_contentPane = new GridBagLayout();
			Global_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
			Global_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			Global_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
			Global_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			contentPane = new JPanel();
			contentPane.setBackground(Color.DARK_GRAY);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			
			contentPane.setLayout(Global_contentPane);

			GridBagConstraints gbc_labl_MapExist = new GridBagConstraints();
			gbc_labl_MapExist.insets = new Insets(0, 0, 5, 0);
			gbc_labl_MapExist.insets = new Insets(0, 0, 5, 0);
			gbc_labl_MapExist.gridwidth = 7;
			gbc_labl_MapExist.gridx = 0;
			gbc_labl_MapExist.gridy = 0;
			JLabel labl_MapExist = new JLabel("EDIT- Existing Map file..");
			labl_MapExist.setFont(new Font("Bookman Old Style", Font.BOLD|Font.PLAIN, 45));
			labl_MapExist.setForeground(Color.WHITE);
			
			contentPane.add(labl_MapExist, gbc_labl_MapExist);

			addContinentButton = new JButton("New Continent");
			//addContinentButton.setForeground(Color.BLACK);
			addContinentButton.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 17));
			addContinentButton.setForeground(new Color(0, 0, 255));
			GridBagConstraints gbc_addContinentBtn = new GridBagConstraints();
			gbc_addContinentBtn.fill = GridBagConstraints.HORIZONTAL;
			gbc_addContinentBtn.insets = new Insets(0, 0, 5, 5);
			gbc_addContinentBtn.gridx = 6;
			gbc_addContinentBtn.gridy = 3;
			contentPane.add(addContinentButton, gbc_addContinentBtn);

			JLabel NameLabel = new JLabel("Continent Name");
			NameLabel.setForeground(new Color(255, 255, 255));
			GridBagConstraints gbc_NameLabel = new GridBagConstraints();
			gbc_NameLabel.anchor = GridBagConstraints.EAST;
			gbc_NameLabel.insets = new Insets(0, 0, 5, 5);
			gbc_NameLabel.gridx = 5;
			gbc_NameLabel.gridy = 4;
			contentPane.add(NameLabel, gbc_NameLabel);

			GridBagConstraints gbc_continentNameText = new GridBagConstraints();
			gbc_continentNameText.insets = new Insets(0, 0, 5, 0);
			gbc_continentNameText.fill = GridBagConstraints.HORIZONTAL;
			gbc_continentNameText.gridx = 6;
			gbc_continentNameText.gridy = 4;
			continentNameText = new JTextField();
			continentNameText.setFont(new Font("Tahoma", Font.ITALIC, 13));
			continentNameText.setForeground(Color.BLACK);
			continentNameText.setEnabled(false);
			
			
			contentPane.add(continentNameText, gbc_continentNameText);
			continentNameText.setColumns(10);

			GridBagConstraints gbc_continentControlValueText = new GridBagConstraints();
			gbc_continentControlValueText.insets = new Insets(0, 0, 5, 0);
			gbc_continentControlValueText.fill = GridBagConstraints.HORIZONTAL;
			gbc_continentControlValueText.gridx = 6;
			gbc_continentControlValueText.gridy = 5;
			continentControlValueText = new JTextField();
			continentControlValueText.setText("Enter new Continent Name here");
			continentControlValueText.setFont(new Font("Tahoma", Font.ITALIC, 13));
			continentControlValueText.setForeground(Color.BLACK);
			continentControlValueText.setEnabled(false);
			
			contentPane.add(continentControlValueText, gbc_continentControlValueText);
			continentControlValueText.setColumns(10);

			GridBagConstraints globalPane_valueLabel = new GridBagConstraints();
			globalPane_valueLabel.anchor = GridBagConstraints.EAST;
			globalPane_valueLabel.insets = new Insets(0, 0, 5, 5);
			globalPane_valueLabel.gridx = 5;
			globalPane_valueLabel.gridy = 5;
			JLabel valueLabel = new JLabel("Continent Control Value");
			valueLabel.setForeground(new Color(255, 255, 255));
			
			
			contentPane.add(valueLabel, globalPane_valueLabel);

			btnComplete = new JButton("Add Continent");
			btnComplete.setBackground(new Color(240, 255, 255));
			btnComplete.setFont(new Font("Tahoma", Font.BOLD, 13));
			btnComplete.setForeground(new Color(0, 0, 255));
			GridBagConstraints gbc_btnComplete = new GridBagConstraints();
			gbc_btnComplete.anchor = GridBagConstraints.EAST;
			gbc_btnComplete.insets = new Insets(0, 0, 5, 0);
			gbc_btnComplete.gridx = 6;
			gbc_btnComplete.gridy = 6;
			contentPane.add(btnComplete, gbc_btnComplete);

			addCountryButton = new JButton("New Country");
			addCountryButton.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
			addCountryButton.setForeground(new Color(0, 0, 255));
			addCountryButton.setBackground(new Color(240, 255, 255));
			GridBagConstraints gbc_btnAddCountry = new GridBagConstraints();
			gbc_btnAddCountry.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnAddCountry.insets = new Insets(0, 0, 5, 5);
			gbc_btnAddCountry.gridx = 1;
			gbc_btnAddCountry.gridy = 5;
			contentPane.add(addCountryButton, gbc_btnAddCountry);

			continentComboBox = new JComboBox();			/*continent list*/
			continentComboBox.setToolTipText("Choose continent that already exist to enable addition of new country");
			GridBagConstraints globalPane_comboBox = new GridBagConstraints();
			globalPane_comboBox.gridwidth = 3;
			globalPane_comboBox.insets = new Insets(0, 0, 5, 5);
			globalPane_comboBox.fill = GridBagConstraints.HORIZONTAL;
			globalPane_comboBox.gridx = 2;
			globalPane_comboBox.gridy = 5;
			contentPane.add(continentComboBox, globalPane_comboBox);
			for (NodeOfMap nodeOfMap : continents) {
				continentComboBox.addItem(nodeOfMap.getContinent());//from NodeOfMap
			}

			
			GridBagConstraints gbc_NameLabel_1 = new GridBagConstraints();
			gbc_NameLabel_1.anchor = GridBagConstraints.EAST;
			gbc_NameLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_NameLabel_1.gridx = 5;
			gbc_NameLabel_1.gridy = 7;
			JLabel NameLabel_1 = new JLabel("Country Name");
			NameLabel_1.setForeground(new Color(255, 255, 255));
			contentPane.add(NameLabel_1, gbc_NameLabel_1);

			GridBagConstraints gbc_countryNameText = new GridBagConstraints();
			gbc_countryNameText.insets = new Insets(0, 0, 5, 0);
			gbc_countryNameText.fill = GridBagConstraints.HORIZONTAL;
			gbc_countryNameText.gridx = 6;
			gbc_countryNameText.gridy = 7;
			countryNameText = new JTextField();
			countryNameText.setFont(new Font("Tahoma", Font.ITALIC, 14));
			countryNameText.setForeground(Color.BLUE);
			countryNameText.setEnabled(false);
			countryNameText.setText("Enter new Country Name here");
			
			contentPane.add(countryNameText, gbc_countryNameText);
			countryNameText.setColumns(10);

			GridBagConstraints gbc_deleteCountryButton = new GridBagConstraints();
			gbc_deleteCountryButton.fill = GridBagConstraints.HORIZONTAL;
			gbc_deleteCountryButton.insets = new Insets(0, 0, 5, 5);
			gbc_deleteCountryButton.gridx = 1;
			gbc_deleteCountryButton.gridy = 8;
			deleteCountryButton = new JButton("Delete Country");
			deleteCountryButton.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
			deleteCountryButton.setForeground(Color.BLUE);
			deleteCountryButton.setBackground(new Color(240, 255, 255));
			
			contentPane.add(deleteCountryButton, gbc_deleteCountryButton);

			countryListCombobox = new JComboBox();			/*list of countries.*/
			GridBagConstraints global_countryListCombobox = new GridBagConstraints();
			ArrayList<String> choice = new ArrayList<>();
			global_countryListCombobox.gridwidth = 3;
			global_countryListCombobox.insets = new Insets(0, 0, 5, 5);
			global_countryListCombobox.fill = GridBagConstraints.HORIZONTAL;
			global_countryListCombobox.gridx = 2;
			global_countryListCombobox.gridy = 8;
			contentPane.add(countryListCombobox, global_countryListCombobox);
			for (NodeOfMap nodeOfMap : continents) {
				for (NodeOfCountry nodeOfCountry : nodeOfMap.getCountries()) {
					countryListCombobox.addItem(nodeOfCountry.getNameOfCountry()); //from NodeOfcountry
				}
			}

			addButton = new JButton("Add Country");
			addButton.setBackground(new Color(240, 255, 255));
			addButton.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
			addButton.setForeground(new Color(0, 0, 255));
			GridBagConstraints globalPane_addButton = new GridBagConstraints();
			globalPane_addButton.insets = new Insets(0, 0, 5, 0);
			globalPane_addButton.anchor = GridBagConstraints.EAST;
			globalPane_addButton.gridx = 6;
			globalPane_addButton.gridy = 8;
			contentPane.add(addButton, globalPane_addButton);

			GridBagConstraints gbc_btnDeleteContinent = new GridBagConstraints();
			gbc_btnDeleteContinent.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnDeleteContinent.insets = new Insets(0, 0, 5, 5);
			gbc_btnDeleteContinent.gridx = 1;
			gbc_btnDeleteContinent.gridy = 11;
			deleteContinentButton = new JButton("Delete Continent");
			deleteContinentButton.setBackground(new Color(240, 255, 255));
			deleteContinentButton.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 19));
			deleteContinentButton.setForeground(Color.BLUE);
			
			contentPane.add(deleteContinentButton, gbc_btnDeleteContinent);

			continentDeletionComboBox = new JComboBox(); //List of Continents
			GridBagConstraints gbc_comboBox3 = new GridBagConstraints();
			gbc_comboBox3.gridwidth = 3;
			gbc_comboBox3.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox3.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox3.gridx = 2;
			gbc_comboBox3.gridy = 11;
			contentPane.add(continentDeletionComboBox, gbc_comboBox3);
			for (NodeOfMap nodeOfMap : continents) {
				continentDeletionComboBox.addItem(nodeOfMap.getContinent());//from NodeOfMap 
			}

			addNeighboursButton = new JButton("Choose Neighbours:");
			addNeighboursButton.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
			addNeighboursButton.setBackground(new Color(240, 255, 255));
			addNeighboursButton.setForeground (Color.BLUE);
			GridBagConstraints globalPane_addNeighboursButton = new GridBagConstraints();
			globalPane_addNeighboursButton.fill = GridBagConstraints.HORIZONTAL;
			globalPane_addNeighboursButton.insets = new Insets(0, 0, 5, 5);
			globalPane_addNeighboursButton.gridx = 1;
			globalPane_addNeighboursButton.gridy = 14;
			contentPane.add(addNeighboursButton, globalPane_addNeighboursButton);

			countriesComboBox = new JComboBox();
			GridBagConstraints gbc_comboBox11 = new GridBagConstraints();
			countriesComboBox.setToolTipText("Select country for adding neighbours");
			gbc_comboBox11.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox11.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox11.gridx = 2;
			gbc_comboBox11.gridy = 14;
			countriesComboBox.setEnabled(true);
			contentPane.add(countriesComboBox, gbc_comboBox11);
			for (NodeOfMap nodeOfMap : continents) {
				for (NodeOfCountry nodeOfCountry : nodeOfMap.getCountries()) {
					countriesComboBox.addItem(nodeOfCountry.getNameOfCountry());
				}
			}

			GridBagConstraints gbc_chooseNeighboursLabel = new GridBagConstraints();
			gbc_chooseNeighboursLabel.insets = new Insets(0, 0, 5, 5);
			gbc_chooseNeighboursLabel.gridx = 1;
			gbc_chooseNeighboursLabel.gridy = 16;
			JLabel chooseNeighboursLabel = new JLabel("Neighbours for country selected ");
			chooseNeighboursLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
			chooseNeighboursLabel.setBackground(new Color(240, 255, 255));
			chooseNeighboursLabel.setForeground(new Color(255, 255, 255));
			
			contentPane.add(chooseNeighboursLabel, gbc_chooseNeighboursLabel);

			model2 = new DefaultListModel<String>();
			
			GridBagConstraints gbc_list = new GridBagConstraints();
			gbc_list.insets = new Insets(0, 0, 5, 5);
			gbc_list.fill = GridBagConstraints.BOTH;
			gbc_list.gridx = 2;
			gbc_list.gridy = 16;list = new JList();
			JScrollPane scrollPane = new JScrollPane(list);
			scrollPane.setViewportView(list);
			list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			list.setModel(model2);
			
			contentPane.add(scrollPane, gbc_list);
			list.setEnabled(false);

			
			GridBagConstraints gbc_neighbourButton = new GridBagConstraints();
			gbc_neighbourButton.anchor = GridBagConstraints.EAST;
			gbc_neighbourButton.insets = new Insets(0, 0, 5, 5);
			gbc_neighbourButton.gridx = 2;
			gbc_neighbourButton.gridy = 17;
			neighbourChooseButton = new JButton("Click to add Selected countries as neighbours");
			neighbourChooseButton.setBackground(new Color(240, 255, 255));
			neighbourChooseButton.setForeground(new Color(0, 0, 255)); 
			contentPane.add(neighbourChooseButton, gbc_neighbourButton);

			
			GridBagConstraints gbc_btnSaveMap = new GridBagConstraints();
			gbc_btnSaveMap.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnSaveMap.gridx = 5;
			gbc_btnSaveMap.gridy = 28;saveMapButton = new JButton("Save Modified Map");
			saveMapButton.setBackground(new Color(192, 192, 192));
			saveMapButton.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
			
			contentPane.add(saveMapButton, gbc_btnSaveMap);
		
		}
		
		public void addActionsToAddCountryButton(ActionListener newAction) {
			addCountryButton.addActionListener(newAction);
		}
		
		public void addActionsToDeleteContinentButton(ActionListener newAction) {
			deleteContinentButton.addActionListener(newAction);
		}

		public void addActionsToAddContinentButton(ActionListener newAction) {
			addContinentButton.addActionListener(newAction);
		}
		public void addActionsToAddNeighboursButton(ActionListener newAction) {
			addNeighboursButton.addActionListener(newAction);
		}
		public void addActionsToSaveMapButton(ActionListener newAction) {
			saveMapButton.addActionListener(newAction);
		}

		public void addActionsToDeleteCountryButton(ActionListener newAction) {
			deleteCountryButton.addActionListener(newAction);
		} 
		public void addActionsToBtnComplete(ActionListener newAction) {
			btnComplete.addActionListener(newAction);
		}

		public void addActionsToAddButton(ActionListener newAction) {
			addButton.addActionListener(newAction);
		}
		public void noNeighboursSelecteError() {
			JOptionPane.showMessageDialog(contentPane, "Values are expected to select neighbours first", "Error", JOptionPane.ERROR_MESSAGE);
		}
		

		public void addActionsToNeighbourChooseButton(ActionListener newAction) {
			neighbourChooseButton.addActionListener(newAction);
		}

				
		public void errorEnterValues() {
			JOptionPane.showMessageDialog(contentPane, "Values are expected to enter first", "Error", JOptionPane.ERROR_MESSAGE);
		}

		public void errorcontinentAlreadyExist() {
			JOptionPane.showMessageDialog(contentPane, "this Continent already exist, choose other country", "Error", JOptionPane.ERROR_MESSAGE);
		}

		public void errorCountryAlreadyExist() {
			JOptionPane.showMessageDialog(contentPane, "this country already exist, choose other country", "Error", JOptionPane.ERROR_MESSAGE);
		}

		public void errorNullContinent() {
			JOptionPane.showMessageDialog(contentPane, "Add continent first", "Error", JOptionPane.ERROR_MESSAGE);
		}

		public void errorNullCountry() {
			JOptionPane.showMessageDialog(contentPane, "Add country firstr", "Error", JOptionPane.ERROR_MESSAGE);
		}

		public String getControlValue() {
			cv = (continentControlValueText.getText());
			return cv;
		}
		/**
		 * Function to remove all continents from continent
		 */
		public void removeContinentFields() {
			continentNameText.setText("");
			continentControlValueText.setText("");
			continentNameText.setEnabled(false);
			continentControlValueText.setEnabled(false);
		}
	
	/**
	 * clearing the all data in continent lists displayed in continentComboBox
	 */
		public void continentContentsCleared() {
			continentComboBox.removeAllItems();
			continentDeletionComboBox.removeAllItems();
		}

		public void countriesContentsCleared() {
			countryListCombobox.removeAllItems();
			countriesComboBox.removeAllItems();
		}

		public void setDataInContinentsComboBox(String continent) {
			continentComboBox.addItem(continent);
			continentDeletionComboBox.addItem(continent);
		}

		public void setDataInCountriesComboBox(String country) {
			countriesComboBox.addItem(country);
			countryListCombobox.addItem(country);
		}
		
		public ExistingMapModifier getFrame() {
			return this;
		}
		public void continentFieldsEnable() {
			continentNameText.setEnabled(true);
			continentControlValueText.setEnabled(true);
		}
		
		/**
		 * Function to get selected country to add as adding neighbour.
		 * @return selected country as neighbour.
		 */
		public String getSelectedCountriesForNeighbours() {
			return (countriesComboBox.getSelectedItem().toString());
		}
		
		/**
		 *Function to delete selected country.
		 * @return  country for deletion.
		 */
		public String getCountriesToRemove() {
			return (countryListCombobox.getSelectedItem().toString());
		}
		
		/**
		 * Function to check for unique continents.
		 * @return true if same continent that you try to add already exist.
		 */
		public boolean continentExistCheck() {
			Boolean ContinentPresent = false;
			if(continentComboBox.getItemCount()!=0) {
				ContinentPresent = true;
			}
			return ContinentPresent;
		}
		
		/**
		 * Function to return complete list of selected neighbours.
		 * @return list of selected neighbours.
		 */
		public List getListOfNeighbours() {
			return (list.getSelectedValuesList());
		}
		
		/**
		 * Function to enable jlist.
		 */
		public void JListEnabled() {
			list.setEnabled(true);
		}
		
		/**
		 * Function to clear and disable the country-name-field.
		 */
		public void countryFieldDisable() {
			countryNameText.setText("");
			countryNameText.setEnabled(false);
		}
		/**
		 * Function to Enable the country-name-field.
		 */
		public void countryfieldEnable() {
			countryNameText.setEnabled(true);
		}
		
		/**
		 * Function to get the country name that is entered.
		 * @return the country name entered.
		 */
		public String getNAmeOfCountry() {
			return (countryNameText.getText());
		}
		/**
		 * Function to disable continent fields
		 */
		public void continentFieldDisabled() {
			continentNameText.setText("");
			continentControlValueText.setText("");
			continentNameText.setEnabled(false);
			continentControlValueText.setEnabled(false);
		}
		
		
		/**
		 *Function to delete the selected Continent from list.
		 * @return  continent for deletion.
		 */
		public String continentForDeletion() {
			return (continentDeletionComboBox.getSelectedItem().toString());
		}
		/**
		 * Function to return list of selected continents
		 * @return list of selected continents
		 */
		public String getSelectedContinent() {
			return (continentComboBox.getSelectedItem().toString());
		}
		/**
		 * Function to remove all neighbours in the list
		 */
		public void clearNeighboursJList() {
			model2.removeAllElements();
		}

		public void addPossibleNeighboursToJList(String neighbour) {
			model2.addElement(neighbour);
		}
		/**
		 * function to get name of continent
		 * @return
		 */
		public String getContinentName() {
			nameContinent = continentNameText.getText();
			return nameContinent;
		}

		
	}



