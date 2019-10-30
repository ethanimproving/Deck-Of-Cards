package org.improving.poker;

public enum PokerHand {
    HighCard("No Pair..."),
    Pair("One Pair."),
    ThreeOfAKind("Three Cards."),
    TwoPair("Two Pairs."),
    Straight("Straight!"),
    Flush("Flush!"),
    FullHouse("Fu-Fu-Full House!"),
    FourOfAKind("FOUR OF A KIND!!"),
    StraightFlush("IT'S STRAIGHT FLUSH!!!"),
    RoyalFlush("Welp, Royal Straight Flush, I am outta here.");

    private final String message;

    PokerHand(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
