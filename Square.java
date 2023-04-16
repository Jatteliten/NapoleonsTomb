// Daniel Isaksson
// Daniel.isaksson90@gmail.com
package NapoleonsGrave;

import java.awt.Graphics;

public class Square extends Card {
    public Square(String suit, int rank, String imagePath, int id) {
        super(suit, rank, imagePath, id);
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (card != null) {
            card.paint(g);
        }

    }// paintComponent

} // class
