package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * class creates the dice-roll VIEW on Main-Game-Window
 * @author raghavsharda
 * @author Harman
 *@version 2.0
 */
public class DiceRollConsole extends JPanel{
	
	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = 2080193456450432494L;

	/**
	 * Creates Dice roll view.
	 */
	public DiceRollConsole(){
		JLabel label = new JLabel("Dice-Here.");
		this.setLayout(new FlowLayout());
		this.add(label);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
}
