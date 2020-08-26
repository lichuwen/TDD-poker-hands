package com.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerHandsTest {
    private static PokerHands pokerHands;

    @BeforeAll
    public static void setUp() throws Exception {
        pokerHands = new PokerHands();
    }

    @Test
    public void test01() {
        String result = pokerHands.run();
        assertThat(result).isEqualTo("ABC");
    }

    @Test
    void should_return_correct_card_state_when_generate_car_type_given_2H3D5S9CKD() {
        //given
        String cards = "2H 3D 5S 9C KD";
        //when
        CardType cardType = pokerHands.generateCarType(Arrays.asList(cards.split(" ")));
        //then
        assertEquals(cardType, CardType.HighCard);
    }

    @Test
    void should_return_correct_card_state_when_generate_car_type_given_3A3D5S9CKD() {
        //given
        String cards = "3A 3D 5S 9C KD";
        //when
        CardType cardType = pokerHands.generateCarType(Arrays.asList(cards.split(" ")));
        //then
        assertEquals(cardType, CardType.Pair);
    }

    @Test
    void should_return_correct_card_state_when_generate_car_type_given_3H3D5S5D9C() {
        //given
        String cards = "3H 3D 5S 5D 9C";
        //when
        CardType cardType = pokerHands.generateCarType(Arrays.asList(cards.split(" ")));
        //then
        assertEquals(cardType, CardType.TwoPair);
    }

    @Test
    void should_return_correct_card_state_when_generate_car_type_given_3H3D3D5S9C() {
        //given
        String cards = "3H 3D 3D 5S 9C";
        //when
        CardType cardType = pokerHands.generateCarType(Arrays.asList(cards.split(" ")));
        //then
        assertEquals(cardType, CardType.ThreeOfAKind);
    }

    @Test
    void should_return_correct_card_state_when_generate_car_type_given_3H4D5S6C7D() {
        //given
        String cards = "3H 4D 5S 6C 7D";
        //when
        CardType cardType = pokerHands.generateCarType(Arrays.asList(cards.split(" ")));
        //then
        assertEquals(cardType, CardType.Straight);
    }

    @Test
    void should_return_correct_card_state_when_generate_car_type_given_2H3H5H9HKH() {
        //given
        String cards = "2H 3H 5H 9H KH";
        //when
        CardType cardType = pokerHands.generateCarType(Arrays.asList(cards.split(" ")));
        //then
        assertEquals(cardType, CardType.Flush);
    }

    @Test
    void should_return_correct_card_state_when_generate_car_type_given_3H3D5S5C3D() {
        //given
        String cards = "3H 3D 5S 5C 3D";
        //when
        CardType cardType = pokerHands.generateCarType(Arrays.asList(cards.split(" ")));
        //then
        assertEquals(cardType, CardType.FullHouse);
    }

    @Test
    void should_return_correct_card_state_when_generate_car_type_given_3H3D3S3D5C() {
        //given
        String cards = "3H 3D 3S 3D 5C";
        //when
        CardType cardType = pokerHands.generateCarType(Arrays.asList(cards.split(" ")));
        //then
        assertEquals(cardType, CardType.FourOfAKind);
    }

    @Test
    void should_return_correct_card_state_when_generate_car_type_given_3H4H5H6H7H() {
        //given
        String cards = "3H 4H 5H 6H 7H";
        //when
        CardType cardType = pokerHands.generateCarType(Arrays.asList(cards.split(" ")));
        //then
        assertEquals(cardType, CardType.StraightFlush);
    }

    @Test
    void should_return_white_when_play_card_with_high_card_given_2H3D5S9CKD_and_2C3H4S8CAH() {
        //given
        String white = "2H 3D 5S 9C KD";
        String black = "2C 3H 4S 8C AH";
        //when
        String result = pokerHands.play(Arrays.asList(white.split(" ")),
                Arrays.asList(black.split(" ")));
        //then
        assertEquals(result, PokerHands.WHITE);
    }

    @Test
    void should_return_white_when_play_card_with_pair_given_3H3D5S9CKD_and_4H4D5S9CKD() {
        //given
        String white = "3H 3D 5S 9C KD";
        String black = "4H 4D 5S 9C KD";
        //when
        String result = pokerHands.play(Arrays.asList(white.split(" ")),
                Arrays.asList(black.split(" ")));
        //then
        assertEquals(result, PokerHands.BLACK);
    }

    @Test
    void should_return_white_when_play_card_with_two_pair_given_3H3D5D9CKD_and_3H3D6S6C9C() {
        //given
        String white = "3H 3D 5D 9C KD";
        String black = "3H 3D 6S 6C 9C";
        //when
        String result = pokerHands.play(Arrays.asList(white.split(" ")),
                Arrays.asList(black.split(" ")));
        //then
        assertEquals(result, PokerHands.BLACK);
    }

    @Test
    void should_return_white_when_play_card_with_three_of_a_kind_given_3H3D3D5S9CKD_and_4H4D4D5S9C() {
        //given
        String white = "3H 3D 3D 9C KD";
        String black = "4H 4D 4D 5S 9C";
        //when
        String result = pokerHands.play(Arrays.asList(white.split(" ")),
                Arrays.asList(black.split(" ")));
        //then
        assertEquals(result, PokerHands.BLACK);
    }

    @Test
    void should_return_white_when_play_card_with_straight_given_3H4D5S6C7D_and_4H5D6S7C8D() {
        //given
        String white = "3H 4D 5S 6C 7D";
        String black = "4H 5D 6S 7C 8D";
        //when
        String result = pokerHands.play(Arrays.asList(white.split(" ")),
                Arrays.asList(black.split(" ")));
        //then
        assertEquals(result, PokerHands.BLACK);
    }

    @Test
    void should_return_white_when_play_card_with_flush_given_2H3H5H9HKH_and_2S3S5S9SAS() {
        //given
        String white = "2H 3H 5H 9H KH";
        String black = "2S 3S 5S 9S AS";
        //when
        String result = pokerHands.play(Arrays.asList(white.split(" ")),
                Arrays.asList(black.split(" ")));
        //then
        assertEquals(result, PokerHands.BLACK);
    }

    @Test
    void should_return_white_when_play_card_with_full_house_given_3H3D5S5C3D_and_4H4D4D3S3C() {
        //given
        String white = "3H 3D 5S 5C 3D";
        String black = "4H 4D 4D 3S 3C";
        //when
        String result = pokerHands.play(Arrays.asList(white.split(" ")),
                Arrays.asList(black.split(" ")));
        //then
        assertEquals(result, PokerHands.BLACK);
    }

    @Test
    void should_return_white_when_play_card_with_four_of_a_kind_given_3H3D3S3D5C_and_4H4D4S4D3C() {
        //given
        String white = "3H 3D 3S 3D 5C";
        String black = "4H 4D 4S 4D 3C";
        //when
        String result = pokerHands.play(Arrays.asList(white.split(" ")),
                Arrays.asList(black.split(" ")));
        //then
        assertEquals(result, PokerHands.BLACK);
    }

    @Test
    void should_return_white_when_play_card_with_straight_flush_given_3H4H5H6H7H_and_4H5H6H7H8H() {
        //given
        String white = "3H 4H 5H 6H 7H";
        String black = "4H 5H 6H 7H 8H";
        //when
        String result = pokerHands.play(Arrays.asList(white.split(" ")),
                Arrays.asList(black.split(" ")));
        //then
        assertEquals(result, PokerHands.BLACK);
    }

}