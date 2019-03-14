package Model;

import java.util.ArrayList;

public interface StrategyOfPlayer {
	public void FPhase(ArrayList<String> Country);
	public void APhase(ArrayList<String> Countries);
	public void RPhase(int armies,String[] clist);

}
