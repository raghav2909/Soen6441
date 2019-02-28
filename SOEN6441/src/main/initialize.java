package main;

import javax.swing.SwingUtilities;

import controllers.the_main_controller;

public class initialize {

	
public static void main(String[] args)
{
	/* created the instance of the_main_Controller for future use
	 * not  commenting it for now */
	the_main_controller maincr= new the_main_controller();
	
	SwingUtilities.invokeLater(new Runnable() {
	 

	@Override
	public void run() {
		// TODO Auto-generated method stub
		the_main_controller.getInstance().chooseplayoredit();
	}
	});
}
}
