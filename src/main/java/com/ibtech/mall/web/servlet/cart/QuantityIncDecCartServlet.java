package com.ibtech.mall.web.servlet.cart;

import com.ibtech.mall.database.entity.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "QuantityIncDecCartServlet", value = "/api/cart/qty")
public class QuantityIncDecCartServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(QuantityIncDecCartServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String action = request.getParameter("operation");
            int id = Integer.parseInt(request.getParameter("id"));
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");

            if (action != null && id >= 1) {
                if (action.equals("inc")) {
                    for (Cart cart : cart_list) {
                        if (cart.getProductId() == id) {
                            long quantity = cart.getQuantity();
                            quantity++;
                            cart.setQuantity(quantity);
                            logger.info("Product increased. Redirecting to cart page!");
                            response.sendRedirect("/cart.jsp");
                        }
                    }
                }

                if (action.equals("dec")) {
                    for (Cart cart : cart_list) {
                        if (cart.getProductId() == id && cart.getQuantity() > 1) {
                            long quantity = cart.getQuantity();
                            quantity--;
                            cart.setQuantity(quantity);
                            break;
                        }
                    }
                    logger.info("Product decrement. Redirecting to cart page!");
                    response.sendRedirect("/cart.jsp");
                }
            } else {
                logger.info("Wrong action or request. Redirecting to cart page!");
                response.sendRedirect("/cart.jsp");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


}
