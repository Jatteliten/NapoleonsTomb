// Daniel Isaksson
// Daniel.isaksson90@gmail.com
package NapoleonsTomb;

public class SixSlot extends Slot {
    final int ALLOWED_CARD = 6;
    public SixSlot(String suit, int rank, String imagePath, int id) {
        super(suit, rank, imagePath, id);
        addMouseListenerToSlot();
    } //SixSlot

    protected void handleMouseClicked(Board board) {
        // Card rank 6 allowed
        if (board.clickedCard.getRank() == ALLOWED_CARD) {
            board.moveCard(board.clickedCard, this);
            board.clickedCard.setId(getId());
            board.checkDrawAllowed();
        }

    }

} //class