package player;



import java.util.ArrayList;


import Model.NodeOfCountry;
import Model.NodeOfMap;
import Model.StrategyOfPlayer;
import Model.Card;
import Model.GameDriver;
import Model.HActionStrategy;

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
     * cards that used once
     */
    private int UsedCards =1;
    
    /**
     * Player Turn
     */
    private boolean PlayerTurn = false;
    
    /**
     * List of Countries owned by player
     */
    private ArrayList<NodeOfCountry> PlayerCountries = new ArrayList<NodeOfCountry>();
    
    /**
     * List of Continents owned by player
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
	private String countrySelected;
	/**
	 * number of armies moved by player
	 */
	private int armiesMoved;
	/**
	 * number of armies owned by player
	 */
	private int armiesCount;
	/**
	 * state of player
	 */
	private boolean lost = false;
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
	private StrategyOfPlayer SOP;
    
    /**
     * Set up Player with name
     * @param name Player Name
     */
    public Player(String name)
    { 
        this.PlayerName = name;
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
    public Player (String name, int Narmies,ArrayList<NodeOfMap> AllContinents)
    {
        this.PlayerName = name;
        System.out.println("Player name "+name);
       this.PlayerCountries = new ArrayList<NodeOfCountry>();
       for(NodeOfMap node: AllContinents) {
    	      this.PlayerCountries.addAll(node.getCountryList());
    	         	   
       }
        System.out.println("Player countries"+PlayerCountries.toString());
        this.PlayerContinents = new ArrayList<NodeOfMap>();
        this.Cards = new ArrayList<Card>();
        this.PlayerArmies = Narmies;
        System.out.println(Narmies);
        this.AllContinents = AllContinents;
        System.out.println(AllContinents.toString());
    }
    
    /**
	 * Initialize player object with name and armies.
	 * @param name name of the player.
	 * @param newArmies armies of the player.
	 */
	public Player(String name, int armies) {
		this.PlayerName = name;
		this.PlayerCountries = new ArrayList<NodeOfCountry>();
		this.PlayerContinents = new ArrayList<NodeOfMap>();
		this.Cards = new ArrayList<Card>();
		this.PlayerArmies = armies;
		this.AllContinents = new ArrayList<NodeOfMap>();
	}
    /**
     * constructor of class
     */
   public Player() {
	}
   
	/**
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
    }
    
    /**
     * Return List of Countries owned by PLayer
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
     * @prarm Country removed country
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
       int CountArmies = (int) Math.ceil(CountCountries/3)+CountContinents;
       if (CountCards >5) {
       	CountArmies = +5*this.UsedCards;
       	this.UsedCards++;
       }
       if(CountArmies<3)
       {
       	CountArmies=3;
       }

        if (CountContinents > 0)
        {
            CountContinents =0;
            for(NodeOfMap Continent : this.PlayerContinents)
            {
                CountContinents += Continent.getControlValue();
            }
        }
       
        CountArmies +=CountContinents;
        System.out.println(CountArmies);
        return CountArmies;
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
     * @param NewArmies number of armies
     */
    public void ArmySet(int NewArmy)
    {
    	this.PlayerArmies = NewArmy;
    }


/**
 * This method runs the reinforcement phase
 */
public void RPhase(){
	
	SOP.RPhase(PlayerArmies,getNameOfCountries());
}

/**
 * This method runs attack phase.
 */
public void APhase(){
	ArrayList<String> cl = new ArrayList<String>();
	for(NodeOfCountry c : this.PlayerCountries) {
		if(c.getArmyCount()>1) {
			for(NodeOfCountry n: c.getNeighboursCountries()) {
				if(!n.getOwner().equals(this)) {
					cl.add(c.getNameOfCountry());
					break;
				}
			}
		}
	}
	if(cl.isEmpty()) {
		GameDriver.GetInit().ChangePhase();
	}
	else {
		SOP.APhase(cl);
	}
}

/**
 * This method runs the fortification phase
 */
public void FPhase(){
	ArrayList<String> list = new ArrayList<String>();
	for(NodeOfCountry c : this.PlayerCountries) {
		if(c.getArmyCount()>1) {
			for(NodeOfCountry n: c.getNeighbours()) {
				if(n.getOwner().equals(this)) {
					list.add(c.getNameOfCountry());
					break;
				}
			}
		}
	}
	if(list.isEmpty()) {
		GameDriver.GetInit().ChangePhase();
	}
	else {
		SOP.FPhase(list);
	}
}

/**
 * Shifts the armies of the player on each reinforcement.
 * @param country the country name to which armies are added.
 * @param armies the number of armies to be reinforced.
 * @return the army count left for the player.
 */
public int shiftArmiesOnReinforcement(String country, int armies) {
	this.countrySelected = country;
	this.armiesMoved = armies;
	getCountry(this.countrySelected).AddArmy(this.armiesMoved);
	RemovedArmies(this.armiesMoved);
	return this.armiesCount;
}

/**
 * Shifts the armies from one country to another.
 * @param sCountry the country name from which armies are moved.
 * @param sNeighbour the country name to which armies are added.
 * @param selectedArmies the number of armies to be moved.
 * @return the army count left in SNeighbour country.
 */
public int getArmiesShiftedAfterFortification(String Country, String Neighbour, int armies){
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
 * Get the neighbour selected to move armies to.
 * @return SNeighbour selected to move armies to.
 */
public NodeOfCountry getSelectNeighbour(){
	return this.SNeighbour;
}

/**
 * This method calculate the number of dice a player can roo during attack phase
 * @param country country selected as attacking or attacked
 * @return number of dice to roll
 */
public int AttackDice(String co) {
	NodeOfCountry c = getCountry(co);
	int armies = c.getArmyCount();
	if(getTurn() && armies>4) {
		armies = 3;
	}
	else if(getTurn()) {
		armies -= 1;
	}
	else if(armies>2) {
		armies = 2;
	}
	return GameDriver.GetInit().InputSetUp(1, this.PlayerName+"! Please select number of dice to roll.",armies);
}

/**
 * It gets number of owned countries by player.
 * @return owned countries number by player.
 */
public int getPlayerCountryCount(){
	return getCountries().size();
}

/**
 * This methods returns value of lost attribute. 
 * @return value of lost
 */
public boolean GetStateOfPlayer() {
	return lost ;
}

/**
 * This method set value of lost attribute.
 * @param value Boolean value for lost attribute.
 */
public void SetStateOfPlayer(boolean value) {
	this.lost = value;

}

/**
 * 
 */
public String toString() {
	return PlayerName;
}

/**
 * Checks if player have Infantry Card
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
 * Checks if player have Cavalry Card
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
 * Checks if player have Artillery Card
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
 * Checks if player have Infantry, Artillery and Cavalry Cards
 * @return false if player does not have Infantry, Artillery and Cavalry Cards otherwise true
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
 * Checks if player have three Artillery cards
 * @return true if player have three Artillery cards otherwise false
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
 * Checks if player have three Cavalry cards
 * @return true if player have three Cavalry cards otherwise false
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
		
}

/**
 * Checks if player have three Infantry cards
 * @return true if player have three Infantry Cards otherwise false
 */
public boolean HaveThreeICards(){
	int infantry = 0;
	for (Card card :this.Cards){
		if (card.getName().equals("Infantry")){
			infantry++;
		}
	}
	if(infantry == 3){
		return true;
	}
	else{
		return false;
	}
		
}

/**
 * Checks if player have either three Cavalry, Artillery or Infantry cards
 * @return true if player have either three Cavalry, Artillery or Infantry cards otherwise false
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
 * @return card list the player has.
 */
public ArrayList<Card> GetCards(){
	return this.Cards;
}

/**
 * Removes one Infantry, Artillery and Cavalry cards
 */
public void RemoveDistinctCards(){
	this.RemoveCard(this.GetCard("Cavalry"));
	this.RemoveCard(this.GetCard("Infantry"));
	this.RemoveCard(this.GetCard("Artillery"));
	this.AddedArmies(5*this.UsedCards++);
}

/**
 * Returns the card from player cardlist
 * @param cardname name of the card
 * @return card with cardname equals to parameter
 */
public Card GetCard(String cardname){
	for (Card c : this.Cards){
		if ( c.getName().equals(cardname)){
			return c;
		}
	}
	return null;
}

/**
 * Removes either of three Infantry or Artillery or Cavalry cards
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
 */
public void setStrategy(StrategyOfPlayer ns) {
	this.SOP = ns;
}
}

