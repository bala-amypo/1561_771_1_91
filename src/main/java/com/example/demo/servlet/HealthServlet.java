package com.example.demo.servlet;

import java.io.IOException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class HealthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/plain");
        resp.getWriter().write("OK");
    }
}
