package org.improving.poker;

public enum PokerHand {
    HighCard("No Pair...", 10),
    Pair("One Pair.", 9),
    TwoPair("Two Pairs.", 8),
    ThreeOfAKind("Three Cards.", 7),
    Straight("Straight!", 6),
    Flush("Flush!", 5),
    FullHouse("Fu-Fu-Full House!", 4),
    FourOfAKind("FOUR OF A KIND!!", 3),
    StraightFlush("IT'S STRAIGHT FLUSH!!!", 2),
    RoyalFlush("Welp, Royal Straight Flush, I am outta here.", 1);

    private final String message;
    private final int handRank;

    PokerHand(String message, int handRank) {
        this.message = message;
        this.handRank = handRank;
    }

    public String getMessage() {
        return message;
    }

    public int getHandRank() {
        return handRank;
    }
}
