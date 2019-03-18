/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import player.Player;


/**
 * this class creates the cards view on main game window
 * @author raghavsharda
 *@version 1.0
 */
public class CardsConsole extends JPanel implements Observer{
	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = 91213123892139L;

	/**
	 * class constructor creating view of card option on main window of game
	 */
	public CardsConsole()
	{
		JLabel lab=  new JLabel("Cards");
		this.setLayout(new FlowLayout());
		this.add(lab);
		this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
		this.setPreferredSize(new Dimension(450,170));
		
	}
	
	/**
	 * Shows a dialog to the player to exchange the cards to get additional armies
	 * @param player current player whose turn is going on
	 */
	public void showCards(Player player){
		this.removeAll();
		/*Cards exchange Dialog Box.*/
		String cards = "";
		for (Model.Card card : player.GetCards()){ 
			cards += (card.getName()+",");
		}
		int cardExchange = JOptionPane.showConfirmDialog (null, cards,"Warning",JOptionPane.YES_OPTION);
		if(cardExchange == JOptionPane.YES_OPTION){
			exchangeCards(player);
		}
		
	}
	
	/**
	 * Removes cards from the player and assign additional armies
	 * @param player current player whose turn is going on
	 */
	public void exchangeCards(Player player){
		if (player.HaveDCard()){
			player.RemoveDCards();
		}
		else if (player.SameTypeCards()){
			player.SameThreeCardsRemoved();
		}
	}
	
	/**
	 * Observer pattern to update if there is a notification from the observable.
	 * It checks if the player has at least 3 same cards or 3 different cards or 5 cards.
	 * it asks player if he wants to exchange cards for armies, if player has 3 or more cards 
	 * if player has 5 armies it forces player to exchange card for armies.
	 */
	@Override
	public void update(Observable o, Object arg) {
		Player player;
		player = Model.GameDriver.getInstance().GetCurrent();
		if (((String) arg).equals("Cards")){
			if( player.GetCards().size()>2 && player.GetCards().size() <5){
				if(player.HaveDCard() || player.SameTypeCards()){					
					this.showCards(player);
				}
			}
			if (player.GetCards().size()==5){
				this.exchangeCards(player);
			}
		}
	}
}
