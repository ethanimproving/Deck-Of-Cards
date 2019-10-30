package org.improving.poker;

import org.improving.tim.Card;
import org.improving.tim.Rank;

import java.util.*;

public class PokerGame {

    public static boolean isFlush(PlayerHand playerHand) {
        var suit = playerHand.getHand().get(0).getSuit();
        return playerHand.getHand().stream().allMatch(e -> e.getSuit().equals(suit));
    }

    public static boolean isStraight(PlayerHand playerHand) {
        var hand = playerHand.getHand();
        var minValue = hand.stream().map(c -> c.getRank().getValue()).min(Integer::compare).orElse(0);
        var maxValue = hand.stream().map(c -> c.getRank().getValue()).max(Integer::compare).orElse(0);
        var distinctCount = hand.stream().map(c -> c.getRank()).distinct().count();
        return (distinctCount == 5 && (maxValue - minValue) == 4);
    }

    public static void sortAscending(PlayerHand playerHand){
        Collections.sort(playerHand.getHand(), Comparator.comparing(c -> c.getRank().getValue()));
    }

    public static Map<Rank, Integer> mapHand(List<Card> cards) {
        Map<Rank, Integer> map = new HashMap<>();
        for (var card : cards) {
            if (map.containsKey(card.getRank())) {
                map.put(card.getRank(), map.get(card.getRank()) + 1);
            } else {
                map.put(card.getRank(),1);
            }
        }
        return map;
    }

}