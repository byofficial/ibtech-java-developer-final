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

@WebServlet(name = "ProductListServlet", value = "/api/products")
public class ProductListServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(ProductListServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Product> productList;
            ProductManager productManager = new ProductManager();
            productList = productManager.findAll();
            Document document = ProductXml.format(productList);
            response.setContentType("application/xml;charset=UTF-8");
            XmlHelper.dump(document, response.getOutputStream());
            logger.info("Product listing request received");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}
