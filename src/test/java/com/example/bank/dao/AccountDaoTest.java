package com.example.bank.dao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountDaoTest {

    @Test
    void balanceCalculationLogicTest() {

        double balance = 1000;

        balance += 500;
        assertEquals(1500, balance);

        balance -= 300;
        assertEquals(1200, balance);
    }
}
