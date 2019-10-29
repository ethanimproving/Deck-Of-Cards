package org.improving.poker;

public enum Hand {
    STRAIGHT("Straight!"),
    THREE_OF_A_KIND("Three Cards."),
    TWO_PAIRS("Two Pairs."),
    ONE_PAIR("One Pair."),
    NO_PAIR("No Pair..."),
    FLUSH("Flush!"),
    FULL_HOUSE("Fu-Fu-Full House!"),
    FOUR_CARDS("FOUR OF A KIND!!"),
    STRAIGHT_FLUSH("IT'S STRAIGHT FLUSH!!!"),
    ROYAL_STRAIGHT_FLUSH("Welp, Royal Straight Flush, I am outta here.");

    private final String message;

    Hand(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
