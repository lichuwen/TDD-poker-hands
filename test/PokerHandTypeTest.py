import sys
sys.path.append(r'/Users/lichuwen/BootCamp/TDD-poker-hands')
from src.PokerHandType import PokerHandType,CardType

import unittest

pokerHand = PokerHandType()

class PokerHandTypeTest(unittest.TestCase):

    def test_should_return_straight_type_when_generate_card_type_given_3H4D5S6C7D(self):
        cards = "3H 4D 5S 6C 7D"
        cardType = pokerHand.generateCardType(cards.split())
        self.assertEqual(cardType, CardType.Straight)

    def test_should_return_straight_flush_type_when_generate_card_type_given_3H4H5H6H7H(self):
        cards = "3H 4H 5H 6H 7H"
        cardType = pokerHand.generateCardType(cards.split())
        self.assertEqual(cardType, CardType.StraightFlush)

    def test_should_return_four_of_a_kind_type_when_generate_card_type_given_3H3D3S3D5C(self):
         cards = "3H 3D 3S 3D 5C"
         cardType = pokerHand.generateCardType(cards.split())
         self.assertEqual(cardType, CardType.FourOfAKind)

    def test_should_return_three_of_a_kind_type_when_generate_card_type_given_3H3D3D5S9C(self):
          cards = "3H 3D 3D 5S 9C"
          cardType = pokerHand.generateCardType(cards.split())
          self.assertEqual(cardType, CardType.ThreeOfAKind)

    def test_should_return_full_house_type_when_generate_card_type_given_3H3D5S5C3D(self):
           cards = "3H 3D 5S 5C 3D"
           cardType = pokerHand.generateCardType(cards.split())
           self.assertEqual(cardType, CardType.FullHouse)

    def test_should_return_flush_type_when_generate_card_type_given_2H3H5H9HKH(self):
           cards = "2H 3H 5H 9H KH"
           cardType = pokerHand.generateCardType(cards.split())
           self.assertEqual(cardType, CardType.Flush)

    def test_should_return_two_pair_type_when_generate_card_type_given_3H3D5S5D9C(self):
           cards = "3H 3D 5S 5D 9C"
           cardType = pokerHand.generateCardType(cards.split())
           self.assertEqual(cardType, CardType.TwoPair)

    def test_should_return_high_card_type_when_generate_card_type_given_2H3D5S9CKD(self):
           cards = "2H 3D 5S 9C KD"
           cardType = pokerHand.generateCardType(cards.split())
           self.assertEqual(cardType, CardType.HighCard)

    def test_should_return_pair_type_when_generate_card_type_given_3A3D5STCKD(self):
           cards = "3A 3D 5S TC KD"
           cardType = pokerHand.generateCardType(cards.split())
           self.assertEqual(cardType, CardType.Pair)

if __name__ == '__main__':
    unittest.main()