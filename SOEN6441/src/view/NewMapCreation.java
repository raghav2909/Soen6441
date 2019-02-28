/**
 * 
 */
package view;
import controllers.*;
import model.MapNode;
import model.MapWriter;
import view.mapeditor.Edit_Create_Map_Controller;

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


/**
 * This class opens the view for add/delete 
 * of continents andcountries for creating new map 
 * @author raghavsharda
 *@author Gursharan
 */
public class NewMapCreation extends JFrame {
	/**
	 * NewMap constructor calls initialize method of the class
	 */
	public NewMapCreation() {
		begin();
	}
		private JPanel contentPane;
		private JTextField continentNameText;
		private JTextField continentControlValueText;
		private JTextField countryNameText;
		private JComboBox comboBox;
		private JComboBox comboBox3;
		private MapNode mapNode;
		private JButton btnComplete;
		private JButton addNeighboursButton;
		private MapWriter mapWriter = new MapWriter();
		private String continentName;
		private String cv;
		private JList list;
		private JComboBox comboBox1;
		private DefaultListModel<String> model2;
		private JButton neighbourChooseButton;
		private JButton deleteContinentButton;
		private JButton saveMapButton;
		private JButton deleteCountryButton;
		private JComboBox comboBox2;
		private JButton addButton;
		private JButton addCountryButton;
		private JButton addContinentButton;
		
