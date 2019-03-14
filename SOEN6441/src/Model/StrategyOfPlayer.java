package Model;

import java.util.ArrayList;
//Strstegy of player trying to commit.
public interface StrategyOfPlayer {
	public void FPhase(ArrayList<String> Country);
	public void APhase(ArrayList<String> Countries);
	public void RPhase(int armies,String[] clist);

}
