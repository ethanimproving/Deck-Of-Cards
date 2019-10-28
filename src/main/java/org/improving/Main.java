package org.improving;

public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();

        deck.showDeck(deck.getDeck());
    }
}
