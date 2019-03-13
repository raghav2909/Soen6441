package Model;

public class GameTurnDriver {
	
	private boolean gamestatus = false;
	
	private String phasename;

	private boolean cardwin=false;
	
	public GameTurnDriver() {
		
	}
	public GameTurnDriver(String phase){
		this.setPhase(phase);
	}
	public void StartPlayerTurn(Player turnplayer) {
	//	turnplayer.ArmySet();
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
		return GameDriver.GetInit().getCurrentPlayer();
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
				GameDriver.GetInit().issueCard();
				cardwin=false;
			}
			this.setPhase("Fortification");
			ongoingPlayer().FPhase();
			break;
		case "f":
			GameDriver.GetInit().setNextPlayerTurn();
			this.setPhase("Reinforecement");
			ongoingPlayer().RPhase();
			break;
		case "go":
			GameDriver.GetInit().announceGameOver();	
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

	private void setPhase(String phase) {
		this.phasename=phasename;
	}
	

}