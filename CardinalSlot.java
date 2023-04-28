// Daniel Isaksson
// Daniel.isaksson90@gmail.com
package NapoleonsTomb;

import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CardinalSlot extends Slot {
    public CardinalSlot(String suit, int rank, String imagePath, int id) {
        super(suit, rank, imagePath, id);
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                Board board = (Board) SwingUtilities.getWindowAncestor(CardinalSlot.this);
                board.mouseClicked(CardinalSlot.this);

                if (board.mouseClicked) {
                    // Any card allowed
                    if (board.tempCard.getRank() == 0) {
                        board.moveCard(board.clickedCard, CardinalSlot.this);
                        board.clickedCard.setId(id);
                        board.checkDrawAllowed();
                    }
                }
            }
        });

    } // CardinalSlot

} // class
