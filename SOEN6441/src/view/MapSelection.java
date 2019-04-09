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
	JButton OpenTheMapFile = new JButton("Select-Map!");
	
	/**
	 * Constructor for the frame setting MapSelection class
	 * 
	 * @throws IllegalAccessException  it will throw illegal exception
	 * @throws ClassNotFoundException it will throw classnotFound exception
	 * @throws InstantiationException  it will throw Instantiation exception
	 * @throws IllegalAccessException it will throw illegalAcess exception
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
		OpenTheMapFile.setForeground(Color.BLACK);
		OpenTheMapFile.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC , 15));
		jp.add(OpenTheMapFile);
		jf.setVisible(true);
		
	}
	/**
     * add Action Listener for the Open-Map file Button!!
     * @param ae reference for ActionListener object
     */
	public void openFileChooseBtnAction(ActionListener ae)
	{
		OpenTheMapFile.addActionListener(ae);
	}
}
