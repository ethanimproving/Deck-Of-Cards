package org.improving.poker;

import java.util.Collections;
import java.util.Comparator;

public class PokerGame {

    public static boolean isFlush(PlayerHand hand) {
        var suit = hand.getHand().get(0).getSuit();
        return hand.getHand().stream().allMatch(e -> e.getSuit().equals(suit));
    }

    public static boolean isStraight(PlayerHand playerHand){
        PokerGame.sortAscending(playerHand);

        //first put the handArray in order in a new array
        var hand = playerHand.getHand();
        var card = hand.get(0);
        boolean straight = true;

        //i starts at 1 because card is already = hand[0]
        for(int i = 1; i < hand.size(); i++){
            //if the Card at [i] == card value +1, it must be the next highest card
            if(hand.get(i).getRank().ordinal() == card.getRank().ordinal()+1){
                card = hand.get(i);
            }
            //otherwise return false because it can't be a straight without all values being 1 higher than the last
            else{
                straight = false;
                return straight;
            }
        }
        return straight;
    }

    public static void sortAscending(PlayerHand playerHand){
        Collections.sort(playerHand.getHand(), Comparator.comparing(c -> c.getRank().getValue()));
    }
}