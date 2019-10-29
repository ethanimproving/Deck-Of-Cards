package org.improving.tim;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private final List<Card> deck = new ArrayList<>();
    private final List<Card> discard = new ArrayList<>();

    public Deck() {
        for (var suit : Suit.values()) {
            for (var rank : Rank.values()) {
                deck.add(new Card(rank, suit));
            }
        }
    }

    private final Random random = new Random();

    public Card draw() {
        var randomIndex = random.nextInt(deck.size());
        var card = deck.get(randomIndex);
        discard.add(card);
        deck.remove(card);
        return card;
    }

    public List<Card> getDeck() {
        return deck;
    }
}
