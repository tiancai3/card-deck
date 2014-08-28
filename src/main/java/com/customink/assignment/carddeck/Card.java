package com.customink.assignment.carddeck;

/**
 * A class representing a card.
 */
public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        if (suit == null) {
            throw new IllegalArgumentException("suit cannot be null");
        }
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Returns the rank of the card. Jokers don't
     * have rank and thus this method will return
     * null.
     *
     * @return
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Returns the suit of the card.
     *
     * @return
     */
    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        if (suit == Suit.JOKER) {
            return suit.toString();
        }
        return rank.toString() + " of " + suit.toString();
    }

    @Override
    public int hashCode() {
        int hashCode = suit.hashCode();
        if (rank != null) {
            hashCode += rank.hashCode();
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Card)) {
            return false;
        }
        if (o == this) {
            return true;
        }
        Card otherCard = (Card)o;
        return otherCard.rank == rank && otherCard.suit == suit;
    }
}
