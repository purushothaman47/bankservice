package com.example.bank.dao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    @Test
    void loginShouldReturnTrueForValidUser() throws Exception {

        UserDao dao = new UserDao() {
            @Override
            public boolean login(String u, String p) {
                return true;
            }
        };

        boolean result = dao.login("raj", "1234");
        assertTrue(result);
    }

    @Test
    void InvalidUser() throws Exception {

        UserDao dao = new UserDao() {
            @Override
            public boolean login(String u, String p) {
                return false;
            }
        };

        boolean result = dao.login("raj", "wrong");
        assertFalse(result);
    }
}
