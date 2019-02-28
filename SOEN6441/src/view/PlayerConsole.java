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

/**
 * @author raghavsharda
 *@author Harman
 */
public class PlayerConsole extends JPanel {
	
	public PlayerConsole() {
		this.setBackground(Color.LIGHT_GRAY);

		JLabel lab=  new JLabel("Players");
		this.setLayout(new FlowLayout());
		this.add(lab);
		this.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
		this.setPreferredSize(new Dimension(450,170));
	}

}
