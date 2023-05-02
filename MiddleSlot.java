// Daniel Isaksson
// Daniel.isaksson90@gmail.com
package NapoleonsTomb;

public class MiddleSlot extends Slot {
    final int ALLOWED_CARD = 6;
    public MiddleSlot(String suit, int rank, String imagePath, int id) {
        super(suit, rank, imagePath, id);
        addMouseListenerToSlot();
    } // MiddleSlot

    @Override
    protected void handleMouseClicked(Board board) {
        // Card rank 6 allowed
        if (board.clickedCard.getRank() == ALLOWED_CARD) {
            board.moveCard(board.clickedCard, this);
            board.clickedCard.setId(getId());
            board.checkDrawAllowed();
        }
    }

} // class
