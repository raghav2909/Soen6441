package view;

import static java.lang.System.exit;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import game.messages.MessageWindow;

/**
 * This class provides a menu to go for tournament mode by providing the info asked
 * @author: Gursharan
 * for Tournament mode maximum 4 players can play
*/

public class TournamentModeMenu extends JFrame {
         private MessageWindow messageWindow;
         private int w,h;
         public JLabel mapLabel1 = new JLabel("No Map Selected");
         public JLabel mapLabel2 = new JLabel("No Map Selected");
         public JLabel mapLabel3 = new JLabel("No Map Selected");
         public JLabel mapLabel4 = new JLabel("No Map Selected");
         public JLabel player = new JLabel("Number of Players");
         public String map1fromFile="";
         public String map2fromFile="";
         public String map3fromFile="";
         public String map4fromFile="";

         public String[] str1 = {"Nothing", "Aggressive", "Benevolent", "Random", "Cheater"};
//         public String[] str2 = {"Nothing", "Aggressive", "Benevolent", "Random", "Cheater"};
//         public String[] str3 = {"Nothing", "Aggressive", "Benevolent", "Random", "Cheater"};
//         public String[] str4 = {"Nothing", "Aggressive", "Benevolent", "Random", "Cheater"};
         public JComboBox tournamentstr1 = new JComboBox(str1);
         public JComboBox tournamentstr2 = new JComboBox(str1);
         public JComboBox tournamentstr3 = new JComboBox(str1);
         public JComboBox tournamentstr4 = new JComboBox(str1);
         public JSpinner numberofplayers = new JSpinner(new SpinnerNumberModel(2, 2, 5, 1));
         public Integer[] ListoftournamentGames = {1, 2, 3, 4};
         public Integer[] ListoftournamentTurns = {10, 15, 20, 25, 30, 35, 40};
         public JComboBox GamesInTournament = new JComboBox(ListoftournamentGames);
         public JComboBox TurnsInTournament = new JComboBox(ListoftournamentTurns);
         
         /**
          * class constructor displaying the frame
          * @param messageWindow
          */
         public TournamentModeMenu(MessageWindow messageWindow) {
                super("Tournament Mode Game");
                this.messageWindow = messageWindow;
                this.w = 1300;
                this.h = 500;
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(this.w, this.h);
                setResizable(false);
                setAlwaysOnTop(false);
                this.setLayout(new GridBagLayout());
                GridBagConstraints grid_bag_cons = new GridBagConstraints();
                grid_bag_cons.insets = new Insets(10, 0, 10, 0);
                grid_bag_cons.gridx = 0;
                grid_bag_cons.gridy = 0;
                getContentPane().add(playernumber(), grid_bag_cons);
                grid_bag_cons.gridx = 0;
                grid_bag_cons.gridy = 1;
                getContentPane().add(mapSelector(), grid_bag_cons);
                grid_bag_cons.gridx = 0;
                grid_bag_cons.gridy = 2;
                getContentPane().add(player_strategy_selector(), grid_bag_cons);
                grid_bag_cons.gridx = 0;
                grid_bag_cons.gridy = 3;
              getContentPane().add(numberSelector(), grid_bag_cons);
                grid_bag_cons.gridx = 0;
                grid_bag_cons.gridy = 4;
               getContentPane().add(initializeGame(), grid_bag_cons);

                setLocationRelativeTo(null);
                setVisible(true);
            }
         
         /**
          * returns the panel to select number of players for game
          */
         private JPanel playernumber() {
        	 JPanel playernumberpanel = new JPanel();
	
	
        	 TitledBorder title = BorderFactory.createTitledBorder("Select Player count");
        	 playernumberpanel.setBorder(title);
	
	playernumberpanel.setLayout(new GridBagLayout());
	GridBagConstraints grid_bag = new GridBagConstraints();
    grid_bag.insets = new Insets(5, 15, 5, 15);
    
    playernumberpanel.add(player, grid_bag);
    grid_bag.gridx= 1;
    grid_bag.gridy=0;
    
    playernumberpanel.add(numberofplayers,grid_bag);
    grid_bag.gridx= 2;
    grid_bag.gridy=0;
	
	return playernumberpanel;
}

