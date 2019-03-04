package Model;

import controllers.the_main_controller;
import view.ControlsConsole;

/**
 * This class is used for Attack, Reinforcement, Fortification.
 * 
 * @author YashNarra version 1.0
 */
public class GamePhase {

	/**
	 * Name of current phase of the game(reinforcement,fortification,attack). trying
	 * to add again
	 */
	the_main_controller tmc;
	String phasename;

	/**
	 * Object of attack phase.
	 */
	public static GamePhase attack = new GamePhase("attack");

	/**
	 * Object of reinforcement phase.
	 */
	public static GamePhase reinforcement = new GamePhase("reinforcement");

	/**
	 * Object of fortification phase.
	 */
	ControlsConsole cls;
	public static GamePhase fortification = new GamePhase("fortification");

	/**
	 * Constructor of GamePhase used for setting the name for the phase.
	 * 
	 * @param string takes the name of phase as string.
	 */
	public GamePhase(String phaseip) {
		// TODO Auto-generated constructor stub
		this.phasename = phaseip;

	}

	/**
	 * method for attack phase.
	 */
	public void aphase() {
		this.phasename = "attack";
		GameDriver.getInstance().ChangePhase();
	}

	/**
	 * method for reinforcement phase.
	 */
	public void rphase() {
		this.phasename = "reinforcement";
		// this.plc = new Player() ;
		// String[] X= {"CandaW","hello"};
		// System.out.println(plc.getCountArmies());
//		cls = new ControlsConsole();
		// cls.reinforcementConrols(GameDriver.play.getCountArmies(),GameDriver.play.getNameOfCountries());
		GameDriver.getInstance().getControl().reinforcementConrols(GameDriver.getInstance().play.getCountArmies(),
				GameDriver.getInstance().play.getNameOfCountries());
		  GameDriver.getInstance().setControlListenerForF();
		

	}

	// trying to commit
	/**
	 * method for fortification phase.
	 */
	public void fphase() {
		this.phasename = "fortification";
		// GameDriver.getInstance().getControl().fortificationConrols(GameDriver.getInstance().getArmyCount(),GameDriver.getInstance().getPlayerCountriesName());
		GameDriver.getInstance().ChangePhase();
	}

	public boolean equals(Object o) {
		if (o instanceof GamePhase && ((GamePhase) o).phasename == this.phasename) {
			return true;
		}
		return false;
	}

}
