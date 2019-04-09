package controllers;
import java.awt.EventQueue;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * 
 * File_Open_Controller  performs action  for 
 * for loading the map file on the system and 
 * loading the similar "bmp" file on the front end 
 * @author Raghav_Sharda
 * @version 2.0
 *
 */
public class File_open_Controller extends JFrame {

	
JFileChooser fc = new JFileChooser("Map Selection");

private  String mapRead= null;


public   File_open_Controller() {
		
	}

public String map_location(String newExtension) {

     fc.setCurrentDirectory(new File("./SOEN6441/Map_Data/map"));
    
     if(newExtension.equals("map"))
     {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Map Files", newExtension);
		fc.setFileFilter(filter);
     }
     else
     {
    	 FileNameExtensionFilter filter = new FileNameExtensionFilter("Bmp File", newExtension);
 		fc.setFileFilter(filter);
     }

         switch (fc.showOpenDialog(File_open_Controller.this))
         {
            case JFileChooser.APPROVE_OPTION:
        File selectedFile = fc.getSelectedFile();
		mapRead = selectedFile.getAbsolutePath();
		
		if(mapRead.substring(mapRead.lastIndexOf("."),mapRead.length()).equalsIgnoreCase("."+newExtension)){
			mapRead=mapRead;
			return mapRead;
			}
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
 

return null;

}

}