
package com.example.bank.dao;

import com.example.bank.config.DBConfig;
import com.example.bank.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class UserDao {

    private static final Logger log =
            LoggerFactory.getLogger(UserDao.class);


    public boolean register(String u, String p) throws Exception {

        log.info("Registering user");

        try (Connection c = DBConfig.get().getConnection()) {
            PreparedStatement ps = c.prepareStatement(
                    "insert into users(username,password) values(?,?)");
            ps.setString(1, u);
            ps.setString(2, PasswordUtil.hash(p));

            log.info("User registration");

            return ps.executeUpdate() == 1;
        }
    }
    public boolean login(String u, String p) throws Exception {

        log.info("Login attempt");

        try (Connection c = DBConfig.get().getConnection()) {

            PreparedStatement ps = c.prepareStatement(
                    "select id from users where username=? and password=?");

            ps.setString(1, u);
            ps.setString(2, PasswordUtil.hash(p));

            log.info("Login result ");

            return ps.executeQuery().next();
        }
    }
}
