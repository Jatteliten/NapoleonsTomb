// Daniel Isaksson
// Daniel.isaksson90@gmail.com
package NapoleonsTomb;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class Button extends JButton {
    public Button(ActionListener actionListener, String imagePath) {
        super(new ImageIcon("images/" + imagePath +".gif"));
        setBounds(Board.STANDARD_POSITION + (Board.HORIZONTAL_OFFSET * 4),
                Board.STANDARD_POSITION + (Board.VERTICAL_OFFSET * 3) + (Board.VERTICAL_OFFSET / 2), Board.CARD_WIDTH, Board.CARD_HEIGHT);
        addActionListener(actionListener);

    } //Button

} // class
