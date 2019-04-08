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
 *@version 2.0
 */
public class NewMapCreation extends JFrame {

	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = -331979393598025771L;

	/**
	 * Stores the JPanel of the frame of this class.
	 */
	private JPanel contentPane;
	
	/**
	 * TextField that receives continent name.
	 */
	private JTextField continentNameText;
	
	/**
	 * TextField that receives control value.
	 */
	private JTextField continentControlValueText;
	
	/**
	 * TextField that receives country name.
	 */
	private JTextField countryNameText;
	
	/**
	 * ComboBox for continent dropdown.
	 */
	private JComboBox continentComboBox;
	
	/**
	 * ComboBox for continent dropdown.
	 */
	private JComboBox continentDeletionComboBox;
	
	/**
	 * Stores the object of NodeOfMap.
	 */
	private NodeOfMap mapNode;
	
	/**
	 * Button to add continent.
	 */
	private JButton btnDone;
	
	/**
	 * Button to add neighbours.
	 */
	private JButton addNeighboursButton;
	
	/**
	 * Creates the object of mapWrited class.
	 */
	private WriteMap mapWriter = new WriteMap();
	
	/**
	 * String to hold the continent name.
	 */
	private String nameOfContinent;
	
	/**
	 * String variable to hold the control value.
	 */
	private String cv;
	
	/**
	 * Stores list of neighbours.
	 */
	private JList list;
	
	/**
	 * ComboBox for storing list of countries.
	 */
	private JComboBox countriesComboBox;
	
	/**
	 * Defines list model for JList of adding neighbours.
	 */
	private DefaultListModel<String> model2;
	
	/**
	 * Defines list model for JList of deleting models.
	 */
	private DefaultListModel<String> model1;
	
	/**
	 * Button to get country for adding neighbours.
	 */
	private JButton neighbourChooseButton;
	
	/**
	 * Button to delete a continent.
	 */
	private JButton deleteContinentButton;
	
	/**
	 * Button to save a map.
	 */
	private JButton saveMapButton;
	
	/**
	 * Button to delete a country.
	 */
	private JButton deleteCountryButton;
	
	/**
	 * ComboBox to hold list of all countries.
	 */
	private JComboBox countryListCombobox;
	
	/**
	 * Button to add new country.
	 */
	private JButton btnAdd;
	
	/**
	 * Button to add new country.
	 */
	private JButton btnAddCountry;
	
	/**
	 * Button to add new continent.
	 */
	private JButton btnAddContinent;
	
	/**
	 * ComboBox for displaying list of countries.
	 */
	private JComboBox comboBox_4;
	
	/**
	 * Label for JList of neighbors.
	 */
	private JLabel lblNeighboursOfSelected;
	
	/**
	 * JList for displaying neighbors of selected country.
	 */
	private JList list_1;
	
	/**
	 * JButton to display possible neighbors of a country for deletion. 
	 */
	private JButton btnDeleteSelectedNeighbours;
	
	/**
	 * JButton to delete selected neighbors for the selected country.
	 */
	private JButton DeleteNeighboursButton;

	/**
	 * NewMapCreationconstructor calls initialize method of the class
	 */
	public NewMapCreation() {
		begin();
	}

