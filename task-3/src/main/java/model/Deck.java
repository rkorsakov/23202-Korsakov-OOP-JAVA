package model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private final ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        String[] ranks = {"6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        for (String suit : suits) {
            for (String rank : ranks) {
                Card card = new Card(suit, rank, false);
                cards.add(card);
            }
        }
        Collections.shuffle(cards);
    }

    public int getSize() {
        return cards.size();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card peekCard() {
        return cards.getLast();
    }

    public Card drawCard() {
        return cards.removeLast();
    }
}
