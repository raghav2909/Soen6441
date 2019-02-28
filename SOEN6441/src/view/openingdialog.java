package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import controllers.*;
import game.messages.MessageWindow;


public class openingdialog {
	 private  MessageWindow messageWindow = new MessageWindow();

	JFrame first_frame;
	JButton newgame;
	JButton loadgame;
	JButton mapedit;
	private Edit_create_Map_Controller ecm;
	private String mapRead1= null;

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
				
				 ecm.getInstance().tobegin();
		System.out.println("hello1");
		first_frame.dispose();}
	
	});
		newgame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				the_main_controller.getInstance().Single_Mode_Start();
				first_frame.dispose();
			}
		});
		
		loadgame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				the_main_controller.getInstance().single_Mode_Saved_Start();
				first_frame.dispose();
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
				the_main_controller.getInstance().Single_Mode_Start();
				
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

	

}


