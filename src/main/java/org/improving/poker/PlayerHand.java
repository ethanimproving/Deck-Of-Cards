package org.improving.poker;

import org.improving.tim.Card;
import org.improving.tim.Deck;

import java.util.ArrayList;
import java.util.List;

public class PlayerHand {
    private List<Card> hand = new ArrayList<>();

    public PlayerHand(Deck deck) {
        for (int i = 0; i < 5; i++) {
            hand.add(deck.draw());
        }
    }

    public void showHand() {
        for (var card : hand) {
            System.out.println(card.toString());
        }
    }

    public List<Card> getHand() {
        return hand;
    }
}
