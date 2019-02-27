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
	private JPanel contentPane;
	
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
		setBounds(160, 160, 500, 240);
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

		GridBagConstraints Global_Pane_contents = new GridBagConstraints();
		Global_Pane_contents.insets = new Insets(0, 0, 5, 0);
		Global_Pane_contents.gridwidth = 11;
		Global_Pane_contents.gridheight = 4;
		Global_Pane_contents.gridx = 0;
		Global_Pane_contents.gridy = 0;
		JLabel Map_Editor = new JLabel("This is a Map Editor");
		Map_Editor.setFont(new Font("Script Bold", Font.PLAIN,50));
		
		contentPane.add(Map_Editor, Global_Pane_contents);

		GridBagConstraints global_Choose_Label = new GridBagConstraints();
		global_Choose_Label.gridheight = 4;
        global_Choose_Label.gridwidth = 11;
        global_Choose_Label.insets = new Insets(0, 0, 5, 5);
        global_Choose_Label.gridx = 2;
		global_Choose_Label.gridy = 2;
		JLabel Choose_Label = new JLabel("Choose: Either to play with an existing map or create a new map for your game.");
		Choose_Label.setFont(new Font("Monotype Corsiva", Font.PLAIN, 28));
		contentPane.add(Choose_Label, global_Choose_Label);

		NewMap = new JButton("New Map");
		NewMap.setToolTipText("Click here for creating new map");
		NewMap.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
		NewMap.setForeground(Color.BLACK);
		NewMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ActionChoosen = "new";
				frame.setVisible(true);
				objct.mapNewActions();//define this method in Edit_create_Map_Controller
			}
		});

		NewMap.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_NewMap = new GridBagConstraints();
		gbc_NewMap.gridwidth = 3;
		gbc_NewMap.fill = GridBagConstraints.HORIZONTAL;
		gbc_NewMap.insets = new Insets(0, 0, 5, 5);
		gbc_NewMap.gridx = 5;
		gbc_NewMap.gridy = 6;
		contentPane.add(NewMap, gbc_NewMap);
		JLabel chooseOne = new JLabel("Select one...");
		chooseOne.setFont(new Font("Tahoma", Font.ITALIC, 19));
		GridBagConstraints gbc_chooseOne = new GridBagConstraints();
		gbc_chooseOne.fill = GridBagConstraints.VERTICAL;
		gbc_chooseOne.gridwidth = 3;
		gbc_chooseOne.insets = new Insets(0, 0, 5, 0);
		gbc_chooseOne.gridx = 4;
		gbc_chooseOne.gridy = 4;
		contentPane.add(chooseOne, gbc_chooseOne);
		
		GridBagConstraints gbc_ExistingMap = new GridBagConstraints();
		gbc_ExistingMap.fill = GridBagConstraints.HORIZONTAL;
		gbc_ExistingMap.gridwidth = 3;
		gbc_ExistingMap.insets = new Insets(0, 0, 0, 5);
		gbc_ExistingMap.gridx = 5;
		gbc_ExistingMap.gridy = 7;
		ExistingMap = new JButton("Existing Map");
		ExistingMap.setForeground(Color.BLACK);
		ExistingMap.setToolTipText("Click here for using existing map");
		ExistingMap.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
		ExistingMap.setBackground(new Color(240, 255, 255));
		
		contentPane.add(ExistingMap, gbc_ExistingMap);
		ExistingMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ActionChoosen = "existing";
			//	obj.mapFileChooserActions();
			}
		});
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	}

	public static String ActionChoosen() {
		return ActionChoosen;
	}
}

