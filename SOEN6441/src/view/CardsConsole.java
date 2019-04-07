
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Model.Card;
import Model.GameDriver;
import player.Player;




/**
 * this class creates the cards view on main game window
 * @author raghavsharda
 *@version 2.0
 */
public class CardsConsole extends JPanel implements Observer {
	
	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = 9127819244400811786L;

	/**
	 * JButton to initiate card exchange.
	 */
	private JButton cardsToExchange;
	
	/**
	 * Creates cards view.
	 */
	public CardsConsole(){
		JLabel label = new JLabel("Cards Here.");
		this.setLayout(new FlowLayout());
		this.add(label);
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setPreferredSize(new Dimension(400,150));
	}
	
	/**
	 * Shows a dialog to the player to exchange the cards to get additional armies
	 * @param player current player whose turn is going on
	 */
	public void cardsToShow(Player player){
		this.removeAll();
		/*Cards exchange Dialog Box.*/
		String cards = "";
		for (Card card : player.getCards()){ 
			cards += (card.getName()+",");
		}
		int cardExchange = JOptionPane.showConfirmDialog (null, cards,"Warning",JOptionPane.YES_OPTION);
		if(cardExchange == JOptionPane.YES_OPTION){
			cardsToExchange(player);
		}
		
	}
	
	
	
	/**
	 * Observer pattern function for Observers to update when there is a notification from the observable.
	 * It checks if the player has atleast 3 similar cards or 3 distinct cards or 5 cards.
	 * If user has 3 or more cards it asks player if he wants to exchange cards for armies.
	 * if user has 5 armies it forces player to exchange card for armies.
	 */
	@Override
	public void update(Observable o, Object arg) {
		Player player = ((GameDriver) o).getCurrentPlayer();
		if (((String) arg).equals("Cards")){
			if( player.getCards().size()>2 && player.getCards().size() <5){
				if(player.haveDistinctCards() || player.haveThreeSameTypeCards()){					
					this.cardsToShow(player);
				}
			}
			if (player.getCards().size()==5){
				this.cardsToExchange(player);
			}
		}
	}
	/**
	 * Removes cards from the player and assign additional armies
	 * @param player current player whose turn is going on
	 */
	public void cardsToExchange(Player player){
		if (player.haveDistinctCards()){
			player.removeDistinctCards();
		}
		else if (player.haveThreeSameTypeCards()){
			player.removeSimilarThreeCards();
		}
	}
}
