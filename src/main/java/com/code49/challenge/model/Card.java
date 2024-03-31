package com.code49.challenge.model;

import static java.util.Comparator.comparing;

public record Card(CardSuit suit, CardValue value) implements Comparable<Card> {

    @Override
    public int compareTo(Card other) {
        return comparing(Card::value)
                .compare(this, other);
    }
}