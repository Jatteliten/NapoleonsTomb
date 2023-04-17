// Daniel Isaksson
// Daniel.isaksson90@gmail.com
package NapoleonsTomb;

import java.util.ArrayList;
import java.util.Collections;

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
    }
    public Card drawCard() {
        int randomIndex = (int) (Math.random() * cards.size());
        return cards.remove(randomIndex);
    } // drawCard

    public Card drawFixedCard() {
        return cards.remove(0);
    }
    public void shuffleDiscard(ArrayList<Card> discard) {
        // add all the cards from the discard list to the deck
        this.cards.addAll(discard);
        // shuffle the deck
        Collections.shuffle(this.cards);
    } // shuffleDiscard

} // class
