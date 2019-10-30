package org.improving.poker;

import org.improving.tim.Card;
import org.improving.tim.Rank;

import java.util.*;
import java.util.stream.Stream;

public class JudgeHand {

    public static PokerHand judge(PlayerHand playerHand) {
        var isFlush = isFlush(playerHand);
        var isStraight = isStraight(playerHand);
        var maxValue = getMaxValueOfHand(playerHand.getHand());

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

    public static int compare(PlayerHand x, PlayerHand y) {
        var xRank = judge(x).getHandRank();
        var yRank = judge(y).getHandRank();
        if (xRank < yRank) return -1;
        else if (xRank == yRank) {
            switch(judge(x)) {
                case RoyalFlush:
                    // Split the pot
                    return 0;
                case StraightFlush:
                case Flush:
                case Straight:
                case HighCard:
                    // The Player With The Highest Card Used In The Straight
                    //  The Flush With The Highest Card Wins
                    // The Straight Ending In The Highest Card Wins
                    var xMaxValue = getMaxValueOfHand(x.getHand());
                    var yMaxValue = getMaxValueOfHand(y.getHand());
                    if (xMaxValue > yMaxValue) return -1;
                    else if (xMaxValue < yMaxValue) return 1;
                    else return 0;
                case FourOfAKind:
                    // Highest set wins
                    Rank xSet = getRankOfMatches(x, 4);
                    Rank ySet = getRankOfMatches(y, 4);
                    return evaluateHighestRank(xSet, ySet);
                case FullHouse:
                case ThreeOfAKind:
                    // Strength Of The Three Of A Kind, if equal, Strength Of The pair
                    xSet = getRankOfMatches(x, 3);
                    ySet = getRankOfMatches(y, 3);
                    return evaluateHighestRank(xSet, ySet);
                case TwoPair:
                    // The Highest Pair Is Used To Determine The Winner.
                    xSet = getPairs(x).max(Comparator.comparing(p -> p.getValue())).orElse(null);
                    ySet = getPairs(y).max(Comparator.comparing(p -> p.getValue())).orElse(null);
                    return evaluateHighestRank(xSet, ySet);
                case Pair:
                    xSet = getRankOfMatches(x, 2);
                    ySet = getRankOfMatches(y, 2);
                    return evaluateHighestRank(xSet, ySet);
                default:
                    return 0;
            }
        }
        else return 1;
    }

    public static int evaluateHighestRank(Rank x, Rank y) {
        if (x.getValue() > y.getValue()) return -1;
        else if (x.getValue() < y.getValue()) return 1;
        else return 0;
    }

    private static Integer getMaxValueOfHand(List<Card> hand) {
        return hand.stream().map(c -> c.getRank().getValue()).max(Integer::compare).orElse(0);
    }

    private static Rank getRankOfMatches(PlayerHand playerHand, int matches) {
        var xMap = mapHand(playerHand.getHand());
        return xMap.keySet().stream().filter(k -> xMap.get(k) == matches).findFirst().orElse(null);
    }

    public static boolean isFlush(PlayerHand playerHand) {
        var suit = playerHand.getHand().get(0).getSuit();
        return playerHand.getHand().stream().allMatch(e -> e.getSuit().equals(suit));
    }

    public static boolean isStraight(PlayerHand playerHand) {
        var hand = playerHand.getHand();
        var minValue = hand.stream().map(c -> c.getRank().getValue()).min(Integer::compare).orElse(0);
        var maxValue = getMaxValueOfHand(hand);
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
        return getPairs(playerHand).count() == 2;
    }

    public static Stream<Rank> getPairs(PlayerHand playerHand) {
        var mapOfHand = mapHand(playerHand.getHand());
        var multipleCards = mapOfHand.keySet().stream().filter(k -> mapOfHand.get(k) > 1);
        return multipleCards.filter(k -> mapOfHand.get(k).equals(2));
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