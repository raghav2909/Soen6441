package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlsConsole extends JPanel {
	
	
	public ControlsConsole(){
		JLabel lab=  new JLabel("Controls");
		this.setLayout(new FlowLayout());
		this.add(lab);

		this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(BorderFactory.createDashedBorder(Color.black));
		this.setPreferredSize(new Dimension(450,170));
	}

}
