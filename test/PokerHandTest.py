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

    def test_should_return_black_when_play_card_with_flush_given_2H3H5H9HTH_and_2H3H6STHKH(self):
        white = "2H 3H 5H 9H TH"
        black = "2H 3H 6H TH KH"
        winner = pokerHand.playCard(white.split(), black.split())
        self.assertEqual(winner, "black")

    def test_should_return_black_when_play_card_with_full_house_given_3H3D5S5C3D_and_4H4H4S3S3C(self):
        white = "3H 3D 5S 5C 3D"
        black = "4H 4H 4S 3S 3C"
        winner = pokerHand.playCard(white.split(), black.split())
        self.assertEqual(winner, "black")

    def test_should_return_black_when_play_card_with_full_house_given_2H3H5H9HTH_and_3H3HTSTS3C(self):
        white = "3H 3D 5S 5C 3D"
        black = "3H 3H TS TS 3C"
        winner = pokerHand.playCard(white.split(), black.split())
        self.assertEqual(winner, "black")

    def test_should_return_black_when_play_card_with_four_of_a_kind_given_3H5C3S3C3D_and_2H4H4S4S4C(self):
        white = "3H 5C 3S 3C 3D"
        black = "2H 4H 4S 4S 4C"
        winner = pokerHand.playCard(white.split(), black.split())
        self.assertEqual(winner, "black")

    def test_should_return_black_when_play_card_with_four_of_a_kind_given_2H3H5H9HTH_and_3H3H3STS3C(self):
        white = "3H 5C 3S 3C 3D"
        black = "3H 3H 3S TS 3C"
        winner = pokerHand.playCard(white.split(), black.split())
        self.assertEqual(winner, "black")

    def test_should_return_black_when_play_card_with_straight_flush_given_3H4H5H6H7H_and_4H5H6H7H8H(self):
        white = "3H 4H 5H 6H 7H"
        black = "4H 5H 6H 7H 8H"
        winner = pokerHand.playCard(white.split(), black.split())
        self.assertEqual(winner, "black")

    def test_should_return_white_when_play_card_with_different_type_given_3H4H5H6H7H_and_4H5D6S7C8D(self):
        white = "3H 4H 5H 6H 7H"
        black = "4H 5D 6S 7C 8D"
        winner = pokerHand.playCard(white.split(), black.split())
        self.assertEqual(winner, "white")

    def test_should_return_tie_when_play_card_with_high_card_type_given_2H3D5S9CKD_and_2D3H5C9SKH(self):
        white = "2H 3D 5S 9C KD"
        black = "2D 3H 5C 9S KH"
        winner = pokerHand.playCard(white.split(), black.split())
        self.assertEqual(winner, "tie")


if __name__ == '__main__':
    unittest.main()
