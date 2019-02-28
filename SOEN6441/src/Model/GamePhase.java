package Model;
/**
 * This class is used for Attack, Reinforcement, Fortification.
 * @author YashNarra
 * version 1.0
 */
public class GamePhase {
	
	/**
	 * Name of current phase of the game(reinforcement,fortification,attack).
	 * trying to add again 
	 */
	String phasename;
	
	/**
	 * Object of attack phase.
	 */
	GamePhase attack=new GamePhase("attack");
	
	/**
	 * Object of reinforcement phase.
	 */
	GamePhase reinforcement=new GamePhase("reinforcement");
	
	/**
	 * Object of fortification phase.
	 */
	GamePhase fortification=new GamePhase("fortification");
	
	/**
	 * Constructor of GamePhase used for setting the name for the phase.
	 * @param string takes the name of phase as string.
	 */
	public GamePhase(String string) {
		// TODO Auto-generated constructor stub
		this.phasename= string;
	}

	/**
	 * method for attack phase.
	 */
	public void aphase() {
		this.phasename="attack";
	}
	
	/**
	 * method for reinforcement phase.
	 */
	public void rphase() {
		this.phasename="reinforcement";
	}
	
	/**
	 * method for fortification phase.
	 */
	public void fphase() {
		this.phasename="fortification";
		
	}
	

}
