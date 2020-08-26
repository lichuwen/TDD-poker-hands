package com.example;

public enum CardType {
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

    private int getCarType() {
        return this.type;
    }

    private CardType(int type) {
        this.type = type;
    }

}
