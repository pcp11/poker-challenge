package com.code49.challenge.model.provider;

import com.code49.challenge.model.Card;
import com.code49.challenge.model.Hand;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

import static com.code49.challenge.model.CardSuit.*;
import static com.code49.challenge.model.CardValue.*;

public class CompareArgumentsProvider {

    public static final Hand HIGH_CARD_HAND1 = Hand.of(List.of(new Card(DIAMONDS, FOUR), new Card(SPADES, SIX),
            new Card(CLUBS, KING), new Card(HEARTS, EIGHT), new Card(SPADES, TWO)));

    public static final Hand HIGH_CARD_HAND2 = Hand.of(List.of(new Card(DIAMONDS, FOUR), new Card(SPADES, SIX),
            new Card(CLUBS, KING), new Card(HEARTS, EIGHT), new Card(SPADES, THREE)));

    public static final Hand ONE_PAIR_HAND1 = Hand.of(List.of(new Card(SPADES, NINE), new Card(HEARTS, TEN),
            new Card(CLUBS, SIX), new Card(SPADES, SIX), new Card(DIAMONDS, TWO)));

    public static final Hand ONE_PAIR_HAND2 = Hand.of(List.of(new Card(SPADES, NINE), new Card(HEARTS, TEN),
            new Card(CLUBS, SEVEN), new Card(SPADES, SEVEN), new Card(DIAMONDS, TWO)));

    public static final Hand TWO_PAIRS_HAND1 = Hand.of(List.of(new Card(CLUBS, THREE), new Card(SPADES, NINE),
            new Card(HEARTS, NINE), new Card(DIAMONDS, FIVE), new Card(CLUBS, FIVE)));

    public static final Hand TWO_PAIRS_HAND2 = Hand.of(List.of(new Card(CLUBS, THREE), new Card(SPADES, NINE),
            new Card(HEARTS, NINE), new Card(DIAMONDS, SIX), new Card(CLUBS, SIX)));

    public static final Hand THREE_OF_A_KIND_HAND1 = Hand.of(List.of(new Card(SPADES, ACE), new Card(HEARTS, ACE),
            new Card(CLUBS, ACE), new Card(SPADES, FIVE), new Card(DIAMONDS, EIGHT)));

    public static final Hand THREE_OF_A_KIND_HAND2 = Hand.of(List.of(new Card(SPADES, KING), new Card(HEARTS, KING),
            new Card(CLUBS, KING), new Card(SPADES, FIVE), new Card(DIAMONDS, EIGHT)));

    public static final Hand STRAIGHT_HAND1 = Hand.of(List.of(new Card(HEARTS, TWO),
            new Card(DIAMONDS, THREE), new Card(CLUBS, FOUR), new Card(HEARTS, FIVE), new Card(SPADES, SIX)));

    public static final Hand STRAIGHT_HAND2 = Hand.of(List.of(new Card(SPADES, ACE), new Card(HEARTS, TWO),
            new Card(DIAMONDS, THREE), new Card(CLUBS, FOUR), new Card(HEARTS, FIVE)));

    public static final Hand FLUSH_HAND1 = Hand.of(List.of(new Card(DIAMONDS, FOUR), new Card(DIAMONDS, EIGHT),
            new Card(DIAMONDS, SIX), new Card(DIAMONDS, SEVEN), new Card(DIAMONDS, ACE)));

    public static final Hand FLUSH_HAND2 = Hand.of(List.of(new Card(SPADES, FOUR), new Card(SPADES, EIGHT),
            new Card(SPADES, SIX), new Card(SPADES, SEVEN), new Card(SPADES, TWO)));

    public static final Hand FULL_HOUSE_HAND1 = Hand.of(List.of(new Card(HEARTS, ACE), new Card(SPADES, ACE),
            new Card(DIAMONDS, ACE), new Card(HEARTS, KING), new Card(SPADES, KING)));

    public static final Hand FULL_HOUSE_HAND2 = Hand.of(List.of(new Card(HEARTS, JACK), new Card(SPADES, QUEEN),
            new Card(DIAMONDS, QUEEN), new Card(HEARTS, QUEEN), new Card(SPADES, JACK)));

    public static final Hand FOUR_OF_A_KIND_HAND1 = Hand.of(List.of(new Card(DIAMONDS, FOUR), new Card(CLUBS, FOUR),
            new Card(HEARTS, FOUR), new Card(SPADES, FOUR), new Card(SPADES, THREE)));

