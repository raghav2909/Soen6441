package player;



import java.util.ArrayList;


import Model.NodeOfCountry;
import Model.NodeOfMap;
import Model.Card;
import Model.GameDriver;
import Model.GameTurnDriver;

/**
 * This class is responsible to represent the player
 * @author samansoltani
 * @version 2.0
 */
public class Player
{
    /**
     * Name of Player
     */
    private String PlayerName;
    
    /**
     * number of time card exchanged happened
     */
    private int UsedCards =1;
    
    /**
     * Player Turn
     */
    private boolean PlayerTurn = false;
    
    /**
     * List of a player countries
     */
    private ArrayList<NodeOfCountry> PlayerCountries = new ArrayList<NodeOfCountry>();
    
    /**
     * List of a player continents
     */
    private ArrayList<NodeOfMap> PlayerContinents;
    
    /**
     * Number of Armies owned by player
     */
    public int PlayerArmies;
    
    /**
     * List of Cards owned by player
     */ 
    private ArrayList<Card> Cards;
    
    /**
     * List of all continents
     */
    private ArrayList<NodeOfMap> AllContinents;
    /**
     * selected country by player
     */
	public String ChosenCountry;
	/**
	 * number of armies moved by player
	 */
	public int AMoved;
	/**
	 * state of player
	 */
	private boolean state = false;
	/**
	 * neighbours of selected country
	 */
	private NodeOfCountry SNeighbour;
	/**
	 * node of selected country
	 */
	private NodeOfCountry SelectedCountry;
	/**
	 * Strategy of Player
	 */
<<<<<<< HEAD
	private StrategyOfPlayer SOP;
    
    /**
     * Set up Player with name
     * @param n Player Name
     */
    public Player(String n)
    { 
        this.PlayerName = n;
       this.PlayerCountries = new ArrayList<NodeOfCountry>();
        this.PlayerContinents = new ArrayList<NodeOfMap>();
        this.Cards = new ArrayList<Card>();
        this.SOP = new HActionStrategy();
    }
 
    /**
     * Set up Player with name and armies
     * @param name Player Name
     * @param Narmies Player New Armies
     * @param AllContitents List of all continents 
     */
    public Player (String name, int Narmies,ArrayList<NodeOfCountry> AllContinents)
    {
       this(name);
       this.PlayerArmies = Narmies;
       this.AllContinents = new ArrayList<NodeOfMap>();
       for(NodeOfCountry country : AllContinents) {
    	   this.AddCountry(country);
       }
    }
    
    /**
=======
	private int cardsUsedCount = 1;
	
	/**
	 * number of armies player has.
	 */
	private int armiesCount;
	
	/**
	 * turn of player
	 */
	private boolean turn = false;
	
	/**
	 * ArrayList of all continents in the Map.
	 */
	private ArrayList<NodeOfMap> mapData;
	
	/**
	 * shows if player is still in game 
	 */
	private boolean lost = false;
	
	/**
	 * Stores reinforcement Country selected.
	 */
	public String countrySelected;
	
	/**
	 * Stores the armies moved in Reinforcement.
	 */
	public int armiesMoved;
	
	/**
	 * Stores Fortification Country selected .
	 */
	private NodeOfCountry countrySelect;
	
	/**
	 * Stores Neightbour Countries of country selected.
	 */
	private NodeOfCountry neighbourC;
	
	/**
	 * Strategy for the player that can be changed on runtime
	 */
	private StrategyOfPlayer strategy;
	
	/**
	 * Stores instance of GameDriver class.
	 */
	private GameDriver driver;
	
	private GameTurnDriver turnManager;
	
	/**
	 * Initialize player object with name.
	 * @param name name of player.
	 * @param nDriver object of game driver
	 */
	public Player(String name, GameDriver nDriver) {
		this.name = name;
		this.driver = nDriver;
		turnManager = driver.getGameTurnDriver();
		this.countries = new ArrayList<NodeOfCountry>();
		this.continents = new ArrayList<NodeOfMap>();
		this.cards = new ArrayList<Card>();
	}
	
