// Daniel Isaksson
// Daniel.isaksson90@gmail.com
package NapoleonsTomb;

public class CornerSlot extends Slot {
    final int ALLOWED_CARD = 7;
    public CornerSlot(String suit, int rank, String imagePath, int id) {
        super(suit, rank, imagePath, id);
        addMouseListenerToSlot();

    }// CornerSlot

    @Override
    protected void handleMouseClicked(Board board) {
        // Card rank 7 allowed
        if (board.clickedCard.getRank() == ALLOWED_CARD) {
            board.moveCard(board.clickedCard, this);
            board.clickedCard.setId(getId());
            board.checkDrawAllowed();
        }

    }

} // class

