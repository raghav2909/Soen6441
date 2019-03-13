package Model;

public class GameTurnDriver {
	private String phasename;
	
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
		if(this.getPhase().equals("Reinforcement"));
		switchp="r";
		if(this.getPhase().equals("Attack"));
		switchp="a";
		if(this.getPhase().equals("Fortification"));
		switchp="f";
		phaseswitch(switchp);
	}
	private void phaseswitch(String switchp) {
		// TODO Auto-generated method stub
		
	}
	public String getPhase() {
		
		
	}
	private Object ongoingplayer() {
		// TODO Auto-generated method stub
		return null;
	}
	private void setPhase(String phase) {
		
	}
	

}