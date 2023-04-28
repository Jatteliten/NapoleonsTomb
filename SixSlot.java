// Daniel Isaksson
// Daniel.isaksson90@gmail.com
package NapoleonsTomb;

import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SixSlot extends Slot {
    public SixSlot(String suit, int rank, String imagePath, int id) {
        super(suit, rank, imagePath, id);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Board board = (Board) SwingUtilities.getWindowAncestor(SixSlot.this);
                board.mouseClicked(SixSlot.this);

                if (board.mouseClicked) {
                    // Card rank 6 allowed
                    if (board.clickedCard.getRank() == 6) {
                        board.moveCard(board.clickedCard, SixSlot.this);
                        board.clickedCard.setId(id);
                        board.checkDrawAllowed();
                    }
                }
            }
        });

    } //SixSlot

} //class