package com.customink.assignment.carddeck;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test for the Deck class.
 */
public class TestDeck extends Assert {

    /**
     * Test a deck with no jokers. Make sure there are no duplicate cards.
     * Also make sure that count of cards is correct.
     */
    @Test
    public void testDeck() {
        Deck deck = new Deck();
        Set<Card> dealtCards = new HashSet<Card>();
        try {
            Card prevCard = null;
            while(deck.hasMoreCards()) {
                Card card = deck.deal();
                if (dealtCards.contains(card)) {
                    fail("found a duplicate card " + card);
                }
                dealtCards.add(card);
                if (prevCard != null) {
                    assertTrue(prevCard.getRank().ordinal() <= card.getRank().ordinal(),
                               "cards are not ordered");
                }
                prevCard = card;
            }
        }
        catch(Exception e) {
            fail("exception was thrown while dealing cards", e);
        }
        assertEquals(dealtCards.size(), 52, "there isn't 52 cards in the deck");
        
        // try to deal one more time to test that exception is thrown
        try {
            deck.deal();
            fail("exception not thrown as expected");
        }
        catch(Exception e) {
        }
    }

    /**
     * Test a deck with a joker. Makes sure that cards are in order.
     * Also ensure that count is correct.
     */
    @Test
    public void testDeckWithJoker() {
        Deck deck = new Deck(true);
        int count = 0;
        try {
            Card prevCard = null;
            while(deck.hasMoreCards()) {
                Card currCard = deck.deal();
                count++;
                if (prevCard != null) {
                    if (prevCard.getSuit() != Suit.JOKER &&
                        currCard.getSuit() != Suit.JOKER) {
                        assertTrue(prevCard.getRank().ordinal() <= currCard.getRank().ordinal(),
                                   "cards are not ordered");
                    }
                }
                prevCard = currCard;
                
            }
        }
        catch(Exception e) {
            fail("exception was thrown while dealing cards", e);
        }
        assertEquals(count, 54, "there isn't 54 cards in the deck");
        
        // try to deal one more time to test that exception is thrown
        try {
            deck.deal();
            fail("exception not thrown as expected");
        }
        catch(Exception e) {
        }
    }

    /**
     * Test shuffling of a deck with no jokers. Verifies that
     * shuffling still has 52 cards and no cards repeat.
     */
    @Test
    public void testShuffle() {
        Deck deck = new Deck();
        deck.shuffle();
        int count = 0;
        try {
            boolean cardsAreOrdered = true;
            Card prevCard = null;
            while(deck.hasMoreCards()) {
                Card currCard = deck.deal();
                count++;
                if (prevCard != null &&
                    prevCard.getRank().ordinal() > currCard.getRank().ordinal()) {
                    cardsAreOrdered = false;
                }
                prevCard = currCard;
            }
            assertFalse(cardsAreOrdered, "cards are ordered");
        }
        catch(Exception e) {
            fail("exception was thrown while dealing cards", e);
        }
        assertEquals(count, 52, "there isn't 52 cards in the deck");
    }

    /**
     * Test after dealing cards that reshuffling puts all cards back.
     */
    @Test
    public void testReshuffle() {
        Deck deck = new Deck();
        int count = 0;
        try {
            while(count < 20) {
                count++;
                deck.deal();
            }
        }
        catch(Exception e) {
            fail("exception was thrown while dealing cards", e);
        }

        deck.reshuffle();
        Set<Card> dealtCards = new HashSet<Card>();
        try {
            while(deck.hasMoreCards()) {
                Card card = deck.deal();
                if (dealtCards.contains(card)) {
                    fail("found duplicate card " + card);
                }
                dealtCards.add(card);
            }
        }
        catch(Exception e) {
            fail("exception was thrown while dealing cards", e);
        }
        assertEquals(dealtCards.size(), 52, "after reshuffling there wasn't 52 cards");
    }

    /**
     * Test after dealing cards that reshuffling puts all cards back.
     */
    @Test
    public void testReshuffleWithJoker() {
        Deck deck = new Deck(true);
        int count = 0;
        try {
            while(count < 20) {
                count++;
                deck.deal();
            }
        }
        catch(Exception e) {
            fail("exception was thrown while dealing cards", e);
        }

        deck.reshuffle();
        Set<Card> dealtCards = new HashSet<Card>();
        count = 0;
        try {
            while(deck.hasMoreCards()) {
                Card card = deck.deal();
                if (card.getSuit() != Suit.JOKER &&
                    dealtCards.contains(card)) {
                    fail("found duplicate card " + card);
                }
                dealtCards.add(card);
                count++;
            }
        }
        catch(Exception e) {
            fail("exception was thrown while dealing cards", e);
        }
        assertEquals(count, 54, "after reshuffling there wasn't 54 cards");
    }

    /**
     * Test the card toString methods. Use the toString to verify no duplicates.
     */
    @Test
    public void testToString() {
        Deck deck = new Deck();
        Set<String> cardToStrings = new HashSet<String>();
        try {
            while(deck.hasMoreCards()) {
                String cardStr = deck.deal().toString();
                if (cardToStrings.contains(cardStr)) {
                    fail("found duplicate card string " + cardStr);
                }
                cardToStrings.add(cardStr);
                System.out.println(cardStr);
            }
        }
        catch(Exception e) {
            fail("exception was thrown while dealing cards", e);
        }
    }

    /**
     * verify that there are at most 4 of each rank. Verify that there are 2 Jokers
     */
    @Test
    public void testCardCounts() {
        Deck deck = new Deck(true);
        deck.shuffle();
        Map<Rank, Integer> rankCountMap = new HashMap<Rank, Integer>();
        while(deck.hasMoreCards()) {
            Card card = deck.deal();
            Integer count = rankCountMap.get(card.getRank());
            if (count == null) {
                rankCountMap.put(card.getRank(), 1);
            }
            else {
                count++;
                assertFalse(count > 4, "there are more than 4 of " + card);
                rankCountMap.put(card.getRank(), count);
            }
        }
        assertEquals(rankCountMap.get(null), new Integer(2), "There are not 2 jokers");
    }
}