	/**
	 * all the contents of the frame are initialized.
	 */
	@SuppressWarnings("unchecked")
	public void begin() {
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(130, 130, 480, 320);
		this.setTitle("New-Map");
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

		JLabel lblCreateYourOwn = new JLabel("Create Own Map for Game (New Map)!");
		lblCreateYourOwn.setFont(new Font("Bookman Old Style", Font.PLAIN, 48));
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

		continentComboBox = new JComboBox();			/*continent list.*/
		continentComboBox.setToolTipText("Choose existing continent to add new country");
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 3;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 5;
		contentPane.add(continentComboBox, gbc_comboBox);

		JLabel lblName_1 = new JLabel("Country Name");
		GridBagConstraints gbc_lblName_1 = new GridBagConstraints();
		gbc_lblName_1.anchor = GridBagConstraints.EAST;
		gbc_lblName_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblName_1.gridx = 5;
		gbc_lblName_1.gridy = 6;
		contentPane.add(lblName_1, gbc_lblName_1);

		countryNameText = new JTextField();
		countryNameText.setFont(new Font("Tahoma", Font.ITALIC, 13));
		countryNameText.setForeground(Color.BLACK);
		countryNameText.setEnabled(false);
		GridBagConstraints gbc_txtCountryName = new GridBagConstraints();
		gbc_txtCountryName.insets = new Insets(0, 0, 5, 0);
		gbc_txtCountryName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCountryName.gridx = 6;
		gbc_txtCountryName.gridy = 6;
		contentPane.add(countryNameText, gbc_txtCountryName);
		countryNameText.setColumns(10);

		deleteCountryButton = new JButton("Delete Country");
		deleteCountryButton.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
		deleteCountryButton.setForeground(Color.BLACK);
		deleteCountryButton.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_btnDeleteCountry = new GridBagConstraints();
		gbc_btnDeleteCountry.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDeleteCountry.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteCountry.gridx = 1;
		gbc_btnDeleteCountry.gridy = 8;
		contentPane.add(deleteCountryButton, gbc_btnDeleteCountry);

		countryListCombobox = new JComboBox();			/*list of countries.*/
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		ArrayList<String> choice = new ArrayList<>();
		gbc_comboBox_2.gridwidth = 3;
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 2;
		gbc_comboBox_2.gridy = 8;
		contentPane.add(countryListCombobox, gbc_comboBox_2);

		btnAdd = new JButton("Add Country");			/*adding countries*/
		btnAdd.setBackground(new Color(240, 255, 255));
		btnAdd.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.anchor = GridBagConstraints.EAST;
		gbc_btnAdd.gridx = 6;
		gbc_btnAdd.gridy = 8;
		contentPane.add(btnAdd, gbc_btnAdd);

		/*deleting continent*/
		deleteContinentButton = new JButton("Delete Continent");
		deleteContinentButton.setBackground(new Color(240, 255, 255));
		deleteContinentButton.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
		deleteContinentButton.setForeground(Color.BLACK);
		GridBagConstraints gbc_btnDeleteContinent = new GridBagConstraints();
		gbc_btnDeleteContinent.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDeleteContinent.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteContinent.gridx = 1;
		gbc_btnDeleteContinent.gridy = 11;
		contentPane.add(deleteContinentButton, gbc_btnDeleteContinent);

		continentDeletionComboBox = new JComboBox();
		GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
		gbc_comboBox_3.gridwidth = 3;
		gbc_comboBox_3.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_3.gridx = 2;
		gbc_comboBox_3.gridy = 11;

		contentPane.add(continentDeletionComboBox, gbc_comboBox_3);

		addNeighboursButton = new JButton("Choose Neighbours");
		addNeighboursButton.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
		GridBagConstraints gbc_btnAddNeighbours = new GridBagConstraints();
		gbc_btnAddNeighbours.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddNeighbours.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddNeighbours.gridx = 1;
		gbc_btnAddNeighbours.gridy = 14;
		contentPane.add(addNeighboursButton, gbc_btnAddNeighbours);

		countriesComboBox = new JComboBox();
		GridBagConstraints gbc_comboBox_11 = new GridBagConstraints();
		countriesComboBox.setToolTipText("Select country for adding neighbours");
		gbc_comboBox_11.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_11.gridx = 2;
		gbc_comboBox_11.gridy = 14;
		countriesComboBox.setEnabled(true);
		contentPane.add(countriesComboBox, gbc_comboBox_11);

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
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setModel(model2);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 2;
		gbc_list.gridy = 16;
		contentPane.add(scrollPane, gbc_list);
		list.setEnabled(false);

		neighbourChooseButton = new JButton("Add selected countries as neighbours");
		GridBagConstraints gbc_btnDeleteNeighbours = new GridBagConstraints();
		gbc_btnDeleteNeighbours.anchor = GridBagConstraints.EAST;
		gbc_btnDeleteNeighbours.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteNeighbours.gridx = 2;
		gbc_btnDeleteNeighbours.gridy = 17;
		contentPane.add(neighbourChooseButton, gbc_btnDeleteNeighbours);
		gbc_btnDeleteNeighbours.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDeleteNeighbours.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteNeighbours.gridx = 1;
		gbc_btnDeleteNeighbours.gridy = 20;
		
		DeleteNeighboursButton = new JButton("Delete Neighbours");
		DeleteNeighboursButton.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
		gbc_btnDeleteNeighbours = new GridBagConstraints();
		gbc_btnDeleteNeighbours.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDeleteNeighbours.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteNeighbours.gridx = 1;
		gbc_btnDeleteNeighbours.gridy = 20;
		contentPane.add(DeleteNeighboursButton, gbc_btnDeleteNeighbours);
		
		comboBox_4 = new JComboBox();
		GridBagConstraints gbc_comboBox_4 = new GridBagConstraints();
		gbc_comboBox_4.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_4.gridx = 2;
		gbc_comboBox_4.gridy = 20;
		contentPane.add(comboBox_4, gbc_comboBox_4);
		
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
		
		btnDeleteSelectedNeighbours = new JButton("Delete selected neighbours");
		GridBagConstraints gbc_btnDeleteSelectedNeighbours = new GridBagConstraints();
		gbc_btnDeleteSelectedNeighbours.anchor = GridBagConstraints.EAST;
		gbc_btnDeleteSelectedNeighbours.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteSelectedNeighbours.gridx = 2;
		gbc_btnDeleteSelectedNeighbours.gridy = 23;
		contentPane.add(btnDeleteSelectedNeighbours, gbc_btnDeleteSelectedNeighbours);

		saveMapButton = new JButton("Save New-Map");
		saveMapButton.setBackground(new Color(152, 251, 152));
		saveMapButton.setFont(new Font("Lucida Calligraphy", Font.BOLD, 16));
		GridBagConstraints gbc_btnSaveMap = new GridBagConstraints();
		gbc_btnSaveMap.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSaveMap.gridx = 6;
		gbc_btnSaveMap.gridy = 29;
		contentPane.add(saveMapButton, gbc_btnSaveMap);
	}

