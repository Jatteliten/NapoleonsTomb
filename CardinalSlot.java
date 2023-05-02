// Daniel Isaksson
// Daniel.isaksson90@gmail.com
package NapoleonsTomb;

public class CardinalSlot extends Slot {
    final int ALLOWED_CARD = 0;
    public CardinalSlot(String suit, int rank, String imagePath, int id) {
        super(suit, rank, imagePath, id);
        addMouseListenerToSlot();

    } // CardinalSlot

    @Override
    protected void handleMouseClicked(Board board) {
        // Any card allowed
        if (board.tempCard.getRank() == ALLOWED_CARD) {
            board.moveCard(board.clickedCard, this);
            board.clickedCard.setId(getId());
            board.checkDrawAllowed();
        }
    }

} // class