	/**
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441
	 * Initialize player object with name and armies.
	 * @param name name of the player.
<<<<<<< HEAD
	 * @param armies armies of the player.
=======
	 * @param newArmies armies of the player.
	 * @param nDriver game driver object
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441
	 */
	public Player(String name, int armies) {
		this(name);
		this.PlayerArmies = armies;
		this.AllContinents = new ArrayList<NodeOfMap>();
	}
    /**
     * constructor of class
     */
   public Player() {
	}
   
	/**
<<<<<<< HEAD
     * return Player Name
     * @return Player Name
     */
    public String getPlayerName()
    {
        return this.PlayerName;
    }
   
    /**
     * Add Country to the Player Countries
     * @param Country Name of Country own by Player
     */
    public  void AddCountry (NodeOfCountry Country)
    {
        this.PlayerCountries.add(Country);
        if(Country.getOwner()!=this) {
        	Country.SetOwner(this);
        }
    }
    
    /**
     * Return List of player countries
     * @return ArrayList of Countries
     */
    public ArrayList<NodeOfCountry> getCountries()
    {
        return this.PlayerCountries;
    }
    
    /**
     * Gets the List of Countries Owned by Player
     * @return List Of Countries Name
     */
    public String[] getNameOfCountries()
    {   
    	
        String[] Names = new String [this.PlayerCountries.size()];
    
        	for(int i=0;i<Names.length;i++)
        {
            Names[i] = this.PlayerCountries.get(i).getNameOfCountry();
           
            
        }
        return Names;
    }
    
    /**
     * Shows Countries owned by Player with No Armies
     * @return List of Countries with no army
     */
    public String[] getEmptyCountriesName()
    {
        ArrayList<String> n = new ArrayList<String>();
        for(NodeOfCountry c: this.PlayerCountries)
        {
            if(c.getArmyCount()==0)
                n.add(c.getNameOfCountry());
        }
        return n.toArray(new String[n.size()]);
    }
    
    /**
     * Removes country from a Player Countries
     * @param Country removed country
     */
    public void RemoveCountry(NodeOfCountry Country)
    {
        this.PlayerCountries.remove(Country);
    }
    
    /**
     * Add a card to Player
     * @param card added card
     */
    public void AddCard (Card card)
    {
        this.Cards.add(card);
    }
    
    /**
     * Remove a card from Player
     * @param card Removed card
     */
    public void RemoveCard (Card card)
    {
        this.Cards.remove(card);
    }
    
    /**
     * Add a continent to Player continents
     * @param continent added continent
     */
    public void AddContinent(NodeOfMap continent)
    {
        this.PlayerContinents.add(continent);
    }
    
    /**
     * Remove a continent from Player continent
     * @param continent removed continent
     */
    public void RemoveContinent(NodeOfMap continent)
    {
        this.PlayerContinents.remove(continent);
    }
    
    /**
     * checking for continent own by player
     */
    public void ContinentCheck ()
    {
        for (NodeOfMap c: this.AllContinents)
        {
            System.out.println(c.getContinent());
            if(this.PlayerCountries.containsAll(c.getClst()))
            {
                AddContinent(c);
                System.out.println("Added :" + c.getContinent());
            }
        }
    }
    
