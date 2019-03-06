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
 * This class displays the player view on main window
 * @author raghavsharda
 *@author Harman
 *@version 1.0
 */
public class PlayerConsole extends JPanel {

	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = -7512274442706727095L;

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
	 * @param Names Names of players.
	 */
	public void setPlayerData(String[] Names) {
		System.out.println(Names);
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
