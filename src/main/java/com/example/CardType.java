package com.example;

/**
 * @author xhz
 */

public enum CardType {
    //use for type of five cards
    HighCard(1),
    Pair(2),
    TwoPair(3),
    ThreeOfAKind(4),
    Straight(5),
    Flush(6),
    FullHouse(7),
    FourOfAKind(8),
    StraightFlush(9);
    private final int type;

    public int getCardType() {
        return this.type;
    }

    CardType(int type) {
        this.type = type;
    }

}
