package org.improving.poker;

import org.improving.tim.Deck;

public class Application {


    public static void main(String[] args) {
        var deck = new Deck();
        var hand = new PlayerHand(deck);
        hand.showHand();
        var isFlush = PokerGame.isFlush(hand);
        System.out.println(isFlush);
    }
}
