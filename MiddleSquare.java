// Daniel Isaksson
// Daniel.isaksson90@gmail.com
package NapoleonsTomb;

import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MiddleSquare extends Square {
    public MiddleSquare(String suit, int rank, String imagePath, int id) {
        super(suit, rank, imagePath, id);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Board board = (Board) SwingUtilities.getWindowAncestor(MiddleSquare.this);
                board.mouseClicked(MiddleSquare.this);

                if (board.mouseClicked) {
                    // Card rank 6 allowed
                    if (board.clickedCard.getRank() == 6) {
                        board.moveCard(board.clickedCard, MiddleSquare.this);
                        board.clickedCard.setId(id);
                    }
                }
            }
        });

    } // MiddleSquare

} // class
