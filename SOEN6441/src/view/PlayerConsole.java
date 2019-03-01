/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 * @author raghavsharda
 *@author Harman
 */
public class PlayerConsole extends JPanel {
	
	/**
	 * creates view of player on main frame
	 */
	public PlayerConsole() {
		this.setBackground(Color.LIGHT_GRAY);

		JLabel lab=  new JLabel("Players");
		this.setLayout(new FlowLayout());
		this.add(lab);
		this.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
		this.setPreferredSize(new Dimension(450,170));
	}
	
	/**
	 * Set the player content.
	 * @param playerNames Names of players.
	 */
	public void setPlayerData(String[] Names) {
		for(String name : Names){
			JLabel lbl = new JLabel(name);
			Border border = lbl.getBorder();
			Border margin = new EmptyBorder(10,10,10,10);
			lbl.setBorder(new CompoundBorder(border, margin));
			this.add(lbl);
		}
		this.validate();
	}

}
