package controllers;
import java.awt.EventQueue;

/**
 * File_Open_Controller  performs action  for 
 * for loading the map file on the system and 
 * loading the similar "bmp" file on the front end 
 * @see the_main_Controller
 * @author Raghav_Sharda
 */
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import view.GameConsole;

public class File_open_Controller extends JFrame {
		   JFileChooser fc = new JFileChooser("Map Selection");
GameConsole gmc;
	
public  String mapRead= null;

/**
 * This constructor gives user the option to select the map file when they go for play game option.
 * The same constructor will be used when the user wants to open a saved game.
 * @param newExtension It will take the extension type of the file that the user wants
 * to open.
 */
public   File_open_Controller( String newExtension) {

	      setDefaultCloseOperation(EXIT_ON_CLOSE);
	      JPanel pnl = new JPanel();
	     // pnl.validate();
	      //pnl.setVisible(true);
	      pnl.setLayout(new GridLayout(2, 1));
	      JButton btn = new JButton("Open Maps");
	      ActionListener al;
	      fc.setCurrentDirectory(new File("./SOEN6441/Map_Data/map"));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Map Files", newExtension);
			fc.setFileFilter(filter);
	      al = new ActionListener()		  
        {
           @Override
           public void actionPerformed(ActionEvent ae)
           {
              switch (fc.showOpenDialog(File_open_Controller.this))
              {
                 case JFileChooser.APPROVE_OPTION:
                    JOptionPane.showMessageDialog(File_open_Controller.this, "Selected: "+
                                                  fc.getSelectedFile(),
                                                  "Open Map",
                                                  JOptionPane.OK_OPTION);File selectedFile = fc.getSelectedFile();
			mapRead = selectedFile.getAbsolutePath();
			
			if(mapRead.substring(mapRead.lastIndexOf("."),mapRead.length()).equalsIgnoreCase("."+newExtension)){
				mapRead=mapRead;

				}
			
			System.out.println(mapRead);
		break;

                 case JFileChooser.CANCEL_OPTION:
                    JOptionPane.showMessageDialog(File_open_Controller.this, "Cancelled",
                                                  "Not Open",
                                                  JOptionPane.OK_OPTION);
                    break;

                 case JFileChooser.ERROR_OPTION:
                    JOptionPane.showMessageDialog(File_open_Controller.this, "Error",
                                                  "Map Opening Error",
                                                  JOptionPane.OK_OPTION);
              }
           }
        };
        btn.addActionListener(al);
        pnl.add(btn);

System.out.println("comning here 2");
setContentPane(pnl);

pack();
setVisible(true);
		
	}

public String map_location() {
	File_open_Controller foc= new File_open_Controller("map");

	return mapRead;
}

}
	
