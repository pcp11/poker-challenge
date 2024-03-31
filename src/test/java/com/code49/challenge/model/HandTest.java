package com.code49.challenge.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandTest {

    @ParameterizedTest
    @MethodSource("com.code49.challenge.model.provider.EvaluateArgumentsProvider#provideArguments")
    void testEvaluate(List<Card> cardsP1, HandRank rank) {
        assertEquals(Hand.of(cardsP1).rank(), rank);
    }

    @ParameterizedTest
    @MethodSource("com.code49.challenge.model.provider.CompareArgumentsProvider#provideArguments")
    void testCompare(Hand handP1, Hand handP2, int result) {
        assertEquals(handP1.compareTo(handP2), result);
    }
}
