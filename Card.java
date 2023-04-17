// Daniel Isaksson
// Daniel.isaksson90@gmail.com
package NapoleonsTomb;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class Card extends JPanel {
    public String suit;
    public int rank;
    private Image image;
    public int id;
    public Card card;

    public Card(String suit, int rank, String imagePath, int Id) {
        this.suit = suit;
        this.rank = rank;
        try {
            this.image = ImageIO.read(new File("images/" + imagePath + ".gif"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.id = Id;
    } // Card

    public String getSuit(){
        return suit;
    }

    public void setSuit(String suit){
        this.suit = suit;
    }

    public int getRank(){
        return rank;
    }

    public void setRank(int rank){
        this.rank = rank;
    }

    public Image getImage(){
        return image;
    }

    public void setImage(Image image){
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Card getCard(){
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);

    } // paintComponent

} //class
