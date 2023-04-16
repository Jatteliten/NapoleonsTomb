// Daniel Isaksson
// Daniel.isaksson90@gmail.com
package NapoleonsGrave;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Board extends JFrame{
    static final int CARD_HEIGHT = 96;
    static final int CARD_WIDTH = 71;
    static final int STANDARD_POSITION = 25;
    static final int OFFSET = 4;
    static final int HORIZONTAL_OFFSET = CARD_WIDTH + OFFSET;
    static final int VERTICAL_OFFSET = CARD_HEIGHT + OFFSET;
    static final int squareWidth = CARD_WIDTH+2;
    static final int squareHeight = CARD_HEIGHT+2;
    public boolean mouseClicked = false;
    private ArrayList<Card> discard = new ArrayList<>();
    private ArrayList<Card> winChecker = new ArrayList<>();
    public Card clickedCard;
    public Card tempCard;
    private JLayeredPane cardPanel;
    Button reshuffleButton;
    Button deckButton;
    private boolean shuffleAllowed = true;

    public Board() {
        setTitle("Napoleon's Tomb");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(100, 100, 420, 540);
        // create a panel to hold the cards and squares
        cardPanel = new JLayeredPane();
        cardPanel.setLayout(null);
        add(cardPanel);
        // create top menu
        Menu menu = new Menu();
        setJMenuBar(menu);
        // create the deck
        Deck deck = new Deck();

        // create diagonal squares
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < 3; j+=2) {
                CornerSquare cornerSquare = new CornerSquare("null", 0, "cornerSquare", 1);
                cornerSquare.setBounds(STANDARD_POSITION + (i * 2 * HORIZONTAL_OFFSET),
                        STANDARD_POSITION + (j*VERTICAL_OFFSET), squareWidth, squareHeight);
                cardPanel.add(cornerSquare);
            }
        }
        // create cardinal squares
        for (int i = 0; i < 3; i++){
            if (i == 1) {
                for (int j = 0; j < 3; j+=2) {
                    CardinalSquare cardinalSquare = new CardinalSquare("null", 0, "cardinalSquareH", 2);
                    cardinalSquare.setBounds(STANDARD_POSITION + (j * HORIZONTAL_OFFSET), STANDARD_POSITION + (VERTICAL_OFFSET),
                            squareWidth, squareHeight);
                    cardPanel.add(cardinalSquare);
                }
            }
            else {
                CardinalSquare cardinalSquare = new CardinalSquare("null", 0, "cardinalSquareV", 2);
                cardinalSquare.setBounds(STANDARD_POSITION + HORIZONTAL_OFFSET, STANDARD_POSITION + (i * VERTICAL_OFFSET),
                        squareWidth, squareHeight);
                cardPanel.add(cardinalSquare);
            }
        }
        // create the middle square and six square
        MiddleSquare middleSquare = new MiddleSquare("null", 0, "middleSquare", 3);
        middleSquare.setBounds(STANDARD_POSITION + (HORIZONTAL_OFFSET), STANDARD_POSITION + (VERTICAL_OFFSET),
                squareWidth, squareHeight);
        SixSquare sixSquare = new SixSquare("null", 0, "sixSquare", 4);
        sixSquare.setBounds(STANDARD_POSITION + (3 * HORIZONTAL_OFFSET), STANDARD_POSITION + (VERTICAL_OFFSET),
                squareWidth, squareHeight);

        cardPanel.add(middleSquare);
        cardPanel.add(sixSquare);


        // create the reshuffleButton, which is only displayed when the deck is out of cards
        reshuffleButton = new Button(e -> {
            for(Component c : cardPanel.getComponents()) {
                // check if the component is a card
                if(c instanceof Card card) {
                    // check if the card has not been assigned to a square (if the id is 0)
                    if (card.getId() == 0 && card.getRank() != 0){
                        discard.add(card);
                        cardPanel.remove(card);
                    }
                }
            }
            shuffleAllowed = false;
            deck.shuffleDiscard(discard);
            // clear the discard list
            discard.clear();
            // remove all the cards with and id of 0 from the cardPanel
            cardPanel.remove(reshuffleButton);
            cardPanel.add(deckButton);
            cardPanel.repaint();
        }, "reshuffle"); // reshuffleButton

        // create the deck button and the functionality to display cards with it
        deckButton = new Button(e -> {
            // draw a card from the deck
            Card card = deck.drawCard();

            // set the drawn card's image to the label
            card.setBounds(STANDARD_POSITION+(HORIZONTAL_OFFSET*3),
                    STANDARD_POSITION+(VERTICAL_OFFSET*3) + (Board.VERTICAL_OFFSET / 2), CARD_WIDTH, CARD_HEIGHT);

            // add the mouse listener to the card object
            if (shuffleAllowed){
                card.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Board.this.mouseClicked(card);
                    }
                });
            }

            // add a card and set it on top of the other cards in hand, if any are present
            cardPanel.add(card, new int[1]);
            cardPanel.setComponentZOrder(card, 0);
            cardPanel.revalidate();
            cardPanel.repaint();

            if (deck.getCards().isEmpty() && shuffleAllowed) {
                // remove the deck button
                cardPanel.remove(deckButton);
                // add the reshuffle button in the same position
                reshuffleButton.setBounds(deckButton.getBounds());
                cardPanel.add(reshuffleButton);
                cardPanel.repaint();
            }
            if (deck.getCards().isEmpty() && !shuffleAllowed){
            cardPanel.remove(deckButton);
            }

        },"b1fv"); // deckButton

        // add the deck button to the panel
        cardPanel.add(deckButton);
        setVisible(true);

    } // Board

    /**
     * Sets the clicked card when a card has not been clicked yet
     * When clicking a second time - checks if card is allowed to be placed on top of another card -
     * Cards have an id depending on what square it is set upon
     * Checks what square you want to place a card on via the id and then decides if the move is allowed
     *  by comparing the ranks of the cards.
     */
    public void mouseClicked(Card card) {
        if (!mouseClicked && card.getRank() != 0) {
            tempCard = null;
            mouseClicked = true;
            clickedCard = card;
            clickedCard.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            System.out.println("click");
        }
        else {
            if (clickedCard != null) {
                clickedCard.setBorder(null);
            }

            if (card.getRank() == 0){
                tempCard = card;
            }
            else{
                tempCard = card;
                if (tempCard.getId() == 1 && clickedCard.getRank() == tempCard.getRank() + 1){
                    replaceCard(clickedCard, tempCard, card);
                }
                if (tempCard.getId() == 3 && clickedCard.getRank() == tempCard.getRank() - 1 ||
                        tempCard.getId() == 3 && clickedCard.getRank () == 6 && tempCard.getRank() == 1){
                    replaceCard(clickedCard, tempCard, card);
                }
                if (tempCard.getId() == 4 && clickedCard.getRank() == 6){
                    replaceCard(clickedCard, tempCard, card);
                }
                System.out.println("clock");

                tempCard = null;
                clickedCard = null;
                mouseClicked = false;
                checkWin();
            }
        }
    } // mouseClicked

    /**
     * Moves card position to an empty square and repaints the cardPanel to reflect the change
     */
    public void moveCard(Card card, Square square){
        // move the clicked card to the square
        card.setBounds(square.getX(), square.getY(), CARD_WIDTH, CARD_HEIGHT);
        cardPanel.setComponentZOrder(card, 0);
        // reset the clicked card and mouse clicked flags
        mouseClicked = false;
        // redraw the square and the card panel
        cardPanel.repaint();
    } // moveCard

    /**
     * Moves a card on top of another card
     * It also increments the win counter
     */
    public void replaceCard(Card originCard, Card destinationCard, Card squareLocation){
        originCard.setBounds(squareLocation.getX(), squareLocation.getY(), CARD_WIDTH, CARD_HEIGHT);
        cardPanel.setComponentZOrder(originCard, 0);
        originCard.setId(destinationCard.getId());
    }

    public void checkWin(){
        for(Component c : cardPanel.getComponents()) {
            // check if the component is a card
            if(c instanceof Card card) {
                // check if the card is assigned to a square
                if (card.getId() != 0 && card.getId() != 2){
                    winChecker.add(card);
                    if (winChecker.size() == 58){
                        System.out.println("You win!");
                    }
                }
            }
        }
        winChecker.clear();
    }

    public void resetGame() {
        // Remove all the cards from the card panel
        cardPanel.removeAll();
        // Call the Board constructor to create a new board and add it to the frame
        new Board();
        // Repaint the card panel to show the new board
        cardPanel.repaint();
    }

} // class