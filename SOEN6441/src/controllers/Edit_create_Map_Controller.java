package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import Model.*;

import view.*;


import javax.swing.JFileChooser;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * Edit_Create_Map_Controller performs action  for creating a new map or modifying the existing maps
 * 
 * @author Raghav
 * @author Harmanpreet
 * @author Gursharan
 * @see File_open_Controller
 */
public class Edit_create_Map_Controller {
	/**
	 * object of Edit_Create_Map_Controller class used for calling the class methods 
	 */
	private static Edit_create_Map_Controller ecm ;
	/*
	 * constructor of the class
	 */
			
	public Edit_create_Map_Controller() {
		
	}
	/**
	 * object of MapSelection class used for calling  methods of MapSelection 
	 */
	private MapSelection mapselection;
	/**
	 * action listener linked to the button "Select Map File" for selecting map file
	 */	
	private ActionListener ActionExistingBtn;
	/**
	 * action listener applied on button "Choose Map File" for selecting map file
	 */
	private ActionListener ActionNewButton;

	/**
	 * object of ExistingMapModifier class used for calling the its methods.
	 */
	
	ExistingMapModifier existingMapModifier;

	/**
	 * Stores the FilePath of the file Selected
	 */
	public String FilePath = "";
	
	/**
	 * Object of ReadMap class from Model
	 */
	