    public static final Hand FOUR_OF_A_KIND_HAND2 = Hand.of(List.of(new Card(DIAMONDS, SEVEN), new Card(CLUBS, SEVEN),
            new Card(HEARTS, SEVEN), new Card(SPADES, TEN), new Card(SPADES, SEVEN)));

    public static final Hand STRAIGHT_FLUSH_HAND1 = Hand.of(List.of(new Card(CLUBS, NINE), new Card(CLUBS, FIVE),
            new Card(CLUBS, SIX), new Card(CLUBS, SEVEN), new Card(CLUBS, EIGHT)));

    public static final Hand STRAIGHT_FLUSH_HAND2 = Hand.of(List.of(new Card(CLUBS, FOUR), new Card(CLUBS, FIVE),
            new Card(CLUBS, SIX), new Card(CLUBS, SEVEN), new Card(CLUBS, EIGHT)));

    public static Stream<Arguments> provideArguments() {
        return Stream.of(
                // Comparing equal ranks
                Arguments.of(HIGH_CARD_HAND1, HIGH_CARD_HAND2, -1), // (K, 8, 6, 4, 2) < (K, 8, 6, 4, 3)
                Arguments.of(ONE_PAIR_HAND1, ONE_PAIR_HAND2, -1), // (6, 6, 10, 9, 2) < (7, 7, 10, 9, 2)
                Arguments.of(TWO_PAIRS_HAND1, TWO_PAIRS_HAND2, -1), // (9, 9, 5, 5, 3) < (9, 9, 6, 6, 3)
                Arguments.of(THREE_OF_A_KIND_HAND1, THREE_OF_A_KIND_HAND2, 1), // (A, A, A, 8, 5) > (K, K, K, 8, 5)
                Arguments.of(STRAIGHT_HAND1, STRAIGHT_HAND2, -1), // (6, 5, 4, 3, 2) < (A, 2, 3, 4, 5)
                Arguments.of(FLUSH_HAND1, FLUSH_HAND2, 1), // (A, 8, 7, 6, 4) > (8, 7, 6, 4, 2)
                Arguments.of(FULL_HOUSE_HAND1, FULL_HOUSE_HAND2, 1),  // (A, A, A, K, K) > (Q, Q, Q, J, J)
                Arguments.of(FOUR_OF_A_KIND_HAND1, FOUR_OF_A_KIND_HAND2, -1), // (4, 4, 4, 4, 3) < (7, 7, 7, 7, 10)
                Arguments.of(STRAIGHT_FLUSH_HAND1, STRAIGHT_FLUSH_HAND2, 1), // (9, 8, 7, 6, 5, 4) > (8, 7, 6, 5, 4)

                // Comparing different ranks
                Arguments.of(HIGH_CARD_HAND1, HIGH_CARD_HAND1, 0),
                Arguments.of(HIGH_CARD_HAND1, ONE_PAIR_HAND2, -1),
                Arguments.of(ONE_PAIR_HAND1, HIGH_CARD_HAND2, 1),
                Arguments.of(ONE_PAIR_HAND1, TWO_PAIRS_HAND2, -1),
                Arguments.of(TWO_PAIRS_HAND1, ONE_PAIR_HAND2, 1),
                Arguments.of(TWO_PAIRS_HAND1, THREE_OF_A_KIND_HAND2, -1),
                Arguments.of(THREE_OF_A_KIND_HAND1, TWO_PAIRS_HAND2, 1),
                Arguments.of(THREE_OF_A_KIND_HAND1, STRAIGHT_HAND2, -1),
                Arguments.of(STRAIGHT_HAND1, THREE_OF_A_KIND_HAND2, 1),
                Arguments.of(STRAIGHT_HAND1, FLUSH_HAND2, -1),
                Arguments.of(FLUSH_HAND1, STRAIGHT_HAND2, 1),
                Arguments.of(FLUSH_HAND1, FULL_HOUSE_HAND2, -1),
                Arguments.of(FULL_HOUSE_HAND1, FLUSH_HAND2, 1),
                Arguments.of(FULL_HOUSE_HAND1, FOUR_OF_A_KIND_HAND2, -1),
                Arguments.of(FOUR_OF_A_KIND_HAND1, FULL_HOUSE_HAND2, 1),
                Arguments.of(FOUR_OF_A_KIND_HAND1, STRAIGHT_FLUSH_HAND2, -1),
                Arguments.of(STRAIGHT_FLUSH_HAND1, FOUR_OF_A_KIND_HAND2, 1),
                Arguments.of(STRAIGHT_FLUSH_HAND1, STRAIGHT_FLUSH_HAND2, 0));
    }
}
