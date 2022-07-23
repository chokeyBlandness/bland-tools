package com.bland.tools.util;

import org.junit.jupiter.api.Test;

class BlandPasswordGeneratorTest {

    @Test
    void generateRandomPassword() {
        BlandPasswordGenerator generator = new BlandPasswordGenerator();
        System.out.println(generator.generateRandomPassword(10, 2));
    }
}