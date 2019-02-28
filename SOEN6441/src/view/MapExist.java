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
//import model.MapNode;
//import view.mapeditor.mapEditorController;

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
		JTable Table_2 = new JTable(m2);
		Table_2.setToolTipText("Map file displayed here");
		GridBagConstraints gbc_Table_2 = new GridBagConstraints();
		gbc_Table_2.gridwidth = 3;
		gbc_Table_2.gridheight = 3;
		gbc_Table_2.insets = new Insets(1, 1, 6,1);
		gbc_Table_2.fill = GridBagConstraints.BOTH;
		gbc_Table_2.gridx = 2;
		gbc_Table_2.gridy = 0;
		jp.add(getContentPane().add(new JScrollPane(Table_2)), gbc_Table_2);

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
				
					int i =0;
					while( i<MapExists.size())
					{
						if (selectedCell.compareTo(MapExists.get(i).getContinent())==0) {
							for (int j = 0; j < MapExists.get(i).getCountries().length; j++) {
								String[] countryName = {MapExists.get(i).getCountries()[j].getCountryName()};
								m2.addRow(countryName);
							}
						}
					i++;
				}
			}
		});

		/**
		 * mouse action listener for selecting row and column
		 */
		Table_2.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				m3.setRowCount(0);
				String selectedCell1 = Table_1.getValueAt(Table_1.getSelectedRow(), Table_1.getSelectedColumn()).toString();
				String selectedCell2 = Table_2.getValueAt(Table_2.getSelectedRow(), Table_2.getSelectedColumn()).toString();
				int i=0;
					while(i<MapExists.size())
					{
						if (selectedCell1.compareTo(MapExists.get(i).getContinent())==0) {
							for (int j = 0; j < MapExists.get(i).getCountries().length; j++) {
								if (selectedCell2.compareTo(MapExists.get(i).getCountries()[j].getCountryName())==0) {
									for (int k = 0; k < MapExists.get(i).getCountries()[j].getNeighboursCountries().length; k++) {
										String[] countryInfo = {MapExists.get(i).getCountries()[j].getNeighboursCountries()[k].getCountryName()};
										m3.addRow(countryInfo);
									}
									break;
								}
							}
							break;
						}
					i++;
				}
			}
		});

		bEdit = new JButton("Edit Map");
		bEdit.setBackground(new Color(152, 251, 152));
		bEdit.setFont(new Font("Script MT Bold", Font.BOLD, 18));
		GridBagConstraints gbc_bEdit = new GridBagConstraints();
		gbc_bEdit.fill = GridBagConstraints.HORIZONTAL;
		gbc_bEdit.gridwidth = 3;
		gbc_bEdit.insets = new Insets(0, 0, 5, 0);
		gbc_bEdit.gridx = 13;
		gbc_bEdit.gridy = 2;
		jp.add(bEdit, gbc_bEdit);
	}
	
	/**
	 * @see mapEditorController
	 * @param newAction actionListener for btnEdit
	 */
	public void addActionsToBtnEdit(ActionListener newAction) {
		bEdit.addActionListener(newAction);
	}
	
	/**
	 * getting all the information from existing map
	 * @return existing map info
	 */
	public ArrayList<NodeOfMap> getMapExistsInfo() {
		return MapExists;
	}




				
	}


