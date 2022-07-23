package com.bland.tools.lottery;

import java.util.HashSet;
import java.util.Set;

/**
 * @author cqk
 * @since 2022/7/23
 */
public class RandomLotteryGenerator {

    public int[] generateRandomTwoColorLottery() {
        int[] res = new int[7];
        // blue
        res[0] = (int) (Math.random() * 16 + 1);
        // red
        Set<Integer> set = new HashSet<>();
        while (set.size() < 6) {
            int red = (int) (Math.random() * 33 + 1);
            set.add(red);
        }
        int index = 1;
        for (Integer red : set) {
            res[index++] = red;
        }
        return res;
    }

}
