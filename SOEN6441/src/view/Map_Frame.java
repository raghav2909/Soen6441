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
 * The class Map_Frame  opens  JFrame view for selecting 
 * either a New map or an Existing map.
 * @author raghavsharda
 * @author gursharandeep
 */
public class Map_Frame extends JFrame {

	/**
	 * Stores all JPanel for the mapFrame.
	 */
	private JPanel contentPanel;
	
	/**
	 * Stores the object of Edit_create_Map_Controller.
	 */
	Edit_create_Map_Controller objct = new Edit_create_Map_Controller();
	
	/**
	 * Map_Frame constructor invoking start method of the class.
	 */
	public Map_Frame() {
		start();
	}
	/**
	 * Stores the information for new-map button.
	 */
	
	JButton NewMap;
	/**
	 * Stores the  information for existing-map button.
	 */
	JButton ExistingMap;
		
	/**
	 * Stores the action that is selected either for creation of new map or editing an existing.
	 */
	static String ActionChoosen ="";

	/**
	 * Initialize the contents of the frame.
	 * includes button: NewMap and ExistingMap
	 * along with 3 other labels for text.
	 */
	public void start() {
		Map_Frame frame = this;
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//this.setBackground(Color.DARK_GRAY);
		//this.setSize(200, 200);

		setBounds(80, 80, 460, 240);
		contentPanel = new JPanel();
		contentPanel.setBackground(new Color(0, 0, 255));
		//contentPanel.setSize(800, 800);
		contentPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPanel);
		GridBagLayout Global_Pane = new GridBagLayout();
		Global_Pane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		Global_Pane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		Global_Pane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		Global_Pane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(Global_Pane);

		GridBagConstraints Global_Pane_contents = new GridBagConstraints();
		Global_Pane_contents.insets = new Insets(0, 0, 5, 0);
		Global_Pane_contents.gridwidth = 7;
		Global_Pane_contents.gridheight = 3;
		Global_Pane_contents.gridx = 0;
		Global_Pane_contents.gridy = 1;
		
		
		JLabel Map_Editor = new JLabel("Map Editor");
		Map_Editor.setFont(new Font("Script Bold", Font.PLAIN,49));
		Map_Editor.setForeground(Color.WHITE);	
		Map_Editor.setBackground(Color.BLACK);
		contentPanel.add(Map_Editor, Global_Pane_contents);

		/*GridBagConstraints global_Choose_Label = new GridBagConstraints();
		global_Choose_Label.gridheight = 5;
        global_Choose_Label.gridwidth = 7;
        global_Choose_Label.insets = new Insets(0, 0, 2, 2);
        global_Choose_Label.gridx = 1;
		global_Choose_Label.gridy = 1;
		JLabel Choose_Label = new JLabel("Select:  	Edit an existing map or "
				+ "create New map for  Game.");
		Choose_Label.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC|Font.PLAIN, 25));
		Choose_Label.setForeground(Color.WHITE);	
		contentPanel.add(Choose_Label, global_Choose_Label);*/

		NewMap = new JButton("Create New Map");
		NewMap.setToolTipText("Click here for creating new map");
		NewMap.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC , 16));
		NewMap.setForeground(Color.BLACK);
		NewMap.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				ActionChoosen = "new";
				frame.setVisible(true);
				System.out.println("Make New Map ..here");
				objct.mapNewActions();
				
				//define this method in Edit_create_Map_Controller
			}
		});

		NewMap.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_NewMap = new GridBagConstraints();
		gbc_NewMap.gridwidth = 2;
		gbc_NewMap.gridheight=2;
		gbc_NewMap.fill = GridBagConstraints.HORIZONTAL;
		gbc_NewMap.insets = new Insets(0, 0, 0, 2);
		gbc_NewMap.gridx = 4;
		gbc_NewMap.gridy = 5;
		contentPanel.add(NewMap, gbc_NewMap);
		//JLabel chooseOne = new JLabel("Select one of the following...");
		//chooseOne.setFont(new Font("Tahoma", Font.ITALIC, 25));
		//chooseOne.setForeground(Color.WHITE);	
		//GridBagConstraints gbc_chooseOne = new GridBagConstraints();
		//gbc_chooseOne.fill = GridBagConstraints.VERTICAL;
		//gbc_chooseOne.gridwidth = 3;
		//gbc_chooseOne.insets = new Insets(0, 0, 5, 0);
		//gbc_chooseOne.gridx = 4;
		//gbc_chooseOne.gridy = 4;
		//contentPanel.add(chooseOne, gbc_chooseOne);
		
		GridBagConstraints gbc_ExistingMap = new GridBagConstraints();
		gbc_ExistingMap.fill = GridBagConstraints.HORIZONTAL;
		gbc_ExistingMap.gridwidth = 2	;
		//gbc_NewMap.gridheight=4;
		gbc_ExistingMap.insets = new Insets(0, 0, 2, 2);
		gbc_ExistingMap.gridx = 4;
		gbc_ExistingMap.gridy = 4;
		ExistingMap = new JButton("Edit Existing Map");
		ExistingMap.setForeground(Color.BLACK);
		ExistingMap.setToolTipText("Click here for using existing map");
		ExistingMap.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 16));
		ExistingMap.setBackground(new Color(240, 255, 255));
		
		contentPanel.add(ExistingMap, gbc_ExistingMap);
		ExistingMap.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				ActionChoosen = "existing";
				System.out.println("Edit Existing Map ..here");
			objct.MapSelectionActions();
			}
		});
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	}

	public static String ActionChoosen() {
		return ActionChoosen;
	}
}

