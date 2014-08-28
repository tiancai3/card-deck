package com.customink.assignment.carddeck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

/**
 * Class representing a deck of cards.
 */
public class Deck {
    private final List<Card> cards;
    private final boolean useJokers;

    /**
     * Constructs a deck without jokers.
     */
    public Deck() {
        this(false);
    }

    /**
     * Constructs a deck with or without jokers depending
     * on useJokers.
     *
     * @param useJokers
     */
    public Deck(boolean useJokers) {
        this.useJokers = useJokers;
        cards = new ArrayList<Card>();
        reset();
    }

    /**
     * Reset the deck into initial state.
     */
    private void reset() {
        cards.clear();
        for(Rank rank: Rank.values()) {
            // add all suits but the joker
            for(Suit suit: EnumSet.complementOf(EnumSet.of(Suit.JOKER))) {
                cards.add(new Card(suit, rank));
            }
        }
        if (useJokers) {
            cards.add(new Card(Suit.JOKER, null));
            cards.add(new Card(Suit.JOKER, null));
        }
    }

    /**
     * Shuffles remaining cards in deck.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Put back all missing cards into the deck and shuffle them.
     */
    public void reshuffle() {
        reset();
        shuffle();
    }

    /**
     * Deals a card from the deck.
     *
     * @return
     */
    public Card deal() {
        if (hasMoreCards()) {
            return cards.remove(0);
        }
        throw new IllegalStateException("ran out of cards");
    }

    /**
     * Whether or not there are more cards.
     *
     * @return
     */
    public boolean hasMoreCards() {
        return !cards.isEmpty();
    }
}
