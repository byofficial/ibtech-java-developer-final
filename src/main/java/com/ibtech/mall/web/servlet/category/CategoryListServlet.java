package com.ibtech.mall.web.servlet.category;

import com.ibtech.mall.core.XmlHelper;
import com.ibtech.mall.database.entity.Category;
import com.ibtech.mall.database.manager.CategoryManager;
import com.ibtech.mall.xml.CategoryXml;
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

@WebServlet(name = "CategoryListServlet", value = "/api/categories")
public class CategoryListServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(CategoryListServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Category> categoryList;
            CategoryManager categoryManager = new CategoryManager();
            categoryList = categoryManager.findAll();
            Document document = CategoryXml.format(categoryList);

            response.setContentType("application/xml;charset=UTF-8");

            XmlHelper.dump(document, response.getOutputStream());
            logger.info("Category listing request received");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}
