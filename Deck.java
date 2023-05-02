// Daniel Isaksson
// Daniel.isaksson90@gmail.com
package NapoleonsTomb;

import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"h", "d", "c", "s"};
        for (String suit : suits) {
            for (int rank = 1; rank <= 13; rank++) {
                String imagePath = suit;
                if (rank == 11) {
                    imagePath += "j";
                } else if (rank == 12) {
                    imagePath += "q";
                } else if (rank == 13) {
                    imagePath += "k";
                } else {
                    imagePath += Integer.toString(rank);
                }
                Card card = new Card(suit, rank, imagePath, 0);
                cards.add(card);
            }
        }
    } // Deck

    public ArrayList<Card> getCards() {
        return cards;
    } //getCards

    /**
     * Draws cards from the deck.
     * If the deck is not in a fixed order, draws a random card from the deck.
     * If the deck has been gone through once, draw cards in the same order as the first time through.
     * If the deck is in a fixed order, draw from the fourth card in the deck and keep going.
     *  If the deck is fixed and there are only four cards left, draw from the top of the deck.
     */
    public Card drawCard(boolean fixed, boolean firstShuffle) {
        if (!fixed && firstShuffle) {
            int randomIndex = (int) (Math.random() * cards.size());
            return cards.remove(randomIndex);
        }
        else if (!firstShuffle){
            return cards.remove(cards.size()-1);
        }
        else {
            try {
            return cards.remove(3);
            }
            catch (IndexOutOfBoundsException error){
                return cards.remove(0);
            }
        }
    } // drawCard

    /**
     * Create a new deck from the discarded cards in the throw-pile.
     */
    public void shuffleDiscard(ArrayList<Card> discard) {
        this.cards.addAll(discard);
    } // shuffleDiscard

} // class
