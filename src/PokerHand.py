import sys
sys.path.append(r'/Users/lichuwen/BootCamp/TDD-poker-hands') # TODO: Should update your project root dir location
from src.CardType import CardType
from enum import Enum

CardType = Enum('CardType', ('HighCard', 'Pair', 'TwoPair', 'ThreeOfAKind', 'Straight', 'Flush', 'FullHouse', 'FourOfAKind', 'StraightFlush'))

class PokerHand:

    def generateCardType(cards):
        return CardType.Straight

    