package com.code49.challenge.model;

import java.util.List;

import static com.code49.challenge.model.HandRank.HIGH_CARD;
import static java.util.Comparator.comparing;

public record Hand(List<Card> cards, HandRank rank) implements Comparable<Hand> {

    public static Hand of(List<Card> cards) {
        if (cards == null || cards.size() != 5) {
            throw new IllegalArgumentException("A poker hand requires 5 cards.");
        }
        return new Hand(cards, HIGH_CARD);
    }

    @Override
    public int compareTo(Hand other) {
        return comparing(Hand::rank).compare(this, other);
    }
}
