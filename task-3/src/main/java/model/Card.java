package model;

public class Card implements Comparable<Card> {
    private final String suit;
    private final String rank;
    private boolean isTrump;

    public Card(String suit, String rank, boolean isTrump) {
        this.suit = suit;
        this.rank = rank;
        this.isTrump = isTrump;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public boolean isTrump() {
        return isTrump;
    }

    private int getRankValue() {
        switch (rank) {
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":
                return 10;
            case "Jack":
                return 11;
            case "Queen":
                return 12;
            case "King":
                return 13;
            case "Ace":
                return 14;
            default:
                throw new IllegalArgumentException("Unknown rank: " + rank);
        }
    }

    public void setTrump(boolean isTrump) {
        this.isTrump = isTrump;
    }

    @Override
    public int compareTo(Card o) {
        if (this.isTrump && !o.isTrump) {
            return 1;
        } else if (!this.isTrump && o.isTrump) {
            return -1;
        }
        if (!this.suit.equals(o.suit)) {
            throw new RuntimeException("Can't do");
        }
        return Integer.compare(this.getRankValue(), o.getRankValue());
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
