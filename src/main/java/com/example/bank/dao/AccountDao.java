package com.example.bank.dao;

import com.example.bank.config.DBConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class AccountDao {

    private static final Logger log =
            LoggerFactory.getLogger(AccountDao.class);

    public int createAccount(int userId) throws Exception {

        try (Connection c = DBConfig.get().getConnection()) {
            log.info("Creating account");

            PreparedStatement ps = c.prepareStatement(
                    "insert into accounts(user_id, balance) values (?, 0)",
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setInt(1, userId);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            log.info("Account created successfully");
            return rs.getInt(1);

        }
    }

    public double getBalance(int accId) throws Exception {
        log.info("Fetching balance");

        try (Connection c = DBConfig.get().getConnection()) {
            PreparedStatement ps = c.prepareStatement(
                    "select balance from accounts where id=?");

            ps.setInt(1, accId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            log.info(" balance fetched ");

            return rs.getDouble(1);
        }
    }

    public void updateBalance(int accId, double amount) throws Exception {
        log.info("Updating balance ");
        try (Connection c = DBConfig.get().getConnection()) {

            PreparedStatement ps = c.prepareStatement(
                    "update accounts set balance=? where id=?");

            ps.setDouble(1, amount);
            ps.setInt(2, accId);
            ps.executeUpdate();
            log.info(" balance Updated");

        }
    }
}