    /**
     * Calculation of armies
     * @return armies count
     */
    public int CalArmy()
    {
        ContinentCheck();
        int CountCountries = this.PlayerCountries.size();
        int CountContinents = this.PlayerContinents.size();
       int CountCards = this.Cards.size();
=======
	 * Initialize player object with name and armies.
	 * @param name name of the player.
	 * @param newArmies armies of the player.
	 * @param countriesList ArrayList of all countries owned by player
	 * @param nDriver object of game driver.
	 */
	public Player(String name, int newArmies, ArrayList<NodeOfCountry> countriesList, GameDriver nDriver) {
		this(name, nDriver);
		this.armiesCount = newArmies;
		this.mapData = new ArrayList<NodeOfMap>();
		for(NodeOfCountry c: countriesList) {
			this.addCountry(c);
		}
	}
	
	/**
	 * return name of the player.
	 * @return name of player in string format.
	 */
	public String getPlayerName() {
		return this.name;
	}
	
	/**
	 * Add country to the list of countries owned by player.
	 * @param country country owned by player
	 */
	public void addCountry(NodeOfCountry country) {
		this.countries.add(country);
		if(country.getOwner()!=this) {
			country.setOwner(this);
		}
	}
	
	/**
	 * returns list of countries owned by the player.
	 * @return ArrayList containing countries.
	 */
	public ArrayList<NodeOfCountry> getCountries() {
		return this.countries ;	
	}
	
	/**
	 * Gets the list of countries owned by the player.
	 * @return list of country names
	 */
	public String[] getNameOfCountries() {
		String[] names = new String[this.countries.size()];
		for(int i=0;i<names.length;i++){
			names[i] = this.countries.get(i).getNameOfCountry();
			System.out.println(names[i]);
		}
		return names;
	}
	
	/**
	 * Gives the countries owned by the player having no armies.
	 * @return list of country names with no army.
	 */
	public String[] getEmptyCountries(){
		ArrayList<String> names = new ArrayList<String>();
		for(NodeOfCountry c : this.countries){
			if(c.getConutOfArmies()==0)
				names.add(c.getNameOfCountry());
		}
		return names.toArray(new String[names.size()]);
	}
	
	/**
	 * Removes country from list of countries owned by player.
	 * @param country Country object to be removed from list
	 */
	public void removeCountry(NodeOfCountry country) {
		this.countries.remove(country);
	}
	
	/**
	 * Add new card to list of cards player has.
	 * @param card new card to be added to list.
	 */
	public void addCard(Card card) {
		this.cards.add(card);
	}
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441

       int CountArmies= CountCountries/3;
       
       if(CountArmies<3)
       {
       	CountArmies=3;
       }

        if (CountContinents > 0)
        {
            CountContinents =0;
            for(NodeOfMap Continent : this.PlayerContinents)
            {
                CountContinents =+ Continent.getControlValue();
            }
        }
       
        CountArmies = CountContinents + CountArmies;
        System.out.println(CountArmies);
        PlayerArmies = CountArmies;
        return PlayerArmies;
    }
    
    /**
     * Add armies to player
     * @param count added armies
     */
    public void AddedArmies(int count)
    {
        this.PlayerArmies += count;
    }
    
    /**
     * Remove armies to player
     * @param count removed armies
     */
    public void RemovedArmies(int count)
    {
        this.PlayerArmies -= count;
    }
    
    /**
     * Number of Player armies
     * @return number of player armies
     */
    public int getCountArmies()
    {
       return this.PlayerArmies;
    }
    
    /**
     * Node of country by country name
     * @param NewCountry the given country
     * @return matched countries
     */
    public NodeOfCountry getCountry(String NewCountry) {
        return NodeOfCountry.getCountry(PlayerCountries,NewCountry);
    }
    
    /**
     * 
     * Set player turn to true
     */
    public void SetTurnTrue()
    {
        this.PlayerTurn =true;
    }
    
    /**
     * Set player turn to false
     */
    public void SetTurnFalse()
    {
        this.PlayerTurn =false;
    }
    
    /**
     * give the player turn
     * @return turn of player
     */
    public boolean getTurn()
    {
        return this.PlayerTurn;
    }
    
