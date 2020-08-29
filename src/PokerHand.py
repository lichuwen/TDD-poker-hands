import sys
sys.path.append(r'/Users/lichuwen/BootCamp/TDD-poker-hands')
from src.CardType import CardType
from enum import Enum
from collections import Counter

CardType = Enum('CardType', ('HighCard', 'Pair', 'TwoPair', 'ThreeOfAKind', 'Straight', 'Flush', 'FullHouse', 'FourOfAKind', 'StraightFlush'))
switch = {'T':10, 'J':11, 'Q':12, 'K':13, 'A': 14}

class PokerHand:

    def __init__(self):
        pass

    def getNumber(self,cards):
        cardNum = []
        for i in range(len(cards)):
            if switch.__contains__(cards[i][0]):
                cardNum.append(switch[cards[i][0]])
            else:
                cardNum.append(cards[i][0])
        cardNum = list(map(int, cardNum))
        return cardNum

    def isStraight(self,cards):
        cardNum = self.getNumber(cards)
        min = cardNum[0]
        for i in range(len(cardNum)):
            min += 1
            if (cardNum[i]+1 != min):
                return False
        return True

    def isFlush(self,cards):
        for i in range(len(cards)-1):
            if (cards[i][1] != cards[i+1][1]):
                return False
        return True

    def generateCardType(self,cards):
        if self.isStraight(cards):
            for i in range(len(cards)-1):
                if cards[i][1] != cards[i+1][1]:
                    return CardType.Straight
            return CardType.StraightFlush
        cardNumCounter = Counter(self.getNumber(cards))
        for key,value in cardNumCounter.items():
            if value == 4:
                return CardType.FourOfAKind
            elif value == 3:
                if len(cardNumCounter) == 2:
                    return CardType.FullHouse
                return CardType.ThreeOfAKind
        if self.isFlush(cards):
            return CardType.Flush
        return False

    