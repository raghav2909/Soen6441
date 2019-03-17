package Model;

import player.Player;

/**
 * This class will phases and manages the turn.
 * @author raghavsharda
 * @version 2.0
 */
public class GameTurnDriver {
	/**
	 * It will store the info, if a player won the territory in attack phase.
	 * value is false if player doesn't won any territory and true if won.
	 */
	
	private boolean gamestatus = false;
	/**
	 * This will store the current phase of game.
	 */
	
	private String phasename;
	/**
	 * this will save the information if game is over
	 */

	private boolean cardwin=false;
	/**
	 * This is empty constructor to create the object 
	 */
	public GameTurnDriver() {
		
	}
	/**
	 * This constructor will set the name of phase
	 * @param phase phase name.
	 */
	public GameTurnDriver(String phase){
		this.setPhase(phase);
	}
	/**
	 * This will set the turn from reinforcement phase
	 * @param turnplayer the player which is having the first turn
	 */
	public void StartPlayerTurn(Player turnplayer) {
		turnplayer.ArmySet(this.ongoingPlayer().getCountArmies());
		turnplayer.RPhase();
	}
	
	public void switchphase() {
		String switchp;
		if(this.getPhase().equals("Reinforcement")){
		switchp="r";
		}
		else if(this.getPhase().equals("Attack")) {
		switchp="a";
		}
		else if(this.getPhase().equals("Fortification") && !GameStatus()) {
			switchp="f";
		}
		else {
			switchp="go";
		}
		phaseswitch(switchp);
	}
	private Player ongoingPlayer() {
		return GameDriver.GetInit().GetCurrent();
	}
	
	public void cardwinset(boolean cardwin) {
		this.cardwin=cardwin;
	}
	
	public boolean Cardwin() {
		return cardwin;
	}
	private void phaseswitch(String switchp) {
		// TODO Auto-generated method stub
		switch(switchp) {
		case "r":
			this.setPhase("Attack");
			ongoingPlayer().APhase();
			break;
		case "a":
			if(cardwin) {
				GameDriver.GetInit().GivenCard();
				cardwin=false;
			}
			this.setPhase("Fortification");
			ongoingPlayer().FPhase();
			break;
		case "f":
			GameDriver.GetInit().setNextPlayer();
			this.setPhase("Reinforecement");
			ongoingPlayer().RPhase();
			break;
		case "go":
			GameDriver.GetInit().CallGameOver();	
		}
	}
	public boolean GameStatus() {
		return gamestatus;
	}
	public void runningPhase() {
		if(this.getPhase().equals("Reinforcement")) {
			ongoingPlayer().RPhase();
		}
		else if(this.getPhase().equals("Attack")) {
			ongoingPlayer().APhase();
		}
		else if(this.getPhase().equals("Fortification")) {
			ongoingPlayer().FPhase();
		}
	}

	public void setGameStatus(boolean gamestatus) {
		this.gamestatus = gamestatus;
	}
	public String getPhase() {
		return this.phasename;
		
	}

	private void setPhase(String phasename) {
		this.phasename=phasename;
	}
	

}