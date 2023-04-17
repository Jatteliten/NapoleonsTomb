// Daniel Isaksson
// Daniel.isaksson90@gmail.com
package NapoleonsTomb;

import java.awt.Graphics;

public class Square extends Card {
    public Square(String suit, int rank, String imagePath, int id) {
        super(suit, rank, imagePath, id);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (card != null) {
            card.paint(g);
        }

    }// paintComponent

} // class
