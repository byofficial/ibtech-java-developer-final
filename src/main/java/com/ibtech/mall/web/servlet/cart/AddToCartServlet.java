package com.ibtech.mall.web.servlet.cart;

import com.ibtech.mall.database.entity.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AddToCartServlet", value = "/api/cart/create")
public class AddToCartServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(AddToCartServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            ArrayList<Cart> cartList = new ArrayList<>();
            long productId = Long.parseLong(request.getParameter("id"));
            Cart cart = new Cart();
            cart.setProductId(productId);
            cart.setQuantity(1);
            HttpSession session = request.getSession();
            ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
            if (cart_list == null) {
                cartList.add(cart);
                session.setAttribute("cart-list", cartList);
                response.sendRedirect("/");
                logger.info("Cart created");
            } else {
                cartList = cart_list;
                boolean exist = false;
                for (Cart cartItem : cart_list) {
                    if (cartItem.getProductId() == productId) {
                        exist = true;
                        response.sendRedirect("/");
                    }
                }
                if (!exist) {
                    cartList.add(cart);
                    response.sendRedirect("/");
                    logger.info("Product added to cart");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