    /**
     * set the new value of PLayer armies
     * @param NewArmy number of armies
     */
    public void ArmySet(int NewArmy)
    {
    	this.PlayerArmies = NewArmy;
    }


/**
 *  reinforcement phase start method
 */
public void RPhase(){
	
<<<<<<< HEAD
	SOP.RPhase(CalArmy(),getNameOfCountries());
}

/**
 *  attack phase start method.
 */
public void APhase(){
	ArrayList<String> cl = new ArrayList<String>();
	for(NodeOfCountry c : this.PlayerCountries) {
		if(c.getArmyCount()>1) {
			for(NodeOfCountry n: c.getNeighboursCountries()) {
				if(!n.getOwner().equals(this)) {
					cl.add(c.getNameOfCountry());
					break; 
=======
	/**
	 * Add new continent to the list of continents.
	 * @param continent continent to be added to list of continent owned by player.
	 */
	public void addContinent(NodeOfMap continent) {
		this.continents.add(continent);
	}
	
	/**
	 * remove continent from the list of continent owned by the player.
	 * @param continent continent to be removed from list of continents owned by player.
	 */
	public void removeContinent(NodeOfMap continent) {
		this.continents.remove(continent);
	}
	
	/**
	 * Checks for the continents owned by the player.
	 */
	public void checkContinent() {
		for (NodeOfMap continent : this.mapData) {
			System.out.println("Inside ForLoop");
			System.out.println(continent.getNameOfContinent());
			if (this.countries.containsAll(continent.getListOfCountries())) {
				System.out.println("Inside If Stametment");
				addContinent(continent);
				System.out.println("Added" + continent.getNameOfContinent());
				break;
			}else {
				break;
			}
			
		}
	}
	
	/**
	 * Calculates the armies to be alloted to the player at each turn.
	 * @return army count
	 */
	public int getNumberOfArmies() {
		//checkContinent();
		int countriesCount = this.countries.size();
		int continentsCount = this.continents.size();
		int cardsCount = this.cards.size();
		int armyCount = countriesCount/3;
		if(armyCount<3) {
			armyCount = 3;
		}
		if (continentsCount > 0) {
			continentsCount = 0;
			for (NodeOfMap continent : this.continents){
				continentsCount =+ continent.getValue();
			}
		}
		armyCount += continentsCount;
		System.out.println(armyCount);
		
		return armyCount;
	}
	
	/**
	 *  Assign armies to the player.
	 *  @param newCount armies to be assigned.
	 */
	public void assignArmies(int newCount) {
		this.armiesCount += newCount;
	}
	
	/**
	 *  Remove Armies from player.
	 *  @param newCount armies to be removed.
	 */
	public void removeArmies(int newCount) {
		this.armiesCount -= newCount;
	}
	
	/**
	 * Gives the number of armies the player has.
	 * @return number of armies player has.
	 */
	public int getCountOfArmies() {
		return this.armiesCount;
	}
	
	/**
	 * Gives the country node for the given country name.
	 * @param newCountry country name whose country node is required.
	 * @return country node matching the string 
	 */
	public NodeOfCountry getCountry(String newCountry) {
		return NodeOfCountry.getCountry(countries, newCountry);
	}
	
	/**
	 * Sets player turn to true
	 */
	public void setTrue() {
		this.turn = true;
	}
	
	/**
	 * Sets player turn to false
	 */
	public void setFalse() {
		this.turn = false;
	}
	
	/**
	 * Gives the value of the turn of the player (True or False).
	 * @return turn of player
	 */
	public boolean getTurn() {
		return this.turn;
	}
	
	/**
	 * Sets the armies of the player to the new value.
	 * @param newArmies new value of the army to be set.
	 */
	public void setArmies(int newArmies) {
		this.armiesCount = newArmies;
	}
	
	/**
	 * This method runs the reinforcement phase
	 */
	public void reinforcementPhase(){
		System.out.print("Checkpoint 1");
		strategy.reinforcementPhase(armiesCount, getNameOfCountries());
		
	}
	
	/**
	 * This method runs attack phase.
	 */
	public void attackPhase(){
		ArrayList<String> countriesList = new ArrayList<String>();
		for(NodeOfCountry c : this.countries) {
			if(c.getConutOfArmies()>1) {
				for(NodeOfCountry n: c.getNeighbourCountries()) {
					if(n.getOwner() != null) {
						if(!n.getOwner().equals(this)) {
							countriesList.add(c.getNameOfCountry());
							break;
						}
					}
					else {
						break;
					}
					
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441
				}
			}
		}
<<<<<<< HEAD
=======
		if(countriesList.isEmpty()) {
			driver.switchPhase();
			turnManager.playTurn();
			
		}
		else {
			strategy.attackPhase(countriesList);
		}
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441
	}
<<<<<<< HEAD
	if(cl.isEmpty()) {
		GameDriver.GetInit().ChangePhase();
	}
	else {
		SOP.APhase(cl);
	}
}

/**
 *  the fortification phase start method.
 */
public void FPhase(){
	ArrayList<String> list = new ArrayList<String>();
	for(NodeOfCountry c : this.PlayerCountries) {
		if(c.getArmyCount()>1) {
			for(NodeOfCountry n: c.getNeighbours()) {
				if(n.getOwner().equals(this)) {
					list.add(c.getNameOfCountry());
					break;
=======
	
	/**
	 * This method runs the fortification phase
	 */
	public void fortificationPhase(){
		ArrayList<String> countriesList = new ArrayList<String>();
		for(NodeOfCountry c : this.countries) {
			if(c.getConutOfArmies()>1) {
				for(NodeOfCountry n: c.getNeighbourCountries()) {
					if(n.getOwner() != null) {
						if(n.getOwner().equals(this)) {
							countriesList.add(c.getNameOfCountry());
							
						}
					}else {
						break;
					}
					
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441
				}
			}
		}
<<<<<<< HEAD
=======
		if(countriesList.isEmpty()) {
			driver.nottifyObservers(driver.getGameTurnDriver().getPhase());
			driver.switchPhase();
			turnManager.playTurn();
		}
		else {
			strategy.fortificationPhase(countriesList);
		}
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441
	}
	if(list.isEmpty()) {
		GameDriver.GetInit().ChangePhase();
	}
	else {
		SOP.FPhase(list);
	}
}

/**
 * each reinforcement shifting army
 * @param country the country name to which armies are added.
 * @param armies the number of armies to be reinforced.
 * @return the army count left for the player.
 */
public int shiftArmiesOnR(String country, int armies) {
	this.ChosenCountry = country;
	this.AMoved = armies;
	getCountry(this.ChosenCountry).AddArmy(this.AMoved);
	RemovedArmies(this.AMoved);
	return this.PlayerArmies;
}

/**
 * Shifts the armies between two countries
 * @param Country the country name from which armies are moved.
 * @param Neighbour the country name to which armies are added.
 * @param armies the number of armies to be moved.
 * @return the army count left in SNeighbour country.
 */
public int getArmiesShiftedAfterF(String Country, String Neighbour, int armies){
	this.SelectedCountry = getCountry(Country);
	this.SNeighbour = getCountry(Neighbour);
	SelectedCountry.SetArmies(SelectedCountry.getArmyCount()-armies);
	SNeighbour.SetArmies(SNeighbour.getArmyCount() + armies);
	return SNeighbour.getArmyCount();
}

/**
 * Get the country to move armies.
 * @return country to move armies from.
 */
public NodeOfCountry getSelectCountry(){
	return this.SelectedCountry;
}

/**
 * Get the neighbour to move armies.
 * @return SNeighbour selected to move armies to.
 */
public NodeOfCountry getSelectNeighbour(){
	return this.SNeighbour;
}

/**
 * calculation of number of dice.
 * @param co country selected 
 * @return number of dice 
 */
public int AttackDice(String co) {
	NodeOfCountry c = getCountry(co);
	int armies = c.getArmyCount();
	if(state && armies>4) {
		armies = 3;
	}
	else if(state) {
		armies -= 1;
	}
	else if(armies>2) {
		armies = 2;
	}
	return GameDriver.GetInit().InputSetUp(1, this.PlayerName+"! Please select number of dice.",armies);
}

/**
 * It gets number of owned countries by player.
 * @return owned countries number by player.
 */
public int getPlayerCountryNumber(){
	return getCountries().size();
}

/**
 * This methods returns value of state attribute. 
 * @return value of state
 */
public boolean GetStateOfPlayer() {
	return state ;
}

/**
 * This method set value of state attribute.
 * @param value Boolean value for state attribute.
 */
public void SetStateOfPlayer(boolean value) {
	this.state = value;

}

/**
 * 
 */
public String toString() {
	return PlayerName;
}

/**
 *  player have Infantry Card or not
 * @return true if player have Infantry Card otherwise false
 */
public boolean HaveICard(){
	for (Card card: this.Cards){
		if (card.getName().equals("Infantry")){
			return true;
		}
	}
	return false;
}

/**
 *  player have Cavalry Card or not
 * @return true if player have Cavalry Card otherwise false
 */
public boolean HaveCCard(){
	for (Card card: this.Cards){
		if (card.getName().equals("Cavalry")){
			return true;
		}
	}
	return false;
}

/**
 *  player have Artillery Card or not
 * @return true if player have Artillery Card otherwise false
 */
public boolean HaveACard(){
	for (Card card: this.Cards){
		if (card.getName().equals("Artillery")){
			return true;
		}
	}
	return false;
}

/**
 * Checks if player have all three cards
 * @return false if player does not have 
 */
public boolean HaveDCard(){
	if (this.HaveICard() && this.HaveACard() && this.HaveCCard()){
		return true;
	}
	else{
		return false;
	}
}

/**
 * checks player that have three Artillery cards 
 * @return true if there are three Artillery
 */
public boolean HaveThreeACards(){
	int artillery = 0;
	for (Card card :this.Cards){
		if (card.getName().equals("Artillery")){
			artillery++;
		}
	}
	if(artillery == 3){
		return true;
	}
	else{
		return false;
	}
		
}

/**
 * checks player that have three Cavalry cards 
 * @return true if there are three Cavalry
 */
public boolean HaveThreeCCards(){
	int cavalry = 0;
	for (Card card :this.Cards){
		if (card.getName().equals("Cavalry")){
			cavalry++;
		}
	}
	if(cavalry == 3){
		return true;
	}
	else{
		return false;
	}
<<<<<<< HEAD
		
}
=======
	
	/**
	 * Set a new strategy for player
	 * @param newStrategy startegy of player
	 */
	public void setStrategy(StrategyOfPlayer newStrategy) {
		this.strategy = newStrategy;
	}
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441

/**
 * checks player that have three Infantry cards
 * @return true if there are three Infantry cards
 */
public boolean HaveThreeICards(){
	int infantry = 0;
	for (Card card :this.Cards){
		if (card.getName().equals("Infantry")){
			infantry++;
		}
	}
<<<<<<< HEAD
	if(infantry == 3){
		return true;
=======
	
	/**
	 * 
	 * @param aArmies armies available
	 * @param maxArmies maximum number of armies
	 * @param message message to be passed
	 * @return move armies
	 * @return armies to be moved.
	 */
	public int moveArmies(int aArmies, int maxArmies, String message) {
		return this.strategy.moveArmies(aArmies, maxArmies, message);
>>>>>>> branch 'master' of https://github.com/raghav2909/Soen6441
	}
	else{
		return false;
	}
		
}

/**
 * Checks if player have three cards no matter what type of cards
 * @return true 
 */
public boolean SameTypeCards(){
	if(this.HaveThreeCCards() || this.HaveThreeACards() || this.HaveThreeICards()){
		return true;
	}
	else{
		return false;
	}
}


/**
 *  this is arraylist of cards 
 * @return list of player cards.
 */
public ArrayList<Card> GetCards(){
	return this.Cards;
}

/**
 * Removes one form each type of cards
 */
public void RemoveDCards(){
	this.RemoveCard(this.GetCard("Cavalry"));
	this.RemoveCard(this.GetCard("Infantry"));
	this.RemoveCard(this.GetCard("Artillery"));
	this.AddedArmies(5*this.UsedCards++);
}

/**
 * Returns the card form list of player cards
 * @param cn card name
 * @return c 
 */
public Card GetCard(String cn){
	for (Card c : this.Cards){
		if ( c.getName().equals(cn)){
			return c;
		}
	}
	return null;
}

/**
 * Removes three cards no matter what kind of cards
 */
public void SameThreeCardsRemoved(){
	if (this.HaveThreeACards()){
		this.RemoveCard(this.GetCard("Artillery"));
		this.RemoveCard(this.GetCard("Artillery"));
		this.RemoveCard(this.GetCard("Artillery"));
	}
	else if (this.HaveThreeCCards()){
		this.RemoveCard(this.GetCard("Cavalry"));
		this.RemoveCard(this.GetCard("Cavalry"));
		this.RemoveCard(this.GetCard("Cavalry"));
	}
	else if (this.HaveThreeICards()){
		this.RemoveCard(this.GetCard("Infantry"));
		this.RemoveCard(this.GetCard("Infantry"));
		this.RemoveCard(this.GetCard("Infantry"));
	}
	this.AddedArmies(5*this.UsedCards++);
	
}

/**
 * Checks that if  the two objects are equal or not.
 */
public boolean equals(Object o) {
	if(o instanceof Player) {
		if(((Player) o).getPlayerName().equals(this.getPlayerName())){
			return true;
		}
	}
	return false;
  }

/**
 * Set a new strategy for player
 * @param ns name of strategy
 */
public void setStrategy(StrategyOfPlayer ns) {
	this.SOP = ns;
}
}

