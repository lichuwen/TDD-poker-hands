import sys
sys.path.append(r'/Users/lichuwen/BootCamp/TDD-poker-hands')
from src.PokerHand import PokerHand

import unittest

pokerHand = PokerHand()

class PokerHandTest(unittest.TestCase):

    def test_should_return_black_when_play_card_with_high_card_given_2H3D5S9CKD_and_2C3H4S8CAH(self):
           white = "2H 3D 5S 9C KD"
           black = "2C 3H 4S 8C AH"
           winner = pokerHand.playCard(white.split(),black.split())
           self.assertEqual(winner, "black")

if __name__ == '__main__':
    unittest.main()