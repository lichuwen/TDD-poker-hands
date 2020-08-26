package com.example;

public class Demo {

    public String run() {
        return "ABC";
    }

    public CardState generateCarType(String[] cards) {
        return new CardState(CardType.HighCard, 13);
    }
}
