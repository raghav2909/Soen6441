package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import Model.ModelOfMap;
import Model.NodeOfCountry;
import Model.NodeOfMap;
import Model.ReadMap;
import view.MapSelection;
import view.NewMapCreation;
import view.*;



/**
 * mapEditorController perform action listeners for 
 * New Map and Existing Map buttons in @see MapFrame view 
 * and choosing map file action.
 * @see MapSelection
 * @author raghavsharda
 * @author gursharan
 */
public class EditCreateMapController {

	/**
	 * mapChooser variable for storing the reference of the class MapSelection
	 */
	private MapSelection mapChooser;

	/**
	 * existingMapEditor variable for storing the reference of the class ExistingMapModifier 
	 */
	private ExistingMapModifier existingMapEditor;

	/**
	 * newMap variable for storing the reference of the class NewMapCreation.
	 */
	private NewMapCreation newMap;

	/**
	 * action listener applied on button "Choose Map File" for selecting map file
	 */	
	private ActionListener existingBtnAction;

	/**
	 * Stores the path of the file chosen
	 */
	public String path = "";

	/**
	 * creates a new variable and storing the reference of the class
	 */
	private ModelOfMap mapModel = new ModelOfMap();
	
	/**
	 * existingMap stores the reference of the class MapExist
	 */
	MapExist existingMap;
	
	/**
	 *  creates a new variable and storing the reference of the class
	 */
	MapExist existingMap1 = new MapExist();
	
	/**
	 * creates a new variable and storing the reference of the class
	 */
	ReadMap mapReader = new ReadMap();

	/**
	 * Calls the readMap function of ReadMap to read the map file
	 * @param filename address of the map file to be loaded
	 */
	public void MapRead(String filename) {
		ReadMap mapReader = new ReadMap();
		mapReader.readingMap(filename);
	}

