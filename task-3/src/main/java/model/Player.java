package model;

import java.util.ArrayList;

public class Player {
    private final ArrayList<Card> hand;
    private boolean isAttacking;

    public Player() {
        hand = new ArrayList<>();
        isAttacking = false;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public ArrayList<Card> getHand() {
        return new ArrayList<>(hand);
    }

    public ArrayList<Card> playHand(ArrayList<Card> selectedCards) {
        ArrayList<Card> playedCards = new ArrayList<>();
        for (Card card : selectedCards) {
            if (hand.contains(card)) {
                hand.remove(card);
                playedCards.add(card);
            }
        }
        return playedCards;
    }

    public void setAttacking() {
        isAttacking = true;
    }

    public void setDefending() {
        isAttacking = false;
    }
}