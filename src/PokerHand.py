import sys
sys.path.append(r'/Users/lichuwen/BootCamp/TDD-poker-hands')
from src.CardType import CardType
from enum import Enum

CardType = Enum('CardType', ('HighCard', 'Pair', 'TwoPair', 'ThreeOfAKind', 'Straight', 'Flush', 'FullHouse', 'FourOfAKind', 'StraightFlush'))

class PokerHand:
    def __init__(self):
        pass


    def generateCardType(cards):
        for i in range(len(cards)-1):
            if cards[i][1] != cards[i+1][1]:
                return CardType.Straight
        return CardType.StraightFlush

    