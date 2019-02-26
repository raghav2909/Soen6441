package controllers;

import view.Map_Frame;

/**
 * Edit_Create_Map_Controller performs action  for 
 * creating new map or modifying the existing maPS
 * @see File_open_Controller
 * @author Raghav
 * @author Harmanpreet
 */
public class Edit_create_Map_Controller {
	/**
	 * object of Edit_Create_Map_Controller class used for calling the class methods 
	 */
	private static Edit_create_Map_Controller ecm ;

	public Edit_create_Map_Controller() {
		
	}
	public static Edit_create_Map_Controller getInstance() {
		if(ecm==null) {
			ecm = new Edit_create_Map_Controller();
		}
		return ecm;
	}
	public void tobegin()
	{
		Map_Frame newMapFrame = new Map_Frame();
		createnewmap();
		editexistingmap();
		System.out.println("hey coming in edit or create");
		
	}
	private void editexistingmap() {
		// TODO Auto-generated method stub
		
	}
	private void createnewmap() {
		// TODO Auto-generated method stub
		
	}
}