        /**
         * allows selection of maps in tournament mode . At max 4 maps can be selected
         */
private JPanel mapSelector() {
    JPanel mapSelectionPanel = new JPanel();
    JPanel mapSelection = new JPanel();

    TitledBorder title = BorderFactory.createTitledBorder("First map selection to make: Select here");
    mapSelectionPanel.setBorder(title);

    mapSelectionPanel.setLayout(new GridBagLayout());
    GridBagConstraints grid_bag_cons = new GridBagConstraints();
    grid_bag_cons.insets = new Insets(5, 15, 5, 15);

    JButton map1 = new JButton(("Select Map 1"));
    JButton map2 = new JButton(("Select Map 2"));
    JButton map3 = new JButton(("Select Map 3"));
    JButton map4 = new JButton(("Select Map 4"));
    grid_bag_cons.gridx = 1;
    grid_bag_cons.gridy = 1;
    mapSelectionPanel.add(map1, grid_bag_cons);
    grid_bag_cons.gridx = 2;
    grid_bag_cons.gridy = 1;
    mapSelectionPanel.add(map2, grid_bag_cons);
    grid_bag_cons.gridx = 3;
    grid_bag_cons.gridy = 1;
    mapSelectionPanel.add(map3, grid_bag_cons);
    grid_bag_cons.gridx = 4;
    grid_bag_cons.gridy = 1;
    mapSelectionPanel.add(map4, grid_bag_cons);
    grid_bag_cons.gridx = 5;
    grid_bag_cons.gridy = 1;


    //  Labels on mapSelectionPanel1
    grid_bag_cons.gridx = 1;
    grid_bag_cons.gridy = 2;

    mapSelectionPanel.add(mapLabel1, grid_bag_cons);
    grid_bag_cons.gridx = 2;
    grid_bag_cons.gridy = 2;
    mapSelectionPanel.add(mapLabel2, grid_bag_cons);
    grid_bag_cons.gridx = 3;
    grid_bag_cons.gridy = 2;
    mapSelectionPanel.add(mapLabel3, grid_bag_cons);
    grid_bag_cons.gridx = 4;
    grid_bag_cons.gridy = 2;
    mapSelectionPanel.add(mapLabel4, grid_bag_cons);
       mapSelection.add(mapSelectionPanel);
    return mapSelection;
}
/**
 * This method returns player strategies and create separate panel for it
 */
        private JPanel player_strategy_selector() {
        JPanel strategy_selector_panel = new JPanel();
        JPanel strategyButton= new JPanel();

    TitledBorder title = BorderFactory.createTitledBorder("Select strategies for each player.");
    strategy_selector_panel.setBorder(title);


    strategy_selector_panel.setLayout(new GridBagLayout());
    GridBagConstraints grid_bag_cons = new GridBagConstraints();
    grid_bag_cons.insets = new Insets(5, 15, 5, 15);

    tournamentstr1.setSelectedIndex(1);
    tournamentstr2.setSelectedIndex(2);
    tournamentstr3.setSelectedIndex(3);
    tournamentstr4.setSelectedIndex(4);

    grid_bag_cons.gridx = 1;
    grid_bag_cons.gridy = 1;
    strategy_selector_panel.add(new JLabel("Player1 strategy:"), grid_bag_cons);
    grid_bag_cons.gridx = 1;
    grid_bag_cons.gridy = 2;
    strategy_selector_panel.add(tournamentstr1, grid_bag_cons);
    grid_bag_cons.gridx = 2;
    grid_bag_cons.gridy = 1;
    strategy_selector_panel.add(new JLabel("Player2 strategy:"), grid_bag_cons);
    grid_bag_cons.gridx = 2;
    grid_bag_cons.gridy = 2;
    strategy_selector_panel.add(tournamentstr2, grid_bag_cons);
    grid_bag_cons.gridx = 3;
    grid_bag_cons.gridy = 1;
   strategy_selector_panel.add(new JLabel("Player3 strategy:"), grid_bag_cons);
    grid_bag_cons.gridx = 3;
    grid_bag_cons.gridy = 2;
   strategy_selector_panel.add(tournamentstr3, grid_bag_cons);
    grid_bag_cons.gridx = 4;
    grid_bag_cons.gridy = 1;
   strategy_selector_panel.add(new JLabel("Player4 strategy:"), grid_bag_cons);
    grid_bag_cons.gridx = 4;
    grid_bag_cons.gridy = 2;
   strategy_selector_panel.add(tournamentstr4, grid_bag_cons);

   strategyButton.add(strategy_selector_panel);
   return strategyButton;
        }
    
        /**
         * This method set settings like number of maps, number of games to play for each game in Tournament mode
         */
        private JPanel numberSelector() {
        JPanel numberSelectorPanel = new JPanel();
        JPanel numberSelectorButtons = new JPanel();

        TitledBorder title = BorderFactory.createTitledBorder("Set following settings for Game: ");
        numberSelectorPanel.setBorder(title);

        numberSelectorPanel.setLayout(new GridBagLayout());
        GridBagConstraints grid_bag_cons = new GridBagConstraints();
        grid_bag_cons.insets = new Insets(5, 15, 5, 15);

        GamesInTournament.setSelectedIndex(0);
        TurnsInTournament.setSelectedIndex(0);


        grid_bag_cons.gridx = 1;
        grid_bag_cons.gridy = 1;
        numberSelectorPanel.add(new JLabel("Enter Number of games to play on each map."),grid_bag_cons);
        grid_bag_cons.gridx = 1;
        grid_bag_cons.gridy = 2;
        numberSelectorPanel.add(GamesInTournament,grid_bag_cons);
        grid_bag_cons.gridx = 2;
        grid_bag_cons.gridy = 1;
        numberSelectorPanel.add(new JLabel("Maximum number of turns"),grid_bag_cons);
        grid_bag_cons.gridx = 2;
        grid_bag_cons.gridy = 2;
        numberSelectorPanel.add(TurnsInTournament,grid_bag_cons);

        numberSelectorButtons.add(numberSelectorPanel);

        return numberSelectorButtons;
    }
        /**
         * starts the tournament mode
         * @return the jpanel
         */
         private JPanel initializeGame() {
                JPanel initializeGamePanel = new JPanel();
                JPanel initializeGameButtons = new JPanel();

                TitledBorder title = BorderFactory.createTitledBorder("Start the game");
                initializeGamePanel.setBorder(title);

                initializeGamePanel.setLayout(new GridBagLayout());
                GridBagConstraints grid_bag_cons = new GridBagConstraints();
                grid_bag_cons.insets = new Insets(5, 15, 5, 15);

                JButton start = new JButton("Start the game");

                grid_bag_cons.gridx = 0;
                grid_bag_cons.gridy = 0;
                initializeGamePanel.add(start,grid_bag_cons);

                initializeGameButtons.add(initializeGamePanel);
                return initializeGameButtons;
         }
}