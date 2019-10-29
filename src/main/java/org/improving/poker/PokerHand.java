package org.improving.poker;

public class PokerHand {

    public static void dealHand(PlayerHand playerHand) {

//        if (isRoyalStraightFlush(card)) {
//            return ROYAL_STRAIGHT_FLUSH_MESSAGE;
//        }
//        else if (isStraightFlush(card)) {
//            return STRAIGHT_FLUSH_MESSAGE;
//        }
//        else if (isFourOfAKind(card)) {
//            return FOUR_CARDS_MESSAGE;
//        }
//        else if (isFullHouse(card)) {
//            return FULL_HOUSE_MESSAGE;
//        }
//        else if (isFlush(card)) {
//            return FLUSH_MESSAGE;
//        }
//        else if (isStraight(card)) {
//            return STRAIGHT_MESSAGE;
//        }
//        else if (isThreeCards(card)) {
//            return THREE_OF_A_KIND_MESSAGE;
//        }
//        else if (isTwoPairs(card)) {
//            return TWO_PAIRS_MESSAGE;
//        }
//        else if (isOnePair(card)) {
//            return ONE_PAIR_MESSAGE;
//        }
//        else {
//            return NO_PAIR_MESSAGE;
//        }
    }


    private static boolean isRoyalStraightFlush(PokerCard[] hand) {
        // hash storing sum
        int[] hash = new int[13];

        for (var card : hand) {

        }

        for(int i=0; i<hand.length; i++) {
            hash[hand[i].number-1]++;
        }

        if(hash[0]==1 && hash[9]==1 && hash[10]==1 && hash[11]==1 && hash[12]==1) {
            if(isFlush(hand)) {
                return true;
            }
        }
        return false;
    }


    private static boolean isStraightFlush(PokerCard[] card) {
        if(isStraight(card) && isFlush(card)) {
            return true;
        }
        return false;
    }


    private static boolean isFourOfAKind(PokerCard[] card) {
        // hash storing sum
        int[] hash = new int[13]; // NOTE: hash[0] is redundant!
        for(int i=0; i<card.length; i++) {
            hash[card[i].number-1]++;
        }

        // find largest sum
        int max = -1;
        for(int i=0; i<hash.length; i++) {
            if(hash[i] > max) {
                max = hash[i];
            }
        }

        // if three cards exist
        if(max==4) {
            return true;
        }
        else {
            return false;
        }
    }


    private static boolean isFullHouse(PokerCard[] card) {
        // hash storing sum
        int[] hash = new int[13]; // NOTE: hash[0] is redundant!
        for(int i=0; i<card.length; i++) {
            hash[card[i].number-1]++;
        }

        // find pair and three of a kind
        Boolean pairFlag = false;
        Boolean threeFlag = false;
        for(int i=0; i<hash.length; i++) {
            if(hash[i] == 2) {
                pairFlag = true;
            }
            if(hash[i] == 3) {
                threeFlag = true;
            }
        }
        // if three cards exist
        if(pairFlag && threeFlag) {
            return true;
        }
        else {
            return false;
        }
    }


    private static boolean isFlush(PokerCard[] hand) {
//        var suit = hand.getHand().get(0).getSuits();
//        for (var card : hand.getHand()) {
//            if (card.getSuits() != suit) return false;
//        }
//        return true;
        return true;
    }


    private static boolean isStraight(PokerCard[] card) {
        // hash storing sum
        int[] hash = new int[13];
        for(int i=0; i<card.length; i++) {
            hash[card[i].number-1]++;
        }
        for(int i=0; i<hash.length-3; i++) {
            if (hash[i] == 1) {
                if (hash[i+1] == 1) {
                    if (hash[i+2] == 1) {
                        if (hash[i+3] == 1) {
                            if (hash[(i+4)%13] == 1) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }


    private static boolean isThreeCards(PokerCard[] card) {
        // hash storing sum
        int[] hash = new int[13]; // NOTE: hash[0] is redundant!
        for(int i=0; i<card.length; i++) {
            hash[card[i].number-1]++;
        }

        // find largest sum
        int max = -1;
        for(int i=0; i<hash.length; i++) {
            if(hash[i] > max) {
                max = hash[i];
            }
        }

        // if three cards exist
        if(max==3) {
            return true;
        }
        else {
            return false;
        }
    }


    private static boolean isTwoPairs(PokerCard[] card) {
        // hash storing sum
        int[] hash = new int[13]; // NOTE: hash[0] is redundant!
        for(int i=0; i<card.length; i++) {
            hash[card[i].number-1]++;
        }

        // search for two pairs
        Boolean flag = false;
        for(int i=0; i<hash.length; i++) {
            if(hash[i] == 2) {
                if(flag) {
                    return true;
                }
                else {
                    flag = true;
                }
            }
        }
        return false;
    }


    private static boolean isOnePair(PokerCard[] card) {
        int[] hash = new int[13]; // NOTE: hash[0] is redundant!
        for(int i=0; i<card.length; i++) {
            hash[card[i].number-1]++;
        }
        // search for one pair
        for(int i=0; i<hash.length; i++) {
            if(hash[i] == 2) {
                return true;
            }
        }
        return false;
    }
}
