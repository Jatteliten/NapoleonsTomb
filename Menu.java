// Daniel Isaksson
// Daniel.isaksson90@gmail.com
package NapoleonsTomb;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Menu extends JMenuBar {
    private Board board;
    /**
     * Creates a menu on the top of the panel.
     * Options:
     *   Game - New game, New fixed Game, Exit
     *   Help - About, Game rules
     */
    public Menu() {
        JMenu fileMenu = new JMenu("Game");
        JMenuItem newGameMenuItem = new JMenuItem("New Game");
        newGameMenuItem.addActionListener(e -> board.resetGame(false));

        JMenuItem newFixedGameMenuItem = new JMenuItem("New fixed Game");
        newFixedGameMenuItem.addActionListener(e -> board.resetGame(true));

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));

        fileMenu.add(newGameMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(newFixedGameMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutMenuItem = new JMenuItem("Created by");
        aboutMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(null, "Daniel Isaksson - 2023"));

        JMenuItem gameRulesMenuItem = new JMenuItem("Game rules");
        gameRulesMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(null, """
                Napoleon's tomb:

                [D] [C] [D]
                [C] [M] [C]  [6]
                [D] [C] [D]
                
                There are 52 cards in the deck.
                To win, all cards must be located in either
                    the middle slot or the slots diagonal to it.
                
                The middle slot accepts a 6 of any suit.
                After that it accepts cards in descending order.
                When an ace has been placed on the middle slot, it accepts a 6 again.
                
                The diagonal slots accepts a 7 of any suit.
                After that it accepts cards in rising order.
                When a king has been placed on a diagonal slot, the pile is complete.
                
                The cardinal direction slots accepts any card,
                    but only one card at a time.
                When a cardinal slot is empty and there are cards in the discard pile,
                    a card must be placed upon it before you draw another card.
                
                You are allowed to shuffle your discard pile into your deck once.
                
                Good luck!""", "Game rules", JOptionPane.INFORMATION_MESSAGE));
        helpMenu.add(gameRulesMenuItem);
        helpMenu.add(aboutMenuItem);

        add(fileMenu);
        add(helpMenu);

    } // Menu

    public void  setBoard(Board board){
        this.board = board;
    }

} // class

