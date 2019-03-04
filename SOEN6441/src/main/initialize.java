package main;

import javax.swing.SwingUtilities;

import Model.GameDriver;
import controllers.the_main_controller;

public class initialize {

	
public static void main(String[] args)
{
	/* created the instance of the_main_Controller for future use
	 * not  commenting it for now */
	//GameDriver maincr= new GameDriver();
	
	SwingUtilities.invokeLater(new Runnable() {
	 

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//GameDriver.getInstance();
		the_main_controller.getInstance();
	
	}
	});
}
}
