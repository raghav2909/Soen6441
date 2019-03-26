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
		the_main_controller tmc= new the_main_controller();

		tmc.init();
	
	}
	});
}
}