	/**action events  for button to add New country
	 * @see EditCreateMapController
	 * @param newAction actionListener for add country button
	 */
	public void addActionsToAddCountryButton(ActionListener newAction) {
		btnAddCountry.addActionListener(newAction);
	}

	/**action events  for button to add New continent
	 * @see EditCreateMapController
	 * @param newAction actionlistener for add continent button.
	 */
	public void addActionsToAddContinentButton(ActionListener newAction) {
		btnAddContinent.addActionListener(newAction);
	}

	/**add action to button complete
	 * @see EditCreateMapController
	 * @param newAction actionListener for btnDone
	 */
	public void addActionsToBtnComplete(ActionListener newAction) {
		btnDone.addActionListener(newAction);
	}

	/**add action to save new added country button
	 * @see EditCreateMapController
	 * @param newAction actionListener for btnAdd
	 */
	public void addActionsToAddButton(ActionListener newAction) {
		btnAdd.addActionListener(newAction);
	}

	/**add action to save newly added neighbours button
	 * @see EditCreateMapController
	 * @param newAction actionListener for addNeighboursButton
	 */
	public void addActionsToAddNeighboursButton(ActionListener newAction) {
		addNeighboursButton.addActionListener(newAction);
	}

	/**add action to enable delete selected neighbour button
	 * @see EditCreateMapController
	 * @param newAction actionListener for DeleteNeighboursButton
	 */
	public void addActionstoDeleteNeighboursButton(ActionListener newAction) {
		DeleteNeighboursButton.addActionListener(newAction);
	}
	
	/**add actions to add selected countries as neighbour 
	 * @see EditCreateMapController
	 * @param newAction actionListener for neighbourChooseButton
	 */
	public void addActionsToNeighbourChooseButton(ActionListener newAction) {
		neighbourChooseButton.addActionListener(newAction);
	}
	