		/**
		 * Initialize the contents of the frame.
		 */
		@SuppressWarnings("unchecked")
		public void begin() {
			this.setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(140, 140, 500, 340);
			this.setTitle("New Map");
			this.setExtendedState(JFrame.MAXIMIZED_BOTH); 

			contentPane = new JPanel();
			contentPane.setBackground(Color.GRAY);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			GridBagLayout gbl_contentPane = new GridBagLayout();
			gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
			gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			contentPane.setLayout(gbl_contentPane);

			JLabel lblCreateYourOwn = new JLabel("Create your own Game Map!");
			lblCreateYourOwn.setFont(new Font("Script MT Bold", Font.PLAIN, 48));
			GridBagConstraints gbc_lblCreateYourOwn = new GridBagConstraints();
			gbc_lblCreateYourOwn.insets = new Insets(0, 0, 5, 0);
			gbc_lblCreateYourOwn.gridwidth = 7;
			gbc_lblCreateYourOwn.gridx = 0;
			gbc_lblCreateYourOwn.gridy = 0;
			contentPane.add(lblCreateYourOwn, gbc_lblCreateYourOwn);

			JButton addContinentButton = new JButton("New Continent");
			addContinentButton.setForeground(Color.BLACK);
			addContinentButton.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
			addContinentButton.setBackground(new Color(240, 255, 255));
			GridBagConstraints gbc_btnAddContinent = new GridBagConstraints();
			gbc_btnAddContinent.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnAddContinent.insets = new Insets(0, 0, 5, 5);
			gbc_btnAddContinent.gridx = 1;
			gbc_btnAddContinent.gridy = 2;
			contentPane.add(addContinentButton, gbc_btnAddContinent);

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

			btnComplete = new JButton("Add Continent");
			btnComplete.setBackground(new Color(240, 255, 255));
			btnComplete.setFont(new Font("Tahoma", Font.BOLD, 13));
			btnComplete.setForeground(Color.BLACK);
			GridBagConstraints gbc_btnDone = new GridBagConstraints();
			gbc_btnDone.anchor = GridBagConstraints.EAST;
			gbc_btnDone.insets = new Insets(0, 0, 5, 0);
			gbc_btnDone.gridx = 6;
			gbc_btnDone.gridy = 4;
			contentPane.add(btnComplete, gbc_btnDone);

			addCountryButton = new JButton("New Country");
			addCountryButton.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
			addCountryButton.setForeground(Color.BLACK);
			addCountryButton.setBackground(new Color(240, 255, 255));
			GridBagConstraints gbc_btnAddCountry = new GridBagConstraints();
			gbc_btnAddCountry.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnAddCountry.insets = new Insets(0, 0, 5, 5);
			gbc_btnAddCountry.gridx = 1;
			gbc_btnAddCountry.gridy = 5;
			contentPane.add(addCountryButton, gbc_btnAddCountry);

			comboBox = new JComboBox();			/*continent list.*/
			comboBox.setToolTipText("Choose existing continent to add new country");
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.gridwidth = 3;
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 2;
			gbc_comboBox.gridy = 5;
			contentPane.add(comboBox, gbc_comboBox);

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

			/**
			 * includes list of countries to be deleted
			 */
			comboBox2 = new JComboBox();			/*list of countries.*/
			GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
			ArrayList<String> choice = new ArrayList<>();
			gbc_comboBox_2.gridwidth = 3;
			gbc_comboBox_2.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox_2.gridx = 2;
			gbc_comboBox_2.gridy = 8;
			contentPane.add(comboBox2, gbc_comboBox_2);

			addButton = new JButton("Add Country");			/*adding countries*/
			addButton.setBackground(new Color(240, 255, 255));
			addButton.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
			GridBagConstraints gbc_btnAdd = new GridBagConstraints();
			gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
			gbc_btnAdd.anchor = GridBagConstraints.EAST;
			gbc_btnAdd.gridx = 6;
			gbc_btnAdd.gridy = 8;
			contentPane.add(addButton, gbc_btnAdd);

			deleteContinentButton = new JButton("Delete Continent");			/*deleting continent*/
			deleteContinentButton.setBackground(new Color(240, 255, 255));
			deleteContinentButton.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
			deleteContinentButton.setForeground(Color.BLACK);
			GridBagConstraints gbc_btnDeleteContinent = new GridBagConstraints();
			gbc_btnDeleteContinent.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnDeleteContinent.insets = new Insets(0, 0, 5, 5);
			gbc_btnDeleteContinent.gridx = 1;
			gbc_btnDeleteContinent.gridy = 11;
			contentPane.add(deleteContinentButton, gbc_btnDeleteContinent);

			/**
			 * includes list of continents to be deleted 
			 */
			comboBox3 = new JComboBox();
			GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
			gbc_comboBox_3.gridwidth = 3;
			gbc_comboBox_3.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox_3.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox_3.gridx = 2;
			gbc_comboBox_3.gridy = 11;

			contentPane.add(comboBox3, gbc_comboBox_3);

			addNeighboursButton = new JButton("Choose Neighbours");
			addNeighboursButton.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
			GridBagConstraints gbc_btnAddNeighbours = new GridBagConstraints();
			gbc_btnAddNeighbours.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnAddNeighbours.insets = new Insets(0, 0, 5, 5);
			gbc_btnAddNeighbours.gridx = 1;
			gbc_btnAddNeighbours.gridy = 14;
			contentPane.add(addNeighboursButton, gbc_btnAddNeighbours);

			/**
			 * include all neighours to be included
			 */
			comboBox1 = new JComboBox();
			GridBagConstraints gbc_comboBox_11 = new GridBagConstraints();
			comboBox1.setToolTipText("Select country for adding neighbours");
			gbc_comboBox_11.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox_11.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox_11.gridx = 2;
			gbc_comboBox_11.gridy = 14;
			comboBox1.setEnabled(true);
			contentPane.add(comboBox1, gbc_comboBox_11);

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
			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.anchor = GridBagConstraints.EAST;
			gbc_button.insets = new Insets(0, 0, 5, 5);
			gbc_button.gridx = 2;
			gbc_button.gridy = 17;
			contentPane.add(neighbourChooseButton, gbc_button);

			saveMapButton = new JButton("Save Map");
			saveMapButton.setBackground(new Color(152, 251, 152));
			saveMapButton.setFont(new Font("Lucida Calligraphy", Font.BOLD, 16));
			GridBagConstraints gbc_btnSaveMap = new GridBagConstraints();
			gbc_btnSaveMap.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnSaveMap.gridx = 6;
			gbc_btnSaveMap.gridy = 29;
			contentPane.add(saveMapButton, gbc_btnSaveMap);
		}

		public void addActionsToAddCountryButton(ActionListener newAction) {
			addCountryButton.addActionListener(newAction);
		}

		public void addActionsToAddContinentButton(ActionListener newAction) {
			addContinentButton.addActionListener(newAction);
		}

		/**
		 * @see Edit_Create_Map_Controller
		 * @param newAction actionListener for btnComplete
		 */
		public void addActionsToBtnComplete(ActionListener newAction) {
			btnComplete.addActionListener(newAction);
		}

		/**
		 * @see Edit_Create_Map_Controller
		 * @param newAction actionListener for addButton
		 */
		public void addActionsToAddButton(ActionListener newAction) {
			addButton.addActionListener(newAction);
		}

