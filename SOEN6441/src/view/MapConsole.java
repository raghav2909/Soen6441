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

/**
 * @author raghavsharda
 *@author Harman
 */
public class MapConsole extends JPanel implements Observer {
	
	Map map= new Map();

	private BufferedImage picture;
	private boolean graphicalMap = false;
	
	private boolean mapgraph = false;
	
	public MapConsole(String mapimage) {
		// TODO Auto-generated constructor stub

		if(mapimage!=null)
		{
			try {
				picture = ImageIO.read(new File(mapimage));
			}
			catch(IOException i){
				i.printStackTrace();
			}
		}
		this.setBackground(Color.LIGHT_GRAY);
		this.setOpaque(true);
	    this.setLayout(null);
	    
	}
<<<<<<< HEAD

	/**
	 * Sets the coordinates on the map file image.
	 * @param newMap Map data to be displayed.
	 */
	public void setLogicalMap(String[][] newMap) {
		this.removeAll();
		JPanel pane = new JPanel();
		GridBagLayout gb = new GridBagLayout();
		pane.setLayout(gb);
		int i = 0;
		for(String[] o : newMap){
			int j=0;
			for(String k: o) {
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
=======
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441

<<<<<<< HEAD
	/**
	 * Sets the coordinates on the map file image.
	 * @param newMapData Map data to be displayed.
	 */
	public void setGraphical(String[][] newMap) {
		this.removeAll();
		
		for(String[] o: newMap){
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
	 * Set dimension of main frame
	 */
	public MapConsole() {
		// TODO Auto-generated constructor stub
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
=======

>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441
	 /**
     * Display the map image on map frame
     */
<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	    	g.drawImage(picture, 0, 0, this);
	}

<<<<<<< HEAD

		
=======
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441
	
	/**
	 * set map data on map frame
	 * @param newMapData contains map data
	 */
	public void Mapset(String[][] mapinfo) {
		this.removeAll();
		JPanel jpane = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		jpane.setLayout(gbl);
		int i = 0;
		for(String[] r : mapinfo){
			int j=0;
			for(String k: r) {
			    GridBagConstraints gbc = new GridBagConstraints();
			    gbc.ipadx = 20;
			    gbc.gridx = j;
			    gbc.gridy = i;
				jpane.add(new JLabel(k), gbc);
				j++;
			}
			i++;
		}
		JScrollPane scrollpane = new JScrollPane(jpane);
		scrollpane.setPreferredSize(getSize());
		this.add(scrollpane);
		this.validate();
	}
	
	/**
	 * set coordinates on image file
	 * @param mapinfo contains map data
	 */
	public void setCoordinates(String[][] mapinfo) {
		this.removeAll();
		
		for(String[] r: mapinfo){
			JPanel jpanel = new JPanel();
			jpanel.setSize(5, 5);
			jpanel.setBackground(Color.BLACK);
			jpanel.setLocation(Integer.parseInt(r[0]), Integer.parseInt(r[4]));
			jpanel.add(new JLabel(" "));
			String s = r[1]+" "+r[3]+" "+r[2];
			jpanel.setToolTipText(s);
			this.add(jpanel);
		}
		this.validate();
	}

	/**
	 * method of observer pattern which to update if there are updates from observable
	 */

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(mapgraph){
			setCoordinates(map.GetMapInfo());
		}else{
			Mapset(map.GetMap());
		}
<<<<<<< HEAD
	}
=======
}
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441
	}

