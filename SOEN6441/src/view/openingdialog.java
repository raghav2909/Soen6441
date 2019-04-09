package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import controllers.*;


/**
 * This class is from where the game starts
 * 1. asks player to play game or edit map
 * 2. displays frame for those options
 * @author Harman
 *@version 2.0
 */
public class openingdialog {
	/**
	 * JFrame for dialog boxes.
	 */
	private JFrame frame;
	
	/**
	 * Button to begin the game.
	 */
	private JButton playTheGame;
	/**
	 * Button to edit map.
	 */
	private JButton editMap;
		
	/**
	 * Stores the path of the map file uploaded.
	 */
	private String readMap = null;
	
	public  String modeOfGame;
	
	public void selectSaveOrLoadGame(){
		JFrame frame1 = new JFrame();
		frame1.setLayout(new BoxLayout(frame1.getContentPane(),BoxLayout.Y_AXIS));
		JButton loadTheGame = new JButton("Load-Game");
		frame1.add(loadTheGame);
		JButton newGameButton = new JButton("New-Game");
		
		frame1.add(newGameButton);
		
		frame1.pack();
		frame1.setVisible(true);
		
		newGameButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				TheMainController.getInit().singleGameInit();
				
			}
		});
		
		loadTheGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				JFrame saveFileLoad = new JFrame("Saved File Chooser!");
				saveFileLoad.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				saveFileLoad.validate();
				saveFileLoad.setVisible(true);
				/*JFileChooser to ask user to choose a map file.*/
				JFileChooser jfc = new JFileChooser();
				jfc.setCurrentDirectory(new File("./"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter(null, "sav");
				jfc.setFileFilter(filter);

				int returnTheValue = jfc.showOpenDialog(frame);
				String saveFileRead = null;
				/*Get the path of the map file chosen*/
				if (returnTheValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					saveFileRead = selectedFile.getAbsolutePath();
					saveFileLoad.dispose();
				}
		TheMainController .getInit().singleGameLoadInit(saveFileRead);	
			}
			
		});
	}
	
	
	
	/**
	 * show message to user to enter the name of player one-by-one.
	 * @return string returns an array with number of players.
	 */
	public String[][] getInfoOnPlayerData() {
		int n = getInput(2,6,"Enter number of Players");
		String[][] namesOfPlayer = new String[n][2];
		String[] behaviorsForGame = {"aggressive", "benevolent", "cheater", "human", "random"};
		JPanel panel = new JPanel();
		JTextField field = new JTextField(10);
		JComboBox<String> options = new JComboBox<String>(behaviorsForGame);
		panel.add(new JLabel("Name: "));
		panel.add(field);
		panel.add(new JLabel("Type: "));
		panel.add(options);
		for(int i=0;i< n;){
			field.setText("");
			options.setSelectedIndex(0);
			int s = JOptionPane.showConfirmDialog(
					frame,
					panel,
                    "Enter name of player "+ (i+1),
                    JOptionPane.OK_CANCEL_OPTION);

			if (s == JOptionPane.OK_OPTION) {
				namesOfPlayer[i][0] = field.getText();
				namesOfPlayer[i][1] = (String) options.getSelectedItem();
				i++;
			}
		}
		return namesOfPlayer;
	}
	
	/**
	 * Places armies on the selected countries.
	 * @param countryList List of countries where the player can place its armies.
	 * @param message Message  displayed
	 * @return country name of country selected.
	 */
	public String dialogToPlaceTheArmy(String[] countryList, String message) {
		String[] options = {"OK"};		
		JOptionPane.showOptionDialog(null, new JComboBox<String>(countryList), message, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,		
		options, options[0]);
		String country = countryList[new JComboBox<String>(countryList).getSelectedIndex()];
		return country;
	}
	/**
	 * Ask user to enter an integer value for spinner.
	 * @param minimum Minimum value for the spinner.
	 * @param maximum Maximum value for the  Spinner.
	 * @param message The message to be displayed on the spinner.
	 * @return number of players entered by user or by default 2.
	 */
	public int getInput(int minimum, int maximum, String message){
		 JPanel Jbox = new JPanel();
		 SpinnerModel sm = new SpinnerNumberModel(minimum, minimum, maximum, 1); 
		 JSpinner inputSpinner = new JSpinner(sm);
         Jbox.add(new JLabel("Input"));
         Jbox.add(inputSpinner);
         
         int result = JOptionPane.showConfirmDialog(null, Jbox, message, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
         if (result == JOptionPane.OK_OPTION) {
             return (int) inputSpinner.getValue();
         }
		return minimum;
	}
	
	/**
	 * make user select the the map file to be used for the game.
	 * @param newExtension extension of the file to be picked.
	 * @return readMap Stores the absolute path of the map file read.
	 */
	public String getMapData(String newExtension) {
		JFrame frame = new JFrame("Map-File-Chooser!");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.validate();
		frame.setVisible(true);
		/*JFile-Chooser to let user to choose existing map-file.*/
		JFileChooser jfc = new JFileChooser();
		jfc.setCurrentDirectory(new File("./SOEN6441/Map_Data/Map"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Map Files", newExtension);
		jfc.setFileFilter(filter);

		int returnTheValue = jfc.showOpenDialog(frame);
		/* path of the map-file selected*/
		if (returnTheValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			readMap = selectedFile.getAbsolutePath();
			frame.dispose();
			if(readMap.substring(readMap.lastIndexOf("."),readMap.length()).equalsIgnoreCase("."+newExtension)){
				return readMap;
			}
		}
		if(newExtension.equals("map")) {
			return getMapData(newExtension);
		}
		return null;
	}
	
	/**
	 * Displays the JavaFrame to choose from Map-Edit and Play-the-Game options on initiating game.
	 */
	public void selectMapEditorOrPlayGame() {
		frame = new JFrame("Choose-any-one!");
		frame.setSize(new Dimension(200,200));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editMap = new JButton("Edit-Map");
		frame.add(editMap);
		playTheGame = new JButton("Play-Game");
		frame.add(playTheGame);
		frame.setLayout(new FlowLayout());
		
		
		frame.validate();
		frame.setVisible(true);
	}
	
	/**
	 * Returns the J-frame .
	 * @return JFrame
	 */
	public JFrame chooseOptionFrame() {
		return this.frame;
	}
	
	/**
	 * add action-Listener to map-edit Button.
	 * @param newAction return actionlistener for map-edit button.
	 */
	public void actionForMapEditButton(ActionListener newAction) {
		this.editMap.addActionListener(newAction);
	}
	
	/**
	 * add action-listener to Play-Game Button.
	 * @param newAction ActionListener for Play Game button
	 */
	public void actionToPlayGameButton(ActionListener newAction) {
		this.playTheGame.addActionListener(newAction);
	}
	
	/**
	 * to display a message-dialog box having two buttons to user to select game-mode: single or tournamenbt.
	 * @return returns the string object containing game mode.
	 */
	public String modeOfGame() {
		JFrame frame = new JFrame("Map File Chooser!");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.validate();
		frame.setVisible(true);
		Object[] options = {"Single Mode", "Tournament Mode"};
		int n = JOptionPane.showOptionDialog(frame,	"Please select a mode:", "Game Mode",	
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		frame.dispose();
		if(n==0) {
			modeOfGame = "single";
			return "single";
			
		}
		else if(n==1) {
			modeOfGame = "tournament";
			return "tournament";
		}
		return "noMode";
	}

	public String[] getPlayerBehavior(String[] playerInfo) {
		String[] behaviorsForGame = new String[playerInfo.length];
		for(int i=0;i<playerInfo.length;i++) {
			behaviorsForGame[i] = "human";
		}
		return behaviorsForGame;
	}
	
	
	public String setMode() {
		String modes=modeOfGame; 
		return modes;
	}
}

