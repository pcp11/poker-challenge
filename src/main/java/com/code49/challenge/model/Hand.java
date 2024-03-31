package com.code49.challenge.model;

import java.util.*;

import static com.code49.challenge.model.CardValue.ACE;
import static com.code49.challenge.model.CardValue.FIVE;
import static com.code49.challenge.model.HandRank.*;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public record Hand(Card[] cards, HandRank rank, CardValue[] tieRanks) implements Comparable<Hand> {

    public static Hand of(Card... cards) {
        if (cards.length != 5) {
            throw new IllegalArgumentException("A poker hand requires 5 cards.");
        }
        var sortedValueCounts = Arrays.stream(cards)
                .collect(groupingBy(Card::value, TreeMap::new, counting()));

        return new Hand(cards, calcHandRank(cards, sortedValueCounts), calcTieRanks(sortedValueCounts));
    }

    /**
     * Calculates the rank of a poker hand given an array of cards and a sorted map of card values with their
     * occurrence counts. This method determines the rank based on the traditional hand ranking system in poker,
     * taking into account the number of different card values present, as well as specific combinations that
     * define hand ranks such as flushes and straights.
     * s
     *
     * @param cards             An array of {@link Card} representing the player's hand.
     * @param sortedValueCounts A TreeMap containing {@link CardValue} as keys and their occurrence counts (Long)
     *                          as values. The TreeMap is sorted by the card values in their natural order.
     *                          {@link CardValue} represents the value of the card (e.g., Ace, King, Queen, etc.).
     * @return A {@link HandRank} enum value representing the best hand rank that can be made with the given cards.
     */
    private static HandRank calcHandRank(Card[] cards, TreeMap<CardValue, Long> sortedValueCounts) {
        if (sortedValueCounts.keySet().size() == 4) {
            return ONE_PAIR;
        } else if (sortedValueCounts.keySet().size() == 3) {
            return sortedValueCounts.containsValue(3L) ? THREE_OF_A_KIND : TWO_PAIRS;
        } else if (sortedValueCounts.keySet().size() == 2) {
            return sortedValueCounts.containsValue(4L) ? FOUR_OF_A_KIND : FULL_HOUSE;
        } else {
            boolean isFlush = Arrays.stream(cards).map(Card::suit).distinct().count() == 1;
            boolean isStraight = (sortedValueCounts.lastKey().ordinal() - sortedValueCounts.firstKey().ordinal()) == 4
                    || (sortedValueCounts.lastKey().equals(ACE) && sortedValueCounts.lowerKey(ACE).equals(FIVE));

            if (isFlush && isStraight) {
                return STRAIGHT_FLUSH;
            } else if (isFlush) {
                return FLUSH;
            } else if (isStraight) {
                return STRAIGHT;
            } else {
                return HIGH_CARD;
            }
        }
    }

    /**
     * Calculates the tie-breaking ranks for a collection of cards based on their values and occurrence counts.
     * This method is used to determine the ordering of card values for tie-breaking poker hands, such as
     * when multiple players have hands of the same type (e.g., two pairs, full house).
     * <p>
     * The method sorts the cards primarily by their occurrence counts (frequencies) and secondarily by their
     * intrinsic values (e.g., King, Queen) in descending order. For example, in a full house or two pairs situation,
     * the card values with higher frequencies (or higher values in case of equal frequencies) are considered stronger
     * for tie-breaking purposes.
     *
     * @param sortedValueCounts A TreeMap containing {@link CardValue} as keys and their occurrence counts (Long)
     *                          as values. The TreeMap is sorted by the card values in their natural order.
     *                          {@link CardValue} represents the value of the card (e.g., Ace, King, Queen, etc.).
     * @return An array of {@link CardValue} sorted based on the criteria for tie-breaking. The array is sorted in
     * descending order, first by the frequency of each CardValue and then by the intrinsic CardValue itself.
     */
    private static CardValue[] calcTieRanks(TreeMap<CardValue, Long> sortedValueCounts) {
        return sortedValueCounts.entrySet()
                .stream()
                .sorted(comparing(Map.Entry<CardValue, Long>::getValue).thenComparing(Map.Entry::getKey).reversed())
                .map(Map.Entry::getKey)
                .toArray(CardValue[]::new);
    }

    @Override
    public int compareTo(Hand other) {
        return comparing(Hand::rank)
                .thenComparing(Hand::tieRanks, Arrays::compare)
                .compare(this, other);
    }
}
