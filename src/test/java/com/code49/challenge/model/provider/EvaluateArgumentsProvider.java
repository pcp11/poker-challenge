package com.code49.challenge.model.provider;

import com.code49.challenge.model.Card;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

import static com.code49.challenge.model.CardSuit.*;
import static com.code49.challenge.model.CardValue.*;
import static com.code49.challenge.model.HandRank.*;

public class EvaluateArgumentsProvider {

    public static Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of(
                        List.of(new Card(DIAMONDS, FOUR), new Card(SPADES, SIX), new Card(CLUBS, KING),
                                new Card(HEARTS, EIGHT), new Card(SPADES, THREE)),
                        HIGH_CARD),
                Arguments.of(
                        List.of(new Card(HEARTS, ACE), new Card(SPADES, KING), new Card(DIAMONDS, QUEEN),
                                new Card(CLUBS, JACK), new Card(SPADES, NINE)),
                        HIGH_CARD),
                Arguments.of(
                        List.of(new Card(SPADES, NINE), new Card(HEARTS, TEN), new Card(CLUBS, SIX),
                                new Card(SPADES, SIX), new Card(DIAMONDS, TWO)),
                        ONE_PAIR),
                Arguments.of(
                        List.of(new Card(HEARTS, ACE), new Card(SPADES, ACE), new Card(HEARTS, KING),
                                new Card(SPADES, QUEEN), new Card(DIAMONDS, JACK)),
                        ONE_PAIR),
                Arguments.of(
                        List.of(new Card(CLUBS, THREE), new Card(SPADES, NINE), new Card(HEARTS, NINE),
                                new Card(DIAMONDS, FIVE), new Card(CLUBS, FIVE)),
                        TWO_PAIRS),
                Arguments.of(
                        List.of(new Card(HEARTS, ACE), new Card(SPADES, ACE), new Card(CLUBS, KING),
                                new Card(DIAMONDS, KING), new Card(SPADES, QUEEN)),
                        TWO_PAIRS),
                Arguments.of(
                        List.of(new Card(SPADES, KING), new Card(HEARTS, KING), new Card(CLUBS, KING),
                                new Card(SPADES, FIVE), new Card(DIAMONDS, EIGHT)),
                        THREE_OF_A_KIND),
                Arguments.of(
                        List.of(new Card(HEARTS, ACE), new Card(SPADES, ACE), new Card(DIAMONDS, ACE),
                                new Card(SPADES, KING), new Card(CLUBS, QUEEN)),
                        THREE_OF_A_KIND),
                Arguments.of(
                        List.of(new Card(SPADES, THREE), new Card(HEARTS, FOUR), new Card(DIAMONDS, FIVE),
                                new Card(CLUBS, SIX), new Card(HEARTS, SEVEN)),
                        STRAIGHT),
                Arguments.of(
                        List.of(new Card(SPADES, ACE), new Card(HEARTS, TWO), new Card(DIAMONDS, THREE),
                                new Card(CLUBS, FOUR), new Card(HEARTS, FIVE)),
                        STRAIGHT),
                Arguments.of(
                        List.of(new Card(DIAMONDS, FOUR), new Card(DIAMONDS, EIGHT), new Card(DIAMONDS, SIX),
                                new Card(DIAMONDS, SEVEN), new Card(DIAMONDS, TWO)),
                        FLUSH),
                Arguments.of(
                        List.of(new Card(SPADES, ACE), new Card(SPADES, TEN), new Card(SPADES, SEVEN),
                                new Card(SPADES, SIX), new Card(SPADES, TWO)),
                        FLUSH),
                Arguments.of(
                        List.of(new Card(SPADES, QUEEN), new Card(HEARTS, QUEEN), new Card(CLUBS, QUEEN),
                                new Card(SPADES, SIX), new Card(DIAMONDS, SIX)),
                        FULL_HOUSE),
                Arguments.of(
                        List.of(new Card(HEARTS, ACE), new Card(SPADES, ACE), new Card(DIAMONDS, ACE),
                                new Card(HEARTS, KING), new Card(SPADES, KING)),
                        FULL_HOUSE),
                Arguments.of(
                        List.of(new Card(HEARTS, ACE), new Card(SPADES, ACE), new Card(CLUBS, ACE),
                                new Card(DIAMONDS, ACE), new Card(SPADES, EIGHT)),
                        FOUR_OF_A_KIND),
                Arguments.of(
                        List.of(new Card(DIAMONDS, SEVEN), new Card(CLUBS, SEVEN), new Card(HEARTS, SEVEN),
                                new Card(SPADES, TEN), new Card(SPADES, SEVEN)),
                        FOUR_OF_A_KIND),
                Arguments.of(
                        List.of(new Card(CLUBS, FOUR), new Card(CLUBS, FIVE), new Card(CLUBS, SIX),
                                new Card(CLUBS, SEVEN), new Card(CLUBS, EIGHT)),
                        STRAIGHT_FLUSH),
                Arguments.of(
                        List.of(new Card(DIAMONDS, JACK), new Card(DIAMONDS, QUEEN), new Card(DIAMONDS, TEN),
                                new Card(DIAMONDS, KING), new Card(DIAMONDS, ACE)),
                        STRAIGHT_FLUSH));
    }
}
