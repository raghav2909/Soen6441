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
	

	private BufferedImage picture;
	private boolean graphicalMap = false;
	
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
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	    	g.drawImage(picture, 0, 0, this);
	}
	@Override
	public void update(Observable obs, Object arg) {

	
		if(graphicalMap){
			setGraphical(((Map) obs).GetMapDataObject());
		}else{
			setLogicalMap(((Map) obs).GetMapDataObject());
		}
	
	}
	}

