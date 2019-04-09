package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;


public class ResultConsole extends JFrame{
	
	/**
	 * button for window closing
	 */
	private JButton closeWindow;
	
	/**
	 *  button to play again
	 */
	private JButton playAgain;
	
	
	
	private void initData(Object[][] data) {
		int columns = data[0].length;
		String[] headingsOfResult = new String[columns];
		headingsOfResult[0] = "Maps";
		for(int i=1; i<columns; i++) {
			headingsOfResult[i] = "Game "+i;
		}
		JPanel panelOfResult = new JPanel();
		JTable results = new JTable(data, headingsOfResult);
		
		panelOfResult.setLayout(new BoxLayout(panelOfResult, BoxLayout.Y_AXIS));
		this.getContentPane().add(panelOfResult);
		panelOfResult.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelOfResult.add(results.getTableHeader());
		panelOfResult.add(results);
		panelOfResult.add(Box.createRigidArea(new Dimension(10, 10)));
		panelOfResult.add(playAgain);
		panelOfResult.add(Box.createRigidArea(new Dimension(10, 10)));
		panelOfResult.add(closeWindow);
		this.pack();
		this.validate();
		this.setVisible(true);
	}
	
	public ResultConsole(Object[][] data) {
		closeWindow = new JButton("Close!");
		closeWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}			
		});
		playAgain = new JButton("Play-again!");
		
		initData(data);
	}

	/**
	 * adds action listener on button
	 * @param e actionListener
	 */
	public void addActionListenerToPlayAgainButton(ActionListener e) {
		this.playAgain.addActionListener(e);
	}
	
}
