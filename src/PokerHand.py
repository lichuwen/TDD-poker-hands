import sys
sys.path.append(r'/Users/lichuwen/BootCamp/TDD-poker-hands')
from src.CardType import CardType
from enum import Enum

CardType = Enum('CardType', ('HighCard', 'Pair', 'TwoPair', 'ThreeOfAKind', 'Straight', 'Flush', 'FullHouse', 'FourOfAKind', 'StraightFlush'))

class PokerHand:

    def __init__(self):
        pass

    def isStraight(self,cards):
        cardNum = []
        for i in range(len(cards)):
            cardNum.append(cards[i][0])
        cardNum = list(map(int, cardNum))
        min = cardNum[0]
        for i in range(len(cardNum)):
            min += 1
            if (cardNum[i]+1 != min):
                return False
        return True

    def generateCardType(self,cards):
        if self.isStraight(cards):
            for i in range(len(cards)-1):
                if cards[i][1] != cards[i+1][1]:
                    return CardType.Straight
        return CardType.StraightFlush

    