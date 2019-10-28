package org.improving.tim;

public class Card {
    private final Rank rank;
    private final Suits suit;

    public Card(Rank rank, Suits suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suits getSuits() {
        return suit;
    }

    @Override
    public String toString() {
        return "" + rank.toString() + " of " + suit.toString();
    }
}
