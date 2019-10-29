package org.improving.poker;

import org.improving.tim.Card;
import org.improving.tim.Deck;
import org.improving.tim.Rank;
import org.improving.tim.Suit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PokerGameTest {

    Card H1 = new Card(Rank.Two, Suit.Hearts);
    Card H2 = new Card(Rank.Three, Suit.Hearts);
    Card H3 = new Card(Rank.Four, Suit.Hearts);
    Card H4 = new Card(Rank.Five, Suit.Hearts);
    Card H5 = new Card(Rank.Six, Suit.Hearts);
    Card H6 = new Card(Rank.Seven, Suit.Hearts);
    Card H7 = new Card(Rank.Eight, Suit.Hearts);
    Card H8 = new Card(Rank.Nine, Suit.Hearts);
    Card H9 = new Card(Rank.Ten, Suit.Hearts);
    Card H10 = new Card(Rank.Jack, Suit.Hearts);
    Card H11 = new Card(Rank.Queen, Suit.Hearts);
    Card H12 = new Card(Rank.King, Suit.Hearts);
    Card H13 = new Card(Rank.Ace, Suit.Hearts);

    Card S1 = new Card(Rank.Two, Suit.Spades);
    Card S2 = new Card(Rank.Three, Suit.Spades);
    Card S3 = new Card(Rank.Four, Suit.Spades);
    Card S4 = new Card(Rank.Five, Suit.Spades);
    Card S5 = new Card(Rank.Six, Suit.Spades);
    Card S6 = new Card(Rank.Seven, Suit.Spades);
    Card S7 = new Card(Rank.Eight, Suit.Spades);
    Card S8 = new Card(Rank.Nine, Suit.Spades);
    Card S9 = new Card(Rank.Ten, Suit.Spades);
    Card S10 = new Card(Rank.Jack, Suit.Spades);
    Card S11 = new Card(Rank.Queen, Suit.Spades);
    Card S12 = new Card(Rank.King, Suit.Spades);
    Card S13 = new Card(Rank.Ace, Suit.Spades);

    Card C1 = new Card(Rank.Two, Suit.Clubs);
    Card D1 = new Card(Rank.Two, Suit.Diamonds);

    PlayerHand hand;

    @BeforeEach
    void init() {
        hand = new PlayerHand(new Deck());
        hand.getHand().clear();
    }

    @Test
    void isFlushShouldReturnTrueWhenAllSuitsAreTheSame() {
        // Arrange
        hand.getHand().addAll(Arrays.asList(H1, H2, H5, H7, H9));

        // Act
        var result = PokerGame.isFlush(hand);

        // Assert
        assertTrue(result);
    }

    @Test
    void sortAscendingTest() {
        // Arrange
        hand.getHand().addAll(Arrays.asList(H3, H2, H4, H6, H5));

        // Act
        PokerGame.sortAscending(hand);
        var result = hand.getHand();

        // Assert
        var expected = Arrays.asList(H6, H5, H4, H3, H2);
        assertEquals(expected, result);
    }
}