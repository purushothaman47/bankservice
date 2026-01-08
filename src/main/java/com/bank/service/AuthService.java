package com.bank.service;

import com.bank.dao.UserDAO;
import com.bank.model.User;
import com.bank.util.PasswordUtil;

public class AuthService {

    private final UserDAO userDAO = new UserDAO();
    public boolean register(String username, String password) {

        if (userDAO.findByUsername(username) != null) {
            return false;
        }

        String hashed = PasswordUtil.hash(password);
        userDAO.save(username, hashed);
        return true;
    }


    public User login(String username, String rawPassword) {

        User user = userDAO.findByUsername(username);
        if (user == null) return null;

        String hashedInput = PasswordUtil.hash(rawPassword);

        if (!hashedInput.equals(user.getPassword())) {
            return null;
        }

        return user;
    }
}
