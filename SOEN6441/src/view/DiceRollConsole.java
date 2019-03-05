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
 * class creates the dice roll view on main game window
 * @author raghavsharda
 * @author Harman
 *
 */
public class DiceRollConsole extends JPanel {
	
	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = 2080098977850432494L;

	/**
	 * class constructor displays dice roll view 
	 */
	public DiceRollConsole() {
		JLabel lab=  new JLabel("Dice Roll");
		this.setLayout(new FlowLayout());
		this.add(lab);

		this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(BorderFactory.createDashedBorder(Color.black));
		this.setPreferredSize(new Dimension(450,170));
	}
}
