package com.phoenix.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void hashIsSixteenBytes() throws Exception {
        assertEquals(16, App.hashPassword("secret").length);
    }
}
