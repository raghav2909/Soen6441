package Model;

import player.Player;
import Model.GameDriver;

/**
 * This class will phases and manages the turn.
 * @author raghavsharda
 * @version 2.0
 */
public class GameTurnDriver {

	
	/**
	 * Stores the current phasename name.
	 */
	private String phasename;
	
	/**
	 * Shows if game is over
	 */
	private boolean gamestatus = false;
	
	/**
	 * Variable stores the information if a player win a territory in attack phasename.
	 * Value is true if player win atleast one territory and false if none.
	 */
	private boolean cardwin = false;
	
	public boolean phaseCon;
	
	/**
	 * Empty constructor to create object for observer.
	 */
	public GameTurnDriver(){
	}
	
	/**
	 * Constructor to set the phasename name.
	 * @param string phasename name.
	 */
	public GameTurnDriver(String string){
		this.setPhase(string);
	}
	/**
	 * This method starts the turn from reinforcement phasename
	 * @param currentPlayer player having first turn
	 */
	public void StartPlayerTurn(Player currentPlayer) {
		currentPlayer.ArmySet(this.ongoingplayer().getCountArmies());
		currentPlayer.RPhase();
	}
	
	/**
	 * Function to switch between different phases and notify observers. Also if a player win a territory during attack phasename
	 * calls <code>issueCard()</code> method from <code>GameDriver</code>.
	 */
	public void switchphase() {
		if(this.getPhase().equals("Reinforcement")) {
			this.setPhase("Attack");
			ongoingplayer().APhase();
		}
		else if(this.getPhase().equals("Attack")) {
			if(cardwin) {
				GameDriver.GetInit().GivenCard();
				cardwin = false;
			}
			this.setPhase("Fortification");
			ongoingplayer().FPhase();
		}
		else if(this.getPhase().equals("Fortification") && !isGameOver()) {
			GameDriver.GetInit().setNextPlayer();
			this.setPhase("Reinforcement");
			ongoingplayer().RPhase();
		}
		else {
			GameDriver.GetInit().CallGameOver();		}
	}
	
	/**
	 * Returns the current player object.
	 * @return current player.
	 */
	private Player ongoingplayer() {
		return GameDriver.GetInit().GetCurrent();
	}

	/**
	 * Refreshes the phases.
	 */
	public void runningPhase() {
		if(this.getPhase().equals("Reinforcement")) {
			ongoingplayer().RPhase();
		}
		else if(this.getPhase().equals("Attack")) {
			ongoingplayer().APhase();
		}
		else if(this.getPhase().equals("Fortification")) {
			ongoingplayer().FPhase();
		}
	}

	/**
	 * Get the current phasename name.
	 * @return the phasename
	 */
	public String getPhase() {
		return this.phasename;
	}

	/**
	 * @param phasename the phasename to set
	 */
	public void setPhase(String phasename) {
		this.phasename = phasename;
	}

	/**
	 * @return the gamestatus
	 */
	public boolean isGameOver() {
		return gamestatus;
	}

	/**
	 * @param gamestatus the gamestatus to set
	 */
	public void setGameOver(boolean gamestatus) {
		this.gamestatus = gamestatus;
	}

	/**
	 * @return value of cardwin
	 */
	public boolean isWonCard() {
		return cardwin;
	}

	/**
	 * @param cardwin the cardwin to set
	 */
	public void setWonCard(boolean cardwin) {
		this.cardwin = cardwin;
	}
	
	public void playTurn() {
		if(this.phaseCon) {
			continuePhase();
		}
		else {
			switchPhase();
		}
	}
	
	
	public void setSwitchPhase () {
		this.phaseCon= false;
	}
	public void setContinuePhase() {
		this.phaseCon = true;
	}

}