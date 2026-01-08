package com.bank.filter;

import javax.servlet.*;
import java.io.IOException;

public class RequestLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        System.out.println("Incoming request received");
        chain.doFilter(request, response);
    }
}
