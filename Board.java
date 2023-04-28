// Daniel Isaksson
// Daniel.isaksson90@gmail.com
package NapoleonsTomb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Board extends JFrame{
    static final int CARD_HEIGHT = 96;
    static final int CARD_WIDTH = 71;
    static final int STANDARD_POSITION = 25;
    static final int OFFSET = 4;
    static final int HORIZONTAL_OFFSET = CARD_WIDTH + OFFSET;
    static final int VERTICAL_OFFSET = CARD_HEIGHT + OFFSET;
    static final int WIN_AMOUNT = 57;
    private int setup = 0;
    private ArrayList<Card> discard = new ArrayList<>();
    private ArrayList<Card> winChecker = new ArrayList<>();
    public Card clickedCard;
    public Card tempCard;
    private JLayeredPane cardPanel;
    Button reshuffleButton;
    Button deckButton;
    public boolean mouseClicked;
    private boolean shuffleAllowed = true;
    private boolean drawAllowed = true;
    private boolean fixedGame;
    private boolean firstShuffle = true;

    public Board() {
        setTitle("Napoleon's Tomb");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(100, 100, 420, 540);
        // create a panel to hold the cards and slots
        cardPanel = new JLayeredPane();
        cardPanel.setLayout(null);
        cardPanel.setOpaque(true);
        cardPanel.setBackground(new Color(92, 178, 131));
        add(cardPanel);

        // create top menu
        Menu menu = new Menu();
        menu.setBoard(this);
        setJMenuBar(menu);

        // create deck
        Deck deck = new Deck();

        // create game board
        slots();
        buttons(deck);

    } // Board

    /**
     * Creates the slots where the player can place cards.
     * The slots have a different ID and a different image depending on their location.
     */
    public void slots(){
        // create diagonal slots
        String rotateImageString;
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < 3; j+=2) {
                if (i == 0 && j == 0 || i == 1 && j == 2){
                    rotateImageString = "L";
                }
                else{
                    rotateImageString = "R";
                }
                CornerSlot cornerSlot = new CornerSlot("null", 0,
                        "cornerSlot" + rotateImageString, 1);
                cornerSlot.setBounds(STANDARD_POSITION + (i * 2 * HORIZONTAL_OFFSET),
                        STANDARD_POSITION + (j*VERTICAL_OFFSET), CARD_WIDTH, CARD_HEIGHT);
                cardPanel.add(cornerSlot);
            }
        }
        // create cardinal direction slots
        for (int i = 0; i < 3; i++){
            if (i == 1) {
                for (int j = 0; j < 3; j+=2) {
                    CardinalSlot cardinalSlot = new CardinalSlot("null", 0, "cardinalSlotH", 2);
                    cardinalSlot.setBounds(STANDARD_POSITION + (j * HORIZONTAL_OFFSET), STANDARD_POSITION + (VERTICAL_OFFSET),
                            CARD_WIDTH, CARD_HEIGHT);
                    cardPanel.add(cardinalSlot);
                }
            }
            else {
                CardinalSlot cardinalSlot = new CardinalSlot("null", 0, "cardinalSlotV", 2);
                cardinalSlot.setBounds(STANDARD_POSITION + HORIZONTAL_OFFSET, STANDARD_POSITION + (i * VERTICAL_OFFSET),
                        CARD_WIDTH, CARD_HEIGHT);
                cardPanel.add(cardinalSlot);
            }
        }
        // create the middle slot and six slot
        MiddleSlot middleSlot = new MiddleSlot("null", 0, "middleSlot", 3);
        middleSlot.setBounds(STANDARD_POSITION + (HORIZONTAL_OFFSET), STANDARD_POSITION + (VERTICAL_OFFSET),
                CARD_WIDTH, CARD_HEIGHT);
        SixSlot sixSlot = new SixSlot("null", 0, "sixSlot", 4);
        sixSlot.setBounds(STANDARD_POSITION + (3 * HORIZONTAL_OFFSET), STANDARD_POSITION + (VERTICAL_OFFSET),
                CARD_WIDTH, CARD_HEIGHT);

        cardPanel.add(middleSlot);
        cardPanel.add(sixSlot);
    }

    /**
     * Creates the reshuffle button and deck button, which allows you to draw cards.
     * Deck button is replaced by the reshuffle button when all cards are drawn, which allows you to reshuffle
     *  your deck with cards currently in the discard pile.
     */
    public void buttons(Deck deck){
        // create the reshuffleButton, which is only displayed when the deck is out of cards
        reshuffleButton = new Button(e -> {
            for(Component c : cardPanel.getComponents()) {
                // check if the component is a card
                if(c instanceof Card card) {
                    // check if the card has not been assigned to a slot (if the id is 0)
                    if (card.getId() == 0 && card.getRank() != 0){
                        discard.add(card);
                        cardPanel.remove(card);
                    }
                }
            }

            deck.shuffleDiscard(discard);
            cardPanel.remove(reshuffleButton);
            if (discard.size() != 0) { // If the game is completed without shuffling once, don't add another deck button
                cardPanel.add(deckButton);
            }
            firstShuffle = false;

            discard.clear();
            cardPanel.repaint();
            shuffleAllowed = false;
            checkDrawAllowed();
        }, "reshuffle"); // reshuffleButton

        // create the deck button and the functionality to display cards with it
        deckButton = new Button(e -> {
            if (drawAllowed) {
                // draw a card from the deck
                Card card = deck.drawCard(fixedGame, firstShuffle);

                if (card.getRank() < 6 || card.getRank() > 7) {
                    // checks if four cards have been drawn. Relevant to checkDrawAllowed function
                    setup++;
                }

                // set the drawn card's image to the label
                card.setBounds(STANDARD_POSITION + (HORIZONTAL_OFFSET * 3),
                        STANDARD_POSITION + (VERTICAL_OFFSET * 3) + (Board.VERTICAL_OFFSET / 2), CARD_WIDTH, CARD_HEIGHT);

                // add the mouse listener to the card object
                if (shuffleAllowed) {
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
                if (deck.getCards().isEmpty() && !shuffleAllowed) {
                    cardPanel.remove(deckButton);
                }
            }
            checkDrawAllowed();
        },"b1fv"); // deckButton

        // add the deck button to the panel
        cardPanel.add(deckButton);
    }

    /**
     * Sets the clicked card when a card has not been clicked yet.
     * When clicking a second time, checks if card is allowed to be placed on top of another card.
     * Cards have an id depending on what slot it is set upon.
     * Checks what slot you want to place a card on via the id and then decides if the move is allowed
     *  by comparing the ranks of the cards.
     */
    public void mouseClicked(Card card) {
        if (!mouseClicked && card.getRank() != 0) {
            tempCard = null;
            mouseClicked = true;
            clickedCard = card;
            clickedCard.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
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
                tempCard = null;
                clickedCard = null;
                mouseClicked = false;
                checkWin();
            }
        }
        checkDrawAllowed();
    } // mouseClicked

    /**
     * Moves card position to an empty slot.
     */
    public void moveCard(Card card, Slot slot){
        card.setBounds(slot.getX(), slot.getY(), CARD_WIDTH, CARD_HEIGHT);
        cardPanel.setComponentZOrder(card, 0);
        mouseClicked = false;
    } // moveCard

    /**
     * Moves a card on top of another card.
     */
    public void replaceCard(Card originCard, Card destinationCard, Card slotLocation){
        originCard.setBounds(slotLocation.getX(), slotLocation.getY(), CARD_WIDTH, CARD_HEIGHT);
        cardPanel.setComponentZOrder(originCard, 0);
        originCard.setId(destinationCard.getId());
    } // replaceCard

    /**
     * Checks if the player has won.
     * When there are a total of 57 card elements that have an ID of 1 or 3, the player has won.
     * The slots carry their own ID, so they are counted too.
     * If the player has won, display "You win!" at the bottom of the window.
     * Also removes the buttons, in the case where they are still present.
     */
    public void checkWin(){
        for(Component c : cardPanel.getComponents()) {
            if(c instanceof Card card) {
                if (card.getId() == 1 || card.getId() == 3){
                    winChecker.add(card);
                    if (winChecker.size() == WIN_AMOUNT){
                        JLabel winLabel = new JLabel("You win!");
                        winLabel.setBounds(STANDARD_POSITION*5/2, STANDARD_POSITION*15, STANDARD_POSITION*8, STANDARD_POSITION+20);
                        winLabel.setFont(new Font("Serif", Font.PLAIN, 40));
                        cardPanel.add(winLabel);
                        cardPanel.remove(deckButton);
                        cardPanel.remove(reshuffleButton);
                        cardPanel.repaint();
                    }
                }
            }
        }
        winChecker.clear();
    } // checkWin

    /**
     * Checks if less than four cards have been drawn since the start of the game, if there are any empty spots
     *  in the cardinal slots and if a card is in the discard pile.
     * If all three conditions are true, then another card draw is not allowed.
     */
    public void checkDrawAllowed() {
        int placedCards = 0;
        boolean discardPile = false;
        for (Component c : cardPanel.getComponents()) {
            if (c instanceof Card card) {
                if (card.getId() == 2) {
                    placedCards++;
                }
                if (card.getId() == 0){
                    discardPile = true;
                }
            }
        }
        if (placedCards < 8 && discardPile && setup >= 4){
            drawAllowed = false;
            deckButton.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        }
        else{
            drawAllowed = true;
            deckButton.setBorder(null);
        }
    }

    /**
     * Resets the game
     * removes all elements on the current board, creates a new one and then repaints it.
     * A fixed game starts drawing cards from index 3 in the deck, which is always winnable.
     */
    public void resetGame(Boolean fixed) {
        // set game state to a new game
        fixedGame = fixed;
        mouseClicked = false;
        discard.clear();
        clickedCard = null;
        tempCard = null;
        shuffleAllowed = true;
        drawAllowed = true;
        firstShuffle = true;
        setup = 0;

        // remove all objects from the card panel
        cardPanel.removeAll();
        // create game board
        Deck deck = new Deck();
        slots();
        buttons(deck);
        cardPanel.repaint();
        checkDrawAllowed();
    } // resetGame

} // class