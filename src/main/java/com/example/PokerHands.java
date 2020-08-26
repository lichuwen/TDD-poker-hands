package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xhz
 */
public class PokerHands {

    public static final String WHITE = "white";
    public static final String BLACK = "black";
    public static final String TIE = "Tie";
    public static final int TWO_PAIR_SIZE = 3;
    public static final int HIGH_CARD_SIZE = 5;

    public String run() {
        return "ABC";
    }

    public String play(List<String> whiteCards, List<String> blackCards) {
        CardType whiteCardType = generateCarType(whiteCards);
        CardType blackCarType = generateCarType(blackCards);
        if (whiteCardType.getCardType() > blackCarType.getCardType()) {
            return WHITE;
        } else if (whiteCardType.getCardType() < blackCarType.getCardType()) {
            return BLACK;
        }
        List<Integer> whiteCardNum = generateCardNumberList(whiteCards);
        List<Integer> blackCardNum = generateCardNumberList(blackCards);
        List<Map.Entry<Integer, Long>> whiteNumberList = generateCardMap(whiteCardNum);
        List<Map.Entry<Integer, Long>> blackNumberList = generateCardMap(blackCardNum);
        whiteNumberList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        blackNumberList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        String whiteNum = whiteNumberList.stream().map(Map.Entry::getKey)
                .map(String::valueOf).collect(Collectors.joining(""));
        String blackNum = blackNumberList.stream().map(Map.Entry::getKey)
                .map(String::valueOf).collect(Collectors.joining(""));
        int compareResult = whiteNum.compareTo(blackNum);
        if (compareResult > 0) {
            return WHITE;
        } else if (compareResult < 0) {
            return BLACK;
        } else {
            return TIE;
        }
    }

    private List<Integer> generateCardNumberList(List<String> cards) {
        return cards.stream().map(card -> transformToNumber(card.charAt(0))).collect(Collectors.toList());
    }

    private List<Map.Entry<Integer, Long>> generateCardMap(List<Integer> cardNum) {
        return new ArrayList<>(cardNum.stream()
                .collect(Collectors.groupingBy(card -> card, Collectors.counting())).entrySet());
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
        Map<Character, Long> counts = calculateCounts(cards);
        for (Map.Entry<Character, Long> entry : counts.entrySet()) {
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
                break;
            }
        }
        if (flush) {
            return CardType.Flush;
        }
        if (counts.size() == TWO_PAIR_SIZE) {
            return CardType.TwoPair;
        }
        if (counts.size() == HIGH_CARD_SIZE) {
            return CardType.HighCard;
        }
        return CardType.Pair;
    }

    public Map<Character, Long> calculateCounts(List<String> cards) {
        return cards.stream().collect(Collectors.groupingBy(card -> card.charAt(0), Collectors.counting()));
    }

    public boolean checkStraight(List<String> cards) {
        List<Integer> cardNumbers = cards.stream().map(card -> transformToNumber(card.charAt(0)))
                .sorted().collect(Collectors.toList());
        int min = cardNumbers.get(0);
        for (int i = 1; i < cardNumbers.size(); i++) {
            if (cardNumbers.get(i) != ++min) {
                return false;
            }
        }
        return true;
    }

    public int transformToNumber(char character) {
        switch (character) {
            case 'T':
                return 10;
            case 'J':
                return 11;
            case 'Q':
                return 12;
            case 'K':
                return 13;
            case 'A':
                return 14;
            default:
                return character - '0';
        }
    }
}
