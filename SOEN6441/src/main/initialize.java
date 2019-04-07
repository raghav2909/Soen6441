package main;

import javax.swing.SwingUtilities;

import Model.GameDriver;
import controllers.TheMainController;
/**
 * 
 *@version 2.0
 *
 */ 
public class initialize {

	
public static void main(String[] args)
{
	
	SwingUtilities.invokeLater(new Runnable() {
	@Override
	public void run() {
		TheMainController.getInstance().initialize();
	}
	});
}
}
