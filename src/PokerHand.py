import sys
sys.path.append(r'/Users/lichuwen/BootCamp/TDD-poker-hands') # TODO: Should update your project root dir location
from src.CardType import CardType
from enum import Enum

CardType = Enum('CardType', ('HighCard', 'Pair', 'TwoPair', 'ThreeOfAKind', 'Straight', 'Flush', 'FullHouse', 'FourOfAKind', 'StraightFlush'))

class PokerHand:

    def generateCardType(cards):
        cardNum = []
        for i in range(len(cards)):
            cardNum.append(cards[i][0])
        cardNum = list(map(int, cardNum))
        min = cardNum[0]
        for i in range(len(cardNum)):
            min += 1
            if (cardNum[i]+1 != min):
                return False
        return CardType.Straight

    