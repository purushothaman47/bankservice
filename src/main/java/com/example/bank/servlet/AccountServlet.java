package com.example.bank.servlet;

import com.example.bank.dao.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.*;
import java.io.IOException;

public class AccountServlet extends HttpServlet {
    private static final Logger log =
            LoggerFactory.getLogger(AccountServlet.class);

    private final AccountDao accDao = new AccountDao();
    private final TransactionDao txDao = new TransactionDao();

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        log.info("Account API called");

        JSONObject json = new JSONObject(req.getReader().lines().reduce("", String::concat));
        JSONObject out = new JSONObject();

        try {
            String action = json.optString("action");

            if ("CREATE".equalsIgnoreCase(action)) {

                int userId = 1;
                log.info("Creating account ");
                int accountId = accDao.createAccount(userId);
                out.put("accountId", accountId);

                log.info("Account created");

            }

            else {
                int accId = json.getInt("accountId");
                String type = json.getString("type");
                double amount = json.getDouble("amount");

                log.info("Transaction request ");

                double bal = accDao.getBalance(accId);

                if ("DEPOSIT".equalsIgnoreCase(type)) {
                    bal += amount;
                } else {
                    bal -= amount;
                }

                accDao.updateBalance(accId, bal);
                txDao.save(accId, type, amount);

                out.put("balance", bal);
                log.info("Transaction completed");
            }

        } catch (Exception e) {
            log.error("Error in AccountServlet");

            out.put("error", "Something went wrong");
        }

        res.setContentType("application/json");
        res.getWriter().write(out.toString());
    }
}
