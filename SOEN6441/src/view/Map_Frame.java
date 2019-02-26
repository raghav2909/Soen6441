package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.MatteBorder;

import controllers.Edit_create_Map_Controller;


/**
 * MapFrame class opens the JFrame view for selecting 
 * New map and Existing map
 * @author raghavsharda
 * @author gursharandeep
 */
public class Map_Frame extends JFrame {

	/**
	 * Stores the JPanel of map frame.
	 */
	private JPanel contentPane;
	
	/**
	 * Stores the object of mapEditorController.
	 */
	Edit_create_Map_Controller objct = new Edit_create_Map_Controller();
	
	/**
	 * Stores the new map button information.
	 */
	
	JButton btnNewMap;
	/**
	 * Stores the existing map button information.
	 */
	JButton ExistingMap;
	
	
	
	/**
	 * Stores the selected Action of creating new map or editing existing.
	 */
	static String selectedAction ="";

	/**
	 * MapFrame constructor calls initialize method of the class.
	 */
	public Map_Frame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * includes New Map button and Existing Map button
	 * together with three text labels
	 */
	public void initialize() {
		Map_Frame frame = this;
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(160, 160, 400, 240);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		GridBagLayout Global_Pane = new GridBagLayout();
		Global_Pane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		Global_Pane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		Global_Pane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		Global_Pane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(Global_Pane);

		JLabel Map_Editor = new JLabel("Map Editor");
		Map_Editor.setFont(new Font("Script Bold", Font.PLAIN,50));
		GridBagConstraints Global_Pane_contents = new GridBagConstraints();
		Global_Pane_contents.insets = new Insets(0, 0, 5, 0);
		Global_Pane_contents.gridwidth = 12;
		Global_Pane_contents.gridheight = 4;
		Global_Pane_contents.gridx = 0;
		Global_Pane_contents.gridy = 0;
		contentPane.add(Map_Editor, Global_Pane_contents);

		JLabel Choose_Label = new JLabel("You can choose to play with an existing map or create your own map.");
		Choose_Label.setFont(new Font("Monotype Corsiva", Font.PLAIN, 29));
		GridBagConstraints global_Choose_Label = new GridBagConstraints();
		global_Choose_Label.gridheight = 3;
        global_Choose_Label.gridwidth = 7;
        global_Choose_Label.insets = new Insets(0, 0, 5, 5);
        global_Choose_Label.gridx = 2;
		global_Choose_Label.gridy = 2;
		contentPane.add(Choose_Label, global_Choose_Label);
//changes from here please
		btnNewMap = new JButton("New Map");
		btnNewMap.setToolTipText("Click here...");
		btnNewMap.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
		btnNewMap.setForeground(Color.BLACK);
		btnNewMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedAction = "new";
				frame.setVisible(true);
				//objct.newMapActions();
			}
		});

		JLabel lblSelectOne = new JLabel("Select one...");
		lblSelectOne.setFont(new Font("Tahoma", Font.ITALIC, 18));
		GridBagConstraints gbc_lblSelectOne = new GridBagConstraints();
		gbc_lblSelectOne.fill = GridBagConstraints.VERTICAL;
		gbc_lblSelectOne.gridwidth = 3;
		gbc_lblSelectOne.insets = new Insets(0, 0, 5, 0);
		gbc_lblSelectOne.gridx = 5;
		gbc_lblSelectOne.gridy = 5;
		contentPane.add(lblSelectOne, gbc_lblSelectOne);
		btnNewMap.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_btnNewMap = new GridBagConstraints();
		gbc_btnNewMap.gridwidth = 3;
		gbc_btnNewMap.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewMap.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewMap.gridx = 5;
		gbc_btnNewMap.gridy = 6;
		contentPane.add(btnNewMap, gbc_btnNewMap);

		ExistingMap = new JButton("Existing Map");
		ExistingMap.setForeground(Color.BLACK);
		ExistingMap.setToolTipText("Click here...");
		ExistingMap.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
		ExistingMap.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_ExistingMap = new GridBagConstraints();
		gbc_ExistingMap.fill = GridBagConstraints.HORIZONTAL;
		gbc_ExistingMap.gridwidth = 3;
		gbc_ExistingMap.insets = new Insets(0, 0, 0, 5);
		gbc_ExistingMap.gridx = 5;
		gbc_ExistingMap.gridy = 7;
		contentPane.add(ExistingMap, gbc_ExistingMap);
		ExistingMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedAction = "existing";
			//	obj.mapFileChooserActions();
			}
		});
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	}

	public static String selectedAction() {
		return selectedAction;
	}
}

