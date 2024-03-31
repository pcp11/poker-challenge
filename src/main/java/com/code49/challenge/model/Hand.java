package com.code49.challenge.model;

import java.util.List;
import java.util.TreeMap;

import static com.code49.challenge.model.CardValue.ACE;
import static com.code49.challenge.model.CardValue.FIVE;
import static com.code49.challenge.model.HandRank.*;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public record Hand(List<Card> cards, HandRank rank) implements Comparable<Hand> {

    public static Hand of(List<Card> cards) {
        if (cards == null || cards.size() != 5) {
            throw new IllegalArgumentException("A poker hand requires 5 cards.");
        }
        return evaluate(cards);
    }

    private static Hand evaluate(List<Card> cards) {
        var sortedRankFrequency = cards.stream().collect(groupingBy(Card::rank, TreeMap::new, counting()));

        if (sortedRankFrequency.keySet().size() == 4) {
            return new Hand(cards, ONE_PAIR);
        } else if (sortedRankFrequency.keySet().size() == 3) {
            if (sortedRankFrequency.containsValue(3L)) {
                return new Hand(cards, THREE_OF_A_KIND);
            }
            return new Hand(cards, TWO_PAIRS);
        } else if (sortedRankFrequency.keySet().size() == 2) {
            if (sortedRankFrequency.containsValue(3L)) {
                return new Hand(cards, FULL_HOUSE);
            }
            return new Hand(cards, FOUR_OF_A_KIND);
        } else {
            boolean isFlush = cards.stream().map(Card::suit).distinct().count() == 1;
            boolean isStraight = (sortedRankFrequency.lastKey().ordinal() - sortedRankFrequency.firstKey().ordinal()) == 4
                    || (sortedRankFrequency.lastKey().equals(ACE) && sortedRankFrequency.lowerKey(ACE).equals(FIVE));

            if (isFlush && isStraight) {
                return new Hand(cards, STRAIGHT_FLUSH);
            } else if (isFlush) {
                return new Hand(cards, FLUSH);
            } else if (isStraight) {
                return new Hand(cards, STRAIGHT);
            } else {
                return new Hand(cards, HIGH_CARD);
            }
        }
    }

    @Override
    public int compareTo(Hand other) {
        return comparing(Hand::rank).compare(this, other);
    }
}
