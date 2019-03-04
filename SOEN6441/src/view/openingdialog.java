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


public class openingdialog {
	 private  MessageWindow messageWindow = new MessageWindow();

	private JFrame first_frame;
	private JButton newgame;
	private JButton loadgame;
	private JButton mapedit;
	private Edit_create_Map_Controller ecm;
	private String mapRead1= null;

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
		 * 
		 */
		mapedit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				first_frame.dispose();
				 ecm.tobegin();
				
		System.out.println("hello1");
		}
	
	});
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
	 * This method returns the mode of the game selected by the player either 'single' or 'tournament'
	 */
	public String decideMode()
	{
		JFrame fr= new JFrame("Choose Mode of the Game");
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setVisible(true);
		String[] choices = {"Single","Tournament"};
		int h;
		h=JOptionPane.showOptionDialog(fr, "Select the game mode to play", "CHOOSE", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
		if(h==0)
		{
			fr.dispose();
			return "s";
		}
		else
		{
			fr.dispose();
			return "m";
		}
//		return "single";
	
	}
	
	/**
	 * This method returns the initial frame 
	 */
	public JFrame returnframe()
	{
		return this.first_frame;
	}
	
	/**
	 * Sets the action for Play Game button
	 */
	public void Actiongameplay(ActionListener action)
	{
//		this.newgame.addActionListener(action);
	}

	
	
	
	/**
	 * This method shows the frame to 'Load game' or 'New game' option
	 * Sets action on each button
	 */
	public void games_option() {
		JFrame f2 = new JFrame();
		f2.setLayout(new BoxLayout(f2.getContentPane(),BoxLayout.Y_AXIS));
		JButton New_Game = new JButton("New Game");
		JButton Load_Game = new JButton("Load Game");
		f2.add(Load_Game);
		f2.add(New_Game);
		f2.pack();
		f2.setVisible(true);
		
		New_Game.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				f2.dispose();
				try {
					the_main_controller.getInstance().Single_Mode_Start();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		Load_Game.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e)
				{
			f2.dispose();
			the_main_controller.getInstance().single_Mode_Saved_Start();
			
			//File_open_Controller foc= new File_open_Controller();
			}
		});
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


