
package com.example.bank.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AuthFilter implements Filter {

    private static final Logger log =
            LoggerFactory.getLogger(AuthFilter.class);

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        log.info("AuthFilter triggered");

        HttpServletRequest r = (HttpServletRequest) req;

        HttpSession s = r.getSession(false);

        if (s == null || s.getAttribute("user") == null) {

            ((HttpServletResponse) res).setStatus(401);

            res.getWriter().write("{\"error\":\"Unauthorized\"}");

            return;
        }

        log.info("User authorized, proceeding request");
        chain.doFilter(req, res);
    }
}
