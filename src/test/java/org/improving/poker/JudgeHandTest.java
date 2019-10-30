package org.improving.poker;

import org.improving.tim.Card;
import org.improving.tim.Deck;
import org.improving.tim.Rank;
import org.improving.tim.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class JudgeHandTest {

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
    void Is_Flush_Should_Return_True_If_Suit_Are_The_Same() {
        // Arrange
        hand.getHand().addAll(Arrays.asList(H1, H2, H5, H7, H9));

        // Act
        var result = JudgeHand.isFlush(hand);

        // Assert
        assertTrue(result);
    }

    @Test
    void Sort_Ascending_Should_Order_Cards_From_Least_To_Greatest() {
        // Arrange
        hand.getHand().addAll(Arrays.asList(H3, H2, H4, H6, H5));

        // Act
        JudgeHand.sortAscending(hand);
        var result = hand.getHand();

        // Assert
        var expected = Arrays.asList(H2, H3, H4, H5, H6);
        assertEquals(expected, result);
    }

    @Test
    public void Is_Straight_Should_Return_True_If_Suit_Are_The_Same() {
        // Arrange
        hand.getHand().addAll(Arrays.asList(
            new Card(Rank.Ace, Suit.Spades),
            new Card(Rank.King, Suit.Spades),
            new Card(Rank.Queen, Suit.Clubs),
            new Card(Rank.Jack, Suit.Spades),
            new Card(Rank.Ten, Suit.Spades)
        ));

        // Act
        var result = JudgeHand.isStraight(hand);

        // Assert
        assertTrue(result);
    }

    @Test
    void Map_Hand_Should_Increment_Value_Of_Key_When_Rank_Is_The_Same() {
        // Arrange
        var cards = Arrays.asList(
            new Card(Rank.Ace, Suit.Spades),
            new Card(Rank.King, Suit.Spades),
            new Card(Rank.Ace, Suit.Clubs),
            new Card(Rank.Jack, Suit.Spades),
            new Card(Rank.Ace, Suit.Hearts)
        );

        // Act
        var mapOfCards = JudgeHand.mapHand(cards);
        var result = mapOfCards.get(Rank.Ace);

        // Assert
        assertEquals(3, result);
    }

    @Test
    public void Is_Pair_Should_Return_True_When_Two_Ranks_Are_The_Same() {
        // Arrange
        hand.getHand().addAll(Arrays.asList(
                new Card(Rank.Ace, Suit.Spades),
                new Card(Rank.King, Suit.Spades),
                new Card(Rank.Queen, Suit.Spades),
                new Card(Rank.Ace, Suit.Clubs),
                new Card(Rank.Ten, Suit.Spades)
        ));

        // Act
        var result = JudgeHand.isPair(hand);

        // Assert
        assertTrue(result);
    }

    @Test
    public void Is_Two_Pair_Should_Return_True_When_Two_Ranks_Are_The_Same_Twice() {
        // Arrange
        hand.getHand().addAll(Arrays.asList(
            new Card(Rank.Ace, Suit.Spades),
            new Card(Rank.King, Suit.Spades),
            new Card(Rank.Ten, Suit.Hearts),
            new Card(Rank.Ace, Suit.Clubs),
            new Card(Rank.Ten, Suit.Spades)
        ));

        // Act
        var result = JudgeHand.isTwoPair(hand);

        // Assert
        assertTrue(result);
    }

    @Test
    public void Is_Three_Of_A_Kind_Should_Return_True_When_Three_Ranks_Are_The_Same() {
        // Arrange
        hand.getHand().addAll(Arrays.asList(
                new Card(Rank.Ace, Suit.Spades),
                new Card(Rank.King, Suit.Spades),
                new Card(Rank.Ace, Suit.Hearts),
                new Card(Rank.Ace, Suit.Clubs),
                new Card(Rank.Ten, Suit.Spades)
        ));

        // Act
        var result = JudgeHand.isThreeOfAKind(hand);

        // Assert
        assertTrue(result);
    }

    @Test
    public void Is_Four_Of_A_Kind_Should_Return_True_When_Four_Ranks_Are_The_Same() {
        // Arrange
        hand.getHand().addAll(Arrays.asList(
                new Card(Rank.Ace, Suit.Spades),
                new Card(Rank.King, Suit.Spades),
                new Card(Rank.Ace, Suit.Hearts),
                new Card(Rank.Ace, Suit.Clubs),
                new Card(Rank.Ace, Suit.Diamonds)
        ));

        // Act
        var result = JudgeHand.isFourOfAKind(hand);

        // Assert
        assertTrue(result);
    }

    @Test
    public void Is_Full_House_Should_Return_True_When_Three_Ranks_Are_The_Same_And_The_Other_Two_Are_The_Same() {
        // Arrange
        hand.getHand().addAll(Arrays.asList(
                new Card(Rank.Ace, Suit.Spades),
                new Card(Rank.Ace, Suit.Clubs),
                new Card(Rank.Ten, Suit.Clubs),
                new Card(Rank.Ten, Suit.Diamonds),
                new Card(Rank.Ten, Suit.Spades)
        ));

        // Act
        var result = JudgeHand.isFullHouse(hand);

        // Assert
        assertTrue(result);
    }

    @Test
    public void Judge_Should_Recognize_A_Flush() {
        // Arrange
        hand.getHand().addAll(Arrays.asList(
            new Card(Rank.Ace, Suit.Spades),
            new Card(Rank.Ten, Suit.Spades),
            new Card(Rank.Two, Suit.Spades),
            new Card(Rank.Three, Suit.Spades),
            new Card(Rank.Five, Suit.Spades)
        ));

        // Act
        var result = JudgeHand.judge(hand);

        // Assert
        assertEquals(PokerHand.Flush, result);
    }


    @Test
    public void Judge_Should_Recognize_A_Straight() {
        // Arrange
        hand.getHand().addAll(Arrays.asList(
            new Card(Rank.Ace, Suit.Spades),
            new Card(Rank.King, Suit.Spades),
            new Card(Rank.Queen, Suit.Clubs),
            new Card(Rank.Jack, Suit.Spades),
            new Card(Rank.Ten, Suit.Spades)
        ));

        // Act
        var result = JudgeHand.judge(hand);

        // Assert
        assertEquals(PokerHand.Straight, result);
    }

    @Test
    public void Judge_Should_Recognize_A_StraightFlush() {
        // Arrange
        hand.getHand().addAll(Arrays.asList(
            new Card(Rank.Nine, Suit.Spades),
            new Card(Rank.King, Suit.Spades),
            new Card(Rank.Queen, Suit.Spades),
            new Card(Rank.Jack, Suit.Spades),
            new Card(Rank.Ten, Suit.Spades)
        ));

        // Act
        var result = JudgeHand.judge(hand);

        // Assert
        assertEquals(PokerHand.StraightFlush, result);
    }

    @Test
    public void Judge_Should_Recognize_A_RoyalFlush() {
        // Arrange
        hand.getHand().addAll(Arrays.asList(
            new Card(Rank.Ace, Suit.Spades),
            new Card(Rank.King, Suit.Spades),
            new Card(Rank.Queen, Suit.Spades),
            new Card(Rank.Jack, Suit.Spades),
            new Card(Rank.Ten, Suit.Spades)
        ));

        // Act
        var result = JudgeHand.judge(hand);

        // Assert
        assertEquals(PokerHand.RoyalFlush, result);
    }


    @Test
    public void Judge_Should_Recognize_A_Pair() {
        // Arrange
        hand.getHand().addAll(Arrays.asList(
            new Card(Rank.Ace, Suit.Spades),
            new Card(Rank.King, Suit.Spades),
            new Card(Rank.Queen, Suit.Spades),
            new Card(Rank.Ace, Suit.Clubs),
            new Card(Rank.Ten, Suit.Spades)
        ));

        // Act
        var result = JudgeHand.judge(hand);

        // Assert
        assertEquals(PokerHand.Pair, result);
    }


    @Test
    public void Judge_Should_Recognize_A_Two_Pair() {
        // Arrange
        hand.getHand().addAll(Arrays.asList(
            new Card(Rank.Ace, Suit.Spades),
            new Card(Rank.King, Suit.Spades),
            new Card(Rank.Ten, Suit.Clubs),
            new Card(Rank.Ace, Suit.Clubs),
            new Card(Rank.Ten, Suit.Spades)
        ));

        // Act
        var result = JudgeHand.judge(hand);

        // Assert
        assertEquals(PokerHand.TwoPair, result);
    }

    @Test
    public void Judge_Should_Recognize_A_Three_Of_A_Kind() {
        // Arrange
        hand.getHand().addAll(Arrays.asList(
            new Card(Rank.Ace, Suit.Spades),
            new Card(Rank.King, Suit.Spades),
            new Card(Rank.Ten, Suit.Clubs),
            new Card(Rank.Ten, Suit.Diamonds),
            new Card(Rank.Ten, Suit.Spades)
        ));

        // Act
        var result = JudgeHand.judge(hand);

        // Assert
        assertEquals(PokerHand.ThreeOfAKind, result);
    }

    @Test
    public void Judge_Should_Recognize_A_Four_Of_A_Kind() {
        // Arrange
        hand.getHand().addAll(Arrays.asList(
            new Card(Rank.Ace, Suit.Spades),
            new Card(Rank.Ten, Suit.Hearts),
            new Card(Rank.Ten, Suit.Clubs),
            new Card(Rank.Ten, Suit.Diamonds),
            new Card(Rank.Ten, Suit.Spades)
        ));

        // Act
        var result = JudgeHand.judge(hand);

        // Assert
        assertEquals(PokerHand.FourOfAKind, result);
    }

    @Test
    public void Judge_Should_Recognize_A_Full_House() {
        // Arrange
        hand.getHand().addAll(Arrays.asList(
            new Card(Rank.Ace, Suit.Spades),
            new Card(Rank.Ace, Suit.Clubs),
            new Card(Rank.Ten, Suit.Clubs),
            new Card(Rank.Ten, Suit.Diamonds),
            new Card(Rank.Ten, Suit.Spades)
        ));

        // Act
        var result = JudgeHand.judge(hand);

        // Assert
        assertEquals(PokerHand.FullHouse, result);
    }

}