package com.ibtech.mall.web.servlet.order;

import com.ibtech.mall.database.manager.OrderManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RemoveOrderServlet", value = "/api/order/remove")
public class RemoveOrderServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(RemoveOrderServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String orderId = request.getParameter("id");
            if (orderId != null) {
                OrderManager orderManager = new OrderManager();
                orderManager.cancelOrder(Long.parseLong(orderId));
            }
            logger.info("Order cancellation request received");
            response.sendRedirect("/order.jsp");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


}
