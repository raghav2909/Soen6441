package Model;

import player.Player;
import Model.GameDriver;

/**
 * This class will phases and manages the turn.
 * @author raghavsharda
 * @version 2.0
 */
public class GameTurnDriver {
	
	/**
	 * Stores the current phase name.
	 */
	private String phase;
	
	/**
	 * Shows if game is over
	 */
	private boolean gameOver = false;
	
	/**
	 * Variable stores the information if a player win a territory in attack phase.
	 * Value is true if player win atleast one territory and false if none.
	 */
	private boolean wonCard = false;
	
	/**
	 * Stores instance of GameDriver class.
	 */
	private GameDriver driver;
	
	/**
	 * Empty constructor to create object for observer.
	 * @param nDriver GameDriver instance.
	 */
	public GameTurnDriver(GameDriver nDriver){
		driver = nDriver;
	}
	
	/**
	 * Constructor to set the phase name.
	 * @param string Phase name.
	 * @param nDriver GameDriver instance.
	 */
	public GameTurnDriver(String string, GameDriver nDriver){
		this(nDriver);
		this.setPhase(string);
	}
	/**
	 * This method starts the turn from reinforcement phase
	 * @param currentPlayer player having first turn
	 */
	public void startFromReinforcement(Player currentPlayer) {
		currentPlayer.setArmies(this.getCurrent().getNumberOfArmies());
		currentPlayer.reinforcementPhase();
	}
	
	/**
	 * Function to switch between different phases and notify observers. Also if a player win a territory during attack phase
	 * calls <code>issueCard()</code> method from <code>GameDriver</code>.
	 */
	public void switchPhase() {
		if(!isGameOver()) {
			if(this.getPhase().equals("Reinforcement")) {
				this.setPhase("Attack");
				getCurrent().attackPhase();
			}
			else if(this.getPhase().equals("Attack")) {
				if(wonCard) {
					driver.cardIssued();
					wonCard = false;
				}
				this.setPhase("Fortification");
				getCurrent().fortificationPhase();
			}
			else if(this.getPhase().equals("Fortification")) {
				driver.setNextPlayer();
				if(!isGameOver()) {
					this.setPhase("Reinforcement");
					getCurrent().reinforcementPhase();
				}
			}
			else {
				driver.callGameOver(getCurrent().getPlayerName());
			}
		}
	}
	
	/**
	 * Returns the current player object.
	 * @return current player.
	 */
	private Player getCurrent() {
		return driver.getCurrent();
	}

	/**
	 * Refreshes the phases.
	 */
	public void continuePhase() {
		if(!isGameOver()) {
			if(this.getPhase().equals("Reinforcement")) {
				getCurrent().reinforcementPhase();
			}
			else if(this.getPhase().equals("Attack")) {
				getCurrent().attackPhase();
			}
			else if(this.getPhase().equals("Fortification")) {
				getCurrent().fortificationPhase();
			}
		}
	}

	/**
	 * Get the current phase name.
	 * @return the phase
	 */
	public String getPhase() {
		return this.phase;
	}

	/**
	 * @param phase the phase to set
	 */
	public void setPhase(String phase) {
		this.phase = phase.trim();
	}

	/**
	 * @return the gameOver
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * @param gameOver the gameOver to set
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	/**
	 * @return value of wonCard
	 */
	public boolean isWonCard() {
		return wonCard;
	}

	/**
	 * @param wonCard the wonCard to set
	 */
	public void setWonCard(boolean wonCard) {
		this.wonCard = wonCard;
	}

}