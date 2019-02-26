package Model;
/**This class is used to assign armies to players
 * @author YashNarra
 * version 1.0
 */
public enum ArmyCount {
	
	InitalData{
		public int getarmycount(int a) {
			int ac=0;
	//switch cases for army counts.
			switch(a) {
			case 1 : ac = 0;
			break;
			case 2 : ac = 0;
			break;
			case 3 : ac = 0;
			break;
			case 4 : ac = 0;
			break;
			case 5 : ac = 0;
			
			
			}
			return ac;
		}
	};
	
	//assigning armies based on number of players.
	
	public abstract int getarmycount(int a);

}
