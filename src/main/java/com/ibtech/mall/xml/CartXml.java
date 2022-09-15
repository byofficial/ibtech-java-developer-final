package com.ibtech.mall.xml;

import com.ibtech.mall.core.XmlHelper;
import com.ibtech.mall.database.entity.Cart;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.ParserConfigurationException;

public class CartXml {
    public static Document format(Cart cart) throws ParserConfigurationException {
        Document document = XmlHelper.create("cart");
        Element element = document.getDocumentElement();
        return document;
    }

}
