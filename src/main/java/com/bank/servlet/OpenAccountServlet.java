package com.bank.servlet;

import com.bank.service.AccountService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OpenAccountServlet extends HttpServlet {

    private final AccountService service = new AccountService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String name = req.getParameter("name");
        double balance = Double.parseDouble(req.getParameter("balance"));

        service.openAccount(name, balance);
        resp.getWriter().write("Account Created Successfully");
    }
}
