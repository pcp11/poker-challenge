package com.code49.challenge;

import com.code49.challenge.model.Card;
import com.code49.challenge.model.Hand;

import java.util.List;

import static com.code49.challenge.model.CardSuit.*;
import static com.code49.challenge.model.CardValue.*;

public class App {

    public static final Hand HAND1 = Hand.of(List.of(new Card(CLUBS, FOUR), new Card(HEARTS, KING),
            new Card(CLUBS, KING), new Card(DIAMONDS, NINE), new Card(SPADES, EIGHT)));

    public static final Hand HAND2 = Hand.of(List.of(new Card(SPADES, ACE), new Card(DIAMONDS, SEVEN),
            new Card(SPADES, TWO), new Card(SPADES, THREE), new Card(CLUBS, EIGHT)));

    public static void main(String... args) {
        switch (HAND1.compareTo(HAND2)) {
            case -1 -> System.out.println("Hand 2 has won!");
            case 0 -> System.out.println("Tie!");
            case 1 -> System.out.println("Hand 1 has won!");
        }
    }
}
