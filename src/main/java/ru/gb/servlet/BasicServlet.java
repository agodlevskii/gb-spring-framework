package ru.gb.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class BasicServlet implements Servlet {
    private static final Logger logger = LoggerFactory.getLogger(BasicServlet.class);

    private transient ServletConfig config;

    @Override
    public void init(ServletConfig servletConfig) {
        this.config = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        logger.info("New request");
        servletResponse.getWriter().println("<h1>Servlet Content</h1>");
    }

    @Override
    public java.lang.String getServletInfo() {
        return "BasicServlet";
    }

    @Override
    public void destroy() {
        logger.info("BasisServlet is destroyed");
    }
}