		/**
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
		 * @see Edit_Create_Map_Controller
		 * @param newAction actionListener for deleteContinentButton
		 */
		public void addActionsToDeleteContinentButton(ActionListener newAction) {
			deleteContinentButton.addActionListener(newAction);
		}

		/**
		 * @see Edit_Create_Map_Controller
		 * @param newAction actionListener for saveMapButton
		 */
		public void addActionsToSaveMapButton(ActionListener newAction) {
			saveMapButton.addActionListener(newAction);
		}

		/**
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
			if(comboBox.getItemCount()!=0) {
				ContinentPresent = true;
			}
			return ContinentPresent;
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
		 * enabling the list
		 */
		public void JListEnabled() {
			list.setEnabled(true);
		}

		/**
		 * disabling the country field
		 */
		public void countryFieldDisable() {
			countryNameText.setText("");
			countryNameText.setEnabled(false);
		}

		/**
		 * disabling the continent field
		 */
		public void continentFieldDisabled() {
			continentNameText.setText("");
			continentControlValueText.setText("");
			continentNameText.setEnabled(false);
			continentControlValueText.setEnabled(false);
		}

		/**
		 * get the selected countries for corresponding neighbours
		 * @return list of countries selected through comboBox2 that includes selecting the countries
		 */
		public String getSelectedCountriesForNeighbours() {
			return (comboBox1.getSelectedItem().toString());
		}

		/**
		 * get countries for deletion in map modifier
		 * @return list of countries selected for deletion 
		 */
		public String getCountriesToRemove() {
			return (comboBox2.getSelectedItem().toString());
		}

		/**
		 * getting continents for deletion
		 * @return list of continents to be deleted
		 */
		public String continentForDeletion() {
			return (comboBox3.getSelectedItem().toString());
		}

		/**
		 * getting selected continent
		 * @return list of continents selected
		 */
		public String getSelectedContinent() {
			return (comboBox.getSelectedItem().toString());
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
			JOptionPane.showMessageDialog(contentPane, "Values are expected to enter first", "Error", JOptionPane.ERROR_MESSAGE);
		}

		/**
		 * dialogue box for error messages
		 */
		public void noNeighboursSelecteError() {
			JOptionPane.showMessageDialog(contentPane, "Values are expected to select neighbours first", "Error", JOptionPane.ERROR_MESSAGE);
		}

		/**
		 * dialogue box for error messages
		 */
		public void errorcontinentAlreadyExist() {
			JOptionPane.showMessageDialog(contentPane, "Continent already exist", "Error", JOptionPane.ERROR_MESSAGE);
		}

		/**
		 * dialogue box for error messages
		 */
		public void countryAlreadyExistError() {
			JOptionPane.showMessageDialog(contentPane, "Country already exist", "Error", JOptionPane.ERROR_MESSAGE);
		}

		/**
		 * dialogue box for error messages
		 */
		public void nullContinentError() {
			JOptionPane.showMessageDialog(contentPane, "Add continent first", "Error", JOptionPane.ERROR_MESSAGE);
		}

		/**
		 * dialogue box for error messages
		 */
		public void nullCountryError() {
			JOptionPane.showMessageDialog(contentPane, "Map validation error", "Error", JOptionPane.ERROR_MESSAGE);
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
		public void eraseContinentFields() {
			continentNameText.setText("");
			continentControlValueText.setText("");
			continentNameText.setEnabled(false);
			continentControlValueText.setEnabled(false);
		}

		public void countryfieldEnable() {
			countryNameText.setEnabled(true);
		}

		public void enableContinentFields() {
			continentNameText.setEnabled(true);
			continentControlValueText.setEnabled(true);
		}
		/**
		 * clearing the lists from comboBox
		 */
		public void clearComboBoxContents() {
			comboBox.removeAllItems();
			comboBox3.removeAllItems();
		}

		/**
		 * clearing list of countries
		 */
		public void clearCountryComBoxContents() {
			comboBox2.removeAllItems();
			comboBox1.removeAllItems();
		}

		/**
		 * setting the continents in the list
		 * @param continent name of continent
		 */
		public void setContinentsComboBox(String continent) {
			comboBox.addItem(continent);
			comboBox3.addItem(continent);
		}

		/**
		 * adding country to the list
		 * @param country name of country
		 */
		public void setCountriesComboBox(String country) {
			comboBox1.addItem(country);
			comboBox2.addItem(country);
		}
	
}
