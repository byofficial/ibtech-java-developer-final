package com.ibtech.mall.web.servlet.user;

import com.ibtech.mall.database.entity.Account;
import com.ibtech.mall.database.manager.AccountManager;
import com.ibtech.mall.xml.AccountXml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginUserServlet", value = "/login-account")
public class LoginUserServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(LoginUserServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Account account;
            String accountName = request.getParameter("accountName");
            String accountPassword = request.getParameter("accountPassword");
            AccountManager userManager = new AccountManager();
            account = userManager.login(accountName, accountPassword);

            if (account == null) {
                response.sendError(404);
                logger.info("Account not found");
            } else {
              //  Document document = AccountXml.format(account);
                request.getSession().setAttribute("auth", account);
                RequestDispatcher dd = request.getRequestDispatcher("error.jsp");
                logger.info("Signed in. Redirecting to home page!");
                response.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
