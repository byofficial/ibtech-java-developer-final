package com.ibtech.mall.web.servlet.user;

import com.ibtech.mall.core.EmailHelper;
import com.ibtech.mall.database.entity.Account;
import com.ibtech.mall.database.manager.AccountManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

@WebServlet(name = "RegisterUserServlet", value = "/register-account")
public class RegisterUserServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(RegisterUserServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String accountName = request.getParameter("accountName");
            String accountEmail = request.getParameter("accountEmail");
            String accountPassword = request.getParameter("accountPassword");
            AccountManager userManager = new AccountManager();
            Account isAccount = userManager.findByName(accountName);
            Account newAccount = new Account(0, accountName, accountPassword, accountEmail);
            if (isAccount != null) {
                String message = "Kullanıcı Sistemde Zaten Kayıtlı!";
                logger.info("Account already registered");
                response.sendRedirect("register.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
            } else {
                userManager.save(newAccount);
                // Document document = AccountXml.format(newAccount);
                request.getSession().setAttribute("auth", newAccount);
                RequestDispatcher dd = request.getRequestDispatcher("error.jsp");
                logger.info("Registered in. Redirecting to home page!");
                EmailHelper.sendEmail(accountEmail, "Hoşgeldiniz!", "welcome.html");
                response.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
