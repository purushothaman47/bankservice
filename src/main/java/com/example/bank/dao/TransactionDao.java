
package com.example.bank.dao;

import com.example.bank.config.DBConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class TransactionDao {

    private static final Logger log =
            LoggerFactory.getLogger(TransactionDao.class);

    public void save(int accId, String type, double amount) throws Exception {
        try (Connection c = DBConfig.get().getConnection()) {

            log.info("Saving transaction");

            PreparedStatement ps = c.prepareStatement(
                    "insert into transactions(account_id,type,amount) values(?,?,?)");
            ps.setInt(1, accId);
            ps.setString(2, type);
            ps.setDouble(3, amount);
            ps.executeUpdate();
            log.info(" transaction Saved");
        }
    }
}
