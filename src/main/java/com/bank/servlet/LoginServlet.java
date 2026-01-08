package com.bank.servlet;

import com.bank.model.User;
import com.bank.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

public class LoginServlet extends HttpServlet {

    private final AuthService authService = new AuthService();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Map<String, String> body =
                mapper.readValue(req.getInputStream(), Map.class);

        User user = authService.login(
                body.get("username"),
                body.get("password")
        );

        resp.setContentType("application/json");

        if (user == null) {
            resp.setStatus(401);
            resp.getWriter().write("{\"message\":\"Invalid Credentials\"}");
            return;
        }

        req.getSession(true).setAttribute("user", user);
        resp.getWriter().write("{\"message\":\"Login Successful\"}");
    }
}
