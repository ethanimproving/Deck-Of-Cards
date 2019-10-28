package org.improving;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> deck = new ArrayList<>();

    public Deck() {
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

        for (String rank : ranks) {
            for (String suit : suits) {
                deck.add(new Card(rank, suit));
            }
        }
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void showDeck(List<Card> deck) {
        for (Card card : deck) {
            System.out.println(card.getRank() + " of " + card.getSuite());
        }
    }

    private final Random random = new Random();

    public void shuffle(){

//        for (Card card : deck) {
//            int index = random.nextInt(deck.size());
//            var temp = card;
//
//            card = deck.get(index);
//            deck.get(index) = temp;
//        }

        for (int i = 0; i < deck.size(); i++) {
            int index = random.nextInt(deck.size());
            Card temp = deck.get(i);
            deck.set(i, deck.get(index));
            deck.set(index, temp);
        }
    }
}
