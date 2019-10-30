package org.improving.poker;

import org.improving.tim.Card;
import org.improving.tim.Rank;

import java.util.*;

public class JudgeHand {

    public static PokerHand judge(PlayerHand playerHand) {
        var isFlush = isFlush(playerHand);
        var isStraight = isStraight(playerHand);
        var maxValue = playerHand.getHand().stream().map(c -> c.getRank().getValue()).max(Integer::compare).orElse(0);

        if (maxValue == 14 && isFlush && isStraight) return PokerHand.RoyalFlush;
        if (isFlush && isStraight) return PokerHand.StraightFlush;
        if (isFourOfAKind(playerHand)) return PokerHand.FourOfAKind;
        if (isFullHouse(playerHand)) return PokerHand.FullHouse;
        if (isFlush) return PokerHand.Flush;
        if (isStraight) return PokerHand.Straight;
        if (isTwoPair(playerHand)) return PokerHand.TwoPair;
        if (isThreeOfAKind(playerHand)) return PokerHand.ThreeOfAKind;
        if (isPair(playerHand)) return PokerHand.Pair;

        return PokerHand.HighCard;
    }

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

    public static boolean isPair(PlayerHand playerHand) {
        var mapOfHand = mapHand(playerHand.getHand());
        var multipleCards = mapOfHand.keySet().stream().filter(k -> mapOfHand.get(k) > 1);
        return multipleCards.anyMatch(k -> mapOfHand.get(k) == 2);
    }

    public static boolean isTwoPair(PlayerHand playerHand) {
        var mapOfHand = mapHand(playerHand.getHand());
        var multipleCards = mapOfHand.keySet().stream().filter(k -> mapOfHand.get(k) > 1);
        return multipleCards.filter(k -> mapOfHand.get(k).equals(2)).count() == 2;
    }

    public static boolean isThreeOfAKind(PlayerHand playerHand) {
        Map<Rank, Integer> map = mapHand(playerHand.getHand());
        var multipleCards = map.keySet().stream().filter(k -> map.get(k) > 1);
        return multipleCards.anyMatch(k -> map.get(k) == 3);
    }

    public static boolean isFourOfAKind(PlayerHand playerHand) {
        Map<Rank, Integer> map = mapHand(playerHand.getHand());
        var multipleCards = map.keySet().stream().filter(k -> map.get(k) > 1);
        return multipleCards.anyMatch(k -> map.get(k) == 4);
    }

    public static boolean isFullHouse(PlayerHand playerHand) {
        return isThreeOfAKind(playerHand) && isPair(playerHand);
    }
}