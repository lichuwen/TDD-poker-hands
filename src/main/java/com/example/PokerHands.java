package com.example;

import java.util.*;

public class PokerHands {

    public static final String WHITE = "white";
    public static final String BLACK = "black";
    public static final String TIE = "Tie";

    public String run() {
        return "ABC";
    }

    public String play(List<String> whiteCards, List<String> blackCards) {
        CardType whiteCardType = generateCarType(whiteCards);
        CardType blackCarType = generateCarType(blackCards);
        if (whiteCardType.getCarType() > blackCarType.getCarType()) {
            return WHITE;
        } else if (whiteCardType.getCarType() < blackCarType.getCarType()) {
            return BLACK;
        }

        List<Integer> whiteCardNum = new ArrayList<>();
        for (String whiteCard : whiteCards) {
            whiteCardNum.add(transformToNumber(whiteCard.charAt(0)));
        }
        List<Integer> blackCardNum = new ArrayList<>();
        for (String blackCard : blackCards) {
            blackCardNum.add(transformToNumber(blackCard.charAt(0)));
        }
        Collections.sort(whiteCardNum);
        Collections.sort(blackCardNum);
        List<Map.Entry<Integer, Integer>> whiteNumberList = generateCardMap(whiteCardNum);
        List<Map.Entry<Integer, Integer>> blackNumberList = generateCardMap(blackCardNum);
        whiteNumberList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        blackNumberList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        StringBuilder whiteNum = new StringBuilder();
        StringBuilder blackNum = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : whiteNumberList) {
            whiteNum.append(entry.getKey());
        }
        for (Map.Entry<Integer, Integer> entry : blackNumberList) {
            blackNum.append(entry.getKey());
        }
        int compareResult = whiteNum.toString().compareTo(blackNum.toString());
        if (compareResult > 0) {
            return WHITE;
        } else if (compareResult < 0) {
            return BLACK;
        } else {
            return TIE;
        }
    }

    private List<Map.Entry<Integer, Integer>> generateCardMap(List<Integer> CardNum) {
        Map<Integer, Integer> whiteNumbers = new HashMap<>(6);
        for (Integer integer : CardNum) {
            if (whiteNumbers.containsKey(integer)) {
                whiteNumbers.put(integer, whiteNumbers.get(integer) + 1);
            } else {
                whiteNumbers.put(integer, 1);
            }
        }
        return new ArrayList<>(whiteNumbers.entrySet());
    }

    public CardType generateCarType(List<String> cards) {
        if (checkStraight(cards)) {
            for (int i = 0; i < cards.size() - 1; i++) {
                if (cards.get(i).charAt(1) != cards.get(i + 1).charAt(1)) {
                    return CardType.Straight;
                }
            }
            return CardType.StraightFlush;
        }
        Map<Character, Integer> counts = calculateCounts(cards);
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

    public Map<Character, Integer> calculateCounts(List<String> cards) {
        Map<Character, Integer> counts = new HashMap<>(5);
        for (String card : cards) {
            char cardNumber = card.charAt(0);
            if (counts.containsKey(cardNumber)) {
                counts.put(cardNumber, counts.get(cardNumber) + 1);
            } else {
                counts.put(cardNumber, 1);
            }
        }
        return counts;
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
