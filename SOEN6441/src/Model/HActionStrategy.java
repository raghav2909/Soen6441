package Model;

import java.util.ArrayList;

public class HActionStrategy implements StrategyOfPlayer{

	public void FPhase(ArrayList<String> Country) {
	GameDriver.GetInit().GetControle().fortificationControls(Country.toArray(new String[Country.size()]));
	GameDriver.GetInit().SetFLiteners();
	}

	public void APhase(ArrayList<String> Countries) {
		GameDriver.GetInit().GetControle().attackControls(Countries.toArray(new String[Countries.size()]));
		GameDriver.GetInit().setAttackListeners();
	}

	public void RPhase(int armies, String[] clist) {
		GameDriver.GetInit().GetControle().reinforcementConrols(armies, clist);
		GameDriver.GetInit().SetActionListeners();

	}


	

}