	/**add action to delete selected neighbour button
	 * @see EditCreateMapController
	 * @param newAction actionListener for btnDeleteSelectedNeighbours
	 */
	public void addActionsToDeleteSelectedNeighboursButton(ActionListener newAction) {
		btnDeleteSelectedNeighbours.addActionListener(newAction);
	}

	/**add action to delete selected continent button
	 * @see EditCreateMapController
	 * @param newAction actionListener for deleteContinentButton
	 */
	public void addActionsToDeleteContinentButton(ActionListener newAction) {
		deleteContinentButton.addActionListener(newAction);
	}

	/**action even to save map button
	 * @see EditCreateMapController
	 * @param newAction actionListener for saveMapButton
	 */
	public void addActionsToSaveMapButton(ActionListener newAction) {
		saveMapButton.addActionListener(newAction);
	}

	/**action event for button to delete selected country
	 * @see EditCreateMapController
	 * @param newAction actionListener for deleteCountryButton
	 */
	public void addActionsToDeleteCountryButton(ActionListener newAction) {
		deleteCountryButton.addActionListener(newAction);
	}

	/**
	 * check for  continent existence 
	 * @return boolean value whether country exist or not
	 */
	public boolean continentExistCheck() {
		Boolean ContinentPresent = false;
		if(continentComboBox.getItemCount()!=0) {
			ContinentPresent = true;
		}
		return ContinentPresent;
	}

	/**
	 * get the country-name
	 * @return country-name
	 */
	public String getNAmeOfCountry() {
		return (countryNameText.getText());
	}

	/**
	 * to get the neighbours-countries from list
	 * @return list of neighbours countries selected
	 */
	public List getListOfNeighbours() {
		return (list.getSelectedValuesList());
	}
	
	/**
	 * to get list of selected-neighbors to delete
	 * @return list of selected neighbors to delete.
	 */
	public List getListOfNeighbours_1() {
		return (list_1.getSelectedValuesList());
	}

	/**
	 * enable the J-list of countries
	 */
	public void jListEnabled() {
		list.setEnabled(true);
	}
	
	/**
	 * enable the  J-List of neighbors.
	 */
	public void jListEnabled_1() {
		list_1.setEnabled(true);
	}

	/**
	 * disabling the country field
	 */
	public void countryFieldDisabled() {
		countryNameText.setText("");
		countryNameText.setEnabled(false);
	}

	/**
	 * to disable the continent-fields
	 */
	public void continentFieldDisabled() {
		continentNameText.setText("");
		continentControlValueText.setText("");
		continentNameText.setEnabled(false);
		continentControlValueText.setEnabled(false);
	}

	/**
	 * to get the selected countries in comboBox
	 * @return list of countries choosen through countryListCombobox that includes selection of countries
	 */
	public String getSelectedCountryForNeighbours() {
		return (countriesComboBox.getSelectedItem().toString());
	}
	
	/**
	 * Gets the country for deleting its neighbors.
	 * @return the country for neighbor deletion.
	 */
	public String getSelectedCountryForNeighbourDeletion() {
		return (comboBox_4.getSelectedItem().toString());
	}

	/**
	 * get countries for deletion in map creation frame window
	 * @return list of countries selected for deletion 
	 */
	public String getCountriesToRemove() {
		return (countryListCombobox.getSelectedItem().toString());
	}

	/**
	 *  to get continents for deletion
	 * @return list of continents to be deleted
	 */
	public String continentForDeletion() {
		return (continentDeletionComboBox.getSelectedItem().toString());
	}

	/**
	 * to get selected continents
	 * @return list of continents-selected
	 */
	public String getSelectedContinent() {
		return (continentComboBox.getSelectedItem().toString());
	}

	/**
	 * clear the list of neighbors
	 */
	public void clearNeighboursJList() {
		model2.removeAllElements();
	}
	
	/**
	 * clears the JList of neighbor countries.
	 */
	public void clearNeighboursJList_1() {
		model1.removeAllElements();
	}

