package Model;

/**
 * Enumeration for the record of armies to be assigned to the players 
 * according to the number of players.
 * @author YashNarra
 */
public enum CountArmies {
	
	InitialArmiesCount{
		public int getArmiesCount(int n){
			int armiescount = 0;
			switch(n){
				case 2:
					armiescount = 40;
					break;
				case 3:
					armiescount = 35;
					break;
				case 4:
					armiescount = 30;
					break;
				case 5:
					armiescount = 25;
					break;
				case 6:
					armiescount = 20;
					break;
			}
			return armiescount;
		}
	};
	
	/**
	 * Abstract method for Risk Data enum to get number of armies given 
	 * based on number of players
	 * @param n number of players
	 * @return number of armies each player gets
	 */
	public abstract int getArmiesCount(int n);
}
