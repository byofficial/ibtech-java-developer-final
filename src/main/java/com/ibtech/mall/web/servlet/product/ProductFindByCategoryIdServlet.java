package com.ibtech.mall.web.servlet.product;

import com.ibtech.mall.core.XmlHelper;
import com.ibtech.mall.database.entity.Product;
import com.ibtech.mall.database.manager.ProductManager;
import com.ibtech.mall.xml.ProductXml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductFindByCategoryIdServlet", value = "/api/products/category")
public class ProductFindByCategoryIdServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(ProductFindByCategoryIdServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Product> productList;
            long categoryId = Long.parseLong(request.getParameter("id"));
            ProductManager productManager = new ProductManager();
            productList = productManager.findByCategoryId(categoryId);
            Document document = ProductXml.format(productList);
            response.setContentType("application/xml;charset=UTF-8");
            XmlHelper.dump(document, response.getOutputStream());
            logger.info("Request to list products received");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}
