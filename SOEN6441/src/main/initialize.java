package main;

import javax.swing.SwingUtilities;

import Model.GameDriver;
import controllers.the_main_controller;
/**
 * 
 * @verison 1.0
 *
 */
public class initialize {

	
public static void main(String[] args)
{
	
	
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
