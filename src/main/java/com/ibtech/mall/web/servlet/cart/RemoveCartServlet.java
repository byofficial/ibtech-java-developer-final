package com.ibtech.mall.web.servlet.cart;

import com.ibtech.mall.database.entity.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "RemoveCartServlet", value = "/api/cart/remove")
public class RemoveCartServlet extends HttpServlet {
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
                response.sendRedirect("/cart.jsp");

            } else {
                response.sendRedirect("/cart.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
