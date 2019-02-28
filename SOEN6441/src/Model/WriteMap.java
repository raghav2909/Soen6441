package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class is used to write the map file
 * @author YashNarra
 * version 1.0
 */
public class WriteMap {
	public String MapWriter(ArrayList<NodeOfMap> maplist) throws IOException{
		
		DateFormat D= new SimpleDateFormat("MM-dd");
		
		Date date= new Date();
		
		String MapPath = System.getProperty("user.dir")+"\\MapData\\Map_"+ D.format(date)+".map";
		
		FileWriter F= new FileWriter(MapPath);
		
		BufferedWriter B= new BufferedWriter(F);
		
		B.write(MapAttribute);
		
		B.write("[Continents]\r\n");
		
		for(NodeOfMap k: maplist) {
			B.write(k.getContinent()+ "="+Integer.toString(k.getControlValue()+"\r\n"));
		}
		B.write("[Territories]\r\n");
		for(NodeOfMap n: maplist) {
			for(NodeOfCountry c:n.getCountries()) {
				String Countries="";
				for(NodeOfCountry noc: c.getNeighboursCountries() ) {
					Countries=Countries+","+noc.getCountryName();
				}
				B.write(c.getCountryName()+","+Integer.toString(250)+","+Integer.toString(250)+","+n.getContinent()+Countries+"\r\n");
				}
			}
		}
	}


