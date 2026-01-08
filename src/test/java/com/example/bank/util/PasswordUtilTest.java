package com.example.bank.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordUtilTest {

    @Test
    void hashShouldNotBeNull() {
        String hash = PasswordUtil.hash("test123");
        assertNotNull(hash);
    }

    @Test
    void samePasswordShouldGenerateSameHash() {
        String h1 = PasswordUtil.hash("test123");
        String h2 = PasswordUtil.hash("test123");
        assertEquals(h1, h2);
    }

    @Test
    void differentPasswordShouldGenerateDifferentHash() {
        String h1 = PasswordUtil.hash("test123");
        String h2 = PasswordUtil.hash("test456");
        assertNotEquals(h1, h2);
    }
}