	/**
	 * Function to browse the map file on the local system
	 * This function implements the ActionListener events for the map file chooser button
	 */
	public void mapFileChooserActions() {
		try {
			/*creates object of mapFileChooser*/
			this.mapChooser = new MapSelection();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		/*actionListener for choose map file button*/
		existingBtnAction=(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new File("./SOEN6441/Map_Data/Map"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Map Files", "map");
				fc.setFileFilter(filter);
				fc.setDialogTitle("Choose your Conquest Map File");
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					/*get the path of the selected file*/
					path = fc.getSelectedFile().getAbsolutePath();
					
					ArrayList<NodeOfMap> map = mapReader.readingMap(fc.getSelectedFile().getAbsolutePath());
					mapModel.existingMapWriter(map);
					
					if(mapModel.checkSavedMap()) {
						if(mapModel.checkConnectedContinent()) {
							//dialog box
							existingMap1.mapSuccessfullyLoadedMsg();
							/*pass the existing map file information to the existing file editor*/
							existingMap = new MapExist(mapReader.readingMap(fc.getSelectedFile().getAbsolutePath()));
							/*actionListener for edit button of existing map frame*/
							existingMap.addActionsToEditButton(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									existingMap.setVisible(false);
									ArrayList<NodeOfMap> existing_map_Info = existingMap.getInfoForExistingMap();
									mapModel.existingMapWriter(existing_map_Info);
									existingMapEditor = new ExistingMapModifier(existing_map_Info);
									existingMapEditor.setVisible(true);
									existingMapActions();
								}
							});
							existingMap.setVisible(true);
						}else {
							existingMap1.showUnconnectedContinentError();
						}
						
					}else {
						existingMap1.mapCannotLoadedError();
					}
				}
			}
		});
		this.mapChooser.openFileChooseBtnAction(existingBtnAction);
	}	

	/**
	 * Function to get the path of map file 
	 * @return path of map file.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Contains actionListeners for all NewMapCreation buttons.
	 * @see NewMapCreation
	 */
	public void newMapActions() {
		newMap = new NewMapCreation();
		newMap.setVisible(true);

		/**
		*action listener for adding the continent
		*/
		newMap.addActionsToAddContinentButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newMap.continentFeldToEnable();
			}
		});

		/**
		*action listener for adding the country.
		*/
		newMap.addActionsToAddCountryButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newMap.countryFeldToEnable();
			}
		});

		/**
		*action listener to save all the selected changes.
		*/
		newMap.addActionsToBtnComplete(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cn = newMap.getNameOfContinent();
				String cv = newMap.getControlValue();
				/*
				 * if continent name or control value fields are empty,
				 * give 'enter values' error dialog box. 
				 */
				if(cn.compareTo("")==0 || cv.compareTo("")==0){
					newMap.errorEnterValues();
				}else{
					int control_value= Integer.parseInt(cv);
					Boolean continentExist1 = mapModel.checkContinentExistence(cn);
					/*check first that the continent is unique*/
					if(!continentExist1){
						ArrayList<NodeOfCountry> countryArr = new ArrayList<NodeOfCountry>();
						mapModel.addContinents(cn, countryArr, control_value);
						newMap.continentContentsClearedFromComboBox();
						for(NodeOfMap i: mapModel.getContinents()){
							String continent = i.getNameOfContinent();
							newMap.setDataInContinentsComboBox(continent);
						}
					}
				}
				newMap.continentFieldDisabled();
			}
		});

		/**
		*action listener for adding the neighbours
		*/
		newMap.addActionsToAddNeighboursButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newMap.jListEnabled();
				String sCountrytToAddNeighbour = newMap.getSelectedCountryForNeighbours();
				newMap.clearNeighboursJList();

				for (NodeOfMap node : mapModel.getContinents()){
					for (NodeOfCountry countryNode : node.getCountries()){
						/*add all possible neighbor countries to the JList*/
						if(sCountrytToAddNeighbour.compareTo(countryNode.getNameOfCountry())==0)
							continue;
						newMap.addPossibleNeighboursInJList(countryNode.getNameOfCountry());
					}
				}
			}
		});

		/**
		*action listener for deleting the neighbours
		*/
		newMap.addActionstoDeleteNeighboursButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newMap.jListEnabled_1();
				String sCountryToDeleteNeighbour = newMap.getSelectedCountryForNeighbourDeletion();
				newMap.clearNeighboursJList_1();

				for (NodeOfMap node : mapModel.getContinents()){
					for (NodeOfCountry countryNode : node.getCountries()){
						if(sCountryToDeleteNeighbour.compareTo(countryNode.getNameOfCountry())==0) {
							for (NodeOfCountry neighbour : countryNode.getNeighbourCountries() ) {
								newMap.addPossibleNeighboursToJList_1(neighbour.getNameOfCountry());
							}
						}
					}
				}
			}
		});

		/**
		*action listener for selecting the neighbours.
		*/
		newMap.addActionsToNeighbourChooseButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(newMap.getListOfNeighbours().isEmpty()) {
					newMap.errorNeighboursNotSelected();
				}
				else {
					ArrayList<NodeOfCountry> neighbours= new ArrayList<NodeOfCountry>();
					/*Get the selected neighbors from the JList 
					 *and add them to the neighbors arrayList
					 **/
					for (Object ncountry : newMap.getListOfNeighbours()){
						NodeOfCountry cn =  new NodeOfCountry(ncountry.toString(), null, null,null);
						neighbours.add(cn);
					}
					String sCountrytToAddNeighbour = newMap.getSelectedCountryForNeighbours();
					for (NodeOfMap node : mapModel.getContinents()){
						for (NodeOfCountry cNode : node.getCountries()){
							/*When the selected country is found for which 
							 * neighbors need to added, add all the neighbors in arrayList
							 * to this countryNode
							 * */
							if(sCountrytToAddNeighbour.compareTo(cNode.getNameOfCountry())==0)
								for (NodeOfCountry neighbourNode : neighbours){
									cNode.addNeighbour(neighbourNode);
								}

						}
					}
					/*When a country A adds B as its neighbor,
					 * then B also adds A in its list of neighbors,
					 * forming bidirectional links between the countries.*/
					for (NodeOfCountry neighbour : neighbours) {
						for (NodeOfMap node : mapModel.getContinents()) {
							for (NodeOfCountry countryNode : node.getCountries()) {
								if(countryNode.getNameOfCountry().compareTo(neighbour.getNameOfCountry())==0) {
									countryNode.addNeighbour(new NodeOfCountry(sCountrytToAddNeighbour, null, null,null));
								}
							}
						}
					}
					newMap.successfullyAddedNeighbours();
				}	
			}
		});

		/**
		*action listener for deleting the selected neighbours
		*/
		newMap.addActionsToDeleteSelectedNeighboursButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(newMap.getListOfNeighbours_1().isEmpty()) {
					newMap.errorNeighboursNotSelected();
				}
				else {
					ArrayList<NodeOfCountry> neighbours_1= new ArrayList<NodeOfCountry>();
					/*get the list of neighbors selected to be deleted 
					 * and store them in an arraList.
					 * */
					for (Object ncountry : newMap.getListOfNeighbours_1()){
						NodeOfCountry cn =  new NodeOfCountry(ncountry.toString(), null, null,null);
						neighbours_1.add(cn);
					}
					String sCountrytToDeleteNeighbour = newMap.getSelectedCountryForNeighbourDeletion();
					for (NodeOfMap node : mapModel.getContinents()){
						for (NodeOfCountry cNode : node.getCountries()){
							/*When the selected country is found, delete its neighbors.
							 */
							if(sCountrytToDeleteNeighbour.compareTo(cNode.getNameOfCountry())==0)
								for (NodeOfCountry neighbourNode : neighbours_1){
									cNode.removeNeighbour(neighbourNode);	
								}
						}
					}
					/*Delete the neighbor link from both sides.
					 */
					for (NodeOfCountry neighbour : neighbours_1) {
						for (NodeOfMap node : mapModel.getContinents()) {
							for (NodeOfCountry countryNode : node.getCountries()) {
								if(countryNode.getNameOfCountry().compareTo(neighbour.getNameOfCountry())==0) {
									countryNode.removeNeighbour(new NodeOfCountry(sCountrytToDeleteNeighbour, null, null,null));
								}
							}
						}
					}
					newMap.successfullyDeletedNeighbours();
				}	
			}
		});


		/**
		*action listener for deleting the continent
		*/
		newMap.addActionsToDeleteContinentButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<NodeOfMap> continents = mapModel.getContinents();
				String delete_continent = newMap.continentForDeletion();
				for (NodeOfMap i :continents) {
					if(i.getNameOfContinent().compareTo(delete_continent)==0) {
						continents.remove(i);
						break;
					}
				}
				/*Update view
				 */
				newMap.clearNeighboursJList();
				newMap.continentContentsClearedFromComboBox();
				newMap.countriesContentsClearedFromComboBox();
				for(NodeOfMap i: continents) {
					newMap.setDataInContinentsComboBox(i.getNameOfContinent());
					for (NodeOfCountry countryNode : i.getCountries()){
						newMap.setDataInCountriesComboBox(countryNode.getNameOfCountry());
						newMap.addPossibleNeighboursInJList(countryNode.getNameOfCountry());
					}
				}
			}
		});

		/**
		*action listener for saving the changes.
		*/
		newMap.addActionsToSaveMapButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mapModel.checkSavedMap()) {
					if(mapModel.checkConnectedContinent()) {
						/*if map satisfies all the validations, save it.*/
						mapModel.saveMapFile();
						newMap.successMessage();
					}else {
						newMap.errorNullCountry();
					}	
				}else {
					newMap.errorNullCountry();
				}
			}
		});

		/**
		*action listener for deleting the country.
		*/
		newMap.addActionsToDeleteCountryButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedcountry = newMap.getCountriesToRemove();
				newMap.clearNeighboursJList();
				newMap.countriesContentsClearedFromComboBox();
				ArrayList<NodeOfMap> continents = mapModel.getContinents();
				
				/*find the selected country and delete it*/
				for (NodeOfMap node: continents) {
					for (NodeOfCountry temp : node.getCountries()) {
						if(temp.getNameOfCountry().compareTo(selectedcountry)==0) {
							node.removeCountry(temp);
						}
					}
				}
				/*Update the JFrame contents*/
				for (NodeOfMap node: continents) {
					for (NodeOfCountry temp : node.getCountries()) {
						newMap.setDataInCountriesComboBox(temp.getNameOfCountry());
						newMap.addPossibleNeighboursInJList(temp.getNameOfCountry());
					}
				}
			}
		});

		/**
		* action listener for adding the existing continent
		*/
		newMap.addActionsToAddButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean continentExist = newMap.continentExistCheck();
				if(continentExist) {
					String cn1 = newMap.getNAmeOfCountry();
					if(cn1.compareTo("")==0) {
						newMap.errorEnterValues();
					}else {
						String selectedContinent = newMap.getSelectedContinent();
						Boolean countryExist = mapModel.checkCountryExist(cn1);
						/*if the country is unique and does not exist before*/
						if(!countryExist) {
							/*arrayList to store neighbors*/
							ArrayList<NodeOfCountry> neighbours= new ArrayList<NodeOfCountry>();
							for (Object ncountry : newMap.getListOfNeighbours()) {
								NodeOfCountry cn =  new NodeOfCountry(ncountry.toString(), null, null,null);
								neighbours.add(cn);
							}
							newMap.clearNeighboursJList();
							newMap.countriesContentsClearedFromComboBox();
							for (NodeOfMap node: mapModel.getContinents()) {
								/*get the value of selected continent and add country to it*/
								if(selectedContinent.compareTo(node.getNameOfContinent())==0) {
									int a[]= {250,250};
									NodeOfCountry newCountry = new NodeOfCountry(cn1,  neighbours , a,null);
									node.addCountry(newCountry);
								}
								/*update the contents of JFrame*/
								for (NodeOfCountry temp : node.getCountries()) {
									newMap.addPossibleNeighboursInJList(temp.getNameOfCountry());
									newMap.setDataInCountriesComboBox(temp.getNameOfCountry());

								}
							}
						}else {
							newMap.errorNullContinent();
						}
					}
				}else {
					newMap.nullContinentError();
				}
				newMap.countryFieldDisabled();
			}
		});

	}

	/**
	 * Contains actionListeners for all ExistingMapModifier buttons.
	 * @see ExistingMapModifier
	 */
	public void existingMapActions() {

		/*actionListeners for all the existing map editor buttons*/
		
		/**
		*action listener for adding the continent
		*/
		existingMapEditor.addActionsToAddContinentButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				existingMapEditor.continentFieldsEnable();
			}
		});

		/**
		*action listener for adding the country
		*/
		existingMapEditor.addActionsToAddCountryButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				existingMapEditor.countryfieldEnable();
			}
		});

		/**
		*action listener for deleting the neighbours
		*/
		existingMapEditor.addActionstoDeleteNeighboursButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				existingMapEditor.jListEnabled_1();
				String sCountrytToDeleteNeighbour = existingMapEditor.getSelectedCountryForNeighbourDeletion();
				existingMapEditor.clearNeighboursJList_1();

				/*find the selected country whose neighbors are to be deleted
				 * and display its all possible neighbors
				 */
				for (NodeOfMap node : mapModel.getContinents()){
					for (NodeOfCountry countryNode : node.getCountries()){
						if(sCountrytToDeleteNeighbour.compareTo(countryNode.getNameOfCountry())==0) {
							for (NodeOfCountry neighbour : countryNode.getNeighbourCountries() ) {
								existingMapEditor.addPossibleNeighboursToJList_1(neighbour.getNameOfCountry());
							}
						}
					}
				}
			}
		});

		/**
		*action listener for deleting the selected neighbours
		*/
		existingMapEditor.addActionsToDeleteSelectedNeighboursButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(existingMapEditor.getNeighboursList_1().isEmpty()) {
					existingMapEditor.noNeighboursSelecteError();
				}else {
					ArrayList<NodeOfCountry> neighbours_1= new ArrayList<NodeOfCountry>();
					String sCountrytToDeleteNeighbour = existingMapEditor.getSelectedCountryForNeighbourDeletion();
					for (Object ncountry : existingMapEditor.getNeighboursList_1()){
						NodeOfCountry cn =  new NodeOfCountry(ncountry.toString(), null, null, null);
						neighbours_1.add(cn);
					}
					/*remove the selected neighbors*/
					for (NodeOfMap node : mapModel.getContinents()){
						for (NodeOfCountry cNode : node.getCountries()){
							if(sCountrytToDeleteNeighbour.compareTo(cNode.getNameOfCountry())==0)
								for (NodeOfCountry neighbourNode : neighbours_1){
									cNode.removeNeighbour(neighbourNode);
								}
						}
					}
					/*delete the neighbor link from both sides*/
					for (NodeOfCountry neighbour : neighbours_1) {
						for (NodeOfMap node : mapModel.getContinents()) {
							for (NodeOfCountry countryNode : node.getCountries()) {
								if(countryNode.getNameOfCountry().compareTo(neighbour.getNameOfCountry())==0) {
									countryNode.removeNeighbour(new NodeOfCountry(sCountrytToDeleteNeighbour, null, null, null));
								}
							}
						}
					}
					existingMapEditor.successfullyDeletedNeighbours();
				}	
			}
		});

		/**
		*action listener forsaving the changes made
		*/
		existingMapEditor.addActionsToBtnComplete(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cn = existingMapEditor.getContinentName();
				String cv = existingMapEditor.getControlValue();
				if(cn.compareTo("")==0 || cv.compareTo("")==0){
					existingMapEditor.errorEnterValues();
				}else{
					int control_value= Integer.parseInt(cv);
					Boolean continentExist1 = mapModel.checkContinentExistence(cn);
					/*if the continent is not already present, add it*/
					if(!continentExist1){
						ArrayList<NodeOfCountry> countryArr = new ArrayList<NodeOfCountry>();
						mapModel.addContinents(cn, countryArr, control_value);
						existingMapEditor.continentComboBoxContentCleared();
						for(NodeOfMap i: mapModel.getContinents()){
							String continent = i.getNameOfContinent();
							/*update the JFrame*/
							existingMapEditor.setDataInContinentsComboBox(continent);
						}
					}
				}
				existingMapEditor.continentFieldDisabled();
			}
		});

		/**
		*action listener for adding the neighbours
		*/
		existingMapEditor.addActionsToAddNeighboursButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				existingMapEditor.jListEnabled();
				String sCountrytToAddNeighbour = existingMapEditor.getSelectedCountryForNeighbours();
				existingMapEditor.clearNeighboursJList();
				for (NodeOfMap node : mapModel.getContinents()){
					for (NodeOfCountry countryNode : node.getCountries()){
						/*add all the possible neighbors for a country*/
						if(sCountrytToAddNeighbour.compareTo(countryNode.getNameOfCountry())==0)
							continue;
						existingMapEditor.addPossibleNeighboursToJList(countryNode.getNameOfCountry());
					}
				}
			}
		});

		/**
		*action listener for selecting the neighbours
		*/
		existingMapEditor.addActionsToNeighbourChooseButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(existingMapEditor.getListOfNeighbours().isEmpty()) {
					existingMapEditor.noNeighboursSelecteError();
				}else {
					/*create a neighbor arrayList and add all the possible neighbors to it*/
					ArrayList<NodeOfCountry> neighbours= new ArrayList<NodeOfCountry>();
					String sCountrytToAddNeighbour = existingMapEditor.getSelectedCountryForNeighbours();
					for (Object ncountry : existingMapEditor.getListOfNeighbours()){
						NodeOfCountry cn =  new NodeOfCountry(ncountry.toString(), null, null, null);
						neighbours.add(cn);
					}
					/*get the selected country and add neighbors to it.*/
					for (NodeOfMap node : mapModel.getContinents()){
						for (NodeOfCountry cNode : node.getCountries()){
							if(sCountrytToAddNeighbour.compareTo(cNode.getNameOfCountry())==0)
								for (NodeOfCountry neighbourNode : neighbours){
									cNode.addNeighbour(neighbourNode);	
								}
						}
					}
					/*create bidirectional link between adjacent countries.*/
					for (NodeOfCountry neighbour : neighbours) {
						for (NodeOfMap node : mapModel.getContinents()) {
							for (NodeOfCountry countryNode : node.getCountries()) {
								if(countryNode.getNameOfCountry().compareTo(neighbour.getNameOfCountry())==0) {
									countryNode.addNeighbour(new NodeOfCountry(sCountrytToAddNeighbour, null, null, null));
								}
							}
						}
					}
					existingMapEditor.successfullyAddedNeighbours();
				}
			}
		});

		/**
		*action listener for deleting the continent
		*/
		existingMapEditor.addActionsToDeleteContinentButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<NodeOfMap> continents = mapModel.getContinents();
				String delete_continent = existingMapEditor.getContinentForDeletion();
				for (NodeOfMap i :continents) {
					if(i.getNameOfContinent().compareTo(delete_continent)==0) {
						continents.remove(i); // remove the continent.
						break;
					}
				}
				existingMapEditor.continentComboBoxContentCleared();
				for(NodeOfMap i: continents) {
					existingMapEditor.setDataInContinentsComboBox(i.getNameOfContinent());
				}
				/*update the JFrame*/
				existingMapEditor.clearNeighboursJList();
				existingMapEditor.continentComboBoxContentCleared();
				existingMapEditor.countriesComboBoxContentClear();
				for(NodeOfMap i: continents) {
					existingMapEditor.setDataInContinentsComboBox(i.getNameOfContinent());
					for (NodeOfCountry countryNode : i.getCountries()){
						existingMapEditor.setDataInCountriesComboBox(countryNode.getNameOfCountry());
						existingMapEditor.addPossibleNeighboursToJList(countryNode.getNameOfCountry());
					}
				}
			}
		});

		
		/**
		*action listener for saving the changes
		*/
		existingMapEditor.addActionsToSaveMapButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mapModel.checkSavedMap()) {
					if(mapModel.checkConnectedContinent()) {
						mapModel.saveToExistingMapFile(path);
						existingMapEditor.successMessage();
					}
					
				}else {
					existingMapEditor.errorNullCountry();
				}
			}
		});

		
		/**
		*action listener for deleting the country
		*/
		existingMapEditor.addActionsToDeleteCountryButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedcountry = existingMapEditor.getCountryForDeletion();
				existingMapEditor.clearNeighboursJList();
				existingMapEditor.countriesComboBoxContentClear();
				ArrayList<NodeOfMap> continents = mapModel.getContinents();
				for (NodeOfMap node: continents) {
					for (NodeOfCountry temp : node.getCountries()) {
						if(temp.getNameOfCountry().compareTo(selectedcountry)==0) {
							node.removeCountry(temp); //delete the countrynode
						}
					}
				}
				for (NodeOfMap node: continents) {
					for (NodeOfCountry temp : node.getCountries()) {
						existingMapEditor.setDataInCountriesComboBox(temp.getNameOfCountry());
						existingMapEditor.addPossibleNeighboursToJList(temp.getNameOfCountry());
					}
				}
			}
		});

		/**
		*action listener for adding the existing continent
		*/
		existingMapEditor.addActionsToAddButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean continentExist = existingMapEditor.continentExistCheck();
				if(continentExist) {
					String cn1 = existingMapEditor.getNAmeOfCountry();
					if(cn1.compareTo("")==0) {
						existingMapEditor.errorEnterValues();
					}else {
						String selectedContinent = existingMapEditor.getSelectedContinent();
						Boolean countryExist = mapModel.checkCountryExist(cn1);
						/*if the country is not already present, then add it
						 * else give error
						 */
						if(!countryExist) {
							ArrayList<NodeOfCountry> neighbours= new ArrayList<NodeOfCountry>();
							for (Object ncountry : existingMapEditor.getListOfNeighbours()) {//check
								NodeOfCountry cn =  new NodeOfCountry(ncountry.toString(), null, null, null);
								neighbours.add(cn);
							}
							existingMapEditor.clearNeighboursJList();
							existingMapEditor.countriesComboBoxContentClear();
							for (NodeOfMap node: mapModel.getContinents()) {
								/*when the appropriate continent is found, add country to it*/
								if(selectedContinent.compareTo(node.getNameOfContinent())==0) {
									int a[]= {250,250};
									NodeOfCountry newCountry = new NodeOfCountry(cn1,  neighbours , a, null);
									node.addCountry(newCountry);
								}
								for (NodeOfCountry temp : node.getCountries()) {
									existingMapEditor.addPossibleNeighboursToJList(temp.getNameOfCountry());
									existingMapEditor.setDataInCountriesComboBox(temp.getNameOfCountry());
								}
							}
						}else {
							existingMapEditor.errorCountryAlreadyExist();
						}
					}
				}else {
					existingMapEditor.errorNullContinent();
				}
				existingMapEditor.countryFieldDisable();
			}
		});
	}
}

