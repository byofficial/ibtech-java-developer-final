package com.ibtech.mall.xml;

import com.ibtech.mall.core.XmlHelper;
import com.ibtech.mall.database.entity.Category;
import com.ibtech.mall.database.entity.enums.FeaturedCategory;
import com.ibtech.mall.database.entity.enums.Status;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.List;

public class CategoryXml {
    public static Document format(Category category) throws ParserConfigurationException {
        Document document = XmlHelper.create("category");
        Element element = document.getDocumentElement();
        XmlHelper.addSingleElementText(document, element, "categoryId", category.getCategoryId());
        XmlHelper.addSingleElementText(document, element, "categoryName", category.getCategoryName());
        XmlHelper.addSingleElementText(document, element, "status", category.getStatus());
        XmlHelper.addSingleElementText(document, element, "featuredCategory", category.getFeaturedCategory());
        return document;
    }

    public static Document format(List<Category> categories) throws ParserConfigurationException {
        Document document = XmlHelper.create("categories");
        Element elementList = document.getDocumentElement();

        for (Category category : categories) {
            Element element = document.createElement("category");
            XmlHelper.addSingleElementText(document, element, "categoryId", category.getCategoryId());
            XmlHelper.addSingleElementText(document, element, "categoryName", category.getCategoryName());
            XmlHelper.addSingleElementText(document, element, "status", category.getStatus());
            XmlHelper.addSingleElementText(document, element, "featuredCategory", category.getFeaturedCategory());
            elementList.appendChild(element);
        }

        return document;

    }

    public static Category parse(Document document) {
        Element element = document.getDocumentElement();
        long categoryId = XmlHelper.getSingleElementText(element, "categoryId", 0);
        String categoryName = XmlHelper.getSingleElementText(element, "categoryName", "");
        long status = XmlHelper.getSingleElementText(element, "status", Status.ACTIVE);
        long featuredCategory = XmlHelper.getSingleElementText(element, "featuredCategory", FeaturedCategory.ACTIVE);
        return new Category(categoryId, categoryName, Status.fromInteger(status), FeaturedCategory.fromInteger(featuredCategory));
    }

    public static List<Category> parseList(Document document) {
        List<Category> newCategoryList = new ArrayList<>();
        Element elementList = document.getDocumentElement();
        NodeList categories = elementList.getElementsByTagName("category");
        for (int i = 0; i < categories.getLength(); i++) {
            Element element = (Element) categories.item(i);
            long categoryId = XmlHelper.getSingleElementText(element, "categoryId", 0);
            String categoryName = XmlHelper.getSingleElementText(element, "categoryName", "");
            long status = XmlHelper.getSingleElementText(element, "status", Status.ACTIVE);
            long featuredCategory = XmlHelper.getSingleElementText(element, "featuredCategory", FeaturedCategory.ACTIVE);
            newCategoryList.add(new Category(categoryId, categoryName, Status.fromInteger(status), FeaturedCategory.fromInteger(featuredCategory)));
        }
        return newCategoryList;
    }
}
