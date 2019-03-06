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
					// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
				first_frame.dispose();
				try {
					the_main_controller.getInstance().Single_Mode_Start();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
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
				// TODO Auto-generated method stub
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
	
//	/**
//	 * Ask user to enter player count.
//	 * @return number of players entered by user or by default 2.
//	 */
//	private int getCountOfPlayers(){
//		 JPanel p1 = new JPanel();
//		 SpinnerModel sm = new SpinnerNumberModel(2, 2, 6, 1); 
//		 JSpinner s1 = new JSpinner(sm);
//         p1.add(new JLabel("Number of players: "));
//         p1.add(s1);
//         
//         int r = JOptionPane.showConfirmDialog(null, p1, "Enter number of Players", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
//         if (r == JOptionPane.OK_OPTION) {
//             return (int) s1.getValue();
//         }
//		return 2;
//	}
//
//	/**
//	 * user enter player names.
//	 * @return string array containing number of players.
//	 */
//	public String[] getPlayerNames() {
//		int n = getCountOfPlayers();
//		System.out.println(n);
//		String[] Names = new String[n];
//		
//		JFrame frame = new JFrame("InputDialog");
//		for(int i=1;i<=n;i++){
//			String s = (String)JOptionPane.showInputDialog(
//					frame,
//                    "Enter name of player "+ i,
//                    "Player Info",
//                    JOptionPane.PLAIN_MESSAGE);
//
//			if ((s != null) && (s.length() > 0)) {
//				Names[i-1] = s;
//				System.out.println(s);
//			}
//		}
//		return Names;
//	}
	
}


