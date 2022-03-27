package ru.gb.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "TestHttpServlet", urlPatterns = "/test_http_servlet")
public class TestHttpServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(TestHttpServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("New GET request");
        logger.info(req.getContextPath());
        logger.info(req.getServletPath());
        logger.info(req.getQueryString());
        logger.info("User agent: {}", req.getHeader("User-agent"));
        resp.setHeader("Content-Type", "text/html; charset=utf-8");
        resp.getWriter().print("<h1>New GET request</h1>");
        resp.getWriter().printf("<h2>New GET request params: param1 = %s, param2 = %s</h2>",
                req.getParameter("param1"),
                req.getParameter("param2"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("New POST request");
        resp.getWriter().printf("<h1>New POST request: echo %s</h1>", readAllLines(req.getReader()));
    }

    private String readAllLines(BufferedReader reader) throws IOException {
        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
        }

        return content.toString();
    }
}
