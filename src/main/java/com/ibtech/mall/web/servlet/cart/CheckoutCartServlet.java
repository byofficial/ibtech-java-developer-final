package com.ibtech.mall.web.servlet.cart;

import com.ibtech.mall.database.entity.Account;
import com.ibtech.mall.database.entity.Cart;
import com.ibtech.mall.database.entity.Orders;
import com.ibtech.mall.database.manager.OrderManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "CheckoutCartServlet", value = "/api/cart/checkout")
public class CheckoutCartServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(CheckoutCartServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            Account auth = (Account) request.getSession().getAttribute("auth");
            if (cart_list != null && auth != null) {
                for (Cart cartItem : cart_list) {
                    Orders orders = new Orders();
                    orders.setProductId(cartItem.getProductId());
                    orders.setAccountId(auth.getAccountId());
                    orders.setQuantity(cartItem.getQuantity());
                    OrderManager orderManager = new OrderManager();
                    boolean result = orderManager.save(orders);
                    if (!result) break;
                }
                cart_list.clear();
                logger.info("Order created!");
                response.sendRedirect("/order.jsp");
            } else {
                if (auth == null) {
                    logger.info("User not found. Redirecting to login page!");
                    response.sendRedirect("/login.jsp");
                }
                logger.info("Failed to create order. Redirecting to cart page!");
                response.sendRedirect("/cart.jsp");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Redirecting POST request");
        doGet(request, response);
    }
}
