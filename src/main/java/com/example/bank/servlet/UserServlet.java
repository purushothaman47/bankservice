
package com.example.bank.servlet;

import com.example.bank.dao.UserDao;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.*;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    private static final Logger log =
            LoggerFactory.getLogger(UserServlet.class);

    private final UserDao dao = new UserDao();
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        log.info("User API called");

        JSONObject in = new JSONObject(req.getReader().lines().reduce("", String::concat));
        JSONObject out = new JSONObject();

        try {
            if ("register".equals(in.getString("action")))
                out.put("success", dao.register(in.getString("username"), in.getString("password")));

            if ("login".equals(in.getString("action")))
            {
                boolean ok = dao.login(in.getString("username"),
                        in.getString("password"));
                if (ok) req.getSession(true)
                        .setAttribute("user", in.getString("username"));
                out.put("authenticated", ok);
            }
        }
        catch (Exception e)
        {
            out.put("error", e.getMessage());
        }
        res.setContentType("application/json");
        res.getWriter().write(out.toString());
    }
}
