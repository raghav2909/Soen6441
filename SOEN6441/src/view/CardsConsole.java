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
 * this class creates the cards view on main game console
 * @author raghavsharda
 *
 */
public class CardsConsole extends JPanel {
	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = 91213123892139L;

	
	public CardsConsole()
	{
		JLabel lab=  new JLabel("Cards");
		this.setLayout(new FlowLayout());
		this.add(lab);
		this.setBorder(BorderFactory.createLineBorder(Color.blue));
		this.setPreferredSize(new Dimension(450,170));
		
	}
}
