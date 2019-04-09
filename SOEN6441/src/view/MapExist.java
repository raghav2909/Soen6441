/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.NodeOfMap;



/**This class opens jFrame to open map file 
 * @author raghavsharda
 * @author gursharandeep
 * @version 2.0
 *
 */
public class MapExist extends JFrame {

	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = 257217734140252917L;

	/**
	 * It Creates J-panel to display existing-map file .
	 */
	private JPanel contentPane;
	
	/**
	 * Button to select option to edit the existing map file.
	 */
	private JButton EditButton;

	/**
	 * ArrayList of NodeOfMap type that receives all  information of existing-map-file choosen
	 */
	ArrayList<NodeOfMap> mapExisting = new ArrayList<NodeOfMap>();
	
	public MapExist() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Existing Map constructor calls initialize method of the class.
	 * @param map arrayList of map nodes.
	 */
	public MapExist(ArrayList<NodeOfMap> map){
		mapExisting = map;
		initialise();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialise() {
		this.setLocationRelativeTo(null);
		this.setTitle("Existing-Map");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(140, 140, 500, 340);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 

		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setContentPane(contentPane);
		
		contentPane.setLayout(gbl_contentPane);

		String[] column1 = {"Continents", "Control Value"};
		DefaultTableModel model1 = new DefaultTableModel(column1, 0);

		for (int i = 0; i < mapExisting.size(); i++) {
			String[] contName = {mapExisting.get(i).getNameOfContinent(), Integer.toString(mapExisting.get(i).getValue())};
			model1.addRow(contName);
		}

		JTable table1 = new JTable(model1);
		table1.setToolTipText("Map file is to displayed here");
		GridBagConstraints gbc_table1 = new GridBagConstraints();
		gbc_table1.gridwidth = 2;
		gbc_table1.gridheight = 2;
		gbc_table1.insets = new Insets(0, 0, 5, 0);
		gbc_table1.fill = GridBagConstraints.BOTH;
		gbc_table1.gridx = 0;
		gbc_table1.gridy = 0;
		contentPane.add(getContentPane().add(new JScrollPane(table1)), gbc_table1);

		String[] column2 = {"Countries"};
		DefaultTableModel model2 = new DefaultTableModel(column2, 0);
		JTable table2 = new JTable(model2);
		table2.setToolTipText("Map file displayed here");
		GridBagConstraints gbc_table2 = new GridBagConstraints();
		gbc_table2.gridwidth = 2;
		gbc_table2.gridheight = 2;
		gbc_table2.insets = new Insets(0, 0, 5, 0);
		gbc_table2.fill = GridBagConstraints.BOTH;
		gbc_table2.gridx = 2;
		gbc_table2.gridy = 0;
		contentPane.add(getContentPane().add(new JScrollPane(table2)), gbc_table2);

		String[] column3 = {"N Countries"};
		DefaultTableModel model3 = new DefaultTableModel(column3, 0);
		JTable table3 = new JTable(model3);
		table3.setToolTipText("Map file displayed here");
		GridBagConstraints gbc_table3 = new GridBagConstraints();
		gbc_table3.gridwidth = 2;
		gbc_table3.gridheight = 2;
		gbc_table3.insets = new Insets(0, 0, 5, 0);
		gbc_table3.fill = GridBagConstraints.BOTH;
		gbc_table3.gridx = 4;
		gbc_table3.gridy = 0;
		contentPane.add(getContentPane().add(new JScrollPane(table3)), gbc_table3);

		table1.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				model2.setRowCount(0);
				model3.setRowCount(0);
				String selectedCell = table1.getValueAt(table1.getSelectedRow(), table1.getSelectedColumn()).toString();
				for (int i = 0; i < mapExisting.size(); i++) {
					if (selectedCell.compareTo(mapExisting.get(i).getNameOfContinent())==0) {
						for (int j = 0; j < mapExisting.get(i).getCountries().length; j++) {
							String[] countryName = {mapExisting.get(i).getCountries()[j].getNameOfCountry()};
							model2.addRow(countryName);
						}
					}
				}
			}
		});

		table2.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				model3.setRowCount(0);
				String selectedCell1 = table1.getValueAt(table1.getSelectedRow(), table1.getSelectedColumn()).toString();
				String selectedCell2 = table2.getValueAt(table2.getSelectedRow(), table2.getSelectedColumn()).toString();
				for (int i = 0; i < mapExisting.size(); i++) {
					if (selectedCell1.compareTo(mapExisting.get(i).getNameOfContinent())==0) {
						for (int j = 0; j < mapExisting.get(i).getCountries().length; j++) {
							if (selectedCell2.compareTo(mapExisting.get(i).getCountries()[j].getNameOfCountry())==0) {
								for (int k = 0; k < mapExisting.get(i).getCountries()[j].getNeighbourCountries().length; k++) {
									String[] countryInfo = {mapExisting.get(i).getCountries()[j].getNeighbourCountries()[k].getNameOfCountry()};
									model3.addRow(countryInfo);
								}
								break;
							}
						}
						break;
					}
				}
			}
		});

		EditButton = new JButton("Edit & Save Map");
		EditButton.setBackground(new Color(152, 251, 152));
		EditButton.setFont(new Font("Script MT Bold", Font.BOLD, 18));
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEdit.gridwidth = 3;
		gbc_btnEdit.insets = new Insets(0, 0, 5, 0);
		gbc_btnEdit.gridx = 13;
		gbc_btnEdit.gridy = 2;
		contentPane.add(EditButton, gbc_btnEdit);
	}
	
	/**
	 * to display error message-dialog-box for not able to load map
	 */
	public void mapCannotLoadedError() {
		JOptionPane.showMessageDialog(contentPane, "Map-file validation-Error!", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 *  to display error message-dialog-box for unConneected continents.
	 */
	public void showUnconnectedContinentError() {
		JOptionPane.showMessageDialog(contentPane, "Cannot load Map-file having unconnected continents", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * displays sucess dialog box with success-message on load of map
	 */
	public void mapSuccessfullyLoadedMsg() {
		JOptionPane.showMessageDialog(contentPane, "Map file is validated => now Click ok to load the map-data", "Message", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * getting all the information from existing map
	 * @return existing-map information
	 */
	public ArrayList<NodeOfMap> getInfoForExistingMap() {
		return mapExisting;
	}
	/**
	 * @param actionNew actionListener for EditButton
	 */
	public void addActionsToEditButton(ActionListener actionNew) {
		EditButton.addActionListener(actionNew);
	}

	
}
