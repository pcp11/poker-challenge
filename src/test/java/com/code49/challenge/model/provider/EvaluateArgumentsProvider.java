package com.code49.challenge.model.provider;

import com.code49.challenge.model.Card;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static com.code49.challenge.model.CardSuit.*;
import static com.code49.challenge.model.CardValue.*;
import static com.code49.challenge.model.HandRank.*;

public class EvaluateArgumentsProvider {

    public static final Card[] HIGH_CARD_HAND1 = new Card[]{new Card(DIAMONDS, FOUR), new Card(SPADES, SIX),
            new Card(CLUBS, KING), new Card(HEARTS, EIGHT), new Card(SPADES, THREE)};

    public static final Card[] HIGH_CARD_HAND2 = new Card[]{new Card(HEARTS, ACE), new Card(SPADES, KING),
            new Card(DIAMONDS, QUEEN), new Card(CLUBS, JACK), new Card(SPADES, NINE)};

    public static final Card[] ONE_PAIR_HAND1 = new Card[]{new Card(SPADES, NINE), new Card(HEARTS, TEN),
            new Card(CLUBS, SIX), new Card(SPADES, SIX), new Card(DIAMONDS, TWO)};

    public static final Card[] ONE_PAIR_HAND2 = new Card[]{new Card(HEARTS, ACE), new Card(SPADES, ACE),
            new Card(HEARTS, KING), new Card(SPADES, QUEEN), new Card(DIAMONDS, JACK)};

    public static final Card[] TWO_PAIRS_HAND1 = new Card[]{new Card(CLUBS, THREE), new Card(SPADES, NINE),
            new Card(HEARTS, NINE), new Card(DIAMONDS, FIVE), new Card(CLUBS, FIVE)};

    public static final Card[] TWO_PAIRS_HAND2 = new Card[]{new Card(HEARTS, ACE), new Card(SPADES, ACE),
            new Card(CLUBS, KING), new Card(DIAMONDS, KING), new Card(SPADES, QUEEN)};

    public static final Card[] THREE_OF_A_KIND_HAND1 = new Card[]{new Card(SPADES, KING), new Card(HEARTS, KING),
            new Card(CLUBS, KING), new Card(SPADES, FIVE), new Card(DIAMONDS, EIGHT)};

    public static final Card[] THREE_OF_A_KIND_HAND2 = new Card[]{new Card(HEARTS, ACE), new Card(SPADES, ACE),
            new Card(DIAMONDS, ACE), new Card(SPADES, KING), new Card(CLUBS, QUEEN)};

    public static final Card[] STRAIGHT_HAND1 = new Card[]{new Card(SPADES, THREE), new Card(HEARTS, FOUR),
            new Card(DIAMONDS, FIVE), new Card(CLUBS, SIX), new Card(HEARTS, SEVEN)};

    public static final Card[] STRAIGHT_HAND2 = new Card[]{new Card(SPADES, ACE), new Card(HEARTS, TWO),
            new Card(DIAMONDS, THREE), new Card(CLUBS, FOUR), new Card(HEARTS, FIVE)};

    public static final Card[] FLUSH_HAND1 = new Card[]{new Card(DIAMONDS, FOUR), new Card(DIAMONDS, EIGHT),
            new Card(DIAMONDS, SIX), new Card(DIAMONDS, SEVEN), new Card(DIAMONDS, TWO)};

    public static final Card[] FLUSH_HAND2 = new Card[]{new Card(SPADES, ACE), new Card(SPADES, TEN),
            new Card(SPADES, SEVEN), new Card(SPADES, SIX), new Card(SPADES, TWO)};

    public static final Card[] FULL_HOUSE_HAND1 = new Card[]{new Card(SPADES, QUEEN), new Card(HEARTS, QUEEN),
            new Card(CLUBS, QUEEN), new Card(SPADES, SIX), new Card(DIAMONDS, SIX)};

    public static final Card[] FULL_HOUSE_HAND2 = new Card[]{new Card(HEARTS, ACE), new Card(SPADES, ACE),
            new Card(DIAMONDS, ACE), new Card(HEARTS, KING), new Card(SPADES, KING)};

    public static final Card[] FOUR_OF_A_KIND_HAND1 = new Card[]{new Card(HEARTS, ACE), new Card(SPADES, ACE),
            new Card(CLUBS, ACE), new Card(DIAMONDS, ACE), new Card(SPADES, EIGHT)};

    public static final Card[] FOUR_OF_A_KIND_HAND2 = new Card[]{new Card(DIAMONDS, SEVEN), new Card(CLUBS, SEVEN),
            new Card(HEARTS, SEVEN), new Card(SPADES, TEN), new Card(SPADES, SEVEN)};

    public static final Card[] STRAIGHT_FLUSH_HAND1 = new Card[]{new Card(CLUBS, FOUR), new Card(CLUBS, FIVE),
            new Card(CLUBS, SIX), new Card(CLUBS, SEVEN), new Card(CLUBS, EIGHT)};

    public static final Card[] STRAIGHT_FLUSH_HAND2 = new Card[]{new Card(DIAMONDS, JACK), new Card(DIAMONDS, QUEEN),
            new Card(DIAMONDS, TEN), new Card(DIAMONDS, KING), new Card(DIAMONDS, ACE)};

    public static Stream<Arguments> provideEvaluateArguments() {
        return Stream.of(
                Arguments.of(HIGH_CARD_HAND1, HIGH_CARD),
                Arguments.of(HIGH_CARD_HAND2, HIGH_CARD),
                Arguments.of(ONE_PAIR_HAND1, ONE_PAIR),
                Arguments.of(ONE_PAIR_HAND2, ONE_PAIR),
                Arguments.of(TWO_PAIRS_HAND1, TWO_PAIRS),
                Arguments.of(TWO_PAIRS_HAND2, TWO_PAIRS),
                Arguments.of(THREE_OF_A_KIND_HAND1, THREE_OF_A_KIND),
                Arguments.of(THREE_OF_A_KIND_HAND2, THREE_OF_A_KIND),
                Arguments.of(STRAIGHT_HAND1, STRAIGHT),
                Arguments.of(STRAIGHT_HAND2, STRAIGHT),
                Arguments.of(FLUSH_HAND1, FLUSH),
                Arguments.of(FLUSH_HAND2, FLUSH),
                Arguments.of(FULL_HOUSE_HAND1, FULL_HOUSE),
                Arguments.of(FULL_HOUSE_HAND2, FULL_HOUSE),
                Arguments.of(FOUR_OF_A_KIND_HAND1, FOUR_OF_A_KIND),
                Arguments.of(FOUR_OF_A_KIND_HAND2, FOUR_OF_A_KIND),
                Arguments.of(STRAIGHT_FLUSH_HAND1, STRAIGHT_FLUSH),
                Arguments.of(STRAIGHT_FLUSH_HAND2, STRAIGHT_FLUSH));
    }
}
