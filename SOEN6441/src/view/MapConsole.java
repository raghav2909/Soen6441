/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author raghavsharda
 *@author Harman
 */
public class MapConsole extends JPanel {
	

	public MapConsole(String mapimage) {
		// TODO Auto-generated constructor stub

		if(mapimage!=null)
		{
			try {
				BufferedImage picture = ImageIO.read(new File(mapimage));
			}
			catch(IOException i){
				i.printStackTrace();
			}
		}
		this.setBackground(Color.MAGENTA);
		this.setOpaque(true);
	    this.setLayout(null);
	    
	}
	
	}


