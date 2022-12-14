package com.ibtech.mall;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "helloServlet", value = "/test")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {

        message = System.getenv("DB_DRIVER");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html><body>");
            out.println("<h1>" + message + "</h1>");
            out.println("</body></html>");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        // Hello


    }

    public void destroy() {
    }
}