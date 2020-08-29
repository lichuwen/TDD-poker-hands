import sys
sys.path.append(r'/Users/lichuwen/BootCamp/TDD-poker-hands') # TODO: Should update your project root dir location
from src.PokerHand import PokerHand,CardType

import unittest

class PokerHandTest(unittest.TestCase):

    def test_should_return_straight_type_when_generate_card_type_given_2H3D5S9CKD(self):
        cards = "2H 3D 5S 9C KD"
        cardType = PokerHand.generateCardType(cards.split())
        self.assertEqual(cardType, CardType.Straight)

if __name__ == '__main__':
    unittest.main()