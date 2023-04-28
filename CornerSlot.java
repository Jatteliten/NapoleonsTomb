// Daniel Isaksson
// Daniel.isaksson90@gmail.com
package NapoleonsTomb;

import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CornerSlot extends Slot {
    public CornerSlot(String suit, int rank, String imagePath, int id) {
        super(suit, rank, imagePath, id);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Board board = (Board) SwingUtilities.getWindowAncestor(CornerSlot.this);
                board.mouseClicked(CornerSlot.this);

                if (board.mouseClicked) {
                    // Card rank 7 allowed
                    if (board.clickedCard.getRank() == 7) {
                        board.moveCard(board.clickedCard, CornerSlot.this);
                        board.clickedCard.setId(id);
                        board.checkDrawAllowed();
                    }
                }
            }
        });

    }// CornerSlot

} // class

