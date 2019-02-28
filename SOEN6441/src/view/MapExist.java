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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.NodeOfMap;
import model.MapNode;
import view.mapeditor.mapEditorController;

/**This class opens jframe to open map file 
 * @author raghavsharda
 * @author gursharandeep
 * @version 1.0
 *
 */
public class MapExist extends JFrame{
	JPanel jp;
	
	ArrayList<NodeOfMap> MapExists = new ArrayList<NodeOfMap>();
	
    JButton bEdit;

	private Object gbc_Table1;

	private Object gbc_table3;
	
	/**
	 * Start method of the class is called by MapExist constructor
	 * @param map arraylist of map nodes.
	 */
 public MapExist(ArrayList<NodeOfMap> maps){
		MapExists = maps;
		start();
	}
/**
 * This method will help to initialize the contents of the frame
 */
	private void start() {
		// TODO Auto-generated method stub
		this.setTitle("Existing Map");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(150, 150, 500, 340);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		jp = new JPanel();
		jp.setBackground(Color.LIGHT_GRAY);
		setContentPane(jp);
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		jp.setLayout(gbl);
		String[]c1 = {"Continents","value"};
		DefaultTableModel m1 = new DefaultTableModel(c1,0);

		for (int i = 0; i < MapExists.size(); i++) {
			String[] contName = {MapExists.get(i).getContinent(), Integer.toString(MapExists.get(i).getControlValue())};
			m1.addRow(contName);
		}
		
		JTable Table_1= new JTable(m1);
		Table_1.setToolTipText("Map file");
		GridBagConstraints gbc_Table_1 = new GridBagConstraints();
		gbc_Table_1.gridheight = 3;
		gbc_Table_1.insets = new Insets(1, 1, 6, 1);
		gbc_Table_1.gridwidth = 3;
		gbc_Table_1.fill = GridBagConstraints.BOTH;
		gbc_Table_1.gridx = 0;
		gbc_Table_1.gridy = 0;
		jp.add(getContentPane().add(new JScrollPane(Table_1)), gbc_Table1);

		String[] c2 = {"Countries"};
		DefaultTableModel m2 = new DefaultTableModel(c2, 0);
		JTable table2 = new JTable(m2);
		table2.setToolTipText("Map file displayed here");
		GridBagConstraints gbc_Table_2 = new GridBagConstraints();
		gbc_Table_2.gridwidth = 3;
		gbc_Table_2.gridheight = 3;
		gbc_Table_2.insets = new Insets(1, 1, 6,1);
		gbc_Table_2.fill = GridBagConstraints.BOTH;
		gbc_Table_2.gridx = 2;
		gbc_Table_2.gridy = 0;
		jp.add(getContentPane().add(new JScrollPane(table2)), gbc_Table_2);

		String[] c3 = {"Neighbour Countries"};
		DefaultTableModel m3 = new DefaultTableModel(c3, 0);
		JTable Table_3 = new JTable(m3);
		Table_3.setToolTipText("Map file displayed here");
		GridBagConstraints gbc_Table_3 = new GridBagConstraints();
		gbc_Table_3.gridwidth = 3;
		gbc_Table_3.gridheight = 3;
		gbc_Table_3.insets = new Insets(1, 1, 6, 1);
		gbc_Table_3.fill = GridBagConstraints.BOTH;
		gbc_Table_3.gridx = 4;
		gbc_Table_3.gridy = 0;
		jp.add(getContentPane().add(new JScrollPane(Table_3)), gbc_Table_3);

		/**
		 * mouse action listener for selecting row and column
		 */
		Table_1.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				m2.setRowCount(0);
				m3.setRowCount(0);
				String selectedCell = Table_1.getValueAt(Table_1.getSelectedRow(), Table_1.getSelectedColumn()).toString();
				//for (int i = 0; i < existingMap.size(); i++) {
					int i =0;
					while( i<MapExists.size())
					{
					if (selectedCell.compareTo(MapExists.get(i).getContinent())==0) {
						for (int j = 0; j < MapExists.get(i).getCountries().size(); j++) {
							String[] countryName = {MapExists.get(i).getCountries()[j].getCountryName()};
							model2.addRow(countryName);
						}
					}
					i++;
				}
			}
		});

		/**
		 * mouse action listener for selecting row and column
		 */
		table2.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				model3.setRowCount(0);
				String selectedCell1 = Table_1.getValueAt(Table_1.getSelectedRow(), Table_1.getSelectedColumn()).toString();
				String selectedCell2 = table2.getValueAt(table2.getSelectedRow(), table2.getSelectedColumn()).toString();
				for (int i = 0; i < existingMap.size(); i++) {
					if (selectedCell1.compareTo(existingMap.get(i).getContinentName())==0) {
						for (int j = 0; j < existingMap.get(i).getCountries().length; j++) {
							if (selectedCell2.compareTo(existingMap.get(i).getCountries()[j].getCountryName())==0) {
								for (int k = 0; k < existingMap.get(i).getCountries()[j].getNeighbourCountries().length; k++) {
									String[] countryInfo = {existingMap.get(i).getCountries()[j].getNeighbourCountries()[k].getCountryName()};
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

		btnEdit = new JButton("Edit Map");
		btnEdit.setBackground(new Color(152, 251, 152));
		btnEdit.setFont(new Font("Script MT Bold", Font.BOLD, 18));
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEdit.gridwidth = 3;
		gbc_btnEdit.insets = new Insets(0, 0, 5, 0);
		gbc_btnEdit.gridx = 13;
		gbc_btnEdit.gridy = 2;
		contentPane.add(btnEdit, gbc_btnEdit);
	}
	
	/**
	 * @see mapEditorController
	 * @param newAction actionListener for btnEdit
	 */
	public void addActionsToBtnEdit(ActionListener newAction) {
		btnEdit.addActionListener(newAction);
	}
	
	/**
	 * getting all the information from existing map
	 * @return existing map info
	 */
	public ArrayList<MapNode> getExistingMapInfo() {
		return existingMap;
	}
}



				
	}

}
