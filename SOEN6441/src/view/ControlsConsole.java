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
		this.setBorder(BorderFactory.createLineBorder(Color.blue));
		this.setPreferredSize(new Dimension(450,170));
	}

}
