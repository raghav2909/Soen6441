/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Model.Map;

/**This class provides the view of the map on the main window class
 * @author raghavsharda
 *@author Harman
 *@version 2.0
 */
public class MapConsole extends JPanel implements Observer {
	

	


private BufferedImage bImage;
	
	/**
	 * Boolean to check for .bmp file for the map.
	 */
	private boolean graphicalMap = false;
	
	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = 2353535256045293828L;

	/**
	 * Sets the map console view of the main-window-frame
	 * @param imageNew bImage file uploaded.
	 */
	public MapConsole(String imageNew) {
		this();
		if(imageNew!=null){
	    	try {
	    		bImage = ImageIO.read(new File(imageNew));
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}
	    }
	    this.setBackground(Color.WHITE);
	    this.setLayout(null);
	    this.setOpaque(true);
	    
	    this.graphicalMap = true;
	}
	
	/**
	 *  to Display the map-file on the map-view.
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(bImage!=null) {	
	    	g.drawImage(bImage, 0, 0, this);
	    }
	}
	
	/**
	 * Sets the dimensions of the map-view border.
	 */
	public MapConsole() {
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	

	/**
	 * Sets map data on the map view in a tabular format.
	 * @param newMapData Map data to be displayed.
	 */
	public void setMap(String[][] newMapData){
		this.removeAll();
		JPanel pane = new JPanel();
		GridBagLayout gb = new GridBagLayout();
		pane.setLayout(gb);
		int i = 0;
		for(String[] o : newMapData){
			int j=0;
			for(String k: o){
			    GridBagConstraints gbc = new GridBagConstraints();
			    gbc.ipadx = 20;
			    gbc.gridx = j;
			    gbc.gridy = i;
				pane.add(new JLabel(k), gbc);
				j++;
			}
			i++;
		}
		JScrollPane scroll = new JScrollPane(pane);
		scroll.setPreferredSize(getSize());
		this.add(scroll);
		this.validate();
	}
	
	/**
	 * Sets the coordinates on the map file bImage.
	 * @param newMapData Map data to be displayed.
	 */
	public void setGraphicalMap(String[][] newMapData) {
		this.removeAll();
		
		for(String[] o: newMapData){
			JPanel panel = new JPanel();
			panel.setSize(5, 5);
			panel.setBackground(Color.BLACK);
			panel.setLocation(Integer.parseInt(o[0]), Integer.parseInt(o[4]));
			panel.add(new JLabel(" "));
			String text = o[1]+" "+o[3]+" "+o[2];
			panel.setToolTipText(text);
			this.add(panel);
		}
		this.validate();
	}

	/**
	 * Observer pattern function for Observers to update when there is a notification from the observable.
	 */
	@Override
	public void update(Observable obs, Object map) {
		if(graphicalMap){
			setGraphicalMap(((Map) obs).getObjectOfMapData());
		}else{
			setMap(((Map) obs).getObjectOfMap());
		}
	}
}
