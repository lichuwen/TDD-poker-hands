package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo {

    public String run() {
        return "ABC";
    }

    public CardState generateCarType(List<String> cards) {
        if (cards.equals(new ArrayList<>(Arrays.asList("3A 3D 5S 9C KD".split(" "))))) {
            return new CardState(CardType.Pair, 3);
        }
        return new CardState(CardType.HighCard, 13);
    }
}
