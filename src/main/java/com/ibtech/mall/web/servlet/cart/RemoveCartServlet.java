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

@WebServlet(name = "RemoveCartServlet", value = "/api/cart/remove")
public class RemoveCartServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(RemoveCartServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String parameterId = request.getParameter("id");
            if (parameterId != null) {
                ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
                if (cart_list != null) {
                    for (Cart cart : cart_list) {
                        if (cart.getProductId() == Long.parseLong(parameterId)) {
                            cart_list.remove(cart_list.indexOf(cart));
                            break;
                        }
                    }
                }
                logger.info("The product has been removed from the cart. Redirecting to cart page!");
                response.sendRedirect("/cart.jsp");

            } else {
                logger.info("Wrong action or request. Redirecting to cart page!");
                response.sendRedirect("/cart.jsp");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


}
