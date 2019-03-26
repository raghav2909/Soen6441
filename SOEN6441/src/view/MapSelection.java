/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.MatteBorder;

/**This class is implementing map file selection
 * @author raghavsharda
 * @author gursharandeep
 *@version 2.0
 */
public class MapSelection {
	/**
	 * This button helps to choose the map file
	 */
	JButton OpenMapFile = new JButton("Select Map");
	
	/**
	 * Constructor for the frame setting
	 * @throws IllegalAccessException  it will throw ilegal exception
	 * @throws ClassNotFoundException it will
	 * @throws InstantiationException  it will 
	 * @throws IllegalAccessException it will
	 * 
	 */
	public MapSelection()throws UnsupportedLookAndFeelException,ClassNotFoundException,InstantiationException, IllegalAccessException
	{
		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		JPanel jp = new JPanel();
		jp.setBackground(Color.BLUE);
		
		
		jp.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		JFrame jf = new JFrame("Map Selection window");
		jf.setBackground(Color.BLUE);
		jf.setBounds(80, 80, 300, 240);
		jf.setForeground(Color.BLUE);
		jf.add(BorderLayout.LINE_START,jp);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC , 15));
		OpenMapFile.setForeground(Color.BLACK);
		OpenMapFile.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC , 15));
		jp.add(OpenMapFile);
		
		
		jf.setVisible(true);
		
		
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
