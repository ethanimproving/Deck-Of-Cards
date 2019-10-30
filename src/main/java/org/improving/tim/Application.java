package org.improving.tim;

import org.improving.poker.PlayerHand;
import org.improving.poker.JudgeHand;

public class Application {
    public static void main(String[] args) {
        var deck = new Deck();
        var hand = new PlayerHand(deck);
        JudgeHand.sortAscending(hand);
        for (int i = 0; i < 7; i++) {
            System.out.println(deck.draw().toString());
        }
    }
}
