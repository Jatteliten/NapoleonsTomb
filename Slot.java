// Daniel Isaksson
// Daniel.isaksson90@gmail.com
package NapoleonsTomb;

import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Slot extends Card {
    public int allowedCard;
    public Slot(String suit, int rank, String imagePath, int id) {
        super(suit, rank, imagePath, id);
    }

    public void addMouseListenerToSlot() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Board board = (Board) SwingUtilities.getWindowAncestor(Slot.this);
                board.mouseClicked(Slot.this);

                if (board.mouseClicked) {
                    handleMouseClicked(board);
                }
            }
        });
    }

    /**
     * Default behavior is empty.
     * Override this method in the Slot subclasses to implement specific behavior.
     */
    protected void handleMouseClicked(Board board) {
    }
} // class
