// Daniel Isaksson
// Daniel.isaksson90@gmail.com
package NapoleonsGrave;

import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CornerSquare extends Square {
    public CornerSquare(String suit, int rank, String imagePath, int id) {
        super(suit, rank, imagePath, id);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Board board = (Board) SwingUtilities.getWindowAncestor(CornerSquare.this);
                board.mouseClicked(CornerSquare.this);

                if (board.mouseClicked) {
                    // Card rank 7 allowed
                    if (board.clickedCard.getRank() == 7) {
                        board.moveCard(board.clickedCard, CornerSquare.this);
                        board.clickedCard.setId(id);
                    }
                }
            }
        });

    }// CornerSquare

} // class

