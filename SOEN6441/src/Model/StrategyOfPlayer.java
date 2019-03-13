package Model;

import java.util.ArrayList;

public interface StrategyOfPlayer {
	public void PhaseFortification(ArrayList<String> Country);
	public void PhaseAttack(ArrayList<String> Countries);
	public void PhaseReinforcement(int armies,String[] clist);

}
