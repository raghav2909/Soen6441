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

import Model.*;
import Model.NodeOfMap;
import Model.WriteMap;
import controllers.EditCreateMapController;

	/** class opens the JFrame view for  
	 *  delete/add country and continent while modifying Map
	 * @author Gursharan
	 *@version 2.0
	 */
	public class ExistingMapModifier extends JFrame {

		/**
		 * JFrame-Serial Version id.
		 * {@inheritDoc}
		 */
		private static final long serialVersionUID = -5188102736306067380L;
		
		/**
		 * Creates a new NodeOfMap.
		 */
		ArrayList<NodeOfMap> continents = new ArrayList<NodeOfMap>();

		/**
		 * Stores the JPanel of the frame this class
		 */
		private JPanel contentPane;

		/**
		 * Text field that receives continent name. 
		 */
		private JTextField continentNameText;

		/**
		 * Text field that receives control value.
		 */
		private JTextField continentControlValueText;

		/**
		 * Text Field to hold country name
		 */
		private JTextField txtCountryName;

		/**
		 * ComboBox for continent dropdown.
		 */
		private JComboBox continentComboBox;

		/**
		 * ComboBox for continent dropdown.
		 */
		private JComboBox continentDeletionComboBox;

		/**
		 * Stores reference to the NodeOfMap object.
		 */
		private NodeOfMap mapNode;

		/**
		 * Stores add continent button.
		 */
		private JButton btnDone;

		/**
		 * Stores add neighbours button
		 */
		private JButton btnAddNeighbours;

		/**
		 * Button to enable add continent field.
		 */
		private JButton btnAddContinent;
		
		/**
		 * Creates WriteMap object.
		 */
		private WriteMap mapWriter = new WriteMap();

		/**
		 * Stores the continent name.
		 */
		private String continentName;

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
		 * Stores list model for JList for deleting neighbors.
		 */
		private DefaultListModel<String> model1;
		
		/**
		 * Button for selected neighbours. 
		 */
		private JButton btnSelectedNeighbours;

		/**
		 * Button for continent deletion.
		 */
		private JButton btnDeleteContinent;

		/**
		 * Button for saving map file.
		 */
		private JButton btnSaveMap;

		/**
		 * Button for country deletion.
		 */
		private JButton btnDeleteCountry;

		/**
		 * Stores and displays list of continents.
		 */
		private JComboBox countryListCombobox;

		/**
		 * Button to add a new country.
		 */
		private JButton btnAdd;
		
		/**
		 * Button to update country arrayList
		 */
		private JButton btnAddCountry;
		
		/**
		 * JButton to get possible neighbors of a country for deletion.
		 */
		private JButton btnDeleteNeighbours;
		
		/**
		 * ComboBox to display country list for deleting neighbors.
		 */
		private JComboBox comboBox_4;
		
		/**
		 * JLabel for list of neighbors of each country.
		 */
		private JLabel lblNeighboursOfSelected;
		
		/**
		 * JList to display neighbors of selected country.
		 */
		private JList list_1;
		
		/**
		 * JButton to delete selected countries.
		 */
		private JButton btnDeleteSelectedNeighbours;

		/**
		 * NewMap constructor calls initialize method of the class
		 * @param editMap arraylist of continents.
		 */
		public ExistingMapModifier(ArrayList<NodeOfMap> editMap) {
			continents = editMap;
			initialize();
		}

		/**
		 * Initialize the contents of the frame.
		 */
		public void initialize() {
			this.setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(140, 140, 500, 340);
			this.setTitle("Existing map editor");
			this.setExtendedState(JFrame.MAXIMIZED_BOTH); 

			contentPane = new JPanel();
			contentPane.setBackground(Color.GRAY);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			GridBagLayout gbl_contentPane = new GridBagLayout();
			gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
			gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			contentPane.setLayout(gbl_contentPane);

			JLabel lblCreateYourOwn = new JLabel("Edit the existing map file!");
			lblCreateYourOwn.setFont(new Font("Script MT Bold", Font.PLAIN, 48));
			GridBagConstraints gbc_lblCreateYourOwn = new GridBagConstraints();
			gbc_lblCreateYourOwn.insets = new Insets(0, 0, 5, 0);
			gbc_lblCreateYourOwn.gridwidth = 7;
			gbc_lblCreateYourOwn.gridx = 0;
			gbc_lblCreateYourOwn.gridy = 0;
			contentPane.add(lblCreateYourOwn, gbc_lblCreateYourOwn);

			btnAddContinent = new JButton("New Continent");
			btnAddContinent.setForeground(Color.BLACK);
			btnAddContinent.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
			btnAddContinent.setBackground(new Color(240, 255, 255));
			GridBagConstraints gbc_btnAddContinent = new GridBagConstraints();
			gbc_btnAddContinent.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnAddContinent.insets = new Insets(0, 0, 5, 5);
			gbc_btnAddContinent.gridx = 1;
			gbc_btnAddContinent.gridy = 2;
			contentPane.add(btnAddContinent, gbc_btnAddContinent);

			JLabel lblName = new JLabel("Continent Name");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.anchor = GridBagConstraints.EAST;
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.gridx = 5;
			gbc_lblName.gridy = 2;
			contentPane.add(lblName, gbc_lblName);

			continentNameText = new JTextField();
			continentNameText.setFont(new Font("Tahoma", Font.ITALIC, 13));
			continentNameText.setForeground(Color.BLACK);
			GridBagConstraints gbc_txtContinentNameHere = new GridBagConstraints();
			gbc_txtContinentNameHere.insets = new Insets(0, 0, 5, 0);
			gbc_txtContinentNameHere.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtContinentNameHere.gridx = 6;
			gbc_txtContinentNameHere.gridy = 2;
			continentNameText.setEnabled(false);
			contentPane.add(continentNameText, gbc_txtContinentNameHere);
			continentNameText.setColumns(10);

			continentControlValueText = new JTextField();
			continentControlValueText.setFont(new Font("Tahoma", Font.ITALIC, 13));
			continentControlValueText.setForeground(Color.BLACK);
			continentControlValueText.setEnabled(false);
			GridBagConstraints gbc_txtContinentControlValue = new GridBagConstraints();
			gbc_txtContinentControlValue.insets = new Insets(0, 0, 5, 0);
			gbc_txtContinentControlValue.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtContinentControlValue.gridx = 6;
			gbc_txtContinentControlValue.gridy = 3;
			contentPane.add(continentControlValueText, gbc_txtContinentControlValue);
			continentControlValueText.setColumns(10);

			JLabel lblValue = new JLabel("Control Value");
			GridBagConstraints gbc_lblValue = new GridBagConstraints();
			gbc_lblValue.anchor = GridBagConstraints.EAST;
			gbc_lblValue.insets = new Insets(0, 0, 5, 5);
			gbc_lblValue.gridx = 5;
			gbc_lblValue.gridy = 3;
			contentPane.add(lblValue, gbc_lblValue);

			btnDone = new JButton("Add Continent");
			btnDone.setBackground(new Color(240, 255, 255));
			btnDone.setFont(new Font("Tahoma", Font.BOLD, 13));
			btnDone.setForeground(Color.BLACK);
			GridBagConstraints gbc_btnDone = new GridBagConstraints();
			gbc_btnDone.anchor = GridBagConstraints.EAST;
			gbc_btnDone.insets = new Insets(0, 0, 5, 0);
			gbc_btnDone.gridx = 6;
			gbc_btnDone.gridy = 4;
			contentPane.add(btnDone, gbc_btnDone);

			btnAddCountry = new JButton("New Country");
			btnAddCountry.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
			btnAddCountry.setForeground(Color.BLACK);
			btnAddCountry.setBackground(new Color(240, 255, 255));
			GridBagConstraints gbc_btnAddCountry = new GridBagConstraints();
			gbc_btnAddCountry.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnAddCountry.insets = new Insets(0, 0, 5, 5);
			gbc_btnAddCountry.gridx = 1;
			gbc_btnAddCountry.gridy = 5;
			contentPane.add(btnAddCountry, gbc_btnAddCountry);

			continentComboBox = new JComboBox();			/*continent list*/
			continentComboBox.setToolTipText("Choose existing continent to add new country");
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.gridwidth = 3;
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 2;
			gbc_comboBox.gridy = 5;
			contentPane.add(continentComboBox, gbc_comboBox);
			for (NodeOfMap mapNode : continents) {
				continentComboBox.addItem(mapNode.getNameOfContinent());
			}

			JLabel lblName_1 = new JLabel("Country Name");
			GridBagConstraints gbc_lblName_1 = new GridBagConstraints();
			gbc_lblName_1.anchor = GridBagConstraints.EAST;
			gbc_lblName_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblName_1.gridx = 5;
			gbc_lblName_1.gridy = 6;
			contentPane.add(lblName_1, gbc_lblName_1);

			txtCountryName = new JTextField();
			txtCountryName.setFont(new Font("Tahoma", Font.ITALIC, 13));
			txtCountryName.setForeground(Color.BLACK);
			txtCountryName.setEnabled(false);
			txtCountryName.setText("");
			GridBagConstraints gbc_txtCountryName = new GridBagConstraints();
			gbc_txtCountryName.insets = new Insets(0, 0, 5, 0);
			gbc_txtCountryName.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCountryName.gridx = 6;
			gbc_txtCountryName.gridy = 6;
			contentPane.add(txtCountryName, gbc_txtCountryName);
			txtCountryName.setColumns(10);

			btnDeleteCountry = new JButton("Delete Country");
			btnDeleteCountry.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
			btnDeleteCountry.setForeground(Color.BLACK);
			btnDeleteCountry.setBackground(new Color(240, 255, 255));
			GridBagConstraints gbc_btnDeleteCountry = new GridBagConstraints();
			gbc_btnDeleteCountry.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnDeleteCountry.insets = new Insets(0, 0, 5, 5);
			gbc_btnDeleteCountry.gridx = 1;
			gbc_btnDeleteCountry.gridy = 8;
			contentPane.add(btnDeleteCountry, gbc_btnDeleteCountry);

			countryListCombobox = new JComboBox();			/*list of countries.*/
			GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
			ArrayList<String> choice = new ArrayList<>();
			gbc_comboBox_2.gridwidth = 3;
			gbc_comboBox_2.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox_2.gridx = 2;
			gbc_comboBox_2.gridy = 8;
			contentPane.add(countryListCombobox, gbc_comboBox_2);
			for (NodeOfMap mapNode : continents) {
				for (NodeOfCountry countryNode : mapNode.getCountries()) {
					countryListCombobox.addItem(countryNode.getNameOfCountry());
				}
			}

			btnAdd = new JButton("Add Country");
			btnAdd.setBackground(new Color(240, 255, 255));
			btnAdd.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
			GridBagConstraints gbc_btnAdd = new GridBagConstraints();
			gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
			gbc_btnAdd.anchor = GridBagConstraints.EAST;
			gbc_btnAdd.gridx = 6;
			gbc_btnAdd.gridy = 8;
			contentPane.add(btnAdd, gbc_btnAdd);

			btnDeleteContinent = new JButton("Delete Continent");
			btnDeleteContinent.setBackground(new Color(240, 255, 255));
			btnDeleteContinent.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
			btnDeleteContinent.setForeground(Color.BLACK);
			GridBagConstraints gbc_btnDeleteContinent = new GridBagConstraints();
			gbc_btnDeleteContinent.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnDeleteContinent.insets = new Insets(0, 0, 5, 5);
			gbc_btnDeleteContinent.gridx = 1;
			gbc_btnDeleteContinent.gridy = 11;
			contentPane.add(btnDeleteContinent, gbc_btnDeleteContinent);

			continentDeletionComboBox = new JComboBox();
			GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
			gbc_comboBox_3.gridwidth = 3;
			gbc_comboBox_3.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox_3.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox_3.gridx = 2;
			gbc_comboBox_3.gridy = 11;
			contentPane.add(continentDeletionComboBox, gbc_comboBox_3);
			for (NodeOfMap mapNode : continents) {
				continentDeletionComboBox.addItem(mapNode.getNameOfContinent());
			}

			btnAddNeighbours = new JButton("Choose Neighbours");
			btnAddNeighbours.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
			GridBagConstraints gbc_btnAddNeighbours = new GridBagConstraints();
			gbc_btnAddNeighbours.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnAddNeighbours.insets = new Insets(0, 0, 5, 5);
			gbc_btnAddNeighbours.gridx = 1;
			gbc_btnAddNeighbours.gridy = 14;
			contentPane.add(btnAddNeighbours, gbc_btnAddNeighbours);

			countriesComboBox = new JComboBox();
			GridBagConstraints gbc_comboBox_11 = new GridBagConstraints();
			countriesComboBox.setToolTipText("Select country for adding neighbours");
			gbc_comboBox_11.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox_11.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox_11.gridx = 2;
			gbc_comboBox_11.gridy = 14;
			countriesComboBox.setEnabled(true);
			contentPane.add(countriesComboBox, gbc_comboBox_11);
			for (NodeOfMap mapNode : continents) {
				for (NodeOfCountry countryNode : mapNode.getCountries()) {
					countriesComboBox.addItem(countryNode.getNameOfCountry());
				}
			}

			JLabel lblChooseNeighbours = new JLabel("Possible neighbours for selected country");
			lblChooseNeighbours.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
			GridBagConstraints gbc_lblChooseNeighbours = new GridBagConstraints();
			gbc_lblChooseNeighbours.insets = new Insets(0, 0, 5, 5);
			gbc_lblChooseNeighbours.gridx = 1;
			gbc_lblChooseNeighbours.gridy = 16;
			contentPane.add(lblChooseNeighbours, gbc_lblChooseNeighbours);

			model2 = new DefaultListModel<String>();
			list = new JList();
			JScrollPane scrollPane = new JScrollPane(list);
			scrollPane.setViewportView(list);
			list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			list.setModel(model2);
			GridBagConstraints gbc_list = new GridBagConstraints();
			gbc_list.insets = new Insets(0, 0, 5, 5);
			gbc_list.fill = GridBagConstraints.BOTH;
			gbc_list.gridx = 2;
			gbc_list.gridy = 16;
			contentPane.add(scrollPane, gbc_list);
			list.setEnabled(false);

			btnSelectedNeighbours = new JButton("Add selected countries as neighbours");
			GridBagConstraints gbc_btnDeleteNeighbours = new GridBagConstraints();
			gbc_btnDeleteNeighbours.anchor = GridBagConstraints.EAST;
			gbc_btnDeleteNeighbours.insets = new Insets(0, 0, 5, 5);
			gbc_btnDeleteNeighbours.gridx = 2;
			gbc_btnDeleteNeighbours.gridy = 17;
			contentPane.add(btnSelectedNeighbours, gbc_btnDeleteNeighbours);
			
			btnDeleteNeighbours = new JButton("Delete Neighbours");
			btnDeleteNeighbours.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
			gbc_btnDeleteNeighbours = new GridBagConstraints();
			gbc_btnDeleteNeighbours.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnDeleteNeighbours.insets = new Insets(0, 0, 5, 5);
			gbc_btnDeleteNeighbours.gridx = 1;
			gbc_btnDeleteNeighbours.gridy = 20;
			contentPane.add(btnDeleteNeighbours, gbc_btnDeleteNeighbours);
			
			comboBox_4 = new JComboBox();
			GridBagConstraints gbc_comboBox_4 = new GridBagConstraints();
			gbc_comboBox_4.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox_4.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox_4.gridx = 2;
			gbc_comboBox_4.gridy = 20;
			contentPane.add(comboBox_4, gbc_comboBox_4);
			for (NodeOfMap mapNode : continents) {
				for (NodeOfCountry countryNode : mapNode.getCountries()) {
					comboBox_4.addItem(countryNode.getNameOfCountry());
				}
			}
			
			lblNeighboursOfSelected = new JLabel("Neighbours of selected country");
			lblNeighboursOfSelected.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
			GridBagConstraints gbc_lblNeighboursOfSelected = new GridBagConstraints();
			gbc_lblNeighboursOfSelected.insets = new Insets(0, 0, 5, 5);
			gbc_lblNeighboursOfSelected.gridx = 1;
			gbc_lblNeighboursOfSelected.gridy = 22;
			contentPane.add(lblNeighboursOfSelected, gbc_lblNeighboursOfSelected);
			
			model1 = new DefaultListModel<String>();
			list_1 = new JList();
			list_1.setModel(model1);
			JScrollPane scrollPane1 = new JScrollPane(list_1);
			list_1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			GridBagConstraints gbc_list_1 = new GridBagConstraints();
			gbc_list_1.insets = new Insets(0, 0, 5, 5);
			gbc_list_1.fill = GridBagConstraints.BOTH;
			gbc_list_1.gridx = 2;
			gbc_list_1.gridy = 22;
			contentPane.add(list_1, gbc_list_1);
			list_1.setEnabled(false);
			
			btnDeleteSelectedNeighbours = new JButton("Delete selected Neighbours");
			GridBagConstraints gbc_btnDeleteSelectedNeighbours = new GridBagConstraints();
			gbc_btnDeleteSelectedNeighbours.anchor = GridBagConstraints.EAST;
			gbc_btnDeleteSelectedNeighbours.insets = new Insets(0, 0, 5, 5);
			gbc_btnDeleteSelectedNeighbours.gridx = 2;
			gbc_btnDeleteSelectedNeighbours.gridy = 23;
			contentPane.add(btnDeleteSelectedNeighbours, gbc_btnDeleteSelectedNeighbours);

			btnSaveMap = new JButton("Save Map");
			btnSaveMap.setBackground(new Color(152, 251, 152));
			btnSaveMap.setFont(new Font("Lucida Calligraphy", Font.BOLD, 16));
			GridBagConstraints gbc_btnSaveMap = new GridBagConstraints();
			gbc_btnSaveMap.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnSaveMap.gridx = 6;
			gbc_btnSaveMap.gridy = 29;
			contentPane.add(btnSaveMap, gbc_btnSaveMap);
		
		}
		
		/**
		 * Function to add actionListener to add country button.
		 * @param actionNew receives the actionListener for this function.
		 */
		public void addActionsToAddCountryButton(ActionListener actionNew) {
			btnAddCountry.addActionListener(actionNew);
		}
		
		/**
		 * Function to add actionListener to add-continent Button.
		 * @param actionNew receives the actionListener for this function.
		 */
		public void addActionsToAddContinentButton(ActionListener actionNew) {
			btnAddContinent.addActionListener(actionNew);
		}
	    
		/**
		 * Function to add actionListener to button for updating list of continent .
		 * @param actionNew receives the actionListener for this function.
		 */
		public void addActionsToBtnComplete(ActionListener actionNew) {
			btnDone.addActionListener(actionNew);
		}
		
		/**
		 * Function to add actionListener to button for updating country list.
		 * @param actionNew receives the actionListener for this function.
		 */
		public void addActionsToAddButton(ActionListener actionNew) {
			btnAdd.addActionListener(actionNew);
		}
		
		/**Action event for button to Add  neighbours
		 * Function to add actionListener to add neighbours button.
		 * @param actionNew receives the actionListener for this function.
		 */
		public void addActionsToAddNeighboursButton(ActionListener actionNew) {
			btnAddNeighbours.addActionListener(actionNew);
		}
		
		/**Action event for button to delete  neighbours
		 * @see EditCreateMapController
		 * @param actionNew actionListener for btnDeleteNeighbors.
		 */
		public void addActionstoDeleteNeighboursButton(ActionListener actionNew) {
			 		btnDeleteNeighbours.addActionListener(actionNew);
		}
		
		/**Action event for button to delete selected neighbours
		 * @see mapEditorController 
		 * @param actionNew actionListener for btnDeleteSelectedNeighbours.
		 */
		public void addActionsToDeleteSelectedNeighboursButton(ActionListener actionNew) {
			 		btnDeleteSelectedNeighbours.addActionListener(actionNew);
		}
		
		/**
		 * Function to add actionListener to select the Neighbours Bbutton.
		 * @param actionNew receives the actionListener for this function.
		 */
		public void addActionsToNeighbourChooseButton(ActionListener actionNew) {
			btnSelectedNeighbours.addActionListener(actionNew);
		}
		
		/**
		 * Function to add actionListener to delete existing continent button.
		 * @param actionNew receives the actionListener for this function.
		 */
		public void addActionsToDeleteContinentButton(ActionListener actionNew) {
			btnDeleteContinent.addActionListener(actionNew);
		}
		
		/**
		 *  add actionListener to save mAP button.
		 * @param actionNew receives the actionListener for this function.
		 */
		public void addActionsToSaveMapButton(ActionListener actionNew) {
			btnSaveMap.addActionListener(actionNew);
		}
		
		/**
		 *  add actionListener to delete country button.
		 * @param actionNew receives the actionListener for this function.
		 */
		public void addActionsToDeleteCountryButton(ActionListener actionNew) {
			btnDeleteCountry.addActionListener(actionNew);
		}
		
		/**
		 * shows error message for not selecting neighbours
		 */
		public void noNeighboursSelecteError() {
			JOptionPane.showMessageDialog(contentPane, "Select neighbours first", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		/**
		 *  enable the fields of continent.
		 */
		public void continentFieldsEnable() {
			continentNameText.setEnabled(true);
			continentControlValueText.setEnabled(true);
		}
		
		/**
		 * checks for unique continents..
		 * @return true if continent already exist.
		 */
		public boolean continentExistCheck() {
			Boolean continentExist = false;
			if(continentComboBox.getItemCount()!=0) {
				continentExist = true;
			}
			return continentExist;
		}
		
		/**
		 *  get the country name mentioned.
		 * @return the country name entered.
		 */
		public String getNAmeOfCountry() {
			return (txtCountryName.getText());
		}

		/**
		 * Funtion to return list of selected neighbours.
		 * @return list of selected neighbours.
		 */
		public List getListOfNeighbours() {
			return (list.getSelectedValuesList());
		}
		
		/**
		 * gives list of neighbors for deletion
		 * @return list of neighbor countries for deletion.
		 */
		public List getNeighboursList_1() {
			 return (list_1.getSelectedValuesList());
		}
		
		/**
		 * enable JList.
		 */
		public void jListEnabled() {
			list.setEnabled(true);
		}
		
		/**
		 * enables JList of neighbors.
		 */
		public void jListEnabled_1() {
			list_1.setEnabled(true);
		}
		
		/**
		 * clears and disables the country-name-field..
		 */
		public void countryFieldDisable() {
			txtCountryName.setText("");
			txtCountryName.setEnabled(false);
		}
		
		/**
		 *  to enable the country-text field.
		 */
		public void countryfieldEnable() {
			txtCountryName.setEnabled(true);
		}
		
		/**
		 * to disable continent-fields
		 */
		public void continentFieldDisabled() {
			continentNameText.setText("");
			continentControlValueText.setText("");
			continentNameText.setEnabled(false);
			continentControlValueText.setEnabled(false);
		}
		
		/**
		 *  get selected country to add as neighbours.
		 * @return selected country for adding neighbours.
		 */
		public String getSelectedCountryForNeighbours() {
			return (countriesComboBox.getSelectedItem().toString());
		}
		
		/**
		 *  get the selected country for deletion of its neighbors.
		 * @return selected country for deleting its neighbors.
		 */
		public String getSelectedCountryForNeighbourDeletion() {
			return (comboBox_4.getSelectedItem().toString());
		}
		
		/**
		 *Function to get selected country for deletion.
		 * @return selected country for deletion.
		 */
		public String getCountryForDeletion() {
			return (countryListCombobox.getSelectedItem().toString());
		}
		
		/**
		 *  get the selected continent to be deleted.
		 * @return the contintent selected for deletion.
		 */
		public String getContinentForDeletion() {
			return (continentDeletionComboBox.getSelectedItem().toString());
		}
		
		/**
		 * retuen selected continent name.
		 * @return the selected continent.
		 */
		public String getSelectedContinent() {
			return (continentComboBox.getSelectedItem().toString());
		}
		
		/**
		 *  clear the List of neighbours.
		 */
		public void clearNeighboursJList() {
			model2.removeAllElements();
		}
		
		/**
		 * Clears the JList of neighbors.
		 */
		public void clearNeighboursJList_1() {
			model1.removeAllElements();
		}

		/**
		 * Function to update the neighbour JList view.
		 * @param neighbour receives the neighbour to be added.
		 */
		public void addPossibleNeighboursToJList(String neighbour) {
			model2.addElement(neighbour);
		}
		
		/**
		 * This function adds neighbors to JList.
		 * @param neighbour receives the neighbor to be added to the list.
		 */
		public void addPossibleNeighboursToJList_1(String neighbour) {
			model1.addElement(neighbour);
		}

		/**
		 *  get the continent name.
		 * @return the continent name.
		 */
		public String getContinentName() {
			continentName = continentNameText.getText();
			return continentName;
		}

		/**
		 *  display error message for not entering values box.
		 */
		public void errorEnterValues() {
			JOptionPane.showMessageDialog(contentPane, "Enter values first", "Error", JOptionPane.ERROR_MESSAGE);
		}

		/**
		 *  displays error message for already existing continent
		 */
		public void errorContinentAlreadyExist() {
			JOptionPane.showMessageDialog(contentPane, "Continent already exist", "Error", JOptionPane.ERROR_MESSAGE);
		}

		/**
		 *  displays error message for already existing country
		 */
		public void errorCountryAlreadyExist() {
			JOptionPane.showMessageDialog(contentPane, "Country already exist", "Error", JOptionPane.ERROR_MESSAGE);
		}

		/**
		 * displays error message for null continent
		 */
		public void errorNullContinent() {
			JOptionPane.showMessageDialog(contentPane, "Add continent first", "Error", JOptionPane.ERROR_MESSAGE);
		}

		/**
		 * displays error message for null country.
		 */
		public void errorNullCountry() {
			JOptionPane.showMessageDialog(contentPane, "Map validation error", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		/**
		 * displays  success message.
		 */
		public void successMessage() {
			JOptionPane.showMessageDialog(contentPane, "Successfully saved", "Message", JOptionPane.INFORMATION_MESSAGE);
		}

		/**
		 * displays  with success message on successfully addition of neighbours.
		 */
		public void successfullyAddedNeighbours() {
			JOptionPane.showMessageDialog(contentPane, "Successfully added neighbours", "Message", JOptionPane.INFORMATION_MESSAGE);
		}

		/**
		 * displays  success message on deletion of neighbours.
		 */
		public void successfullyDeletedNeighbours() {
			JOptionPane.showMessageDialog(contentPane, "Successfully deleted neighbours", "Message", JOptionPane.INFORMATION_MESSAGE);
		}

		/**
		 *  provides control value from textfield.
		 * @return the continent control value.
		 */
		public String getControlValue() {
			cv = (continentControlValueText.getText());
			return cv;
		}

		/**
		 *removes all continents from textfield 
		 */
		public void removeContinentFromFields() {
			continentNameText.setText("");
			continentControlValueText.setText("");
			continentNameText.setEnabled(false);
			continentControlValueText.setEnabled(false);
		}

		/**
		 * clears all data in continent lists displayed in continentComboBox
		 */
		public void continentComboBoxContentCleared() {
			continentComboBox.removeAllItems();
			continentDeletionComboBox.removeAllItems();
		}

		/**
		 * clears all data in country lists displayed in continentComboBox
		 */
		public void countriesComboBoxContentClear() {
			countryListCombobox.removeAllItems();
			countriesComboBox.removeAllItems();
			comboBox_4.removeAllItems();
		}

		/**
		 * adds data in continent lists displayed in continentComboBox
		 * @param continent receives the newly-added continent name.
		 */
		public void setDataInContinentsComboBox(String continent) {
			continentComboBox.addItem(continent);
			continentDeletionComboBox.addItem(continent);
		}

		/**
		 * adds data in country lists displayed in continentComboBox
		 * @param country receives the newly added-country name.
		 */
		public void setDataInCountriesComboBox(String country) {
			countriesComboBox.addItem(country);
			countryListCombobox.addItem(country);
			comboBox_4.addItem(country);
		}
		
		/**
		 *provides existing map-editor frame  for this class .
		 * @return returns the existing map editor frame.
		 */
		public ExistingMapModifier getFrame() {
			return this;
		}
	}

