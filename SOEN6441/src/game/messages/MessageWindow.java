package game.messages;

	import java.awt.Color;

	import javax.swing.JFrame;
	import javax.swing.JScrollPane;
	import javax.swing.JTextArea;

	/** 
	 * Message notification window 
	 * @author Gursharan
	 *
	 */
	public class MessageWindow extends JFrame {

		private static final long serialVersionUID = 1L;
	    private int w, h;
	    private JTextArea notification = new JTextArea();

	    /**
	     * The constructor of the class.
	     */
	    public MessageWindow() {
	        super("Notification.");
	        this.w = 600;
	        this.h = 600;

	        setSize(this.w, this.h);
	        setResizable(false);
	        setAlwaysOnTop(false);

	        notification.setEditable(false);
	        notification.setLineWrap(true);
	        notification.setWrapStyleWord(true);
	        notification.setBackground(Color.LIGHT_GRAY);
	        notification.setForeground(Color.WHITE);

	        JScrollPane scroll = new JScrollPane (notification, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	        this.add(scroll);
	        setLocationRelativeTo(null);

	        // pack(); // ignore sizing
	        setLocationRelativeTo(null);
	        setVisible(false);
	    }

	    /**
	     * Append new notification to the notificaton window
	     * @param message
	     */
	    public void setNotification(String message) {
	        notification.append("Action: "+message+"\n");
	    }

	    /**
	     * Method creates an panel on the window with message and close button
	     * @return messagePanel object to attach to the window
	     */
	    private JTextArea addNotification() {
	        return null;
	    }

	}


