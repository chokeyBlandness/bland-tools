package com.bland.tools.lottery;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class RandomLotteryGeneratorTest {

    @Test
    public void randomTwoColorLottery() {
        RandomLotteryGenerator generator = new RandomLotteryGenerator();
        System.out.println(Arrays.toString(generator.generateRandomTwoColorLottery()));
    }


}