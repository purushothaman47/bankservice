package com.bank.servlet;

import com.bank.service.AccountService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OpenAccountServlet extends HttpServlet {

    private final AccountService service = new AccountService();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        JsonNode json = mapper.readTree(req.getInputStream());

        String name = json.get("name").asText();
        double balance = json.get("balance").asDouble();

        service.openAccount(name, balance);

        resp.setContentType("application/json");
        resp.getWriter().write(
                "{\"message\":\"Account opened successfully\"}"
        );
    }
}
