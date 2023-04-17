// Daniel Isaksson
// Daniel.isaksson90@gmail.com
package NapoleonsTomb;

import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SixSquare extends Square {
    public SixSquare(String suit, int rank, String imagePath, int id) {
        super(suit, rank, imagePath, id);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Board board = (Board) SwingUtilities.getWindowAncestor(SixSquare.this);
                board.mouseClicked(SixSquare.this);

                if (board.mouseClicked) {
                    // Card rank 6 allowed
                    if (board.clickedCard.getRank() == 6) {
                        board.moveCard(board.clickedCard, SixSquare.this);
                        board.clickedCard.setId(id);
                    }
                }
            }
        });

    } //SixSquare

} //class