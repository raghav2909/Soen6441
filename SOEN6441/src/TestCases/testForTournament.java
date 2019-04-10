package TestCases;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import Model.Tournament;
import controllers.TheMainController;

public class testForTournament {
	
	Tournament tour ;
	
String winner= "draw";
	@Test
	public void testTournament() {
		String[][] myPs = {{"Player1","aggressive"},{"Player2","cheater"},{"Player3","benevolent"}};
		String[] maps = {"C:\\Users\\saman\\git\\SOEN6441final\\SOEN6441\\Map_Data\\map\\World2005.map","C:\\Users\\saman\\git\\SOEN6441final\\SOEN6441\\Map_Data\\map\\World2005.bmp"};
		//String[][] s = new String[maps.length][3];
		Tournament s = new Tournament(1,maps, myPs, 10, TheMainController.getInitialize());
		TheMainController.getInitialize().setGameMode(s);
		s.start();
		System.out.println(Arrays.toString(s.winners));
		//String x= s.lastWinner();
		//System.out.println(x);
		assertEquals("draw",winner);
	}

}
