package com.customink.assignment.carddeck;

/**
 * Represents suit for cards.
 */
public enum Suit {
    SPADES("Spades"),
    HEARTS("Hearts"),
    DIAMONDS("Diamonds"),
    CLUBS("Clubs"),
    JOKER("Joker");

    private String name;
    
    private Suit(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
