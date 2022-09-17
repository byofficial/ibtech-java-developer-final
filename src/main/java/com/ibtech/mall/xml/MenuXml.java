package com.ibtech.mall.xml;

import com.ibtech.mall.core.XmlHelper;
import com.ibtech.mall.database.entity.Menu;
import com.ibtech.mall.database.entity.enums.Status;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.List;

public class MenuXml {
    public static Document format(Menu menu) throws ParserConfigurationException {
        Document document = XmlHelper.create("menu");
        Element element = document.getDocumentElement();
        XmlHelper.addSingleElementText(document, element, "menuId", menu.getMenuId());
        XmlHelper.addSingleElementText(document, element, "menuName", menu.getMenuName());
        XmlHelper.addSingleElementText(document, element, "menuUrl", menu.getMenuUrl());
        XmlHelper.addSingleElementText(document, element, "menuActive", menu.getMenuActive());
        XmlHelper.addSingleElementText(document, element, "status", menu.getStatus());

        return document;
    }

    public static Document format(List<Menu> categories) throws ParserConfigurationException {
        Document document = XmlHelper.create("menus");
        Element elementList = document.getDocumentElement();

        for (Menu menu : categories) {
            Element element = document.createElement("menu");
            XmlHelper.addSingleElementText(document, element, "menuId", menu.getMenuId());
            XmlHelper.addSingleElementText(document, element, "menuName", menu.getMenuName());
            XmlHelper.addSingleElementText(document, element, "menuUrl", menu.getMenuUrl());
            XmlHelper.addSingleElementText(document, element, "menuActive", menu.getMenuActive());
            XmlHelper.addSingleElementText(document, element, "status", menu.getStatus());
            elementList.appendChild(element);
        }

        return document;

    }

    public static Menu parse(Document document) {
        Element element = document.getDocumentElement();
        long menuId = XmlHelper.getSingleElementText(element, "menuId", 0);
        String menuName = XmlHelper.getSingleElementText(element, "menuName", "");
        String menuUrl = XmlHelper.getSingleElementText(element, "menuUrl", "");
        String menuActive = XmlHelper.getSingleElementText(element, "menuActive", "");
        long status = XmlHelper.getSingleElementText(element, "status", Status.ACTIVE);
        return new Menu(menuId, menuName, menuUrl, menuActive, Status.fromInteger(status));
    }

    public static List<Menu> parseList(Document document) {
        List<Menu> newCategoryList = new ArrayList<>();
        Element elementList = document.getDocumentElement();
        NodeList categories = elementList.getElementsByTagName("menu");
        for (int i = 0; i < categories.getLength(); i++) {
            Element element = (Element) categories.item(i);
            long menuId = XmlHelper.getSingleElementText(element, "menuId", 0);
            String menuName = XmlHelper.getSingleElementText(element, "menuName", "");
            String menuUrl = XmlHelper.getSingleElementText(element, "menuUrl", "");
            String menuActive = XmlHelper.getSingleElementText(element, "menuActive", "");
            long status = XmlHelper.getSingleElementText(element, "status", Status.ACTIVE);
            newCategoryList.add(new Menu(menuId, menuName, menuUrl, menuActive, Status.fromInteger(status)));
        }
        return newCategoryList;
    }
}
