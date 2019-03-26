package view;

import java.awt.Color;
import java.awt.Container;
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
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import controllers.*;
import game.messages.MessageWindow;

/**
 * This class is from where the game starts
 * 1. asks player to play game or edit map
 * 2. displays frame for those options
 * @author Harman
 *@version 1.0
 */
public class openingdialog {
	 private  MessageWindow messageWindow = new MessageWindow();
 
	 /**
	  * frame declared
	  */
	 private JFrame first_frame;
	 
	 /**
	  * Button for new game
	  */
	private JButton newgame;
	
	/**
	 * Button for load game
	 */
	private JButton loadgame;
	
	/**
	 * button to edit map
	 */
	private JButton mapedit;
	
	/**
	 * object of edit create map controller class
	 */
	private Edit_create_Map_Controller ecm;
	
	/**
	 * declared a null string
	 */
	private String mapRead1= null;

	/**
	 * asks player to go for 'single' or 'tournament' mode 
	 */
	public void start()
	{
		JRadioButton j1, j2;
		j1=new JRadioButton("Single");
		j1.setBackground(Color.cyan);
		j2=new JRadioButton("tournament");
		j2.setBackground(Color.cyan);
		JButton b = new JButton("OK");
		b.setBackground(Color.cyan);
		ButtonGroup bg=new ButtonGroup();
		bg.add(j1); bg.add(j2);
		JLabel l=new JLabel("SELECT MODE TO PLAY");
		first_frame=new JFrame("RISK GAME- Team 35");
		
		
		first_frame.setSize(200, 200);
		first_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		first_frame.setLayout(new FlowLayout());
		first_frame.add(l);
		first_frame.add(j1);
		first_frame.add(j2);
		first_frame.add(b);
		Container c = first_frame.getContentPane(); 
		c.setBackground(Color.orange);
		first_frame.setVisible(true);
		
		/**
		 * listener for radiobutton
		 */
			b.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					first_frame.dispose();
					if(j1.isSelected())
					{
					chooseplayoredit();
					}
					else
					{
						tournament_mode();
					}
				}
			});
		}
	
	/**
	 * This method shows the frame to select the option 'play game' or 'edit map'
	 */
	public void chooseplayoredit()
	{
		first_frame = new JFrame("Select an option");
		newgame = new JButton("New Game");
		newgame.setBackground(Color.CYAN);
		loadgame = new JButton("Load Game");
		loadgame.setBackground(Color.CYAN);
		mapedit = new JButton("Edit Map");
		mapedit.setBackground(Color.CYAN);
		first_frame.setSize(150, 150);
		first_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		first_frame.setLayout(new FlowLayout());
		first_frame.add(newgame);
		first_frame.add(loadgame);
		first_frame.add(mapedit);
		Container c = first_frame.getContentPane();
		c.setBackground(Color.black);
		first_frame.setVisible(true);
		
		
		/**
		 * Map edit button action listener 
		 */
		mapedit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				first_frame.dispose();
				ecm = new Edit_create_Map_Controller();
				 ecm.tobegin();
				
		
		}
	
	});
		/**
		 * new game listener
		 */
		newgame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				first_frame.dispose();
				try {
					the_main_controller.getInstance().Single_Mode_Start();
					
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		
		/**
		 * load game listener
		 */
		loadgame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				first_frame.dispose();
				the_main_controller.getInstance().single_Mode_Saved_Start();
				
				
			}
		});

	}
	
	/**
	 * returns the initial frame 
	 */
	public JFrame returnframe()
	{
		return this.first_frame;
	}
	
	
	public void tournament_mode() {
		
		new TournamentModeMenu(messageWindow);
		
	}
public static String[] name_Of_Player; 
	
	/**
	 * This function will give the information regarding the player.
	 * In this function Spinner  model is used to define the minimum and maximum number of players
	 * when the user starts the game play. 
	 * @return This method will return the Number of players.
	 */
	public int NumberOfPlayer(int minimum,String Mesg,int maximum)
	{
		
		JPanel jp = new JPanel();
       SpinnerModel S_M = new SpinnerNumberModel(minimum, minimum, maximum, 1);
       JSpinner jsm= new JSpinner(S_M);
       jp.add(new JLabel("Enter Number Of Players"));
       jp.add(jsm);
       
       int result = JOptionPane.showConfirmDialog(null, jp, "Enter Number Of Players", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
       if (result == JOptionPane.OK_OPTION) {
    	   return (int) jsm.getValue();
    	  
    	   
       }
       return minimum;
	
	}
	
	/**
	 * This function will give the information regarding the players
	 * @return It will return the names of players as string array
	 */
	public String[] Information_OF_Playres()
	{
		System.out.println("infoofplayer");
		int n = NumberOfPlayer(2,"Player Numbers",6);
		System.out.println("Number of players" + n);
		
		 name_Of_Player= new String[n];
		 JFrame jf = new JFrame("Player Names");
		 
		jf.setBackground(Color.BLUE);
		 
		for (int i=1;i<=n;i++)
		 {
			 String name= (String)JOptionPane.showInputDialog(
						jf,
	                    "Enter name "+ i,
	                    "Player Information",
	                    JOptionPane.PLAIN_MESSAGE);
			 if((name!= null )&& (name.length()>0))
			 {
				 name_Of_Player[i-1]=name; 
			 }
			
			 System.out.println(name);
		 }
		return name_Of_Player;
	}
	public String InfoOfMap(String newExtension) {
		JFrame frame = new JFrame("Map File Chooser");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.validate();
		frame.setVisible(true);
		/*JFileChooser to ask user to choose a map file.*/
		JFileChooser jfc = new JFileChooser();
		jfc.setCurrentDirectory(new File("./SOEN6441/Map_Data/map"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Map Files", newExtension);
		jfc.setFileFilter(filter);

		int returnValue = jfc.showOpenDialog(frame);
		/*Get the path of the map file chosen*/
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			mapRead1 = selectedFile.getAbsolutePath();
			frame.dispose();
			if(mapRead1.substring(mapRead1.lastIndexOf("."),mapRead1.length()).equalsIgnoreCase("."+newExtension)){
				return mapRead1;
			}
		}
		if(newExtension.equals("map")) {
			return InfoOfMap(newExtension);
		}
		return null;
	}
	/**
	 *The countries that are selected to place army
	 * @param CL List of countries where the player can place armies.
	 * @return country name selected.
	 */
	public String ArmyPlacing(String[] CL, String Msg) {
		JComboBox<String> List_Of_countries = new JComboBox<String>(CL);
		String[] opt= {"Ok"};
		JOptionPane.showOptionDialog(null, CL, Msg, JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null,		
				opt, opt[0]);
		String country = CL[List_Of_countries.getSelectedIndex()];
		return country;
	}
	

}


