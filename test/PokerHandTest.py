import sys
sys.path.append(r'/Users/lichuwen/BootCamp/TDD-poker-hands') # TODO: Should update your project root dir location
from src.PokerHand import PokerHand,CardType

import unittest

class PokerHandTest(unittest.TestCase):

    def test_should_return_straight_type_when_generate_card_type_given_3H4D5S6C7D(self):
        cards = "3H 4D 5S 6C 7D"
        cardType = PokerHand.generateCardType(cards.split())
        self.assertEqual(cardType, CardType.Straight)

    def test_should_return_straight_flush_type_when_generate_card_type_given_3H4H5H6H7H(self):
        cards = "3H 4H 5H 6H 7H"
        cardType = PokerHand.generateCardType(cards.split())
        self.assertEqual(cardType, CardType.StraightFlush)

if __name__ == '__main__':
    unittest.main()