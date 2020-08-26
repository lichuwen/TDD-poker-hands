package com.example;

import java.util.*;

public class Demo {

    public String run() {
        return "ABC";
    }

    public CardType generateCarType(List<String> cards) {
        int biggestNumber = calculateBiggestNumber(cards);
        if (checkStraight(cards)) {
            for (int i = 0; i < cards.size() - 1; i++) {
                if (cards.get(i).charAt(1) != cards.get(i + 1).charAt(1)) {
                    return CardType.Straight;
                }
            }
            return CardType.StraightFlush;
        }
        Map<Character, Integer> counts = new HashMap<>(5);
        for (String card : cards) {
            char cardNumber = card.charAt(0);
            if (counts.containsKey(cardNumber)) {
                counts.put(cardNumber, counts.get(cardNumber) + 1);
            } else {
                counts.put(cardNumber, 1);
            }
        }
        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == 4) {
                return CardType.FourOfAKind;
            }
            if (entry.getValue() == 3) {
                if (counts.size() == 2) {
                    return CardType.FullHouse;
                } else {
                    return CardType.ThreeOfAKind;
                }
            }
        }
        boolean flush = true;
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).charAt(1) != cards.get(i + 1).charAt(1)) {
                flush = false;
            }
        }
        if (flush) {
            return CardType.Flush;
        }
        if (counts.size() == 3) {
            return CardType.TwoPair;
        }
        if (counts.size() == 5) {
            return CardType.HighCard;
        }
        return CardType.Pair;
    }

    public boolean checkStraight(List<String> cards) {
        List<Integer> cardNumbers = new ArrayList<>();
        for (String card : cards) {
            cardNumbers.add(transformToNumber(card.charAt(0)));
        }
        Collections.sort(cardNumbers);
        int min = cardNumbers.get(0);
        for (int i = 1; i < cardNumbers.size(); i++) {
            if (cardNumbers.get(i) != ++min) {
                return false;
            }
        }
        return true;
    }

    public int calculateBiggestNumber(List<String> cards) {
        int maxNumber = 0;
        for (String card : cards) {
            int cardNumber = transformToNumber(card.charAt(0));
            maxNumber = Math.max(cardNumber, maxNumber);
        }
        return maxNumber;
    }

    public int transformToNumber(char character) {
        if (character == 'T') {
            return 10;
        }
        if (character == 'J') {
            return 11;
        }
        if (character == 'Q') {
            return 12;
        }
        if (character == 'K') {
            return 13;
        }
        if (character == 'A') {
            return 14;
        }
        return character - '0';
    }
}
