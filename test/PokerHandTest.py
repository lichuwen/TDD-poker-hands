import sys

sys.path.append(r'/Users/lichuwen/BootCamp/TDD-poker-hands')
from src.PokerHand import PokerHand

import unittest

pokerHand = PokerHand()


class PokerHandTest(unittest.TestCase):

    def test_should_return_black_when_play_card_with_high_card_given_2H3D5S9CKD_and_2C3H4S8CAH(self):
        white = "2H 3D 5S 9C KD"
        black = "2C 3H 4S 8C AH"
        winner = pokerHand.playCard(white.split(), black.split())
        self.assertEqual(winner, "black")

    def test_should_return_black_when_play_card_with_pair_given_3H3D5S9CKD_and_4H4D5S9CKD(self):
        white = "3H 3D 5S 9C KD"
        black = "4H 4D 5S 9C KD"
        winner = pokerHand.playCard(white.split(), black.split())
        self.assertEqual(winner, "black")

    def test_should_return_black_when_play_card_with_pair_given_3H3D5S9CKD_and_3H3D5STCKD(self):
        white = "3H 3D 5S 9C KD"
        black = "3H 3D 5S TC KD"
        winner = pokerHand.playCard(white.split(), black.split())
        self.assertEqual(winner, "black")

    def test_should_return_black_when_play_card_with_two_pair_given_3H3D5S5D9C_and_3H3D9C6S6D(self):
        white = "3H 3D 5S 5D 9C"
        black = "3H 3D 9C 6S 6D"
        winner = pokerHand.playCard(white.split(), black.split())
        self.assertEqual(winner, "black")

    def test_should_return_black_when_play_card_with_two_pair_given_3H3D5S5D9C_and_5S5D3H3DTC(self):
        white = "3H 3D 5S 5D 9C"
        black = "5S 5D 3H 3D TC"
        winner = pokerHand.playCard(white.split(), black.split())
        self.assertEqual(winner, "black")

    def test_should_return_black_when_play_card_with_three_of_a_kind_given_3H3D3D5S9C_and_5S4D4D9C4H(self):
        white = "3H 3D 3D 5S 9C"
        black = "5S 4D 4D 9C 4H"
        winner = pokerHand.playCard(white.split(), black.split())
        self.assertEqual(winner, "black")

    def test_should_return_black_when_play_card_with_three_of_a_kind_given_5S9C3H3D3C_and_9CAS3H3D3C(self):
        white = "5S 9C 3H 3D 3C"
        black = "9C AS 3H 3D 3C"
        winner = pokerHand.playCard(white.split(), black.split())
        self.assertEqual(winner, "black")

    def test_should_return_black_when_play_card_with_straight_given_3D4H5H6H7H_and_4H5H6S7H8H(self):
        white = "3D 4H 5H 6H 7H"
        black = "4H 5H 6S 7H 8H"
        winner = pokerHand.playCard(white.split(), black.split())
        self.assertEqual(winner, "black")


if __name__ == '__main__':
    unittest.main()
