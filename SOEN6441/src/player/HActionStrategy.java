package player;

import java.util.ArrayList;

import Model.GameDriver;

public class HActionStrategy implements StrategyOfPlayer{

	public void FPhase(ArrayList<String> Country) {
	GameDriver.GetInit().GetControle().fortificationControls(Country.toArray(new String[Country.size()]));
	GameDriver.GetInit().SetFLiteners();
	}

	public void APhase(ArrayList<String> Countries) {
		GameDriver.GetInit().GetControle().resetControls(Countries.toArray(new String[Countries.size()]));
		GameDriver.GetInit().setAttackListeners();
	}

	public void RPhase(int armies, String[] clist) {
		GameDriver.GetInit().GetControle().reinforcementConrols(armies, clist);
		GameDriver.GetInit().SetActionListeners();

	}


	

}
