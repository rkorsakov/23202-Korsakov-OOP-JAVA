package model;

import java.util.ArrayList;

public class Game {
    private final Deck deck;
    private final Player[] players;
    private final ArrayList<Card> table;
    private final int playersCount;
    private int currentPlayerIndex;
    private String trumpSuit;

    public Game(int playersCount) {
        if (playersCount < 2 || playersCount > 6) {
            throw new IllegalArgumentException("Players count must be between 2 and 6!");
        }
        this.deck = new Deck();
        this.playersCount = playersCount;
        this.players = new Player[playersCount];
        for (int i = 0; i < playersCount; i++) {
            players[i] = new Player();
        }
        this.table = new ArrayList<>();
        initGame();
    }

    public String getTrumpSuit() {
        return trumpSuit;
    }

    private void initGame() {
        for (int i = 0; i < 6; i++) {
            for (Player player : players) {
                Card card = deck.drawCard();
                if (card != null) {
                    player.addCard(card);
                    if (deck.getSize() == 0)
                        trumpSuit = card.getSuit();
                }
            }
        }
        if (deck.getSize() != 0)
            trumpSuit = deck.peekCard().getSuit();
        for (Player player : players) {
            for (Card card : player.getHand()) {
                card.setTrump(card.getSuit().equals(trumpSuit));
            }
        }
        for (Card card : deck.getCards()) {
            card.setTrump(card.getSuit().equals(trumpSuit));
        }
        currentPlayerIndex = determineFirstPlayer();
    }

    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    public ArrayList<Card> getTable() {
        return new ArrayList<>(table);
    }

    public void playHand(ArrayList<Card> selectedCards) {
        ArrayList<Card> playedCards = getCurrentPlayer().playHand(selectedCards);
        table.addAll(playedCards);
    }

    public void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % playersCount;
    }

    private int determineFirstPlayer() {
        int playerWithSmallestTrump = -1;
        Card smallestTrumpCard = null;
        for (int i = 0; i < players.length; i++) {
            for (Card card : players[i].getHand()) {
                if (card.isTrump() && (smallestTrumpCard == null || card.compareTo(smallestTrumpCard) < 0)) {
                    smallestTrumpCard = card;
                    playerWithSmallestTrump = i;
                }
            }
        }
        if (playerWithSmallestTrump == -1) {
            int playerWithSmallestCard = 0;
            Card smallestCard = players[0].getHand().getFirst();
            for (int i = 0; i < players.length; i++) {
                for (Card card : players[i].getHand()) {
                    if (card.compareTo(smallestCard) < 0) {
                        smallestCard = card;
                        playerWithSmallestCard = i;
                    }
                }
            }
            return playerWithSmallestCard;
        }
        return playerWithSmallestTrump;
    }
}