	ReadMap readMap = new ReadMap();
/**
 * object of Map class from Model
 */
	 
	
	Map mapEditor =new Map();// 
	/**
	 * Calls the readMap function of ReadMap class from Model to read the map files
	 * @param file_name address of the map file to be loaded
	 */
	public void ReadofMap(String file_name) {
		 try {
			readMap.mapreader(file_name);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // to be done in ReadMap class in model 
	}
	
	/**
	 * Function to choose from the existing map files on the local system
	 * Implements the ActionListener events for the button performing map file selection
	 * while modifying existing maps.
	 */
	public void MapSelectionActions() {
		try {
			this.mapselection = new MapSelection();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} 

		ActionExistingBtn=(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser jfc = new JFileChooser();
				jfc.setCurrentDirectory(new java.io.File("user.home"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Map Files", "map");
				jfc.setFileFilter(filter);
				jfc.setDialogTitle("Choose Map File");
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

				if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					FilePath = jfc.getSelectedFile().getAbsolutePath();
					
					ArrayList<NodeOfMap> x = null;
					try {
						x = readMap.mapreader(jfc.getSelectedFile().getAbsolutePath());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//mapReader = new Map(); //check the Map file in Model
					MapExist mapExist = new MapExist(x);
					// create clss  map exist
					mapExist.addActionsToBtnEdit(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							mapExist.setVisible(false);// to be defined in MapExist
							ArrayList<NodeOfMap> existing_map_Info = mapExist.getMapExistsInfo(); // to pick from MapExist
							mapEditor.WritingOldMap(existing_map_Info);// to be written in Map
							existingMapModifier = new ExistingMapModifier(existing_map_Info);// to come from ExistingMapModifier
							existingMapModifier.setVisible(true);
							ActionOnExistingMap();
						}
					});
					mapExist.setVisible(true);// to be defined in MapExist
				}
			}
		});
		this.mapselection.openfilebuttonaction(ActionExistingBtn);// in MapSelection need to create class
	}	

	public String getFilePath() {
		return FilePath;
	}

	/**
	 * MapNew Frame get initialize with this method
	 */
	public void mapNewActions() {
		NewMapCreation mapNew = new NewMapCreation();
		mapNew.setVisible(true);//from MapNew in View
		mapNew.addActionsToAddContinentButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapNew.continentFieldsEnable();
			}
		});

		mapNew.addActionsToAddCountryButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapNew.countryfieldEnable();
			}
		});

		mapNew.addActionsToBtnComplete(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cn = mapNew.getContinentName();
				String cv = mapNew.getControlValue();
				if(cn.compareTo("")==0 || cv.compareTo("")==0){
					mapNew.errorEnterValues();
				}else{
					int control_value= Integer.parseInt(cv);
					Boolean continentExist1 = mapEditor.CheckContinentExist(cn);//from Map
					if(!continentExist1){
						ArrayList<NodeOfCountry > countryArr = new ArrayList<NodeOfCountry >();
						mapEditor.AddContinents(cn, countryArr, control_value);//from Map
						mapNew.continentContentsCleared();
						for(NodeOfMap i: mapEditor.getContinents()){ // from Map
							String continent = i.getContinent();//from nodeOfMap
							mapNew.setDataInContinentsComboBox(continent);
						}
					}
				}
				mapNew.continentFieldDisabled();
			}
		});

		mapNew.addActionsToAddNeighboursButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapNew.JListEnabled();
				String sCountrytToAddNeighbour = mapNew.getSelectedCountriesForNeighbours();
				mapNew.clearNeighboursJList();

				for (NodeOfMap node : mapEditor.getContinents()){//from Map
					for (NodeOfCountry NodeOfCountry : node.getCountries()){
						if(sCountrytToAddNeighbour.compareTo(NodeOfCountry.getNameOfCountry())==0)
							continue;
						mapNew.addPossibleNeighboursToJList(NodeOfCountry.getNameOfCountry());
					}
				}
			}
		});

		mapNew.addActionsToNeighbourChooseButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mapNew.getListOfNeighbours().isEmpty()) {
					mapNew.noNeighboursSelecteError();
				}else {
					ArrayList<NodeOfCountry> neighbours= new ArrayList<NodeOfCountry>();
					for (Object ncountry : mapNew.getListOfNeighbours()){
						NodeOfCountry cn =  new NodeOfCountry(ncountry.toString(), null, null);
						neighbours.add(cn);
					}
					for (NodeOfMap node : mapEditor.getContinents()){
						for (NodeOfCountry cNode : node.getCountries()){
							String sCountrytToAddNeighbour = mapNew.getSelectedCountriesForNeighbours();
							if(sCountrytToAddNeighbour.compareTo(cNode.getNameOfCountry())==0)
								for (NodeOfCountry neighbourNode : neighbours){
									cNode.AddNeighbour(neighbourNode);	 //from Nodeofcountry
								}
						}
					}
				}	
			}
		});

		mapNew.addActionsToDeleteContinentButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<NodeOfMap> continents = mapEditor.getContinents();
				String delete_continent = mapNew.continentForDeletion();
				for (NodeOfMap i :continents) {
					if(i.getContinent().compareTo(delete_continent)==0) {
						continents.remove(i);
						break;
					}
				}
				mapNew.clearNeighboursJList();
				mapNew.continentContentsCleared();
				mapNew.countriesContentsCleared();
				for(NodeOfMap i: continents) {
					mapNew.setDataInContinentsComboBox(i.getContinent());
					for (NodeOfCountry NodeOfCountry : i.getCountries()){
						mapNew.setDataInCountriesComboBox(NodeOfCountry.getNameOfCountry());
						mapNew.addPossibleNeighboursToJList(NodeOfCountry.getNameOfCountry());
					}
				}
			}
		});

		mapNew.addActionsToSaveMapButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mapEditor.CheckSaveMap()) {
					try {
						mapEditor.SaveMapFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					mapNew.errorNullCountry();
				}
			}
		});

		mapNew.addActionsToDeleteCountryButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedcountry = mapNew.getCountriesToRemove();
				mapNew.clearNeighboursJList();
				mapNew.countriesContentsCleared();
				ArrayList<NodeOfMap> continents = mapEditor.getContinents();

				for (NodeOfMap node: continents) {
					for (NodeOfCountry temp : node.getCountries()) {
						if(temp.getNameOfCountry().compareTo(selectedcountry)==0) {
							node.deletecountry(temp);//from NodeOfCountry
						}
					}
				}
				for (NodeOfMap node: continents) {
					for (NodeOfCountry temp : node.getCountries()) {
						mapNew.setDataInCountriesComboBox(temp.getNameOfCountry());
						mapNew.addPossibleNeighboursToJList(temp.getNameOfCountry());
					}
				}
			}
		});

		mapNew.addActionsToAddButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean ContinentPresent = mapNew.continentExistCheck();
				if(ContinentPresent) {
					String cn1 = mapNew.getNAmeOfCountry();
					if(cn1.compareTo("")==0) {
						mapNew.errorEnterValues();
					}else {
						String selectedContinent = mapNew.getSelectedContinent();
						Boolean countryExist = mapEditor.CheckCountryExist(cn1);
						if(!countryExist) {
							ArrayList<NodeOfCountry> neighbours= new ArrayList<NodeOfCountry>();
							for (Object ncountry : mapNew.getListOfNeighbours()) {
								NodeOfCountry cn =  new NodeOfCountry(ncountry.toString(), null, null);
								neighbours.add(cn);
							}
							mapNew.clearNeighboursJList();
							mapNew.countriesContentsCleared();
							for (NodeOfMap node: mapEditor.getContinents()) {
								if(selectedContinent.compareTo(node.getContinent())==0) { //from Nodeofcountyry
									int a[]= {250,250};
									NodeOfCountry newCountry = new NodeOfCountry(cn1,  neighbours , a);
									node.addcountry(newCountry);//from countryNode
								}
								for (NodeOfCountry temp : node.getCountries()) {
									mapNew.addPossibleNeighboursToJList(temp.getNameOfCountry());
									mapNew.setDataInCountriesComboBox(temp.getNameOfCountry());

								}
							}
						}else {
							mapNew.errorCountryAlreadyExist();
						}
					}
				}else {
					mapNew.errorNullContinent();
				}
				mapNew.countryFieldDisable();
			}
		});

		mapNew.setVisible(true);
	}

	public void ActionOnExistingMap() {

		existingMapModifier.addActionsToAddContinentButton(new ActionListener() { //from ExistingMapMODIFIER
			public void actionPerformed(ActionEvent e) {
				existingMapModifier.continentFieldsEnable();
			}
		});

		existingMapModifier.addActionsToAddCountryButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				existingMapModifier.countryfieldEnable();
			}
		});

		existingMapModifier.addActionsToBtnComplete(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cv = existingMapModifier.getControlValue();
				String cn = existingMapModifier.getContinentName();
				
				if(cn.compareTo("")==0 || cv.compareTo("")==0){
					existingMapModifier.errorEnterValues();
				}else{
					int control_value= Integer.parseInt(cv);
					Boolean continentExist1 = mapEditor.CheckContinentExist(cn);
					if(!continentExist1){
						ArrayList<NodeOfCountry> countryArr = new ArrayList<NodeOfCountry>();
						mapEditor.AddContinents(cn, countryArr, control_value);
						existingMapModifier.continentContentsCleared();
						for(NodeOfMap i: mapEditor.getContinents()){
							String continent = i.getContinent(); //from NodeOfmap
							existingMapModifier.setDataInContinentsComboBox(continent);
						}
					}
				}
				existingMapModifier.continentFieldDisabled();
			}
		});

		existingMapModifier.addActionsToAddNeighboursButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				existingMapModifier.JListEnabled();
				String sCountrytToAddNeighbour = existingMapModifier.getSelectedCountriesForNeighbours();
				existingMapModifier.clearNeighboursJList();
				for (NodeOfMap node : mapEditor.getContinents()){
					for (NodeOfCountry NodeOfCountry : node.getCountries()){
						if(sCountrytToAddNeighbour.compareTo(NodeOfCountry.getNameOfCountry())==0)
							continue;
						existingMapModifier.addPossibleNeighboursToJList(NodeOfCountry.getNameOfCountry());
					}
				}
			}
		});

		existingMapModifier.addActionsToNeighbourChooseButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(existingMapModifier.getListOfNeighbours().isEmpty()) {
					existingMapModifier.noNeighboursSelecteError();
				}else {
					ArrayList<NodeOfCountry> neighbours= new ArrayList<NodeOfCountry>();
					for (Object ncountry : existingMapModifier.getListOfNeighbours()){
						NodeOfCountry cn =  new NodeOfCountry(ncountry.toString(), null, null);
						neighbours.add(cn);
					}
					for (NodeOfMap node : mapEditor.getContinents()){
						for (NodeOfCountry cNode : node.getCountries()){
							String sCountrytToAddNeighbour = existingMapModifier.getSelectedCountriesForNeighbours();
							if(sCountrytToAddNeighbour.compareTo(cNode.getNameOfCountry())==0)
								for (NodeOfCountry neighbourNode : neighbours){
									cNode.AddNeighbour(neighbourNode);	//FROM NODEoFCOUNTRY
								}
						}
					}
				}
			}
		});

		existingMapModifier.addActionsToDeleteContinentButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<NodeOfMap> continents = mapEditor.getContinents();
				String delete_continent = existingMapModifier.continentForDeletion();
				for (NodeOfMap i :continents) {
					if(i.getContinent().compareTo(delete_continent)==0) {
						continents.remove(i);//from nodeOfMap
						break;
					}
				}
				existingMapModifier.continentContentsCleared();
				for(NodeOfMap i: continents) {
					existingMapModifier.setDataInContinentsComboBox(i.getContinent());// from NodeOfMap
				}
				existingMapModifier.clearNeighboursJList();
				existingMapModifier.continentContentsCleared();
				existingMapModifier.countriesContentsCleared();
				for(NodeOfMap i: continents) {
					existingMapModifier.setDataInContinentsComboBox(i.getContinent());
					for (NodeOfCountry NodeOfCountry : i.getCountries()){
						existingMapModifier.setDataInCountriesComboBox(NodeOfCountry.getNameOfCountry());
						existingMapModifier.addPossibleNeighboursToJList(NodeOfCountry.getNameOfCountry());
					}
				}
			}
		});

		existingMapModifier.addActionsToSaveMapButton(new ActionListener() {//addActionsToSaveMapButton
			public void actionPerformed(ActionEvent e) {
				if(mapEditor.CheckSaveMap()) { //to come from map
					try {
						mapEditor.SaveToOldFile(getFilePath());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					existingMapModifier.errorNullCountry();
				}
			}
		});

		existingMapModifier.addActionsToDeleteCountryButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedcountry = existingMapModifier.getCountriesToRemove();
				existingMapModifier.clearNeighboursJList();
				existingMapModifier.countriesContentsCleared();
				ArrayList<NodeOfMap> continents = mapEditor.getContinents();
				for (NodeOfMap node: continents) {
					for (NodeOfCountry temp : node.getCountries()) {
						if(temp.getNameOfCountry().compareTo(selectedcountry)==0) {
							node.deletecountry(temp); //NodeOfCountry
						}
					}
				}
				for (NodeOfMap node: continents) {
					for (NodeOfCountry temp : node.getCountries()) {
						existingMapModifier.setDataInCountriesComboBox(temp.getNameOfCountry());
						existingMapModifier.addPossibleNeighboursToJList(temp.getNameOfCountry());
					}
				}
			}
		});

		existingMapModifier.addActionsToAddButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean ContinentPresent = existingMapModifier.continentExistCheck();
				if(ContinentPresent) {
					String cn1 = existingMapModifier.getNAmeOfCountry();
					if(cn1.compareTo("")==0) {
						existingMapModifier.errorEnterValues();
					}else {
						String selectedContinent = existingMapModifier.getSelectedContinent();
						Boolean countryExist = mapEditor.CheckCountryExist(cn1); // from map
						if(!countryExist) {
							ArrayList<NodeOfCountry> neighbours= new ArrayList<NodeOfCountry>();
							for (Object ncountry : existingMapModifier.getListOfNeighbours()) {//check
								NodeOfCountry cn =  new NodeOfCountry(ncountry.toString(), null, null);
								neighbours.add(cn);
							}
							existingMapModifier.clearNeighboursJList();
							existingMapModifier.countriesContentsCleared();
							for (NodeOfMap node: mapEditor.getContinents()) {
								if(selectedContinent.compareTo(node.getContinent())==0) {// from NodeOFMap
									int a[]= {250,250};
									NodeOfCountry newCountry = new NodeOfCountry(cn1,  neighbours , a);
									node.addcountry(newCountry);
								}
								for (NodeOfCountry temp : node.getCountries()) {
									existingMapModifier.addPossibleNeighboursToJList(temp.getNameOfCountry());
									existingMapModifier.setDataInCountriesComboBox(temp.getNameOfCountry());
								}
							}
						}else {
							existingMapModifier.errorCountryAlreadyExist();
						}
					}
				}else {
					existingMapModifier.errorNullContinent();
				}
				existingMapModifier.countryFieldDisable();
			}
		});
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
		newMapFrame.start();
		//createnewmap();
		//editexistingmap();
		System.out.println("hey coming in edit or create");
		
	}
	//private void editexistingmap() {
		// TODO Auto-generated method stub
		
	//}
	//private void createnewmap() {
		// TODO Auto-generated method stub
		
	//}
}