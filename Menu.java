// Daniel Isaksson
// Daniel.isaksson90@gmail.com
package NapoleonsGrave;

import javax.swing.*;

public class Menu extends JMenuBar {
    public Menu() {
        JMenu fileMenu = new JMenu("Game");
        JMenuItem newGameMenuItem = new JMenuItem("New Game");
        newGameMenuItem.addActionListener(e -> {
            Board board = new Board();
            board.resetGame();
        });
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(newGameMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(null, "Created by\nDaniel Isaksson - 2023"));
        JMenuItem gameRulesMenuItem = new JMenuItem("Game rules");
        gameRulesMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(null, """
                Napoleon's tomb rules:

                There are 52 cards in the deck.
                To win, all cards must be located on either
                the middle square or the squares diagonal to it.
                
                The middle square accepts a 6 of any suit.
                After that it accepts cards in falling order.
                When an ace has been placed on the middle square, it accepts a 6 again.
                
                The diagonal squares accepts a 7 of any suit.
                After that it accepts cards in rising order.
                When a king has been placed on a diagonal square, the pile is complete.
                
                The cardinal direction squares accepts any card,
                but only one card at a time.
                
                Good luck!
                """, "Game rules", JOptionPane.INFORMATION_MESSAGE));
        helpMenu.add(gameRulesMenuItem);
        helpMenu.add(aboutMenuItem);

        add(fileMenu);
        add(helpMenu);
    }
}

