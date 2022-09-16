package com.ibtech.mall.web.servlet.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogoutUserServlet", value = "/logout")
public class LogoutUserServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(LogoutUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            if (request.getSession().getAttribute("auth") != null) {
                request.getSession().removeAttribute("auth");
                response.sendRedirect("index.jsp");
                logger.error("Successfully logged out");
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
