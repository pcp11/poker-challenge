package com.poker.challenge.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandTest {

    @ParameterizedTest
    @MethodSource("com.poker.challenge.model.provider.EvaluateArgumentsProvider#provideEvaluateArguments")
    void testEvaluate(Card[] cardsP1, HandRank rank) {
        assertEquals(Hand.of(cardsP1).rank(), rank);
    }

    @ParameterizedTest
    @MethodSource({
            "com.poker.challenge.model.provider.CompareArgumentsProvider#provideCompareArguments",
            "com.poker.challenge.model.provider.CompareArgumentsProvider#provideTieBreakArguments"})
    void testCompare(Hand handP1, Hand handP2, int result) {
        assertEquals(Math.signum(handP1.compareTo(handP2)), result);
    }
}
