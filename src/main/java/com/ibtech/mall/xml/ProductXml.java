package com.ibtech.mall.xml;

import com.ibtech.mall.core.XmlHelper;
import com.ibtech.mall.database.entity.Product;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.List;

public class ProductXml {
    public static Document format(Product product) throws ParserConfigurationException {
        Document document = XmlHelper.create("product");
        Element element = document.getDocumentElement();
        formatDocumentElement(document, product, element);
        return document;
    }

    public static Document format(List<Product> products) throws ParserConfigurationException {
        Document document = XmlHelper.create("products");
        Element elementList = document.getDocumentElement();

        for (Product product : products) {
            Element element = document.createElement("product");
            formatDocumentElement(document, product, element);
            elementList.appendChild(element);
        }

        return document;

    }


    public static Product parse(Document document) {
        Element element = document.getDocumentElement();
        long productId = XmlHelper.getSingleElementText(element, "productId", 0);
        String productName = XmlHelper.getSingleElementText(element, "productName", "");
        String imagePath = XmlHelper.getSingleElementText(element, "imagePath", "");
        double salesPrice = XmlHelper.getSingleElementText(element, "salesPrice", 0.0);
        long categoryId = XmlHelper.getSingleElementText(element, "categoryId", 0);
        String description = XmlHelper.getSingleElementText(element, "description", "");
        String longDescription = XmlHelper.getSingleElementText(element, "longDescription", "");
        Product product = new Product(productId, productName, imagePath, salesPrice, description, longDescription);
        product.setCategoryId(categoryId);
        return product;
    }

    public static List<Product> parseList(Document document) {
        List<Product> newList = new ArrayList<>();
        Element elementList = document.getDocumentElement();
        NodeList nodeEntityList = elementList.getElementsByTagName("product");
        for (int i = 0; i < nodeEntityList.getLength(); i++) {
            Element element = (Element) nodeEntityList.item(i);
            long productId = XmlHelper.getSingleElementText(element, "productId", 0);
            String productName = XmlHelper.getSingleElementText(element, "productName", "");
            String imagePath = XmlHelper.getSingleElementText(element, "imagePath", "");
            double salesPrice = XmlHelper.getSingleElementText(element, "salesPrice", 0.0);
            long categoryId = XmlHelper.getSingleElementText(element, "categoryId", 0);
            String description = XmlHelper.getSingleElementText(element, "description", "");
            String longDescription = XmlHelper.getSingleElementText(element, "longDescription", "");
            Product product = new Product(productId, productName, imagePath, salesPrice, description, longDescription);
            product.setCategoryId(categoryId);
            newList.add(product);
        }
        return newList;
    }

    private static void formatDocumentElement(Document document, Product product, Element element) {
        XmlHelper.addSingleElementText(document, element, "productId", product.getProductId());
        XmlHelper.addSingleElementText(document, element, "productName", product.getProductName());
        XmlHelper.addSingleElementText(document, element, "imagePath", product.getImagePath());
        XmlHelper.addSingleElementText(document, element, "salesPrice", product.getSalesPrice());
        XmlHelper.addSingleElementText(document, element, "categoryId", product.getCategoryId());
        XmlHelper.addSingleElementText(document, element, "description", product.getProductName());
        XmlHelper.addSingleElementText(document, element, "longDescription", product.getProductName());
    }
}
