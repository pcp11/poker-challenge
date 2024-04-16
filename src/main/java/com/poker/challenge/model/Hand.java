package com.poker.challenge.model;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import static com.poker.challenge.model.CardValue.ACE;
import static com.poker.challenge.model.CardValue.FIVE;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public record Hand(Card[] cards, HandRank rank, CardValue... tieRanks) implements Comparable<Hand> {

    public static Hand of(Card... cards) {
        if (cards.length != 5) {
            throw new IllegalArgumentException("A poker hand requires 5 cards.");
        }
        return evaluateHand(cards);
    }

    /**
     * Evaluates the poker hand represented by the given array of cards and determines its rank.
     * This method analyzes the cards to identify poker hands such as One Pair, Two Pairs, Three of a Kind,
     * Straight, Flush, Full House, Four of a Kind, Straight Flush, and High Card. It also calculates tie-breaking
     * ranks for hands where multiple players might have the same hand type.
     *
     * @param cards An array of Card objects representing the player's poker hand. Each Card object includes
     *              both a card value and a suit.
     * @return A Hand object representing the evaluated poker hand, including its rank (e.g., One Pair, Flush),
     *         and the tie-breaking ranks calculated from the cards.
     */
    private static Hand evaluateHand(Card[] cards) {
        TreeMap<CardValue, Long> sortedValueCounts = Arrays.stream(cards)
                .collect(groupingBy(Card::value, TreeMap::new, counting()));

        if (sortedValueCounts.keySet().size() == 4) {
            return new Hand(cards, HandRank.ONE_PAIR, calcTieRanks(sortedValueCounts));
        } else if (sortedValueCounts.keySet().size() == 3) {
            boolean isThreeOfAKind = sortedValueCounts.containsValue(3L);
            return new Hand(cards, isThreeOfAKind ? HandRank.THREE_OF_A_KIND : HandRank.TWO_PAIRS, calcTieRanks(sortedValueCounts));
        } else if (sortedValueCounts.keySet().size() == 2) {
            boolean isFourOfAKind = sortedValueCounts.containsValue(4L);
            return new Hand(cards, isFourOfAKind ? HandRank.FOUR_OF_A_KIND : HandRank.FULL_HOUSE, calcTieRanks(sortedValueCounts));
        } else {
            boolean isFlush = Arrays.stream(cards).map(Card::suit).distinct().count() == 1;
            boolean isStraight = (sortedValueCounts.lastKey().ordinal() - sortedValueCounts.firstKey().ordinal()) == 4;
            boolean isWheel = sortedValueCounts.lastKey().equals(ACE) && sortedValueCounts.lowerKey(ACE).equals(FIVE);

            if (isStraight) {
                return new Hand(cards, isFlush ? HandRank.STRAIGHT_FLUSH : HandRank.STRAIGHT, sortedValueCounts.lastKey());
            } else if (isWheel) {
                return new Hand(cards, isFlush ? HandRank.STRAIGHT_FLUSH : HandRank.STRAIGHT, FIVE);
            } else if (isFlush) {
                return new Hand(cards, HandRank.FLUSH, calcTieRanks(sortedValueCounts));
            } else {
                return new Hand(cards, HandRank.HIGH_CARD, calcTieRanks(sortedValueCounts));
            }
        }
    }

    /**
     * Calculates the tie-breaking ranks for a collection of cards based on their values and occurrence counts.
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
