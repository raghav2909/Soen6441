package Model;
/**This class is used for enumeration of number of armies based on number of players.
 * @author YashNarra
 * version 2.0
 */
public class ArmyCount {
	/**
	 * @param a number of players.
	 * @return ac Number of army count
	 */
	
		public static int getarmycount(int a) {
			int ac=0;
			if(a==2) {
				ac=40;
			}
			if(a==3) {
				ac=35;
			}
			if(a==4) {
				ac=30;
			}
			if(a==5) {
				ac=25;
			}
			return ac;
		}

}
 