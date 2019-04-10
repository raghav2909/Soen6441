package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * This class is used to write the map file
 * @author YashNarra
 * @version 2.0
 */

public class WriteMap {
	DateFormat D= new SimpleDateFormat("yyyy-MM-dd-HH-mm");
	
	Date date= new Date(0);
	String MapPath = System.getProperty("user.dir")+"\\Map_Data\\map-"+ D.format(date)+".map";
	
	public void MapWriter(ArrayList<NodeOfMap> maplist) throws IOException{
		
		String MapAttribute="[Map]\r\n" + 
				"author=Iceworm72\r\n" + 
				"image=001_I72_Ghtroc 720.bmp\r\n" + 
				"wrap=no\r\n" + 
				"scroll=vertical\r\n" + 
				"warn=no\r\n\r\n";

<<<<<<< HEAD
		String MapPath = System.getProperty("user.dir")+"\\MapData\\Map_"+ D.format(date)+".map";
=======
	/**
	 * This function implements the file write operation
	 * @param map receives the NodeOfMap with all the map details.
	 */
	public void writeMap(ArrayList<NodeOfMap> map) {
		
		/*Date object for writing the date and time of creation map file*/
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		
		/*Stores the Date Object.*/ 
		Date date = new Date();
		
		/*FILENAME Stores the file name of the newly created map file.*/
		FILENAME = System.getProperty("user.dir") + "\\SOEN6441\\Map_Data\\map\\map-"+ dateFormat.format(date) + ".map";
		
		/*Stores BufferedWriter object.*/
		BufferedWriter bw = null;
		
		/*Stores FileWriter object.*/
		FileWriter fw = null;
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441

		
		FileWriter F= new FileWriter(MapPath);
		
		BufferedWriter B= new BufferedWriter(F);
		
		B.write(MapAttribute);
		
		B.write("[Continents]\r\n");
		for(NodeOfMap k: maplist) {
			B.write(k.getContinent()+ "="+Integer.toString(k.getControlValue())+"\r\n");
		}
		B.write("[Territories]\r\n");
		for(NodeOfMap n: maplist) {
			for(NodeOfCountry c:n.getCountries()) {
				String Countries="";
				for(NodeOfCountry noc: c.getNeighboursCountries() ) {
					Countries=Countries+","+noc.getNameOfCountry();
				}
				B.write(c.getNameOfCountry()+","+Integer.toString(250)+","+Integer.toString(250)+","+n.getContinent()+Countries+"\r\n");
				}
			}
		B.close();
		F.close();
		}
	
	public void ExistingMapWriter(ArrayList<NodeOfMap> maplist,String Address) throws IOException {
		Address=MapPath;
		
		String MapAttribute="";
		
		FileWriter F= new FileWriter(MapPath);
		
		BufferedWriter B= new BufferedWriter(F);
		
		B.write(MapAttribute);
		B.write("");
		B.write("[Continents]\r\n");
		
		for(NodeOfMap k: maplist) {
			B.write(k.getContinent()+ "="+Integer.toString(k.getControlValue())+"\r\n");
		}
		B.write("[Territories]\r\n");
		for(NodeOfMap n: maplist) {
			for(NodeOfCountry c:n.getCountries()) {
				String Countries="";
				for(NodeOfCountry noc: c.getNeighboursCountries() ) {
					Countries=Countries+","+noc.getNameOfCountry();
				}
				B.write(c.getNameOfCountry()+","+Integer.toString(250)+","+Integer.toString(250)+","+n.getContinent()+Countries+"\r\n");
				}
			}
		B.close();
		F.close();
	}
	
	public String getMapFilePath() {
		return MapPath;
	}
	}


