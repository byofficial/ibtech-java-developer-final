package com.ibtech.mall.web.servlet.product;

import com.ibtech.mall.core.XmlHelper;
import com.ibtech.mall.database.entity.Product;
import com.ibtech.mall.database.manager.ProductManager;
import com.ibtech.mall.xml.ProductXml;
import org.w3c.dom.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductFindByIdServlet", value = "/api/product")
public class ProductFindByIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Product product;
            long productId = Long.parseLong(request.getParameter("id"));
            ProductManager productManager = new ProductManager();
            product = productManager.findById(productId);
            if (product == null) {
                response.sendError(404);
            } else {
                Document document = ProductXml.format(product);
                response.setContentType("application/xml;charset=UTF-8");

                XmlHelper.dump(document, response.getOutputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
