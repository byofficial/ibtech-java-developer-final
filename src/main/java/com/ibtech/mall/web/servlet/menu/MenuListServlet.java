package com.ibtech.mall.web.servlet.menu;

import com.ibtech.mall.core.XmlHelper;
import com.ibtech.mall.database.entity.Menu;
import com.ibtech.mall.database.manager.MenuManager;
import com.ibtech.mall.xml.MenuXml;
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

@WebServlet(name = "MenuListServlet", value = "/api/menus")
public class MenuListServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(MenuListServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Menu> menuList;
            MenuManager menuManager = new MenuManager();
            menuList = menuManager.findAll();
            Document document = MenuXml.format(menuList);
            response.setContentType("application/xml;charset=UTF-8");
            XmlHelper.dump(document, response.getOutputStream());
            logger.info("Menu listing request received");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}
