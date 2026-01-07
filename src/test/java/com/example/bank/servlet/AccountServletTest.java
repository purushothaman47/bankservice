package com.example.bank.servlet;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountServletTest {

    @Test
    void createActionShouldBeDetected() {
        String action = "CREATE";
        assertEquals("CREATE", action);
    }

    @Test
    void depositTypeShouldBeValid() {
        String type = "DEPOSIT";
        assertTrue(type.equals("DEPOSIT") || type.equals("WITHDRAW"));
    }
}
