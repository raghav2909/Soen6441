/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**This class is implementing map file selection
 * @author raghavsharda
 * @author gursharandeep
 *
 */
public class MapSelection {
	/**
	 * This button helps to choose the map file
	 */
	JButton OpenMapFile = new JButton("Map File Selection");
	/**
	 * Constructor for the frame setting
	 * @throws IllegalAccessException 
	 * 
	 */
	public MapSelection()throws UnsupportedLookAndFeelException,ClassNotFoundException,InstantiationException, IllegalAccessException
	{
		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		JPanel jp = new JPanel();
		JFrame jf = new JFrame("Map Selection");
		jp.add(BorderLayout.CENTER,jp);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jp.add(OpenMapFile);
		jf.setSize(400,450);
		jf.setVisible(true);
		//jf.setLocationRelativeTo(null);
		
	}
	/**
     * ActionListener for the Open Map file button
     * @param ae reference for ActionListener object
     */
	public void openfilebuttonaction(ActionListener ae)
	{
		OpenMapFile.addActionListener(ae);
	}
}
