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
 * @author Harman
 *
 */
public class DiceRollConsole extends JPanel {

	public DiceRollConsole() {
		JLabel lab=  new JLabel("Dice Roll");
		this.setLayout(new FlowLayout());
		this.add(lab);
		this.setBorder(BorderFactory.createLineBorder(Color.blue));
		this.setPreferredSize(new Dimension(450,170));
	}
}