	/**
	 * add possible neighbor countries to the list
	 * @param neighbour countries
	 */
	public void addPossibleNeighboursInJList(String neighbour) {
		model2.addElement(neighbour);
	}
	
	/**
	 * add possible neighbor countries to the list
	 * @param neighbour receives neighbor country to be added as neighbours.
	 */
	public void addPossibleNeighboursToJList_1(String neighbour) {
		model1.addElement(neighbour);
	}

	/**
	 *  to get the  name of continent
	 * @return continent name
	 */
	public String getNameOfContinent() {
		nameOfContinent = continentNameText.getText();
		return nameOfContinent;
	}

	/**
	 * displays  with success message on successfully addition of neighbours.
	 */
	public void successfullyAddedNeighbours() {
		JOptionPane.showMessageDialog(contentPane, "Successfully added neighbours", "Message", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * displays  with success message on successfully deletion of neighbours.
	 */
	public void successfullyDeletedNeighbours() {
		JOptionPane.showMessageDialog(contentPane, "Successfully deleted neighbours", "Message", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * display error message for not entering values box.
	 */
	public void errorEnterValues() {
		JOptionPane.showMessageDialog(contentPane, "Enter values first", "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * display error message for not selecting neighbours from box.
	 */
	public void errorNeighboursNotSelected() {
		JOptionPane.showMessageDialog(contentPane, "Select neighbours first", "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * display error dialogue box for already existing continent
	 */
	public void errorContinentAlreadyExist() {
		JOptionPane.showMessageDialog(contentPane, "Continent already exist", "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * display error dialogue box for already existing country
	 */
	public void errorNullContinent() {
		JOptionPane.showMessageDialog(contentPane, "Country already-exist", "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * displaya error dialogue box for null continent
	 */
	public void nullContinentError() {
		JOptionPane.showMessageDialog(contentPane, "fitsr Add continent", "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 *displays error dialogue box for null country
	 */
	public void errorNullCountry() {
		JOptionPane.showMessageDialog(contentPane, "Map validation Error!", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	
	/**
	 * show success dialog box for successfully  saving map.
	 */
	public void successMessage() {
		JOptionPane.showMessageDialog(contentPane, "Successfully saved new Map!", "Message", JOptionPane.INFORMATION_MESSAGE);
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
	 * removing or disabling continent fields 
	 */
	public void clearContinentFields() {
		continentNameText.setText("");
		continentControlValueText.setText("");
		continentNameText.setEnabled(false);
		continentControlValueText.setEnabled(false);
	}

	/**
	 *  to enable country-field.
	 */
	public void countryFeldToEnable() {
		countryNameText.setEnabled(true);
	}

	/**
	 * enable continent fields.  to enter data
	 */
	public void continentFeldToEnable() {
		continentNameText.setEnabled(true);
		continentControlValueText.setEnabled(true);
	}
	/**
	 * clearing the lists from continentComboBox
	 */
	public void continentContentsClearedFromComboBox() {
		continentComboBox.removeAllItems();
		continentDeletionComboBox.removeAllItems();
	}

	/**
	 * clear list of countries from comboBox
	 */
	public void countriesContentsClearedFromComboBox() {
		countryListCombobox.removeAllItems();
		countriesComboBox.removeAllItems();
		comboBox_4.removeAllItems();
	}

	/**
	 * to set the continents in the list of continentscountriesContentsCleared
	 * @param continent name of continent
	 */
	public void setDataInContinentsComboBox(String continent) {
		continentComboBox.addItem(continent);
		continentDeletionComboBox.addItem(continent);
	}

	/**
	 * to add country to the list
	 * @param country name of country
	 */
	public void setDataInCountriesComboBox(String country) {
		countriesComboBox.addItem(country);
		countryListCombobox.addItem(country);
		comboBox_4.addItem(country);
	}
	
	/**
	 * gets the JFrame of newMap.
	 * @return the current frame.
	 */
	public NewMapCreation getFrame() {
		return this;
	}
}

