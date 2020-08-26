package com.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoTest {
    private static Demo demo;

    @BeforeAll
    public static void setUp() throws Exception {
        demo = new Demo();
    }

    @Test
    public void test01() {
        String result = demo.run();
        assertThat(result).isEqualTo("ABC");
    }

    @Test
    void should_return_correct_card_state_when_generate_car_type_given_2H3D5S9CKD() {
        //given
        String cards = "2H 3D 5S 9C KD";
        //when
        CardState cardState = demo.generateCarType(Arrays.asList(cards.split(" ")));
        //then
        assertEquals(cardState.cardType, CardType.HighCard);
        assertEquals(cardState.biggestNumber, 13);
    }

    @Test
    void should_return_correct_card_state_when_generate_car_type_given_3A3D5S9CKD() {
        //given
        String cards = "3A 3D 5S 9C KD";
        //when
        CardState cardState = demo.generateCarType(Arrays.asList(cards.split(" ")));
        //then
        assertEquals(cardState.cardType, CardType.Pair);
        assertEquals(cardState.biggestNumber, 3);
    }
}